# Insert Vettura
INSERT INTO Vettura (NumeroGara, NomeScuderia, MotivoRitiro, Modello, Punti, Posizione) VALUES
    (?,?,?,?,?,?,?);

# Insert Costruttore
INSERT INTO Costruttore (RagioneSociale, Nome, Sede) VALUES
    (?,?,?);

# Insert Componente
INSERT INTO Componente (CodiceComponente, Costruttore, Tipo, Costo, Cilindrata, DataInstallazione, NumeroCilindri, NumeroMarce, Peso, Materiale, TipoMotore, NumeroComponenti, Vettura) VALUES
    (?,?,?,?,?,?,?,?,?,?,?,?,?);

    

