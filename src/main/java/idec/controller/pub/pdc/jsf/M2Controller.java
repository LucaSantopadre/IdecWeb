package idec.controller.pub.pdc.jsf;

import idec.model.pub.pdc.M2;
import idec.controller.pub.pdc.jsf.util.JsfUtil;
import idec.controller.pub.pdc.jsf.util.JsfUtil.PersistAction;
import idec.controller.pub.pdc.session.M2Facade;
import idec.model.ditta.Reg03Rigo;
import idec.model.pub.pdc.M3;
import idec.model.pub.pdc.M4;
import idec.model.pub.pdc.M5;
import idec.model.pub.pdc.Mc;

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

@Named("m2Controller")
@SessionScoped
public class M2Controller implements Serializable {

    @EJB
    private idec.controller.pub.pdc.session.M2Facade ejbFacade;
    private List<M2> items = null;
    private M2 selected;

    // *** luca
    @Inject
    private M3Controller m3Controller;
    @Inject
    private M4Controller m4Controller;
    @Inject
    private M5Controller m5Controller;
    @Inject
    private McController mcController;

    public void m2_aggiorna(Reg03Rigo reg03Rigo) {
        //reg03Rigo.setM2Selected(selected);
        
        List<M3> m3_lista = selected.getM3List();
        m3Controller.setItems(m3_lista);

        List<M4> m4_lista = new ArrayList<>();
        for (M3 m3 : m3_lista) {
            m4_lista.addAll(m3.getM4List());
        }
        m4Controller.setItems(m4_lista);

        List<M5> m5_lista = new ArrayList<>();
        for (M4 m4 : m4_lista) {
            m5_lista.addAll(m4.getM5List());
        }
        m5Controller.setItems(m5_lista);

        // conti m2
//        List<Mc> mc_lista = new ArrayList<>();
//        mc_lista.addAll(selected.getMcList());
//        mcController.setItems(mc_lista);
    }
    // --------------------------------------------------------------

    public M2Controller() {
    }

    public M2 getSelected() {
        return selected;
    }

    public void setItems(List<M2> items) {
        this.items = items;
    }

    public void setSelected(M2 selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private M2Facade getFacade() {
        return ejbFacade;
    }

    public M2 prepareCreate() {
        selected = new M2();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("M2Created"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("M2Updated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("M2Deleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<M2> getItems() {
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

    public M2 getM2(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<M2> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<M2> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = M2.class)
    public static class M2ControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            M2Controller controller = (M2Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "m2Controller");
            return controller.getM2(getKey(value));
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
            if (object instanceof M2) {
                M2 o = (M2) object;
                return getStringKey(o.getM2Id());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), M2.class.getName()});
                return null;
            }
        }

    }

}
