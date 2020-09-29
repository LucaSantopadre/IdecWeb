/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.quadrature;

import static idec.x.spesometro.AvviaSpesometro.data_1;
import static idec.x.spesometro.AvviaSpesometro.data_2;
import idec.x.spesometro.conn.Connessione;
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
public class QuadratureIva01 {

    public static List<HashMap> quadraIva01(String db, String tipo_libro) {
        try {
            String query = "SELECT DISTINCT\n"
                    + "	SUM (" + db + ".reg03_rigo.importo_conto) AS imponibile,\n"
                    + db + ".reg04_iva.iva_perc,\n"
                    + "	SUM (" + db + ".reg04_iva.iva_imposta) AS iva_imposta,\n"
                    + db + ".reg04_iva.iva_perc_det,\n"
                    + "	SUM (" + db + ".reg04_iva.iva_importo_det) AS iva_importo_det,\n"
                    + db + ".reg04_iva.iva_perc_non_det,\n"
                    + "	SUM (" + db + ".reg04_iva.iva_importo_non_det) AS iva_importo_non_det,\n"
                    + db + ".reg04_iva.iva_perc_ded,\n"
                    + "	SUM (" + db + ".reg04_iva.iva_importo_ded) AS iva_importo_ded,\n"
                    + db + ".reg04_iva.iva_natura\n"
                    + "\n"
                    + "FROM\n"
                    + db + ".reg04_iva\n"
                    + "INNER JOIN " + db + ".reg03_rigo ON " + db + ".reg04_iva.reg03_rigo_reg_id = " + db + ".reg03_rigo.reg03_rigo_reg_id\n"
                    + "AND " + db + ".reg04_iva.reg03_subrigo_reg_id = " + db + ".reg03_rigo.reg03_subrigo_reg_id\n"
                    + "AND " + db + ".reg04_iva.reg01_num_reg_id = " + db + ".reg03_rigo.reg01_num_reg_id\n"
                    + "AND " + db + ".reg03_rigo.reg01_reparto = ? \n"
                    + "INNER JOIN " + db + ".reg01 ON " + db + ".reg01.num_reg_id = " + db + ".reg03_rigo.reg01_num_reg_id\n"
                    + "WHERE " + db + ".reg01.data_reg BETWEEN '" + data_1 + "' AND '" + data_2 + "'"
                    + "GROUP BY\n"
                    + db + ".reg04_iva.iva_perc,\n"
                    + db + ".reg04_iva.iva_natura,\n"
                    + db + ".reg04_iva.iva_perc_ded,\n"
                    + db + ".reg04_iva.iva_perc_det,\n"
                    + db + ".reg04_iva.iva_perc_non_det";

            Connection conn = Connessione.getConnection(db);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, tipo_libro);
            ResultSet rs = preparedStmt.executeQuery();
            HashMap map;
            List<HashMap> listaMap = new ArrayList<>();
            while (rs.next()) {
                map = new HashMap();
                for (int i = 1; i < 11; i++) {
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
            return listaMap;
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return null;
    }
}
