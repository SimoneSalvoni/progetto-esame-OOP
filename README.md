# progetto-esame-OOP
L'applicazione qua presentata costruisce un web service basato su Springboot che fa da intermediario fra le API di Facebook ed un client esterno. Tale client avrà la possibiltà di:
 - visualizzare i metadati relativi ai post
 - visualizzare l'intera lista di post dell'utente
 - visualizzare statistiche sui post dell'utente
 - filtrare i post dell'utente e visualizzare quindi statistiche su tali post filtrati

Le tipologie di statistiche fornite e i filtri supportati saranno descritti in seguito. Tutto ciò viene restituito in formato JSON. 
## Funzionamento dell'applicazione
### Avvio
All'avvio viene mandata una richiesta di tipo GET a Facebook, ricevendo da esso tutti i post dell' utente assieme a varie altre informazioni:
- L'id dei post
- L'effettivo messaggio contenuto nel post
-  La data di creazione del post
- La tipologia del post, che può essere: 
--  "status" se è un normale post
-- "link" se contiene un link
-- "photo" se contiene una foto
-- "video" se contiene un video
- Eventuali link presenti nel post (compreso il link ad un immagine/video). 

La presenza di foto/video ha precedenza sulla presenza di link, sia per il tipo sia per il link restituito.

Per richiedere tali informazioni a Facebook è ovviamente necessario un token d'accesso. Esso deve essere salvato localmente su un file di testo denominato "token.txt", da dove verrà poi letto dall'applicazione. 

A questo punto vengono creati i metadati e calcolate le statistiche su tutti i post restituiti da Facebook. Tutto ciò è salvato in strutture dati create appositamente. 

Un appunto su quanto restituito da Facebook: oltre a ciò che ci interessa Facebook ci restituisce due elementi indesiderati, che quindi verranno il primo eliminato, il secondo ignorato. Nella sezione dei post infatti viene inserito come primo una sorta di post vuoto, che rappresenta la creazione dell'account. Non è di nostro interesse salvarlo.
Inoltre dopo i post Facebook restituisce anche una sezione nominata "paging", che viene ignorata dall'applicazione fin da subito. 
### Web service
Una volta completato il collegamento con Facebook, il calcolo delle statistiche e il salvataggio dei dati, l'applicazione è pronta a dialogare con un client esterno.
L'applicazione supporta le seguenti richieste GET e POST:

##### GET
|Rotta       | Descrizione|
|-----------|------------------------|
|/Metadata| Vengono restituiti i metadati|
|/Data|Vengono restituiti tutti i post|
|/Stats|Vengono restituite tutte le statistiche calcolate sull'intero feed|
|/Stats/Politic|Vengono restituite le statistiche sulla categorizzazione politica calcolate sull'intero feed|
|/Stats/Lenght|Vengono restituite tutte le statistiche sulla lunghezza dei post calcolate sull'intero feed|
|Stats/Time|Vengono restituite tutte le statistiche sulla data di creazione calcolate sull'intero feed|
#####  POST
|Rotta       | Descrizione|
|-----------|------------------------|
|/Data|Vengono restituiti i post filtrati secondo quanto specificato nel body della richiesta
/Stats|Vengono restituite tutte le statistiche calcolate sul feed filtrato secondo quanto specificato nel body della richiesta|
|/Stats/Politic|Vengono restituite le statistiche sulla categorizzazione politica calcolate sul feed filtrato secondo quanto specificato nel body della richiesta|
|/Stats/Lenght|Vengono restituite tutte le statistiche sulla lunghezza dei post calcolate calcolate sul feed filtrato secondo quanto specificato nel body della richiesta|
|Stats/Time|Vengono restituite tutte le statistiche sulla data di creazione calcolate calcolate sul feed filtrato secondo quanto specificato nel body della richiesta|

Seguono i diagrammi UML dei casi d'uso che rappresentano le azioni possibili del Client:

Diagramma dei casi d'uso generale:
![Diagramma dei casi d'uso generale](https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Casi%20d'uso%20-%20generale.png )

Diagramma dei casi d'uso per la richiesta dei post:
![Diagramma dei casi d'uso per la richiesta dei post](https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Casi%20d'uso%20-%20dati.png)

