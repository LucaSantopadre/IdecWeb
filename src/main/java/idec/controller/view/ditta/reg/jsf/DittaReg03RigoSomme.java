/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.reg.jsf;

import idec.model.ditta.Reg03Rigo;
import idec.model.ditta.Reg03RigoPK;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luca classe che esegue le somme nella tabella reg03
 */
public class DittaReg03RigoSomme {

    // *** subrigo 1 crea dare oppure avere
    public static void eseguiAzioneSubRigo1(Reg03Rigo rigoPadre, Reg03Rigo nuovoRigo) {
        BigDecimal importoContoPadre = rigoPadre.getImportoConto();
        if (importoContoPadre.compareTo(BigDecimal.ZERO) > 0) {
            nuovoRigo.setAvere(importoContoPadre);
        } else {
            nuovoRigo.setDare(importoContoPadre.multiply(BigDecimal.valueOf(-1)));
        }
        nuovoRigo.setImportoConto(importoContoPadre.multiply(BigDecimal.valueOf(-1)));
    }
    // FINE subrigo 1 crea dare oppure avere -----------------------------------

    // *** subrigo 2 crea dare oppure avere 
    public static void eseguiAzioneSubRigo2(Reg03Rigo rigoPadre, Reg03Rigo nuovoRigo) {
        BigDecimal importoContoPadre = rigoPadre.getImportoConto();
        BigDecimal darePadre = rigoPadre.getDare();
        BigDecimal averePadre = rigoPadre.getAvere();

        nuovoRigo.setDare(darePadre);
        nuovoRigo.setAvere(averePadre);
        nuovoRigo.setImportoConto(importoContoPadre);
    }
    // FINE subrigo 2 ----------------------------------------------------------

//    // *** esegue il prodotto con la 1 condizione
//    // *** caso 1 : ivaDeT = 100%  ,  ivaDeD = 100 %
//    // *** importo conto * percDoc * percIvaDeD=100% * percIvaDeT=100%
//    // ***   100             22  = 22        100   = 22      = 22
//    // *** crea solo 1 subrigo = conto = ER200037
//    public static void eseguiProdottoIva_1(Reg03Rigo rigoPadre, Reg03Rigo nuovoRigo) {
//        BigDecimal importoConto = rigoPadre.getImportoConto();
//        BigDecimal percIvaDoc = rigoPadre.getPercIva();
//        BigDecimal percDetIva = rigoPadre.getPercDetIva();
//        BigDecimal percIvaDed = rigoPadre.getPercIvaDeducibile();
//
//        BigDecimal iva20037Detraibile;
//        iva20037Detraibile = importoConto.multiply(percIvaDoc).divide(BigDecimal.valueOf(100));
//        iva20037Detraibile.multiply(percIvaDed).divide(BigDecimal.valueOf(100));
//        iva20037Detraibile.multiply(percDetIva).divide(BigDecimal.valueOf(100));
//
//        System.out.println(iva20037Detraibile);
//        if (importoConto.compareTo(BigDecimal.ZERO) > 0) {
//            nuovoRigo.setDare(iva20037Detraibile);
//        } else {
//            nuovoRigo.setAvere(iva20037Detraibile);
//        }
//    }
//    // -------------------------------------------------------------------------
    // *** esegue il prodotto con la 1 condizione
    // *** caso 1 : ivaDeT = 100%  ,  ivaDeD = 100 %
    // *** importo conto * percDoc * percIvaDeD=30% *       percIvaDeT=50%
    // ***1   100             22  = 22                        
    // ***                          22    
    // ***                          
    // ***                          rigo1 =      11    22<11> = 11     
    // ***                                                  11 *  50% = 5,5
    // ***                                          rigo2 5,5 = iva 2037DET
    // ***                                          rigo3 5,5 = conto stampante
    // *** crea subrighi
    public static void eseguiProdottoIva(Reg03Rigo rigoPadre, List<Reg03Rigo> lista) {
        // elimino subrighi 
        List<Reg03Rigo> iteraLista = new ArrayList<>();
        iteraLista.addAll(lista);
        for (Reg03Rigo subEsiste : iteraLista) {
            if (subEsiste.getReg03RigoPK().getReg03SubrigoRegId() > 2) {
                lista.remove(subEsiste);
            }
        }

        // indice nella lista del rigo selezionato
        int indiceRigo = lista.indexOf(rigoPadre) + 1;
        // prendo il rigo selezionato
        Reg03RigoPK pkRigoSel = rigoPadre.getReg03RigoPK();
        long numSubRigo = 3;

        BigDecimal importoConto = rigoPadre.getImportoConto();
        BigDecimal percIvaDoc = rigoPadre.getPercIva();
        BigDecimal percDetIva = rigoPadre.getPercDetIva();
        BigDecimal percIvaDed = rigoPadre.getPercIvaDeducibile();

        BigDecimal ivaDoc;
        BigDecimal percDifIvaDed;
        BigDecimal ivaNonDed;
        BigDecimal restoIvaDet;
        BigDecimal iva20037Detraibile;
        BigDecimal NomeContoIvaNonDetraibile;

        ivaDoc = importoConto.multiply(percIvaDoc).divide(BigDecimal.valueOf(100));

        if (ivaDoc.compareTo(BigDecimal.ZERO) < 0) {
            ivaDoc = ivaDoc.multiply(BigDecimal.valueOf(-1));
        }
        percDifIvaDed = BigDecimal.valueOf(100).subtract(percIvaDed);
        ivaNonDed = percDifIvaDed.multiply(ivaDoc).divide(BigDecimal.valueOf(100));

        restoIvaDet = ivaDoc.subtract(ivaNonDed);

        iva20037Detraibile = restoIvaDet.multiply(percDetIva).divide(BigDecimal.valueOf(100));
        NomeContoIvaNonDetraibile = restoIvaDet.subtract(iva20037Detraibile);

        if (percDifIvaDed.compareTo(BigDecimal.ZERO) == 0) {
            // non creo rigo se differenza = 0(percDed=100 - percDedDigitata=100)

        } else {
            //creo rigo per 70(differenza tra 100 e deducibile 30) 
            Reg03Rigo nuovoRigo = new Reg03Rigo();
            Reg03RigoPK nuovoRigoPK = new Reg03RigoPK();

            nuovoRigoPK.setReg03RigoRegId(pkRigoSel.getReg03RigoRegId());
            nuovoRigoPK.setReg03SubrigoRegId(numSubRigo);
            nuovoRigo.setReg03RigoPK(nuovoRigoPK);

            if (importoConto.compareTo(BigDecimal.ZERO) < 0) {
                nuovoRigo.setAvere(ivaNonDed);
                nuovoRigo.setImportoConto(ivaNonDed.multiply(BigDecimal.valueOf(-1)));
            } else {
                nuovoRigo.setDare(ivaNonDed);
                nuovoRigo.setImportoConto(ivaNonDed);
            }

            lista.add(indiceRigo, nuovoRigo);
            indiceRigo++;
            numSubRigo++;
        }

        if (iva20037Detraibile.compareTo(BigDecimal.ZERO) > 0) {
            // creo rigo iva 20037
            Reg03Rigo nuovoRigo = new Reg03Rigo();
            Reg03RigoPK nuovoRigoPK = new Reg03RigoPK();

            nuovoRigoPK.setReg03RigoRegId(pkRigoSel.getReg03RigoRegId());
            nuovoRigoPK.setReg03SubrigoRegId(numSubRigo);
            nuovoRigo.setReg03RigoPK(nuovoRigoPK);

            if (importoConto.compareTo(BigDecimal.ZERO) < 0) {
                nuovoRigo.setAvere(iva20037Detraibile);
                nuovoRigo.setImportoConto(iva20037Detraibile.multiply(BigDecimal.valueOf(-1)));
            } else {
                nuovoRigo.setDare(iva20037Detraibile);
                nuovoRigo.setImportoConto(iva20037Detraibile);
            }
            // aggiungo conto ER20037
            
            lista.add(indiceRigo, nuovoRigo);
            indiceRigo++;
            numSubRigo++;

        }

        if (NomeContoIvaNonDetraibile.compareTo(BigDecimal.ZERO) > 0) {
            // crea rigo = conto
            Reg03Rigo nuovoRigo = new Reg03Rigo();
            Reg03RigoPK nuovoRigoPK = new Reg03RigoPK();

            nuovoRigoPK.setReg03RigoRegId(pkRigoSel.getReg03RigoRegId());
            nuovoRigoPK.setReg03SubrigoRegId(numSubRigo);
            nuovoRigo.setReg03RigoPK(nuovoRigoPK);

            if (importoConto.compareTo(BigDecimal.ZERO) < 0) {
                nuovoRigo.setAvere(NomeContoIvaNonDetraibile);
                nuovoRigo.setImportoConto(NomeContoIvaNonDetraibile.multiply(BigDecimal.valueOf(-1)));
            } else {
                nuovoRigo.setDare(NomeContoIvaNonDetraibile);
                nuovoRigo.setImportoConto(NomeContoIvaNonDetraibile);
            }
            lista.add(indiceRigo, nuovoRigo);
            indiceRigo++;
            numSubRigo++;
        }
        System.out.println("");
    }
    // FINE metodo crea righi iva ----------------------------------------------
    // -------------------------------------------------------------------------

