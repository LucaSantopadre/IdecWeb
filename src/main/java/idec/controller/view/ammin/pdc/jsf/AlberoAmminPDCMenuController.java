/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ammin.pdc.jsf;

import idec.controller.pub.pdc.jsf.M1Controller;
import idec.controller.pub.pdc.session.M1Facade;
import idec.dizionario.Liste;
import idec.model.pub.pdc.M1;
import idec.model.pub.pdc.M2;
import idec.model.pub.pdc.M3;
import idec.model.pub.pdc.M4;
import idec.model.pub.pdc.M5;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
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
public class AlberoAmminPDCMenuController implements Serializable {

    // *** variabili
    @EJB
    private M1Facade m1Facade;
    private TreeNode root;
    private TreeNode selectNode;
    private String selectNodeToString;
    private TreeNode selectNodePadre;
    private String selectNodeToStringPadre;

    @Inject
    M1Controller m1Controller;
    @Inject
    PDCFormController formController;
    @Inject
    Liste liste;
    
    private List<HashMap> listaPDC = new ArrayList<>();
    // *** FINE variabili  -----------------------------------------------------

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
    // FINE get e ste ----------------------------------------------------------

    // *** metodo costruttore albero
    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode("Piano dei conti", root);
        node0.setExpanded(true);
        
        liste.getListaPDC();

        //creaAlberoPdc(root);
    }
    // FINE metodo costruttore albero ------------------------------------------

    // *** metodo crea albero pdc
    private void creaAlberoPdc(TreeNode node0) {
        List<M1> m1Lista = m1Facade.findAll();
        for (M1 m1 : m1Lista) {
            //System.out.println("m1-" + m1.getCgTipoMasCodDes());
            DefaultTreeNode m1Node = new DefaultTreeNode(m1.getM1Id() + "- " + m1.getM1Descr());
            node0.getChildren().add(m1Node);

            List<M2> m2Lista = m1.getM2List();
            for (M2 m2 : m2Lista) {
                //System.out.println("m2- -" + m2.getCeeMasCodDes());
                DefaultTreeNode m2Node = new DefaultTreeNode(m2.getM2Id() + "- " + m2.getM2Descr());
                m1Node.getChildren().add(m2Node);

                List<M3> m3Lista = m2.getM3List();
                for (M3 m3 : m3Lista) {
                    //System.out.println("m3- - -" + m3.getCeeMas2CodDes());
                    DefaultTreeNode m3Node = new DefaultTreeNode(m3.getM3Id() + "- " + m3.getCeeMas2CodDes());
                    m2Node.getChildren().add(m3Node);

                    List<M4> m4Lista = m3.getM4List();
                    for (M4 m4 : m4Lista) {
                        //System.out.println("m4- - - -" + m4.getCeeMas3CodDes());
                        DefaultTreeNode m4Node = new DefaultTreeNode(m4.getM4Id() + "- " + m4.getCeeMas3CodDes());
                        m3Node.getChildren().add(m4Node);

                        List<M5> m5Lista = m4.getM5List();
                        for (M5 m5 : m5Lista) {
                            //System.out.println("m5- - - - -" + m5.getCeeMas4CodDes());
                            DefaultTreeNode m5Node = new DefaultTreeNode(m5.getM5Id() + "- " + m5.getCeeMas4CodDes());
                            m4Node.getChildren().add(m5Node);
                        }
                    }
                }
            }
        }
    }
    // FINE metodo crea albero pdc ---------------------------------------------

    public void onNodeSelect(NodeSelectEvent event) {
        setSelectNodeToString(event.getTreeNode().getData().toString());
        setSelectNodeToStringPadre(event.getTreeNode().getParent().getData().toString());
        System.out.println("Node Data ::" + event.getTreeNode().getData() + " :: Selected");
        System.out.println("Padre :: " + event.getTreeNode().getParent().getData().toString());

        String toString = event.getTreeNode().getData().toString();
        String substring = toString.substring(0, toString.indexOf("-"));
        Integer idScelto = Integer.valueOf(substring);

        if (idScelto < 1000) {
            m1Controller.setSelected(m1Facade.find(idScelto));
            formController.setNuovoIdPanel();
        }
    }
}
