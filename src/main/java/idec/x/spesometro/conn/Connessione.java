/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class Connessione {
    
    public static String DITTA = "fi001";


    public static final String DATABASE = "idec_db";
    public static final String SCHEMA_PUB = "public";
    public static final String USER = "postgres";
    public static final String PASSWORD = "meil21";
    public static final String HOST = "localhost";

    public static Connection cnn = null;
    private static final int CONNECTION_TIMEOUT_MINUTES = 1;

    private static long timeMillisCurrentConnection = 0;
    private static long timeMillisLastConnection = 0;
    private static long timeMillisCurrentMinusLast = 0;

    //passare come parametro il database a cui collegarsi
    public static Connection getConnection(String schema) {
        //***************************************************************
        //Imposta timeMillisLastConnection e timeMillisCurrentConnection
        if (timeMillisLastConnection == 0) {
            //Caso : prima connessione
            timeMillisLastConnection = System.currentTimeMillis();
            timeMillisCurrentConnection = timeMillisLastConnection;
        } else {
            //Caso : connessione successiva alla prima
            timeMillisLastConnection = timeMillisCurrentConnection;
            timeMillisCurrentConnection = System.currentTimeMillis();
        }

        //************************************
        //Calcola il tempo intercorso fra la penultima e 
        //l'ultima connessione
        timeMillisCurrentMinusLast = timeMillisCurrentConnection - timeMillisLastConnection;

        //Crea la connessione SOLO se non è stata gia' creata in precedenza
        if (cnn == null) {
            //Caso: La connessione NON esiste.
            //mysql
            //cnn = createNewMysqlConnection(database);
            //postgresql
            cnn = createNewPostgresConnection(schema);
        } else {
            //Caso: La connessione ESISTE	.
            if (isGoodConnection(cnn)) {
                //Caso: La connessione ESISTE ed e' funzionante
                //Non deve si deve fare nulla
            } else {
                //Caso: La connessione ESISTE MA NON e' funzionante
                //  La connessione DEVE essere ricreata.
                //mysql
                //cnn = createNewMysqlConnection(database);
                //postgresql
                cnn = createNewPostgresConnection(schema);
            }

        }

        return cnn;
    }

    /**
     * Crea una connessione jdbc con il database Mysql
     *
     * @return
     */
    private static Connection createNewMysqlConnection(String database) {
        Connection result = null;
        String user = "idecadmin";
        String password = "meil21";
        String host = "localhost";

        //Cartica i driver per Mysql
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            result = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);

            //@TODO Agiungere riga di log , livello INFO
            System.out.println("MySQL - Connessione a dB: " + database + "   OK ! : user " + user);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
        }

        return result;
    }

    //luca crea una connessione POSTGRESQL
    private static Connection createNewPostgresConnection(String schema) {
        Connection result = null;
        //Cartica i driver per Mysql
        try {
            Class.forName("org.postgresql.Driver");

            result = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DATABASE + "?currentSchema=" + schema, USER, PASSWORD);

            //@TODO Agiungere riga di log , livello INFO
            System.out.println("POSTGRESQL - Connessione a dB: " + DATABASE + "/" + schema + "   OK ! : user " + USER);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Errore connessione dB !" + e);
        }

        return result;
    }

    /**
     * Controlla se una connessione a DB funziona correttamente.
     *
     * @param connectioToTest
     * @return
     */
    private static boolean isGoodConnection(Connection connectioToTest) {
        boolean result = false;
        Statement stmt;

        try {

            if (timeMillisCurrentMinusLast > (CONNECTION_TIMEOUT_MINUTES * 60 * 1000)) {
                //CASO Connessione NON utilizzata da più di "CONNECTION_TIMEOUT_MINUTES" minuti.
                //Connessione a rischio timeout.
                //Si esegue un test

                // Get a Statement object
                stmt = connectioToTest.createStatement();

                //Execute a simple command;We do not trap the response
                //We are only wanting a connection test
                result = stmt.execute("show databases;");

                stmt.close();

            } else {
                //CASO Connessione utilizzata recentemente.
                //NON a rischio timeout di connessione
                result = true;
            }

        } catch (SQLException e) {
            result = false;
        }

        return result;
    }

    public static void closeConnection() {
        try {
            cnn.close();
        } catch (SQLException e) {
            System.out.println("CONNECTION CLOSE ERROR");
            // e.printStackTrace();
        }
        cnn = null;
    }

    public static Statement getStatement(String schema) {
        try {
            return getConnection(schema).createStatement();
        } catch (SQLException sqle) {
            return null;
        }
    }
}
