-- Active: 1714701530602@@127.0.0.1@3306@joeun

CREATE TABLE action
(
  like_no  INT       NOT NULL DEFAULT AUTO_INCREMENTE COMMENT '좋아요데이터번호',
  reg_date TIMESTAMP NOT NULL DEFAULT SYSDATE() COMMENT '생성일자',
  user_no  INT       NOT NULL COMMENT '유저데이터번호',
  star_no  INT       NOT NULL COMMENT '이벤트데이터번호',
  PRIMARY KEY (like_no)
) COMMENT '좋아요정보';

CREATE TABLE file
(
  file_no   INT         NOT NULL DEFAULT AUTO_INCREMENTE COMMENT '파일데이터번호',
  file_name VARCHAR(50) NOT NULL COMMENT '파일이름',
  size      VARCHAR(50) NOT NULL COMMENT '파일용량',
  last_name VARCHAR(10) NOT NULL COMMENT '확장자이름',
  reg_date  TIMESTAMP   NOT NULL DEFAULT SYSDATE() COMMENT '생성일자',
  user_no   INT         NULL     COMMENT '유저데이터번호',
  star_no   INT         NULL     COMMENT '이벤트데이터번호',
  PRIMARY KEY (file_no)
) COMMENT '업로드파일';

CREATE TABLE message
(
  message_no INT         NOT NULL DEFAULT AUTO_INCREMENTE COMMENT '알림데이터번호',
  content    TEXT        NOT NULL COMMENT '내용',
  code       VARCHAR(30) NOT NULL COMMENT '알림종류',
  reg_date   TIMESTAMP   NOT NULL DEFAULT SYSDATE() COMMENT '생성일자',
  reply_no   INT         NOT NULL COMMENT '댓글데이터번호',
  pay_no     INT         NOT NULL COMMENT '결제데이터번호',
  qna_no     INT         NOT NULL COMMENT '질의응답데이터번호',
  PRIMARY KEY (message_no)
) COMMENT '알림메세지';

CREATE TABLE payment_info
(
  pay_no     INT         NOT NULL DEFAULT AUTO_INCREMENTE COMMENT '결제데이터번호',
  code       INT         NOT NULL COMMENT '상품코드',
  price      VARCHAR(30) NOT NULL COMMENT '결제가격',
  user_no    INT         NOT NULL COMMENT '유저데이터번호',
  product_no INT         NOT NULL COMMENT '상품데이터번호',
  PRIMARY KEY (pay_no)
) COMMENT '결제정보';

CREATE TABLE persistent_logins
(
  serise    VARCHAR   NOT NULL,
  token     VARCHAR   NOT NULL,
  last_used TIMESTAMP NOT NULL DEFAULT SYSDATE(),
  username  VARCHAR   NOT NULL,
  PRIMARY KEY (serise)
) COMMENT '자동로그인';

CREATE TABLE product
(
  product_no INT         NOT NULL DEFAULT AUTO_INCREMENTE COMMENT '상품데이터번호',
  name       VARCHAR(30) NOT NULL COMMENT '상품이름',
  price      VARCHAR(30) NOT NULL COMMENT '상품가격',
  code       INT         NOT NULL COMMENT '상품종류코드',
  str_date   TIMESTAMP   NOT NULL COMMENT '시작일자',
  end_date   TIMESTAMP   NOT NULL COMMENT '종료일자',
  reg_date   TIMESTAMP   NOT NULL DEFAULT SYSDATE() COMMENT '생성일자',
  upd_date   TIMESTAMP   NULL     COMMENT '수정일자',
  PRIMARY KEY (product_no)
) COMMENT '상품데이터';

CREATE TABLE qna_board
(
  qna_no   INT         NOT NULL DEFAULT AUTO_INCREMENTE COMMENT '질의응답데이터번호',
  title    VARCHAR(30) NOT NULL COMMENT '제목',
  writer   VARCHAR(20) NOT NULL COMMENT '작성자',
  content  TEXT        NOT NULL COMMENT '내용',
  answer   TEXT        NULL     COMMENT '답변',
  reg_date TIMESTAMP   NOT NULL DEFAULT SYSDATE() COMMENT '생성일자',
  upd_date TIMESTAMP   NULL     COMMENT '수정일자',
  views    INT         NULL     COMMENT '조회수',
  user_no  INT         NOT NULL COMMENT '유저데이터번호',
  PRIMARY KEY (qna_no)
) COMMENT '질의응답게시판';

CREATE TABLE reply
(
  reply_no  INT       NOT NULL DEFAULT AUTO_INCREMENTE COMMENT '댓글데이터번호',
  writer    VARCHAR   NOT NULL COMMENT '작성자',
  content   TEXT      NOT NULL COMMENT '내용',
  reg_date  TIMESTAMP NOT NULL DEFAULT SYSDATE() COMMENT '생성일자',
  upd_date  TIMESTAMP NULL     COMMENT '수정일자',
  user_no   INT       NULL     COMMENT '유저데이터번호',
  star_no   INT       NULL     COMMENT '이벤트데이터번호',
  review_no int       NULL     COMMENT '후기데이터번호',
  pre_no    INT       NULL     COMMENT '공지사항데이터번호',
  parent_no INT       NULL     COMMENT '부모댓글번호',
  PRIMARY KEY (reply_no)
) COMMENT '댓글';

