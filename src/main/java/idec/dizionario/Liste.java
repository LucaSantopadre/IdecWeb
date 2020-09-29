/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.dizionario;

import idec.utility.CaricaPianoDeiConti;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Luca
 */
@SessionScoped
@Named
public class Liste implements Serializable {
    
    @Inject 
    CaricaPianoDeiConti caricaPDC;

    private List<Object> listaPDC = new ArrayList<>();

    public List<Object> getListaPDC() {
        if (this.listaPDC.isEmpty()) {
            
            this.listaPDC = caricaPDC.creaAlberoPdc();
        }
        return listaPDC;
    }

    public void setListaPDC(List<Object> listaPDC) {
        this.listaPDC = listaPDC;
    }

}
