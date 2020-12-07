/*ALTER TABLE  USER_TRANSACTION DROP CONSTRAINT IF EXISTS USER_TRANSACTION_CONSTR ;*/
CREATE TABLE IF NOT EXISTS USER  (
  id      INTEGER      NOT NULL,
  name    VARCHAR(128) NOT NULL,
  address VARCHAR(128) NOT NULL,
  version INTEGER NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS USER_TRANSACTION (
  id            INTEGER                                     NOT NULL,

  name          VARCHAR(128)                                NOT NULL,

  type          ENUM ('GROSSERY', 'ACCOUNTING', 'TRANSFER') NOT NULL,
  amount        DECIMAL(20, 2)                              NOT NULL,
  executionDate TIMESTAMP                                   NOT NULL,

  version       INTEGER                                     NOT NULL,
  user_id       INTEGER                                     NOT NULL,
  PRIMARY KEY (id)
);
ALTER TABLE USER_TRANSACTION ADD CONSTRAINT IF NOT EXISTS USER_TRANSACTION_CONSTR FOREIGN KEY (user_id) REFERENCES USER (id) ON DELETE CASCADE ;
CREATE SEQUENCE IF NOT EXISTS user_sequence START WITH 1;
CREATE SEQUENCE IF NOT EXISTS user_trans_sequence START WITH 1;
CREATE INDEX IF NOT EXISTS IDX_USER_ADDRESS on USER(address);
