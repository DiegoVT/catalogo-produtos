CREATE TABLE IF NOT EXISTS categoria (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(120) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_categoria_nome (nome)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS marca (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(120) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_marca_nome (nome)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS produto (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(200) NOT NULL,
  descricao TEXT NULL,

  categoria_id BIGINT NOT NULL,
  marca_id BIGINT NOT NULL,

  preco DECIMAL(12,2) NOT NULL,
  estoque INT NOT NULL DEFAULT 0,
  visualizacoes BIGINT NOT NULL DEFAULT 0,

  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (id),

  CONSTRAINT fk_produto_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id),
  CONSTRAINT fk_produto_marca FOREIGN KEY (marca_id) REFERENCES marca(id),

  INDEX idx_produto_categoria (categoria_id),
  INDEX idx_produto_marca (marca_id),
  INDEX idx_produto_preco (preco)
) ENGINE=InnoDB;

-- Opcional: só crie FULLTEXT se você realmente for usar (e se seu MySQL suportar bem)
-- ALTER TABLE produto ADD FULLTEXT KEY ft_produto_nome_descricao (nome, descricao);

