
use campionato;
-- Inserimenti per la tabella Circuito

-- Inserimenti per la tabella Scuderia
INSERT INTO Scuderia (NomeScuderia, SedePrincipale, NumeroFinanziamenti) VALUES
    ('Ferrari', 'Maranello', 0),
    ('Mercedes', 'Brackley', 1),
    ('Red Bull Racing', 'Milton Keynes', 0),
    ('McLaren', 'Woking', 1),
    ('Alfa Romeo Racing', 'Hinwil', 1);

-- Inserimenti per la tabella Equipaggio
INSERT INTO Equipaggio (IDEquipaggio, Nome, Cognome, NumeroLicenza, NumeroMembro, DataNascita, Nazionalita, Scuderia) VALUES
    (1, 'Mario', 'Rossi', 12345, 1, '1990-03-15', 'Italiana', 'Ferrari'),
    (2, 'Luca', 'Bianchi', 56789, 2, '1988-07-22', 'Francese', 'Mercedes'),
    (3, 'Anna', 'Verdi', 98765, 3, '1995-05-10', 'Spagnola', 'Red Bull Racing'),
    (4, 'Giovanni', 'Neri', 54321, 4, '1992-11-18', 'Tedesc', 'McLaren'),
    (5, 'Elena', 'Russo', 11111, 5, '1985-09-03', 'Inglese', 'Alfa Romeo Racing'),
    (6, 'Marco', 'Gialli', 78901, 6, '1993-12-08', 'Italiana', 'Ferrari'),
    (7, 'Laura', 'Verde', 23456, 7, '1987-06-25', 'Spagnola', 'Mercedes'),
    (8, 'Antonio', 'Blu', 87654, 8, '1994-02-17', 'Francese', 'Red Bull Racing'),
    (9, 'Giulia', 'Arancione', 32109, 9, '1991-09-29', 'Inglese', 'McLaren'),
    (10, 'Roberto', 'Rosa', 56789, 10, '1986-04-14', 'Tedesca', 'Alfa Romeo Racing');

INSERT INTO Circuito (NomeCircuito, Paese, Lunghezza, NumeroCurve) VALUES
    ('Monza', 'Italia', 5793, 11),
    ('Silverstone', 'Regno Unito', 5891, 18),
    ('Monte Carlo', 'Monaco', 3337, 19),
    ('Spa-Francorchamps', 'Belgio', 7004, 20),
    ('Suzuka', 'Giappone', 5807, 18);

-- Inserimenti per la tabella Gara
INSERT INTO Gara (NomeGara, Data, TipoGara, Durata, Circuito) VALUES
    ('Gara1', '2023-05-01', 'Asciutto', 120, 'Monza'),
    ('Gara2', '2023-05-02', 'Asciutto', 180, 'Silverstone'),
    ('Gara3', '2023-05-03', 'Asciutto', 120, 'Monte Carlo'),
    ('Gara4', '2023-05-04', 'Asciutto', 180, 'Spa-Francorchamps'),
    ('Gara5', '2023-05-05', 'Asciutto', 180, 'Suzuka');

-- Inserimenti per la tabella PilotaAM
INSERT INTO PilotaAM (Codice, Tipo, Data_1a_Licenza, QuotaFinanziaria, Equipaggio) VALUES
(1, false, '2010-05-20', NULL, 1),
(2, true, '2012-08-15', 450000, 2),
(3, false, '2015-03-10', NULL, 3),
(4, true, '2011-11-25', 550000, 4),
(5, true, '2009-07-12', 40000, 5);

-- Inserimenti per la tabella PilotaPRO
INSERT INTO PilotaPRO (Codice, NumLicenzePossedute, Equipaggio) VALUES
    (6, 3, 6),
    (7, 4, 7),
    (8, 5, 8),
    (9, 2, 9),
    (10, 6, 10);

-- Inserimenti per la tabella Vettura
INSERT INTO Vettura (NumeroGara, NomeScuderia, MotivoRitiro, Modello, Punti, Posizione,Gara) VALUES
    (1, 'Ferrari', NULL, 'ModelloF', 25, 1,'Gara1'),
    (2, 'Mercedes', NULL, 'ModelloM', 18, 2,'Gara2'),
    (3, 'Red Bull Racing', NULL, 'ModelloR', 15, 3,'Gara3'),
    (4, 'McLaren', NULL, 'ModelloMc', 12, 4,'Gara4'),
    (5, 'Alfa Romeo Racing', NULL, 'ModelloAR', 10, 5,'Gara5');

-- Inserimenti per la tabella Costruttore
INSERT INTO Costruttore (RagioneSociale, Nome, Sede) VALUES
    ('FerrariCostruttore', 'Ferrari', 'Maranello'),
    ('MercedesCostruttore', 'Mercedes', 'Brackley'),
    ('RedBullCostruttore', 'Red Bull Racing', 'Milton Keynes'),
    ('McLarenCostruttore', 'McLaren', 'Woking'),
    ('AlfaRomeoCostruttore', 'Alfa Romeo Racing', 'Hinwil');

-- Inserimenti per la tabella Componente
INSERT INTO Componente (CodiceComponente, Costruttore, Tipo, Costo, Cilindrata, DataInstallazione, NumeroCilindri, NumeroMarce, Peso, Materiale, TipoMotore, NumeroComponenti, Vettura) VALUES
    (1, 'FerrariCostruttore', 'Motore', 1000000, 1600, '2023-01-15', 6, 8, 200, 'Leghe di alluminio', 'V6 Turbo', 15, 1),
    (2, 'MercedesCostruttore', 'Motore', 1200000, 1600, '2023-02-20', 6, 7, 195, 'Leghe di magnesio', 'V6 Turbo', 14, 2),
    (3, 'RedBullCostruttore', 'Motore', 900000, 1600, '2023-03-25', 6, 8, 210, 'Leghe di titanio', 'V6 Turbo', 16, 3),
    (4, 'AlfaRomeoCostruttore', 'Motore', 1100000, 1600, '2023-04-30', 6, 7, 198, 'Leghe di alluminio', 'V6 Turbo', 13, 4),
    (5, 'McLarenCostruttore', 'Motore', 850000, 1600, '2023-05-15', 6, 8, 205, 'Leghe di titanio', 'V6 Turbo', 12, 5);