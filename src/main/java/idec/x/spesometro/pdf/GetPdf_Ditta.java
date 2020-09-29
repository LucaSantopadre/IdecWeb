/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.pdf;

import idec.x.spesometro.conn.Connessione;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author Luca
 */
public class GetPdf_Ditta {
        public static HashMap stampaDatiDitta(String tipo_libro,String db, String percorso_file, LocalDate data_1, LocalDate data_2) throws Exception {
        try {
            String query = "SELECT\n"
                    + db + ".\"2018az\".ragione_sociale,\n"
                    + db + ".\"2018az\".\"Cognome\",\n"
                    + db + ".\"2018az\".\"Nome\",\n"
                    + db + ".\"2018az\".\"CodiceFiscale\",\n"
                    + db + ".\"2018az\".\"PartitaIva\"\n"
                    + "FROM\n"
                    + db + ".\"2018az\"";

            Connection conn = Connessione.getConnection("public");
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            HashMap map = new HashMap();
            while (rs.next()) {
                for (int i = 1; i < 6; i++) {
                    String colLabel = rs.getMetaData().getColumnLabel(i);
                    String colType = rs.getMetaData().getColumnTypeName(i);

                    if (colType.equals("numeric") || colType.equals("int4")|| colType.equals("int8")) {

                        map.put(colLabel, rs.getBigDecimal(i));

                    } else {
                        map.put(colLabel, rs.getString(i));
                    }

                }
            }
            return map;
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return null;
    }
}
