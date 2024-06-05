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

INSERT INTO payment_info (code, price, user_no, star_no, product_title, status) VALUES
(102, '54000', 1, 9, '유튜브 홍보합니다', '환불대기')

TRUNCATE payment_info

SELECT *
FROM payment_info

select * 
FROM star_board

SELECT pi.*
      ,sb.status as star_status
        FROM 
            payment_info pi
        JOIN 
            star_board sb
        ON 
            pi.user_no = sb.user_no
        WHERE pi.user_no = 1
        AND pi.star_no = sb.star_no


SELECT sb.title, sb.category1, sb.reg_date, sb.status
      ,pi.price
        FROM 
            star_board sb
        JOIN 
            payment_info pi
        ON 
            pi.user_no = sb.user_no
        WHERE pi.user_no = 1
        AND pi.star_no = sb.star_no
;
SELECT 
    sb.user_no,
    sb.title, 
    sb.category1, 
    sb.reg_date, 
    sb.status, 
    pi.price as star_price
FROM 
    star_board sb
JOIN 
    payment_info pi
ON 
    pi.user_no = sb.user_no
AND 
    pi.star_no = sb.star_no
WHERE 
    sb.user_no = 1

    INSERT INTO payment_info (product_title, code, price, user_no, star_no, reg_date, status) VALUES
('홍보카드 기간제 상품', 'MC4xMDkwODM2MTkzMDk', 10000, 1, 16, '2024-06-04 20:29:55', '결제완료'),
('홍보카드 기간제 상품', 'MC44NTkwMTI4NDM0ODc', 9000, 1, 20, '2024-06-04 21:55:39', '결제완료'),
('홍보카드 기간제 상품', 'MC4yNjYyODIxNjc2MTMw', 11000, 1, 21, '2024-06-05 09:40:20', '결제완료'),
('홍보카드 기간제 상품', 'MC43ODY1Nzg3NTg3MTc', 7000, 1, 23, '2024-06-05 10:01:32', '결제완료'),
('홍보카드 기간제 상품', 'MC4zMDYyOTIyMjMzMzcy', 11000, 1, 29, '2024-06-05 10:39:31', '결제완료');

 SELECT SUM(price) as price
    FROM payment_info
    WHERE user_no = 1