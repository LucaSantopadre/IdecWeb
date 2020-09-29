package idec.controller.pub.pdc.jsf;

import idec.model.pub.pdc.M1;
import idec.controller.pub.pdc.jsf.util.JsfUtil;
import idec.controller.pub.pdc.jsf.util.JsfUtil.PersistAction;
import idec.controller.pub.pdc.session.M1Facade;
import idec.controller.view.ditta.reg.mas.jsf.M2Menu;
import idec.model.ditta.Reg03Rigo;
import idec.model.pub.pdc.M2;
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

@Named("m1Controller")
@SessionScoped
public class M1Controller implements Serializable {

    @EJB
    private idec.controller.pub.pdc.session.M1Facade ejbFacade;
    private List<M1> items = null;
    private M1 selected;

    // *** luca
    @Inject
    private M2Controller m2Controller;
    @Inject
    private M3Controller m3Controller;
    @Inject
    private M4Controller m4Controller;
    @Inject
    private M5Controller m5Controller;
    @Inject
    private McController mcController;

    public void m1_aggiorna(Reg03Rigo reg03Rigo) {
        //reg03Rigo.setM1Selected(selected);
        
        List<M2> m2_lista = selected.getM2List();
        m2Controller.setItems(m2_lista);

        List<M3> m3_lista = new ArrayList<>();
        for (M2 m2 : m2_lista) {
            m3_lista.addAll(m2.getM3List());
        }
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

        //aggiorno mc
        List<Mc> mc_lista = new ArrayList<>();
        for (M2 m2 : m2_lista) {
            mc_lista.addAll(m2.getMcList());
        }
        for (M3 m3 : m3_lista) {
            mc_lista.addAll(m3.getMcList());
        }
        for (M4 m4 : m4_lista) {
            mc_lista.addAll(m4.getMcList());
        }
        for (M5 m5 : m5_lista) {
            mc_lista.addAll(m5.getMcList());
        }
        mcController.setItems(mc_lista);
    }
    // --------------------------------------------------------------

    public M1Controller() {
    }

    public M1 getSelected() {
        return selected;
    }

    public void setSelected(M1 selected) {
        this.selected = selected;
    }

    public void setItems(List<M1> items) {
        this.items = items;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private M1Facade getFacade() {
        return ejbFacade;
    }

    public M1 prepareCreate() {
        selected = new M1();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("M1Created"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("M1Updated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("M1Deleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<M1> getItems() {
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

    public M1 getM1(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<M1> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<M1> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = M1.class)
    public static class M1ControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            M1Controller controller = (M1Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "m1Controller");
            return controller.getM1(getKey(value));
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
            if (object instanceof M1) {
                M1 o = (M1) object;
                return getStringKey(o.getM1Id());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), M1.class.getName()});
                return null;
            }
        }

    }

}
