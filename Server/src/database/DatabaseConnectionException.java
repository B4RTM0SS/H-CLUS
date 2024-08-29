package database;

/**
 * Thrown if the database connection fails or if an error occurs while closing a database connection.
 */
class DatabaseConnectionException extends Exception {

    /**
     * Construct a <code>DatabaseConnectionException</code> object, with a default string as exception message.
     */
    DatabaseConnectionException () {
        super("Database connection failed.");
    }

    /**
     * Construct a <code>DatabaseConnectionException</code> object, with a given string argument as exception message.
     * @param str a description of the exception
     */
    DatabaseConnectionException (String str) {
        super(str);
    }

}