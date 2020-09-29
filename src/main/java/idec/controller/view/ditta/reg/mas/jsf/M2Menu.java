/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.reg.mas.jsf;

import idec.controller.pub.pdc.session.M2Facade;
import idec.model.pub.pdc.M2;
import idec.model.pub.pdc.M3;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Luca
 */
@Named("m2Menu")
@SessionScoped
public class M2Menu implements Serializable {

    @EJB
    private M2Facade m2Facade;
    @Inject
    private M3Menu m3Menu;

    private M2 m2_select = new M2();
    private List<M2> m2_lista = new ArrayList<>();

    public M2 getM2_select() {
        return m2_select;
    }

    public void setM2_select(M2 m2_select) {
        this.m2_select = m2_select;
    }

    public List<M2> getM2_lista() {
        return m2_lista;
    }

    public void setM2_lista(List<M2> m2_lista) {
        this.m2_lista = m2_lista;
    }

    public void m2_aggiorna() {
        List<M3> m3_lista = m2_select.getM3List();
        m3Menu.setM3_lista(m3_lista);
    }

    // *** metodo post construct
    // inizializzo le variabili in quaso metodo
    @PostConstruct
    public void init() {
        //m2_lista = m2Facade.findAll();
    }
    // FINE metodo post construct ----------------------------------------------
    // -------------------------------------------------------------------------
}
