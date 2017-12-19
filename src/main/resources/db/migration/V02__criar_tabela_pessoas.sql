CREATE TABLE pessoa (
	codigo        BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome          VARCHAR(50) NOT NULL,
	ativo         BOOL NOT NULL,
	logradouro    VARCHAR(100),
	numero        VARCHAR(10),
	complemento   VARCHAR(10),
	bairro        VARCHAR(30),
	cep           VARCHAR(10),
	cidade        VARCHAR(50),  
	estado        VARCHAR(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) VALUES ('Rafael', TRUE, 'Rua Antonio Zeferino de Paula','723','','Sta Monica','38408270','Uberlandia','MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) VALUES ('Juliano',  TRUE,  'Rua Antonio Zeferino de Paula','723','','Sta Monica','38408270','Uberlandia','MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) VALUES ('Fabiana', TRUE, 'Rua Antonio Zeferino de Paula','723','','Sta Monica','38408270','Uberlandia','MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) VALUES ('Mauro', TRUE, 'Rua Antonio Zeferino de Paula','723','','Sta Monica','38408270','Uberlandia','MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) VALUES ('Hugo', TRUE, 'Rua Antonio Zeferino de Paula','723','','Sta Monica','38408270','Uberlandia','MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) VALUES ('Teste', TRUE, 'Rua Antonio Zeferino de Paula','723','','Sta Monica','38408270','Uberlandia','MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) VALUES ('Camila', TRUE, 'Rua Antonio Zeferino de Paula','723','','Sta Monica','38408270','Uberlandia','MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) VALUES ('Moises', TRUE, 'Rua Antonio Zeferino de Paula','723','','Sta Monica','38408270','Uberlandia','MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) VALUES ('Thiago', TRUE, 'Rua Antonio Zeferino de Paula','723','','Sta Monica','38408270','Uberlandia','MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) VALUES ('Daiton', TRUE, 'Rua Antonio Zeferino de Paula','723','','Sta Monica','38408270','Uberlandia','MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) VALUES ('Wilker', TRUE, 'Rua Antonio Zeferino de Paula','723','','Sta Monica','38408270','Uberlandia','MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) VALUES ('Junior', TRUE, 'Rua Antonio Zeferino de Paula','723','','Sta Monica','38408270','Uberlandia','MG');



