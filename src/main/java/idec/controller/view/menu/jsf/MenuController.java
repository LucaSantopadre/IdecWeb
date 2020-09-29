/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.menu.jsf;

import idec.controller.SessionUtils;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luca classe del pannello menu principale
 */
@SessionScoped
@Named
public class MenuController implements Serializable {

    // *** variabili schermata
    private String pwd;
    private boolean mostraPwdTemporanea = false;
    // FINE variabili schermata ------------------------------------------------

    // *** get e ste variabili
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isMostraPwdTemporanea() {
        HttpSession session = SessionUtils.getSession();
        if(session.getAttribute("pwd").equals("123")){
            mostraPwdTemporanea=true;
        }
        return mostraPwdTemporanea;
    }

    public void setMostraPwdTemporanea(boolean mostraPwdTemporanea) {
        this.mostraPwdTemporanea = mostraPwdTemporanea;
    }
    // FINE get e set variabili ------------------------------------------------

    // *** valida login amministratore
    public String validatePasswordAmministratore() {
        HttpSession session = SessionUtils.getSession();

        if (pwd.equals(session.getAttribute("pwd"))) {
            return "amminMenu";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Attenzione! password errata.",
                            "Attenzione! password errata.2"));
            return "";
        }
    }
    // FINE valida login amministratore
}
