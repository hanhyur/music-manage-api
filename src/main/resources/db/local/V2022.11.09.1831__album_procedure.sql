-- ALBUM PAGES --
CREATE OR ALTER PROCEDURE DSP_PF_ALBUM_GET_PAGE
    @page NVARCHAR(MAX),
    @find NVARCHAR(MAX)
AS
BEGIN
    SET NOCOUNT ON
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED

    DECLARE @PAGE_NO   INT = JSON_VALUE(@page, '$.number')
    DECLARE @PAGE_SIZE INT = JSON_VALUE(@page, '$.size'  )

    SELECT
        COUNT(*) OVER() AS 'total',
        @PAGE_NO        AS 'number',
        @PAGE_SIZE      AS 'size',
        AL.ALBUM_ID,
        AL.TITLE,
        AL.RELEASE_DATE,
        AL.GENRE,
        AL.DESCRIPTION,
        AL.REGISTRANT,
        AL.CREATED_AT,
        A.ARTIST_ID     AS 'artist.artist_id',
        A.NAME          AS 'artist.name'
    FROM
        TB_PF_ALBUM AL
        LEFT JOIN TB_PF_ARTIST A on AL.ARTIST_ID = A.ARTIST_ID
    WHERE AL.TITLE LIKE CONCAT('%', ISNULL(JSON_VALUE(@find, '$.title'), AL.TITLE), '%')
    ORDER BY AL.ALBUM_ID DESC
    OFFSET (@PAGE_NO * @PAGE_SIZE) ROW FETCH NEXT @PAGE_SIZE ROW ONLY
END
GO

-- ALBUM GET --
CREATE OR ALTER PROCEDURE DSP_PF_ALBUM_GET
    @album NVARCHAR(MAX)
AS
BEGIN
    SET NOCOUNT ON
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED

    SELECT
        AL.ALBUM_ID,
        AL.TITLE,
        AL.RELEASE_DATE,
        AL.GENRE,
        AL.DESCRIPTION,
        AL.REGISTRANT,
        AL.CREATED_AT,
        A.ARTIST_ID     AS 'artist.artist_id',
        A.NAME          AS 'artist.name',
        ST.TRACK_ID     AS 'track.track_id',
        ST.TRACK_NO     AS 'track.trackNo',
        ST.TITLE        AS 'track.title',
        ST.PLAY_TIME    AS 'track.playTime',
        ST.EXPOSURE     AS 'track.exposure'
    FROM TB_PF_ALBUM AL
             LEFT JOIN TB_PF_ARTIST A on AL.ARTIST_ID = A.ARTIST_ID
             LEFT JOIN TB_PF_SOUNDTRACK ST on AL.ALBUM_ID = ST.ALBUM_ID
    WHERE AL.ALBUM_ID = JSON_VALUE(@album, '$.id')
END
GO

-- ALBUM INSERT --
CREATE OR ALTER PROCEDURE DSP_PF_ALBUM_ADD
    @album NVARCHAR(MAX)
AS
BEGIN
    SET NOCOUNT ON
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
    DECLARE @ERROR_CODE NVARCHAR(4000) = 'E00100006'
    BEGIN TRY
        BEGIN TRANSACTION

            DECLARE @id INT;

            BEGIN
                INSERT INTO
                    TB_PF_ALBUM (
                        TITLE,
                        RELEASE_DATE,
                        GENRE,
                        DESCRIPTION,
                        REGISTRANT,
                        CREATED_AT,
                        UPDATED_AT,
                        ARTIST_ID
                    )
                VALUES (
                    JSON_VALUE(@album, '$.title'),
                    JSON_VALUE(@album, '$.release_date'),
                    JSON_VALUE(@album, '$.genre'),
                    JSON_VALUE(@album, '$.description'),
                    'aslan',
                    GETDATE(),
                    GETDATE(),
                    JSON_VALUE(@album, '$.artist.id')
                );

                SET @id = @@IDENTITY;
                SELECT @id;
            END

            BEGIN

                DECLARE @index INT, @item NVARCHAR(MAX), @len INT
                SET @index = 0;
                SET @item = JSON_QUERY(@album, CONCAT('$.soundtracks[', @index, ']'));
                SET @len = ISNULL(LEN(@item), 0);

                WHILE (@len > 0)
                    BEGIN

                        INSERT INTO
                            TB_PF_SOUNDTRACK (
                                TRACK_NO,
                                TITLE,
                                PLAY_TIME,
                                EXPOSURE,
                                CREATED_AT,
                                UPDATED_AT,
                                ALBUM_ID
                            )
                        VALUES (
                            JSON_VALUE(@item, '$.track_no'),
                            JSON_VALUE(@item, '$.title'),
                            JSON_VALUE(@item, '$.play_time'),
                            JSON_VALUE(@item, '$.exposure'),
                            GETDATE(),
                            GETDATE(),
                            @id
                        );

                        SET @index = @index + 1;
                        SET @item = JSON_QUERY(@album, CONCAT('$.soundtracks[', @index, ']'));
                        SET @len = ISNULL(LEN(@item), 0);
                    END;
            END

        COMMIT TRANSACTION
    END TRY
    BEGIN CATCH
        IF XACT_STATE() <> 0 ROLLBACK TRANSACTION
        SET @ERROR_CODE = CONCAT(@ERROR_CODE, '|', (SELECT ERROR_MESSAGE()))
        RAISERROR (@ERROR_CODE, 11, 1);
    END CATCH
