/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.reg.mas.jsf;

import idec.controller.pub.pdc.session.M1Facade;
import idec.model.pub.pdc.M1;
import idec.model.pub.pdc.M2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Luca
 */
@Named
@SessionScoped
public class M1Menu implements Serializable {

    @EJB
    private M1Facade m1Facade;
    @Inject
    private M2Menu m2Menu;

    private M1 m1_select = new M1();
    private List<M1> m1_lista = new ArrayList<>();

    public M1 getM1_select() {
        System.out.println(m1_select);
        return m1_select;
        
    }

    public void setM1_select(M1 m1_select) {
        this.m1_select = m1_select;
    }

    public List<M1> getM1_lista() {
        return m1_lista;
    }

    public void setM1_lista(List<M1> m1_lista) {
        this.m1_lista = m1_lista;
    }

    public void m1_aggiorna() {
        List<M2> m2_lista = m1_select.getM2List();
        m2Menu.setM2_lista(m2_lista);
    }

    // *** metodo post construct
    // inizializzo le variabili in quaso metodo
    @PostConstruct
    public void init() {
        m1_lista = m1Facade.findAll();
    }
    // FINE metodo post construct ----------------------------------------------
    // -------------------------------------------------------------------------
}
