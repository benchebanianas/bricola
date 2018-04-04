package controller;

import bean.Client;
import bean.Day;
import bean.DemandeCleaning;
import bean.DemandeService;
import bean.Manager;
import bean.TypeAction;
import bean.PlanningItem;
import bean.Secteur;
import bean.Service;
import bean.Timing;
import bean.Ville;
import bean.Worker;
import bean.WorkerType;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.DemandeServiceFacade;

import java.io.Serializable;
import java.util.Date;
import controller.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import service.DemandeServiceConfirmationDetailFacade;
import service.TypeActionFacade;
import org.primefaces.context.RequestContext;

@Named("demandeServiceController")
@SessionScoped
public class DemandeServiceController implements Serializable {

    @EJB
    private service.DemandeServiceFacade ejbFacade;
    @EJB
    private DemandeServiceConfirmationDetailFacade detailFacade;
    private service.DemandeCleaningFacade demandeCleaningFacade;
    @EJB
    private service.WorkerTypeFacade workerTypeFacade;
    @EJB
    private service.WorkerJobFacade workerJobFacade;
    @EJB
    private service.ServiceFacade serviceFacade;
    @EJB
    private service.TimingFacade timingFacade;
    @EJB
    private service.DayFacade dayFacade;
    @EJB
    private service.ClientFacade clientFacade;
    @EJB
    private service.VilleFacade villeFacade;
    @EJB
    private service.SecteurFacade secteurFacade;
    private List<Worker> companies;
    private List<Worker> individuals;
    private List<Secteur> secteurs;

    private DemandeService demandeService;
    private DemandeCleaning demandeCleaning;

    private WorkerType workerType;
    private Service currentService;
    private Worker company;
    private Worker individual;
    private PlanningItem planningItem;
    private Client loadedClient;
    private Ville ville;

    private boolean oneTime = true;
    private boolean multipleTimes;
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

    public void saveDemande() {

        try {
            ejbFacade.saveDemandeService(demandeService, currentService, company, individual, oneTime, multipleTimes);
            if (currentService.getId() == 1) {
                demandeCleaningFacade.saveDemandeCleaning(demandeCleaning, demandeService);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Demande enregistrer avec succes !"));
            resetObjects();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Erreur lors du sauvgarde de la demande !"));
        }
    }

    public void resetObjects() {

        demandeService = new DemandeService();
        demandeCleaning = new DemandeCleaning();
    }

    public void checkOnce() {
        if (oneTime == true) {
            multipleTimes = false;
        }
        if (oneTime == false) {
            oneTime = true;
        }
    }

    public void checkMany() {
        if (multipleTimes == true) {
            oneTime = false;
        }
        if (multipleTimes == false) {
            multipleTimes = true;
        }
    }

    public void initService(String nomService) {
        demandeService = new DemandeService();
        currentService = serviceFacade.findServiceByName(nomService);
    }

    public List<Timing> loadTimings() {
        return timingFacade.findAll();
    }

    public List<Day> loadDays() {
        return dayFacade.findAll();
    }

    public List<WorkerType> loadWorkerType() {
        return workerTypeFacade.findAll();
    }

    public void checkChoice() {

        RequestContext context = RequestContext.getCurrentInstance();
        if (demandeService.getWorkerType().getId() == 2) {
            companies = workerJobFacade.findWorkerByServiceAndType(currentService.getNom(), demandeService.getWorkerType().getId());
            context.execute("PF('SocieteDialog').show()");
        } else if (demandeService.getWorkerType().getId() == 1) {
            individuals = workerJobFacade.findWorkerByServiceAndType(currentService.getNom(), demandeService.getWorkerType().getId());
            context.execute("PF('IndividualDialog').show()");
        }
    }

    public List<Integer> loadNumeroJour() {
        List<Integer> numeroJours = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            numeroJours.add(i + 1);
        }
        return numeroJours;
    }

    public void addPlanningItem() {

        PlanningItem item = clone(planningItem);
        demandeService.getPlanning().getPlanningItems().add(item);

    }

    public void deletePlanningItem(PlanningItem item) {

        demandeService.getPlanning().getPlanningItems().remove(item);
    }

