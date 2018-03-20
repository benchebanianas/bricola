package controller;

import bean.CuisineDemandeEvent;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.CuisineDemandeEventFacade;

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

@Named("cuisineDemandeEventController")
@SessionScoped
public class CuisineDemandeEventController implements Serializable {

    @EJB
    private service.CuisineDemandeEventFacade ejbFacade;
    private List<CuisineDemandeEvent> items = null;
    private CuisineDemandeEvent selected;

    public CuisineDemandeEventController() {
    }

    public CuisineDemandeEvent getSelected() {
        return selected;
    }

    public void setSelected(CuisineDemandeEvent selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CuisineDemandeEventFacade getFacade() {
        return ejbFacade;
    }

    public CuisineDemandeEvent prepareCreate() {
        selected = new CuisineDemandeEvent();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CuisineDemandeEventCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CuisineDemandeEventUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CuisineDemandeEventDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CuisineDemandeEvent> getItems() {
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

    public CuisineDemandeEvent getCuisineDemandeEvent(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<CuisineDemandeEvent> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CuisineDemandeEvent> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CuisineDemandeEvent.class)
    public static class CuisineDemandeEventControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CuisineDemandeEventController controller = (CuisineDemandeEventController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cuisineDemandeEventController");
            return controller.getCuisineDemandeEvent(getKey(value));
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
            if (object instanceof CuisineDemandeEvent) {
                CuisineDemandeEvent o = (CuisineDemandeEvent) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CuisineDemandeEvent.class.getName()});
                return null;
            }
        }

    }

}
