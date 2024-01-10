SELECT
    C.RagioneSociale AS Costruttore,
    MONTH(CMP.DataInstallazione) AS Mese,
    COUNT(*) AS NumeroComponentiForniti
FROM Costruttore C
         JOIN Componente CMP ON C.RagioneSociale = CMP.Costruttore
GROUP BY Costruttore, Mese;