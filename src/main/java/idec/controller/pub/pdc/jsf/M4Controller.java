package idec.controller.pub.pdc.jsf;

import idec.model.pub.pdc.M4;
import idec.controller.pub.pdc.jsf.util.JsfUtil;
import idec.controller.pub.pdc.jsf.util.JsfUtil.PersistAction;
import idec.controller.pub.pdc.session.M4Facade;
import idec.model.ditta.Reg03Rigo;
import idec.model.pub.pdc.M5;

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

@Named("m4Controller")
@SessionScoped
public class M4Controller implements Serializable {

    @EJB
    private idec.controller.pub.pdc.session.M4Facade ejbFacade;
    private List<M4> items = null;
    private M4 selected;
    
    
        // *** luca
    @Inject
    private M2Controller m2Controller;
    @Inject
    private M3Controller m3Controller;
    @Inject
    private M5Controller m5Controller;

    public void m4_aggiorna(Reg03Rigo reg03Rigo) {
        //reg03Rigo.setM4Selected(selected);
        
        List<M5> m5_lista = selected.getM5List();
        m5Controller.setItems(m5_lista);
        
        m3Controller.setSelected(selected.getM3id());
        
        m2Controller.setSelected(selected.getM3id().getM2id());
    }
    // --------------------------------------------------------------

    public M4Controller() {
    }

    public M4 getSelected() {
        return selected;
    }

    public void setSelected(M4 selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private M4Facade getFacade() {
        return ejbFacade;
    }

    public void setItems(List<M4> items) {
        this.items = items;
    }

    public M4 prepareCreate() {
        selected = new M4();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("M4Created"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("M4Updated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("M4Deleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<M4> getItems() {
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

    public M4 getM4(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<M4> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<M4> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = M4.class)
    public static class M4ControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            M4Controller controller = (M4Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "m4Controller");
            return controller.getM4(getKey(value));
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
            if (object instanceof M4) {
                M4 o = (M4) object;
                return getStringKey(o.getM4Id());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), M4.class.getName()});
                return null;
            }
        }

    }

}
