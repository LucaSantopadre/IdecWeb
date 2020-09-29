/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro;

import static idec.x.spesometro.pdf.PdfAddNumPag.addNumPagPdf;
import static idec.x.spesometro.pdf.StampaPdf.creaPdfCorpo;
import static idec.x.spesometro.xml.XmlDte.creaXml_DTE;
import static idec.x.spesometro.xml.XmlDtr.creaXml_DTR;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Luca
 */
public class AvviaSpesometro {

    public static String PATH_FOLDER_SPESOMETRO = "C:\\idecStampe\\spesometro\\";
    public static String FILE_PDF;

    ArrayList<String> listaDbSelezionati = new ArrayList<>();
    public static LocalDate data_1;
    public static LocalDate data_2;

    public void actionProceduraDTE(LocalDate dalla_data, LocalDate alla_data, String db) throws Exception {

        // *** XML -----------------
        boolean xmlCreato = creaXml_DTE(db);
        // -------------------------

        // *** PDF -----------------
        creaPdfCorpo("1", FILE_PDF, db);
        addNumPagPdf(FILE_PDF, ".\\temp.pdf", db, "1");
        // -------------------------
        //}
    }

    public void actionProceduraDTR(LocalDate partenza, LocalDate arrivo, String db) throws Exception {
        // *** XML -----------------
        boolean xmlCreato = creaXml_DTR(db);
        // -------------------------

        // *** PDF -----------------
        creaPdfCorpo("2", FILE_PDF, db);
        addNumPagPdf(FILE_PDF, ".\\temp.pdf", db, "2");
        // -------------------------
    }
}
