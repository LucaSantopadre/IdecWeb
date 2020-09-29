/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.view.ammin.jsf;

import idec.model.pub.pdc.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Luca
 */

public class StampaPianoDeiConti implements Serializable{
    
    
    public void stampaPdc(List<M1> m1Lista){    
        for (M1 m1 : m1Lista) {
            System.out.println("m1-" + m1.getM1Descr());
            
            List<M2> m2Lista = m1.getM2List();
            for (M2 m2 : m2Lista) {
                System.out.println("m2- -" + m2.getM2Descr());
                
                List<M3> m3Lista = m2.getM3List();
                for (M3 m3 : m3Lista) {
                    System.out.println("m3- - -" + m3.getCeeMas2CodDes());
                    
                    List<M4> m4Lista = m3.getM4List();
                    for (M4 m4 : m4Lista) {
                        System.out.println("m4- - - -" + m4.getCeeMas3CodDes());
                        
                        List<M5> m5Lista = m4.getM5List();
                        for (M5 m5 : m5Lista) {
                            System.out.println("m5- - - - -" + m5.getCeeMas4CodDes());
                        }
                    }
                }
            }
        }
    }
    

   
}
