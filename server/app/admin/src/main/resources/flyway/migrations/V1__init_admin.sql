DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user`
(
  id          BIGINT AUTO_INCREMENT NOT NULL COMMENT 'ID',
  uuid        CHAR(36)              NOT NULL COMMENT 'UUID',
  user_name   VARCHAR(255)          NOT NULL COMMENT '이름',
  email       VARCHAR(255)          NOT NULL COMMENT '이메일',
  user_status VARCHAR(100)          NOT NULL COMMENT '상태',
  created_at  DATETIME(6)           NOT NULL COMMENT '생성일시',
  updated_at  DATETIME(6)           NOT NULL COMMENT '수정일시',
  created_by  VARCHAR(50)           NOT NULL COMMENT '생성자',
  updated_by  VARCHAR(50)           NOT NULL COMMENT '수정자',
  PRIMARY KEY (id),
  UNIQUE INDEX idx_user_uuid (uuid),
  INDEX idx_user_name (user_name)
) ENGINE = InnoDB;
