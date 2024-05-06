CREATE TBALE IF NOT EXISTS marcas (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	nome VARCHAR(45) NOT NULL,
	modelo VARCHAR(45) NOT NULL,
	capacidade INT(4) UNSIGNED NOT NULL,
	valor INT(7,2) UNSIGNED NOT NULL,
	marcas_id INT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	INDEX fk_produtos_marcas_idx (marcas_id ASC)
		FOREIGN KEY (marcas_id)
		REFERENCES mascas (id)
)