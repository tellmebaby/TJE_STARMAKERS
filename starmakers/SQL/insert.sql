
-- Active: 1716759486779@@127.0.0.1@3306@joeun



TRUNCATE user;
TRUNCATE user_auth;

-- ID : joeun@naver.com
-- PW : 123456
-- 사용자
INSERT INTO user ( name, id, email, password, phone, address, gender, birth )
VALUES ( '김조은', 'joeun', 'joeun@naver.com', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '010-1234-1234', '인천시', '', '2024-01-01' );

INSERT INTO user_auth ( user_id,  auth )
VALUES ( 'joeun@naver.com', 'ROLE_USER' );