Diagramma dei casi d'uso per la richiesta delle statistiche:
![Diagramma dei casi d'uso per la richiesta delle statistiche](https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Casi%20d'uso%20-%20stat.png)

## Statistiche
Le statistiche che vengono calcolate sono le seguenti:
##### Statistiche sulla categorizzazione politica
|Stat|Descrizione|
-----|------------|
|numPoliticPost|Il numero di post generalemente considerati politici, in percentuale rispetto al totale|
|numNationalPost|Il numero di post riguardanti politica interna italiana, in percentuale rispetto al totale|
|numEUPost|Il numero di post riguardanti politica europea, in percentuale rispetto al totale|
|numExtraEUPost|Il numero di post riguardanti politica extra europea, in percentuale rispetto al totale|
|numInternationalPost|Il numero di post riguardanti politica internazionale, in percentuale rispetto al totale
##### Statistiche sulla lunghezza dei post
|Stat|Descrizione|
-----|------------|
|minChar|Il numero di caratteri del post più corto|
|maxChar|Il numero di caratteri del post più lungo|
|avgChar|Il numero medio di caratteri dei post|
|stdDev|La deviazione standard del numero di caratteri dei post|
##### Statistiche sulla data di creazione dei post
|Stat|Descrizione|
-----|------------|
|postPerHour|Per ogni fascia oraria, da 0-1 a 23-24, rappresenta quanti post in percentuale sono stati creati in tale fascia|

## Filtri
Il client può decidere di filtrate i post richiesti o le statistiche richieste secondo più criteri:

- secondo la categorizzazione politica dei post
- secondo la tipologia dei post
- secondo la data di creazione dei post

Il filtro va inserito nel body della richiesta di tipo POST, con ovviamente la rotta corretta. Essi devono avere una precisa formattazione, generalmente dell forma *{field:value}*, dove *field* indica la tipologia di filtro, e *value* indica generalmente la condizione da rispettare (entrambi vanno fra virgolette).
È possibile inoltre possibile filtrare rispetto a due condizioni, sia nel caso  in cui le si voglia entrambe soddisfatte, sia nel caso in cui si voglia che almeno una delle due lo sia.
#### Filtri secondo la categorizzazione politica  
Per questa tipologia di filtro il *"campo"* è *"category"*, mentre il valore è la categoria richiesta, espressa in questa forma:

- NON_POLITIC
- POLITIC
- NATIONAL
- EU
- EXTRA_EU
- INTERNATIONAL

Un esempio è il seguente: *{"category":"NATIONAL"}* se si vuole ottenere solo informazioni sui post sulla politica nazionale. 
#### Fitri secondo la tipologia
Per questa tipologia di filtro il *"campo"* è *"type"*, mente il valore è la tipologia richiesta, fra 

- status
- link
- photo
- video

Un esempio è il seguente: *{"type":"photo"}* se si vuole ottenere solo informazioni sui post contenenti foto.
#### Filtri secondo la data di creazione
Questa tipologia di filtro ha una sintassi un po' più complessa. Questa tipologia di filtraggio è infatti suddivisa a sua volta in 3 sotto-tipi, che rappresentano un'operazione diversa, la quale va specificata
La generica sintassi è la seguente: *{"created_time":{operation:date}}*.
Con questo filtraggio è possibile ottenere informazioni sui post

- che precedono la data specificata: in questo caso *operation* è *"before"*,  mentre *date* è una singola data.
- che seguono la data specificata: in questo caso *operation* sarebbe *"after"*, mentre *date* è una singola data.
- che sono stati creati fra le date specificate: in questo caso *operation* è *"between"*, mentre *date* è una coppia di date, separate da "-".

Le date vanno scritte in questo modo: YYYY/MM/DD, ovvero anno-mese-giorno, con l'anno a 4 cifre, mese e giorno con 2. Esse sono estremi inclusi: se la data di creazione coincide con quella specificata il post è mantenuto. 
Esempi di filtri di questo tipo sono: 
*{"created_time":{"before":"2020/01/10"}}*
 *{"created_time":{"after":"2020/03/15"}}* 
 *{"created_time":{"between":"2020/06/20-2020/08/15"}}*
#### Filtri doppi
In questo caso la sintassi da seguire è la seguente: *Filtro1,Logica,Filtro2*, dove *Logica* può essere

