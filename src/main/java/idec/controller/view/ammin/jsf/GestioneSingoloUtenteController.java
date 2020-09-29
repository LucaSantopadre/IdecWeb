/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ammin.jsf;

import idec.controller.pub.dit00.session.Dit00Facade;
import idec.controller.pub.ute00.session.Ute00Facade;
import idec.model.pub.Dit00;
import idec.model.pub.Ute00;
import idec.utility.StringUtility;
import static idec.utility.StringUtility.daString_aListaString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Luca classe che gestisce il pannello del menu amministratore del
 * singolo utente
 */
@SessionScoped
@Named
public class GestioneSingoloUtenteController implements Serializable {

    // *** variabili
    private List<Dit00> listaDitte;
    @EJB
    private Dit00Facade dit00Facade;
    private DualListModel<String> dualListaDitte;
    @EJB
    private Ute00Facade ute00Facade;
    // FINE variabili ----------------------------------------------------------

    // *** get e set variabili
    public List<Dit00> getListaDitte() {
        return listaDitte;
    }

    public void setListaDitte(List<Dit00> listaDitte) {
        this.listaDitte = listaDitte;
    }

    public Dit00Facade getDit00Facade() {
        return dit00Facade;
    }

    public void setDit00Facade(Dit00Facade dit00Facade) {
        this.dit00Facade = dit00Facade;
    }

    public DualListModel<String> getDualListaDitte() {
        return dualListaDitte;
    }

    public void setDualListaDitte(DualListModel<String> dualListaDitte) {
        this.dualListaDitte = dualListaDitte;
    }
    // FINE get e set variabili ------------------------------------------------

    // *** metodo richiamato dal pannello appena aperto
    @PostConstruct
    public void init() {
        List<String> ditteSource = new ArrayList<>();
        List<String> ditteTarget = new ArrayList<>();
        listaDitte = dit00Facade.findAll();
        for (Dit00 dit00 : listaDitte) {
            ditteSource.add(dit00.getCodiceDitta());
        }
        // setto le ditte GIA visibili all'utente
        String ditteVisibili = ute00Facade.find(0).getDitteVisibili();
        ditteTarget.addAll(daString_aListaString(ditteVisibili));

        dualListaDitte = new DualListModel<>(ditteSource, ditteTarget);
        dualListaDitte = sistemaDitteVisibili(dualListaDitte);
    }
    // FINE metodo init

    // *** sistema ditte Visibili e già viste
    public DualListModel<String> sistemaDitteVisibili(DualListModel<String> dualList) {
        List<String> sourceDef = new ArrayList<>();
        sourceDef.addAll(dualList.getSource());
        for (String source : dualList.getSource()) {
            for (String target : dualList.getTarget()) {
                if (source.equals(target)) {
                    sourceDef.remove(source);
                }
            }
        }
        dualList.setSource(sourceDef);
        return dualList;
    }
    // FINE metodo sistema

    // *** metodo action del pannello
    public String dualListaAction() {
        List<String> target = dualListaDitte.getTarget();
        Ute00 utente = ute00Facade.find(0);
        String ditteVisibili = "";
        for (String ditta_cod : target) {
            ditteVisibili = ditteVisibili.concat(ditta_cod).concat("|");
        }
        utente.setDitteVisibili(ditteVisibili);
        ute00Facade.edit(utente);
        return "";
    }
    // FINE metodo action del pannello
}
