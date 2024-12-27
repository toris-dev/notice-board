-- POST 테이블 생성
CREATE MEMORY TABLE post (
    postId VARCHAR(16) NOT NULL PRIMARY KEY, -- 게시글의 고유 ID (Primary Key)
    authorId VARCHAR(10),                    -- 작성자 ID
    title VARCHAR(100),                       -- 게시글 제목
    content VARCHAR(1000),                    -- 게시글 내용
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 게시글 생성 시간
    updatedAt TIMESTAMP,                     -- 게시글 업데이트 시간
    useYn CHAR(1),                           -- 게시글 사용 여부 ('Y' 또는 'N')
    regUser VARCHAR(10)                      -- 게시글을 등록한 사용자
);

-- COMMENT 테이블 생성
CREATE MEMORY TABLE comment (
    commentId DECIMAL(30) NOT NULL PRIMARY KEY, -- 댓글 고유 ID
    postId VARCHAR(16) NOT NULL,                -- 관련된 게시글 ID (외래키)
    authorId VARCHAR(10),                       -- 댓글 작성자 ID
    content VARCHAR(255) NOT NULL,               -- 댓글 내용
    replyComment VARCHAR(255),                  -- 대댓글 (옵션)
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 댓글 작성 시간
    updatedAt TIMESTAMP,                       -- 댓글 업데이트 시간
    useYn CHAR(1),                             -- 댓글 사용 여부 ('Y' 또는 'N')
    regUser VARCHAR(10)                        -- 댓글을 등록한 사용자
);

-- USER 테이블 생성
CREATE MEMORY TABLE user (
    userId VARCHAR(10) NOT NULL PRIMARY KEY, -- 사용자 고유 ID (Primary Key)
    password VARCHAR(255) NOT NULL,           -- 비밀번호
    nickname VARCHAR(50),                    -- 사용자 닉네임
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 계정 생성 시간
    updatedAt TIMESTAMP,                    -- 계정 업데이트 시간
    useYn CHAR(1) DEFAULT 'Y',              -- 계정 사용 여부 ('Y' 또는 'N')
    regUser VARCHAR(10)                     -- 계정을 등록한 사용자
);

-- PUBLIC 스키마 설정
SET SCHEMA PUBLIC;

-- USER 테이블에 계정 데이터 삽입
INSERT INTO user (userId, password, nickname, createdAt, updatedAt, useYn, regUser) 
VALUES 
    ('user1', 'password1', 'nickname1', CURRENT_TIMESTAMP, NULL, 'Y', 'admin'),
    ('user2', 'password2', 'nickname2', CURRENT_TIMESTAMP, NULL, 'Y', 'admin'),
    ('user3', 'password3', 'nickname3', CURRENT_TIMESTAMP, NULL, 'Y', 'admin'),
    ('user4', 'password4', 'nickname4', CURRENT_TIMESTAMP, NULL, 'Y', 'admin'),
    ('user5', 'password5', 'nickname5', CURRENT_TIMESTAMP, NULL, 'Y', 'admin');

-- POST 테이블에 데이터 삽입
INSERT INTO post (postId, authorId, title, content, createdAt, updatedAt, useYn, regUser) 
VALUES 
    ('POST-00001', 'user1', 'Runtime Environment - Foundation Layer', '이 샘플은 실행 환경을 설정하는 데 사용됩니다.', CURRENT_TIMESTAMP, NULL, 'Y', 'user1'),
    ('POST-00002', 'user2', 'Runtime Environment - Persistence Layer', 'DB 설정을 쉽게 할 수 있는 방법이 궁금해요.', CURRENT_TIMESTAMP, NULL, 'Y', 'user2'),
    ('POST-00003', 'user3', 'Runtime Environment - Presentation Layer', 'API 통합을 위한 첫 단계가 무엇인가요?', CURRENT_TIMESTAMP, NULL, 'Y', 'user3'),
    ('POST-00004', 'user4', 'Runtime Environment - Business Layer', '보안 설정을 강화하려면 어떤 조치를 취해야 하나요?', CURRENT_TIMESTAMP, NULL, 'Y', 'user4');

-- COMMENT 테이블에 댓글 데이터 삽입
INSERT INTO comment (commentId, postId, authorId, content, replyComment, createdAt, updatedAt, useYn, regUser) 
VALUES 
    (1, 'POST-00001', 'user1', '이 샘플은 실행 환경을 설정하는 데 사용됩니다.', NULL, CURRENT_TIMESTAMP, NULL, 'Y', 'user1'),
    (2, 'POST-00002', 'user2', 'DB 설정을 쉽게 할 수 있는 방법이 궁금해요.', NULL, CURRENT_TIMESTAMP, NULL, 'Y', 'user2'),
    (3, 'POST-00003', 'user3', 'API 통합을 위한 첫 단계가 무엇인가요?', NULL, CURRENT_TIMESTAMP, NULL, 'Y', 'user3'),
    (4, 'POST-00004', 'user4', '보안 설정을 강화하려면 어떤 조치를 취해야 하나요?', NULL, CURRENT_TIMESTAMP, NULL, 'Y', 'user4');

-- 추가 게시글 및 댓글 삽입
INSERT INTO post (postId, authorId, title, content, createdAt, updatedAt, useYn, regUser) 
VALUES ('POST-00005', 'user5', 'Runtime Environment - Batch Layer', '배치 환경 설정을 위한 설명입니다.', CURRENT_TIMESTAMP, NULL, 'Y', 'user5');

INSERT INTO comment (commentId, postId, authorId, content, replyComment, createdAt, updatedAt, useYn, regUser) 
VALUES (5, 'POST-00005', 'user5', '배치 환경 설정에 대해 더 자세히 설명해주세요.', NULL, CURRENT_TIMESTAMP, NULL, 'Y', 'user5');
