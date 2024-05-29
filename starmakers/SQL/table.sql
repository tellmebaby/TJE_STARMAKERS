
-- Active: 1716894496133@@127.0.0.1@3306@joeun



DROP TABLE IF EXISTS action ;
CREATE TABLE action
(
  like_no  INT       NOT NULL AUTO_INCREMENT COMMENT '좋아요데이터번호',
  reg_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
  user_no  INT       NOT NULL COMMENT '유저데이터번호',
  star_no  INT       NOT NULL COMMENT '이벤트데이터번호',
  PRIMARY KEY (like_no)
) COMMENT '좋아요정보';

DROP TABLE IF EXISTS file ;

CREATE TABLE file
(
  file_no   INT         NOT NULL AUTO_INCREMENT COMMENT '파일데이터번호',
  file_name VARCHAR(255) NOT NULL COMMENT '파일이름', -- 파일이름의 길이를 늘렸습니다.
  size      BIGINT      NOT NULL COMMENT '파일용량', -- 파일 크기를 정수로 저장합니다.
  ext_name  VARCHAR(10) NOT NULL COMMENT '확장자이름', -- 컬럼명을 last_name에서 ext_name으로 변경했습니다.
  reg_date  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
  user_no   INT         NULL     COMMENT '유저데이터번호',
  star_no   INT         NULL     COMMENT '이벤트데이터번호',
  PRIMARY KEY (file_no)
) COMMENT '업로드파일';

DROP TABLE IF EXISTS message ;
CREATE TABLE message
(
  message_no INT         NOT NULL AUTO_INCREMENT COMMENT '알림데이터번호',
  content    TEXT        NOT NULL COMMENT '내용',
  code       VARCHAR(30) NOT NULL COMMENT '알림종류',
  reg_date   TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
  reply_no   INT         NOT NULL COMMENT '댓글데이터번호',
  pay_no     INT         NOT NULL COMMENT '결제데이터번호',
  qna_no     INT         NOT NULL COMMENT '질의응답데이터번호',
  user_no    INT         NOT NULL COMMENT '유저데이터번호',
  PRIMARY KEY (message_no)
) COMMENT '알림메세지';



DROP TABLE IF EXISTS payment_info ;
CREATE TABLE payment_info
(
  pay_no     INT         NOT NULL AUTO_INCREMENT COMMENT '결제데이터번호',
  code       INT         NOT NULL COMMENT '상품코드',
  price      VARCHAR(30) NOT NULL COMMENT '결제가격',
  user_no    INT         NOT NULL COMMENT '유저데이터번호',
  product_no INT         NOT NULL COMMENT '상품데이터번호',
  PRIMARY KEY (pay_no)
) COMMENT '결제정보';

DROP TABLE IF EXISTS persistent_logins;
CREATE TABLE persistent_logins
(
  series    VARCHAR(64)  NOT NULL COMMENT '시리즈', -- serise를 series로 수정하고 길이를 64로 설정했습니다.
  token     VARCHAR(64)  NOT NULL COMMENT '토큰',  -- 적절한 길이를 설정했습니다.
  last_used TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '마지막 사용 시간',
  username  VARCHAR(64)  NOT NULL COMMENT '사용자 이름', -- 적절한 길이를 설정했습니다.
  PRIMARY KEY (series)
) COMMENT '자동로그인';

DROP TABLE IF EXISTS product_io ;
DROP TABLE IF EXISTS product ;
CREATE TABLE product
(
  product_no INT         NOT NULL AUTO_INCREMENT COMMENT '상품데이터번호',
  name       VARCHAR(30) NOT NULL COMMENT '상품이름',
  price      VARCHAR(30) NOT NULL COMMENT '상품가격',
  code       INT         NOT NULL COMMENT '상품종류코드',
  str_date   TIMESTAMP   NOT NULL COMMENT '시작일자',
  end_date   TIMESTAMP   NOT NULL COMMENT '종료일자',
  reg_date   TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
  upd_date   TIMESTAMP   NULL     COMMENT '수정일자',
  PRIMARY KEY (product_no)
) COMMENT '상품데이터';


