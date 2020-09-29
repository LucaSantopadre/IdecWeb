/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.fatturaelettronica.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Luca classe controller della fattura elettronica
 */
@SessionScoped
@Named
public class FatturaElettronicaCessionarioController implements Serializable {

    // *** variabili 
    private List<String> listRadio;
    private String selectRadio;
    // FINE variabili ----------------------------------------------------------
    
    // *** get e set variabili
    public List<String> getListRadio() {
        return listRadio;
    }

    public void setListRadio(List<String> listRadio) {
        this.listRadio = listRadio;
    }

    public String getSelectRadio() {
        return selectRadio;
    }

    public void setSelectRadio(String selectRadio) {
        if(selectRadio.equals("Nuovo")){
            RequestContext.getCurrentInstance().reset(":tabView:formVendor01 :tabView:formVendor02");
        }
        this.selectRadio = selectRadio;
    }
    //FINE get e set variabili -------------------------------------------------
    
    // *** metodo post construct inizializzo le variabili in quaso metodo
    @PostConstruct
    public void init() {
        listRadio = new ArrayList<>();
        listRadio.add("Nuovo");
        listRadio.add("Modifica");
    }
    // FINE metodo post construct ----------------------------------------------
    
    public void registra(){
        
    }
}
