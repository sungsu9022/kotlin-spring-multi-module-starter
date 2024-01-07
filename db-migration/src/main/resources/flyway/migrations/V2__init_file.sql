CREATE TABLE IF NOT EXISTS `file`
(
  id	BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ID',
  file_uuid CHAR(36) NOT NULL COMMENT 'UUID',
  file_path VARCHAR(255) NOT NULL COMMENT '파일 경로(s3 key 등)',
  file_name VARCHAR(255) NOT NULL COMMENT '파일 이름',
  file_size INT NOT NULL COMMENT 'file size(byte)',
  file_extension_enum VARCHAR(100) NOT NULL COMMENT '파일 포맷',
  file_type_enum VARCHAR(100) NOT NULL COMMENT '파일 타입',
  is_deleted TINYINT(1) NOT NULL DEFAULT FALSE COMMENT '삭제 여부',
  created_at   datetime(6)  null COMMENT '생성 일시',
  updated_at   datetime(6)  null COMMENT '수정 일시',
  created_by   VARCHAR(100)  null COMMENT '생성자',
  updated_by   VARCHAR(100)  null COMMENT '수정자',
  PRIMARY KEY (id)
  ) engine = InnoDB;
