-- Active: 1714701530602@@127.0.0.1@3306@joeun
SELECT COUNT(*)
FROM user
WHERE name = "김조은" AND email = "joeun@naver.com";

SELECT COUNT(*)
FROM user
WHERE id = "joeun";

SELECT * 
FROM user;

SELECT *
FROM star_board

SELECT COUNT(*)
FROM star_board 
WHERE type = "event"   
ORDER BY reg_date DESC 

SELECT COUNT(*)
FROM star_board 
WHERE type = "event";
and title LIKE CONCAT '%%'
OR content LIKE CONCAT '%%';

SELECT *
FROM file;


SELECT * FROM file
    WHERE user_no = 1 AND star_no = 0
    ORDER BY reg_date DESC
    LIMIT 1;

SELECT * FROM file 
    WHERE user_no = 1 AND star_no = 0 ;

  SELECT COUNT(*)
        FROM star_board 
        WHERE type = "event";
        and title LIKE CONCAT '%%'
                OR content LIKE CONCAT '%%';

SELECT *
FROM user

SELECT *
FROM file

SELECT *
        FROM file
        WHERE user_no = 3
        AND star_no = 0


-- 새 메인 스타카드 불러오기
SELECT 
    s.*,
    f.file_no AS imgNo,
    (
        SELECT file_no 
        FROM file f2 
        WHERE f2.user_no = s.user_no AND f2.star_no = 0 
        ORDER BY f2.reg_date DESC 
        LIMIT 1
    ) AS userImgId
FROM 
    star_board s
LEFT JOIN 
    file f ON f.star_no = s.star_no
WHERE 
    s.type = 'starCard'
ORDER BY 
    s.reg_date DESC;

