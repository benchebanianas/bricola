package controller;

import bean.DemandeServiceConfirmationDetail;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.DemandeServiceConfirmationDetailFacade;

import java.io.Serializable;
import java.util.Date;
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

@Named("demandeServiceConfirmationDetailController")
@SessionScoped
public class DemandeServiceConfirmationDetailController implements Serializable {

    @EJB
    private service.DemandeServiceConfirmationDetailFacade ejbFacade;
    private List<DemandeServiceConfirmationDetail> items = null;
    private DemandeServiceConfirmationDetail selected;
    //search
    private String workerNom;
    private Long secteurR;
    private Long serviceR;
    private Date dateDemande;
    private Date dateAction;
    private Long typeAction;
    
    public void recherche(){
        items = ejbFacade.findByCriteria(null,dateAction,secteurR, workerNom, serviceR,dateDemande, typeAction);
    }
    
    public DemandeServiceConfirmationDetailController() {
    }

    public DemandeServiceConfirmationDetail getSelected() {
        return selected;
    }

    public void setSelected(DemandeServiceConfirmationDetail selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public String getWorkerNom() {
        return workerNom;
    }

    public void setWorkerNom(String workerNom) {
        this.workerNom = workerNom;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    public Long getSecteurR() {
        return secteurR;
    }

    public void setSecteurR(Long secteurR) {
        this.secteurR = secteurR;
    }

    public Long getServiceR() {
        return serviceR;
    }

    public void setServiceR(Long serviceR) {
        this.serviceR = serviceR;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Long getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(Long typeAction) {
        this.typeAction = typeAction;
    }

    
    private DemandeServiceConfirmationDetailFacade getFacade() {
        return ejbFacade;
    }

    public DemandeServiceConfirmationDetail prepareCreate() {
        selected = new DemandeServiceConfirmationDetail();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeServiceConfirmationDetailCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeServiceConfirmationDetailUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeServiceConfirmationDetailDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandeServiceConfirmationDetail> getItems() {
        if (items == null) {
            items = ejbFacade.findAll();
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

    public DemandeServiceConfirmationDetail getDemandeServiceConfirmationDetail(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandeServiceConfirmationDetail> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandeServiceConfirmationDetail> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DemandeServiceConfirmationDetail.class)
    public static class DemandeServiceConfirmationDetailControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeServiceConfirmationDetailController controller = (DemandeServiceConfirmationDetailController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeServiceConfirmationDetailController");
            return controller.getDemandeServiceConfirmationDetail(getKey(value));
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
            if (object instanceof DemandeServiceConfirmationDetail) {
                DemandeServiceConfirmationDetail o = (DemandeServiceConfirmationDetail) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandeServiceConfirmationDetail.class.getName()});
                return null;
            }
        }

    }

}
