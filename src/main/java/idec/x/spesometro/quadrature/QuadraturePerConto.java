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
public class QuadraturePerConto {

    public static List<HashMap> quadraPerConto(String db, String tipo_libro) {
        try {
            String query = "SELECT DISTINCT\n"
                    + db + ".reg03_rigo.conto,\n"
                    + "Sum(" + db + ".reg03_rigo.importo_conto)\n"
                    + "FROM\n"
                    + db + ".reg03_rigo\n"
                    + "INNER JOIN " + db + ".reg01 ON " + db + ".reg03_rigo.reg01_num_reg_id = " + db + ".reg01.num_reg_id AND " + db + ".reg03_rigo.reg01_reparto = " + db + ".reg01.reparto\n"
                    + "WHERE\n"
                    + db + ".reg03_rigo.reg01_reparto = ? AND\n"
                    + db + ".reg01.data_reg BETWEEN '" + data_1 + "' AND '" + data_2 + "'\n"
                    + "GROUP BY\n"
                    + db + ".reg03_rigo.conto\n"
                    + "ORDER BY\n"
                    + db + ".reg03_rigo.conto ASC";

            Connection conn = Connessione.getConnection(db);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, tipo_libro);
            ResultSet rs = preparedStmt.executeQuery();
            HashMap map;
            List<HashMap> listaMap = new ArrayList<>();
            while (rs.next()) {
                map = new HashMap();
                for (int i = 1; i < 3; i++) {
                    //System.out.println(rs.getMetaData().getColumnLabel(i) + " : " + rs.getString(i));
                    String colLabel = rs.getMetaData().getColumnLabel(i);
                    String colType = rs.getMetaData().getColumnTypeName(i);

                    if (colType.equals("numeric") || colType.equals("int4") || colType.equals("int8")) {

                        map.put(colLabel, rs.getBigDecimal(i));

                    } else {
                        map.put(colLabel, rs.getString(i));
                    }
                }
                addDescrizioneConto(map);
                listaMap.add(map);
            }
            return listaMap;
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return null;
    }

    public static void addDescrizioneConto(HashMap map) {
        try {
            String query = "SELECT\n"
                    + "\"public\".mc.cee_conto_cod_des as conto_descr\n"
                    + "FROM\n"
                    + "\"public\".mc\n"
                    + "WHERE\n"
                    + "\"CEE_CONTO_COD\" = ?";

            Connection conn = Connessione.getConnection("public");
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, map.get("conto").toString());
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                for (int i = 1; i < 2; i++) {
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
