/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.utils;

import idec.x.spesometro.conn.Connessione;
import static idec.x.spesometro.conn.Connessione.DITTA;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Luca
 */
public class Luca005CreaSchemaPostgresDaFile {

    public static void luca004CreaTab_CreaSchema(String schema) {
        try {
            String query = "CREATE SCHEMA IF NOT EXISTS " + schema;

            Connection conn = Connessione.getConnection(DITTA);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            System.out.println(preparedStmt);
            ResultSet rs = preparedStmt.executeQuery();

        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }

    public static void leggiCodiciDitteDaFile() {
        try {
            File file = new File("C:\\Users\\Luca\\Desktop\\MysqlElencoDitte.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");

                //per ogni ditta creo uno schema Postgres richiamando il metodo
                luca004CreaTab_CreaSchema(line);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
