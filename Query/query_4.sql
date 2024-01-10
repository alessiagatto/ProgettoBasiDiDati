# Registrazione di un finanziamento per una scuderia
UPDATE Scuderia SET NumeroFinanziamenti = NumeroFinanziamenti + 1
WHERE NomeScuderia = ?;