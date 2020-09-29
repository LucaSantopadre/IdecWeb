/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.conn;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luca
 */
public class Sql {

    public String valDaCercare;
    public String database;
    public String valDaInserire;

    public List<String> listaTabelle;

    public List<String> scorriTabelle(String db) throws SQLException {

        List<String> tabelleList = new ArrayList<>();
        //la connessione si effettua sul database sulla quale si sta facendo la ricerca
        Connection conn = Connessione.getConnection(db);
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null); // alla posizione 3(%) c'è il nome delle tabelle del database
        while (rs.next()) {
            tabelleList.add(rs.getString(3));
        }
        Connessione.closeConnection();
        return tabelleList;
    }

    public List<String> scorriDatabases() throws SQLException {
        List<String> databasesList = new ArrayList<>();
        //la connessione si effettua sul database sulla quale si sta facendo la ricerca 
        Connection conn = Connessione.getConnection("mysql");
        String query = "show databases;";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            databasesList.add(rs.getString(1));
        }
        Connessione.closeConnection();
        return databasesList;
    }

    public void cercaNelleTabelle(List<String> listaTab) throws SQLException {

        int i, totTrovati = 0;
        for (i = 0; i < listaTab.size(); i++) {
            totTrovati += ricerca(listaTab.get(i));
        }

        if (totTrovati == 0) {
            
        } else {
            
        }

    }

    public int ricerca(String tabella) throws SQLException {

        int result = 0;

        String selectSQL = "SELECT * FROM " + tabella;

        Connection conn = Connessione.getConnection(database);
        Statement statement = conn.createStatement();
        //preparedStatement.setInt(1, 1001);

        // execute select SQL stetement
        DatabaseMetaData meta = conn.getMetaData();

        ResultSet rs = statement.executeQuery(selectSQL);
        ResultSetMetaData rsmd = rs.getMetaData();

        int numColonne = rsmd.getColumnCount();
        int i;

        ResultSet rsPrimary = meta.getPrimaryKeys(null, null, tabella);
        String primaryKey = null;
        while (rsPrimary.next()) {
            primaryKey = rsPrimary.getString(4);
        }

        try {
            while (rs.next()) {
                //System.out.println("***rigo");

                // scorro le colonne
                for (i = 1; i < numColonne; i++) {
                    if (valDaCercare.equals(rs.getString(i))) {
                        System.out.println("    ----------trovato");
                        System.out.println("    ----------PK      : " + primaryKey);
                        System.out.println("    ----------id      : " + rs.getString(primaryKey));
                        System.out.println("    ----------colonna : " + rsmd.getColumnName(i));

                        String id = rs.getString(primaryKey);
                        String colonna = rsmd.getColumnName(i);

                        sostituisci(tabella, primaryKey, id, colonna);

                        result++;
                    }
                }

            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();


        } finally {
            statement.close();
        }

        return result;
    }

    public void sostituisci(String tabella, String primaryKey, String id, String colonna) throws SQLException {

        // create the java mysql update preparedstatement
        String query = "UPDATE " + tabella + " SET " + colonna + " = ? WHERE " + primaryKey + " = ?";

        Connection conn = Connessione.getConnection(database);
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, valDaInserire);
        preparedStmt.setString(2, id);

        // execute the java preparedstatement
        preparedStmt.executeUpdate();

    }

    public void aggiustaDate0000(List<String> listaTabelle, String db) throws SQLException {
        int i = 0;
        Connection conn = Connessione.getConnection(db);
        for (String tabella : listaTabelle) {

            if (tabella.equals("2044mv")) {

                String query = "UPDATE " + tabella + " SET data_gio = data_bil WHERE data_gio='0000-00-00' ";
                String query2 = "UPDATE " + tabella + " SET data_rateo1 = data_bil WHERE data_rateo1='0000-00-00' ";
                String query3 = "UPDATE " + tabella + " SET data_rateo2 = data_bil WHERE data_rateo2='0000-00-00' ";
                String query4 = "UPDATE " + tabella + " SET data_storno = data_bil WHERE data_storno='0000-00-00' ";
                String query5 = "UPDATE " + tabella + " SET dataAccertamento = data_bil WHERE dataAccertamento='0000-00-00' ";
                String query6 = "UPDATE " + tabella + " SET data_scadenza_fissa = data_bil WHERE data_scadenza_fissa='0000-00-00' ";
                String query7 = "UPDATE " + tabella + " SET data_storno_accertamento = data_bil WHERE data_storno_accertamento='0000-00-00' ";
//                String query8 = "UPDATE " + tabella + " SET data_doc = data_bil WHERE data_doc LIKE '%-00%' ";
//                String query9 = "UPDATE " + tabella + " SET data_scadenza_cod_trib = data_bil WHERE data_scadenza_cod_trib LIKE '%-00%' ";

                PreparedStatement preparedStmt;
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();

                preparedStmt = conn.prepareStatement(query2);
                preparedStmt.executeUpdate();

                preparedStmt = conn.prepareStatement(query3);
                preparedStmt.executeUpdate();

                preparedStmt = conn.prepareStatement(query4);
                preparedStmt.executeUpdate();

                preparedStmt = conn.prepareStatement(query5);
                preparedStmt.executeUpdate();

                preparedStmt = conn.prepareStatement(query6);
                preparedStmt.executeUpdate();

                preparedStmt = conn.prepareStatement(query7);
                preparedStmt.executeUpdate();
                
//                System.out.println(query8);
//                preparedStmt = conn.prepareStatement(query8);
//                preparedStmt.executeUpdate();
//                
//                preparedStmt = conn.prepareStatement(query9);
//                preparedStmt.executeUpdate();
                
            } else if (tabella.equals("2052ra")) {
                String query = "UPDATE " + tabella + " SET alla_data = '1900-01-01' WHERE alla_data='0000-00-00' ";
                String query2 = "UPDATE " + tabella + " SET data_nascita = '1900-01-01' WHERE data_nascita='0000-00-00' ";

                PreparedStatement preparedStmt;
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();

                preparedStmt = conn.prepareStatement(query2);
                preparedStmt.executeUpdate();

            } else if (tabella.equals("2059ec")) {
                String query = "UPDATE " + tabella + " SET data_scadenza = data_doc WHERE data_scadenza = '0000-00-00' ";

                PreparedStatement preparedStmt;
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();

            } else if (tabella.equals("2040ce")) {
                String query = "UPDATE " + tabella + " SET dalla_data = '1901-01-01' WHERE dalla_data='0000-00-00' ";

                PreparedStatement preparedStmt;
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();

            } else if (tabella.equals("2060ec")) {
                String query = "UPDATE " + tabella + " SET data_scadenza = data_doc WHERE data_scadenza = '0000-00-00' ";

                PreparedStatement preparedStmt;
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();

            } else if (tabella.equals("a012l")) {
                String query = "UPDATE " + tabella + " SET data_scad = data_bil WHERE data_scad = '0000-00-00' ";
                String query2 = "UPDATE " + tabella + " SET data_doc = data_bil WHERE data_doc = '0000-00-00' ";

                PreparedStatement preparedStmt;
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();

                preparedStmt = conn.prepareStatement(query2);
                preparedStmt.executeUpdate();
            } else if (tabella.equals("movimento_transito")) {
                String query = "UPDATE " + tabella + " SET data_doc = data_bil WHERE data_doc = '0000-00-00' ";

                PreparedStatement preparedStmt;
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();
            } else if (tabella.equals("a012_da_pagare")) {
                String query = "UPDATE " + tabella + " SET data_scad = data_bil WHERE data_scad = '0000-00-00' ";
                String query2 = "UPDATE " + tabella + " SET data_doc = data_bil WHERE data_doc = '0000-00-00' ";

                PreparedStatement preparedStmt;
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();

                preparedStmt = conn.prepareStatement(query2);
                preparedStmt.executeUpdate();
            } else if (tabella.equals("a012_pagato")) {
                String query = "UPDATE " + tabella + " SET data_scad = data_bil WHERE data_scad = '0000-00-00' ";
                String query2 = "UPDATE " + tabella + " SET data_doc = data_bil WHERE data_doc = '0000-00-00' ";

                PreparedStatement preparedStmt;
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();

                preparedStmt = conn.prepareStatement(query2);
                preparedStmt.executeUpdate();
            } else if (tabella.equals("a012s")) {
                String query = "UPDATE " + tabella + " SET data_scad = data_bil WHERE data_scad = '0000-00-00' ";
                String query2 = "UPDATE " + tabella + " SET data_doc = data_bil WHERE data_doc = '0000-00-00' ";
                String query3 = "UPDATE " + tabella + " SET data_gio = data_bil WHERE data_gio = '0000-00-00' ";

                PreparedStatement preparedStmt;
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();

                preparedStmt = conn.prepareStatement(query2);
                preparedStmt.executeUpdate();

                preparedStmt = conn.prepareStatement(query3);
                preparedStmt.executeUpdate();
            }else if (tabella.equals("2059ec_b")) {
                String query = "UPDATE " + tabella + " SET data_scadenza = data_bil WHERE data_scadenza = '0000-00-00' ";
                
                PreparedStatement preparedStmt;
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.executeUpdate();
            }

        }
        Connessione.closeConnection();

    }

}
