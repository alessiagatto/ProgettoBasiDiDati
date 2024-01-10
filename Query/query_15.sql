
SELECT
    S.NomeScuderia,
    AVG(V.Punti * 1.0 / G.Durata) AS RapportoPuntiMinutiMedio
FROM Scuderia S
         JOIN Vettura V ON S.NomeScuderia = V.NomeScuderia
         JOIN Gara G ON V.Gara = G.NomeGara
GROUP BY S.NomeScuderia
ORDER BY RapportoPuntiMinutiMedio DESC;


