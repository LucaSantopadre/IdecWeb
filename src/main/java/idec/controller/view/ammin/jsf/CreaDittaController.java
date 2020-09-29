/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ammin.jsf;

import idec.controller.pub.dit00.session.Dit00Facade;
import idec.model.pub.Dit00;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Luca classe controller di una nuova ditta da creare
 */
@SessionScoped
@Named
public class CreaDittaController implements Serializable {

    // *** variabili
    private List<Dit00> listaProva = new ArrayList<>();
    private Dit00 dittaNuova = new Dit00();
    @EJB
    private Dit00Facade dit00Facade;
    // FINE variabili

    // *** get e set variabili
    public List<Dit00> getListaProva() {
        if(listaProva.size()==60){
            return listaProva;
        }
        for (int i = 0; i < 60; i++) {
            Dit00 dit00 = new Dit00();
            listaProva.add(dit00);
        }
        return listaProva;
    }

    public void setListaProva(List<Dit00> listaProva) {

        this.listaProva = listaProva;
    }

    public Dit00 getDittaNuova() {
        return dittaNuova;
    }

    public void setDittaNuova(Dit00 dittaNuova) {
        this.dittaNuova = dittaNuova;
    }
    // FINE get e set variabili

    // *** crea la ditta action
    public String creaDittaNew() throws SQLException {
        System.out.println(dittaNuova.getCodiceDitta());
        dit00Facade.create(dittaNuova);
        //creaSchema(dittaNuova.getCodiceDitta());
        return "";
    }
    // FINE crea la ditta
}