CREATE TABLE star_board
(
  star_no   INT         NOT NULL DEFAULT AUTO_INCREMENT COMMENT '이벤트데이터번호',
  title     VARCHAR(30) NOT NULL COMMENT '제목',
  writer    VARCHAR(20) NOT NULL COMMENT '작성자',
  content   TEXT        NOT NULL COMMENT '내용',
  reg_date  TIMESTAMP   NOT NULL DEFAULT SYSDATE() COMMENT '생성일자',
  upd_date  TIMESTAMP   NULL     COMMENT '수정일자',
  views     INT         NULL     COMMENT '조회수',
  user_no   INT         NOT NULL COMMENT '유저데이터번호',
  pay_no    INT         NOT NULL COMMENT '결제데이터번호',
  likes     INT         NULL     COMMENT '좋아요수',
  status    VARCHAR     NULL     COMMENT '상태',
  category1 VARCHAR     NULL     COMMENT '종류1',
  category2 VARCHAR     NULL     COMMENT '종류2',
  type      VARCHAR     NULL     COMMENT '타입',
  PRIMARY KEY (star_no)
) COMMENT '통합게시판';

CREATE TABLE user
(
  user_no  INT         NOT NULL DEFAULT AUTO_INCREMENT COMMENT '유저데이터번호',
  name     VARCHAR(20) NOT NULL COMMENT '이름',
  id       VARCHAR(30) NOT NULL COMMENT '아이디',
  email    VARCHAR(50) NOT NULL COMMENT '이메일',
  password VARCHAR     NOT NULL COMMENT '비밀번호',
  phone    VARCHAR     NOT NULL COMMENT '전화번호',
  address  VARCHAR     NULL     COMMENT '주소',
  reg_date TIMESTAMP   NOT NULL DEFAULT SYSDATE() COMMENT '등록일자',
  upd_date TIMESTAMP   NULL     COMMENT '수정일자',
  gender   VARCHAR(10) NOT NULL COMMENT '성별',
  birth    VARCHAR(20) NOT NULL COMMENT '생년월일',
  PRIMARY KEY (user_no)
) COMMENT '회원정보';

CREATE TABLE user_auth
(
  auth_no INT          NOT NULL DEFAULT AUTO_INCREMENT,
  user_id VARCHAR(100) NOT NULL,
  auth    VARCHAR(100) NOT NULL,
  PRIMARY KEY (auth_no)
);

ALTER TABLE star_board
  ADD CONSTRAINT FK_user_TO_star_board
    FOREIGN KEY (user_no)
    REFERENCES user (user_no);

ALTER TABLE file
  ADD CONSTRAINT FK_user_TO_file
    FOREIGN KEY (user_no)
    REFERENCES user (user_no);

ALTER TABLE file
  ADD CONSTRAINT FK_star_board_TO_file
    FOREIGN KEY (star_no)
    REFERENCES star_board (star_no);

ALTER TABLE qna_board
  ADD CONSTRAINT FK_user_TO_qna_board
    FOREIGN KEY (user_no)
    REFERENCES user (user_no);

ALTER TABLE payment_info
  ADD CONSTRAINT FK_user_TO_payment_info
    FOREIGN KEY (user_no)
    REFERENCES user (user_no);

ALTER TABLE payment_info
  ADD CONSTRAINT FK_product_TO_payment_info
    FOREIGN KEY (product_no)
    REFERENCES product (product_no);

ALTER TABLE reply
  ADD CONSTRAINT FK_user_TO_reply
    FOREIGN KEY (user_no)
    REFERENCES user (user_no);

ALTER TABLE reply
  ADD CONSTRAINT FK_star_board_TO_reply
    FOREIGN KEY (star_no)
    REFERENCES star_board (star_no);

ALTER TABLE star_board
  ADD CONSTRAINT FK_payment_info_TO_star_board
    FOREIGN KEY (pay_no)
    REFERENCES payment_info (pay_no);

ALTER TABLE message
  ADD CONSTRAINT FK_reply_TO_message
    FOREIGN KEY (reply_no)
    REFERENCES reply (reply_no);

ALTER TABLE message
  ADD CONSTRAINT FK_payment_info_TO_message
    FOREIGN KEY (pay_no)
    REFERENCES payment_info (pay_no);

ALTER TABLE message
  ADD CONSTRAINT FK_qna_board_TO_message
    FOREIGN KEY (qna_no)
    REFERENCES qna_board (qna_no);

ALTER TABLE action
  ADD CONSTRAINT FK_user_TO_action
    FOREIGN KEY (user_no)
    REFERENCES user (user_no);

ALTER TABLE action
  ADD CONSTRAINT FK_star_board_TO_action
    FOREIGN KEY (star_no)
    REFERENCES star_board (star_no);
