/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.menu.jsf;

import idec.controller.SessionUtils;
import idec.controller.pub.dit00.session.Dit00Facade;
import idec.model.pub.Dit00;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Luca classe che crea l'albero delle ditte
 */
@SessionScoped
@Named
public class AlberoDitteMenuController implements Serializable {

    // *** variabili
    private TreeNode root;
    private TreeNode selectNode;
    private String selectNodeToString = "";
    private TreeNode selectNodePadre;
    private String selectNodeToStringPadre;
    @EJB
    private Dit00Facade dit00Facade;
    // FINE variabili ----------------------------------------------------------

    // *** get e set variabili
    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;

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

    public void setSelectNodeToString(String selectNodeToString) {
        this.selectNodeToString = selectNodeToString;
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
    // FINE get e set variabili ------------------------------------------------

    // *** metodo che costruisce l'albero
    @PostConstruct
    public void init() {
        this.root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode("Elenco ditte", this.root);
        node0.setExpanded(true);
        creaAlberoDitte(node0);
    }
    // FINE metodo che costruisce l'albero

    // *** metodo aggiungi ditte albero
    private void creaAlberoDitte(TreeNode node0) {
        // *** per mysql eliminare*** 
//            List<HashMap> listaCodici = getListaDitte();
//            ArrayList<String> listaDitte = getObservableListaDitteMysql(listaCodici);
//            for (String ditta : listaDitte) {
//                node0.getChildren().add(new DefaultTreeNode(ditta));
//            }

        // *** per postgres JPA ***
        for (Dit00 dit00 : dit00Facade.findAll()) {
            node0.getChildren().add(new DefaultTreeNode(dit00.getCodiceDitta() + "_" + dit00.getDenominazione() + dit00.getCognome() + " " + dit00.getNome()));
        }
    }
    // FINE metodo aggiungi ditte

    // *** metodo action per entrare nella ditta
    public String entraNellaDitta() {
        if (selectNodeToString.equals("")) {
            return "";
        }
        return "/idec/ditta/MenuDitta.xhtml";
    }
    // FINE metodo entra nella ditta

    public void onNodeSelect(NodeSelectEvent event) {
        System.out.println("Node Data ::" + event.getTreeNode().getData() + " :: Selected");
        setSelectNodeToString(event.getTreeNode().getData().toString());
        setSelectNodeToStringPadre(event.getTreeNode().getParent().getData().toString());
        System.out.println(event.getTreeNode().getParent().getData().toString());

        // setto la ditta entrataa
        HttpSession session = SessionUtils.getSession();
        String substring = selectNodeToString.substring(0, 5);
        session.setAttribute("ditta", substring);
        session.setAttribute("descr_ditta", selectNodeToString);
    }
}
