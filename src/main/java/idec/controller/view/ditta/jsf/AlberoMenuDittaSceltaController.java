/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ditta.jsf;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Luca
 */
@SessionScoped
@Named
public class AlberoMenuDittaSceltaController implements Serializable{
    
    // *** variabili
    private TreeNode root;
    private TreeNode selectNode;
    private String selectNodeToString = "";
    private TreeNode selectNodePadre;
    private String selectNodeToStringPadre = "";
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
        TreeNode node0 = new DefaultTreeNode("Menu ditta", this.root);
        node0.setExpanded(true);
        creaAlberoDitta(node0);
    }
    // FINE metodo che costruisce l'albero

    // *** metodo aggiungi ditte albero
    private void creaAlberoDitta(TreeNode node0) {
        TreeNode node00 = new DefaultTreeNode("Registrazioni", node0);
        TreeNode node000 = new DefaultTreeNode("Nuovo", node00);
        TreeNode node001 = new DefaultTreeNode("Nuova Fattura Elettronica", node00);
        TreeNode node01 = new DefaultTreeNode("Spesometro", node0);
        
    }
    // FINE metodo aggiungi ditte

    // *** metodo action per entrare nella ditta
    public String entraNellaOperazioneScelta() {
        if(selectNodeToString.equals("Nuovo")){
            return "/idec/ditta/registrazioni/nuovo/DittaNuovaRegistrazione.xhtml";
        }
        if(selectNodeToString.equals("Nuova Fattura Elettronica")){
            return "/idec/ditta/registrazioni/fatturaElettr/FatturaElettronica.xhtml";
        }
        if(selectNodeToString.equals("Spesometro")){
            return "/idec/ditta/spesometro/Spesometro.xhtml";
        }
        return "";
    }
    // FINE metodo entra nella ditta

    public void onNodeSelect(NodeSelectEvent event) {
        System.out.println("Node Data ::" + event.getTreeNode().getData() + " :: Selected");
        setSelectNodeToString(event.getTreeNode().getData().toString());
        setSelectNodeToStringPadre(event.getTreeNode().getParent().getData().toString());
        System.out.println(event.getTreeNode().getParent().getData().toString());
    }
}
