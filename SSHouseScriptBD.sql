DROP SCHEMA IF EXISTS SSHouse;
CREATE DATABASE SSHouse;
USE SSHouse;

DROP TABLE IF EXISTS Morador;
DROP TABLE IF EXISTS Ambiente;
DROP TABLE IF EXISTS Equipamento;
DROP TABLE IF EXISTS Operacao;
DROP TABLE IF EXISTS Alarme;
DROP TABLE IF EXISTS Evento;

CREATE TABLE Morador (
login VARCHAR(10) NOT NULL,
nome VARCHAR(80) NOT NULL,
dataNascimento DATE NOT NULL,
senha VARCHAR(500) NOT NULL,
PRIMARY KEY (login));

CREATE TABLE Ambiente(
codigoAmbiente VARCHAR(2) NOT NULL,
descricaoAmbiente VARCHAR(100) NOT NULL,
PRIMARY KEY (codigoAmbiente));

CREATE TABLE Equipamento(
codigoEquipamento VARCHAR(2) NOT NULL,
descricaoEquipamento VARCHAR(80) NOT NULL,
estado VARCHAR(1) NOT NULL,
pinoArduino int NOT NULL,
codigoAmbiente VARCHAR(2) NOT NULL,
PRIMARY KEY (codigoEquipamento),
FOREIGN KEY (codigoAmbiente) REFERENCES Ambiente(codigoAmbiente) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE Operacao(
login VARCHAR(10) NOT NULL,
codigoEquipamento VARCHAR(2) NOT NULL,
diaOperacao DATE NOT NULL,
horaOpercao TIME NOT NULL,
PRIMARY KEY (login,codigoEquipamento),
FOREIGN KEY (login) REFERENCES Morador(login) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (codigoEquipamento) REFERENCES Equipamento(codigoEquipamento) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE Alarme(
codigoAlarme VARCHAR(2) NOT NULL,
descricaoAlarme VARCHAR(100) NOT NULL,
PRIMARY KEY (codigoAlarme));

CREATE TABLE Evento(
codigoAlarme VARCHAR(2) NOT NULL,
codigoEquipamento VARCHAR(2) NOT NULL,
diaEvento DATE NOT NULL,
horaEvento TIME NOT NULL,
PRIMARY KEY (codigoAlarme,codigoEquipamento),
FOREIGN KEY (codigoAlarme) REFERENCES Alarme(codigoAlarme) 
ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (codigoEquipamento) REFERENCES Equipamento(codigoEquipamento) 
ON DELETE CASCADE ON UPDATE CASCADE);

INSERT INTO Alarme (codigoAlarme, descricaoAlarme) VALUES ('01','Alarme de gás');
INSERT INTO Ambiente (codigoAmbiente, descricaoAmbiente) VALUES ('SL','Sala');
INSERT INTO Equipamento (codigoEquipamento, descricaoEquipamento, 
estado, pinoArduino, codigoAmbiente) VALUES ('01','Lampada','D','2','SL');
INSERT INTO Morador (login, nome, dataNascimento, senha) 
VALUES ('walter12','Walter Camargo','1978-05-12',MD5('121578'));