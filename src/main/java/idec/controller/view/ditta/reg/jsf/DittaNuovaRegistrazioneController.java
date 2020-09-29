/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.reg.jsf;

import idec.controller.ditta.reg.session.Reg01Facade;
import idec.controller.ditta.reg.session.RegDocFacade;
import idec.model.ditta.Reg01;
import idec.model.ditta.RegDoc;
import idec.model.pub.Libro;
import idec.model.pub.Moneta;
import idec.model.pub.Reparto;
import idec.model.pub.TipoDoc;
import idec.session.pub.session.LibroFacade;
import idec.session.pub.session.MonetaFacade;
import idec.session.pub.session.RepartoFacade;
import idec.session.pub.session.TipoDocFacade;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import static idec.utility.DataUtility.trasformaDataInString_1;

/**
 *
 * @author Luca classe controller del pannello di registrazione
 */
@SessionScoped
@Named
public class DittaNuovaRegistrazioneController implements Serializable {

    // *** variabili 
    // operazione -----------------------------
    private String operazionePanel;
    // ----------------------------------------

    // reparto ---------------------------------
    private Reparto repartoPanel = new Reparto();
    @EJB
    private RepartoFacade repartoFacade;
    private List<Reparto> repartoLista = new ArrayList<>();
    // -----------------------------------------

    // libro ----------------------------------
    private Libro libroPanel = new Libro();
    private List<Libro> libroLista = new ArrayList<>();
    @EJB
    private LibroFacade libroFacade;
    // -----------------------------------------

    // tab reg01 ---------------------------------
    private Reg01 reg01Panel = new Reg01();
    private List<Reg01> reg01Lista = new ArrayList<>();
    @EJB
    private Reg01Facade reg01Facade;
    // ------------------------------------------

//    // tab reg_doc --------------------------------
    private RegDoc regDocPanel = new RegDoc();
    private List<RegDoc> regDocLista = new ArrayList<>();
    @EJB
    private RegDocFacade regDocFacade;
//    // ------------------------------------------

    // tipo doc ---------------------------------
    private TipoDoc tipoDocPanel = new TipoDoc();
    private List<TipoDoc> tipoDocLista = new ArrayList<>();
    @EJB
    private TipoDocFacade tipoDocFacade;
    // ------------------------------------------

    // moneta -----------------------------------
    private Moneta monetaPanel = new Moneta();
    private List<Moneta> monetaLista = new ArrayList<>();
    @EJB
    private MonetaFacade monetaFacade;
    // ------------------------------------------

    // moneta conto -----------------------------
    private Moneta monetaContoPanel = new Moneta();
    //-------------------------------------------

    // FINE variabili ----------------------------------------------------------
    // -------------------------------------------------------------------------
    // *** get e set variabili
    public String getOperazionePanel() {
        return operazionePanel;
    }

    public void setOperazionePanel(String operazionePanel) {
        this.operazionePanel = operazionePanel;
    }

    public Reparto getRepartoPanel() {
        return repartoPanel;
    }

    public void setRepartoPanel(Reparto repartoPanel) {
        this.repartoPanel = repartoPanel;
    }

    public List<Reparto> getRepartoLista() {
        return repartoLista;
    }

    public RegDoc getRegDocPanel() {
        return regDocPanel;
    }

    public void setRegDocPanel(RegDoc regDocPanel) {
        this.regDocPanel = regDocPanel;
    }

    public List<RegDoc> getRegDocLista() {
        return regDocLista;
    }

    public void setRegDocLista(List<RegDoc> regDocLista) {
        this.regDocLista = regDocLista;
    }

    public void setRepartoLista(List<Reparto> repartoLista) {
        this.repartoLista = repartoLista;
    }

    public Libro getLibroPanel() {
        return libroPanel;
    }

    public void setLibroPanel(Libro libroPanel) {
        this.libroPanel = libroPanel;
    }

    public List<Libro> getLibroLista() {
        return libroLista;
    }

    public void setLibroLista(List<Libro> libroLista) {
        this.libroLista = libroLista;
    }

    public Reg01 getReg01Panel() {
        return reg01Panel;
    }

    public void setReg01Panel(Reg01 reg01Panel) {
        this.reg01Panel = reg01Panel;
    }

    public List<Reg01> getReg01Lista() {
        return reg01Lista;
    }

    public void setReg01Lista(List<Reg01> reg01Lista) {
        this.reg01Lista = reg01Lista;
    }

