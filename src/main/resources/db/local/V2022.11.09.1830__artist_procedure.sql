-- ARTIST PAGES --
CREATE OR ALTER PROCEDURE DSP_PF_ARTIST_GET_PAGE
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
        A.ARTIST_ID,
        A.NAME,
        A.BIRTH,
        A.AGENCY,
        A.NATIONALITY,
        A.INTRODUCTION,
        A.REGISTRANT,
        A.CREATED_AT
    FROM
        TB_PF_ARTIST A
    WHERE  A.NAME LIKE CONCAT('%', ISNULL(JSON_VALUE(@find, '$.name'), A.NAME), '%')
    ORDER  BY A.ARTIST_ID DESC
    OFFSET (@PAGE_NO * @PAGE_SIZE) ROW FETCH NEXT @PAGE_SIZE ROW ONLY
END
GO

-- ARTIST GET --
CREATE OR ALTER PROCEDURE DSP_PF_ARTIST_GET
    @artist NVARCHAR(MAX)
AS
BEGIN
    SET NOCOUNT ON
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED

    SELECT
        A.ARTIST_ID,
        A.NAME,
        A.BIRTH,
        A.AGENCY,
        A.NATIONALITY,
        A.INTRODUCTION,
        A.REGISTRANT,
        A.CREATED_AT
    FROM
        TB_PF_ARTIST A
    WHERE A.ARTIST_ID = JSON_VALUE(@artist, '$.id')
END
GO

-- ARTIST INSERT --
CREATE OR ALTER PROCEDURE DSP_PF_ARTIST_ADD
    @artist NVARCHAR(MAX)
AS
BEGIN
    SET NOCOUNT ON
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
	DECLARE @ERROR_CODE NVARCHAR(4000) = 'E00100006';
	BEGIN TRY
        BEGIN TRANSACTION

            INSERT INTO
                TB_PF_ARTIST (
                    NAME,
                    BIRTH,
                    AGENCY,
                    NATIONALITY,
                    INTRODUCTION,
                    REGISTRANT,
                    CREATED_AT,
                    UPDATED_AT
                )
            VALUES(
                JSON_VALUE(@artist, '$.name'),
                JSON_VALUE(@artist, '$.birth'),
                JSON_VALUE(@artist, '$.agency'),
                JSON_VALUE(@artist, '$.nationality'),
                JSON_VALUE(@artist, '$.introduction'),
                'aslan',
                GETDATE(),
                GETDATE()
            );

            SELECT @@IDENTITY;

		COMMIT TRANSACTION;
	END TRY
	BEGIN CATCH
		IF XACT_STATE() <> 0 ROLLBACK TRANSACTION;
		SET @ERROR_CODE = CONCAT(@ERROR_CODE, '|', (SELECT ERROR_MESSAGE()));
		RAISERROR (@ERROR_CODE, 11, 1);
	END CATCH
END
GO

-- ARTIST UPDATE --
CREATE OR ALTER PROCEDURE DSP_PF_ARTIST_MODIFY
    @artist NVARCHAR(MAX)
AS
BEGIN
    SET NOCOUNT ON
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
	DECLARE @ERROR_CODE NVARCHAR(4000) = 'E00100006';
	BEGIN TRY
		BEGIN TRANSACTION

			UPDATE
			    TB_PF_ARTIST
			SET
                NAME          = JSON_VALUE(@artist, '$.name'),
                BIRTH         = JSON_VALUE(@artist, '$.birth'),
                AGENCY        = JSON_VALUE(@artist, '$.agency'),
                NATIONALITY   = JSON_VALUE(@artist, '$.nationality'),
                INTRODUCTION  = JSON_VALUE(@artist, '$.introduction')
			WHERE
			    ARTIST_ID     = JSON_VALUE(@artist, '$.id');

			SELECT JSON_VALUE(@artist, '$.id');

		COMMIT TRANSACTION;
	END TRY
	BEGIN CATCH
		IF XACT_STATE() <> 0 ROLLBACK TRANSACTION;
		SET @ERROR_CODE = CONCAT(@ERROR_CODE, '|', (SELECT ERROR_MESSAGE()));
		RAISERROR (@ERROR_CODE, 11, 1);
	END CATCH
END
GO

-- ARTIST REMOVE --
CREATE OR ALTER PROCEDURE DSP_PF_ARTIST_REMOVE
    @artist NVARCHAR(MAX)
AS
BEGIN
    SET NOCOUNT ON
	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
	DECLARE @ERROR_CODE NVARCHAR(4000) = 'E00100006';
	BEGIN TRY
		BEGIN TRANSACTION

		    DELETE FROM
		        TB_PF_ARTIST
		    WHERE
		        ARTIST_ID = JSON_VALUE(@artist, '$.id')

		COMMIT TRANSACTION;
	END TRY
	BEGIN CATCH
		IF XACT_STATE() <> 0 ROLLBACK TRANSACTION;
		SET @ERROR_CODE = CONCAT(@ERROR_CODE, '|', (SELECT ERROR_MESSAGE()));
		RAISERROR (@ERROR_CODE, 11, 1);
	END CATCH
END
GO