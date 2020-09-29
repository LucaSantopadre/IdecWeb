/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.reg.jsf;

import idec.controller.pub.pdc.session.*;
import idec.model.pub.pdc.M1;
import idec.model.pub.pdc.M2;
import idec.model.pub.pdc.M3;
import idec.model.pub.pdc.M4;
import idec.model.pub.pdc.M5;
import idec.model.pub.pdc.Mc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

/**
 *
 * @author Luca
 */
@Named
@SessionScoped
public class DittaReg03Rigo_MAS implements Serializable {

    // *** variabili
    @EJB
    private M1Facade m1Facade;
    @EJB
    private M2Facade m2Facade;
    @EJB
    private M3Facade m3Facade;
    @EJB
    private M4Facade m4Facade;
    @EJB
    private M5Facade m5Facade;
    @EJB
    private McFacade mcFacade;

    private List<M1> listaM1 = new ArrayList<>();
    private M1 m1Scelto = new M1();

    private List<M2> listaM2 = new ArrayList<>();
    private M2 m2Scelto = new M2();

    private List<M3> listaM3 = new ArrayList<>();
    M3 m3Select = new M3();

    private List<M4> listaM4 = new ArrayList<>();
    private M4 m4Scelto = new M4();

    private List<M5> listaM5 = new ArrayList<>();
    private M5 m5Scelto = new M5();

    private List<Mc> listaMc = new ArrayList<>();
    private Mc mcScelto = new Mc();
    // FINE variabili ----------------------------------------------------------
    
    // *** get e set
    public List<M1> getListaM1() {
        return listaM1;
    }

    public void setListaM1(List<M1> listaM1) {
        this.listaM1 = listaM1;
    }

    public M1 getM1Scelto() {
        return m1Scelto;
    }

    public void setM1Scelto(M1 m1Scelto) {
        this.m1Scelto = m1Scelto;
    }

    public List<M2> getListaM2() {
        return listaM2;
    }

    public void setListaM2(List<M2> listaM2) {
        this.listaM2 = listaM2;
    }

    public M2 getM2Scelto() {
        return m2Scelto;
    }

    public void setM2Scelto(M2 m2Scelto) {
        this.m2Scelto = m2Scelto;
    }

    public List<M3> getListaM3() {
        return listaM3;
    }

    public void setListaM3(List<M3> listaM3) {
        this.listaM3 = listaM3;
    }

    public M3 getM3Select() {
        return m3Select;
    }

    public void setM3Select(M3 m3Select) {
        this.m3Select = m3Select;
    }

    public List<M4> getListaM4() {
        return listaM4;
    }

    public void setListaM4(List<M4> listaM4) {
        this.listaM4 = listaM4;
    }

    public M4 getM4Scelto() {
        return m4Scelto;
    }

    public void setM4Scelto(M4 m4Scelto) {
        this.m4Scelto = m4Scelto;
    }

    public List<M5> getListaM5() {
        return listaM5;
    }

    public void setListaM5(List<M5> listaM5) {
        this.listaM5 = listaM5;
    }

    public M5 getM5Scelto() {
        return m5Scelto;
    }

    public void setM5Scelto(M5 m5Scelto) {
        this.m5Scelto = m5Scelto;
    }

    public List<Mc> getListaMc() {
        return listaMc;
    }

    public void setListaMc(List<Mc> listaMc) {
        this.listaMc = listaMc;
    }

    public Mc getMcScelto() {
        return mcScelto;
    }

    public void setMcScelto(Mc mcScelto) {
        this.mcScelto = mcScelto;
    }
    // FINE get e set ----------------------------------------------------------
    
    // *** metodo post construct
    // inizializzo le variabili in quaso metodo
    @PostConstruct
    public void init() {
        listaM1 = m1Facade.findAll();
        listaM2 = m2Facade.findAll();
        listaM3 = m3Facade.findAll();
        listaM4 = m4Facade.findAll();
        listaM5 = m5Facade.findAll();
        listaMc = mcFacade.findAll();
    }
    // FINE metodo post construct ----------------------------------------------
    // -------------------------------------------------------------------------

    public void aggiornaM1() {
        listaM2.clear();
        listaM3.clear();
        listaM4.clear();
        listaM5.clear();

        listaM2 = m1Scelto.getM2List();
        for (M2 m2 : listaM2) {
            listaM3.addAll(m2.getM3List());
        }

        for (M3 m3 : listaM3) {
            listaM4.addAll(m3.getM4List());
        }

        for (M4 m4 : listaM4) {
            listaM5.addAll(m4.getM5List());
        }
    }

    public void aggiornaM2() {
        listaM3.clear();
        listaM3 = m2Scelto.getM3List();
    }

    public void aggiornaM3() {
        m2Scelto = m3Select.getM2id();
        listaM4.clear();
        if (m3Select.getM2id().getM1Id().getM1Id() == 10
                || m3Select.getM2id().getM1Id().getM1Id() == 40) {
            listaM4 = m3Select.getM4List();
        }

    }

    public void aggiornaM4() {
        m3Select = m4Scelto.getM3id();
//        m2Scelto = m3Select.getM2id();
//        listaM5.clear();
//        listaM5 = m4Scelto.getM5List();
    }

    public void aggiornaM5() {
//        m4Scelto = m5Scelto.getM4id();
//        m3Select = m4Scelto.getM3id();
//        m2Scelto = m3Select.getM2id();
    }

    public void aggiornaMc() {
        if(mcScelto.getM2id().getM2Id() >= 2000){
            m2Scelto=mcScelto.getM2id();
        }
    }
}
