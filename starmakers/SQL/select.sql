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




SELECT 
    sb.writer,
    sb.views,
    sb.user_no,
    sb.likes,
    sb.category2,
    f.file_no AS userImgId
FROM (
    SELECT 
        s.user_no,
        MAX(s.writer) AS writer,
        MAX(s.views) AS views,
        MAX(s.likes) AS likes,
        MAX(s.category2) AS category2,
        COALESCE(MAX(s.views), 0) + COALESCE(MAX(s.likes), 0) AS total_score  -- total_score 계산
    FROM 
        star_board s
    WHERE 
        s.card IS NOT NULL
    GROUP BY 
        s.user_no
    ORDER BY 
        total_score DESC
    LIMIT 5
) AS sb
LEFT JOIN file AS f ON sb.user_no = f.user_no AND f.star_no = 0;


-- 최근에 글올린 유저
SELECT 
    sb.star_no,
    sb.writer,
    sb.views,
    sb.user_no,
    sb.likes,
    sb.category2,
    f.file_no AS userImgId
FROM (
    SELECT 
        s.*,
        ROW_NUMBER() OVER (PARTITION BY s.user_no ORDER BY s.reg_date DESC) AS row_num
    FROM 
        star_board s
    WHERE 
        s.card IS NOT NULL  
    ORDER BY 
        s.reg_date DESC
) sb
LEFT JOIN file f ON sb.user_no = f.user_no AND f.star_no = 0
WHERE 
    sb.row_num = 1
ORDER BY 
    sb.reg_date DESC
LIMIT 5;


SELECT 
    sb.writer,
    sb.views,
    sb.user_no,
    sb.likes,
    sb.category2,
    sb.star_no,  -- 가장 높은 조회수 게시물의 star_no
    f.file_no AS userImgId
FROM (
    SELECT 
        s.user_no,
        s.writer,
        s.views,
        s.likes,
        s.category2,
        s.star_no,
        COALESCE(s.views, 0) + COALESCE(s.likes, 0) AS total_score
    FROM 
        star_board s
    WHERE 
        s.card IS NOT NULL
        AND s.views = (
            SELECT MAX(inner_s.views)
            FROM star_board inner_s
            WHERE inner_s.user_no = s.user_no
        )
    ORDER BY 
        total_score DESC
    LIMIT 5
) AS sb
LEFT JOIN file AS f ON sb.user_no = f.user_no AND f.star_no = 0;
