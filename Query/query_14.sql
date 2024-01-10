SELECT
    C.TipoMotore,
    V.Modello,
    SUM(V.Punti) AS PuntiTotali
FROM Vettura V
         JOIN Gara G ON V.Gara = G.NomeGara
         JOIN Componente C ON V.NumeroGara = C.Vettura
GROUP BY C.TipoMotore, V.Modello
ORDER BY C.TipoMotore, PuntiTotali DESC;