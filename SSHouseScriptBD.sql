DROP SCHEMA IF EXISTS SSHouse;
CREATE DATABASE SSHouse;
USE SSHouse;

DROP TABLE IF EXISTS Morador;
DROP TABLE IF EXISTS Ambiente;
DROP TABLE IF EXISTS Atuador;
DROP TABLE IF EXISTS Sensor;
DROP TABLE IF EXISTS Operacao;
DROP TABLE IF EXISTS Alarme;
DROP TABLE IF EXISTS Evento;

CREATE TABLE Morador (
login VARCHAR(10) NOT NULL,
nome VARCHAR(80) NOT NULL,
dataNascimento DATE NOT NULL,
celular VARCHAR(13),
email VARCHAR (100),
senha VARCHAR(500) NOT NULL,
PRIMARY KEY (login));

CREATE TABLE Ambiente(
codigoAmbiente VARCHAR(4) NOT NULL,
descricaoAmbiente VARCHAR(100) NOT NULL,
PRIMARY KEY (codigoAmbiente));

CREATE TABLE Alarme(
codigoAlarme VARCHAR(3) NOT NULL,
descricaoAlarme VARCHAR(100) NOT NULL,
PRIMARY KEY (codigoAlarme));

CREATE TABLE Sensor(
codigoSensor VARCHAR(3) NOT NULL,
descricaoSensor VARCHAR(80) NOT NULL,
pinoArduino VARCHAR(3) NOT NULL,
limiteInfAlarme INT,
limiteSupAlarme INT,
codigoAmbiente VARCHAR(4) NOT NULL,
codigoAlarme VARCHAR(3) NOT NULL,
PRIMARY KEY (codigoSensor),
FOREIGN KEY (codigoAlarme) REFERENCES Alarme(codigoAlarme) 
ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (codigoAmbiente) REFERENCES Ambiente(codigoAmbiente) 
ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE Atuador(
codigoAtuador VARCHAR(3) NOT NULL,
descricaoAtuador VARCHAR(80) NOT NULL,
comando VARCHAR(1) NOT NULL,
pinoArduino VARCHAR(3) NOT NULL,
requerLogin VARCHAR(1) NOT NULL,
codigoSensor VARCHAR(3),
codigoAmbiente VARCHAR(4) NOT NULL,
PRIMARY KEY (codigoAtuador),
FOREIGN KEY (codigoSensor) REFERENCES Sensor(codigoSensor) 
ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (codigoAmbiente) REFERENCES Ambiente(codigoAmbiente) 
ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE Operacao(
login VARCHAR(10) NOT NULL,
codigoAtuador VARCHAR(2) NOT NULL,
dataOperacao DATE NOT NULL,
horaOperacao TIME NOT NULL,
descricaoOperacao VARCHAR(100) NOT NULL,
PRIMARY KEY (login,codigoAtuador, dataOperacao, horaOperacao),
FOREIGN KEY (login) REFERENCES Morador(login) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (codigoAtuador) REFERENCES Atuador(codigoAtuador) 
ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE Evento(
codigoAlarme VARCHAR(3) NOT NULL,
codigoSensor VARCHAR(3) NOT NULL,
dataEvento DATE NOT NULL,
horaEvento TIME NOT NULL,
PRIMARY KEY (codigoAlarme,codigoSensor,dataEvento,horaEvento),
FOREIGN KEY (codigoAlarme) REFERENCES Alarme(codigoAlarme) 
ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (codigoSensor) REFERENCES Sensor(codigoSensor) 
ON DELETE CASCADE ON UPDATE CASCADE);

INSERT INTO Alarme (codigoAlarme, descricaoAlarme) VALUES ('01','Alarme de gás');

INSERT INTO Ambiente (codigoAmbiente, descricaoAmbiente) VALUES ('SL01','Sala');

INSERT INTO Ambiente (codigoAmbiente, descricaoAmbiente) VALUES ('CZ01','Cozinha');

INSERT INTO Ambiente (codigoAmbiente, descricaoAmbiente) VALUES ('QT01','Quarto do casal');

INSERT INTO Ambiente (codigoAmbiente, descricaoAmbiente) VALUES ('PG01','Portão da garagem');

INSERT INTO Atuador (codigoAtuador, descricaoAtuador, 
comando, pinoArduino, codigoAmbiente, requerLogin) 
VALUES ('01','Lampada','L','2','SL01','N');

INSERT INTO Atuador (codigoAtuador, descricaoAtuador, 
comando, pinoArduino, codigoAmbiente, requerLogin) 
VALUES ('02','Lampada','L','3','CZ01','N');

INSERT INTO Atuador (codigoAtuador, descricaoAtuador, 
comando, pinoArduino, codigoAmbiente, requerLogin) 
VALUES ('04','Portão 01','P','5','PG01','S');

INSERT INTO Sensor (codigoSensor, descricaoSensor, 
pinoArduino, codigoAmbiente, codigoAlarme, limiteSupAlarme) 
VALUES ('80','Sensor de gás','A0','CZ01','01','1000');

INSERT INTO Atuador (codigoAtuador, descricaoAtuador, 
comando, pinoArduino, codigoAmbiente, requerLogin, codigoSensor) 
VALUES ('03','Atuador do Sensor de Gás','L','4','CZ01','N', '80');

INSERT INTO Morador (login, nome, dataNascimento, senha) 
VALUES ('walter12','Walter Camargo','1978-05-12',MD5('121578'));
