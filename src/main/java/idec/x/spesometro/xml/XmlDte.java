/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.xml;

import static idec.x.spesometro.xml.mapTabelle.GetMap_Ditta.getXmlDatiDitta;
import static idec.x.spesometro.xml.mapTabelle.GetMap_IdentFiscali.getIdentificativiFiscali;
import static idec.x.spesometro.xml.mapTabelle.GetMap_RighiDocumento.getRighiConto;
import static idec.x.spesometro.xml.mapTabelle.GetMap_TipoDocumento.getRegistrazioni;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import static idec.x.spesometro.AvviaSpesometro.PATH_FOLDER_SPESOMETRO;

/**
 *
 * @author Luca
 */
public class XmlDte {

    public static boolean creaXml_DTE(String db) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("ns2:DatiFattura");

            Attr attr = doc.createAttribute("versione");
            attr.setValue("DAT20");
            rootElement.setAttributeNode(attr);

            Attr attr2 = doc.createAttribute("xmlns:ns2");
            attr2.setValue("http://ivaservizi.agenziaentrate.gov.it/docs/xsd/fatture/v2.0");
            rootElement.setAttributeNode(attr2);

            doc.appendChild(rootElement);

            //*** crea blocco DTE
            Element DTE = doc.createElement("DTE");
            rootElement.appendChild(DTE);

            String num_trasmissione = "00001";
            String nomeFile = creaBloccoCedentePrestatoreDTE(doc, DTE, db);

            String nomePercorsoFile = PATH_FOLDER_SPESOMETRO + db + "\\IT"
                    .concat(nomeFile)
                    .concat("_DF_")
                    .concat(num_trasmissione)
                    .concat(".xml");

