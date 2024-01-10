SELECT E.Nome, E.Cognome, C.Paese AS NazionalitaCircuito
FROM Equipaggio E
         JOIN Scuderia S ON S.NomeScuderia= E.Scuderia
         JOIN Vettura V ON S.NomeScuderia = V.NomeScuderia
         JOIN Gara G ON V.Gara = G.NomeGara
         JOIN Circuito C ON G.Circuito = C.NomeCircuito
WHERE E.Nazionalita = C.Paese AND V.Posizione=1