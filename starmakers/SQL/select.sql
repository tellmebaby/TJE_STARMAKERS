-- Active: 1716511247384@@127.0.0.1@3306@joeun
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

delete  
FROM file
WHERE user_no = 6 AND star_no = 0

SELECT *
FROM file

SELECT 
    pi.pay_no,
    pi.code,
    pi.price,
    pi.user_no,
    pi.product_title,
    pi.reg_date, 
    sb.status  
FROM 
    payment_info pi
JOIN 
    star_board sb
ON 
    pi.user_no = sb.user_no AND
    pi.status = sb.status
WHERE
    pi.user_no = 6

INSERT INTO payment_info (code, price, user_no, product_title, status) VALUES
(101, '54000', 6, '인스타 홍보합니다', '검토중'),
(102, '54000', 6, '유튜브 홍보합니다', '검토중'),
(103, '54000', 6, '블로그 홍보합니다', '검토중');

SELECT *
FROM payment_info