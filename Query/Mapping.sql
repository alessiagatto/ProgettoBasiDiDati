
use campionato;

create table Circuito(
                         NomeCircuito  varchar(30) not null,
                         Paese         varchar(20) not null,
                         Lunghezza     integer not null,
                         NumeroCurve   integer not null,
                         primary key(NomeCircuito)
);

create table Gara(
                     NomeGara     varchar(30) not null primary key,
                     Data         date not null,
                     TipoGara     varchar(20),
                     Durata       integer not null,
                     Circuito varchar(30) not null,
                     foreign key(Circuito) references Circuito(NomeCircuito)
                         on update cascade
                         on delete cascade
);

create table Scuderia(
    NomeScuderia varchar(30) not null primary key,
    SedePrincipale varchar(20) not null,
    NumeroFinanziamenti integer not null
);

create table Vettura(
                        NumeroGara      integer not null,
                        NomeScuderia    varchar(30) not null,
                        MotivoRitiro varchar(50),
                        Modello varchar(20) not null,
                        Punti  integer not null,
                        Posizione integer not null,
                        Gara varchar(30) not null,
                        foreign key(Gara)  references Gara(NomeGara)
                            on update cascade
                            on delete cascade,
                        foreign key(NomeScuderia) references Scuderia(NomeScuderia)
                            on update cascade
                            on delete cascade,
                        primary key(NumeroGara,NomeScuderia)
);

create table Equipaggio(
    IDEquipaggio    integer not null primary key,
    Nome            varchar(20) not null,
    Cognome            varchar(20) not null,
    NumeroLicenza      integer not null,
    NumeroMembro       integer not null,
    DataNascita        date not null,
    Nazionalita        varchar(20) not null,
    Scuderia varchar(30) not null,
    foreign key(Scuderia) references Scuderia(NomeScuderia)
        on update cascade
        on delete cascade
);


create table PilotaAM(
    Codice integer not null primary key,
    Tipo   boolean not null, -- se true allora pilota Gentleman
    Data_1a_Licenza date not null,
    QuotaFinanziaria integer,
    Equipaggio integer not null,
    foreign key(Equipaggio)  references Equipaggio(IDEquipaggio)
        on update cascade
        on delete cascade
);

create table PilotaPRO(
    Codice integer not null primary key,
    NumLicenzePossedute integer not null,
    Equipaggio integer not null,
    foreign key(Equipaggio)  references Equipaggio(IDEquipaggio)
        on update cascade
        on delete cascade
);


create table Costruttore(
    RagioneSociale varchar(30) not null primary key,
    Nome varchar(20) not null,
    Sede varchar(20) not null
);

create table Componente(
    CodiceComponente integer not null, #
    Costruttore varchar(30) not null , #
    Tipo varchar(20) not null, #
    Costo integer not null, #
    Cilindrata integer, #
    DataInstallazione date , #
    NumeroCilindri integer, #
    NumeroMarce integer, #
    Peso integer, #
    Materiale varchar(20), #
    TipoMotore varchar(20), #
    NumeroComponenti integer not null, #
    Vettura integer not null, #
    foreign key(Vettura) references Vettura(NumeroGara)
        on update cascade
        on delete cascade,
    foreign key(Costruttore) references Costruttore(RagioneSociale)
        on update cascade
        on delete cascade,
    primary key(CodiceComponente,Costruttore)
);


-- Creazione del trigger per incrementare NumeroFinanziamenti quando viene inserito un nuovo PilotaAM con Tipo = true
/*CREATE TRIGGER IncrementaFinanziamenti
    AFTER INSERT ON PilotaAM
    FOR EACH ROW
BEGIN
    DECLARE scuderia_nome VARCHAR(30);
    DECLARE tipo_pilota BOOLEAN;

    -- Ottenere il nome della scuderia e il tipo del pilota dalla tabella Equipaggio
    SELECT e.Scuderia, p.Tipo
    INTO scuderia_nome, tipo_pilota
    FROM Equipaggio e, PilotaAM p
    WHERE e.IDEquipaggio = NEW.Equipaggio;

    -- Verificare se il tipo del pilota è true e se l'equipaggio appartiene alla scuderia desiderata
    IF tipo_pilota = true AND scuderia_nome = 'NomeScuderiaDesiderata' THEN
        UPDATE Scuderia
        SET NumeroFinanziamenti = NumeroFinanziamenti + 1
        WHERE NomeScuderia = scuderia_nome;
    END IF;
END;
*/

