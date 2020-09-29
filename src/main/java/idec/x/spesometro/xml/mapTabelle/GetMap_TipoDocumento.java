/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.xml.mapTabelle;

import static idec.x.spesometro.AvviaSpesometro.data_1;
import static idec.x.spesometro.AvviaSpesometro.data_2;
import idec.x.spesometro.conn.Connessione;
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
public class GetMap_TipoDocumento {

    /**
     * NECESSARIO PER BLOCCO CESSIONARIO COMMITTENTE DTE :
     *
     * da tabella reg_doc :
     *
     * tipo_doc data_doc doc_n
     *
     */
    public static List<HashMap> getRegistrazioni(String tipo_libro, String db, HashMap hashMap) {
        try {
            String query = "SELECT\n"
                    + db + ".reg03_rigo.reg01_num_reg_id,\n"
                    + db + ".reg01.data_reg,\n"
                    + db + ".reg03_rigo.importo_conto\n"
                    + "FROM\n"
                    + db + ".reg03_rigo\n"
                    + "INNER JOIN " + db + ".reg01 ON " + db + ".reg03_rigo.reg01_num_reg_id = " + db + ".reg01.num_reg_id "
                    + "WHERE\n"
                    + db + ".reg01.data_reg BETWEEN '" + data_1 + "' AND '" + data_2 + "' AND \n"
                    + db + ".reg03_rigo.reg03_rigo_reg_id = '0' AND\n"
                    + db + ".reg03_rigo.reg03_subrigo_reg_id = '0' AND\n"
                    + db + ".reg03_rigo.conto = ? AND "
                    + db + ".reg03_rigo.vendor = ? AND "
                    + db + ".reg01.reparto =  ? \n";

            Connection conn = Connessione.getConnection(db);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, hashMap.get("conto").toString());
            preparedStmt.setString(2, hashMap.get("vendor").toString());
            preparedStmt.setString(3, tipo_libro);
            ResultSet rs = preparedStmt.executeQuery();
            HashMap map;
            List<HashMap> listaMap = new ArrayList<>();
            int progressivo_doc = 1;
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
                map.put("progressivo_doc", progressivo_doc);
                getTipoDocumento("2", db, map);
                get039li( map,db);
                listaMap.add(map);

                progressivo_doc++;
            }

            return listaMap;

        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return null;
    }

    private static void getTipoDocumento(String tipo_libro, String db, HashMap hashMap) {
        try {
            String query = "SELECT\n"
                    + db + ".reg_doc.tipo_doc,\n"
                    + db + ".reg_doc.data_doc,\n"
                    + db + ".reg_doc.num_doc,\n"
                    + db + ".reg_doc.reg03_n_reg_id,\n"
                    + db + ".reg_doc.reg03_rigo_id,\n"
                    + db + ".reg_doc.reg03_subrigo_id\n"
                    + "FROM\n"
                    + db + ".reg_doc\n"
                    + "WHERE\n"
                    + db + ".reg_doc.reg03_n_reg_id = ?";

            Connection conn = Connessione.getConnection(db);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setBigDecimal(1, (BigDecimal) hashMap.get("reg01_num_reg_id"));
            ResultSet rs = preparedStmt.executeQuery();
            List<HashMap> listaMap = new ArrayList<>();
            while (rs.next()) {
                for (int i = 1; i < 7; i++) {
                    //System.out.println(rs.getMetaData().getColumnLabel(i) + " : " + rs.getString(i));
                    String colLabel = rs.getMetaData().getColumnLabel(i);
                    String colType = rs.getMetaData().getColumnTypeName(i);

                    if (colType.equals("numeric") || colType.equals("int4") || colType.equals("int8")) {

                        hashMap.put(colLabel, rs.getBigDecimal(i));

                    } else {
                        hashMap.put(colLabel, rs.getString(i));
                    }
                }
            }
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }

    private static void get039li(HashMap map, String db) {
        Connection conn = Connessione.getConnection(db);
        try {
            String query = "SELECT\n"
                    + "public.\"039li\".op_des\n"
                    + "FROM\n"
                    + "public.\"039li\"\n"
                    + "WHERE\n"
                    + "public.\"039li\".tipo_documento_iva = ? \n";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, map.get("tipo_doc").toString());
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                for (int i = 1; i < 2; i++) {
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
