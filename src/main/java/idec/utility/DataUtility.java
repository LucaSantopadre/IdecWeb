/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Luca
 */
public class DataUtility {

    // *** trasforma da data a stringa  
    public static String trasformaDataInString_1(Date data) {
        String output;
        SimpleDateFormat formatter;

        formatter = new SimpleDateFormat("dd/MM/yy");
        output = formatter.format(data);
        return output;
    }
    // FINE trasforma da data a string
    

        // *** trasforma da data a stringa  
    public static String trasformaDataInString_2(Date data) {
        String output;
        SimpleDateFormat formatter;

        formatter = new SimpleDateFormat("dd-MM-yyyy");
        output = formatter.format(data);
        return output;
    }
    // FINE trasforma da data a string

}
