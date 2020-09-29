package idec.controller.ditta.vendor.jsf;

import idec.model.ditta.Vendor02Sede;
import idec.controller.ditta.vendor.jsf.util.JsfUtil;
import idec.controller.ditta.vendor.jsf.util.JsfUtil.PersistAction;
import idec.controller.ditta.vendor.session.Vendor02SedeFacade;

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

@Named("vendor02SedeController")
@SessionScoped
public class Vendor02SedeController implements Serializable {

    @EJB
    private idec.controller.ditta.vendor.session.Vendor02SedeFacade ejbFacade;
    private List<Vendor02Sede> items = null;
    private Vendor02Sede selected;

    public Vendor02SedeController() {
    }

    public Vendor02Sede getSelected() {
        return selected;
    }

    public void setSelected(Vendor02Sede selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private Vendor02SedeFacade getFacade() {
        return ejbFacade;
    }

    public Vendor02Sede prepareCreate() {
        selected = new Vendor02Sede();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("Vendor02SedeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("Vendor02SedeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("Vendor02SedeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Vendor02Sede> getItems() {
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

    public Vendor02Sede getVendor02Sede(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Vendor02Sede> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Vendor02Sede> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Vendor02Sede.class)
    public static class Vendor02SedeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            Vendor02SedeController controller = (Vendor02SedeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vendor02SedeController");
            return controller.getVendor02Sede(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Vendor02Sede) {
                Vendor02Sede o = (Vendor02Sede) object;
                return getStringKey(o.getVendor02Id());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Vendor02Sede.class.getName()});
                return null;
            }
        }

    }

}
