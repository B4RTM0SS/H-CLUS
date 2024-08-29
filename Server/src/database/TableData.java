package database;

import data.Example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains and manages the data of the tables resulting from the queries made to the database.
 */
public class TableData {

    /**
     * Access to the database from which the data, contained in the table, is obtained.
     */
    private DbAccess db;

    /**
     * Constructs a <code>TableData</code> object, using the argument, a <code>DbAccess</code> instance, as the database
     * access.
     *
     * @param db database access
     */
    public TableData (DbAccess db) {
        this.db = db;
    }

    /**
     * Returns a list of <code>Example</code> instances, whose values are stored in a database table, retrieved with a
     * query.
     *
     * @param table name of the database table containing the values of each examples
     *
     * @return a list of examples
     *
     * @throws SQLException if there's an error formulating query.
     *
     * @throws EmptySetException if the query resulting table is empty.
     *
     * @throws MissingNumberException if there's a non-numeric data type column in the query resulting table.
     */
    public List<Example> getDistinctTransactions(String table) throws SQLException, EmptySetException, MissingNumberException {
        List<Example> list = new ArrayList<>();

        try {
            TableSchema tabS = new TableSchema(db, table);
            int numAtt = tabS.getNumberOfAttributes();

            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM ";
            ResultSet res = stmt.executeQuery(query + table + ";");

            res.first();
            do {
                Example xmp = new Example();
                for (int i = 1; i <= numAtt; i++)
                    try {
                        xmp.add(res.getDouble(i));
                    } catch (SQLDataException e) {
                        throw new MissingNumberException();
                    } catch (SQLException e) {
                        throw new EmptySetException();
                    }
                list.add(xmp);
            } while (res.next());

            res.close();
            stmt.close();
            conn.close();

        } catch (DatabaseConnectionException e) {
            throw new SQLException(e.getMessage());
        }

        return list;
    }

}
