/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.utility;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luca
 * classe utility utilizzata per la gestione delle stringhe
 * riceve degli input e rilascia l'output cercato
 */
public class StringUtility {
    
    // *** metodo da una String ---> a List<String>
    public static List<String> daString_aListaString(String stringaSingola){
        List<String> result = new ArrayList<>();
        String []arrayStringa = stringaSingola.split("\\|");
        for (String valoreSingolo : arrayStringa){
            if (!valoreSingolo.equals("") && !valoreSingolo.equals(" ")){
                result.add(valoreSingolo);
            }
        }
        return result;
    }
}
