/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.xml.mapTabelle;

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
public class GetMap_RighiDocumento {

    /**
     * NECESSARIO PER BLOCCO CESSIONARIO COMMITTENTE DTE :
     *
     * da tabella reg_03_rigo
     *
     */
    public static List<HashMap> getRighiConto(String tipo_libro, String db, HashMap hashMap) {
        try {
            String query = "SELECT\n"
                    + db + ".reg03_rigo.reg03_rigo_reg_id,"
                    + db + ".reg03_rigo.reg03_subrigo_reg_id,"
                    + db + ".reg03_rigo.reg01_num_reg_id,\n"
                    + db + ".reg03_rigo.importo_conto,\n"
                    + db + ".reg03_rigo.conto,\n"
                    + db + ".reg03_rigo.reg03_rigo_id\n"
                    + "FROM\n"
                    + db + ".reg03_rigo\n"
                    + "WHERE\n"
                    + db + ".reg03_rigo.reg01_num_reg_id = ? AND "
                    + db + ".reg03_rigo.reg03_rigo_reg_id != 0 AND "
                    + db + ".reg03_rigo.conto != 'ER20037' AND "
                    + db + ".reg03_rigo.reg01_reparto = ?";

            Connection conn = Connessione.getConnection(db);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setBigDecimal(1, (BigDecimal) hashMap.get("reg03_n_reg_id"));
            preparedStmt.setString(2, tipo_libro);
            ResultSet rs = preparedStmt.executeQuery();
            HashMap map;
            List<HashMap> listaMap = new ArrayList<>();
            while (rs.next()) {
                map = new HashMap();
                for (int i = 1; i < 7; i++) {
                    //System.out.println(rs.getMetaData().getColumnLabel(i) + " : " + rs.getString(i));
                    String colLabel = rs.getMetaData().getColumnLabel(i);
                    String colType = rs.getMetaData().getColumnTypeName(i);

                    if (colType.equals("numeric") || colType.equals("int4") || colType.equals("int8")) {

                        map.put(colLabel, rs.getBigDecimal(i));

                    } else {
                        map.put(colLabel, rs.getString(i));
                    }
                }

                if(map.get("reg01_num_reg_id").toString().equals("14")){
                    System.out.println(""); 
                }
                
                getRighiIva(db, map);

                if(map.size()>10){
                    listaMap.add(map);
                }
                

            }

            return listaMap;
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return null;
    }

    private static void getRighiIva(String db, HashMap map) {
        try {
            String query = "SELECT\n"
                    + db + ".reg04_iva.iva_perc,\n"
                    + db + ".reg04_iva.iva_imposta,\n"
                    + db + ".reg04_iva.iva_natura,\n"
                    + db + ".reg04_iva.iva_perc_det,\n"
                    + db + ".reg04_iva.iva_importo_det,\n"
                    + db + ".reg04_iva.iva_perc_ded,\n"
                    + db + ".reg04_iva.iva_importo_ded\n"
                    + "FROM\n"
                    + db + ".reg04_iva\n"
                    + "WHERE\n"
                    + db + ".reg04_iva.reg03_rigo_reg_id = ?  AND "
                    + db + ".reg04_iva.reg01_num_reg_id = ?  AND "
                    + db + ".reg04_iva.reg03_rigo_id = ?   ";

            Connection conn = Connessione.getConnection(db);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setBigDecimal(1, (BigDecimal) map.get("reg03_rigo_reg_id"));
            //preparedStmt.setBigDecimal(2, (BigDecimal) map.get("reg03_subrigo_reg_id"));
            preparedStmt.setBigDecimal(2, (BigDecimal) map.get("reg01_num_reg_id"));
            preparedStmt.setBigDecimal(3,(BigDecimal)  map.get("reg03_rigo_id"));
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {

                for (int i = 1; i < 8; i++) {
                    //System.out.println(rs.getMetaData().getColumnLabel(i) + " : " + rs.getString(i));
                    String colLabel = rs.getMetaData().getColumnLabel(i);
                    String colType = rs.getMetaData().getColumnTypeName(i);

                    if (colType.equals("numeric") || colType.equals("int4") || colType.equals("int8")) {

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
