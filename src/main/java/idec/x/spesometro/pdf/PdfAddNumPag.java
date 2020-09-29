/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import static idec.x.spesometro.xml.mapTabelle.GetMap_Ditta.getXmlDatiDitta;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import static idec.x.spesometro.AvviaSpesometro.data_1;
import static idec.x.spesometro.AvviaSpesometro.data_2;
import static idec.x.spesometro.utils.Data.DATA_ORA_1;

public class PdfAddNumPag {

    public static void addNumPagPdf(String src, String dest, String db, String tipo) throws Exception {
        System.out.println("//Aggiungo testata");

        HashMap xmlDatiDitta = getXmlDatiDitta(db);

        String testata = "";

        testata = xmlDatiDitta.get("ragione_sociale") + "      ";
        testata = testata + xmlDatiDitta.get("Cognome") + "  ";
        testata = testata + xmlDatiDitta.get("Nome") + "      ";
        testata = testata + xmlDatiDitta.get("CodiceFiscale") + "  ";
        testata = testata + xmlDatiDitta.get("PartitaIva") + "      -     ";
        if (tipo.equals("1")) {
            testata = testata + "FATTURE EMESSE      ";
        } else {
            testata = testata + "FATTURE RICEVUTE      ";
        }   
        testata = testata + "dal " + data_1;
        testata = testata + "   al " + data_2;
        
        
        
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
        doc.setFontSize(6);
        int n = pdfDoc.getNumberOfPages();
        for (int i = 1; i <= n; i++) {
            doc.showTextAligned(new Paragraph(String.format(DATA_ORA_1 + "   -   pag %s.%s", i, n)),
                    806, 575, i, TextAlignment.RIGHT, VerticalAlignment.TOP, 0);
            
            doc.showTextAligned(new Paragraph(String.format("TOT DOC    IMPORTO IVA               %% IVA       NATURA       %% DET    IMPOSTA          DIFFERENZA", i, n)),
                    535, 566, i, TextAlignment.LEFT, VerticalAlignment.TOP, 0);
            doc.showTextAligned(new Paragraph(String.format("__________________________________________________________________________________________", i, n)),
                    535, 565, i, TextAlignment.LEFT, VerticalAlignment.TOP, 0);

            doc.showTextAligned(new Paragraph(String.format(testata, i, n)),
                    100, 575, i, TextAlignment.LEFT, VerticalAlignment.TOP, 0);
        }
        doc.close();

        File file_no_pag = new File(src);
        file_no_pag.delete();

        File file_new = new File(dest);
        boolean renameTo = file_new.renameTo(file_no_pag);
    }

}
