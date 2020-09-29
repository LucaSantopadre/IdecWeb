/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ammin.pdc.jsf;

import idec.controller.pub.pdc.jsf.M2Controller;
import idec.model.pub.pdc.M2;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Luca
 */
@SessionScoped
@Named
public class PDCFormController implements Serializable {

    // *** variabili
    @Inject
    M2Controller m2Controller;
    private Integer nuovoId = null;

    // *** FINE variabili  -----------------------------------------------------
    // *** get e set variabili
    public Integer getNuovoId() {
        return nuovoId;
    }

    public void setNuovoId(Integer nuovoId) {

        this.nuovoId = nuovoId;
    }

    // FINE get e ste ----------------------------------------------------------
    // *** metodo costruttore albero
    @PostConstruct
    public void init() {

    }

    // FINE metodo costruttore albero ------------------------------------------
    public Integer setNuovoIdPanel() {
        List<M2> m2List = m2Controller.getItems();
        nuovoId = 1;
        for (M2 m2 : m2List) {
            if (nuovoId < m2.getM2Id()) {
                nuovoId = m2.getM2Id() + 1;
            }
        }
        return nuovoId;
    }

}
