CREATE TABLE products (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(60) NOT NULL,
  price DECIMAL(8,2) NOT NULL,
  balance INT NOT NULL,
  category BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_category
    FOREIGN KEY (category)
    REFERENCES categories (id)
)ENGINE = InnoDB;