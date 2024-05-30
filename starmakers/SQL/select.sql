-- Active: 1714701530602@@127.0.0.1@3306@joeun
SELECT COUNT(*)
FROM user
WHERE name = "김조은" AND email = "joeun@naver.com";

SELECT COUNT(*)
FROM user
WHERE id = "joeun";

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