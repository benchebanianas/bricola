package controller;

import bean.Carburant;
import bean.Client;
import bean.Couleur;
import bean.Day;
import bean.DemandeBabySitting;
import bean.DemandeCleaning;
import bean.DemandeEvent;
import bean.DemandeFormationPersonnel;
import bean.DemandeGardening;
import bean.DemandeHandyMan;
import bean.DemandeMoving;
import bean.DemandePainting;
import bean.DemandePestControl;
import bean.DemandePhotographie;
import bean.DemandeService;
import bean.MenuFormulaire;
import bean.DemandeVoiture;
import bean.DemandeVoitureItem;
import bean.Faq;
import bean.Filiere;
import bean.HandymanType;
import bean.Manager;
import bean.Matiere;
import bean.NiveauScolaire;
import bean.PestControlType;
import bean.PlanningItem;
import bean.Secteur;
import bean.Service;
import bean.ServicePricing;
import bean.Timing;
import bean.TypeAction;
import bean.Ville;
import bean.Voiture;
import bean.VoitureCarburantCouleur;
import bean.VoitureMarque;
import bean.VoitureModele;
import bean.Worker;
import bean.WorkerType;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.DemandeServiceFacade;

import java.io.Serializable;
import java.math.BigDecimal;
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
import service.DemandeServiceConfirmationDetailFacade;
import org.primefaces.context.RequestContext;
import service.CarburantFacade;
import service.CouleurFacade;
import service.DemandeEventFacade;
import service.DemandeFormationPersonnelFacade;
import service.DemandeHandyManFacade;
import service.DemandeMovingFacade;
import service.DemandePestControlFacade;
import service.FaqFacade;
import service.FiliereFacade;
import service.MatiereFacade;
import service.PackagingFacade;
import service.VoitureFacade;

@Named("demandeServiceController")
@SessionScoped
public class DemandeServiceController implements Serializable {

    @EJB
    private service.DemandeServiceFacade ejbFacade;
    @EJB
    private service.ServicePricingFacade servicePricingFacade;
    @EJB
    private service.VoitureCarburantCouleurFacade voitureCarburantCouleurFacade;
    @EJB
    private DemandeServiceConfirmationDetailFacade detailFacade;
    @EJB
    private service.DemandeCleaningFacade demandeServiceCleaningFacade;
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
    @EJB
    private service.MenuFormulaireFacade menuFormulaireFacade;
    @EJB
    private service.TypeActionFacade typeActionFacade;
    @EJB
    private service.DemandeVoitureFacade demandeVoitureFacade;
    @EJB
    private service.VoitureCarburantCouleurFacade modeleFacade;
    @EJB
    private FiliereFacade filiereFacade;
    @EJB
    private MatiereFacade matiereFacade;
    @EJB
    private service.ProfJobFacade profJobFacade;
    @EJB
    private service.DemandePhotographieFacade demandePhotographieFacade;
    @EJB
    private PackagingFacade packagingFacade;
    @EJB
    private DemandeMovingFacade demandeMovingFacade;
    @EJB
    private DemandeEventFacade demandeEventFacade;
    @EJB
    private DemandeFormationPersonnelFacade demandeFormationPersonnelFacade;
    @EJB
    private DemandePestControlFacade demandePestControlFacade;
    @EJB
    private DemandeHandyManFacade demandeHandyManFacade;
    @EJB
    private VoitureFacade voitureFacade;
    @EJB
    private CouleurFacade couleurFacade;
    @EJB
    private CarburantFacade carburantFacade;
    @EJB
    private FaqFacade faqFacade;

    private List<Worker> companies;
    private List<Worker> individuals;
    private List<Secteur> secteurs;
    private List<String> eventCuisines;
    private List<String> eventSupplements;
    private List<Couleur> couleurs;
    private List<Carburant> carburants;
    private List<VoitureCarburantCouleur> voitures;
    private List<Voiture> voituresRecherches;

    private MenuFormulaire menuFormulaire;

