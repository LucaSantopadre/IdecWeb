/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ammin.jsf;

import idec.controller.pub.dit00.session.Dit00Facade;
import idec.model.pub.Dit00;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Luca 
 * classe del pannello gestione ditte uso del form
 */
@SessionScoped
@Named
public class GestioneDitteAmminController implements Serializable {

    // *** variabili Form
    private List<Dit00> listaDitte;
    private String codiceDittaForm;
    private String idPaeseIvaForm;
    private String idCodiceIvaForm;
    private String codiceFiscaleForm;
    private String denominazioneForm;
    private String nomeForm;
    private String cognomeForm;
    // FINE variabili Form

    // *** variabili
    @EJB
    private Dit00Facade dit00Facade;
    // FINE variabili ----------------------------------------------------------

    // *** get e set variabili
    public Dit00Facade getDit00Facade() {
        return dit00Facade;
    }

    public void setDit00Facade(Dit00Facade dit00Facade) {
        this.dit00Facade = dit00Facade;
    }

    public List<Dit00> getListaDitte() {
        return listaDitte;
    }

    public void setListaDitte(List<Dit00> listaDitte) {
        this.listaDitte = listaDitte;
    }

    public String getCodiceDittaForm() {
        return codiceDittaForm;
    }

    public void setCodiceDittaForm(String codiceDittaForm) {
        this.codiceDittaForm = codiceDittaForm;
    }

    public String getIdPaeseIvaForm() {
        return idPaeseIvaForm;
    }

    public void setIdPaeseIvaForm(String idPaeseIvaForm) {
        this.idPaeseIvaForm = idPaeseIvaForm;
    }

    public String getIdCodiceIvaForm() {
        return idCodiceIvaForm;
    }

    public void setIdCodiceIvaForm(String idCodiceIvaForm) {
        this.idCodiceIvaForm = idCodiceIvaForm;
    }

    public String getCodiceFiscaleForm() {
        return codiceFiscaleForm;
    }

    public void setCodiceFiscaleForm(String codiceFiscaleForm) {
        this.codiceFiscaleForm = codiceFiscaleForm;
    }

    public String getDenominazioneForm() {
        return denominazioneForm;
    }

    public void setDenominazioneForm(String denominazioneForm) {
        this.denominazioneForm = denominazioneForm;
    }

    public String getNomeForm() {
        return nomeForm;
    }

    public void setNomeForm(String nomeForm) {
        this.nomeForm = nomeForm;
    }

    public String getCognomeForm() {
        return cognomeForm;
    }

    public void setCognomeForm(String cognomeForm) {
        this.cognomeForm = cognomeForm;
    }
    // FINE get e set variabili ------------------------------------------------

    // *** metodo post init
    @PostConstruct
    public void init() {
        listaDitte = dit00Facade.findAll();
    }
    // fine metodo post init

    // *** metodo richiamato per creare la ditta
    public String creaDitta() {
        if (dittaGiaEsistente()) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Attenzione! ",
                            "Attenzione! "));
        } else {
            //crea nuova ditta
            Dit00 nuovaDitta = new Dit00();
            nuovaDitta.setCodiceDitta(codiceDittaForm);
            nuovaDitta.setIdPaeseIva(idPaeseIvaForm);
            nuovaDitta.setIdCodiceIva(idCodiceIvaForm);
            nuovaDitta.setCodiceFiscale(codiceFiscaleForm);
            nuovaDitta.setDenominazione(denominazioneForm);
            nuovaDitta.setNome(nomeForm);
            nuovaDitta.setCognome(cognomeForm);

            dit00Facade.create(nuovaDitta);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Ditta creata con successo!",
                            "Ditta creata con successo!"));
            listaDitte.add(nuovaDitta);
        }
        return "amminMenu";
    }
    // FINE crea Ditta

    // *** controlla dati
    private boolean dittaGiaEsistente() {
        return false;
    }
    // FINE controlla dati

    // *** metodo modifica tabella
    public void onRowEdit(RowEditEvent event) {
        Dit00 dittaModificata = (Dit00) event.getObject();
        dit00Facade.edit(dittaModificata);
    }
    // FINE modifica tabella
}
