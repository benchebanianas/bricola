package controller;

import bean.DemandeVoiture;
import bean.Secteur;
import bean.Timing;
import bean.Ville;
import bean.VoitureMarque;
import bean.VoitureModele;
import bean.Worker;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.DemandeVoitureFacade;

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
import javax.faces.event.AjaxBehaviorEvent;
import static org.primefaces.component.focus.Focus.PropertyKeys.context;
import service.SecteurFacade;
import service.TimingFacade;
import service.VoitureCarburantCouleurFacade;
import service.WorkerJobFacade;

@Named("demandeVoitureController")
@SessionScoped
public class DemandeVoitureController implements Serializable {

    @EJB
    private service.DemandeVoitureFacade ejbFacade;
    @EJB
    private VoitureCarburantCouleurFacade modeleFacade;
    @EJB
    private SecteurFacade secteurFacade;
    @EJB
    private TimingFacade timingFacade;
    @EJB
    private WorkerJobFacade workerJobFacade;

    private List<DemandeVoiture> items = null;
    private DemandeVoiture selected;
    private VoitureMarque voitureMarque;
    private List<VoitureModele> modeles;
    private Ville ville;
    private List<Secteur> secteurs;
    private List<Worker> workers;

    public DemandeVoitureController() {
    }

    public void save() {
    }

    public List<Worker> checkChoice() {
        //hadii rah khedama ela 7sab yassine
        workers = workerJobFacade.findWorkerByServiceAndType("Location Voiture",null);
        return workers;
    }

    public List<Timing> loadTimings() {
        return timingFacade.findAll();

    }

//    public void loadModeles(final AjaxBehaviorEvent event) {
//        modeles = modeleFacade.searchByMarque(voitureMarque);
//    }

    public void loadSectors(final AjaxBehaviorEvent event) {
        secteurs = secteurFacade.findByVille(ville);
    }

    public DemandeVoiture getSelected() {
        if (selected == null) {
            selected = new DemandeVoiture();
        }
        return selected;
    }

    public void setSelected(DemandeVoiture selected) {
        this.selected = selected;
    }

    public List<Worker> getWorkers() {
        if (workers == null) {
            workers = new ArrayList<>();
        }
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Secteur> getSecteurs() {
        if (secteurs == null) {
            secteurs = new ArrayList();
        }
        return secteurs;
    }

    public void setSecteurs(List<Secteur> secteurs) {
        this.secteurs = secteurs;
    }

    public SecteurFacade getSecteurFacade() {
        return secteurFacade;
    }

    public void setSecteurFacade(SecteurFacade secteurFacade) {
        this.secteurFacade = secteurFacade;
    }

    public TimingFacade getTimingFacade() {
        return timingFacade;
    }

    public void setTimingFacade(TimingFacade timingFacade) {
        this.timingFacade = timingFacade;
    }

    public Ville getVille() {
        if (ville == null) {
            ville = new Ville();
        }
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public VoitureCarburantCouleurFacade getModeleFacade() {
        return modeleFacade;
    }

    public void setModeleFacade(VoitureCarburantCouleurFacade modeleFacade) {
        this.modeleFacade = modeleFacade;
    }

    public List<VoitureModele> getModeles() {
        return modeles;
    }

    public void setModeles(List<VoitureModele> modeles) {
        this.modeles = modeles;
    }

    public DemandeVoitureFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DemandeVoitureFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public VoitureMarque getVoitureMarque() {
        if (voitureMarque == null) {
            voitureMarque = new VoitureMarque();
        }
        return voitureMarque;
    }

    public void setVoitureMarque(VoitureMarque voitureMarque) {
        this.voitureMarque = voitureMarque;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeVoitureFacade getFacade() {
        return ejbFacade;
    }

    public DemandeVoiture prepareCreate() {
        selected = new DemandeVoiture();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeVoitureCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeVoitureUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeVoitureDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandeVoiture> getItems() {
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

    public DemandeVoiture getDemandeVoiture(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandeVoiture> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandeVoiture> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DemandeVoiture.class)
    public static class DemandeVoitureControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeVoitureController controller = (DemandeVoitureController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeVoitureController");
            return controller.getDemandeVoiture(getKey(value));
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
            if (object instanceof DemandeVoiture) {
                DemandeVoiture o = (DemandeVoiture) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandeVoiture.class.getName()});
                return null;
            }
        }

    }

}