END
GO

-- ALBUM UPDATE --
CREATE OR ALTER PROCEDURE DSP_PF_ALBUM_MODIFY
    @album NVARCHAR(MAX)
AS
BEGIN
    SET NOCOUNT ON
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
    DECLARE @ERROR_CODE NVARCHAR(4000) = 'E00100006';
    BEGIN TRY
        BEGIN TRANSACTION

            DECLARE @id INT;

            UPDATE
                TB_PF_ALBUM
            SET
                TITLE           = JSON_VALUE(@album, '$.title'),
                RELEASE_DATE    = JSON_VALUE(@album, '$.release_date'),
                GENRE           = JSON_VALUE(@album, '$.genre'),
                DESCRIPTION     = JSON_VALUE(@album, '$.description'),
                ARTIST_ID       = JSON_VALUE(@album, '$.artist.id'),
                UPDATED_AT      = GETDATE()
            WHERE
                ALBUM_ID        = JSON_VALUE(@album, '$.id');

            SELECT JSON_VALUE(@album, '$.id');

            BEGIN
                MERGE
                    TB_PF_SOUNDTRACK A
                USING
                    (
                    SELECT
                         *
                    FROM
                         OPENJSON(JSON_QUERY(@album, '$.soundtracks'))
                    WITH (
                        TRACK_ID INT '$.id',
                        TRACK_NO INT '$.track_no',
                        TITLE NVARCHAR(100) '$.title',
                        PLAY_TIME NVARCHAR(100) '$.play_time',
                        EXPOSURE NVARCHAR(50) '$.exposure'
                         )
                    ) B
                ON
                    A.TRACK_ID = B.TRACK_ID
                WHEN NOT MATCHED AND B.TRACK_ID IS NOT NULL -- INSERT TRACK
                THEN
                    INSERT (
                        TRACK_NO,
                        TITLE,
                        PLAY_TIME,
                        EXPOSURE,
                        CREATED_AT,
                        UPDATED_AT
                    )
                    VALUES (
                        B.TRACK_NO,
                        B.TITLE,
                        B.PLAY_TIME,
                        B.EXPOSURE,
                        GETDATE(),
                        GETDATE()
                    )
                WHEN MATCHED AND B.TRACK_ID = A.TRACK_ID -- UPDATE TRACK
                THEN
                    UPDATE
                    SET
                        TRACK_NO = B.TRACK_NO,
                        TITLE = B.TITLE,
                        PLAY_TIME = B.PLAY_TIME,
                        EXPOSURE = B.EXPOSURE,
                        UPDATED_AT = GETDATE()
                WHEN NOT MATCHED BY SOURCE AND A.ALBUM_ID = @id -- DELETE TRACK
                THEN
                    DELETE;
            END

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF XACT_STATE() <> 0 ROLLBACK TRANSACTION;
        SET @ERROR_CODE = CONCAT(@ERROR_CODE, '|', (SELECT ERROR_MESSAGE()));
        RAISERROR (@ERROR_CODE, 11, 1);
    END CATCH
END
GO

-- ALBUM DELETE --
CREATE OR ALTER PROCEDURE DSP_PF_ALBUM_REMOVE
    @album NVARCHAR(MAX)
AS
BEGIN
    SET NOCOUNT ON
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
    DECLARE @ERROR_CODE NVARCHAR(4000) = 'E00100006';
    BEGIN TRY
        BEGIN TRANSACTION

            BEGIN
                DELETE FROM
                    TB_PF_SOUNDTRACK
                WHERE
                    ALBUM_ID = JSON_VALUE(@album, '$.id');
            END

            BEGIN
                DELETE FROM
                    TB_PF_ALBUM
                WHERE
                    ALBUM_ID = JSON_VALUE(@album, '$.id');
            END

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF XACT_STATE() <> 0 ROLLBACK TRANSACTION;
        SET @ERROR_CODE = CONCAT(@ERROR_CODE, '|', (SELECT ERROR_MESSAGE()));
        RAISERROR (@ERROR_CODE, 11, 1);
    END CATCH
END
GO