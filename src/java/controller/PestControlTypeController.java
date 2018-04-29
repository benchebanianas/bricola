package controller;

import bean.PestControlType;
import bean.ServicePricing;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.PestControlTypeFacade;

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
import service.PackagingFacade;

@Named("pestControlTypeController")
@SessionScoped
public class PestControlTypeController implements Serializable {

    @EJB
    private service.PestControlTypeFacade ejbFacade;
    @EJB
    private PackagingFacade packagingFacade;

    private DemandeServiceController demandeServiceController;

    private List<PestControlType> items;
    private PestControlType selected;
    private List<ServicePricing> unites;

    public void bookUnite(PestControlType type) {
        setSelected(type);
    }

    public List<ServicePricing> getUnites() {
        if (unites == null) {
            unites = packagingFacade.findServicePricingFromPackaging(selected);
        }
        return unites;
    }

    public String redirectToPestControl() {
        return "/demandeService/deratisation/PestControlPack.xhtml";
    }

    public String initService2(ServicePricing servicePricing) {
        String link = demandeServiceController.initService2("deratisation");
        demandeServiceController.getDemandePestControl().setTypeOfPestControl(selected);
        demandeServiceController.getDemandeService().setServicePricing(servicePricing);
        return link;
    }

    public void setUnites(List<ServicePricing> unites) {
        this.unites = unites;
    }

    public PestControlTypeController() {
    }

    public PestControlType getSelected() {
        return selected;
    }

    public void setSelected(PestControlType selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PestControlTypeFacade getFacade() {
        return ejbFacade;
    }

    public PestControlType prepareCreate() {
        selected = new PestControlType();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PestControlTypeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PestControlTypeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PestControlTypeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PestControlType> getItems() {
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

    public PestControlType getPestControlType(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<PestControlType> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PestControlType> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PestControlType.class)
    public static class PestControlTypeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PestControlTypeController controller = (PestControlTypeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pestControlTypeController");
            return controller.getPestControlType(getKey(value));
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
            if (object instanceof PestControlType) {
                PestControlType o = (PestControlType) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PestControlType.class.getName()});
                return null;
            }
        }

    }

}
