SELECT
    S.NomeScuderia,
    COUNT(CASE WHEN P.Tipo = true THEN 1 END) AS NumeroGentlemanDriver,
    COUNT(*) AS NumeroTotaleMembri,
    ROUND(COUNT(CASE WHEN P.Tipo = true THEN 1 END) * 100 / COUNT(*), 2) AS PercentualeGentlemanDriver
FROM Scuderia S
JOIN Equipaggio E ON S.NomeScuderia = E.Scuderia
JOIN (
    SELECT Codice, Tipo, Equipaggio
    FROM PilotaAM
    UNION ALL
    SELECT Codice, NULL AS Tipo, Equipaggio
    FROM PilotaPRO
) P ON E.IDEquipaggio = P.Equipaggio
GROUP BY S.NomeScuderia;












 