            //FINE DOCUMENTO
            doc.setXmlStandalone(true);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(nomePercorsoFile));
            transformer.transform(source, result);

            System.out.println("*** OK XML CREATO ***");
            
            return true;

            // Output to console for testing
            //StreamResult consoleResult = new StreamResult(System.out);
            //transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String creaBloccoCedentePrestatoreDTE(Document doc, Element DTE, String db) throws Exception {

        String nomeFile = "";

        HashMap hashMap = getXmlDatiDitta(db);
        Element CedentePrestatoreDTE = doc.createElement("CedentePrestatoreDTE");
        DTE.appendChild(CedentePrestatoreDTE);

        Element IdentificativiFiscali = doc.createElement("IdentificativiFiscali");
        CedentePrestatoreDTE.appendChild(IdentificativiFiscali);

        //** id fiscale iva
        if (hashMap.get("PartitaIva").toString().length() == 11
                || hashMap.get("CodiceFiscale").toString().length() == 11) {

            Element IdFiscaleIVA = doc.createElement("IdFiscaleIVA");
            IdentificativiFiscali.appendChild(IdFiscaleIVA);

            Element IdPaese = doc.createElement("IdPaese");
            IdPaese.appendChild(doc.createTextNode("IT"));
            IdFiscaleIVA.appendChild(IdPaese);

            if (hashMap.get("PartitaIva").toString().length() < 11) {
                nomeFile = hashMap.get("CodiceFiscale").toString();

                Element IdCodice = doc.createElement("IdCodice");
                IdCodice.appendChild(doc.createTextNode((String) hashMap.get("CodiceFiscale"))); //2048pc.part_iva
                IdFiscaleIVA.appendChild(IdCodice);
            } else {
                nomeFile = hashMap.get("CodiceFiscale").toString();

                Element IdCodice = doc.createElement("IdCodice");
                IdCodice.appendChild(doc.createTextNode((String) hashMap.get("PartitaIva"))); //2048pc.part_iva
                IdFiscaleIVA.appendChild(IdCodice);
            }
        }

        //codice fiscale
        if (hashMap.get("CodiceFiscale").toString().length() < 11
                && hashMap.get("PartitaIva").toString().length() == 11) {

            Element CodiceFiscale = doc.createElement("CodiceFiscale");
            CodiceFiscale.appendChild(doc.createTextNode((String) hashMap.get("PartitaIva"))); //2048pc.fisc_cod||2048pc.part_iva
            IdentificativiFiscali.appendChild(CodiceFiscale);
        } else {
            Element CodiceFiscale = doc.createElement("CodiceFiscale");
            CodiceFiscale.appendChild(doc.createTextNode((String) hashMap.get("CodiceFiscale"))); //2048pc.fisc_cod||2048pc.part_iva
            IdentificativiFiscali.appendChild(CodiceFiscale);
        }

        Element AltriDatiIdentificativi = doc.createElement("AltriDatiIdentificativi");
        CedentePrestatoreDTE.appendChild(AltriDatiIdentificativi);

        //** ragione_sociale , nome o cognome
        if (hashMap.get("ragione_sociale").toString().equals("")) {
            Element Nome = doc.createElement("Nome");
            Nome.appendChild(doc.createTextNode((String) hashMap.get("Nome"))); //2048pc.ragione_sociale||cee_codice_cognome&&cee_codice_nome
            AltriDatiIdentificativi.appendChild(Nome);

            Element Cognome = doc.createElement("Cognome");
            Cognome.appendChild(doc.createTextNode((String) hashMap.get("Cognome"))); //2048pc.ragione_sociale||cee_codice_cognome&&cee_codice_nome
            AltriDatiIdentificativi.appendChild(Cognome);
        } else {
            Element Denominazione = doc.createElement("Denominazione");
            Denominazione.appendChild(doc.createTextNode((String) hashMap.get("ragione_sociale"))); //2048pc.ragione_sociale||cee_codice_cognome&&cee_codice_nome
            AltriDatiIdentificativi.appendChild(Denominazione);
        }

        Element Sede = doc.createElement("Sede");
        AltriDatiIdentificativi.appendChild(Sede);

        Element Indirizzo = doc.createElement("Indirizzo");
        Indirizzo.appendChild(doc.createTextNode((String) hashMap.get("residenza_via"))); //2053pc.via
        Sede.appendChild(Indirizzo);

        Element CAP = doc.createElement("CAP");
        CAP.appendChild(doc.createTextNode((String) hashMap.get("cap")));//017co.cap
        Sede.appendChild(CAP);

        Element Comune = doc.createElement("Comune");
        Comune.appendChild(doc.createTextNode((String) hashMap.get("descrizione_comune")));//017co.descrizione_comune
        Sede.appendChild(Comune);

        Element Provincia = doc.createElement("Provincia");
        Provincia.appendChild(doc.createTextNode((String) hashMap.get("sigla_provincia")));//017co.sigla_provincia
        Sede.appendChild(Provincia);

        Element Nazione = doc.createElement("Nazione");
        Nazione.appendChild(doc.createTextNode("IT"));
        Sede.appendChild(Nazione);

        //blocco fattura body
        creaBloccoCessionarioCommittenteDTE(doc, DTE, db);

        return nomeFile;
    }

    public static void creaBloccoCessionarioCommittenteDTE(Document doc, Element DTE, String db) throws Exception {

        List<HashMap> listMapIdentificativiFiscali = getIdentificativiFiscali("1", db);
        for (HashMap hashMap : listMapIdentificativiFiscali) {
            Element CessionarioCommittenteDTE = doc.createElement("CessionarioCommittenteDTE");
            DTE.appendChild(CessionarioCommittenteDTE);

            Element IdentificativiFiscali = doc.createElement("IdentificativiFiscali");
            CessionarioCommittenteDTE.appendChild(IdentificativiFiscali);

            //** id fiscale iva
            if (hashMap.get("vendor01_part_iva").toString().length() == 11
                    || hashMap.get("vendor01_cod_fisc").toString().length() == 11) {

                Element IdFiscaleIVA = doc.createElement("IdFiscaleIVA");
                IdentificativiFiscali.appendChild(IdFiscaleIVA);

                Element IdPaese = doc.createElement("IdPaese");
                IdPaese.appendChild(doc.createTextNode("IT"));
                IdFiscaleIVA.appendChild(IdPaese);

                if (hashMap.get("vendor01_part_iva").toString().length() < 11) {
                    Element IdCodice = doc.createElement("IdCodice");
                    IdCodice.appendChild(doc.createTextNode((String) hashMap.get("vendor01_cod_fisc"))); //2048pc.part_iva
                    IdFiscaleIVA.appendChild(IdCodice);
                } else {
                    Element IdCodice = doc.createElement("IdCodice");
                    IdCodice.appendChild(doc.createTextNode(hashMap.get("vendor01_part_iva").toString())); //2048pc.part_iva
                    IdFiscaleIVA.appendChild(IdCodice);
                }
            }

            //codice fiscale
            if (hashMap.get("vendor01_cod_fisc").toString().length() < 11
                    && hashMap.get("vendor01_part_iva").toString().length() == 11) {

                Element CodiceFiscale = doc.createElement("CodiceFiscale");
                CodiceFiscale.appendChild(doc.createTextNode(hashMap.get("vendor01_part_iva").toString())); //2048pc.fisc_cod||2048pc.part_iva
                IdentificativiFiscali.appendChild(CodiceFiscale);
            } else {
                Element CodiceFiscale = doc.createElement("CodiceFiscale");
                CodiceFiscale.appendChild(doc.createTextNode((String) hashMap.get("vendor01_cod_fisc"))); //2048pc.fisc_cod||2048pc.part_iva
                IdentificativiFiscali.appendChild(CodiceFiscale);
            }

            Element AltriDatiIdentificativi = doc.createElement("AltriDatiIdentificativi");
            CessionarioCommittenteDTE.appendChild(AltriDatiIdentificativi);

            //** ragione_sociale , nome o cognome
            if (hashMap.get("vendor01_denominazione").toString().equals("")) {
                Element Nome = doc.createElement("Nome");
                Nome.appendChild(doc.createTextNode((String) hashMap.get("vendor01_nome"))); //2048pc.ragione_sociale||cee_codice_cognome&&cee_codice_nome
                AltriDatiIdentificativi.appendChild(Nome);

                Element Cognome = doc.createElement("Cognome");
                Cognome.appendChild(doc.createTextNode((String) hashMap.get("vendor01_cognome"))); //2048pc.ragione_sociale||cee_codice_cognome&&cee_codice_nome
                AltriDatiIdentificativi.appendChild(Cognome);
            } else {
                Element Denominazione = doc.createElement("Denominazione");
                Denominazione.appendChild(doc.createTextNode((String) hashMap.get("vendor01_denominazione"))); //2048pc.ragione_sociale||cee_codice_cognome&&cee_codice_nome
                AltriDatiIdentificativi.appendChild(Denominazione);
            }

            Element Sede = doc.createElement("Sede");
            AltriDatiIdentificativi.appendChild(Sede);

            Element Indirizzo = doc.createElement("Indirizzo");
            Indirizzo.appendChild(doc.createTextNode((String) hashMap.get("vendor02_via"))); //2053pc.via
            Sede.appendChild(Indirizzo);

            Element CAP = doc.createElement("CAP");
            CAP.appendChild(doc.createTextNode((String) hashMap.get("cap")));//017co.cap
            Sede.appendChild(CAP);

            Element Comune = doc.createElement("Comune");
            Comune.appendChild(doc.createTextNode((String) hashMap.get("descrizione_comune")));//017co.descrizione_comune
            Sede.appendChild(Comune);

            Element Provincia = doc.createElement("Provincia");
            Provincia.appendChild(doc.createTextNode((String) hashMap.get("sigla_provincia")));//017co.sigla_provincia
            Sede.appendChild(Provincia);

            Element Nazione = doc.createElement("Nazione");
            Nazione.appendChild(doc.createTextNode("IT"));
            Sede.appendChild(Nazione);

            //blocco fattura body
            creaBloccoDatyFatturaBodyDTE(doc, CessionarioCommittenteDTE, hashMap, db);
        }
    }

    public static void creaBloccoDatyFatturaBodyDTE(Document doc, Element CessionarioCommittenteDTE, HashMap hashMap, String db) throws Exception {
        List<HashMap> listTipoDocumento = getRegistrazioni("1", db, hashMap);

        for (HashMap map : listTipoDocumento) {
            Element DatiFatturaBodyDTE = doc.createElement("DatiFatturaBodyDTE");
            CessionarioCommittenteDTE.appendChild(DatiFatturaBodyDTE);

            Element DatiGenerali = doc.createElement("DatiGenerali");
            DatiFatturaBodyDTE.appendChild(DatiGenerali);

            Element TipoDocumento = doc.createElement("TipoDocumento");
            TipoDocumento.appendChild(doc.createTextNode((String) map.get("tipo_doc"))); //2044mv.causele *TRASFORMATO FA001=TD01
            DatiGenerali.appendChild(TipoDocumento);

            Element Data = doc.createElement("Data");
            Data.appendChild(doc.createTextNode((String) map.get("data_doc")));//"2044mv.data_doc"
            DatiGenerali.appendChild(Data);

            Element Numero = doc.createElement("Numero");
            Numero.appendChild(doc.createTextNode((String) map.get("num_doc")));//2044mv.doc_n
            DatiGenerali.appendChild(Numero);

            creaBloccoDatiRiepilogoFattDTE(doc, DatiFatturaBodyDTE, map, db);
        }

    }

    public static void creaBloccoDatiRiepilogoFattDTE(Document doc, Element DatiFatturaBodyDTE, HashMap map2, String db) throws Exception {
        List<HashMap> listRigoDocumento = getRighiConto("1", db, map2);
        for (HashMap hashMap : listRigoDocumento) {
            Element DatiRiepilogo = doc.createElement("DatiRiepilogo");
            DatiFatturaBodyDTE.appendChild(DatiRiepilogo);

            Element ImponibileImporto = doc.createElement("ImponibileImporto");
            BigDecimal imp_conto = (BigDecimal) hashMap.get("importo_conto");
            if (imp_conto.compareTo(BigDecimal.ZERO) < 0) {
                imp_conto = imp_conto.multiply(BigDecimal.valueOf(-1));
                hashMap.put("importo_conto", imp_conto);
            }
            ImponibileImporto.appendChild(doc.createTextNode(hashMap.get("importo_conto").toString())); // 2044mv.IMPORTO_EURO_DARE-IMPORTO_EURO_AVERE
            DatiRiepilogo.appendChild(ImponibileImporto);

            Element DatiIVA = doc.createElement("DatiIVA");
            DatiRiepilogo.appendChild(DatiIVA);

            if (hashMap.get("iva_natura").toString().equals("")) {

                Element Imposta = doc.createElement("Imposta");
                BigDecimal iva_imposta = (BigDecimal) hashMap.get("iva_imposta");
                if (iva_imposta.compareTo(BigDecimal.ZERO) < 0) {
                    iva_imposta = iva_imposta.multiply(BigDecimal.valueOf(-1));
                    hashMap.put("iva_imposta", iva_imposta);
                }
                Imposta.appendChild(doc.createTextNode(hashMap.get("iva_imposta").toString())); //Q2** 2044mv.IMPORTO_EURO_DARE-IMPORTO_EURO_AVERE
                DatiIVA.appendChild(Imposta);

                Element Aliquota = doc.createElement("Aliquota");
                Aliquota.appendChild(doc.createTextNode(hashMap.get("iva_perc").toString())); // Q2** 2044mv.iva_perc
                DatiIVA.appendChild(Aliquota);

            } else {
                Element Imposta = doc.createElement("Imposta");
                Imposta.appendChild(doc.createTextNode("0.00")); //Q2** 2044mv.IMPORTO_EURO_DARE-IMPORTO_EURO_AVERE
                DatiIVA.appendChild(Imposta);

                Element Aliquota = doc.createElement("Aliquota");
                Aliquota.appendChild(doc.createTextNode(hashMap.get("iva_perc").toString())); // Q2** 2044mv.iva_perc
                DatiIVA.appendChild(Aliquota);

                Element Natura = doc.createElement("Natura");
                Natura.appendChild(doc.createTextNode(hashMap.get("iva_natura").toString())); //Q2** 2044mv.IMPORTO_EURO_DARE-IMPORTO_EURO_AVERE
                DatiRiepilogo.appendChild(Natura);

            }

        }
    }
}
