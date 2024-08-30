# H-CLUS

---

## INDICE

1. [Introduzione](#introduzione)

2. [Guida di installazione](#installazione)

3. [Guida utente](#utente)

   4.1 [Server](#guida-server)  
   4.2 [Client](#guida-client)

4. [Casi d'uso](#test)
5. [JavaDoc](#javadoc)
5. [Diagramma delle classi](#diagramma)

   1.1 [Server](#diagramma-server)  
   1.2 [Client](#diagramma-client)

---

<h2 id="introduzione">Introduzione</h2>

“H-CLUS” è un sistema client-server che include funzionalità di data mining per la scoperta di un dendrogramma di
cluster di dati con algoritmo di clustering agglomerativo e consente di usufruire del servizio di scoperta remoto, 
permettendo di visualizzare il dendrogramma scoperto.

---

<h2 id="installazione">Guida di installazione</h2>

Per installare il software H-CLUS, segui questi passaggi:

1. Installazione del JDK:

   + Assicurati di avere il Java Development Kit (JDK) versione 11 o successiva. Puoi scaricarlo dal 
     <a href="https://www.oracle.com/java/technologies/downloads/#java11">sito</a> ufficiale di Oracle.

2. Installazione di MySQL:

   + Scarica e installa MySQL Community Server dal <a href="https://dev.mysql.com/downloads/mysql/">sito ufficiale</a>.

3. Configurazione delle variabili d'ambiente:

   1. Apri il menu Start e cerca "variabili d'ambiente".
   2. Seleziona "Modifica le variabili d'ambiente di sistema".
   3. Nella finestra "Proprietà del sistema", clicca su "Variabili d'ambiente...".
   4. Nella sezione "Variabili di sistema", individua e seleziona la variabile "Path", quindi clicca su "Modifica...".
   5. Aggiungi il percorso della cartella bin di MySQL, di solito situata in C:\Program Files\MySQL\MySQL Server [versione]\bin .
   6. Aggiungi il percorso della cartella bin di MySQL, di solito situata in C:\Program Files\Java\jdk-[versione]\bin.
   7. Conferma cliccando su "OK" per chiudere tutte le finestre.

4. Verifica dell'installazione: 
   1. Apri il prompt dei comandi.
   2. Digita mysqlsh --version e premi "Invio".
      Se l'installazione è avvenuta correttamente, verrà visualizzata la versione di MySQL installata.
   3. Digita java --version e premi "Invio".
      Se l'installazione è avvenuta correttamente, verrà visualizzata la versione di Java installata.

---

<h2 id="utente">Guida utente</h2>

<h3 id="guida-server">Server</h3>
1. Nella cartella principale del progetto, trova il file `server.bat` ed eseguilo con un doppio clic, come di seguito 
mostrato.
<img src="image/clic.gif">  
  In alternativa:
   + apri il prompt dei comandi  
   <img src="image/cmd.png">
   + usa il comando `cd` seguito dal percorso della directory del progetto  
     <img src="image/cd.png">
   + esegui il file `server.bat`  
   <img src="image/bat.png">  

2. Dopo aver avviato il programma, inserisci le informazioni richieste riguardanti il database  
    <img src="image/database.png">
3. Se la connessione al database è avvenuta con successo, ti sarà richiesto di specificare il numero di porta su cui 
   avviare il server  
   <img src="image/port.png">
4. Inserito il numero di porta su cui avviare il servzio, verrà compilato il codice sorgente del programma: in caso di 
 successo, il server verrà avviato, con il servizio attivo sulla porta specificata al punto precedente  
    <img src="image/server.png">





<h3 id="guida-client">Client</h3>
Eseguire il file `client.bat` presente nella cartella principale del progetto.
Per eseguirlo è possibile effettuare doppio clic su di esso o, in alternativa, aprire il terminale nella directory del 
progetto e digitare `client.bat`, premendo infine il tasto "Invio". 
 
Attendere che il programma compili il codice sorgente.  
Una volta terminata l'inizializzazione, inserire l'indirizzo IP del server a cui si desidera collegarsi.

> Enter server IP: 

Premere il tasto "Invio" per confermare.  
Successivamente immettere la porta sulla quale il server è in ascolto.

> Enter server Port: 

Premere il tasto "Invio" per confermare.  
Inserire il nome della tabella da cui si desidera caricare il data set.

> addr = ..........  
> Socket[addr=..........,port=....,localport=.....]  
> Nome tabella:  
> <br>

Premere il tasto "Invio" per confermare.  
Immettere il numero dell'opzione del menu scelta.

> Scegli una opzione  
> <a href="#first-option">(1) Carica Dendrogramma da File</a>  
> <a href="#second-option">(2) Apprendi Dendrogramma da Database</a>  
> Risposta:

Premere il tasto "Invio" per confermare.

<br><br><br>

<div id="first-option">
Nel caso si sia scelta l'opzione 1, inserire il nome del file, presente sul server, nel quale è contenuto il
dendrogramma desiderato.

> Risposta:1  
> Inserire il nome dell'archivio (comprensivo di estensione):  
> <br>

Premere il tasto "Invio" per confermare.  
A schermo sarà visualizzato il dendrogramma caricato da file.
</div>

<br><br><br>

<div id="second-option">
Nel caso si sia scelta l'opzione 2, inserire la profondità desiderata per il nuovo dendrogramma.

> Risposta:2  
> Introdurre la profondita' del dendrogramma  
> <br>

Premere il tasto "Invio" per confermare.  
Immettere il numero relativo al metodo per la distanza scelto.

> Distanza: single-link (1), average-link (2):  
> <br>

Premere il tasto "Invio" per confermare.  
A schermo sarà visualizzato il nuovo dendrogramma.
Inserire il nome del file nel quale si desidera salvare il dendrogramma appena generato.

> Inserire il nome dell'archivio (comprensivo di estensione):  
> <br>

Premere il tasto "Invio" per confermare.
</div>

---

<h2 id="test">Casi d'uso</h2>

<h3>Server</h3>

- [Credenziali corrette](#s1)
- [Nome utente errato](#s2)
- [Password errata](#s3)
- [Credenziali nulle](#s4)
- [Porta disponibile](#s5)
- [Porta non disponibile](#s6)
- [Porta non valida](#s7)

<h3>Client</h3>

- [Indirizzo IP e porta corretti](#c1)
- [Indirizzo IP non disponibile](#c2)
- [Formato indirizzo IP errato](#c3)
- [IP e porta nulli](#c4)
- [Numero porta errato](#c5)
- [Nome tabella database corretto](#c6)
- [Nome tabella database errato](#c7)
- [Opzione menu corretta (1)](#c8)
- [Nome file caricamento corretto](#c9)
- [Nome file caricamento errato](#c10)
- [Opzione menu corretta (2)](#c11)
- [Profondità dendrogramma valida](#c12)
- [Profondità dendrogramma negativa](#c13)
- [Profondità dendrogramma non valida](#c14)
- [Opzione menu distanza non valida](#c15)
- [Nome file salvataggio corretto](#c16)
- [Opzione menu non valida](#c17)

---

<h4 id="s1">Credenziali corrette</h4>

Comportamento del programma nel caso in cui vengano inserite delle credenziali d'accesso per il server mysql errate.

<img src="./image/server/correctCredentials.png">



Caso in cui un vengano inserite credenziali corrette per l'accesso al servizio mysql.

<h4 id="s2">Nome utente errato</h4>

<img src="./image/server/wrongUsername.png">



<h4 id="s3">Password errata</h4>

Comportamento nel caso in cui venga inserita una password errata per l'accesso al servizio mysql.

<img src="./image/server/wrongPassword.png">



<h4 id="s4">Credenziali nulle</h4>

Comportamento del programma nel caso non vengano inserite delle credenziali d'accesso.

<img src="./image/server/nullCredentials.png">



<h4 id="s5">Porta disponibile</h4>

Caso in cui venga inserito un numero di porta disponibile per il server.

<img src="./image/server/availablePort.png">



<h4 id="s6">Porta non disponibile</h4>

Comportamento del programma nel caso in cui sia stata inserito un numero di porta già utilizzato da un altro servizio
e pertanto non disponibile.

<img src="./image/server/unavailablePort.png">



<h4 id="s7">Porta non valida</h4>

Comportamento del programma nel caso il numero di porta inserito sia riservato per il sistema e dunque invalido.

<img src="./image/server/invalidPort.png">







<h4 id="c1">Indirizzo IP e porta corretti</h4>

Caso in cui venga inserito un indirizzo ed una porta corretti per il collegamento al server.

<img src="./image/client/correctAddress.png">



<h4 id="c2">Indirizzo IP non disponibile</h4>

Comportamento nel caso in cui l'indirizzo inserito non è disponibile a ricevere richieste sulla porta specificata.

<img src="./image/client/wrongAddress.png">



<h4 id="c3">Formato indirizzo IP errato</h4>

Comportamento nel caso in cui sia stato inserito un indirizzo IP nel formato errato.

<img src="./image/client/wrongAddressFormat.png">



<h4 id="c4">IP e porta nulli</h4>

Comportamento del programma nel caso non vengano immessi valori nei campi IP e Port per la connessione.

<img src="./image/client/nullIPandPort.png">



<h4 id="c5">Numero porta errato</h4>

Comportamento nel caso in cui venga specificato un numero di porta non corrispondente al servizio richiesto.

<img src="./image/client/wrongPort.png">



<h4 id="c6">Nome tabella database corretto</h4>

Caso in cui venga inserito il nome di una tabella presente nel database MapDb.

<img src="./image/client/correctTableName.png">



<h4 id="c7">Nome tabella database errato</h4>

Comportamento nel caso in cui il nome della tabella specificato non sia presente nel database MapDb.

<img src="./image/client/wrongTableName.png">



<h4 id="c8">Opzione menu corretta (1)</h4>

Caso in cui venga scelta l'opzione 1 nel menu di selezione scelta.

<img src="./image/client/correctOption1.png">



<h4 id="c9">Nome file caricamento corretto</h4>

Caso in cui venga inserito il nome di un file, per il caricamento del dendrogramma, presente sul server.

<img src="./image/client/correctFileName.png">



<h4 id="c10">Nome file caricamento errato</h4>

Comportamento nel caso venga inserito il nome di un file, per il caricamento del dendrogramma, non presente sul server
a cui ci si è collegati.

<img src="./image/client/wrongFileName.png">



<h4 id="c11">Opzione menu corretta (2)</h4>

Caso in cui venga scelta l'opzione 2 nel menu di selezione scelta.

<img src="./image/client/correctOption2.png">



<h4 id="c12">Profondità dendrogramma valida</h4>

Caso in cui venga inserita una profondità del dendrogramma valida.

<img src="./image/client/validDendrogramDepth.png">



<h4 id="c13">Profondità dendrogramma negativa</h4>

Comportamento nel caso in cui sia stata inserita una profondità del dendrogramma negativa.

<img src="./image/client/negativeDendrogramDepth.png">



<h4 id="c14">Profondità dendrogramma non valida</h4>

Comportamento nel caso in cui sia stata immessa una profondità del dendrogramma non valida (che eccede il numero di 
elementi presenti nella tabella del database precedentemente scelta)

<img src="./image/client/invalidDendrogramDepth.png">



<h4 id="c15">Opzione menu distanza non valida</h4>

Comportamento nel caso in cui venga inserito un numero, per effettuare la scelta del metodo di aggragazione da 
utilizzare, che non è presente tra le opzioni del menu. 

<img src="./image/client/wrongDistanceOption.png">



<h4 id="c16">Nome file salvataggio corretto</h4>

Caso in cui venga inserito un nome corretto per il file di salvataggio.

<img src="./image/client/correctSaveFileName.png">



<h4 id="c17">Opzione menu non valida</h4>

Comportamento nel caso in cui venga inserito un numero, per la selezione dell'azione da eseguire, non presente tra 
le opzioni del menu.

<img src="./image/client/wrongMenuOption.png">

---

<h2 id="javadoc">JavaDoc</h2>

- [JavaDoc Server](./Server/javadoc/index.html)
- [JavaDoc Client](./Client/javadoc/index.html)

---

<h2 id="diagramma">Diagramma delle classi</h2>

<h3 id="diagramma-server">Server</h3>

[ <img src="image/serverUML.png"> ](./image/serverUML.png)

<h3 id="diagramma-client">Client</h3>

[ <img src="image/clientUML.png"> ](./image/clientUML.png)
