-- 2022-06-29 DML/DDL ���� 
-- �Խ��� (CURD) => DAO,VO
/*
CREATE TABLE freeboard(
     no NUMBER,
     name VARCHAR2(34) CONSTRAINT fb_name_nn NOT NULL,
     subject VARCHAR2(2000) CONSTRAINT fb_subject_nn NOT NULL,
     content CLOB CONSTRAINT fb_content_nn NOT NULL,
     pwd VARCHAR2(10) CONSTRAINT fb_pwd_nn NOT NULL,
     regdate DATE DEFAULT SYSDATE,
     hit NUMBER DEFAULT 0,
     CONSTRAINT fb_no_pk PRIMARY KEY(no)
);
CREATE TABLE boardMember(
     id VARCHAR2(20),
     pwd VARCHAR2(10) CONSTRAINT bm_pwd_nn NOT NULL,
     name VARCHAR2(34) CONSTRAINT bm_name_nn NOT NULL,
     CONSTRAINT bm_name_pk PRIMARY KEY(id)
); 
*/
INSERT INTO boardMember VALUES('hong','1234','ȫ�浿');
INSERT INTO boardMember VALUES('shim','1234','��û��');
INSERT INTO boardMember VALUES('lee','1234','�̼���');
INSERT INTO boardMember VALUES('park','1234','�ڹ���');
INSERT INTO boardMember VALUES('kang','1234','������');
COMMIT;