DROP TABLE IF EXISTS qna_board ;
CREATE TABLE qna_board
(
  qna_no   INT         NOT NULL AUTO_INCREMENT COMMENT '질의응답데이터번호',
  title    VARCHAR(30) NOT NULL COMMENT '제목',
  writer   VARCHAR(20) NOT NULL COMMENT '작성자',
  content  TEXT        NOT NULL COMMENT '내용',
  answer   TEXT        NULL     COMMENT '답변',
  reg_date TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
  upd_date TIMESTAMP   NULL     COMMENT '수정일자',
  views    INT         NULL     COMMENT '조회수',
  user_no  INT         NOT NULL COMMENT '유저데이터번호',
  status    VARCHAR(20) NULL     COMMENT '상태', -- 상태의 길이 지정.
  
  PRIMARY KEY (qna_no)
) COMMENT '질의응답게시판';

DROP TABLE IF EXISTS reply;
CREATE TABLE reply
(
  reply_no  INT       NOT NULL AUTO_INCREMENT COMMENT '댓글데이터번호',
  writer    VARCHAR(50) NOT NULL COMMENT '작성자', -- 적절한 길이를 설정했습니다.
  content   TEXT      NOT NULL COMMENT '내용',
  reg_date  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
  upd_date  TIMESTAMP NULL     COMMENT '수정일자',
  user_no   INT       NULL     COMMENT '유저데이터번호',
  star_no   INT       NULL     COMMENT '이벤트데이터번호',
  review_no INT       NULL     COMMENT '후기데이터번호',
  pre_no    INT       NULL     COMMENT '공지사항데이터번호',
  parent_no INT       NULL     COMMENT '부모댓글번호',
  PRIMARY KEY (reply_no)
) COMMENT '댓글';



DROP TABLE IF EXISTS star_board ;
CREATE TABLE star_board
(
  star_no   INT         NOT NULL AUTO_INCREMENT COMMENT '이벤트데이터번호',
  title     VARCHAR(100) NOT NULL COMMENT '제목', -- 제목의 길이를 더 늘렸습니다.
  writer    VARCHAR(50)  NOT NULL COMMENT '작성자', -- 작성자 길이 조정.
  content   TEXT        NOT NULL COMMENT '내용',
  reg_date  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자',
  upd_date  TIMESTAMP   NULL     COMMENT '수정일자',
  views     INT         NULL     COMMENT '조회수',
  user_no   INT         NOT NULL COMMENT '유저데이터번호',
  pay_no    INT         NULL     COMMENT '결제데이터번호',
  likes     INT         NULL     COMMENT '좋아요수',
  status    VARCHAR(20) NULL     COMMENT '상태', -- 상태의 길이 지정.
  category1 VARCHAR(30) NULL     COMMENT '종류1', -- 카테고리1 길이 지정.
  category2 VARCHAR(30) NULL     COMMENT '종류2', -- 카테고리2 길이 지정.
  type      VARCHAR(20) NULL     COMMENT '타입', -- 타입의 길이 지정.
  start_date TIMESTAMP NULL COMMENT '홍보시작일',
  end_date TIMESTAMP NULL COMMENT '홍보종료일',
  PRIMARY KEY (star_no)
) COMMENT '통합게시판';

