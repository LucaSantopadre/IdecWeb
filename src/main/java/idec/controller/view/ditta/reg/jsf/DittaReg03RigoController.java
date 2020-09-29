/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.reg.jsf;

import idec.controller.ditta.reg.session.Reg03RigoFacade;
import idec.model.ditta.Reg03Rigo;
import idec.model.ditta.Reg03RigoPK;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import static idec.controller.view.ditta.reg.jsf.DittaReg03RigoSomme.*;
import idec.model.pub.IvaFunzione;
import idec.model.pub.NaturaIva;
import idec.session.pub.session.IvaFunzioneFacade;
import idec.session.pub.session.NaturaIvaFacade;

/**
 *
 * @author Luca classe controller della tabella reg03Rigo del database della
 * ditta
 */
@Named
@SessionScoped
public class DittaReg03RigoController implements Serializable {

    // *** variabili
    int tabIndex = 0;

    Reg03Rigo rigoSelezionato = new Reg03Rigo();

    // tab reg02 --------------------------------
    private Reg03Rigo reg03RigoPanel = new Reg03Rigo();
    private List<Reg03Rigo> reg03RigoLista = new ArrayList<>();
    @EJB
    private Reg03RigoFacade reg03RigoFacade;
    // ------------------------------------------

    // tab iva_funzione -------------------------
    private IvaFunzione ivaFunzPanel = new IvaFunzione();
    private List<IvaFunzione> ivaFunzLista = new ArrayList<>();
    @EJB
    private IvaFunzioneFacade ivaFunzFacade;
    // ------------------------------------------

    // tab natura -------------------------------
    private NaturaIva naturaIvaPanel = new NaturaIva();
    private List<NaturaIva> naturaIvaList = new ArrayList<>();
    @EJB
    private NaturaIvaFacade naturaIvaFacade;
    // FINE variabili ----------------------------------------------------------

    // get e set
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

    public List<IvaFunzione> getIvaFunzLista() {
        return ivaFunzLista;
    }

    public void setIvaFunzLista(List<IvaFunzione> ivaFunzLista) {
        this.ivaFunzLista = ivaFunzLista;
    }

    public NaturaIva getNaturaIvaPanel() {
        return naturaIvaPanel;
    }

    public void setNaturaIvaPanel(NaturaIva naturaIvaPanel) {
        this.naturaIvaPanel = naturaIvaPanel;
    }

    public List<NaturaIva> getNaturaIvaList() {
        return naturaIvaList;
    }

    public void setNaturaIvaList(List<NaturaIva> naturaIvaList) {
        this.naturaIvaList = naturaIvaList;
    }
    // FINE get e set ----------------------------------------------------------

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

            reg03Rigo.setReg03RigoPK(reg03RigoPK1);
            reg03RigoLista.add(reg03Rigo);
        }

        // inizializzo i campi
        inizializzaIvaFunzione();
        inizializzaNaturaIva();
    }
    // FINE metodo post construct ----------------------------------------------

    // *** inizializza iva_funzione
    private void inizializzaIvaFunzione() {
        ivaFunzLista = ivaFunzFacade.findAll();
    }
    // FINE inizializza funzione -----------------------------------------------

    // *** inizializza naturaIva
    private void inizializzaNaturaIva() {
        naturaIvaList = naturaIvaFacade.findAll();
    }
    // FINE inizializza natura iva ---------------------------------------------

    // *** somma dare avere
    public void aggiornaSommaDareAvere() {
        for (Reg03Rigo reg03Rigo : reg03RigoLista) {
            BigDecimal dareLux = reg03Rigo.getDare();
            BigDecimal avereLux = reg03Rigo.getAvere();
            BigDecimal somma;
            somma = dareLux.subtract(avereLux);
            reg03Rigo.setImportoConto(somma);
        }
    }
    // FINE somma dare avere ---------------------------------------------------

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

    // *** aggiorna funz iva Panel
    public void aggiornaFunzIvaPanel(Reg03Rigo rigo) {
        rigo.setFunIva(ivaFunzPanel.getIvaFunzId().toString());
    }
    // FINE aggiorna funz iva panel --------------------------------------------

    // *** aggiorna natura iva panel 
    public void aggiornaNaturaIvaPanel(Reg03Rigo rigo) {
        rigo.setIvaNatura(naturaIvaPanel.getNaturaIvaId().toString());
    }
    // FINE aggiorna natura iva panel ------------------------------------------

    // *** matodo per aggiungere subrighi
    // *** NATURA = N6
    public void actionAggiungiSubRighiNatura_N6(Reg03Rigo oggetto) {
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

//        // indice nella lista del rigo selezionato
//        int indiceRigo = reg03RigoLista.indexOf(oggetto);
//        // prendo il rigo selezionato
//        Reg03RigoPK pkRigoSel = oggetto.getReg03RigoPK();
//        // prendo le percentuali per le condizioni
//        BigDecimal percIvaSel = oggetto.getPercIva();
//        BigDecimal percDetIvaSel = oggetto.getPercDetIva();
//        BigDecimal percIvaDedSel = oggetto.getPercIvaDeducibile();
//        // *** caso 1 : ivaDeT = 100%  ,  ivaDeD = 100 %
//        // *** importo conto * percDoc * percIvaDeT=100% * percIvaDeD=100%
//        if (percIvaSel.compareTo(BigDecimal.ZERO) > 0
//                && percDetIvaSel.compareTo(BigDecimal.valueOf(100)) == 0
//                && percIvaDedSel.compareTo(BigDecimal.valueOf(100)) == 0) {
//            // creo nuovo rigo
//            Reg03Rigo nuovoRigo = new Reg03Rigo();
//            Reg03RigoPK nuovoRigoPK = new Reg03RigoPK();
//            nuovoRigoPK.setReg03RigoRegId(pkRigoSel.getReg03RigoRegId());
//            nuovoRigoPK.setReg03SubrigoRegId(pkRigoSel.getReg03SubrigoRegId() + 1);
//            nuovoRigo.setReg03RigoPK(nuovoRigoPK);
//            
//            //eseguiProdottoIva_1(oggetto,nuovoRigo);
//            eseguiProdottoIva(oggetto);
//            
//            reg03RigoLista.add(indiceRigo + 1, nuovoRigo);
//        }
    }
    // FINE metodo aggiunge subrighi REPARTO IVA -------------------------------

//    // *** aggiunge 2 subrighi alla tabella se PAGATO
//    private void aggiungiSubrighiRepartoIva_1(Reg03Rigo oggetto) {
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
//    }
//    // FINE aggiunge 2 subrighi se PAGATO---------------------------------------
    
    // *** bottone avanti
    public void btnAvantiAction() {
        long reg03RigoRegId = reg03RigoLista.size();
        //oggetto.getReg03RigoPK().getReg03RigoRegId();

        Reg03Rigo nuovoRigo = new Reg03Rigo();
        Reg03RigoPK nuovoRigoPK = new Reg03RigoPK();
        nuovoRigoPK.setReg03RigoRegId(reg03RigoRegId);
        nuovoRigo.setReg03RigoPK(nuovoRigoPK);

        reg03RigoLista.add(nuovoRigo);
        tabIndex++;
    }
    // FINE bottone avanti -----------------------------------------------------

    // *** bottone indietro
    public void btnIndietroAction() {
        tabIndex--;
    }
    // FINE bottone indietro ---------------------------------------------------
}
