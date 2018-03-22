package controller;

import bean.Client;
import bean.Day;
import bean.DemandeCleaning;
import bean.PlanningItem;
import bean.Secteur;
import bean.Timing;
import bean.Ville;
import bean.Worker;
import bean.WorkerType;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.DemandeCleaningFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import org.primefaces.context.RequestContext;

@Named("demandeCleaningController")
@SessionScoped
public class DemandeCleaningController implements Serializable {

    @EJB
    private service.DemandeCleaningFacade ejbFacade;
    @EJB
    private service.WorkerTypeFacade workerTypeFacade;
    @EJB
    private service.DayFacade dayFacade;
    @EJB
    private service.VilleFacade villeFacade;
    @EJB
    private service.SecteurFacade secteurFacade;
    @EJB
    private service.ClientFacade clientFacade;
    @EJB
    private service.TimingFacade timingFacade;
    @EJB
    private service.WorkerJobFacade workerJobFacade;
    private List<DemandeCleaning> items = null;
    private DemandeCleaning selected;
    private boolean cleaningOnce = true;
    private boolean cleaningMnayTimes;
    private Client loadedClient;
    private Date date;
    private Date dateOnce;
    private List<Date> dates;
    private Ville ville;
    private Secteur secteur;
    private List<Secteur> secteurs;
    private List<Worker> companies;
    private List<Worker> individuals;
    private int choice;
    private int repetition;
    private Worker company;
    private WorkerType workerType;
    private Worker individual;
    private PlanningItem planningItem;

    private DemandeCleaning demandeCleaning;

    public DemandeCleaningController() {
    }

    public List<WorkerType> loadWorkerType() {
        return workerTypeFacade.findAll();
    }

    public void saveDemandeCleaning() {

    }

    public void deletePlanningItem(PlanningItem item) {

        demandeCleaning.getDemandeService().getPlanning().getPlanningItems().remove(item);
    }

    public void addPlanningItem() {

        PlanningItem item = clone(planningItem, demandeCleaning.getDemandeService().getPlanning().getPlanningItems());
        demandeCleaning.getDemandeService().getPlanning().getPlanningItems().add(item);

    }

