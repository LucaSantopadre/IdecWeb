/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.fatturaelettronica.jsf;

import idec.controller.ditta.reg.jsf.Reg01Controller;
import idec.controller.ditta.reg.jsf.Reg03RigoController;
import idec.controller.ditta.vendor.jsf.Vendor01BaseController;
import idec.model.ditta.Reg03Rigo;
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
public class FatturaElettronicaReg01Controller implements Serializable {

    // *** variabili 
    @Inject
    Reg01Controller reg01Controller;
    @Inject
    Vendor01BaseController vendor01Controller;
    @Inject
    Reg03RigoController reg03RigoController;
    // FINE variabili ----------------------------------------------------------

    // *** get e set variabili
    //FINE get e set variabili -------------------------------------------------
    // *** metodo post construct inizializzo le variabili in quaso metodo
    @PostConstruct
    public void init() {

    }
    // FINE metodo post construct ----------------------------------------------

    public void aggiornaPannello() {
        List<Reg03Rigo> reg03RigoList = reg01Controller.getSelected().getReg03RigoList();
        // *** tabella righi
        reg03RigoController.setListaPerReg01(reg03RigoList);

        for (Reg03Rigo reg03Rigo : reg03RigoList) {
            if (reg03Rigo.getReg03RigoPK().getReg03RigoRegId() == 0 && reg03Rigo.getReg03RigoPK().getReg03SubrigoRegId() == 0) {
                // *** documento

                // -------------------------

                // *** vendor
                String conto = reg03Rigo.getConto();
                String vendor = reg03Rigo.getVendor();
                List<Vendor01Base> listaVendor = vendor01Controller.getItems();
                for (Vendor01Base ven : listaVendor) {
                    if (ven.getVendor01Conto().equals(conto) && ven.getVendor01Codice().equals(vendor)) {
                        vendor01Controller.setSelected(ven);
                    }
                }
            }
        }

    }
}
