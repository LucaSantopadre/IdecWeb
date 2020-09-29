package idec.controller.pub.pdc.jsf;

import idec.model.pub.pdc.M3;
import idec.controller.pub.pdc.jsf.util.JsfUtil;
import idec.controller.pub.pdc.jsf.util.JsfUtil.PersistAction;
import idec.controller.pub.pdc.session.M3Facade;
import idec.model.ditta.Reg03Rigo;
import idec.model.pub.pdc.M4;
import idec.model.pub.pdc.M5;

import java.io.Serializable;
import java.util.ArrayList;
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

@Named("m3Controller")
@SessionScoped
public class M3Controller implements Serializable {

    @EJB
    private idec.controller.pub.pdc.session.M3Facade ejbFacade;
    private List<M3> items = null;
    private M3 selected;

    // *** luca
    @Inject
    private M2Controller m2Controller;
    @Inject
    private M4Controller m4Controller;
    @Inject
    private M5Controller m5Controller;

    public void m3_aggiorna(Reg03Rigo reg03Rigo) {
        //reg03Rigo.setM3Selected(selected);
        
        List<M4> m4_lista = selected.getM4List();
        m4Controller.setItems(m4_lista);

        List<M5> m5_lista = new ArrayList<>();
        for (M4 m4 : m4_lista) {
            m5_lista.addAll(m4.getM5List());
        }
        m5Controller.setItems(m5_lista);

        m2Controller.setSelected(selected.getM2id());
        setSelected(selected);
    }
    // --------------------------------------------------------------

    public M3Controller() {
    }

    public M3 getSelected() {
        return selected;
    }

    public void setSelected(M3 selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    public void setItems(List<M3> items) {
        this.items = items;
    }

    protected void initializeEmbeddableKey() {
    }

    private M3Facade getFacade() {
        return ejbFacade;
    }

    public M3 prepareCreate() {
        selected = new M3();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("M3Created"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("M3Updated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("M3Deleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<M3> getItems() {
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

    public M3 getM3(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<M3> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<M3> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = M3.class)
    public static class M3ControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            M3Controller controller = (M3Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "m3Controller");
            return controller.getM3(getKey(value));
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
            if (object instanceof M3) {
                M3 o = (M3) object;
                return getStringKey(o.getM3Id());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), M3.class.getName()});
                return null;
            }
        }

    }

}
