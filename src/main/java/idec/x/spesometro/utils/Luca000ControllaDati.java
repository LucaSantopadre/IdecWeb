/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.utils;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 *
 * @author Luca
 */
public class Luca000ControllaDati {

    public static HashMap controllaUpdate_PartitaIva_CodiceFiscale(HashMap map) {
        //aggiunge 0 alla partita iva se minore di 11
        if (map.get("part_iva").toString().length() == 10) {
            map.put("part_iva", "0" + map.get("part_iva"));
        }
        if (map.get("fisc_cod").toString().length() == 10) {
            map.put("fisc_cod", "0" + map.get("fisc_cod"));
        }
        return map;
    }

    public static HashMap controllaDatiAnagrafici(HashMap map) {
        String errori = "";

        if (map.get("vendor01_part_iva").toString().length() < 11 && map.get("vendor01_cod_fisc").toString().length() < 11) {
            errori = errori.concat("part_iva, fisc_cod, ");
        }

        if (map.get("vendor01_denominazione").toString().length() > 0
                && (map.get("vendor01_cognome").toString().length() > 0 || map.get("vendor01_nome").toString().length() > 0)) {
            errori = errori.concat("rag_soc, ");
        }

        if (map.get("vendor02_via").toString().length() < 1) {
            errori = errori.concat("via, ");
        }

        if (map.get("vendor02_num_civico").toString().length() < 1 || map.get("vendor02_num_civico").equals(".")) {
            errori = errori.concat("n_civico, ");
        }

        map.put("errori", errori);
        return map;
    }

    public static HashMap controllaDati_iva_2044_2_Rigo_0(HashMap map) {
        String errori = "";
//        //aggiunge 0 alla partita iva e cod fiscale , se == 10 la sua lunghezza
        if (map.get("part_iva").toString().length() == 10) {
            map.put("part_iva", "0" + map.get("part_iva"));
        }
        if (map.get("fisc_cod").toString().length() == 10) {
            map.put("fisc_cod", "0" + map.get("fisc_cod"));
        }
        //elimina meno da importo euro avere
//        if (!map.get("IMPORTO_EURO_AVERE").toString().equals("0.00")) {
//            StringBuilder sb = new StringBuilder(map.get("IMPORTO_EURO_AVERE").toString());
//            sb.deleteCharAt(0);
//            map.put("IMPORTO_EURO_AVERE", sb);
//        }

        if (map.get("part_iva").toString().length() < 11 && map.get("fisc_cod").toString().length() < 11) {
            errori = errori.concat("part_iva, fisc_cod, ");
        }

        if (map.get("ragione_sociale").toString().length() > 0
                && (map.get("cee_codice_cognome").toString().length() > 0 || map.get("cee_codice_nome").toString().length() > 0)) {
            errori = errori.concat("rag_soc, ");
        }

        if (map.get("via").toString().length() < 1) {
            errori = errori.concat("via, ");
        }

        if (map.get("numero_civico").toString().length() < 1 || map.get("numero_civico").equals(".")) {
            errori = errori.concat("n_civico, ");
        }

        if (map.get("descrizione_comune").toString().length() < 1) {
            errori = errori.concat("comune, ");
        }

        map.put("errori", errori);
        return map;
    }

    public static HashMap controllaDati_tipoDocumento(HashMap map) {

        BigDecimal importo_doc = (BigDecimal) map.get("importo_doc");
        if (importo_doc.doubleValue() < 0) {
            importo_doc.multiply(BigDecimal.valueOf(-1));
            map.put("importo_doc", importo_doc);
        }

        return map;
    }

    public static HashMap controllaDati_rigoDocumento(HashMap map) {
        BigDecimal iva_imp_det = (BigDecimal) map.get("IVA_IMP_DET");
        if (iva_imp_det.doubleValue() < 0) {
            iva_imp_det = iva_imp_det.multiply(BigDecimal.valueOf(-1));
            map.put("IVA_IMP_DET", iva_imp_det);
        }

        BigDecimal imposta_iva = (BigDecimal) map.get("imposta_iva");
        if (imposta_iva.doubleValue() < 0) {
            imposta_iva = imposta_iva.multiply(BigDecimal.valueOf(-1));
            map.put("imposta_iva", imposta_iva);
        }

        return map;
    }

}
