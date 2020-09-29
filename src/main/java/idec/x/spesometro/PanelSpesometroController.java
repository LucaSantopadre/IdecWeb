/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file_streamed_emesse, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro;

import static idec.controller.SessionUtils.getSession;
import static idec.x.spesometro.AvviaSpesometro.data_1;
import static idec.x.spesometro.AvviaSpesometro.data_2;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import static idec.utility.DataUtility.trasformaDataInString_2;
import static idec.x.spesometro.AvviaSpesometro.FILE_PDF;
import static idec.x.spesometro.AvviaSpesometro.PATH_FOLDER_SPESOMETRO;
import static idec.x.spesometro.utils.Data.DATA_ORA_1;
import static idec.x.spesometro.utils.Data.setDataOra1;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Luca
 */
@SessionScoped
@Named
public class PanelSpesometroController implements Serializable {

    // *** variabili
    private Date dalla_data;
    private Date alla_data;
    private StreamedContent file_streamed_emesse;
    private StreamedContent file_streamed_ricevute;
    //--------------------------------------------------

    // *** get e set
    public Date getDalla_data() {
        return dalla_data;
    }

    public void setDalla_data(Date dalla_data) {
        this.dalla_data = dalla_data;
    }

    public Date getAlla_data() {
        return alla_data;
    }

    public void setAlla_data(Date alla_data) {
        this.alla_data = alla_data;
    }

    public StreamedContent getFile_streamed_emesse() throws Exception {
        avvia_fatture_emesse();
        return file_streamed_emesse;
    }

    public void setFile_streamed_emesse(DefaultStreamedContent file) {
        this.file_streamed_emesse = file;
    }

    public StreamedContent getFile_streamed_ricevute() throws Exception {
        avvia_fatture_ricevute();
        return file_streamed_ricevute;
    }

    public void setFile_streamed_ricevute(StreamedContent file_streamed_ricevute) {
        this.file_streamed_ricevute = file_streamed_ricevute;
    }
    // --------------------------------------------------------------------------------------------------------------

    public String avvia_fatture_emesse() throws Exception {
        // setto data
        setDataOra1();
        // crea file
        HttpSession session = getSession();
        String db = (String) session.getAttribute("ditta");
        File file;
        db = db.substring(0, 5);
        //inizializzo la cartella per il file_streamed_emesse 
        Path path = Paths.get(PATH_FOLDER_SPESOMETRO + db);
        if (Files.notExists(path)) {
            new File(PATH_FOLDER_SPESOMETRO + db).mkdirs();
        }
        file = new File(PATH_FOLDER_SPESOMETRO + db);
        FILE_PDF = file.getAbsolutePath().concat("\\CLIENTI" + db + "_" + DATA_ORA_1 + ".pdf");
        // fine crea file

        // ---------------------
        String dalla_data_string = trasformaDataInString_2(dalla_data);
        String alla_data_string = trasformaDataInString_2(alla_data);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        data_1 = LocalDate.parse(dalla_data_string, formatter);
        data_2 = LocalDate.parse(alla_data_string, formatter);

        AvviaSpesometro avvia = new AvviaSpesometro();
        avvia.actionProceduraDTE(data_1, data_2, db);

        FILE_PDF = FILE_PDF.replace("\\", "/");
        File fileppp = new File(FILE_PDF);
        InputStream input = new FileInputStream(fileppp);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        file_streamed_emesse = new DefaultStreamedContent(input, externalContext.getMimeType(fileppp.getName()), fileppp.getName());

        return "";
    }
    // ------------------------------------------------------------------------------------------------------------------

    public String avvia_fatture_ricevute() throws Exception {
        // setto data
        setDataOra1();
        // crea file
        HttpSession session = getSession();
        String db = (String) session.getAttribute("ditta");
        File file;
        db = db.substring(0, 5);
        //inizializzo la cartella per il file_streamed_emesse 
        Path path = Paths.get(PATH_FOLDER_SPESOMETRO + db);
        if (Files.notExists(path)) {
            new File(PATH_FOLDER_SPESOMETRO + db).mkdirs();
        }
        file = new File(PATH_FOLDER_SPESOMETRO + db);
        FILE_PDF = file.getAbsolutePath().concat("\\FORNITORI" + db + "_" + DATA_ORA_1 + ".pdf");
        // fine crea file

        // ---------------------
        String dalla_data_string = trasformaDataInString_2(dalla_data);
        String alla_data_string = trasformaDataInString_2(alla_data);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        data_1 = LocalDate.parse(dalla_data_string, formatter);
        data_2 = LocalDate.parse(alla_data_string, formatter);

        AvviaSpesometro avvia = new AvviaSpesometro();
        avvia.actionProceduraDTR(data_1, data_2, db);

        FILE_PDF = FILE_PDF.replace("\\", "/");
        File fileppp = new File(FILE_PDF);
        InputStream input = new FileInputStream(fileppp);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        file_streamed_ricevute = new DefaultStreamedContent(input, externalContext.getMimeType(fileppp.getName()), fileppp.getName());

        return "";
    }

    public void actionEmesse() {

    }

    public void actionRicevute() {

    }
}
