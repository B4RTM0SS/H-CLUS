package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Contains information about table structure and information about the columns in that table.
 */
public class TableSchema {

    /**
     * Manages access to the database containing the table, whose information we want.
     */
    private DbAccess db;

    /**
     * Contains information about a table single column.
     */
    public class Column{

        /**
         * Name of the column.
         */
        private String name;

        /**
         * Data type contained in this column.
         */
        private String type;

        /**
         * Constructs a <code>Column</code> object, using the name and data type argument.
         *
         * @param name name of this column in the table
         *
         * @param type type of data stored in this column in the table
         */
        Column(String name,String type){
            this.name=name;
            this.type=type;
        }

        /**
         * Return a string representation of the column.
         *
         * @return a string representation of the column
         */
        public String toString(){
            return name+":"+type;
        }
    }

    /**
     * Contains metadata of all columns contained in a database table.
     */
    private List<Column> tableSchema = new ArrayList<Column>();

    /**
     * Constructs a new <code>TableSchema</code> instance for the specified table of the database.
     *
     * @param db the database containing the table.
     *
     * @param tableName the dable from which to construct the schema.
     *
     * @throws SQLException if there's an error formulating query.
     *
     * @throws DatabaseConnectionException if the database connection fails.
     */
    public TableSchema(DbAccess db, String tableName) throws SQLException, DatabaseConnectionException{
        this.db=db;
        HashMap<String,String> mapSQL_JAVATypes=new HashMap<String, String>();
        //http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html
        mapSQL_JAVATypes.put("CHAR","string");
        mapSQL_JAVATypes.put("VARCHAR","string");
        mapSQL_JAVATypes.put("LONGVARCHAR","string");
        mapSQL_JAVATypes.put("BIT","string");
        mapSQL_JAVATypes.put("SHORT","number");
        mapSQL_JAVATypes.put("INT","number");
        mapSQL_JAVATypes.put("LONG","number");
        mapSQL_JAVATypes.put("FLOAT","number");
        mapSQL_JAVATypes.put("DOUBLE","number");


        Connection con=db.getConnection();
        DatabaseMetaData meta = con.getMetaData();
        ResultSet res = meta.getColumns(null, null, tableName, null);

        while (res.next()) {

            if(mapSQL_JAVATypes.containsKey(res.getString("TYPE_NAME")))
                tableSchema.add(new Column(
                        res.getString("COLUMN_NAME"),
                        mapSQL_JAVATypes.get(res.getString("TYPE_NAME")))
                );

        }

        res.close();

    }

    /**
     * Gets the number of column in this <code>TableSchema</code>.
     *
     * @return the number of attributes in the <code>TableSchema</code>.
     */
    public int getNumberOfAttributes(){
        return tableSchema.size();
    }

}




