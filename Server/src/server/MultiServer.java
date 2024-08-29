package server;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Manages client connections on a specific port, where a service is active.
 */
public class MultiServer {

    /**
     * Port on which the server is listening.
     */
    private final int port;

    /**
     * Constructs a new <code>MultiServer</code> object, with the port argument.
     *
     * @param port port on which a service is active
     */
    public MultiServer (int port) {
        this.port = port;
    }

    /**
     * Start the server service on the specific port and waits for client connections.
     */
    public void runMultiServer () {
        try (ServerSocket sS = new ServerSocket(port)) {
            while (true) {
                Socket s = sS.accept();
                try {
                    ServerOneClient sOC = new ServerOneClient(s);
                    Thread t = new Thread(sOC);
                    t.start();
                } catch (IOException e) {
                    s.close();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
