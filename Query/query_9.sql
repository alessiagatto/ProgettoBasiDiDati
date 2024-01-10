
SELECT
    S.NomeScuderia,
    COUNT(DISTINCT V.NumeroGara) AS NumeroPartecipazioni,
    S.NumeroFinanziamenti
FROM Scuderia S
         LEFT JOIN Vettura V ON S.NomeScuderia = V.NomeScuderia
GROUP BY S.NomeScuderia, S.NumeroFinanziamenti
ORDER BY S.NomeScuderia;





