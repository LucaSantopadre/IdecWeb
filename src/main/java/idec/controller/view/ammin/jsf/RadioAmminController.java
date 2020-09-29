/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ammin.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Luca classe controller dei radio button nella pagina amministratore
 */
@SessionScoped
@Named
public class RadioAmminController implements Serializable {

    // *** variabili
    private String selezionata;
    private List<String> scelte;
    public static String SCELTA_RADIO;
    private String crea = "crea";
    private String modifica = "modifica";
    private String elimina = "elimina";
    // FINE variabili ----------------------------------------------------------
    
    // *** get e set variabili
        public String getSelezionata() {
        return selezionata;
    }

    public void setSelezionata(String selezionata) {
        SCELTA_RADIO = selezionata;
        this.selezionata = selezionata;
    }

    public List<String> getScelte() {
        return scelte;
    }

    public void setScelte(List<String> scelte) {
        this.scelte = scelte;
    }

    public String getCrea() {
        return crea;
    }

    public void setCrea(String crea) {
        this.crea = crea;
    }

    public String getModifica() {
        return modifica;
    }

    public void setModifica(String modifica) {
        this.modifica = modifica;
    }

    public String getElimina() {
        return elimina;
    }

    public void setElimina(String elimina) {
        this.elimina = elimina;
    }
    // FINE get e set variabili ------------------------------------------------

    // *** metodo di inizializzazione dei radio button
    @PostConstruct
    public void init() {
        scelte = new ArrayList<>();
        scelte.add("Crea");
        scelte.add("Modifica");
        scelte.add("Elimina");
        scelte.add("Stampa");
        selezionata = "Crea";
    }
    // FINE metodo di inizializzazione dei radio button


}
