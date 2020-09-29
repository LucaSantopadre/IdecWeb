package idec.controller.ditta.reg.jsf;

import idec.model.ditta.Reg04Iva;
import idec.controller.ditta.reg.jsf.util.JsfUtil;
import idec.controller.ditta.reg.jsf.util.JsfUtil.PersistAction;
import idec.controller.ditta.reg.session.Reg04IvaFacade;

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

@Named("reg04IvaController")
@SessionScoped
public class Reg04IvaController implements Serializable {

    @EJB
    private idec.controller.ditta.reg.session.Reg04IvaFacade ejbFacade;
    private List<Reg04Iva> items = null;
    private Reg04Iva selected;

    public Reg04IvaController() {
    }

    public Reg04Iva getSelected() {
        return selected;
    }

    public void setSelected(Reg04Iva selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private Reg04IvaFacade getFacade() {
        return ejbFacade;
    }

    public Reg04Iva prepareCreate() {
        selected = new Reg04Iva();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("Reg04IvaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("Reg04IvaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("Reg04IvaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Reg04Iva> getItems() {
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

    public Reg04Iva getReg04Iva(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Reg04Iva> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Reg04Iva> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Reg04Iva.class)
    public static class Reg04IvaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            Reg04IvaController controller = (Reg04IvaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reg04IvaController");
            return controller.getReg04Iva(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Reg04Iva) {
                Reg04Iva o = (Reg04Iva) object;
                return getStringKey(o.getReg04IvaId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Reg04Iva.class.getName()});
                return null;
            }
        }

    }

}
