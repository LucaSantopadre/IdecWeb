/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ammin.jsf;

import idec.controller.pub.pdc.session.M1Facade;
import idec.controller.pub.pdc.session.M2Facade;
import idec.controller.pub.ute00.session.Ute00Facade;
import idec.model.pub.Ute00;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Luca classe controller dell'albero amministratore crea le voci
 * dell'albero
 */
@SessionScoped
@Named
public class AlberoAmminMenuController implements Serializable {

    // *** variabili
    private TreeNode root;
    private TreeNode selectNode;
    private String selectNodeToString;
    private TreeNode selectNodePadre;
    private String selectNodeToStringPadre;
    @EJB
    private Ute00Facade ute00Facade;
    // FINE variabili ----------------------------------------------------------

    // *** get e set variabili
    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getSelectNode() {
        return selectNode;
    }

    public void setSelectNode(TreeNode selectNode) {
        this.selectNode = selectNode;
    }

    public String getSelectNodeToString() {
        return selectNodeToString;
    }

    public TreeNode getSelectNodePadre() {
        return selectNodePadre;
    }

    public void setSelectNodePadre(TreeNode selectNodePadre) {
        this.selectNodePadre = selectNodePadre;
    }

    public String getSelectNodeToStringPadre() {
        return selectNodeToStringPadre;
    }

    public void setSelectNodeToStringPadre(String selectNodeToStringPadre) {
        this.selectNodeToStringPadre = selectNodeToStringPadre;
    }

    public void setSelectNodeToString(String selectNodeToString) {
        this.selectNodeToString = selectNodeToString;
    }

    // FINE get e set variabili ------------------------------------------------
    // *** metodo costruttore albero
    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode("Amministratore", root);
        node0.setExpanded(true);

        TreeNode node00 = new DefaultTreeNode("Gestione utenti", node0);
        creaAlberoUtenti(node00);

        TreeNode node01 = new DefaultTreeNode("Gestione ditte", node0);
        TreeNode node010 = new DefaultTreeNode("Crea ditta", node01);

        TreeNode node02 = new DefaultTreeNode("Piano dei conti", node0);
        System.out.println(node00);
        System.out.println(node01);
    }
    // FINE metodo costruttore albero

    // *** metodo crea albero con utenti
    private void creaAlberoUtenti(TreeNode node00) {
        for (Ute00 ute00 : ute00Facade.findAll()) {
            node00.getChildren().add(new DefaultTreeNode(ute00.getEmail()));
        }
    }
    // FINE metodo crea albero con utenti

    public void onNodeSelect(NodeSelectEvent event) {
        System.out.println("Node Data ::" + event.getTreeNode().getData() + " :: Selected");
        setSelectNodeToString(event.getTreeNode().getData().toString());
        setSelectNodeToStringPadre(event.getTreeNode().getParent().getData().toString());
        System.out.println(event.getTreeNode().getParent().getData().toString());

//        if (selectNodeToString.equals("Stampa pdc")) {
//            List<M1> m1Lista = m1Facade.findAll();
//
//            StampaPianoDeiConti stampa = new StampaPianoDeiConti();
//            stampa.stampaPdc(m1Lista);
//        }
    }

    public boolean mostraPannelloGestioneSingolaDitta(NodeSelectEvent event) {
        String toString = event.getTreeNode().getParent().toString();
        return toString.equals("Gestione utenti");

    }

    // *** metodo action per entrare nell'operazione
    public String btnEntraOperazione() {
        if (selectNodeToString.equals("")) {
            return "";
        }

        if (selectNodeToString.equals("Piano dei conti")) {
            return "/idec/ammin/pdc/AmminPdc.xhtml";
        }

        return "";
    }
    // FINE metodo entra nell'operazione

}
