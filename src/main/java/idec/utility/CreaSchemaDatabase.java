/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.utility;

import static idec.utility.Connessione.SCHEMA_PUB;
import static idec.utility.Connessione.getConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Luca
 */
public class CreaSchemaDatabase {

    // *** metodo static che crea lo schema
    public static void creaSchema(String schemaName) throws SQLException {
        Connection conn = getConnection(SCHEMA_PUB);
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("CREATE SCHEMA " + schemaName);
    }
    // FINE  metodo crea schema
}
