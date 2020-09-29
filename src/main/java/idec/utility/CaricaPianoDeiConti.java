/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.utility;

import idec.controller.pub.pdc.session.M1Facade;
import idec.model.pub.pdc.M1;
import idec.model.pub.pdc.M2;
import idec.model.pub.pdc.M3;
import idec.model.pub.pdc.M4;
import idec.model.pub.pdc.M5;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Luca
 */
@SessionScoped
@Named
public class CaricaPianoDeiConti implements Serializable {

    @EJB
    private M1Facade m1Facade;

    // *** metodo crea albero pdc
    public List<Object> creaAlberoPdc() {
        List<Object> lista = new ArrayList<>();
        
        List<M1> m1Lista = m1Facade.findAll();
        for (M1 m1 : m1Lista) {
            //System.out.println("m1-" + m1.getCgTipoMasCodDes());
            lista.add(m1);

            List<M2> m2Lista = m1.getM2List();
            for (M2 m2 : m2Lista) {
                //System.out.println("m2- -" + m2.getCeeMasCodDes());
                lista.add(m2);

                List<M3> m3Lista = m2.getM3List();
                for (M3 m3 : m3Lista) {
                    //System.out.println("m3- - -" + m3.getCeeMas2CodDes());
                    lista.add(m3);

                    List<M4> m4Lista = m3.getM4List();
                    for (M4 m4 : m4Lista) {
                        //System.out.println("m4- - - -" + m4.getCeeMas3CodDes());
                        lista.add(m4);

                        List<M5> m5Lista = m4.getM5List();
                        for (M5 m5 : m5Lista) {
                            //System.out.println("m5- - - - -" + m5.getCeeMas4CodDes());
                            lista.add(m5);
                        }
                    }
                }
            }
        }
        
        return lista;
    }
    // FINE metodo crea albero pdc ---------------------------------------------

}
