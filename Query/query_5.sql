# Insert Circuito
INSERT INTO Circuito (NomeCircuito, Paese, Lunghezza, NumeroCurve) VALUES
    (?,?,?,?);

# Insert Gara
INSERT INTO Gara (NomeGara, Data, TipoGara, Durata, Circuito) VALUES
    (?,?,?,?,?);

# Insert Scuderia
INSERT INTO Scuderia (NomeScuderia, SedePrincipale, NumeroFinanziamenti) VALUES
    (?,?,?);

# Insert Vettura
INSERT INTO Vettura (NumeroGara, NomeScuderia, MotivoRitiro, Modello, Punti, Posizione,Gara) VALUES
    (?,?,?,?,?,?,?);