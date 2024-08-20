CREATE TABLE fx_rate (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
--  `date` DATE NOT NULL,
  date DATE NOT NULL,
  source_currency VARCHAR(253) NOT NULL,
  target_currency VARCHAR(253) NOT NULL,
  exchange_rate DECIMAL(10, 5) NOT NULL,
  UNIQUE KEY unique_rate (date, source_currency, target_currency)
);