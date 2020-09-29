/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.fatturaelettronica.jsf;

import idec.controller.ditta.reg.jsf.Reg01Controller;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Luca classe controller della fattura elettronica
 */
@SessionScoped
@Named
public class FatturaElettronicaController implements Serializable {

    // *** variabili 
    @Inject
    Reg01Controller reg01Controller;
    // FINE variabili ----------------------------------------------------------
    
    // *** get e set variabili

    //FINE get e set variabili -------------------------------------------------
    
    // *** metodo post construct inizializzo le variabili in quaso metodo
    @PostConstruct
    public void init() {

    }
    // FINE metodo post construct ----------------------------------------------
    
    
    
}