SELECT *
FROM star_board

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
  user_no  INT         NOT NULL AUTO_INCREMENT COMMENT '유저데이터번호',
  name     VARCHAR(20) NOT NULL COMMENT '이름',
  id       VARCHAR(30) NOT NULL COMMENT '아이디',
  email    VARCHAR(50) NOT NULL COMMENT '이메일',
  password VARCHAR(100) NOT NULL COMMENT '비밀번호', -- 비밀번호 길이 지정.
  phone    VARCHAR(15) NOT NULL COMMENT '전화번호', -- 전화번호 길이 지정.
  address  VARCHAR(100) NULL     COMMENT '주소', -- 주소 길이 지정.
  reg_date TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  upd_date TIMESTAMP   NULL     COMMENT '수정일자',
  gender   VARCHAR(10) NOT NULL COMMENT '성별',
  birth    VARCHAR(20) NOT NULL COMMENT '생년월일',
  PRIMARY KEY (user_no)
) COMMENT '회원정보';

INSERT INTO user (name, id, email, password, phone, address, gender, birth)
VALUES ('홍길동', 'user', 'user@example.com', '123456', '010-1234-5678', '서울시 강남구', '남성', '1990-01-01');

INSERT INTO user (name, id, email, password, phone, address, gender, birth)
VALUES ('관리자', 'admin', 'admin@example.com', '123456', '010-1234-5678', '본사', '남성', '1997-01-01');


DROP TABLE IF EXISTS user_auth;
CREATE TABLE user_auth
(
  auth_no INT          NOT NULL AUTO_INCREMENT,
  user_id VARCHAR(100) NOT NULL,
  auth    VARCHAR(100) NOT NULL,
  PRIMARY KEY (auth_no)
);

-- ALTER TABLE star_board
--   ADD CONSTRAINT FK_user_TO_star_board
--     FOREIGN KEY (user_no)
--     REFERENCES user (user_no);

-- ALTER TABLE file
--   ADD CONSTRAINT FK_user_TO_file
--     FOREIGN KEY (user_no)
--     REFERENCES user (user_no);

-- ALTER TABLE file
--   ADD CONSTRAINT FK_star_board_TO_file
--     FOREIGN KEY (star_no)
--     REFERENCES star_board (star_no);

-- ALTER TABLE qna_board
--   ADD CONSTRAINT FK_user_TO_qna_board
--     FOREIGN KEY (user_no)
--     REFERENCES user (user_no);

-- ALTER TABLE payment_info
--   ADD CONSTRAINT FK_user_TO_payment_info
--     FOREIGN KEY (user_no)
--     REFERENCES user (user_no);

-- ALTER TABLE payment_info
--   ADD CONSTRAINT FK_product_TO_payment_info
--     FOREIGN KEY (product_no)
--     REFERENCES product (product_no);

-- ALTER TABLE reply
--   ADD CONSTRAINT FK_user_TO_reply
--     FOREIGN KEY (user_no)
--     REFERENCES user (user_no);

-- ALTER TABLE reply
--   ADD CONSTRAINT FK_star_board_TO_reply
--     FOREIGN KEY (star_no)
--     REFERENCES star_board (star_no);

-- ALTER TABLE star_board
--   ADD CONSTRAINT FK_payment_info_TO_star_board
--     FOREIGN KEY (pay_no)
--     REFERENCES payment_info (pay_no);

-- ALTER TABLE message
--   ADD CONSTRAINT FK_reply_TO_message
--     FOREIGN KEY (reply_no)
--     REFERENCES reply (reply_no);

-- ALTER TABLE message
--   ADD CONSTRAINT FK_payment_info_TO_message
--     FOREIGN KEY (pay_no)
--     REFERENCES payment_info (pay_no);

-- ALTER TABLE message
--   ADD CONSTRAINT FK_qna_board_TO_message
--     FOREIGN KEY (qna_no)
--     REFERENCES qna_board (qna_no);

-- ALTER TABLE action
--   ADD CONSTRAINT FK_user_TO_action
--     FOREIGN KEY (user_no)
--     REFERENCES user (user_no);

-- ALTER TABLE action
--   ADD CONSTRAINT FK_star_board_TO_action
--     FOREIGN KEY (star_no)
--     REFERENCES star_board (star_no);
