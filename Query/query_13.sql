SELECT
    V.NomeScuderia,
    V.Modello,
    SUM(V.Punti) AS PuntiTotali
FROM Vettura V
GROUP BY V.NomeScuderia, V.Modello
ORDER BY PuntiTotali DESC;