    private WorkerType workerType;
    private Service currentService;
    private Worker company;
    private Worker individual;
    private PlanningItem planningItem;
    private Client loadedClient;
    private Ville ville;
    private VoitureMarque voitureMarque;
    private List<VoitureModele> modeles;
    private NiveauScolaire niveauScolaire;
    private List<Filiere> filieres;
    private List<Matiere> matieres;
    private List<Faq> faqs;
    private Filiere filiere;
    private Matiere matiere;
    private Boolean fromDemandeDetail = false;
    private List<PestControlType> pestControltypes;
    private PestControlType pestControlType;
    private List<ServicePricing> servicePricings;
    private HandymanType handymanType;
    private Couleur couleur;
    private Carburant carburant;
    private VoitureModele modele;
    private VoitureCarburantCouleur voiture;
    private DemandeVoitureItem demandeVoitureItem;

    private boolean oneTime = true;
    private boolean multipleTimes;
    private List<DemandeService> items = null;
    private DemandeService selected;
    private Object demande;
    private DemandeBabySitting demandeBabySitting;
    private DemandeEvent demandeEvent;
    private DemandeFormationPersonnel demandeFormationPersonnel;
    private DemandeGardening demandeGardening;
    private DemandeHandyMan demandeHandyMan;
    private DemandeMoving demandeMoving;
    private DemandePainting demandePainting;
    private DemandePestControl demandePestControl;
    private DemandePhotographie demandePhotographie;
    private DemandeVoiture demandeVoiture;
    private DemandeService demandeService;
    private DemandeCleaning demandeCleaning;

    private List<PlanningItem> planningItems;
    private List<DemandeVoitureItem> demandeVoitureItems;

    private String imageName;
    //search
    private Long secteur;
    private String workerNom;
    private Long service;
    private Date dateDemande;
    private BigDecimal prixMin;
    private BigDecimal prixMax;
    private Integer confirSuprr;
    
    private ServicePricing servicePricing;

    public void recherche() {
        items = ejbFacade.findByCriteria(secteur, workerNom, service, dateDemande, prixMin, prixMax, confirSuprr);
    }
    private String nomService;

