/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.reg.jsf;

import idec.model.ditta.Reg03Rigo;
import idec.model.ditta.Reg03RigoPK;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import static idec.controller.view.ditta.reg.jsf.DittaReg03RigoSomme.*;
import idec.model.pub.IvaFunzione;
import idec.model.pub.NaturaIva;
import idec.model.pub.pdc.M1;

/**
 *
 * @author Luca classe controller della tabella reg03Rigo del database della
 * ditta
 */
@Named
@SessionScoped
public class DittaReg03Rigo_FORM_Controller implements Serializable {

    // *** variabili
    int tabIndex = 0;

    Reg03Rigo rigoSelezionato = new Reg03Rigo();

    private List<Reg03Rigo> reg03Rigo_ListaCompleta = new ArrayList<>();

    // tab reg03 --------------------------------
    private Reg03Rigo reg03RigoPanel = new Reg03Rigo();
    private List<Reg03Rigo> reg03RigoLista = new ArrayList<>();
    // ------------------------------------------

    // variabili pannello -------------------------
    private IvaFunzione ivaFunzPanel = new IvaFunzione();
    private NaturaIva naturaIvaPanel = new NaturaIva();

    private BigDecimal percIvaPanel = BigDecimal.valueOf(22);
    private BigDecimal percIvaDeducibilePanel = BigDecimal.valueOf(100);
    private BigDecimal percDetIvaPanel = BigDecimal.valueOf(100);
    // ------------------------------------------

    // FINE variabili ----------------------------------------------------------
    // -------------------------------------------------------------------------
    // ***TEST LUCA
    private List<M1> listaM1 = new ArrayList<>();

    public List<M1> getListaM1() {
        return listaM1;
    }

    public void setListaM1(List<M1> listaM1) {
        this.listaM1 = listaM1;
    }

    // FINE TEST LUCA ----------------------------------------------------------
    // *** metodo post construct
    // inizializzo le variabili in quaso metodo
    @PostConstruct
    public void init() {        
        for (int i = 0; i < 1; i++) {
            Reg03Rigo reg03Rigo = new Reg03Rigo();
            Reg03RigoPK reg03RigoPK1 = new Reg03RigoPK();
            reg03RigoPK1.setReg03RigoRegId(i);
            reg03RigoPK1.setReg03SubrigoRegId(0);

            reg03Rigo.setPagatoDoc(false);
            reg03Rigo.setIvaNatura("0");

            reg03Rigo.setReg03RigoPK(reg03RigoPK1);
            reg03RigoLista.add(reg03Rigo);
        }
    }
    // FINE metodo post construct ----------------------------------------------
    // -------------------------------------------------------------------------

    //  *** get e set
    public BigDecimal getPercIvaPanel() {
        return percIvaPanel;
    }

    public void setPercIvaPanel(BigDecimal percIvaPanel) {
        this.percIvaPanel = percIvaPanel;
    }

    public List<Reg03Rigo> getReg03Rigo_ListaCompleta() {
        return reg03Rigo_ListaCompleta;
    }

    public void setReg03Rigo_ListaCompleta(List<Reg03Rigo> reg03Rigo_ListaCompleta) {
        this.reg03Rigo_ListaCompleta = reg03Rigo_ListaCompleta;
    }

    public BigDecimal getPercIvaDeducibilePanel() {
        return percIvaDeducibilePanel;
    }

    public void setPercIvaDeducibilePanel(BigDecimal percIvaDeducibilePanel) {
        this.percIvaDeducibilePanel = percIvaDeducibilePanel;
    }

    public BigDecimal getPercDetIvaPanel() {
        return percDetIvaPanel;
    }

