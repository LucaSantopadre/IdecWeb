package idec.controller.ditta.vendor.jsf;

import idec.model.ditta.Vendor01Base;
import idec.controller.ditta.vendor.jsf.util.JsfUtil;
import idec.controller.ditta.vendor.jsf.util.JsfUtil.PersistAction;
import idec.controller.ditta.vendor.session.Vendor01BaseFacade;
import idec.model.ditta.Vendor02Sede;

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
import javax.inject.Inject;

@Named("vendor01BaseController")
@SessionScoped
public class Vendor01BaseController implements Serializable {

    // ***LUCA --------------------
    @Inject
    Vendor02SedeController vendor02;
    // -----------------------------
    
    @EJB
    private idec.controller.ditta.vendor.session.Vendor01BaseFacade ejbFacade;
    private List<Vendor01Base> items = null;
    private Vendor01Base selected;

    public Vendor01BaseController() {
    }

    public Vendor01Base getSelected() {
        return selected;
    }

    public void setSelected(Vendor01Base selected) {
        this.selected = selected;
        // ***LUCA
        for (Vendor02Sede item : selected.getVendor02SedeList()) {
            vendor02.setSelected(item);
        }

    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private Vendor01BaseFacade getFacade() {
        return ejbFacade;
    }

    public Vendor01Base prepareCreate() {
        selected = new Vendor01Base();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("Vendor01BaseCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("Vendor01BaseUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("Vendor01BaseDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Vendor01Base> getItems() {
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

    public Vendor01Base getVendor01Base(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Vendor01Base> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Vendor01Base> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Vendor01Base.class)
    public static class Vendor01BaseControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            Vendor01BaseController controller = (Vendor01BaseController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vendor01BaseController");
            return controller.getVendor01Base(getKey(value));
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
            if (object instanceof Vendor01Base) {
                Vendor01Base o = (Vendor01Base) object;
                return getStringKey(o.getVendor01Id());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Vendor01Base.class.getName()});
                return null;
            }
        }

    }

}
