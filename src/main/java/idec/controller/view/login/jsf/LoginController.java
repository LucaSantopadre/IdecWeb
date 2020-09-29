/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.login.jsf;

import idec.controller.SessionUtils;
import idec.controller.pub.ute00.session.Ute00Facade;
import idec.model.pub.Ute00;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 * @author Luca 10/11/2017 classe che gestisce il LoginController dell'utente
 */
@SessionScoped
@Named
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    // *** variabili schermata
    private String pwd;
    private String msg;
    private String user;
    private boolean pwdDefault = false;
    // FINE variabili schermata

    // *** variabili interne (db)
    @EJB
    private Ute00Facade ute00Facade;
    List<Ute00> listaUtenti;
    Integer tipoUtente;
    // FINE variabili interne (db)----------------------------------------------

    // *** get e set variabili
    public String getPwd() {
        return pwd;
    }

    public boolean isPwdDefault() {
        return pwdDefault;
    }

    public void setPwdDefault(boolean pwdDefault) {
        this.pwdDefault = pwdDefault;
    }

    public List<Ute00> getListaUtenti() {
        return listaUtenti;
    }

    public void setListaUtenti(List<Ute00> listaUtenti) {
        this.listaUtenti = listaUtenti;
    }

    public Integer getTipoUtente() {
        return tipoUtente;
    }

    public void setTipoUtente(Integer tipoUtente) {
        this.tipoUtente = tipoUtente;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    // FINE get e set variabili ------------------------------------------------

    // *** valida login
    public String validateUsernamePassword() {
        boolean valid = controllaUtentePassword();
        if (valid) {
            memorizzaUtenteEntrato();

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlg2').show();");
            return "menu";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Attenzione! Email o password errati.",
                            "Attenzione! Email o password errati.2"));
            return "index";
        }
    }
    // FINE valida login

    // *** Memorizza Utente entrato
    public void memorizzaUtenteEntrato() {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("username", user);
        session.setAttribute("pwd", pwd);
        if (tipoUtente == 0) {
            session.setAttribute("tipoUtente", "amministratore");
        } else {
            session.setAttribute("tipoUtente", "utente");
        }
    }
    // FINE memorizza dati utente

    // *** controllo utente e password con il database
    private boolean controllaUtentePassword() {
        listaUtenti = ute00Facade.findAll();
        boolean result = false;
        for (Ute00 ute00 : listaUtenti) {
            result = user.equals(ute00.getEmail()) && pwd.equals(ute00.getPassword());
            if (result) {
                tipoUtente = ute00.getId();
                if (tipoUtente == 0) {

                }
                break;
            }
        }
        return result;
    }
    // FINE controllo utente e password

    // *** logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index";
    }
    // *** FINE logout event
}