    public PlanningItem clone(PlanningItem myPlanning) {

        PlanningItem item = new PlanningItem();

        int id = 0;
        try {
            id = demandeService.getPlanning().getPlanningItems().get(demandeService.getPlanning().getPlanningItems().size() - 1).getId().intValue() + 1;
        } catch (Exception e) {
        }

        item.setId(new Long(id));
        item.setDay(myPlanning.getDay());
        item.setNumeroJour(myPlanning.getNumeroJour());
        item.setRepetition(myPlanning.getRepetition());
        item.setTiming(myPlanning.getTiming());
        System.out.println("hani item : " + item);
        return item;
    }

    public void checkEmail() {

        List<Client> clients = clientFacade.checkEmail(demandeService.getClient().getEmail());

        if (clients.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Adresse email non enregister, veuillez remplir les champs !"));

        } else {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('ConnexionDialog').show()");
            loadedClient = clients.get(0);
        }
    }

    public List<Ville> loadVilles() {
        return villeFacade.findAll();
    }

    public void loadSectors() {
        secteurs = secteurFacade.findByVille(ville);
    }

    public void checkPassword() {
        if (demandeService.getClient().getPassword().equals(loadedClient.getPassword())) {
            demandeService.setClient(loadedClient);
            ville = loadedClient.getSecteur().getVille();
            loadSectors();
            demandeService.getClient().setSecteur(loadedClient.getSecteur());
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('ConnexionDialog').hide()");

        } else {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('ConnexionDialog').jq.effect('shake', {times:5}, 100)");

        }
    }

    public List<Secteur> getSecteurs() {
        if (secteurs == null) {
            secteurs = new ArrayList<>();
        }
        return secteurs;
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

    public Client getLoadedClient() {
        if (loadedClient == null) {
            loadedClient = new Client();
        }
        return loadedClient;
    }

    public void setLoadedClient(Client loadedClient) {
        this.loadedClient = loadedClient;
    }

    public PlanningItem getPlanningItem() {
        if (planningItem == null) {
            planningItem = new PlanningItem();
        }
        return planningItem;
    }

    public void setPlanningItem(PlanningItem planningItem) {
        this.planningItem = planningItem;
    }

    public Worker getCompany() {
        if (company == null) {
            company = new Worker();
        }
        return company;
    }

    public void setCompany(Worker company) {
        this.company = company;
    }

    public Worker getIndividual() {
        if (individual == null) {
            individual = new Worker();
        }
        return individual;
    }

    public void setIndividual(Worker individual) {
        this.individual = individual;
    }

    public List<Worker> getCompanies() {
        if (companies == null) {
            companies = new ArrayList<>();
        }
        return companies;
    }

    public void setCompanies(List<Worker> companies) {
        this.companies = companies;
    }

    public List<Worker> getIndividuals() {
        if (individuals == null) {
            individuals = new ArrayList<>();
        }
        return individuals;
    }

    public void setIndividuals(List<Worker> individuals) {
        this.individuals = individuals;
    }

    public WorkerType getWorkerType() {
        if (workerType == null) {
            workerType = new WorkerType();
        }
        return workerType;
    }

    public void setWorkerType(WorkerType workerType) {
        this.workerType = workerType;
    }

    public DemandeCleaning getDemandeCleaning() {
        if (demandeCleaning == null) {
            demandeCleaning = new DemandeCleaning();
        }
        return demandeCleaning;
    }

    public void setDemandeCleaning(DemandeCleaning demandeCleaning) {
        this.demandeCleaning = demandeCleaning;
    }

    public Service getCurrentService() {
        if (currentService == null) {
            currentService = new Service();
        }
        return currentService;
    }

    public void setCurrentService(Service currentService) {
        this.currentService = currentService;
    }

    public DemandeService getDemandeService() {
        if (demandeService == null) {
            demandeService = new DemandeService();
        }
        return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    public boolean isMultipleTimes() {
        return multipleTimes;
    }

    public void setMultipleTimes(boolean multipleTimes) {
        this.multipleTimes = multipleTimes;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public void setOneTime(boolean oneTime) {
        this.oneTime = oneTime;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeServiceFacade getFacade() {
        return ejbFacade;
    }

    public DemandeService prepareCreate() {
        demandeService = new DemandeService();
        initializeEmbeddableKey();
        return demandeService;
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
            demandeService = null; // Remove selection
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
        if (demandeService != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(demandeService);
                } else {
                    getFacade().remove(demandeService);
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
