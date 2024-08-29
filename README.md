# H-CLUS

---
## INDICE


+ [Diagramma delle classi](#diagramma)

  - [Server](#diagramma-server)
  - [Client](#diagramma-client)

+ [JavaDoc](#javadoc)

+ [Guida di installazione](#installazione)

+ [Guida utente](#utente)

    - [Server](#guida-server)
    - [Client](#guida-client)

---

<h2 id="diagramma">Diagramma delle classi</h2>

<h3 id="diagramma-server">Server</h3>
[ <img src="image/serverUML.png"> ](./image/serverUML.png)

<h3 id="diagramma-client">Client</h3>
[ <img src="image/clientUML.png"> ](./image/clientUML.png)


---

<h2 id="javadoc">JavaDoc</h2>

- [JavaDoc Server](./Server/javadoc/index.html)
- [JavaDoc Client](./Client/javadoc/index.html)


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
1. Nella cartella principale del progetto, trova il file `Server.bat` ed eseguilo con un doppio clic, come di seguito 
mostrato.
<img src="image/clic.gif">  
  In alternativa:
   + apri il prompt dei comandi
     <img src="image/cmd.png">
   + usa il comando `cd` seguito dal percorso della directory del progetto  
     <img src="image/cd.png">
   + esegui il file `Server.bat`
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
Eseguire il file `Client.bat` presente nella cartella principale del progetto.
Per eseguirlo è possibile effettuare doppio clic su di esso o, in alternativa, aprire il terminale nella directory del 
progetto e digitare `Client.bat`, premendo infine il tasto "Invio". 
 
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