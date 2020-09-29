/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.reg.mas.jsf;

import idec.controller.pub.pdc.session.M4Facade;
import idec.model.pub.pdc.M4;
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
public class M4Menu implements Serializable {

    @EJB
    private M4Facade m4Facade;
    @Inject
    private M2Menu m2Menu;

    private M4 m4_select = new M4();
    private List<M4> m4_lista = new ArrayList<>();

    public M4 getM4_select() {
        return m4_select;
    }

    public void setM4_select(M4 m4_select) {
        this.m4_select = m4_select;
    }

    public List<M4> getM4_lista() {
        return m4_lista;
    }

    public void setM4_lista(List<M4> m4_lista) {
        this.m4_lista = m4_lista;
    }

    public void m4_aggiorna() {
        
    }

    // *** metodo post construct
    // inizializzo le variabili in quaso metodo
    @PostConstruct
    public void init() {
    }
    // FINE metodo post construct ----------------------------------------------
    // -------------------------------------------------------------------------
}