    public void voirPlus(DemandeService demandeService) {
        setSelected(demandeService);
        String view = demandeService.getTypeDemande().getId();
        demande = ejbFacade.findDemande(getSelected());
        switch (view) {
            case "DemandeBabySitting":
                demandeBabySitting = (DemandeBabySitting) demande;
                break;
            case "DemandeCleaning":
                demandeCleaning = (DemandeCleaning) demande;
                break;
            case "DemandeEvent":
                demandeEvent = (DemandeEvent) demande;
                break;
            case "DemandeFormationPersonnel":
                demandeFormationPersonnel = (DemandeFormationPersonnel) demande;
                break;
            case "DemandeGardening":
                demandeGardening = (DemandeGardening) demande;
                break;
            case "DemandeHandyMan":
                demandeHandyMan = (DemandeHandyMan) demande;
                break;
            case "DemandeMoving":
                demandeMoving = (DemandeMoving) demande;
                break;
            case "DemandePainting":
                demandePainting = (DemandePainting) demande;
                break;
            case "DemandePestControl":
                demandePestControl = (DemandePestControl) demande;
                break;
            case "DemandePhotographie":
                demandePhotographie = (DemandePhotographie) demande;
                break;
            case "DemandeVoiture":
                demandeVoiture = (DemandeVoiture) demande;
                break;
            default:
                break;
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("" + view + "Form");
        context.execute("PF('" + view + "ViewDialog').show();");
        if (fromDemandeDetail) {
            items = null;
            fromDemandeDetail = false;
        }
    }

    public void addDemandeVoitureItem() {

        DemandeVoitureItem item = clone(demandeVoitureItem);
        demandeVoitureItems.add(item);
    }
    
    public DemandeVoitureItem clone(DemandeVoitureItem myDemandeVoitureItem) {

        DemandeVoitureItem item = new DemandeVoitureItem();

        int id = 0;
        try {
            id = demandeVoitureItems.get(demandeVoitureItems.size() - 1).getId().intValue() + 1;
        } catch (Exception e) {
        }

        item.setId(new Long(id));
        item.setCarburant(myDemandeVoitureItem.getCarburant());
        item.setModele(myDemandeVoitureItem.getModele());
        item.setDateDebut(myDemandeVoitureItem.getDateDebut());
        item.setDateFin(myDemandeVoitureItem.getDateFin());
        item.setQuantite(myDemandeVoitureItem.getQuantite());
        System.out.println("hani item : " + item);
        return item;
    }
    
    public BigDecimal findPricing(){
        
        servicePricing = servicePricingFacade.findByService(currentService);
        return servicePricing.getPrix();
    }
    
    public BigDecimal pricingTotaleNettoyageMaison(){
        return servicePricing.getPrix().multiply(demandeCleaning.getNbrCleaner().multiply(demandeCleaning.getNbrHeures()));
    }
    
    public BigDecimal pricingTotaleHandyMan(){
        return servicePricing.getPrix().multiply(demandeHandyMan.getNbrHandyMan().multiply(demandeHandyMan.getNbrHeures()));
    }

    public void findCarsByCreteria() {

        System.out.println("f wst l methode");

        voituresRecherches = voitureFacade.findByCriteria(modele, null, carburant);

    }

    public void loadModeles() {
        modeles = modeleFacade.findByMarque(voitureMarque);
    }

//    public void findCars() {
//
//        voitures = voitureFacade.findByModel(modele);
//    }
    public void findCarburantsByColor() {

        System.out.println("hahowa couleur : " + couleur);

        if (couleur != null) {

            carburants = carburantFacade.findCarburantsByColor(couleur, modele);

        } else {
            carburants = carburantFacade.findByModele(modele);
        }

    }

    public void findColorsByCarburants() {

        System.out.println("hahowa carburant : " + carburant);

        if (carburant != null) {

            couleurs = couleurFacade.findColorByCarburant(carburant, modele);
        } else {
            couleurs = couleurFacade.findByModele(modele);
        }

    }

    public void checkColorAndFuel() {

        //couleurs = couleurFacade.findByModele(modele);
        carburants = carburantFacade.findByModele(modele);

    }

    public String redirectToHandyman() {
        return "/demandeService/handyman/handyManPack.xhtml?faces-redirect=true";
    }

    public String changeItems(DemandeService demandeService) {
        getItems().clear();
        getItems().add(demandeService);
        fromDemandeDetail = true;
        return "/manager/Demande?faces-redirect=true";
    }

    public void retour() {
        setSelected(null);
        SessionUtil.remove("demandeService");
    }

    public void Action(DemandeService demandeService, Long idType) {
        if (demandeService != null) {
            setSelected(demandeService);
        }
        Manager manager = (Manager) SessionUtil.getAttribute("connectedManager");
        if (idType == 1) {
            if (getSelected().getDateConfirmation() == null) {
                System.out.println("bsmlah");
                selected.setDateConfirmation(new Date());
                selected.setDateSuppression(null);
            }
        } else if (idType == 2) {

            if (getSelected().getDateSuppression() == null) {
                System.out.println("bsmlah");
                selected.setDateSuppression(new Date());
                selected.setDateConfirmation(null);
            }
        }
        selected.setManagerConfirmation(manager);
        ejbFacade.edit(selected);
        TypeAction action = typeActionFacade.find(idType);
        detailFacade.save(manager, action, selected);
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
            if (currentService.getId() == 1) {//cleaning
                demandeServiceCleaningFacade.saveDemandeCleaning(demandeCleaning, demandeService);
            } else if (currentService.getId() == 19) {//photographie
                demandePhotographieFacade.saveDemandePhotographie(demandePhotographie, demandeService);
            } else if (currentService.getId() == 21) {//locationVoiture
                demandeVoitureFacade.saveDemandeVoiture(demandeVoiture, demandeService, demandeVoitureItems);
            } else if (currentService.getId() == 5) {//demenagement
                demandeMovingFacade.saveDemandeMoving(demandeMoving, demandeService);
            } else if (currentService.getId() == 17) {//traiteur
                demandeEventFacade.saveDemandeEvent(demandeEvent, eventCuisines, eventSupplements, demandeService);
            } else if (currentService.getId() == 22) {//formationPerso sway3
                demandeFormationPersonnelFacade.saveDemandeFormationPersonnel(demandeFormationPersonnel, demandeService);
            } else if (currentService.getId() == 14) {//deratisation pestControl
                demandePestControlFacade.saveDemandePestControl(demandePestControl, demandeService);
//                demandeFormationPersonnelFacade.saveDemandeFormationPersonnel();
            } else if (currentService.getId() == 8 || currentService.getId() == 10 || currentService.getId() == 11 || currentService.getId() == 12) {//peinture, electricite, plomberie, menuiserie
                demandeHandyManFacade.saveDemandeHandyMan(demandeHandyMan, demandeService);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Demande enregistrer avec succes !"));
            resetObjects();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Erreur lors du sauvgarde de la demandeService !"));
        }
    }

    public void resetObjects() {

        demandeService = new DemandeService();
        demandeCleaning = new DemandeCleaning();
        demandeHandyMan = new DemandeHandyMan();
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
        menuFormulaire = new MenuFormulaire();
        System.out.println(nomService);
        currentService = serviceFacade.findServiceByName(nomService);
        menuFormulaire = menuFormulaireFacade.findMenuByService(nomService);
        System.out.println(currentService.getNom());
        System.out.println(menuFormulaire.getService().getNom());
    }

    public String initService2(String nomService) {
        String link;

        System.out.println(nomService);

        demandeService = new DemandeService();
        menuFormulaire = new MenuFormulaire();

        currentService = serviceFacade.findServiceByName(nomService);
        faqs = faqFacade.findByService(currentService);
        menuFormulaire = menuFormulaireFacade.findMenuByService(nomService);

        System.out.println("service name " + currentService.getNom());
        System.out.println("type demande " + menuFormulaire.getService().getNom());
        System.out.println("" + menuFormulaire.isCompanyTab() + "," + menuFormulaire.isDetailsTab() + "," + menuFormulaire.isInfoTab() + ",");

        link = "/demandeService/Demande.xhtml?faces-redirect=true";
        return link;
    }

    public String initService3() {
        return initService2(nomService);
    }

    public String bookUnitePestControl(PestControlType type) {
        setPestControlType(type);
        System.out.println("ha type" + type.getNom());
        return "/demandeService/deratisation/PestControlUnit.xhtml";
    }

    public String bookUniteHandyman(HandymanType type) {
        setHandymanType(type);
        System.out.println("ha type" + type.getNom());
        return "/demandeService/handyman/handyManUnit.xhtml";
    }

    public String initServicePestControl(ServicePricing servicePricing) {
        String link = initService2("deratisation");
        getDemandePestControl().setTypeOfPestControl(pestControlType);
        getDemandeService().setServicePricing(servicePricing);

        return link;
    }

    public List<Timing> loadTimings() {
        return timingFacade.findAll();
    }

    public List<Day> loadDays() {
        return dayFacade.findAll();
    }

    public void doActionNiveau() {
        filieres = filiereFacade.findByNiveauScolaire(niveauScolaire);
    }

    public void doActionFiliere() {
        matieres = matiereFacade.findByFiliere(demandeFormationPersonnel.getFiliere());
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
    
    public void deleteDemandeVoitureItem(DemandeVoitureItem item) {

        demandeVoitureItems.remove(item);
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

    public String redirectToIndex() {
//        return "../index.xhtml?faces-redirect=true";
        return "/index?faces-redirect=true";
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

    public PestControlType getPestControlType() {
        if (pestControlType == null) {
            pestControlType = new PestControlType();
        }
        return pestControlType;
    }

    public void setPestControlType(PestControlType pestControlType) {
        this.pestControlType = pestControlType;
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

    public Object getDemande() {
        return demande;
    }

    public void setDemande(Object demande) {
        this.demande = demande;
    }

    public List<PlanningItem> getPlanningItems() {
        return planningItems;
    }

    public void setPlanningItems(List<PlanningItem> planningItems) {
        this.planningItems = planningItems;
    }

    public DemandeService getSelected() {
        if (selected == null) {
            selected = new DemandeService();
        }
        return selected;
    }

    public void setSelected(DemandeService selected) {
        this.selected = selected;
    }

    public DemandeBabySitting getDemandeBabySitting() {
        return demandeBabySitting;
    }

    public void setDemandeBabySitting(DemandeBabySitting demandeBabySitting) {
        this.demandeBabySitting = demandeBabySitting;
    }

    public DemandeFormationPersonnel getDemandeFormationPersonnel() {
        if (demandeFormationPersonnel == null) {
            demandeFormationPersonnel = new DemandeFormationPersonnel();
        }
        return demandeFormationPersonnel;
    }

    public void setDemandeFormationPersonnel(DemandeFormationPersonnel demandeFormationPersonnel) {
        this.demandeFormationPersonnel = demandeFormationPersonnel;
    }

    public DemandeGardening getDemandeGardening() {
        return demandeGardening;
    }

    public void setDemandeGardening(DemandeGardening demandeGardening) {
        this.demandeGardening = demandeGardening;
    }

    public DemandeHandyMan getDemandeHandyMan() {
        if (demandeHandyMan == null) {
            demandeHandyMan = new DemandeHandyMan();
        }
        return demandeHandyMan;
    }

    public void setDemandeHandyMan(DemandeHandyMan demandeHandyMan) {
        this.demandeHandyMan = demandeHandyMan;
    }

    public DemandeEvent getDemandeEvent() {
        if (demandeEvent == null) {
            demandeEvent = new DemandeEvent();
        }
        return demandeEvent;
    }

    public void setDemandeEvent(DemandeEvent demandeEvent) {
        this.demandeEvent = demandeEvent;
    }

    public DemandeMoving getDemandeMoving() {
        if (demandeMoving == null) {
            demandeMoving = new DemandeMoving();
        }
        return demandeMoving;
    }

    public void setDemandeMoving(DemandeMoving demandeMoving) {
        this.demandeMoving = demandeMoving;
    }

    public DemandePestControl getDemandePestControl() {
        if (demandePestControl == null) {
            demandePestControl = new DemandePestControl();
        }
        return demandePestControl;
    }

    public void setDemandePestControl(DemandePestControl demandePestControl) {
        this.demandePestControl = demandePestControl;
    }

    public DemandePhotographie getDemandePhotographie() {
        if (demandePhotographie == null) {
            demandePhotographie = new DemandePhotographie();
        }
        return demandePhotographie;
    }

    public void setDemandePhotographie(DemandePhotographie demandePhotographie) {
        this.demandePhotographie = demandePhotographie;
    }

    public DemandeVoiture getDemandeVoiture() {
        if (demandeVoiture == null) {
            demandeVoiture = new DemandeVoiture();
        }
        return demandeVoiture;
    }

    public void setDemandeVoiture(DemandeVoiture demandeVoiture) {
        this.demandeVoiture = demandeVoiture;
    }

    public DemandePainting getDemandePainting() {
        return demandePainting;
    }

    public void setDemandePainting(DemandePainting demandePainting) {
        this.demandePainting = demandePainting;
    }

    public MenuFormulaire getMenuFormulaire() {
        return menuFormulaire;
    }

    public void setMenuFormulaire(MenuFormulaire menuFormulaire) {
        this.menuFormulaire = menuFormulaire;
    }

    public Long getSecteur() {
        return secteur;
    }

    public void setSecteur(Long secteur) {
        this.secteur = secteur;
    }

    public String getWorkerNom() {
        return workerNom;
    }

    public void setWorkerNom(String workerNom) {
        this.workerNom = workerNom;
    }

    public Long getService() {
        return service;
    }

    public void setService(Long service) {
        this.service = service;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public BigDecimal getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(BigDecimal prixMin) {
        this.prixMin = prixMin;
    }

    public BigDecimal getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(BigDecimal prixMax) {
        this.prixMax = prixMax;
    }

    public Integer getConfirSuprr() {
        return confirSuprr;
    }

    public void setConfirSuprr(Integer confirSuprr) {
        this.confirSuprr = confirSuprr;
    }

    public List<VoitureCarburantCouleur> getVoitures() {
        if (voitures == null) {
            voitures = voitureCarburantCouleurFacade.findAll();
        }
        return voitures;
    }

    public void setVoitures(List<VoitureCarburantCouleur> voitures) {
        this.voitures = voitures;
    }

    public VoitureModele getModele() {
        if (modele == null) {
            modele = new VoitureModele();
        }
        return modele;
    }

    public void setModele(VoitureModele modele) {
        this.modele = modele;
    }

    public List<Voiture> getVoituresRecherches() {
        if (voituresRecherches == null) {
            voituresRecherches = new ArrayList<>();
        }
        return voituresRecherches;
    }

    public void setVoituresRecherches(List<Voiture> voituresRecherches) {
        this.voituresRecherches = voituresRecherches;
    }

    public List<Faq> getFaqs() {
        if (faqs == null) {
            faqs = new ArrayList<>();
        }
        return faqs;
    }

    public void setFaqs(List<Faq> faqs) {
        this.faqs = faqs;
    }

    public DemandeVoitureItem getDemandeVoitureItem() {
        if (demandeVoitureItem == null) {
            demandeVoitureItem = new DemandeVoitureItem();
        }
        return demandeVoitureItem;
    }

    public void setDemandeVoitureItem(DemandeVoitureItem demandeVoitureItem) {
        this.demandeVoitureItem = demandeVoitureItem;
    }

    public List<DemandeVoitureItem> getDemandeVoitureItems() {
        if(demandeVoitureItems == null){
            demandeVoitureItems = new ArrayList<>();
        }
        return demandeVoitureItems;
    }

    public void setDemandeVoitureItems(List<DemandeVoitureItem> demandeVoitureItems) {
        this.demandeVoitureItems = demandeVoitureItems;
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

//    public void loadModeles() {
//        modeles = modeleFacade.searchByMarque(voitureMarque);
//    }
    public VoitureMarque getVoitureMarque() {
        if (voitureMarque == null) {
            voitureMarque = new VoitureMarque();
        }
        return voitureMarque;
    }

    public void setVoitureMarque(VoitureMarque voitureMarque) {
        this.voitureMarque = voitureMarque;
    }

    public List<VoitureModele> getModeles() {
        if (modeles == null) {
            modeles = new ArrayList();
        }
        return modeles;
    }

    public void setModeles(List<VoitureModele> modeles) {
        this.modeles = modeles;
    }

    public List<DemandeService> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void setItems(List<DemandeService> items) {
        this.items = items;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public NiveauScolaire getNiveauScolaire() {
        if (niveauScolaire == null) {
            niveauScolaire = new NiveauScolaire();
        }
        return niveauScolaire;
    }

    public void setNiveauScolaire(NiveauScolaire niveauScolaire) {
        this.niveauScolaire = niveauScolaire;
    }

    public List<Filiere> getFilieres() {
        if (filieres == null) {
            filieres = new ArrayList();
        }
        return filieres;
    }

    public void setFilieres(List<Filiere> filieres) {
        this.filieres = filieres;
    }

    public List<Matiere> getMatieres() {
        if (matieres == null) {
            matieres = new ArrayList();
        }
        return matieres;
    }

    public void setMatieres(List<Matiere> matieres) {
        this.matieres = matieres;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Matiere getMatiere() {
        if (matiere == null) {
            matiere = new Matiere();
        }
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public List<String> getEventCuisines() {
        if (eventCuisines == null) {
            eventCuisines = new ArrayList();
        }
        return eventCuisines;
    }

    public void setEventCuisines(List<String> eventCuisines) {
        this.eventCuisines = eventCuisines;
    }

    public List<String> getEventSupplements() {
        if (eventSupplements == null) {
            eventSupplements = new ArrayList();
        }
        return eventSupplements;
    }

    public HandymanType getHandymanType() {
        return handymanType;
    }

    public void setHandymanType(HandymanType handymanType) {
        this.handymanType = handymanType;
    }

    public void setEventSupplements(List<String> eventSupplements) {
        this.eventSupplements = eventSupplements;
    }

    public VoitureCarburantCouleur getVoiture() {
        if (voiture == null) {
            voiture = new VoitureCarburantCouleur();
        }
        return voiture;
    }

    public void setVoiture(VoitureCarburantCouleur voiture) {
        this.voiture = voiture;
    }

    public ServicePricing getServicePricing() {
        if(servicePricing == null){
            servicePricing = new ServicePricing();
        }
        return servicePricing;
    }

    public void setServicePricing(ServicePricing servicePricing) {
        this.servicePricing = servicePricing;
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

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public List<Couleur> getCouleurs() {
        if (couleurs == null) {
            couleurs = new ArrayList<>();
        }
        return couleurs;
    }

    public void setCouleurs(List<Couleur> couleurs) {
        this.couleurs = couleurs;
    }

    public List<Carburant> getCarburants() {
        if (carburants == null) {
            carburants = new ArrayList<>();
        }
        return carburants;
    }

    public void setCarburants(List<Carburant> carburants) {
        this.carburants = carburants;
    }

    public Couleur getCouleur() {
        if (couleur == null) {
            couleur = new Couleur();
        }
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Carburant getCarburant() {
        if (carburant == null) {
            carburant = new Carburant();
        }
        return carburant;
    }

    public void setCarburant(Carburant carburant) {
        this.carburant = carburant;
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
