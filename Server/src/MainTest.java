import server.MultiServer;

/**
 * Allows to test the server side of the clustering project
 */
public class MainTest {

    /**
     * Runs the server on the specified port, if correct.
     *
     * @param args the first argument refer to the port on which start the server service
     */
    public static void main (String[] args) {

        try {

            int port = Integer.parseInt(args[0]);
            MultiServer mS = new MultiServer(port);
            mS.runMultiServer();

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("No port value entered.");

        } catch (NumberFormatException e) {

            System.out.println("Invalid port number format.");

        } catch (IllegalArgumentException e) {

            System.out.println("Port parameter outside the specified range of valid port values (between 0 and " +
                    "65535, inclusive).");
        }

    }

}
