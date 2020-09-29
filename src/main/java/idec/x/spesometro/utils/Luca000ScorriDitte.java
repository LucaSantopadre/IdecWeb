/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.utils;

import idec.x.spesometro.conn.Connessione;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class Luca000ScorriDitte {

    public static int scelta_iva_2044 = 1;

    public static ArrayList<String> luca000ScorriDitteSchema() throws ParseException, Exception {
        try {
            ArrayList<String> dataBObservable = new ArrayList<>();

            String query = "select schema_name\n"
                    + "from information_schema.schemata";

            Connection conn = Connessione.getConnection("public");
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            HashMap map = new HashMap();
            while (rs.next()) {
                String res = rs.getString(1);
                if (!res.equals("pg_toast")
                        //SI                       
//                        && !res.equals("al001")
//                        && !res.equals("am001")
//                        && !res.equals("fi001")
//                        && !res.equals("gb002")
//                        && !res.equals("ia001")
//                        && !res.equals("ma002")
//                        && !res.equals("mi001")
//                        && !res.equals("pa003")
//                        && !res.equals("pm001")
//                        && !res.equals("ro001")
//                        && !res.equals("se001")
//                        && !res.equals("ma003")
//                        && !res.equals("ma005")
//                        && !res.equals("ma007")
                        //NO
                        && !res.equals("idec_schema")
                        && !res.equals("pg_temp_1")
                        && !res.equals("pg_toast_temp_1")
                        && !res.equals("pg_catalog")
                        && !res.equals("public")
                        && !res.equals("information_schema")
                        && !res.equals("model_ditte")
                        && !res.equals("fi001_old")
                        && !res.equals("am002")
                        && !res.equals("am003")
                        && !res.equals("ar001")
                        && !res.equals("ar002")
                        && !res.equals("be001")
                        && !res.equals("br001")
                        && !res.equals("ce001")
                        && !res.equals("ce003")
                        && !res.equals("ce004")
                        && !res.equals("cl001")
                        && !res.equals("de001")
                        && !res.equals("di001")
                        && !res.equals("ed001")
                        && !res.equals("ed002")
                        && !res.equals("eu001")
                        && !res.equals("fa001")
                        && !res.equals("fb001")
                        && !res.equals("fe001")
                        && !res.equals("gb001")
                        && !res.equals("gl001")
                        && !res.equals("gr001")
                        && !res.equals("in001")
                        && !res.equals("ma001")
                        && !res.equals("ma006")
                        && !res.equals("mo001")
                        && !res.equals("ne001")
                        && !res.equals("om001")
                        && !res.equals("pa001")
                        && !res.equals("se002")
                        && !res.equals("si001")
                        && !res.equals("su001")) {
                    map.put(rs.getMetaData().getColumnLabel(1), rs.getString(1));
                    String db = rs.getString(1);

                    dataBObservable.add(db);
                }
            }
            return dataBObservable;
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return null;
    }

}
