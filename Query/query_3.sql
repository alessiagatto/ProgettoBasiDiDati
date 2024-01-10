# Insert Scuderia
INSERT INTO Scuderia (NomeScuderia, SedePrincipale, NumeroFinanziamenti) VALUES (?,?,?);

# Registrazione Equipaggio
INSERT INTO Equipaggio (IDEquipaggio, Nome, Cognome, NumeroLicenza, NumeroMembro, DataNascita, Nazionalita, Scuderia) VALUES
    (?,?,?,?,?,?,?,?);

# Registrazione tipo Pilota
INSERT INTO PilotaAM (Codice, Tipo, Data_1a_Licenza, QuotaFinanziaria, Equipaggio) VALUES
    (?,?,?,?,?);

# Registrazione tipo Pilota
INSERT INTO PilotaPRO (Codice, NumLicenzePossedute, Equipaggio) VALUES
    (?,?,?);