- AND: se vogliamo che entrambe le condizioni specificate dai filtri siano soddisfatte
- OR: se vogliamo che solo una delle due condizioni specificate dai filtri siano soddisfatte

Esempi di filtri doppi sono: 
*{"type":"photo"},AND,{"category","POLITIC"}*, se si vuole ottenere informazioni su post categorizzati come politici e che contengano foto, oppure 
*{"type":"status"},OR,{"created_time":{"after":"2020/07/11"}}*, se si vuole ottenere informazioni su post che contengono solo testo o che siano stati pubblicati a partire dall'11 luglio del 2020. 
## UML: diagramma delle classi
Questa è la struttura interna dell'apllicazione:
![classi](https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Diagramma%20delle%20classi.png)
## UML: diagrammi delle sequenze
Segueono i diagrammi delle sequenze per le varie operazioni possibili al Client
#### GET /Metadata
![GETMetadata](https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Sequenze%20-%20GET_metadata.png)
La classe *ControllerClass* richiede i metadati costruiti all'avvio con il metodo *getMetadata()* e li restituisce in formato JSON al Client.
#### GET /Data
![](https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Sequenze%20-%20GET_data.png)
La classe *ControllerClass* richiede i Post salvati all'avvio con il metodo *getFeed()* e li restituisce in formato JSON al Client
#### GET /Stat
![https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Sequenze%20-%20GET_Stats.png](https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Sequenze%20-%20GET_Stats.png)
La classe *ControllerClass* restituisce l'oggetto *Stat* creato inizialmente in formato JSON.
#### GET /Stat/[specifica]
[specifica] può indicare una delle tre rotte specifiche indicate precedentemente
![https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Sequenze%20-%20GET_Stat_%5Bspecifica%5D.png](https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Sequenze%20-%20GET_Stat_%5Bspecifica%5D.png)
La classe *ControllerClass* ottiene uno degli attibuti dell'oggetto della classe *Stat* creato inizialmente con il giusto metodo getter fra *getStatPoltic()*, *getStatLenght()* e *getStatTime()* e li restituisce in formato JSON.
#### POST /Data
![https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Sequenze%20-%20POST_Data.png](https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Sequenze%20-%20POST_Data.png)
La classe *ControllerClass* chiama il metodo costruttore *FilterHandler()* per costruire un oggetto di tale classe, il quale costruisce a sua volta il filtro/i filtri necessari a seconda della richiesta presente nel body. A questo punto chiama il metodo *filterFeed()* dell'oggetto costruito per filtrare il feed e ottenere un nuovo vettore di *Post*, il quale viene restituito in formato JSON.
#### POST /Stats o POST /Stats/[specifica]
Nel caso in cui si richiedano tutte le statistiche il diagramma è il seguente:
![https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Sequenze%20-%20POST_Stat.png](https://raw.githubusercontent.com/Ziozimomo/progetto-esame-OOP/master/Sequenze%20-%20POST_Stat.png)
La classe *ControllerClass* chiama il metodo costruttore *FilterHandler()* per costruire un oggetto di tale classe, il quale costruisce a sua volta il filtro/i filtri necessari a seconda della richiesta presente nel body. A questo punto chiama il metodo *filterFeed()* dell'oggetto costruito per filtrare il feed e ottenere un nuovo vettore di *Post*. Su questo vettore vengono ricalcolate tutte le statistiche, formando un oggetto dela classe *Stat*, il quale viene restituito in formato JSON.
Se la richiesta è di solo alcune statistiche (quindi POST Stats/Lenght, etc), il processo è identico, ma invece di costruire un oggetto della classe *Stat* completo, si costruisce soltanto un oggetto della classe necessaria per fornire i dati richiesti (quindi *StatLenght*, etc).

## AUTORI 
- Simone Salvoni. Apporto al progetto: modellazione iniziale, classi Post, Metadata, Feed, FilterHandler, InstanceFilter, ExceptionHandler, package Exceptions, package Test, documentazione delle proprie classi, revisioni e correzioni su modellazione, documentazione e applicazione, README
- Daniele Staffolani. Apporto al progetto: modellazione iniziale, classi Stat, StatLenght, StatPolitic, StatTime, ControllerClass, package util, classe Filter e le relative sottoclassi, documentazione delle proprie classi, README. 

