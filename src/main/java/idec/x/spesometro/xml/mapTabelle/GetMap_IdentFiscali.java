/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.xml.mapTabelle;

import static idec.x.spesometro.AvviaSpesometro.data_1;
import static idec.x.spesometro.AvviaSpesometro.data_2;
import idec.x.spesometro.conn.Connessione;
import static idec.x.spesometro.utils.Luca000ControllaDati.controllaDatiAnagrafici;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Luca
 */
public class GetMap_IdentFiscali {

    /**
     * NECESSARIO PER BLOCCO CESSIONARIO COMMITTENTE DTE :
     *
     * serve conto e codice da prendere reg03_rigo
     *
     * part_iva-2048pc; fisc_cod-2048pc; ragione_sociale-2048pc; nome-2048pc;
     * cognome-2048pc;
     *
     * via-2053pc; provincia-2053pc;
     *
     * cap-017co; descrizione_comune-017co; nazione-'IT';
     *
     */
    public static List<HashMap> getIdentificativiFiscali(String tipo_libro, String db) {
        try {
            String query = " SELECT\n"
                    + db + ".reg01.reparto,\n"
                    + db + ".reg03_rigo.conto,\n"
                    + db + ".reg03_rigo.vendor\n"
                    + "FROM\n"
                    + db + ".reg01\n"
                    + "INNER JOIN " + db + " .reg03_rigo ON " + db + " .reg03_rigo.reg01_num_reg_id = " + db + " .reg01.num_reg_id AND " + db + " .reg03_rigo.reg01_reparto = " + db + " .reg01.reparto\n"
                    + "WHERE\n"
                    + db + ".reg01.data_reg BETWEEN '" + data_1 + "' AND '" + data_2 + "' AND \n"
                    + db + ".reg01.reparto = ? AND \n"
                    + db + ".reg03_rigo.vendor != '0' AND \n"
                    + db + ".reg03_rigo.vendor != '' AND \n"
                    + db + ".reg03_rigo.reg03_rigo_reg_id = 0  \n"
                    + "GROUP BY vendor,reparto,conto "
                    + "ORDER BY vendor";

            Connection conn = Connessione.getConnection(db);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, tipo_libro);
            ResultSet rs = preparedStmt.executeQuery();
            HashMap map;
            List<HashMap> listaMap = new ArrayList<>();
            while (rs.next()) {
                map = new HashMap();
                for (int i = 1; i < 4; i++) {
                    //System.out.println(rs.getMetaData().getColumnLabel(i) + " : " + rs.getString(i));
                    String colLabel = rs.getMetaData().getColumnLabel(i);
                    String colType = rs.getMetaData().getColumnTypeName(i);

                    if (colType.equals("numeric") || colType.equals("int4") || colType.equals("int8")) {

                        map.put(colLabel, rs.getBigDecimal(i));

                    } else {
                        map.put(colLabel, rs.getString(i));
                    }
                }
                listaMap.add(map);
            }

            getVendor01_base(listaMap, db);
            getVendor02_sede(listaMap, db);
            get017co(listaMap, db);

            for (HashMap mapVendor : listaMap) {
                // controllo sui dati anagrafici e generazione errori
                mapVendor.put("errori", "");
                controllaDatiAnagrafici(mapVendor);
            }

            return listaMap;

        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return null;
    }

    private static void getVendor01_base(List<HashMap> listaMap, String db) {
        Connection conn = Connessione.getConnection(db);
        for (HashMap map : listaMap) {
            try {
                String query = "SELECT * \n"
                        + "FROM\n"
                        + db + ".vendor01_base\n"
                        + "WHERE\n"
                        + db + ".vendor01_base.vendor01_conto = ? AND\n"
                        + db + ".vendor01_base.vendor01_codice = ? ";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, map.get("conto").toString());
                preparedStmt.setString(2, map.get("vendor").toString());
                ResultSet rs = preparedStmt.executeQuery();
                while (rs.next()) {
                    for (int i = 1; i < 9; i++) {
                        //System.out.println(rs.getMetaData().getColumnLabel(i) + " : " + rs.getString(i));
                        String colLabel = rs.getMetaData().getColumnLabel(i);
                        String colType = rs.getMetaData().getColumnTypeName(i);

                        if (colType.equals("numeric") || colType.equals("int4")) {

                            map.put(colLabel, rs.getBigDecimal(i));

                        } else {
                            map.put(colLabel, rs.getString(i));
                        }
                    }
                }

            } catch (SQLException sqle) {
                System.out.println(sqle);
            }
        }
    }

    private static void getVendor02_sede(List<HashMap> listaMap, String db) {
        Connection conn = Connessione.getConnection(db);
        for (HashMap map : listaMap) {
           
            map.put("vendor02_via", "");
            map.put("vendor02_num_civico", "");
            map.put("vendor02_id_comune", BigDecimal.ZERO);

            try {
                String query = "SELECT *\n"
                        + "FROM\n"
                        + db + ".vendor02_sede\n"
                        + "WHERE\n"
                        + db + ".vendor02_sede.vendor02_conto = ? AND\n"
                        + db + ".vendor02_sede.vendor02_codice = ? ";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, map.get("conto").toString());
                preparedStmt.setString(2, map.get("vendor").toString());
                ResultSet rs = preparedStmt.executeQuery();
                while (rs.next()) {
                    for (int i = 1; i < 8; i++) {
                        //System.out.println(rs.getMetaData().getColumnLabel(i) + " : " + rs.getString(i));
                        String colLabel = rs.getMetaData().getColumnLabel(i);
                        String colType = rs.getMetaData().getColumnTypeName(i);

                        if (colType.equals("numeric") || colType.equals("int4")) {

                            map.put(colLabel, rs.getBigDecimal(i));

                        } else {
                            map.put(colLabel, rs.getString(i));
                        }
                    }
                }

            } catch (SQLException sqle) {
                System.out.println(sqle);
            }
        }
    }

    private static void get017co(List<HashMap> listaMap, String db) {
        Connection conn = Connessione.getConnection(db);
        for (HashMap map : listaMap) {
           
            map.put("descrizione_comune", "");
            map.put("cap", "");
            map.put("sigla_provincia", "");

            try {
                String query = "SELECT\n"
                        + "public.\"017co\".cap,\n"
                        + "public.\"017co\".descrizione_comune,\n"
                        + "public.\"017co\".sigla_provincia\n"
                        + "FROM\n"
                        + "public.\"017co\"\n"
                        + "WHERE\n"
                        + "public.\"017co\".id_comune = ? \n";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setBigDecimal(1, (BigDecimal) map.get("vendor02_017co_id_comune"));
                ResultSet rs = preparedStmt.executeQuery();
                while (rs.next()) {
                    for (int i = 1; i < 4; i++) {
                        //System.out.println(rs.getMetaData().getColumnLabel(i) + " : " + rs.getString(i));
                        String colLabel = rs.getMetaData().getColumnLabel(i);
                        String colType = rs.getMetaData().getColumnTypeName(i);

                        if (colType.equals("numeric") || colType.equals("int4")) {
                            map.put(colLabel, rs.getBigDecimal(i));

                        } else {
                            map.put(colLabel, rs.getString(i));
                        }
                    }
                }

            } catch (SQLException sqle) {
                System.out.println(sqle);
            }
        }
    }

}