    // *** crea righi per natura = N&
    // *** righi conto er20037 dare e avere , SI ANNULLANO
    public static void eseguiProdottoIvaNatura_N6(Reg03Rigo rigoPadre, List<Reg03Rigo> lista) {
        // elimino subrighi 
        List<Reg03Rigo> iteraLista = new ArrayList<>();
        iteraLista.addAll(lista);
        for (Reg03Rigo subEsiste : iteraLista) {
            if (subEsiste.getReg03RigoPK().getReg03SubrigoRegId() > 2) {
                lista.remove(subEsiste);
            }
        }
        // indice nella lista del rigo selezionato
        int indiceRigo = lista.indexOf(rigoPadre) + 1;
        // prendo il rigo selezionato
        Reg03RigoPK pkRigoSel = rigoPadre.getReg03RigoPK();
        long numSubRigo = 6;
        BigDecimal importoConto = rigoPadre.getImportoConto();
        BigDecimal percIvaDoc = rigoPadre.getPercIva();

        // calcolo ivaDOc
        BigDecimal ivaDoc;
        ivaDoc = importoConto.multiply(percIvaDoc).divide(BigDecimal.valueOf(100));
        if (ivaDoc.compareTo(BigDecimal.ZERO) < 0) {
            ivaDoc = ivaDoc.multiply(BigDecimal.valueOf(-1));
        }

        // 1 rigo
        Reg03Rigo nuovoRigo_1 = new Reg03Rigo();
        Reg03RigoPK nuovoRigoPK_1 = new Reg03RigoPK();
        nuovoRigoPK_1.setReg03RigoRegId(pkRigoSel.getReg03RigoRegId());
        nuovoRigoPK_1.setReg03SubrigoRegId(numSubRigo);
        nuovoRigo_1.setReg03RigoPK(nuovoRigoPK_1);
        if (importoConto.compareTo(BigDecimal.ZERO) < 0) {
            nuovoRigo_1.setAvere(ivaDoc);
            nuovoRigo_1.setImportoConto(ivaDoc.multiply(BigDecimal.valueOf(-1)));
        } else {
            nuovoRigo_1.setDare(ivaDoc);
            nuovoRigo_1.setImportoConto(ivaDoc);
        }
        lista.add(indiceRigo, nuovoRigo_1);
        indiceRigo++;
        numSubRigo++;

        // 2 rigo
        Reg03Rigo nuovoRigo_2 = new Reg03Rigo();
        Reg03RigoPK nuovoRigoPK_2 = new Reg03RigoPK();
        nuovoRigoPK_2.setReg03RigoRegId(pkRigoSel.getReg03RigoRegId());
        nuovoRigoPK_2.setReg03SubrigoRegId(numSubRigo);
        nuovoRigo_2.setReg03RigoPK(nuovoRigoPK_2);

        if (importoConto.compareTo(BigDecimal.ZERO) < 0) {
            nuovoRigo_2.setDare(ivaDoc);
            nuovoRigo_2.setImportoConto(ivaDoc);
        } else {
            nuovoRigo_2.setAvere(ivaDoc);
            nuovoRigo_2.setImportoConto(ivaDoc.multiply(BigDecimal.valueOf(-1)));
        }
        lista.add(indiceRigo, nuovoRigo_2);

    }
    // FINE metodo righi natura N6 ---------------------------------------------
    // -------------------------------------------------------------------------
}
