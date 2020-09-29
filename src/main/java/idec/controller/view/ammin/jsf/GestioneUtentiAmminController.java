/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ammin.jsf;

import idec.controller.pub.dit00.session.Dit00Facade;
import idec.controller.pub.ute00.session.Ute00Facade;
import idec.model.pub.Dit00;
import idec.model.pub.Ute00;
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
 * @author Luca classe del pannello gestione utenti uso del form
 */
@SessionScoped
@Named
public class GestioneUtentiAmminController implements Serializable {

    // *** variabili Form
    private String email;
    private String password;
    private List<Ute00> listaUtenti;
    private List<Dit00> listaDitte;
    private String[] codiciDitte;
    // FINE variabili Form

    // *** variabili
    @EJB
    private Ute00Facade ute00Facade;
    @EJB
    private Dit00Facade dit00Facade;
    // FINE variabili ----------------------------------------------------------

    // *** get e set variabili
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Ute00> getListaUtenti() {
        return listaUtenti;
    }

    public void setListaUtenti(List<Ute00> listaUtenti) {
        this.listaUtenti = listaUtenti;
    }

    public List<Dit00> getListaDitte() {
        return listaDitte;
    }

    public void setListaDitte(List<Dit00> listaDitte) {
        this.listaDitte = listaDitte;
    }

    public String[] getCodiciDitte() {
        return codiciDitte;
    }

    public void setCodiciDitte(String[] codiciDitte) {
        this.codiciDitte = codiciDitte;
    }
    // FINE get e set variabili ------------------------------------------------

    // *** metodo post init
    @PostConstruct
    public void init() {
        listaUtenti = ute00Facade.findAll();
        // prendo solo i codici dalle ditte
        listaDitte = dit00Facade.findAll();
        int numDitte = listaDitte.size();
        codiciDitte = new String[numDitte];
        for (int i = 0; i < numDitte; i++) {
            codiciDitte[i] = listaDitte.get(i).getCodiceDitta();
        }
    }
    // fine metodo post init

    // *** metodo richiamato per creare l'utente
    public String creaUtente() {
        if (utenteGiaEsistente()) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Attenzione! Email utente già esistente.",
                            "Attenzione! Email utente già esistente."));
        } else {
            //crea nuovo utente
            Ute00 nuovoUtente = new Ute00();
            nuovoUtente.setEmail(email);
            nuovoUtente.setPassword(password);
            nuovoUtente.setDitteVisibili("");
            ute00Facade.create(nuovoUtente);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Utente creato con successo!",
                            "Utente creato con successo!"));
            listaUtenti.add(nuovoUtente);
        }
        return "amminMenu";
    }
    // FINE crea Utente

    // *** controlla dati
    private boolean utenteGiaEsistente() {
        for (Ute00 ute00 : ute00Facade.findAll()) {
            if (ute00.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    // FINE controlla dati

    // *** metodo modifica tabella
    public void onRowEdit(RowEditEvent event) {
        Ute00 utenteModificato = (Ute00) event.getObject();
        ute00Facade.edit(utenteModificato);
    }
    // FINE modifica tabella

}
