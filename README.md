

## Progetto Basi di Dati 2023/24
> Progetto realizzato da [Iari Normanno](https://github.com/wassupiari) - [Alessia Gatto](https://github.com/alessiagatto)

## Obiettivo
E' stato richiesto di creare un’applicazione per la gestione di un
campionato mondiale di automobili. Tale applicazione dovrà
caratterizzare ogni aspetto relativo al campionato, ai circuiti, alle gare e
alle scuderie che partecipano. Infatti, un’applicazione di questi tipo
caratterizza non solo le categorie di piloti che formano l’equipaggio ma
anche le vetture e le componenti di cui sono dotate. Inoltre, essa deve
essere in grado di memorizzare i risultati conseguiti da ciascuna vettura
nel corso delle gare di una stagione. I piloti possono essere membri
dell’equipaggio e finanziatori della scuderia stessa. Quest’ultima si dota
di autovetture, le quali sono assemblate a partire da diversi
componenti, prodotti da costruttori diversi. Se una vettura partecipa ad
una gara conseguirà dei punti a seconda della sua posizione ricoperta
al termine della stessa.

La documentazione del progetto la trovate [qui](documentazione/Progetto.pdf). 

## Descrizione dettagliata 
 > macro-operazioni
1. Gestione del campionato:
   - Registrazione di una nuova gara al campionato;
   - Assegnazione della gara al circuito nel quale si svolge;
   - Iscrizione delle vetture di una scuderia al campionato;
   - Visualizzazione dei risultati ottenuti da ciascuna vettura;
2. Gestione delle scuderie:
   - Assegnamento delle vetture alle scuderie che le gestiscono;
   - Gestione dei finanziatori e dei finanziamenti ottenuti dalla scuderia;
3. Gestione delle vetture:
   - Scelta delle componenti di cui è caratterizzata una vettura;
   - Tracciamento dei costruttori responsabili per la creazione delle
   componenti;
   - Acquisto della vettura da parte di una scuderia;
   - Affidamento della vettura ad un equipaggio di piloti;

## Struttura progetto

> Il progetto durante il corso e' stato suddiviso in 3 consegne diverse/parti

Prima parte:

- Documento di progetto
  - Descrizione Dettagliata
  - Analisi della specifica
  - Glossario dei termini
  - Schema Entità/Relazioni
  - Commenti sulle scelte progettuali effettuate

Seconda parte:
- Documento di Progetto
  - Sviluppo del carico applicativo
  - Ristrutturazione dello schema
  - Disegno dello schema ristrutturato
  - Mapping verso lo schema logico relazionale
- Script da consegnare
  - [File .sql per la creazione del database;](Query/Mapping.sql)
  - [File .sql per il caricamento dei dati;](Query/Insert.sql)

Terza parte: 
- [Applicazione da consegnare](src/PaginaInizialeApp.java)
  - Archivio .zip con il progetto Java che implementi l’applicazione
    «WECApp», nella quale è prevista:
    - La connessione al database;
    - L’implementazione di tutte le operazioni definite di seguito;
    - La stampa/visualizzazione dei risultati di ogni operazione.

## Operazioni effettuate

Tutte le operazioni che sono stato effettuate si trovano in questa [cartella](Query) in questo [file](Query/query.md) troverete la descrizione per ogni operazione.

## License
MIT


