/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.pdf;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import static idec.x.spesometro.quadrature.QuadratureIva01.quadraIva01;
import static idec.x.spesometro.quadrature.QuadraturePerConto.quadraPerConto;
import static idec.x.spesometro.xml.mapTabelle.GetMap_IdentFiscali.getIdentificativiFiscali;
import static idec.x.spesometro.xml.mapTabelle.GetMap_RighiDocumento.getRighiConto;
import static idec.x.spesometro.xml.mapTabelle.GetMap_TipoDocumento.getRegistrazioni;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Luca
 */
public class StampaPdf {

    public static BigDecimal DIFF_DOC = BigDecimal.ZERO;
    public static BigDecimal DIFF_TOT_SOMMA = BigDecimal.ZERO;

    public static BigDecimal GEN_TOT_DOC = BigDecimal.ZERO;
    public static BigDecimal GEN_TOT_IMPONIBILE = BigDecimal.ZERO;
    public static BigDecimal GEN_TOT_IMPOSTA_IVA_DETRAIBILE = BigDecimal.ZERO;
    public static BigDecimal GEN_TOT_IMPOSTA_IVA_NO_DETRAIBILE = BigDecimal.ZERO;

    public static BigDecimal GEN_TOT_IMPOSTA_IVA_DEDUCIBILE = BigDecimal.ZERO;
    public static BigDecimal GEN_TOT_IMPOSTA_IVA_NO_DEDUCIBILE = BigDecimal.ZERO;

    public static BigDecimal GEN_TOT_N1 = BigDecimal.ZERO;
    public static BigDecimal GEN_TOT_N2 = BigDecimal.ZERO;
    public static BigDecimal GEN_TOT_N3 = BigDecimal.ZERO;
    public static BigDecimal GEN_TOT_N4 = BigDecimal.ZERO;

    public static Document generaDocument(String dest) throws FileNotFoundException {        
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));

        pdfDoc.setDefaultPageSize(PageSize.A4.rotate());
        Document doc = new Document(pdfDoc);
        doc.setFontSize(6);
        
        return doc;
    }

    public static void creaPdfCorpo(String tipo_libro, String percorso_file, String db) throws Exception {
        List<HashMap> listMap = getIdentificativiFiscali(tipo_libro, db);

        Document doc = generaDocument(percorso_file);
        GeneraPDFtabella oggettoPDF = null;

        for (HashMap hashMap : listMap) {
            oggettoPDF = new GeneraPDFtabella();
            oggettoPDF.pdf_dati_identificativi(hashMap, percorso_file, doc);

            List<HashMap> registrazioni = getRegistrazioni(tipo_libro, db, hashMap);
            for (HashMap reg_doc : registrazioni) {
                DIFF_DOC = DIFF_DOC.add((BigDecimal) reg_doc.get("importo_conto"));
                GEN_TOT_DOC = GEN_TOT_DOC.add((BigDecimal) reg_doc.get("importo_conto"));

                oggettoPDF = new GeneraPDFtabella();
                oggettoPDF.pdf_tipo_documento(percorso_file, reg_doc, 0, doc);

                List<HashMap> listaRighiDocumento = getRighiConto(tipo_libro, db, reg_doc);
                for (HashMap rigoDoc : listaRighiDocumento) {                    
                    DIFF_DOC = DIFF_DOC.add((BigDecimal) rigoDoc.get("importo_conto"));
                    
                    DIFF_DOC = DIFF_DOC.add((BigDecimal) rigoDoc.get("iva_imposta"));
                    GEN_TOT_IMPOSTA_IVA_DETRAIBILE = GEN_TOT_IMPOSTA_IVA_DETRAIBILE.add((BigDecimal) rigoDoc.get("iva_imposta"));

                    if (!rigoDoc.get("iva_natura").toString().contains("N")) {
                        GEN_TOT_IMPONIBILE = GEN_TOT_IMPONIBILE.add((BigDecimal) rigoDoc.get("importo_conto"));
                    }

                    // *** totali per natura
                    if (rigoDoc.get("iva_natura").equals("N1")) {
                        GEN_TOT_N1 = GEN_TOT_N1.add((BigDecimal) rigoDoc.get("importo_conto"));
                    }
                    if (rigoDoc.get("iva_natura").equals("N2")) {
                        GEN_TOT_N2 = GEN_TOT_N2.add((BigDecimal) rigoDoc.get("importo_conto"));
                    }
                    if (rigoDoc.get("iva_natura").equals("N3")) {
                        GEN_TOT_N3 = GEN_TOT_N3.add((BigDecimal) rigoDoc.get("importo_conto"));
                    }
                    if (rigoDoc.get("iva_natura").equals("N4")) {
                        GEN_TOT_N4 = GEN_TOT_N4.add((BigDecimal) rigoDoc.get("importo_conto"));
                    }

                    oggettoPDF = new GeneraPDFtabella();
                    oggettoPDF.pdf_rigo_documento(percorso_file, rigoDoc, doc);

                    DIFF_DOC = BigDecimal.ZERO;

                }
                // *** prima di uscire dal vendor aggiunge differenza documenti
                DIFF_TOT_SOMMA = DIFF_TOT_SOMMA.add(DIFF_DOC);
            }
            DIFF_DOC = BigDecimal.ZERO;
        }

        // stampa piede
        oggettoPDF = new GeneraPDFtabella();
        List<HashMap> listaQuadraPerConto = quadraPerConto(db,tipo_libro);
        oggettoPDF.pdf_quadrature_conto(doc, listaQuadraPerConto);

        List<HashMap> listaQuadraIva = quadraIva01(db,tipo_libro);
        oggettoPDF.pdf_quadrature_iva(doc, listaQuadraIva);

        doc.close();
    }

}