    public List<Integer> loadNumeroJour() {
        List<Integer> numeroJours = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            numeroJours.add(i + 1);
        }
        return numeroJours;
    }

    public List<Timing> loadTimings() {
        return timingFacade.findAll();
    }

    public List<Day> loadDays() {
        return dayFacade.findAll();
    }

    public void confirmCompany() {
        System.out.println("hahiya company selected : " + company);
    }

    public void confirmIndividual() {
        System.out.println("hahowa individual selected : " + individual);
    }

    public List<Ville> loadVilles() {
        return villeFacade.findAll();
    }

    public void checkChoice() {
        System.out.println("hahowa id li khtar : " + demandeCleaning.getDemandeService().getWorkerType().getId());
        System.out.println("\nhahowa nom li khtar : " + demandeCleaning.getDemandeService().getWorkerType().getName());
        
        RequestContext context = RequestContext.getCurrentInstance();
        if (demandeCleaning.getDemandeService().getWorkerType().getId() == 2) {
            companies = workerJobFacade.findWorkerByServiceAndType("Nettoyage Maison", demandeCleaning.getDemandeService().getWorkerType().getId());
            context.execute("PF('SocieteDialog').show()");
        } else if (demandeCleaning.getDemandeService().getWorkerType().getId() == 1) {
            individuals = workerJobFacade.findWorkerByServiceAndType("Nettoyage Maison", demandeCleaning.getDemandeService().getWorkerType().getId());

            context.execute("PF('IndividualDialog').show()");
        }
    }

    public void doAction() {
        loadSectors();
    }

    public void checkEmail() {

        List<Client> clients = clientFacade.checkEmail(demandeCleaning.getDemandeService().getClient().getEmail());

        if (clients.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Adresse email non enregister, veuillez remplir les champs !"));

        } else {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('ConnexionDialog').show()");
            loadedClient = clients.get(0);
        }
    }

    public void checkPassword() {
        if (demandeCleaning.getDemandeService().getClient().getPassword().equals(loadedClient.getPassword())) {
            demandeCleaning.getDemandeService().setClient(loadedClient);
            ville = loadedClient.getSecteur().getVille();
            doAction();
            demandeCleaning.getDemandeService().getClient().setSecteur(loadedClient.getSecteur());
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('ConnexionDialog').hide()");

        } else {

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('ConnexionDialog').jq.effect('shake', {times:5}, 100)");

        }
    }

    public void loadSectors() {
        secteurs = secteurFacade.findByVille(ville);
    }

    public void checkOnce() {
        if (cleaningOnce == true) {
            cleaningMnayTimes = false;
        }
        if (cleaningOnce == false) {
            cleaningOnce = true;
        }
    }

    public void checkMany() {
        if (cleaningMnayTimes == true) {
            cleaningOnce = false;
        }
        if (cleaningMnayTimes == false) {
            cleaningMnayTimes = true;
        }
    }

    public void addNetoyageDate() {
        Date newDate = (Date) date.clone();
        dates.add(newDate);

    }

    public void removeDate(Date selectedDate) {
        dates.remove(selectedDate);
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

    public Ville getVille() {
        if (ville == null) {
            ville = new Ville();
        }
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Secteur getSecteur() {
        if (secteur == null) {
            secteur = new Secteur();
        }
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public List<Secteur> getSecteurs() {
        if (secteurs == null) {
            secteurs = new ArrayList<>();
        }
        return secteurs;
    }

    public void setSecteurs(List<Secteur> secteurs) {
        this.secteurs = secteurs;
    }

    public Date getDateOnce() {
        return dateOnce;
    }

    public void setDateOnce(Date dateOnce) {
        this.dateOnce = dateOnce;
    }

    public boolean isCleaningMnayTimes() {
        return cleaningMnayTimes;
    }

    public void setCleaningMnayTimes(boolean cleaningMnayTimes) {
        this.cleaningMnayTimes = cleaningMnayTimes;
    }

    public List<Date> getDates() {
        if (dates == null) {
            dates = new ArrayList<>();
        }
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public Date getDate() {

        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCleaningOnce() {
        return cleaningOnce;
    }

    public void setCleaningOnce(boolean cleaningOnce) {
        this.cleaningOnce = cleaningOnce;
    }

    public DemandeCleaning getSelected() {
        return selected;
    }

    public void setSelected(DemandeCleaning selected) {
        this.selected = selected;
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

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeCleaningFacade getFacade() {
        return ejbFacade;
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

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
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

    public WorkerType getWorkerType() {
        if (workerType == null) {
            workerType = new WorkerType();
        }
        return workerType;
    }

    public void setWorkerType(WorkerType workerType) {

        this.workerType = workerType;
    }

    public DemandeCleaning prepareCreate() {
        selected = new DemandeCleaning();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeCleaningCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeCleaningUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeCleaningDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandeCleaning> getItems() {
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

    public DemandeCleaning getDemandeCleaning(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandeCleaning> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandeCleaning> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public PlanningItem clone(PlanningItem myPlanning, List<PlanningItem> list) {

        PlanningItem item = new PlanningItem();
        int id = 0;
        try {
            id = list.get(list.size() - 1).getId().intValue() + 1;
        } catch (Exception e) {
        }

        item.setId(new Long(id));
        item.setDay(myPlanning.getDay());
        item.setNumeroJour(myPlanning.getNumeroJour());
        item.setRepetition(myPlanning.getRepetition());
        item.setTiming(myPlanning.getTiming());

        return item;
    }

    @FacesConverter(forClass = DemandeCleaning.class)
    public static class DemandeCleaningControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeCleaningController controller = (DemandeCleaningController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeCleaningController");
            return controller.getDemandeCleaning(getKey(value));
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
            if (object instanceof DemandeCleaning) {
                DemandeCleaning o = (DemandeCleaning) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandeCleaning.class.getName()});
                return null;
            }
        }

    }

}
