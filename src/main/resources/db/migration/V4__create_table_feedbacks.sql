CREATE TABLE feedbacks (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nome_usuario VARCHAR(255) NOT NULL,
                          feedback TEXT,
                          nota INT
);
