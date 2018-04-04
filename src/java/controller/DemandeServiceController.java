package controller;

import bean.DemandeService;
import bean.Manager;
import bean.TypeAction;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.DemandeServiceFacade;

import java.io.Serializable;
import java.util.Date;
import controller.util.*;
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
import service.DemandeServiceConfirmationDetailFacade;
import service.TypeActionFacade;

@Named("demandeServiceController")
@SessionScoped
public class DemandeServiceController implements Serializable {

    @EJB
    private service.DemandeServiceFacade ejbFacade;
    @EJB
    private DemandeServiceConfirmationDetailFacade detailFacade;
    private List<DemandeService> items = null;
    private DemandeService selected;
    @EJB
    private TypeActionFacade typeActionFacade;

    public String voirPlus(DemandeService demande) {
        Object d = ejbFacade.findDemande(demande);
        SessionUtil.setAttribute("demande", d);
        if (demande.getTypeDemande().getId().equals("DemandeBabySitting")) {
            return "/demandeBabySitting/babySittingView";
        } else if (demande.getTypeDemande().getId().equals("DemandeCleaning")) {
            return "/demandeCleaning/cleaningView";
        } else if (demande.getTypeDemande().getId().equals("DemandeEvent")) {
            return "/demandeEvent/eventView";
        } else if (demande.getTypeDemande().getId().equals("DemandeGardening")) {
            return "/demandeGardening/gardeningView";
        } else if (demande.getTypeDemande().getId().equals("DemandeHandyMan")) {
            return "/demandeHandyMan/handyManView";
        } else if (demande.getTypeDemande().getId().equals("DemandeMoving")) {
            return "/demandeMoving/movingView";
        } else if (demande.getTypeDemande().getId().equals("DemandePainting")) {
            return "/demandePainting/paintingView";
        } else if (demande.getTypeDemande().getId().equals("DemandePestControl")) {
            return "/demandePestControl/pestControlView";
        } else if (demande.getTypeDemande().getId().equals("DemandePhotographie")) {
            return "/demandePhotographie/photographieView";
        } else if (demande.getTypeDemande().getId().equals("DemandeVoiture")) {
            return "/demandeVoiture/voitureView";
        }
        return "#";

    }

    public String Action(DemandeService demandeService, Long idType) {
        Manager manager = (Manager) SessionUtil.getAttribute("connectedManager");
        if (idType == 1) {
            demandeService.setDateConfirmation(new Date());
            demandeService.setDateSuppression(null);
        }
        if (idType == 2) {
            demandeService.setDateSuppression(new Date());
            demandeService.setDateConfirmation(null);
        }
        demandeService.setManagerConfirmation(manager);
        ejbFacade.edit(demandeService);
        TypeAction action = typeActionFacade.find(idType);
        detailFacade.save(manager, action, demandeService);
        return "#";
    }

    public int nvDmd() {
        List<DemandeService> dmnds = ejbFacade.findAll();
        int nbr = 0;
        for (DemandeService dmnd : dmnds) {
            if (dmnd.getDateConfirmation() == null && dmnd.getDateSuppression() == null) {
                nbr++;
            }
        }
        return nbr;
    }

    public DemandeServiceController() {
    }

    public DemandeService getSelected() {
        return selected;
    }

    public void setSelected(DemandeService selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeServiceFacade getFacade() {
        return ejbFacade;
    }

    public DemandeService prepareCreate() {
        selected = new DemandeService();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeServiceCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeServiceUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeServiceDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandeService> getItems() {
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

    public DemandeService getDemandeService(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandeService> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandeService> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DemandeService.class)
    public static class DemandeServiceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeServiceController controller = (DemandeServiceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeServiceController");
            return controller.getDemandeService(getKey(value));
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
            if (object instanceof DemandeService) {
                DemandeService o = (DemandeService) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandeService.class.getName()});
                return null;
            }
        }

    }

}
