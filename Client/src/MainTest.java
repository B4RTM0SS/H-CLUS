import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * MainTest is the class that allows to test the client side of the clustering project.
 */
public class MainTest {

    /**
     * The output stream for the objects send to the server.
     */
    private ObjectOutputStream out;

    /**
     * The input stream for the objects received from the server.
     */
    private ObjectInputStream in ; // stream con richieste del client

    /**
     * Initializes a new test.
     *
     * <p>Starts a connection with a server on the specified ip and port.</p>
     *
     * @param ip the ip location of the server.
     *
     * @param port the port where the server is listening to.
     *
     * @throws IOException if an I/O operation failed or interrupted.
     */
    private MainTest (String ip, int port) throws IOException {
        InetAddress addr = InetAddress.getByName(ip); //ip
        System.out.println ("addr = " + addr);
        Socket socket = new Socket (addr, port); //Port
        System.out.println (socket);

        out = new ObjectOutputStream (socket.getOutputStream());
        in = new ObjectInputStream (socket.getInputStream());	// stream con richieste del client
    }

    /**
     * Gets the user choice for the main menu.
     *
     * @return the option chosen by the user.
     */
    private int menu () {
        int answer;
        System.out.println ("Scegli una opzione");
        do {
            System.out.println ("(1) Carica Dendrogramma da File");
            System.out.println ("(2) Apprendi Dendrogramma da Database");
            System.out.print ("Risposta:");
            answer = Keyboard.readInt();
        } while(answer <= 0 || answer > 2);
        return answer;
    }

    /**
     * Specifies the name of the database table from which to load the data set on the server.
     *
     * @throws IOException if an I/O operation failed or interrupted.
     *
     * @throws ClassNotFoundException if no definition for the class with the specified name could be found.
     */
    private void loadDataOnServer () throws IOException, ClassNotFoundException {
        boolean flag = false;
        do {
            System.out.println ("Nome tabella:");
            String tableName = Keyboard.readString();
            out.writeObject (0);
            out.writeObject (tableName);
            String risposta = (String) (in.readObject());
            if ( risposta.equals("OK") )
                flag  =true;
            else System.out.println (risposta);
        } while (flag == false);
    }

    /**
     * Specifies the name of the file on the server from which to load the dendrogram.
     *
     * @throws IOException if an I/O operation failed or interrupted.
     *
     * @throws ClassNotFoundException if no definition for the class with the specified name could be found.
     */
    private void loadDendrogramFromFileOnServer () throws IOException, ClassNotFoundException {
        System.out.println ("Inserire il nome dell'archivio (comprensivo di estensione):");
        String fileName = Keyboard.readString();

        out.writeObject (2);
        out.writeObject (fileName);
        String risposta = (String) (in.readObject());
        if ( risposta.equals("OK") )
            System.out.println (in.readObject()); // stampo il dendrogramma che il server mi sta inviando
        else
            System.out.println (risposta); // stampo il messaggio di errore
    }

    /**
     * Starts a new mining session on the server.
     *
     * @throws IOException if an I/O operation failed or interrupted.
     *
     * @throws ClassNotFoundException if no definition for the class with the specified name could be found.
     */
    private void mineDendrogramOnServer() throws IOException, ClassNotFoundException {
        out.writeObject (1);
        System.out.println ("Introdurre la profondita' del dendrogramma");
        int depth = Keyboard.readInt();
        out.writeObject (depth);
        int dType = -1;
        do {
            System.out.println ("Distanza: single-link (1), average-link (2):");
            dType = Keyboard.readInt();
        } while (dType <= 0 || dType > 2);
        out.writeObject (dType);

        String risposta= (String) (in.readObject());
        if ( risposta.equals("OK") ) {
            System.out.println (in.readObject()); // stampo il dendrogramma che il server mi sta inviando
            System.out.println ("Inserire il nome dell'archivio (comprensivo di estensione):");
            String fileName = Keyboard.readString();
            out.writeObject (fileName);
        } else
            System.out.println(risposta); // stampo il messaggio di errore
    }

    /**
     * Runs the test on the specified ip and port.
     *
     * @param args the first argument refers to the ip of the server,
     *             the second argument specifies the port of the server.
     */
    public static void main (String[] args) {
        String ip = args[0];
        int port = new Integer(args[1]).intValue();
        MainTest main = null;
        try {
            main = new MainTest (ip,port);

            main.loadDataOnServer();
            int scelta = main.menu();
            if (scelta == 1)
                main.loadDendrogramFromFileOnServer();
            else
                main.mineDendrogramOnServer();
        } catch (IOException | ClassNotFoundException e){
            System.out.println(e);
            return;
        }
    }

}

