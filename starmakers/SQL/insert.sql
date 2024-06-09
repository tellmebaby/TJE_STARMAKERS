
-- Active: 1716511247384@@127.0.0.1@3306@joeun



TRUNCATE user;
TRUNCATE user_auth;

-- ID : joeun@naver.com
-- PW : 123456
-- 사용자
INSERT INTO user ( name, id, email, password, phone, address, gender, birth )
VALUES ( '김조은', 'joeun', 'joeun@naver.com', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '010-1234-1234', '인천시', '', '2024-01-01' );

INSERT INTO user_auth ( user_id,  auth )
VALUES ( 'joeun@naver.com', 'ROLE_USER' );


INSERT INTO star_board (title, writer, content, user_no, type)
VALUES 
('제목1', '작성자1', '내용1', 1, 'event'),
('제목2', '작성자2', '내용2', 1, 'event'),
('제목3', '작성자3', '내용3', 1, 'event'),
('제목4', '작성자4', '내용4', 1, 'event'),
('제목5', '작성자5', '내용5', 1, 'event'),
('제목6', '작성자6', '내용6', 1, 'event'),
('제목7', '작성자7', '내용7', 1, 'event'),
('제목8', '작성자8', '내용8', 1, 'event'),
('제목9', '작성자9', '내용9', 1, 'event'),
('제목10', '작성자10', '내용10', 1, 'event'),
('제목11', '작성자11', '내용11', 1, 'event');

INSERT INTO reply (writer, content, reg_date, upd_date, user_no, star_no, review_no, pre_no, parent_no)
VALUES ('joeun', 'This is a comment for star_no 10.', NOW(), NOW(), NULL, 10, NULL, NULL, NULL);

SELECT *
FROM reply



SELECT *
FROM `user`

INSERT INTO user ( name, id, email, password, phone, address, gender, birth )
VALUES ( '관리자', 'admin', 'admin@naver.com', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '010-1234-1234', '인천시', '', '2024-01-01' );

INSERT INTO user_auth ( user_id,  auth )
VALUES ( 'admin@naver.com', 'ROLE_USER' ),
       ( 'admin@naver.com', 'ROLE_ADMIN' )