    public TipoDoc getTipoDocPanel() {
        return tipoDocPanel;
    }

    public void setTipoDocPanel(TipoDoc tipoDocPanel) {
        this.tipoDocPanel = tipoDocPanel;
    }

    public List<TipoDoc> getTipoDocLista() {
        return tipoDocLista;
    }

    public void setTipoDocLista(List<TipoDoc> tipoDocLista) {
        this.tipoDocLista = tipoDocLista;
    }


    public Moneta getMonetaPanel() {
        return monetaPanel;
    }

    public void setMonetaPanel(Moneta monetaPanel) {
        this.monetaPanel = monetaPanel;
    }

    public List<Moneta> getMonetaLista() {
        return monetaLista;
    }

    public void setMonetaLista(List<Moneta> monetaLista) {
        this.monetaLista = monetaLista;
    }

    public Moneta getMonetaContoPanel() {
        return monetaContoPanel;
    }

    public void setMonetaContoPanel(Moneta monetaContoPanel) {
        this.monetaContoPanel = monetaContoPanel;
    }

    //FINE get e set variabili -------------------------------------------------
    // -------------------------------------------------------------------------
    // *** metodo post construct
    // inizializzo le variabili in quaso metodo
    @PostConstruct
    public void init() {
        inizializzaReparto();
        inizializzaTipoDoc();
        inizializzaMoneta();
        try {
            inizializzaReg01Lista();
            inizializzaReg02DocLista();
        } catch (ParseException ex) {
            Logger.getLogger(DittaNuovaRegistrazioneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // FINE metodo post construct ----------------------------------------------
    // -------------------------------------------------------------------------

    // *** inizializza il reparto ----------------------------------------------
    private void inizializzaReparto() {
        repartoLista = repartoFacade.findAll();
    }
    // FINE inizializza reparto  -----------------------------------------------

    // *** inizializza tipo doc ------------------------------------------------
    private void inizializzaTipoDoc() {
        tipoDocLista = tipoDocFacade.findAll();
    }
    // FINe inizializza tipo doc -----------------------------------------------

    // *** inizializza moneta --------------------------------------------------
    private void inizializzaMoneta() {
        monetaLista = monetaFacade.findAll();
    }
    //FINE inizializza moneta --------------------------------------------------

    // *** inizializza listaReg01 ----------------------------------------------
    public void inizializzaReg01Lista() throws ParseException {
        reg01Lista.add(reg01Panel);
    }
    // FINE inizializza reg01Lista ---------------------------------------------

    // *** inizializza reg02Lista ----------------------------------------------
    public void inizializzaReg02DocLista() {
        regDocLista.add(regDocPanel);
    }
    // FINE inizializza reg02Lista ---------------------------------------------

    // *** aggiorna libro da reparto -------------------------------------------
    public void aggiornaLibroDaReparto() {
        libroPanel.setLibroDescr("");
        List<Libro> findAllLibro = libroFacade.findAll();
        libroLista.clear();
        for (Libro libro : findAllLibro) {
            if (Objects.equals(repartoPanel.getRepartoId(), libro.getRepartoId().getRepartoId())) {
                libroLista.add(libro);
            }
        }
    }
    // FINE aggiorna libro da reparto ------------------------------------------

    // *** aggiorna data reg output --------------------------------------------
    public void aggiornaDataRegOutput() {
        //reg01Panel.setDataRegString(trasformaDataInString_1(reg01Panel.getDataReg()));
    }
    // FINE aggiorna data reg output -------------------------------------------

    // *** aggiorna data doc output --------------------------------------------
    public void aggiornaDataDocOutput() {
        //regDocPanel.setDataDocString(trasformaDataInString_1(regDocPanel.getDataDoc()));
    }
    // FINE aggiorna data doc output -------------------------------------------

    // *** aggiorna data scad doc output --------------------------------------------
    public void aggiornaDataScadDocOutput() {
        //regDocPanel.setDataScadDocString(trasformaDataInString_1(regDocPanel.getDataScadDoc()));
    }
    // FINE aggiorna data scad doc output -------------------------------------------

    // *** metodo del bottone registra -----------------------------------------
    public void btnRegistraAction() {
        reg01Panel.setReparto(repartoPanel.getRepartoId().toString());
        reg01Panel.setLibro((int)libroPanel.getLibroId());

        reg01Facade.create(reg01Panel);
    }
    // FINE metodo bottone registra --------------------------------------------

    public void btnAvantiAction() {
        System.out.println("");
    }
}
