INSERT INTO TB_PF_ARTIST (NAME, BIRTH, AGENCY, NATIONALITY, INTRODUCTION, REGISTRANT, CREATED_AT, UPDATED_AT)
VALUES (N'아이유', '1993-05-16', N'이담엔터테인먼트', N'한국', N'아이유는 베스트셀러이자 스테디셀러', 'aslan', GETDATE(), GETDATE());
INSERT INTO TB_PF_ARTIST (NAME, BIRTH, AGENCY, NATIONALITY, INTRODUCTION, REGISTRANT, CREATED_AT, UPDATED_AT)
VALUES (N'스텔라장', '1991-11-18', 'GLG', N'한국', N'작사, 작곡은 물론 기타, 건반, 플롯 등 여러 악기에도 수준급 연주 실력을 보유하고 있는 뛰어난 음악적 감각의 소유자', 'aslan', GETDATE(), GETDATE());
INSERT INTO TB_PF_ARTIST (NAME, BIRTH, AGENCY, NATIONALITY, INTRODUCTION, REGISTRANT, CREATED_AT, UPDATED_AT)
VALUES (N'윤하', '1988-04-29', N'씨나인엔터테인먼트', N'한국', N'저물어가는 아날로그 시대의 맥을 잇는 아티스트', 'aslan', GETDATE(), GETDATE());
INSERT INTO TB_PF_ARTIST (NAME, BIRTH, AGENCY, NATIONALITY, INTRODUCTION, REGISTRANT, CREATED_AT, UPDATED_AT)
VALUES (N'청하', '1996-02-09', N'엠엔에이치엔터테인먼트', N'한국', N'이번에는 어떤 느낌으로 나올까?''라는 궁금함이 드는 가수', 'aslan', GETDATE(), GETDATE());

INSERT INTO TB_PF_ALBUM (TITLE, RELEASE_DATE, GENRE, DESCRIPTION, REGISTRANT, CREATED_AT, UPDATED_AT, ARTIST_ID)
VALUES ('Modern Times', '2013-10-08', 'K-pop', N'2013년 10월 8일에 발매된 아이유의 정규 3집이다.', 'aslan', GETDATE(), GETDATE(), 1);
INSERT INTO TB_PF_ALBUM (TITLE, RELEASE_DATE, GENRE, DESCRIPTION, REGISTRANT, CREATED_AT, UPDATED_AT, ARTIST_ID)
VALUES ('Love poem', '2019-11-18','K-pop', N'2019년 11월 18일에 발매된 아이유의 미니 5집 엘범이다.', 'aslan',  GETDATE(), GETDATE(), 1);
INSERT INTO TB_PF_ALBUM (TITLE, RELEASE_DATE, GENRE, DESCRIPTION, REGISTRANT, CREATED_AT, UPDATED_AT, ARTIST_ID)
VALUES ('Stairs', '2021-10-15', 'Folk / Blues', N'2021년 10월 15일에 발매된 스텔라장의 다섯 번째 미니앨범이다.', 'aslan', GETDATE(), GETDATE(), 2);

INSERT INTO TB_PF_SOUNDTRACK (TRACK_NO, TITLE, PLAY_TIME, EXPOSURE, CREATED_AT, UPDATED_AT, ALBUM_ID)
VALUES ('1', N'을의 연애', '03:11', 0, GETDATE(), GETDATE(), '1');
INSERT INTO TB_PF_SOUNDTRACK (TRACK_NO, TITLE, PLAY_TIME, EXPOSURE, CREATED_AT, UPDATED_AT, ALBUM_ID)
VALUES ('2', N'누구나 비밀은 있다', '03:49', 0, GETDATE(), GETDATE(), '1');
INSERT INTO TB_PF_SOUNDTRACK (TRACK_NO, TITLE, PLAY_TIME, EXPOSURE, CREATED_AT, UPDATED_AT, ALBUM_ID)
VALUES ('3', N'입술 사이 (50cm)', '02:50', 1, GETDATE(), GETDATE(), '1');
INSERT INTO TB_PF_SOUNDTRACK (TRACK_NO, TITLE, PLAY_TIME, EXPOSURE, CREATED_AT, UPDATED_AT, ALBUM_ID)
VALUES ('4', N'분홍신', '04:14', 1, GETDATE(), GETDATE(), '1');
INSERT INTO TB_PF_SOUNDTRACK (TRACK_NO, TITLE, PLAY_TIME, EXPOSURE, CREATED_AT, UPDATED_AT, ALBUM_ID)
VALUES ('5', N'Modern Times', '03:25', 1, GETDATE(), GETDATE(), '1');
INSERT INTO TB_PF_SOUNDTRACK (TRACK_NO, TITLE, PLAY_TIME, EXPOSURE, CREATED_AT, UPDATED_AT, ALBUM_ID)
VALUES ('1', N'unlucky', '03:51', 1, GETDATE(), GETDATE(), '2');
INSERT INTO TB_PF_SOUNDTRACK (TRACK_NO, TITLE, PLAY_TIME, EXPOSURE, CREATED_AT, UPDATED_AT, ALBUM_ID)
VALUES ('2', N'그 사람', '03:51', 1, GETDATE(), GETDATE(), '2');
INSERT INTO TB_PF_SOUNDTRACK (TRACK_NO, TITLE, PLAY_TIME, EXPOSURE, CREATED_AT, UPDATED_AT, ALBUM_ID)
VALUES ('3', N'Blueming', '03:37', 1, GETDATE(), GETDATE(), '2');
INSERT INTO TB_PF_SOUNDTRACK (TRACK_NO, TITLE, PLAY_TIME, EXPOSURE, CREATED_AT, UPDATED_AT, ALBUM_ID)
VALUES ('4', N'시간의 바깥', '05:01', 0, GETDATE(), GETDATE(), '2');
INSERT INTO TB_PF_SOUNDTRACK (TRACK_NO, TITLE, PLAY_TIME, EXPOSURE, CREATED_AT, UPDATED_AT, ALBUM_ID)
VALUES ('5', N'자장가', '04:22', 0, GETDATE(), GETDATE(), '2');
INSERT INTO TB_PF_SOUNDTRACK (TRACK_NO, TITLE, PLAY_TIME, EXPOSURE, CREATED_AT, UPDATED_AT, ALBUM_ID)
VALUES ('6', N'Love poem', '04:18', 1, GETDATE(), GETDATE(), '2');