/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.utils;

import idec.x.spesometro.conn.Connessione;
import static idec.x.spesometro.conn.Connessione.DITTA;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Luca004CreaTab {


    public static void luca004CreaTab_CreaSeNonEsisteVendor01_base() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS " + DITTA + ".vendor01_base (\n"
                    + "\"vendor01_id\" varchar(10) COLLATE \"default\" NOT NULL,\n"
                    + "\"vendor01_denominazione\" varchar(80) COLLATE \"default\",\n"
                    + "\"vendor01_nome\" varchar(60) COLLATE \"default\",\n"
                    + "\"vendor01_cognome\" varchar(60) COLLATE \"default\",\n"
                    + "\"vendor01_paese_fis\" varchar(2) COLLATE \"default\",\n"
                    + "\"vendor01_piva\" varchar(11) COLLATE \"default\",\n"
                    + "\"vendor01_codfis\" varchar(16) COLLATE \"default\",\n"
                    + "PRIMARY KEY (\"vendor01_id\")"
                    + ")\n"
                    + "WITH (OIDS=FALSE)\n"
                    + "\n"
                    + ";";

            Connection conn = Connessione.getConnection(DITTA);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            System.out.println(preparedStmt);
            ResultSet rs = preparedStmt.executeQuery();

        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }

    public static void luca004CreaTab_CreaSeNonEsisteVendor02_sede() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS " + DITTA + ".vendor02_sede (\n"
                    + "\"vendor02_id\" SERIAL NOT NULL,\n"
                    + "\"vendor01_ID\" varchar(10) COLLATE \"default\",\n"
                    + "\"vendor02_via\" varchar(255) COLLATE \"default\",\n"
                    + "\"vendor02_numero_civico\" varchar(255) COLLATE \"default\",\n"
                    + "\"vendor02_id_comune\" int4,\n"
                    + "\"vendor02_dadata\" date,\n"
                    + "\"vendor02_paese_sede\" varchar(2) COLLATE \"default\",\n"
                    + "PRIMARY KEY (\"vendor02_id\")"
                    + ")\n"
                    + "WITH (OIDS=FALSE)\n"
                    + "\n"
                    + ";";

            Connection conn = Connessione.getConnection(DITTA);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            System.out.println(preparedStmt);
            ResultSet rs = preparedStmt.executeQuery();

        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }

    public static void luca004CreaTab_CreaSeNonEsisteIva_2044_1(String n_tab) {
        try {
            String query = "CREATE TABLE IF NOT EXISTS " + DITTA + ".iva_2044_"+n_tab+" (\n"
                    + "\"cod_att\" varchar(5) COLLATE \"default\",\n"
                    + "\"data_bil\" date,\n"
                    + "\"tipo_libro\" char(1) COLLATE \"default\",\n"
                    + "\"nome_libro\" numeric(2),\n"
                    + "\"localita\" varchar(4) COLLATE \"default\",\n"
                    + "\"n_reg\" numeric(6),\n"
                    + "\"rigo\" numeric(4),\n"
                    + "\"subrigo\" numeric(3),\n"
                    + "\"a_g\" char(1) COLLATE \"default\",\n"
                    + "\"causele\" varchar(6) COLLATE \"default\",\n"
                    + "\"data_gio\" date,\n"
                    + "\"bol_gio\" char(1) COLLATE \"default\",\n"
                    + "\"bol_iva\" char(1) COLLATE \"default\",\n"
                    + "\"divisa\" char(3) COLLATE \"default\",\n"
                    + "\"doc_n\" varchar(16) COLLATE \"default\",\n"
                    + "\"data_doc\" date,\n"
                    + "\"rif_doc\" varchar(16) COLLATE \"default\",\n"
                    + "\"tipomastro\" int4,\n"
                    + "\"mastro\" int4,\n"
                    + "\"mastro2\" int4,\n"
                    + "\"mastro3\" int4,\n"
                    + "\"mastro4\" int4,\n"
                    + "\"conto\" varchar(7) COLLATE \"default\" NOT NULL,\n"
                    + "\"codice\" varchar(5) COLLATE \"default\" NOT NULL,\n"
                    + "\"IMPORTO_DOC_DARE\" numeric(15,2),\n"
                    + "\"IMPORTO_DOC_AVERE\" numeric(15,2),\n"
                    + "\"cambio\" numeric(12,6),\n"
                    + "\"IMPORTO_EURO_DARE\" numeric(15,2),\n"
                    + "\"IMPORTO_EURO_AVERE\" numeric(15,2),\n"
                    + "\"iva_cod\" varchar(5) COLLATE \"default\",\n"
                    + "\"sezione_iva\" varchar(8) COLLATE \"default\",\n"
                    + "\"riv\" char(1) COLLATE \"default\",\n"
                    + "\"iva_perc\" numeric(5,2),\n"
                    + "\"iva_perc_det\" numeric(5,2),\n"
                    + "\"IVA_IMP_DET\" numeric(15,2),\n"
                    + "\"despar\" varchar(35) COLLATE \"default\",\n"
                    + "\"desconpar\" varchar(35) COLLATE \"default\",\n"
                    + "\"FUNZ_IVA\" varchar(50) COLLATE \"default\",\n"
                    + "\"IMPORTO_CONTO\" numeric(15,2),\n"
                    + "\"IMPORTO_DOCUMENTO\" numeric(15,2),\n"
                    + "\"TOT_DARE\" numeric(15,2),\n"
                    + "\"TOT_AVERE\" numeric(15,2),\n"
                    + "\"TOT_DIF\" numeric(15,2),\n"
                    + "\"TOT_DIF_IVA\" numeric(15,2),\n"
                    + "\"TOT_IMP\" numeric(15,2),\n"
                    + "\"TOT_IVA\" numeric(15,2),\n"
                    + "\"VALORE_CALCOLO\" numeric(15,2),\n"
                    + "\"tipomastroiva\" int4,\n"
                    + "\"mastroiva\" int4,\n"
                    + "\"mastro2iva\" int4,\n"
                    + "\"mastro3iva\" int4,\n"
                    + "\"mastro4iva\" int4,\n"
                    + "\"contoiva\" varchar(7) COLLATE \"default\",\n"
                    + "\"codiceiva\" varchar(5) COLLATE \"default\",\n"
                    + "\"ARTICOLO_IVA_PERC\" varchar(5) COLLATE \"default\",\n"
                    + "\"tipo_doc\" varchar(6) COLLATE \"default\",\n"
                    + "\"importo_euro\" numeric(15,2),\n"
                    + "\"Totale\" numeric(15,2),\n"
                    + "\"tm0\" int4,\n"
                    + "\"m0\" int4,\n"
                    + "\"m20\" int4,\n"
                    + "\"m30\" int4,\n"
                    + "\"m40\" int4,\n"
                    + "\"cnt0\" varchar(7) COLLATE \"default\",\n"
                    + "\"cod0\" varchar(5) COLLATE \"default\",\n"
                    + "\"annocodtrib\" numeric(4),\n"
                    + "\"codTrib\" varchar(8) COLLATE \"default\",\n"
                    + "\"TipoAcquisti\" char(1) COLLATE \"default\",\n"
                    + "\"data_storno\" date,\n"
                    + "\"data_rateo1\" date,\n"
                    + "\"data_rateo2\" date,\n"
                    + "\"f24_causale_contributo\" varchar(5) COLLATE \"default\",\n"
                    + "\"mesecodtrib\" int4,\n"
                    + "\"tipo_libro_storno_accertamento\" char(1) COLLATE \"default\",\n"
                    + "\"nome_libro_storno_accertamento\" int4,\n"
                    + "\"causale_storno_accertamento\" varchar(6) COLLATE \"default\",\n"
                    + "\"num_reg_storno_accertamento\" int4,\n"
                    + "\"data_storno_accertamento\" date,\n"
                    + "\"num_reg_competenza\" int4,\n"
                    + "\"data_competenza\" date,\n"
                    + "\"tipomastro_storno_accertamento\" int4,\n"
                    + "\"mastro_storno_accertamento\" int4,\n"
                    + "\"mastro2_storno_accertamento\" int4,\n"
                    + "\"mastro3_storno_accertamento\" int4,\n"
                    + "\"mastro4_storno_accertamento\" int4,\n"
                    + "\"conto_storno_accertamento\" varchar(7) COLLATE \"default\",\n"
                    + "\"codice_storno_accertamento\" varchar(5) COLLATE \"default\",\n"
                    + "\"importo_storno_accertamento\" numeric(15,2),\n"
                    + "\"tipo_pagamento\" int4,\n"
                    + "\"cod_pagamento\" int4,\n"
                    + "\"rif_banca\" varchar(10) COLLATE \"default\",\n"
                    + "\"ra_pagato\" numeric(15,2),\n"
                    + "\"totale_ra\" numeric(15,2),\n"
                    + "\"checkPagato\" char(1) COLLATE \"default\",\n"
                    + "\"checkStorno\" char(1) COLLATE \"default\",\n"
                    + "\"checkRateo\" char(1) COLLATE \"default\",\n"
                    + "\"checkCompetenza\" char(1) COLLATE \"default\",\n"
                    + "\"utente\" varchar(100) COLLATE \"default\",\n"
                    + "\"data_utente\" timestamp(0),\n"
                    + "\"imp_non_soggetto_ra\" numeric(13,2),\n"
                    + "\"perc_trib_ra\" numeric(5,2),\n"
                    + "\"checkAccertamento\" char(1) COLLATE \"default\",\n"
                    + "\"dataAccertamento\" date,\n"
                    + "\"tipomastroAccertamento\" int4,\n"
                    + "\"mastroAccertamento\" int4,\n"
                    + "\"mastro2Accertamento\" int4,\n"
                    + "\"mastro3Accertamento\" int4,\n"
                    + "\"mastro4Accertamento\" int4,\n"
                    + "\"contoAccertamento\" varchar(7) COLLATE \"default\",\n"
                    + "\"codiceAccertamento\" varchar(5) COLLATE \"default\",\n"
                    + "\"numeroAccertamento\" int4,\n"
                    + "\"rigoAccertamento\" int4,\n"
                    + "\"checkDiversi\" char(1) COLLATE \"default\",\n"
                    + "\"data_storno_rateo\" date,\n"
                    + "\"numero_storno_rateo\" int4,\n"
                    + "\"rigo_storno_rateo\" int4,\n"
                    + "\"ripartoAcquisti\" int4,\n"
                    + "\"condizioniPagamento\" int4,\n"
                    + "\"tipoPagamento\" int4,\n"
                    + "\"data_scadenza_fissa\" date,\n"
                    + "\"da_griglia\" char(1) COLLATE \"default\",\n"
                    + "\"iva_dati_riepilogo\" varchar(10) COLLATE \"default\",\n"
                    + "\"imm_chk\" char(1) COLLATE \"default\",\n"
                    + "\"imm_num_inv\" int4,\n"
                    + "\"num_scadenzario\" int4,\n"
                    + "\"rif_scadenzario\" int4,\n"
                    + "\"tipoPerc\" int4,\n"
                    + "\"percIidd\" numeric(5,2),\n"
                    + "\"percIrap\" numeric(5,2),\n"
                    + "\"importo_lit_dare\" numeric(15),\n"
                    + "\"importo_lit_avere\" numeric(15),\n"
                    + "\"iva_imp_det_lit\" numeric(15),\n"
                    + "\"max_fis\" numeric(15,2),\n"
                    + "\"cod_trib_21\" varchar(8) COLLATE \"default\",\n"
                    + "\"anno_cod_trib_21\" int4,\n"
                    + "\"mese_cod_trib_21\" int4,\n"
                    + "\"imp_ra_21\" numeric(15,2),\n"
                    + "\"non_imp_ra_21\" numeric(15,2),\n"
                    + "\"perc_ra_21\" numeric(5,2),\n"
                    + "\"tm_21\" int4,\n"
                    + "\"m_21\" int4,\n"
                    + "\"m2_21\" int4,\n"
                    + "\"m3_21\" int4,\n"
                    + "\"m4_21\" int4,\n"
                    + "\"cnt_21\" varchar(7) COLLATE \"default\",\n"
                    + "\"cod_21\" varchar(5) COLLATE \"default\",\n"
                    + "\"check_rata_personale\" char(1) COLLATE \"default\",\n"
                    + "\"check_calcolatrice\" char(1) COLLATE \"default\",\n"
                    + "\"entro1anno\" numeric(15,2),\n"
                    + "\"oltre1anno\" numeric(15,2),\n"
                    + "\"entro5anni\" numeric(15,2),\n"
                    + "\"oltre5anni\" numeric(15,2),\n"
                    + "\"descrizioneRateo\" varchar(250) COLLATE \"default\",\n"
                    + "\"numRateo\" int4,\n"
                    + "\"oggetto_iidd\" int4,\n"
                    + "\"oggetto_irap\" int4,\n"
                    + "\"oggetto_iva\" int4,\n"
                    + "\"iva_non_imponibile\" numeric(15,2),\n"
                    + "\"iva_perc_non_imponibile\" numeric(5,2),\n"
                    + "\"condizione_pilota\" int4,\n"
                    + "\"data_scadenza_cod_trib\" date,\n"
                    + "\"imponibile_indicativo\" numeric(15,2),\n"
                    + "\"causale_pagamento_ra\" varchar(1) COLLATE \"default\",\n"
                    + "\"perc_riduzione_ra\" numeric(5,2),\n"
                    + "\"riduzione_ra\" numeric(15,2),\n"
                    + "\"num_reg_iva_integrata\" int4,\n"
                    + "\"numeratore_iidd\" int4,\n"
                    + "\"denominatore_iidd\" int4,\n"
                    + "\"numeratore_irap\" int4,\n"
                    + "\"denominatore_irap\" int4,\n"
                    + "\"iva_per_quadratura\" numeric(15,2),\n"
                    + "\"soggetto_iidd\" int4,\n"
                    + "\"soggetto_irap\" int4,\n"
                    + "\"soggetto_iva\" int4,\n"
                    + "\"utente_data\" date,\n"
                    + "\"utente_modifica\" varchar(255) COLLATE \"default\",\n"
                    + "\"utente_data_modifica\" date,\n"
                    + "\"cee_codice_nome\" varchar(255) COLLATE \"default\",\n"
                    + "\"cee_codice_cognome\" varchar(255) COLLATE \"default\",\n"
                    + "\"ragione_sociale\" varchar(255) COLLATE \"default\",\n"
                    + "\"part_iva\" varchar(255) COLLATE \"default\",\n"
                    + "\"fisc_cod\" varchar(255) COLLATE \"default\",\n"
                    + "\"via\" varchar(255) COLLATE \"default\",\n"
                    + "\"descrizione_comune\" varchar(255) COLLATE \"default\",\n"
                    + "\"cap\" varchar(255) COLLATE \"default\",\n"
                    + "\"sigla_provincia\" varchar(255) COLLATE \"default\"\n"
                    + ")\n"
                    + "WITH (OIDS=FALSE)\n"
                    + "\n"
                    + ";";

            Connection conn = Connessione.getConnection(DITTA);
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            System.out.println(preparedStmt);
            ResultSet rs = preparedStmt.executeQuery();

        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }

    
    

}
