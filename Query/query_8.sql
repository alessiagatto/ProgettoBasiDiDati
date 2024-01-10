# Per ciascuna scuderia, stampare la somma totale dei finanziamenti ricevuti

SELECT S.NomeScuderia, SUM(PA.QuotaFinanziaria) AS SommaFinanziamenti
FROM PilotaAM PA
JOIN Equipaggio E ON PA.Equipaggio = E.IDEquipaggio
JOIN Scuderia S ON E.Scuderia = S.NomeScuderia
WHERE PA.QuotaFinanziaria IS NOT NULL
GROUP BY S.NomeScuderia;

