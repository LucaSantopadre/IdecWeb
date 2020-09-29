package idec.controller.pub.pdc.jsf;

import idec.model.pub.pdc.M5;
import idec.controller.pub.pdc.jsf.util.JsfUtil;
import idec.controller.pub.pdc.jsf.util.JsfUtil.PersistAction;
import idec.controller.pub.pdc.session.M5Facade;
import idec.model.ditta.Reg03Rigo;
import idec.model.pub.pdc.M4;

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

@Named("m5Controller")
@SessionScoped
public class M5Controller implements Serializable {

    @EJB
    private idec.controller.pub.pdc.session.M5Facade ejbFacade;
    private List<M5> items = null;
    private M5 selected;

    // *** luca
    @Inject
    private M2Controller m2Controller;
    @Inject
    private M3Controller m3Controller;
    @Inject
    private M4Controller m4Controller;

    public void m5_aggiorna(Reg03Rigo reg03Rigo) {
        //reg03Rigo.setM5Selected(selected);
        
        m4Controller.setSelected(selected.getM4id());
        
        m3Controller.setSelected(selected.getM4id().getM3id());
        
        m2Controller.setSelected(selected.getM4id().getM3id().getM2id());
    }
    // --------------------------------------------------------------

    public M5Controller() {
    }

    public M5 getSelected() {
        return selected;
    }

    public void setSelected(M5 selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public void setItems(List<M5> items) {
        this.items = items;
    }

    private M5Facade getFacade() {
        return ejbFacade;
    }

    public M5 prepareCreate() {
        selected = new M5();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("M5Created"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("M5Updated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("M5Deleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<M5> getItems() {
//        if (items == null) {
//            items = getFacade().findAll();
//        }
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

    public M5 getM5(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<M5> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<M5> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = M5.class)
    public static class M5ControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            M5Controller controller = (M5Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "m5Controller");
            return controller.getM5(getKey(value));
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
            if (object instanceof M5) {
                M5 o = (M5) object;
                return getStringKey(o.getM5Id());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), M5.class.getName()});
                return null;
            }
        }

    }

}
