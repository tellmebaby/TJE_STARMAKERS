-- Active: 1716511247384@@127.0.0.1@3306@joeun
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