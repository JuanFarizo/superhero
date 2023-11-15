CREATE TABLE IF NOT EXISTS superhero
(
     id                     BIGINT          NOT NULL auto_increment,
     created_at             DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
     name                   VARCHAR(255)    NOT NULL,
     updated_at             DATETIME,
     version                INTEGER         NOT NULL DEFAULT 0,
     PRIMARY KEY (id)
);

ALTER TABLE superhero
  ADD CONSTRAINT name_uk UNIQUE (name);