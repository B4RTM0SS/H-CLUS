package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;

import clustering.DifferentDimensionException;
import clustering.InvalidDepthException;
import clustering.NegativeDepthException;
import data.Data;
import data.InvalidSizeException;
import data.NoDataException;

import clustering.HierarchicalClusterMiner;

import distance.ClusterDistance;
import distance.AverageLinkDistance;
import distance.SingleLinkDistance;


/**
 * Manages the service offered to a client, connected to the server on a specified port.
 */
class ServerOneClient implements Runnable {

    /**
     * Endpoint for communication between the service server machine and the client machine.
     */
    private Socket socket;

    /**
     *  Stream manipulator which deserializes primitive data and objects previously written using an ObjectOutputStream.
     */
    private ObjectInputStream objIn;

    /**
     * Writes serialized primitive data types and objects to an OutputStream.
     */
    private ObjectOutputStream objOut;

    /**
     * Dataset of examples.
     * <p>Stores multiple <code>Example</code> instances.</p>
     */
    private Data data;

    /**
     * Allows to cluster examples in the dataset.
     */
    private HierarchicalClusterMiner clustering;

    /**
     * Constructs a new <code>ServerOneClient</code> object, with the socket argument.
     *
     * @param s an endpoint for communication between a server and a client
     *
     * @throws IOException if an I/O error occurs getting I/O stream.
     */
    ServerOneClient (Socket s) throws IOException {
        socket = s;
        objIn = new ObjectInputStream(s.getInputStream());
        objOut = new ObjectOutputStream(s.getOutputStream());
    }

    /**
     * Runs the server service for the client connected with the socket.
     */
    public void run () {
        boolean end = false;

        try {

            do {

                switch ((int) objIn.readObject()) {

                    case 0:
                        loadDataset();
                        break;

                    case 1:
                        createDendrogram();
                        end = true;
                        break;

                    case 2:
                        loadDendrogram();
                        end = true;
                        break;

                }

            } while (!end);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                objIn.close();
                objOut.close();
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Loads the data set from the database table name received from the input stream.
     * Sends back, to the output stream, the string "OK" if the loading is successful, the error message string otherwise.
     *
     * @throws IOException if an I/O operation failed or got interrupted.
     *
     * @throws ClassNotFoundException if no definition for the class with the specified name could be found.
     */
    private void loadDataset () throws IOException, ClassNotFoundException {
        String tableName = (String) objIn.readObject();
        try {
            data = new Data(tableName);
            objOut.writeObject("OK");
        } catch (NoDataException e) {
            objOut.writeObject(e.getMessage());
        }
    }

    /**
     * Sends a new dendrogram, with the parameters received from the input stream, to the output stream.
     *
     * @throws IOException if an I/O operation failed or got interrupted.
     *
     * @throws ClassNotFoundException if no definition for the class with the specified name could be found.
     */
    private void createDendrogram () throws IOException, ClassNotFoundException {
        try {
            clustering = new HierarchicalClusterMiner((int) objIn.readObject());
            ClusterDistance distance = switch ((int) objIn.readObject()) {
                case 1 -> new SingleLinkDistance();
                case 2 -> new AverageLinkDistance();
                default -> null;
            };
            clustering.mine(data, distance);
            objOut.writeObject("OK");
            objOut.writeObject(clustering.toString(data));
            clustering.salva((String) objIn.readObject());
        } catch (NegativeDepthException | InvalidSizeException | DifferentDimensionException |
                 InvalidDepthException e) {
            objOut.writeObject(e.getMessage());
        }
    }

    /**
     * Sends the dendrogram loaded on the server to the output stream.
     * The dendrogram is loaded from a file, which name is received from the input stream.
     *
     * @throws IOException if an I/O operation failed or got interrupted.
     */
    private void loadDendrogram () throws IOException {
        try {
            clustering = HierarchicalClusterMiner.loadHierarchicalClusterMiner((String) objIn.readObject());
            objOut.writeObject("OK");
            objOut.writeObject(clustering.toString(data));
        } catch (ClassNotFoundException | IOException | DifferentDimensionException e) {
            objOut.writeObject(e.getMessage());
        }
    }

}
