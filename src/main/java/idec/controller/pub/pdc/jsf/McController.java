package idec.controller.pub.pdc.jsf;

import idec.model.pub.pdc.Mc;
import idec.controller.pub.pdc.jsf.util.JsfUtil;
import idec.controller.pub.pdc.jsf.util.JsfUtil.PersistAction;
import idec.controller.pub.pdc.session.McFacade;
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

@Named("mcController")
@SessionScoped
public class McController implements Serializable {

    @EJB
    private idec.controller.pub.pdc.session.McFacade ejbFacade;
    private List<Mc> items = null;
    private Mc selected;

    // *** luca
    @Inject
    private M2Controller m2Controller;
    @Inject
    private M3Controller m3Controller;
    @Inject
    private M4Controller m4Controller;
    @Inject
    private M5Controller m5Controller;

    public void mc_aggiorna(Reg03Rigo reg03Rigo) {
        //reg03Rigo.setMcSelected(selected);
        
        // caso conto padre in m5
        if (selected.getM5id() != null) {
            m5Controller.setSelected(selected.getM5id());
            m5Controller.m5_aggiorna(reg03Rigo);
            return;
        }
        // caso conto padre in m4
        if (selected.getM4id() != null) {
            m4Controller.setSelected(selected.getM4id());
            m4Controller.m4_aggiorna(reg03Rigo);
            return;
        }

        //caso conto padre in m3
        if (selected.getM3id() != null) {
            m3Controller.setSelected(selected.getM3id());
            m3Controller.m3_aggiorna(reg03Rigo);
            return;
        }

        // caso conto padre in m2
        if (selected.getM2id() != null) {
            m2Controller.setSelected(selected.getM2id());
            m2Controller.m2_aggiorna(reg03Rigo);
        }
    }
    // --------------------------------------------------------------

    public McController() {
    }

    public Mc getSelected() {
        return selected;
    }

    public void setSelected(Mc selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public void setItems(List<Mc> items) {
        this.items = items;
    }

    private McFacade getFacade() {
        return ejbFacade;
    }

    public Mc prepareCreate() {
        selected = new Mc();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("McCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("McUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("McDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Mc> getItems() {
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

    public Mc getMc(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Mc> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Mc> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Mc.class)
    public static class McControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            McController controller = (McController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mcController");
            return controller.getMc(getKey(value));
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
            if (object instanceof Mc) {
                Mc o = (Mc) object;
                return getStringKey(o.getMcId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Mc.class.getName()});
                return null;
            }
        }

    }

}
