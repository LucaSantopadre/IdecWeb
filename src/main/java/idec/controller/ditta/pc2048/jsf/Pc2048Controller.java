package idec.controller.ditta.pc2048.jsf;

import idec.model.ditta.Pc2048;
import idec.controller.ditta.pc2048.jsf.util.JsfUtil;
import idec.controller.ditta.pc2048.jsf.util.JsfUtil.PersistAction;
import idec.controller.ditta.pc2048.session.Pc2048Facade;

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

@Named("pc2048Controller")
@SessionScoped
public class Pc2048Controller implements Serializable {

    @EJB
    private idec.controller.ditta.pc2048.session.Pc2048Facade ejbFacade;
    private List<Pc2048> items = null;
    private Pc2048 selected;

    public Pc2048Controller() {
    }

    public Pc2048 getSelected() {
        return selected;
    }

    public void setSelected(Pc2048 selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setPc2048PK(new idec.model.ditta.Pc2048PK());
    }

    private Pc2048Facade getFacade() {
        return ejbFacade;
    }

    public Pc2048 prepareCreate() {
        selected = new Pc2048();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("Pc2048Created"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("Pc2048Updated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("Pc2048Deleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Pc2048> getItems() {
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

    public Pc2048 getPc2048(idec.model.ditta.Pc2048PK id) {
        return getFacade().find(id);
    }

    public List<Pc2048> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Pc2048> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Pc2048.class)
    public static class Pc2048ControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            Pc2048Controller controller = (Pc2048Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pc2048Controller");
            return controller.getPc2048(getKey(value));
        }

        idec.model.ditta.Pc2048PK getKey(String value) {
            idec.model.ditta.Pc2048PK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new idec.model.ditta.Pc2048PK();
            key.setCgTipoMasCod(Integer.parseInt(values[0]));
            key.setCeeMasCod(Integer.parseInt(values[1]));
            key.setCeeMas2Cod(Integer.parseInt(values[2]));
            key.setCeeMas3Cod(Integer.parseInt(values[3]));
            key.setCeeMas4Cod(Integer.parseInt(values[4]));
            key.setCeeContoCod(values[5]);
            key.setCeeCodiceCod(values[6]);
            return key;
        }

        String getStringKey(idec.model.ditta.Pc2048PK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCgTipoMasCod());
            sb.append(SEPARATOR);
            sb.append(value.getCeeMasCod());
            sb.append(SEPARATOR);
            sb.append(value.getCeeMas2Cod());
            sb.append(SEPARATOR);
            sb.append(value.getCeeMas3Cod());
            sb.append(SEPARATOR);
            sb.append(value.getCeeMas4Cod());
            sb.append(SEPARATOR);
            sb.append(value.getCeeContoCod());
            sb.append(SEPARATOR);
            sb.append(value.getCeeCodiceCod());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Pc2048) {
                Pc2048 o = (Pc2048) object;
                return getStringKey(o.getPc2048PK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pc2048.class.getName()});
                return null;
            }
        }

    }

}
