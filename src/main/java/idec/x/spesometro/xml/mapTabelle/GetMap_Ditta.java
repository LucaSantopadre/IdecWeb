/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.xml.mapTabelle;

import idec.x.spesometro.conn.Connessione;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

// *** import dati ditta da tabelle ditta01, ditta02,017co
public class GetMap_Ditta {

    public static HashMap getXmlDatiDitta(String db) throws Exception {
        try {
            String query = "SELECT\n"
                    + db + ".ditta01.ragione_sociale,\n"
                    + db + ".ditta01.\"Cognome\",\n"
                    + db + ".ditta01.\"Nome\",\n"
                    + db + ".ditta01.\"CodiceFiscale\",\n"
                    + db + ".ditta01.\"PartitaIva\",\n"
                    + "\n"
                    + db + ".ditta02.residenza_via,\n"
                    + db + ".ditta02.residenza_via_numero,\n"
                    + "\n"
                    + "\"public\".\"017co\".cap,\n"
                    + "\"public\".\"017co\".descrizione_comune,\n"
                    + "\"public\".\"017co\".sigla_provincia\n"
                    + "FROM\n"
                    + db + ".ditta01,\n"
                    + db + ".ditta02,\n"
                    + "\"public\".\"017co\"\n"
                    + "\n"
                    + "WHERE \n"
                    + db + ".ditta02.residenza_comune = \"public\".\"017co\".id_comune";

            Connection conn = Connessione.getConnection("public");
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            //System.out.println(preparedStmt);
            ResultSet rs = preparedStmt.executeQuery();
            HashMap map = new HashMap();
            while (rs.next()) {
                System.out.println("****** DITTA *******");

                for (int i = 1; i < 11; i++) {
                    System.out.println(rs.getMetaData().getColumnLabel(i) + " : " + rs.getString(i));
                    if (rs.getMetaData().getColumnLabel(i).equals("IMPORTO_EURO_DARE")
                            || rs.getMetaData().getColumnLabel(i).equals("IMPORTO_EURO_AVERE")
                            || rs.getMetaData().getColumnLabel(i).equals("tot_cliente")) {

                        map.put(rs.getMetaData().getColumnLabel(i), rs.getBigDecimal(i));

                    } else {
                        map.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
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
