package data;

import database.DbAccess;
import database.EmptySetException;
import database.MissingNumberException;
import database.TableData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data is a class that allows to store multiple <code>Example</code> instances. This class enables to create a data set of examples.
 *
 * <p>A Data object can be used to generate a matrix of distances between all examples contained in it, using the method <code>distance</code>.</p>
 */
public class Data {

    /**
     * Examples of the data set. Each position of the array represent a different entity.
     */
    private List<Example> data = new ArrayList<>();

    /**
     * Constructs a dataset of examples, loading them from a table named as the string argument, from a database.
     *
     * @param tableName name of the database table containing the examples
     *
     * @throws NoDataException if the examples cannot be loaded from the specified table, due to an error during the
     * process
     */
    public Data(String tableName) throws NoDataException {
        DbAccess db = new DbAccess ();
        TableData tabDat = new TableData (db);
        try {
            data = tabDat.getDistinctTransactions(tableName);
        } catch (SQLException | EmptySetException | MissingNumberException e) {
            throw new NoDataException(e.getMessage());
        }
        try {
            db.closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the number of examples in <code>Data</code>.
     *
     * @return how many examples are actually stored in the data set.
     */
    public int getNumberOfExamples() {
        return data.size();
    }

    /**
     * Gets the <code>Example</code> in the specified position from <code>Data</code>.
     *
     * @param exampleIndex the position in which to look for the <code>Example</code>.
     *
     * @return the <code>Example</code> in the specified position of the data set.
     */
    public Example getExample(int exampleIndex) {
        return data.get(exampleIndex);
    }

    /**
     * Returns a string representation of <code>Data</code>.
     *
     * <p>The string represent all the examples in this <code>Data</code>.
     *
     * @return a string representation of <code>Data</code>.
     */
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Example example : data) {
            output.append(data.indexOf(example)).append(":").append(example).append("\n");
        }
        return output.toString();
    }

}
