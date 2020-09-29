/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.reg.mas.jsf;

import idec.controller.pub.pdc.session.M3Facade;
import idec.model.pub.pdc.M3;
import idec.model.pub.pdc.M2;
import idec.model.pub.pdc.M4;
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
public class M3Menu implements Serializable {

    @EJB
    private M3Facade m3Facade;
    @Inject
    private M4Menu m4Menu;

    private M3 m3_select = new M3();
    private List<M3> m3_lista = new ArrayList<>();

    public M3 getM3_select() {
        System.out.println(m3_select);
        return m3_select;
    }

    public void setM3_select(M3 m3_select) {
        this.m3_select = m3_select;
    }

    public List<M3> getM3_lista() {
        
        return m3_lista;
        
    }

    public void setM3_lista(List<M3> m3_lista) {
        this.m3_lista = m3_lista;
    }

    public void m3_aggiorna() {
        List<M4> m4_lista = m3_select.getM4List();
        m4Menu.setM4_lista(m4_lista);
    }

    // *** metodo post construct
    // inizializzo le variabili in quaso metodo
    @PostConstruct
    public void init() {
        m3_lista = m3Facade.findAll();
    }
    // FINE metodo post construct ----------------------------------------------
    // -------------------------------------------------------------------------
}
