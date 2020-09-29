/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.pdf;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import static idec.x.spesometro.AvviaSpesometro.data_1;
import static idec.x.spesometro.AvviaSpesometro.data_2;
import static idec.x.spesometro.pdf.StampaPdf.*;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class GeneraPDFtabella {

    public GeneraPDFtabella() throws FileNotFoundException {
        newTableTestata();

        newTableIdentificativi();

        newTableIdentificativi_indirizzo();

        newTableDocumento();

        newTableRighi();

        newTableQuadraConto();
        newTableQuadraIva();
    }

    //public static float[] columnWidths = {10,3,4,5,5,10,5,5,10,10,5,5,10}; = max 523
    //public static float[] columnWidths = {40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 3, 40};
    //public static float[] columnWidths = {40, 40, 90, 90, 18, 30, 40, 40};
    //= new Table(columnWidths).setWidthPercent(100);
    private Table table_testata;

    private Table table_identificativi_nome;
    private Table table_identificativi_indirizzo;
    private Table table_documento;
    private Table table_righi;
    private Table table_quadra_conto;
    private Table table_quadra_iva;

    private Table newTableTestata() throws FileNotFoundException {
        float[] columnWidths = {35, 35, 85, 85, 225, 5, 80, 80};
        table_testata = new Table(columnWidths);
        table_testata.setWidthPercent(64);
        return table_testata;
    }
    //----------------------------------------------------------------------------

    private Table newTableIdentificativi() throws FileNotFoundException {
        float[] columnWidths = {5, 5, 10, 10, 20, 3, 6, 10, 31};
        table_identificativi_nome = new Table(columnWidths);
        table_identificativi_nome.setWidthPercent(100);

        table_identificativi_nome.setFixedLayout();

        return table_identificativi_nome;
    }

    private Table newTableIdentificativi_indirizzo() {
        float[] columnWidths = {5, 5, 20, 5, 18, 5, 11, 31};
        table_identificativi_indirizzo = new Table(columnWidths);
        table_identificativi_indirizzo.setWidthPercent(100);

        table_identificativi_indirizzo.setFixedLayout();
        return table_identificativi_indirizzo;
    }

    private Table newTableDocumento() {
        float[] columnWidths = {5, 5, 3, 3, 10, 5, 5, 30, 8, 26};
        table_documento = new Table(columnWidths);
        table_documento.setWidthPercent(100);

        table_documento.setFixedLayout();
        return table_documento;
    }

    private Table newTableRighi() {
        float[] columnWidths = {70, 7, 4, 4, 4, 7, 4};
        table_righi = new Table(columnWidths);
        table_righi.setWidthPercent(100);

        table_righi.setFixedLayout();
        return table_righi;
    }
    // FINE tabelle righe ------------------------------------------------------

    // *** tabelle quadrature
    private Table newTableQuadraConto() {
        float[] tracciato_piede = {20, 50, 10, 10, 10};
        table_quadra_conto = new Table(tracciato_piede);
        table_quadra_conto.setWidthPercent(50);
        table_quadra_conto.setFixedLayout();
        table_quadra_conto.setKeepTogether(true);
        return table_quadra_conto;
    }

    private Table newTableQuadraIva() {
        float[] tracciato_piede = {10, 5, 10, 10, 10, 10, 10, 10, 10, 15};
        table_quadra_iva = new Table(tracciato_piede);
        table_quadra_iva.setWidthPercent(60);
        table_quadra_iva.setFixedLayout();
        table_quadra_iva.setKeepTogether(true);
        return table_quadra_iva;
    }
    // FINE tabelle quadrature--------------------------------------------------

    //Luca***** creazione pdf 
    public void stampa_pdf_testata(String tipo_libro, String dest, HashMap map, String db, Document doc) throws Exception {
        String tipoElenco = "";
        if (tipo_libro.equals("1")) {
            tipoElenco = "Elenco clienti";
        } else {
            tipoElenco = "Elenco fornitori";
        }

        table_testata.addHeaderCell(new Cell().add(db).setBorder(Border.NO_BORDER));
        table_testata.addHeaderCell(new Cell().add(map.get("ragione_sociale").toString()).setBorder(Border.NO_BORDER));
        table_testata.addHeaderCell(new Cell().add(map.get("Nome").toString()).setBorder(Border.NO_BORDER));
        table_testata.addHeaderCell(new Cell().add(map.get("Cognome").toString()).setBorder(Border.NO_BORDER));
        table_testata.addHeaderCell(new Cell().add(map.get("CodiceFiscale").toString()).setBorder(Border.NO_BORDER));
        table_testata.addHeaderCell(new Cell().add(map.get("PartitaIva").toString()).setBorder(Border.NO_BORDER));

        //dalla_data  alla_data
        table_testata.addHeaderCell(new Cell().add(data_1.toString()).setBorder(Border.NO_BORDER));
        table_testata.addHeaderCell(new Cell().add(data_2.toString()).setBorder(Border.NO_BORDER));

        table_testata.addHeaderCell(new Cell().add(tipoElenco).setBorder(Border.NO_BORDER));

        doc.add(table_testata);
    }

    public void pdf_dati_identificativi(HashMap map, String dest, Document doc) throws Exception {
        table_identificativi_nome.addCell(new Cell().add(map.get("conto").toString()).setBorderRight(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER));
        table_identificativi_nome.addCell(new Cell().add(map.get("vendor").toString()).setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER));
        table_identificativi_nome.addCell(new Cell().add(map.get("vendor01_nome").toString()).setBorderRight(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER));
        table_identificativi_nome.addCell(new Cell().add(map.get("vendor01_cognome").toString()).setBorderRight(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER));
        table_identificativi_nome.addCell(new Cell().add(map.get("vendor01_denominazione").toString()).setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER));
        table_identificativi_nome.addCell(new Cell().add("IT").setBorderBottom(Border.NO_BORDER));
        table_identificativi_nome.addCell(new Cell().add(map.get("vendor01_part_iva").toString()).setBorderBottom(Border.NO_BORDER));
        table_identificativi_nome.addCell(new Cell().add(map.get("vendor01_cod_fisc").toString()).setBorderBottom(Border.NO_BORDER));
        table_identificativi_nome.addCell(new Cell().setBorder(Border.NO_BORDER));

        doc.add(table_identificativi_nome);
        pdf_dati_identificativi_indirizzo(dest, map, doc);

    }

    public void pdf_dati_identificativi_indirizzo(String dest, HashMap map, Document doc) throws Exception {
        table_identificativi_indirizzo.addCell(new Cell().setBorder(Border.NO_BORDER));
        table_identificativi_indirizzo.addCell(new Cell().setBorder(Border.NO_BORDER));
        table_identificativi_indirizzo.addCell(new Cell().add(map.get("vendor02_via").toString()).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        table_identificativi_indirizzo.addCell(new Cell().add(map.get("cap").toString()).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        table_identificativi_indirizzo.addCell(new Cell().add(map.get("descrizione_comune").toString()).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        table_identificativi_indirizzo.addCell(new Cell().add(map.get("vendor02_num_civico").toString()).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
        //table.addCell(new Cell().add("#errori#").setBorderBottom(Border.NO_BORDER));
        table_identificativi_indirizzo.addCell(new Cell().setBorder(Border.NO_BORDER));
        if (map.get("errori").toString().length() > 1) {
            table_identificativi_indirizzo.addCell(new Cell().add(map.get("errori").toString()).setBorderBottom(Border.NO_BORDER));
        } else {
            table_identificativi_indirizzo.addCell(new Cell().add(map.get("errori").toString()).setBorder(Border.NO_BORDER));
        }

        doc.add(table_identificativi_indirizzo);
    }

    public void pdf_tipo_documento(String dest, HashMap map, int progressivo_doc, Document doc) throws Exception {
        table_documento.addCell(new Cell().add(map.get("reg01_num_reg_id").toString()).setBorder(Border.NO_BORDER));
        table_documento.addCell(new Cell().add(map.get("data_reg").toString()).setBorder(Border.NO_BORDER));
        table_documento.addCell(new Cell().add(String.valueOf(map.get("progressivo_doc"))).setBorderBottom(Border.NO_BORDER));
        table_documento.addCell(new Cell().add(map.get("tipo_doc").toString()).setBorderBottom(Border.NO_BORDER));
        table_documento.addCell(new Cell().add(map.get("op_des").toString()).setBorderBottom(Border.NO_BORDER));
        table_documento.addCell(new Cell().add(map.get("num_doc").toString()).setBorderBottom(Border.NO_BORDER));
        table_documento.addCell(new Cell().add(map.get("data_doc").toString()).setBorderBottom(Border.NO_BORDER));

        table_documento.addCell(new Cell().setBorder(Border.NO_BORDER));
        table_documento.addCell(new Cell().add(map.get("importo_conto").toString()).setBorder(Border.NO_BORDER));

        table_documento.addCell(new Cell().setBorder(Border.NO_BORDER));
        doc.add(table_documento);
    }

    public void pdf_rigo_documento(String dest, HashMap map, Document doc) throws Exception {
        table_righi.addCell(new Cell().setBorder(Border.NO_BORDER));
        table_righi.addCell(new Cell().add(map.get("importo_conto").toString()).setBorder(Border.NO_BORDER));
        table_righi.addCell(new Cell().add(map.get("iva_perc").toString().concat("%")).setBorder(Border.NO_BORDER));
        BigDecimal ded = (BigDecimal) map.get("iva_importo_ded");
        if (ded.compareTo(BigDecimal.ZERO) != 0) {
            map.put("iva_natura", "DeD");
        }
        table_righi.addCell(new Cell().add(map.get("iva_natura").toString()).setBorder(Border.NO_BORDER));
        table_righi.addCell(new Cell().add(map.get("iva_perc_det").toString().concat("%")).setBorder(Border.NO_BORDER));
        table_righi.addCell(new Cell().add(map.get("iva_imposta").toString()).setBorder(Border.NO_BORDER));
        //table_righi.addCell(new Cell().add(map.get("imposta_iva").toString()).setBorder(Border.NO_BORDER));
        table_righi.addCell(new Cell().add(DIFF_DOC.toString()).setBorder(Border.NO_BORDER));
        //table_righi.addCell(new Cell().setBorder(Border.NO_BORDER));
        doc.add(table_righi);
    }

    public void pdf_quadrature_conto(Document doc, List<HashMap> listMap) throws Exception {
        table_quadra_conto.addHeaderCell("conto");
        table_quadra_conto.addHeaderCell("descrizione");
        table_quadra_conto.addHeaderCell("dare +");
        table_quadra_conto.addHeaderCell("avere -");
        table_quadra_conto.addHeaderCell("differenza");

        BigDecimal totDare = BigDecimal.ZERO;
        BigDecimal totAvere = BigDecimal.ZERO;
        for (HashMap map : listMap) {
            table_quadra_conto.addCell(new Cell().add(map.get("conto").toString()).setBorder(Border.NO_BORDER));
            table_quadra_conto.addCell(new Cell().add(map.get("conto_descr").toString()).setBorder(Border.NO_BORDER));
            BigDecimal sum = (BigDecimal) map.get("sum");
            if (sum.compareTo(BigDecimal.ZERO) > 0) {
                table_quadra_conto.addCell(new Cell().add(map.get("sum").toString()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
                table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
                totDare = totDare.add((BigDecimal) map.get("sum"));
            } else {
                table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
                table_quadra_conto.addCell(new Cell().add(map.get("sum").toString()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
                table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
                totAvere = totAvere.add((BigDecimal) map.get("sum"));
            }

        }
        table_quadra_conto.addCell(new Cell().add("TOTALE").setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER));
        table_quadra_conto.addCell(new Cell().setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER));
        table_quadra_conto.addCell(new Cell().add(totDare.toString()).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        table_quadra_conto.addCell(new Cell().add(totAvere.toString()).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        table_quadra_conto.addCell(new Cell().add(totDare.add(totAvere).toString()).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

        doc.add(table_quadra_conto);
    }

    public void pdf_quadrature_iva(Document doc, List<HashMap> listMap) throws Exception {
        table_quadra_iva.addHeaderCell("imponibile");
        table_quadra_iva.addHeaderCell("iva_natura");
        table_quadra_iva.addHeaderCell("iva_perc");
        table_quadra_iva.addHeaderCell("iva_imposta");
        table_quadra_iva.addHeaderCell("iva_perc_det");
        table_quadra_iva.addHeaderCell("iva_importo_det");

        table_quadra_iva.addHeaderCell("iva_perc_ded");
        table_quadra_iva.addHeaderCell("iva_importo_ded");

        table_quadra_iva.addHeaderCell("iva_perc_non_det");
        table_quadra_iva.addHeaderCell("iva_importo_non_det");

        BigDecimal imponibileTot = BigDecimal.ZERO;
        BigDecimal impostaTot = BigDecimal.ZERO;
        BigDecimal iva_importo_det = BigDecimal.ZERO;
        BigDecimal iva_importo_non_det = BigDecimal.ZERO;
        BigDecimal iva_importo_ded = BigDecimal.ZERO;

        for (HashMap map : listMap) {
            table_quadra_iva.addCell(new Cell().add(map.get("imponibile").toString()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            table_quadra_iva.addCell(new Cell().add(map.get("iva_natura").toString()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            table_quadra_iva.addCell(new Cell().add(map.get("iva_perc").toString()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            table_quadra_iva.addCell(new Cell().add(map.get("iva_imposta").toString()).setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            table_quadra_iva.addCell(new Cell().add(map.get("iva_perc_det").toString()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            table_quadra_iva.addCell(new Cell().add(map.get("iva_importo_det").toString()).setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

            table_quadra_iva.addCell(new Cell().add(map.get("iva_perc_ded").toString()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            table_quadra_iva.addCell(new Cell().add(map.get("iva_importo_ded").toString()).setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

            table_quadra_iva.addCell(new Cell().add(map.get("iva_perc_non_det").toString()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
            table_quadra_iva.addCell(new Cell().add(map.get("iva_importo_non_det").toString()).setBorderLeft(Border.NO_BORDER).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));

            imponibileTot = imponibileTot.add((BigDecimal) map.get("imponibile"));
            impostaTot = impostaTot.add((BigDecimal) map.get("iva_imposta"));
            iva_importo_det = iva_importo_det.add((BigDecimal) map.get("iva_importo_det"));
            iva_importo_non_det = iva_importo_non_det.add((BigDecimal) map.get("iva_importo_non_det"));
            iva_importo_ded = iva_importo_ded.add((BigDecimal) map.get("iva_importo_ded"));
        }

        table_quadra_iva.addFooterCell(imponibileTot.toString()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
        table_quadra_iva.addFooterCell("").setBorder(Border.NO_BORDER);
        table_quadra_iva.addFooterCell("").setBorder(Border.NO_BORDER);
        table_quadra_iva.addFooterCell(impostaTot.toString()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
        table_quadra_iva.addFooterCell("").setBorder(Border.NO_BORDER);
        table_quadra_iva.addFooterCell(iva_importo_det.toString()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);

        table_quadra_iva.addFooterCell("").setBorder(Border.NO_BORDER);
        table_quadra_iva.addFooterCell(iva_importo_ded.toString()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);

        table_quadra_iva.addFooterCell("").setBorder(Border.NO_BORDER);
        table_quadra_iva.addFooterCell(iva_importo_non_det.toString()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);

        doc.add(table_quadra_iva);
    }

//    // ------------------------------------------------------------------------------
//    public void pdfQuadraOLD(String dest, String db) throws Exception {
//        newTableQuadraConto();
//
//        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
//
//        pdfDoc.setDefaultPageSize(PageSize.A4.rotate());
//        Document doc = new Document(pdfDoc);
//        doc.setFontSize(6);
//
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add("DIFFERENZA GENERALE DOC:").setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add(DIFF_TOT_SOMMA.toString()).setBorder(Border.NO_BORDER));
//
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add("TOTALE GENERALE DOCUMENTI").setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add(GEN_TOT_DOC.toString()).setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add("TOTALE IMPONIBILE").setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add(GEN_TOT_IMPONIBILE.toString()).setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add("imposta_ER20037").setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add(GEN_TOT_IMPOSTA_IVA_DETRAIBILE.toString()).setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add("N1_op_escluse").setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add(GEN_TOT_N1.toString()).setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add("N2_op_non_sogg").setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add(GEN_TOT_N2.toString()).setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add("N3_op_non_imponib").setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add(GEN_TOT_N3.toString()).setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add("N4_op_esenti").setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().add(GEN_TOT_N4.toString()).setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
//        table_quadra_conto.addCell(new Cell().setBorder(Border.NO_BORDER));
////
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().add("N5_regime_marg").setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().add("N6_reverse_change").setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().add("N1998").setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().add(map.get("N1998").toString()).setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().add("N1999").setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().add(map.get("N1999").toString()).setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().add("quadra").setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().add(map.get("tot_imp").toString()).setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().add(map.get("contoER20037").toString()).setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().add(map.get("tot").toString()).setBorder(Border.NO_BORDER));
////
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().add("nome libro 99").setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().add(map.get("nom_libro_99").toString()).setBorder(Border.NO_BORDER));
////        table_piede.addCell(new Cell().setBorder(Border.NO_BORDER));
//
//        doc.add(table_quadra_conto);
//        doc.close();
//    }
}
