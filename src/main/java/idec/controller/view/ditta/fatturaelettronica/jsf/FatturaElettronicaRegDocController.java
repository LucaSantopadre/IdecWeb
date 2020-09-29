/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.fatturaelettronica.jsf;

import idec.controller.ditta.reg.jsf.Reg01Controller;
import idec.controller.ditta.reg.jsf.RegDocController;
import idec.controller.ditta.vendor.jsf.Vendor01BaseController;
import idec.model.ditta.Reg03Rigo;
import idec.model.ditta.RegDoc;
import idec.model.ditta.Vendor01Base;
import java.io.Serializable;
import java.util.List;
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
public class FatturaElettronicaRegDocController implements Serializable {

    // *** variabili 
    private boolean renderPannelloDoc;
    // FINE variabili ----------------------------------------------------------

    // *** get e set variabili

    public boolean isRenderPannelloDoc() {
        return renderPannelloDoc;
    }

    public void setRenderPannelloDoc(boolean renderPannelloDoc) {
        this.renderPannelloDoc = renderPannelloDoc;
    }
    
    
    //FINE get e set variabili -------------------------------------------------
    // *** metodo post construct inizializzo le variabili in quaso metodo
    @PostConstruct
    public void init() {
        setRenderPannelloDoc(true);
    }
    // FINE metodo post construct ----------------------------------------------
}
