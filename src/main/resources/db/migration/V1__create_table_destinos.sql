CREATE TABLE destinos (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          destino_img_url VARCHAR(255),
                          destino_img_url2 VARCHAR(255),
                          meta_descricao VARCHAR(160) NOT NULL,
                          descricao_completa TEXT,
                          preco DECIMAL(10, 2) NOT NULL
);
