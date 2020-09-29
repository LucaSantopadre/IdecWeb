package idec.controller.ditta.reg.jsf;

import idec.model.ditta.Reg03Rigo;
import idec.controller.ditta.reg.jsf.util.JsfUtil;
import idec.controller.ditta.reg.jsf.util.JsfUtil.PersistAction;
import idec.controller.ditta.reg.session.Reg03RigoFacade;
import idec.model.ditta.RegDoc;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("reg03RigoController")
@SessionScoped
public class Reg03RigoController implements Serializable {

    @EJB
    private idec.controller.ditta.reg.session.Reg03RigoFacade ejbFacade;
    private List<Reg03Rigo> items = null;
    private Reg03Rigo selected;

    // ***LUCA 
    private List<Reg03Rigo> listaPerReg01 = null;
    private RegDoc regDocPerRigo = null;

    public RegDoc getRegDocPerRigo() {
        return regDocPerRigo;
    }

    public void setRegDocPerRigo(RegDoc regDocPerRigo) {
        this.regDocPerRigo = regDocPerRigo;
    }

    public List<Reg03Rigo> getListaPerReg01() {
        return listaPerReg01;
    }

    public void setListaPerReg01(List<Reg03Rigo> listaPerReg01) {
        this.listaPerReg01 = listaPerReg01;
    }
    // -----------------------------

    public Reg03RigoController() {
    }

    public Reg03Rigo getSelected() {
        return selected;
    }

    public void setSelected(Reg03Rigo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setReg03RigoPK(new idec.model.ditta.Reg03RigoPK());
    }

    private Reg03RigoFacade getFacade() {
        return ejbFacade;
    }

    public Reg03Rigo prepareCreate() {
        selected = new Reg03Rigo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("Reg03RigoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("Reg03RigoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("Reg03RigoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Reg03Rigo> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Reg03Rigo getReg03Rigo(idec.model.ditta.Reg03RigoPK id) {
        return getFacade().find(id);
    }

    public List<Reg03Rigo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Reg03Rigo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Reg03Rigo.class)
    public static class Reg03RigoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            Reg03RigoController controller = (Reg03RigoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reg03RigoController");
            return controller.getReg03Rigo(getKey(value));
        }

        idec.model.ditta.Reg03RigoPK getKey(String value) {
            idec.model.ditta.Reg03RigoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new idec.model.ditta.Reg03RigoPK();
            key.setReg03RigoRegId(Long.parseLong(values[0]));
            key.setReg03SubrigoRegId(Long.parseLong(values[1]));
            key.setReg03NumRegId(Long.parseLong(values[2]));
            return key;
        }

        String getStringKey(idec.model.ditta.Reg03RigoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getReg03RigoRegId());
            sb.append(SEPARATOR);
            sb.append(value.getReg03SubrigoRegId());
            sb.append(SEPARATOR);
            sb.append(value.getReg03NumRegId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Reg03Rigo) {
                Reg03Rigo o = (Reg03Rigo) object;
                return getStringKey(o.getReg03RigoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Reg03Rigo.class.getName()});
                return null;
            }
        }

    }

}