    public void setPercDetIvaPanel(BigDecimal percDetIvaPanel) {
        this.percDetIvaPanel = percDetIvaPanel;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public Reg03Rigo getReg03RigoPanel() {
        return reg03RigoPanel;
    }

    public void setReg03RigoPanel(Reg03Rigo reg03RigoPanel) {
        this.reg03RigoPanel = reg03RigoPanel;
    }

    public List<Reg03Rigo> getReg03RigoLista() {
        return reg03RigoLista;
    }

    public void setReg03RigoLista(List<Reg03Rigo> reg03RigoLista) {
        this.reg03RigoLista = reg03RigoLista;
    }

    public IvaFunzione getIvaFunzPanel() {
        return ivaFunzPanel;
    }

    public void setIvaFunzPanel(IvaFunzione ivaFunzPanel) {
        this.ivaFunzPanel = ivaFunzPanel;
    }

    public NaturaIva getNaturaIvaPanel() {
        return naturaIvaPanel;
    }

    public void setNaturaIvaPanel(NaturaIva naturaIvaPanel) {
        this.naturaIvaPanel = naturaIvaPanel;
    }
    // FINE get e set ----------------------------------------------------------
    // -------------------------------------------------------------------------

    // *** aggiorna funz iva Panel
    public void aggiornaFunzIvaPanel(Reg03Rigo rigo) {
        //rigo.setFunIva(ivaFunzPanel.getIvaFunzId().toString());
    }
    // FINE aggiorna funz iva panel --------------------------------------------

    // *** aggiorna natura iva panel 
    public void aggiornaNaturaIva(Reg03Rigo rigo) {
        rigo.setIvaNatura(naturaIvaPanel.getNaturaIvaId().toString());
    }
    // FINE aggiorna natura iva panel ------------------------------------------

    // *** aggiorna dare panel 
    public void aggiornaDarePanel(Reg03Rigo rigo) {
        rigo.setImportoConto(rigo.getDare().subtract(rigo.getAvere()));
    }
    // FINE aggiorna dare panel ------------------------------------------------

    // *** aggiorna avere panel 
    public void aggiornaAverePanel(Reg03Rigo rigo) {
        rigo.setImportoConto(rigo.getDare().subtract(rigo.getAvere()));
    }
    // FINE aggiorna avere panel -----------------------------------------------

    // *** aggiorna perc iva
    public void aggiornaPercIvaPanel(Reg03Rigo rigo) {
        actionAggiungiSubRighiNatura_N6(rigo);
    }
    // FINE aggiorna perc iva panel --------------------------------------------

    // *** aggiorna perc iva DeD
    public void aggiornaPercIvaDeducibilePanel(Reg03Rigo rigo) {

    }
    // FINE aggiorna perc iva DeD panel ----------------------------------------

    // *** aggiorna perc iva DeT
    public void aggiornaPercDetIvaPanel(Reg03Rigo rigo) {
        actionAggiungiSubRighiIva(rigo);
    }
    // FINE aggiorna perc iva DeT panel ----------------------------------------

    // FINE aggiorna campi dell oggetto ----------------------------------------
    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    // *** aggiunge 2 subrighi alla tabella se PAGATO
    public void aggiungiSubrighiPagato(Reg03Rigo oggetto) {
//        if (oggetto.isPagatoDoc() && oggetto.getReg03RigoPK().getReg03SubrigoRegId() == 0) {
//            // indice nella lista del rigo selezionato
//            int indiceRigo = reg03RigoLista.indexOf(oggetto);
//            // prendo il rigo selezionato
//            Reg03RigoPK pkRigoSelezionato = oggetto.getReg03RigoPK();
//
//            // creo nuovo rigo e aggiungo voci rigo e subrigo+1
//            Reg03Rigo nuovoRigo1 = new Reg03Rigo();
//            Reg03RigoPK pkNuovoRigo1 = new Reg03RigoPK();
//            pkNuovoRigo1.setReg03RigoRegId(pkRigoSelezionato.getReg03RigoRegId());
//            pkNuovoRigo1.setReg03SubrigoRegId(pkRigoSelezionato.getReg03SubrigoRegId() + 1);
//            nuovoRigo1.setReg03RigoPK(pkNuovoRigo1);
//            eseguiAzioneSubRigo1(oggetto, nuovoRigo1);
//
//            // creo nuovo rigo e aggiungo voci rigo e subrigo+1
//            Reg03Rigo nuovoRigo2 = new Reg03Rigo();
//            Reg03RigoPK pkNuovoRigo2 = new Reg03RigoPK();
//            pkNuovoRigo2.setReg03RigoRegId(pkRigoSelezionato.getReg03RigoRegId());
//            pkNuovoRigo2.setReg03SubrigoRegId(pkRigoSelezionato.getReg03SubrigoRegId() + 2);
//            nuovoRigo2.setReg03RigoPK(pkNuovoRigo2);
//            eseguiAzioneSubRigo2(oggetto, nuovoRigo2);
//
//            // aggiungo nuovoRigo alla lista   
//            reg03RigoLista.add(indiceRigo + 1, nuovoRigo1);
//            reg03RigoLista.add(indiceRigo + 2, nuovoRigo2);
//        } else {
//            if (!oggetto.isPagatoDoc() && oggetto.getReg03RigoPK().getReg03SubrigoRegId() == 0) {
//                eliminaSubrighiPagato(oggetto);
//            }
//        }
    }
    // FINE aggiunge 2 subrighi se PAGATO---------------------------------------

    // *** elimina 2 subrighi se PAGATO== false
    private void eliminaSubrighiPagato(Reg03Rigo oggetto) {
        List<Reg03Rigo> listaAppoggio = new ArrayList<>();
        listaAppoggio.addAll(reg03RigoLista);

        // prendo il rigo selezionato
        Reg03RigoPK pkRigoSel = oggetto.getReg03RigoPK();
        long rigoSel = pkRigoSel.getReg03RigoRegId();
        long subRigoSel = pkRigoSel.getReg03SubrigoRegId();
        // scorro la lista dei righi
        for (Reg03Rigo reg03Rigo : listaAppoggio) {
            // elimino solo i subrighi --> del subrigo 0
            if (rigoSel == reg03Rigo.getReg03RigoPK().getReg03RigoRegId()
                    && subRigoSel != reg03Rigo.getReg03RigoPK().getReg03SubrigoRegId()) {
                reg03RigoLista.remove(reg03Rigo);
            }
        }
    }
    // FINE elimina 2 subrighi se PAGATO== false -------------------------------

    // *** matodo per aggiungere subrighi
    // *** NATURA = N6
    public void actionAggiungiSubRighiNatura_N6(Reg03Rigo oggetto) {
        // elimino subrighi 
        List<Reg03Rigo> iteraLista = new ArrayList<>();
        iteraLista.addAll(reg03RigoLista);
        for (Reg03Rigo subEsiste : iteraLista) {
            if (subEsiste.getReg03RigoPK().getReg03SubrigoRegId() > 2) {
                reg03RigoLista.remove(subEsiste);
            }
        }
        if (oggetto.getIvaNatura().equals("6") && oggetto.getPercIva().compareTo(BigDecimal.ZERO) > 0) {
            eseguiProdottoIvaNatura_N6(oggetto, reg03RigoLista);
        }
    }
    // FINE aggiunge subrighi natura = N6 --------------------------------------

    // *** metodo per aggiungere i subrighi REPARTO IVA
    public void actionAggiungiSubRighiIva(Reg03Rigo oggetto) {
        if (!oggetto.getIvaNatura().equals("0")) {

        } else {
            eseguiProdottoIva(oggetto, reg03RigoLista);
            
        }
    }
    // FINE metodo aggiunge subrighi REPARTO IVA -------------------------------

    // *** bottone indietro
    public void btnIndietroAction() {
        tabIndex--;
    }
    // FINE bottone indietro ---------------------------------------------------

    public void aggiungiRigo() {
        Reg03Rigo nuovoRigo = new Reg03Rigo();
        Reg03RigoPK nuovoRigoPK = new Reg03RigoPK();

        reg03Rigo_ListaCompleta.addAll(reg03RigoLista);
        long numRigo = 0;
        for (Reg03Rigo reg03Rigo : reg03Rigo_ListaCompleta) {
            if (reg03Rigo.getReg03RigoPK().getReg03RigoRegId() > numRigo) {
                numRigo = reg03Rigo.getReg03RigoPK().getReg03RigoRegId() + 1;
            }
            if (reg03Rigo.getReg03RigoPK().getReg03RigoRegId() == numRigo) {
                numRigo++;
            }
        }

        nuovoRigoPK.setReg03RigoRegId(numRigo);
        nuovoRigoPK.setReg03SubrigoRegId(0);
        nuovoRigo.setReg03RigoPK(nuovoRigoPK);

        reg03RigoLista.add(nuovoRigo);
        reg03Rigo_ListaCompleta.add(nuovoRigo);

        for (Reg03Rigo iterato : reg03Rigo_ListaCompleta) {
            if (iterato.getReg03RigoPK().getReg03RigoRegId() != nuovoRigoPK.getReg03RigoRegId()) {
                reg03RigoLista.remove(iterato);
            }
        }
    }
}
