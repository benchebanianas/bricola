package controller;

import bean.Carburant;
import bean.Client;
import bean.DemandeCleaning;
import bean.DemandeFormationPersonnel;
import bean.DemandeHandyMan;
import bean.DemandeService;
import bean.DemandeServiceConfirmationDetail;
import bean.DemandeVoiture;
import bean.DemandeVoitureItem;
import bean.Device;
import bean.Manager;
import bean.Review;
import bean.Secteur;
import bean.Service;
import bean.StatistiqueGenerale;
import bean.Ville;
import bean.VoitureModele;
import bean.Worker;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.MathUtil;
import controller.util.SessionUtil;
import java.io.IOException;
import service.ManagerFacade;

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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import service.DemandeServiceConfirmationDetailFacade;
import service.DeviceFacade;

@Named("managerController")
@SessionScoped
public class ManagerController implements Serializable {

    @EJB
    private service.ManagerFacade ejbFacade;
    @EJB
    private service.DemandeFormationPersonnelFacade demandeFormationPersonnelFacade;
    @EJB
    private service.DemandeHandyManFacade demandeHandyManFacade;
    @EJB
    private service.DemandeCleaningFacade demandeCleaningFacade;
    @EJB
    private service.DemandeVoitureFacade demandeVoitureFacade;
    @EJB
    private service.DemandeVoitureItemFacade demandeVoitureItemFacade;
    @EJB
    private service.ClientFacade clientFacade;
    @EJB
    private service.ReviewFacade reviewFacade;
    @EJB
    private service.ServiceFacade serviceFacade;
    @EJB
    private service.WorkerFacade workerFacade;
    @EJB
    private service.WorkerJobFacade workerJobFacade;
    @EJB
    private service.VilleFacade villeFacade;
    @EJB
    private service.SecteurFacade secteurFacade;
    @EJB
    private service.DemandeServiceFacade demandeServiceFacade;
    @EJB
    private service.ServiceVilleFacade serviceVilleFacade;
    @EJB
    private DeviceFacade deviceFacade;
    @EJB
    private DemandeServiceConfirmationDetailFacade confirmationDetailFacade;
    private List<Manager> items = null;
    private List<Worker> workers;
    private Manager selected;
    private Ville ville;
    private Client client;
    private Client clientRecherche;
    private Secteur secteur;
    private Service service;
    private Service serviceRecherche;
    private Worker workerRecherche;
    private DemandeService selectedDemandeService;
    private List<Service> services;
    private List<Secteur> secteurs;
    
    private List<DemandeServiceConfirmationDetail> managerItems;
    private Date dernierConex;
    private Date dernierConfirmation;
    private String action;
    private DemandeServiceConfirmationDetail demandeServiceConfirmationDetail;
    private String ancienPassword;
    private String nvPassword;
    private String nvPassword1;

    private List<Ville> statVilles;
    private List<Worker> statWorkers;
    private List<Secteur> statSecteurs;
    private List<DemandeService> demandeServices;
    private List<DemandeVoiture> demandeVoitures;
    private List<DemandeService> demandesRecentes;
    private List<DemandeVoitureItem> demandeVoitureItems;
    private LineChartModel lineCharModel;
    private LineChartModel lineCharModel2;
    private BarChartModel barCharModel;
    private Worker workerReview;
    private List<Review> workerReviews;
    private List<Worker> workerList;
    private List<Worker> locationVoitureWorkerList;
    private Worker workerSelected;
    private Worker locationVoitureWorkerSelected;
    private DemandeVoitureItem demandeVoitureItem;
    private DemandeVoiture demandeVoiture;
    private DemandeVoitureItem demandeVoitureItemRecherche;

    private int equipement;
    private int etatRecherche;
    private boolean renderLocationVoitureItems = false;
    private BigDecimal max;

    private Date dateMin;
    private Date dateMax;
    private DemandeService demandeServiceSelected;
    private StatistiqueGenerale statistiqueGenerale;

    private VoitureModele modeleRecherche;
    private Carburant carburantRecherche;
    private Worker employeRecherche;
    private Date dateDebutMin;
    private Date dateFinMin;
    private Date dateDebutMax;
    private Date dateFinMax;

    private DemandeCleaning detailDemandeCleaning;
    private DemandeHandyMan detailDemandeHandyMan;
    private DemandeFormationPersonnel detailDemandeFormationPersonnel;
    
    public int countStars(Worker worker) {

        double value = workerFacade.showRating(worker);

        return (int) Math.round(value);

    }

    public boolean checkWorkerForDemandeVoitureItems(DemandeVoiture demandeVoiture) {
        List<DemandeVoitureItem> items = demandeVoitureItemFacade.findByDemandeVoiture(demandeVoiture);
        for (DemandeVoitureItem item : items) {
            if (item.getWorker().getEmail() == null) {
                return false;
            }
        }
        if(items.isEmpty()){
            return false;
        }
        return true;
    }

    public void findDemandeVoitureItems(DemandeVoiture demandeVoiture) {
        this.demandeVoiture = demandeVoiture;
        demandeVoitureItems = demandeVoitureItemFacade.findByDemandeVoiture(demandeVoiture);
        renderLocationVoitureItems = true;
    }

    public void accepter(Worker worker) {
        worker.setAccepted(true);
        workerFacade.edit(worker);
        workers = workerFacade.findAll();
    }

    public void confirmWorkerSelected() {
        demandeServiceSelected.setWorker(workerSelected);
        demandeServiceFacade.edit(demandeServiceSelected);
        demandeServices = demandeServiceFacade.findAll();
    }

    public void confirmDemandeVoitureItemWorker() {
        demandeVoitureItem.setWorker(locationVoitureWorkerSelected);
        demandeVoitureItemFacade.edit(demandeVoitureItem);
        demandeVoitureItems = demandeVoitureItemFacade.findAll();
    }

    public void prepareWorkerSelected(DemandeService demandeService) {
        workerList = new ArrayList<>();
        demandeServiceSelected = demandeService;
        List<Worker> morale = workerJobFacade.findWorkerByServiceAndType(demandeService.getService().getNom(), new Long(1));
        List<Worker> physique = workerJobFacade.findWorkerByServiceAndType(demandeService.getService().getNom(), new Long(2));
        for (int i = 0; i < morale.size(); i++) {
            workerList.add(morale.get(i));
        }
        for (int i = 0; i < physique.size(); i++) {
            workerList.add(physique.get(i));
        }

    }

    public void prepareLocationVoitureWorker(DemandeVoitureItem demandeVoitureItem) {
        this.demandeVoitureItem = demandeVoitureItem;
        demandeVoitureItemRecherche = demandeVoitureItem;
        locationVoitureWorkerList = new ArrayList<>();
        List<Worker> morale = workerJobFacade.findWorkerByServiceAndType(demandeVoitureItem.getDemandeVoiture().getDemandeService().getService().getNom(), new Long(1));
        List<Worker> physique = workerJobFacade.findWorkerByServiceAndType(demandeVoitureItem.getDemandeVoiture().getDemandeService().getService().getNom(), new Long(2));
        for (int i = 0; i < morale.size(); i++) {
            locationVoitureWorkerList.add(morale.get(i));
        }
        for (int i = 0; i < physique.size(); i++) {
            locationVoitureWorkerList.add(physique.get(i));
        }

    }

    public void prepareReviews(Worker worker) {

        workerReview = worker;
        workerReviews = reviewFacade.findReviewByWorker(workerReview);

    }

    public void validerDemande(DemandeService demandeService) {

        System.out.println("hahiya demande service : " + demandeService);

        demandeService.setDateConfirmation(new Date());
        demandeService.setManagerConfirmation(selected);
        demandeServiceFacade.edit(demandeService);

    }

    public List<Client> clientsRecherche() {
        return clientFacade.findAll();
    }

    public List<Service> servicesRecherche() {
        return serviceFacade.findAll();
    }

    public List<Worker> workersRecherche() {
        return workerFacade.findAll();
    }

    public void rechercheDemande() {
        demandeServices = demandeServiceFacade.rechercher(workerRecherche, clientRecherche, serviceRecherche, etatRecherche, dateMin, dateMax);
    }

    public void rechercheDemandeVoiture() {
        demandeVoitures = demandeVoitureFacade.rechercher(clientRecherche, etatRecherche, dateMin, dateMax);
    }

    public void findWorkerByCriteria() {
        locationVoitureWorkerList = workerFacade.rechercher(demandeVoitureItemRecherche);
    }

    public void genererPdf(DemandeService demandeService) throws JRException, IOException {

        checkServiceForPDF(demandeService);
        FacesContext.getCurrentInstance().getResponseComplete();
    }
    
    public void checkServiceForPDF(DemandeService demandeService) throws JRException, IOException{
        if(demandeService.getService().getId() == 1){
            demandeCleaningFacade.generatePdf(demandeService);
        }
        if(demandeService.getService().getId() == 8 || demandeService.getService().getId() == 10 || demandeService.getService().getId() == 11 || demandeService.getService().getId() == 12){
            demandeHandyManFacade.generatePdf(demandeService);
        }
        if(demandeService.getService().getId() == 22){
            demandeFormationPersonnelFacade.generatePdf(demandeService);
        }
    }

    public void genererPdfDemandeVoiture(DemandeVoiture demandeVoiture) throws JRException, IOException {
        this.demandeVoiture = demandeVoiture;
        System.out.println("hahowa this.demnade voiture : " + this.demandeVoiture);
        System.out.println("hahowa demnade voiture : " + demandeVoiture);
        demandeVoitureFacade.generatePdf(demandeVoiture);
        FacesContext.getCurrentInstance().getResponseComplete();

    }


    public void prepareDetail(DemandeService demandeService) {

        selectedDemandeService = demandeService;
        initDetailSpecifique(selectedDemandeService);

    }
    
    public void initDetailSpecifique(DemandeService demandeService) {
        if (demandeService.getService().getId() == 1) {
            detailDemandeCleaning = demandeCleaningFacade.findByDemandeService(demandeService);
        }
        if (demandeService.getService().getId() == 8 || demandeService.getService().getId() == 10 || demandeService.getService().getId() == 11 || demandeService.getService().getId() == 12) {
            detailDemandeHandyMan = demandeHandyManFacade.findByDemandeService(demandeService);
        }
        if (demandeService.getService().getId() == 22) {
            detailDemandeFormationPersonnel = demandeFormationPersonnelFacade.findByDemandeService(demandeService);
        }
    }

    public String redirectToOperation() {
        return "/manager/Operation.xhtml?faces-redirect=true";
    }

    public String redirectToProfile() {
        return "/manager/Profile.xhtml?faces-redirect=true";
    }

    public String redirectToStat() {
        return "/manager/StatistiqueTabs.xhtml?faces-redirect=true";
    }

    public void initClientInfo(DemandeService demandeService) {
        client = demandeService.getClient();
        System.out.println("info client : " + client);
    }

    public String seDeConnnecter() {

        ejbFacade.seDeConnnecter();
        System.out.println("hani hnaaaa");
        return "/index?faces-redirect=true";
    }

    public void miseAJourProfil() {
        ejbFacade.edit(selected);
    }

    public void changeMdp() {
        if (ancienPassword.equals(selected.getPassword())) {
            if (nvPassword.equals(nvPassword1) && nvPassword != null) {
                ejbFacade.changeMdp(getSelected(), nvPassword);
                selected.setPassword(nvPassword);
            } else {
            }
        } else {
        }
    }

    public int numberWorkers() {
        return ejbFacade.numberWorkers();
    }

    public BigDecimal profitAnnuelle() {
        return ejbFacade.profitAnnuelle();
    }

    public int numberDemandes() {
        return ejbFacade.numberDemandes();
    }

    public void afficherChart(String service) {
        if (statistiqueGenerale.getChart() == 1) {
            createLineModels(service, statistiqueGenerale, equipement);
        }
        if (statistiqueGenerale.getChart() == 2) {
            createBarModel(service, statistiqueGenerale, equipement);
        }
    }

    private void createLineModels1(String service, StatistiqueGenerale statistiqueGenerale, int equipement) {

        lineCharModel2 = initCategoryModel(service, statistiqueGenerale, equipement);

        lineCharModel2.setLegendPosition("ne");

        lineCharModel2.setShowPointLabels(true);
        lineCharModel2.getAxes().put(AxisType.X, new CategoryAxis("Mois"));
        Axis yAxis = lineCharModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Montant");
        yAxis.setMin(0);
        System.out.println("hahowa si max : " + max);
        yAxis.setMax(max.multiply(new BigDecimal(1.1)));
        Axis xAxis = lineCharModel2.getAxis(AxisType.X);
        xAxis.setMin(0);
    }

    private void createLineModels(String service, StatistiqueGenerale statistiqueGenerale, int equipement) {

        lineCharModel = initCategoryModel(service, statistiqueGenerale, equipement);

        String title = "Statisique pour Service '" + service + "'";

        if (statistiqueGenerale.getAnnee() > 0) {
            title += ", Annee : " + statistiqueGenerale.getAnnee() + "";
        }
        if (statistiqueGenerale.getVille() != null) {
            title += ", Ville : " + statistiqueGenerale.getVille().getNom() + "";
        }
        if (statistiqueGenerale.getSecteur() != null) {
            title += ", Secteur : " + statistiqueGenerale.getSecteur().getNom() + "";
        }
        if (statistiqueGenerale.getWorker() != null) {
            title += ", Worker : " + statistiqueGenerale.getWorker().getNom() + "";
        }

        lineCharModel.setTitle(title);
        lineCharModel.setLegendPosition("ne");

        lineCharModel.setShowPointLabels(true);
        lineCharModel.getAxes().put(AxisType.X, new CategoryAxis("Mois"));
        Axis yAxis = lineCharModel.getAxis(AxisType.Y);
        yAxis.setLabel("Montant");
        yAxis.setMin(0);
        yAxis.setMax(max.multiply(new BigDecimal(1.1)));
        Axis xAxis = lineCharModel.getAxis(AxisType.X);
        xAxis.setMin(0);
    }

    private void createBarModel(String service, StatistiqueGenerale statistiqueGenerale, int equipement) {
        barCharModel = initBarModel(service, statistiqueGenerale, equipement);

        String title = "Statisique pour Service '" + service + "'";

        if (statistiqueGenerale.getAnnee() > 0) {
            title += ", Annee : " + statistiqueGenerale.getAnnee() + "";
        }
        if (statistiqueGenerale.getVille() != null) {
            title += ", Ville : " + statistiqueGenerale.getVille().getNom() + "";
        }
        if (statistiqueGenerale.getSecteur() != null) {
            title += ", Secteur : " + statistiqueGenerale.getSecteur().getNom() + "";
        }
        if (statistiqueGenerale.getWorker() != null) {
            title += ", Worker : " + statistiqueGenerale.getWorker().getNom() + "";
        }

        barCharModel.setTitle(title);
        barCharModel.setLegendPosition("ne");
        barCharModel.setShowDatatip(false);
        barCharModel.setShowPointLabels(true);
        Axis xAxis = barCharModel.getAxis(AxisType.X);
        xAxis.setLabel("Mois");

        Axis yAxis = barCharModel.getAxis(AxisType.Y);
        yAxis.setLabel("Montant");
        yAxis.setMin(0);
        yAxis.setMax(max.multiply(new BigDecimal(1.1)));
    }

    private LineChartModel initCategoryModel(String service, StatistiqueGenerale statistiqueGenerale, int equipement) {
        LineChartModel model = new LineChartModel();
        BigDecimal[] resultas = ejbFacade.genererStatistique(service, statistiqueGenerale, equipement);
        max = MathUtil.calculerMax(resultas);
        ChartSeries annee = new ChartSeries();
        if (statistiqueGenerale.getAnnee() > 0) {
            annee.setLabel("Annee " + statistiqueGenerale.getAnnee());
        } else {
            annee.setLabel("Globale");
        }
        annee.set("Janvier", resultas[0]);
        annee.set("Fevrier", resultas[1]);
        annee.set("Mars", resultas[2]);
        annee.set("Avril", resultas[3]);
        annee.set("Mai", resultas[4]);
        annee.set("Juin", resultas[5]);
        annee.set("Juillet", resultas[6]);
        annee.set("Aout", resultas[7]);
        annee.set("Semptembre", resultas[8]);
        annee.set("Octobre", resultas[9]);
        annee.set("Novombre", resultas[10]);
        annee.set("Decembre", resultas[11]);

        model.addSeries(annee);

        return model;

    }

    private BarChartModel initBarModel(String service, StatistiqueGenerale statistiqueGenerale, int equipement) {
        BarChartModel model = new BarChartModel();
        BigDecimal[] resultas = ejbFacade.genererStatistique(service, statistiqueGenerale, equipement);
        max = MathUtil.calculerMax(resultas);
        ChartSeries annee = new ChartSeries();
        if (statistiqueGenerale.getAnnee() > 0) {
            annee.setLabel("Annee " + statistiqueGenerale.getAnnee());
        } else {
            annee.setLabel("Globale");
        }

        annee.set("Janvier", resultas[0]);
        annee.set("Fevrier", resultas[1]);
        annee.set("Mars", resultas[2]);
        annee.set("Avril", resultas[3]);
        annee.set("Mai", resultas[4]);
        annee.set("Juin", resultas[5]);
        annee.set("Juillet", resultas[6]);
        annee.set("Aout", resultas[7]);
        annee.set("Semptembre", resultas[8]);
        annee.set("Octobre", resultas[9]);
        annee.set("Novombre", resultas[10]);
        annee.set("Decembre", resultas[11]);

        model.addSeries(annee);

        return model;
    }

    public String login() {
        System.out.println("bsmllah");
        int conected = ejbFacade.login(selected);
        System.out.println(selected);
//        if (conected == 0) {
//            SessionUtil.setAttribute("connectedManager", selected);
////            Device dev = deviceFacade.getManagerDevice(selected);
////            deviceFacade.creerDevice(dev);
//        } else if (conected == 1) {
//            System.out.println("search device");
////            Device device = deviceFacade.verifDevice(selected);
//            System.out.println(device);
//            if (device == null) {
//                return "/manager/question?faces-redirect=true";
//            } else {
//                device.setDateConnection(new Date());
//                deviceFacade.edit(device);
//                selected = ejbFacade.find(selected.getId());
//                System.out.println(selected);
//                SessionUtil.setAttribute("connectedManager", selected);
//            }
//        }
        System.out.println("hahowa selected : " + selected);

        if (conected > 0) {
            selected = ejbFacade.find(selected.getLogin());
            SessionUtil.setAttribute("connectedManager", selected);
            StatistiqueGenerale sg = new StatistiqueGenerale();
            sg.setPrix(2);
            sg.setAnnee(new Date().getYear() + 1900);
            createLineModels1(null, sg, -1);

            return "/manager/Dashboard?faces-redirect=true";
        } else {
            return null;
        }

    }

    public String verifRepons() {
        int verifier = 0;
        verifier += ejbFacade.RepDernConx(selected, dernierConex);
        verifier += ejbFacade.RepDernAction(selected, action);
        verifier += ejbFacade.RepDernConfir(selected, dernierConfirmation);
        if (verifier < 2) {
            return "/manager/Login?faces-redirect=true";
        } else {
            Device dev = deviceFacade.getManagerDevice(selected);
            deviceFacade.creerDevice(dev);
            SessionUtil.setAttribute("device", dev);
            SessionUtil.setAttribute("connectedManager", selected);
            return "/manager/Profile?faces-redirect=true";
        }
    }

    public DeviceFacade getDeviceFacade() {
        return deviceFacade;
    }

    public void setDeviceFacade(DeviceFacade deviceFacade) {
        this.deviceFacade = deviceFacade;
    }

    public Date getDernierConfirmation() {
        return dernierConfirmation;
    }

    public void setDernierConfirmation(Date dernierConfirmation) {
        this.dernierConfirmation = dernierConfirmation;
    }

    public DemandeServiceConfirmationDetail getDemandeServiceConfirmationDetail() {
        if (demandeServiceConfirmationDetail == null) {
            demandeServiceConfirmationDetail = new DemandeServiceConfirmationDetail();
        }
        return demandeServiceConfirmationDetail;
    }

    public void setDemandeServiceConfirmationDetail(DemandeServiceConfirmationDetail demandeServiceConfirmationDetail) {
        this.demandeServiceConfirmationDetail = demandeServiceConfirmationDetail;
    }

    public ManagerFacade getManagerFacade() {
        return ejbFacade;
    }

    public void setManagerFacade(ManagerFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Date getDernierConex() {
        return dernierConex;
    }

    public void setDernierConex(Date dernierConex) {
        this.dernierConex = dernierConex;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<DemandeServiceConfirmationDetail> getManagerItems() {
        if (managerItems == null) {
            //return confirmationDetailFacade.findByManager(getSelected());
        }
        return managerItems;
    }

    public void setManagerItems(List<DemandeServiceConfirmationDetail> managerItems) {
        this.managerItems = managerItems;
    }

    public int getEquipement() {
        return equipement;
    }

    public void setEquipement(int equipement) {
        this.equipement = equipement;
    }

    public List<Ville> getStatVilles() {
        if (statVilles == null) {
            statVilles = villeFacade.findAll();
        }
        return statVilles;
    }

    public void setStatVilles(List<Ville> statVilles) {
        this.statVilles = statVilles;
    }

    public List<Secteur> getStatSecteurs() {
        if (statSecteurs == null) {
            statSecteurs = new ArrayList<>();
        }
        return statSecteurs;
    }

    public List<DemandeService> getDemandeServices() {
        if (demandeServices == null) {
            demandeServices = demandeServiceFacade.findAllDemandesSaufLocation();
        }
        return demandeServices;
    }

    public void setDemandeServices(List<DemandeService> demandeServices) {
        this.demandeServices = demandeServices;
    }

    public void setStatSecteurs(List<Secteur> statSecteurs) {
        this.statSecteurs = statSecteurs;
    }

    public LineChartModel getLineCharModel() {
        return lineCharModel;
    }

    public void setLineCharModel(LineChartModel lineCharModel) {
        this.lineCharModel = lineCharModel;
    }

    public LineChartModel getLineCharModel2() {
        if (lineCharModel2 == null) {
            lineCharModel2 = new LineChartModel();
        }

        return lineCharModel2;
    }

    public void setLineCharModel2(LineChartModel lineCharModel2) {
        this.lineCharModel2 = lineCharModel2;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public ManagerController() {
    }

    public void onCityChange() {

    }

    public String nomVille() {
        if (ville == null) {
            ville = new Ville();
            ville.setNom("Marrakesh");
        }
        return ville.getNom();

    }

    public String redirectToHandyMan() {
        return "/demandeHandyMan/List.xhtml";
    }

//    public List<Service> loadServices(){
//        return serviceFacade.findByVille(ville);
//    }
    public String getImagename() {
        String nomVille = "Marrakesh";
        if (ville != null) {
            return ville.getNom();
        }
        return nomVille;
    }

    public List<Ville> loadVilles() {
        return villeFacade.findAll();
    }

    public void doAction() {
        loadSeectors(ville);
        loadServices(ville);
    }

    public void loadStatSecteursAndServices() {
        loadSeectors(statistiqueGenerale.getVille());
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
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

    public Manager getSelected() {
        if (selected == null) {
            selected = new Manager();
            if (((Manager) SessionUtil.getAttribute("connectedManager")) != null) {
                selected = (Manager) SessionUtil.getAttribute("connectedManager");
            }
        }
        return selected;
    }

    public void setSelected(Manager selected) {
        this.selected = selected;
    }

    public List<Service> getServices() {
        if (services == null) {
            services = new ArrayList<>();
        }
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getAncienPassword() {
        return ancienPassword;
    }

    public void setAncienPassword(String ancienPassword) {
        this.ancienPassword = ancienPassword;
    }

    public String getNvPassword() {
        return nvPassword;
    }

    public void setNvPassword(String nvPassword) {
        this.nvPassword = nvPassword;
    }

    public String getNvPassword1() {
        return nvPassword1;
    }

    public void setNvPassword1(String nvPassword1) {
        this.nvPassword1 = nvPassword1;
    }

    public Client getClient() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BarChartModel getBarCharModel() {
        return barCharModel;
    }

    public void setBarCharModel(BarChartModel barCharModel) {
        this.barCharModel = barCharModel;
    }

    public List<Worker> getStatWorkers() {
        if (statWorkers == null) {
            statWorkers = workerFacade.findAll();
        }
        return statWorkers;
    }

    public void setStatWorkers(List<Worker> statWorkers) {
        this.statWorkers = statWorkers;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ManagerFacade getFacade() {
        return ejbFacade;
    }

    public List<Review> getWorkerReviews() {
        if (workerReviews == null) {
            workerReviews = new ArrayList<>();
        }
        return workerReviews;
    }

    public void setWorkerReviews(List<Review> workerReviews) {
        this.workerReviews = workerReviews;
    }

    public DemandeService getDemandeServiceSelected() {
        if (demandeServiceSelected == null) {
            demandeServiceSelected = new DemandeService();
        }
        return demandeServiceSelected;
    }

    public void setDemandeServiceSelected(DemandeService demandeServiceSelected) {
        this.demandeServiceSelected = demandeServiceSelected;
    }

    public List<Worker> getWorkers() {
        if (workers == null) {
            workers = workerFacade.findAll();
        }
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public DemandeService getSelectedDemandeService() {
        if (selectedDemandeService == null) {
            selectedDemandeService = new DemandeService();
        }
        return selectedDemandeService;
    }

    public void setSelectedDemandeService(DemandeService selectedDemandeService) {
        this.selectedDemandeService = selectedDemandeService;
    }

    public List<DemandeService> getDemandesRecentes() {
        if (demandesRecentes == null) {
            demandesRecentes = demandeServiceFacade.orderByDate();
        }
        return demandesRecentes;
    }

    public void setDemandesRecentes(List<DemandeService> demandesRecentes) {
        this.demandesRecentes = demandesRecentes;
    }

    public Worker getWorkerReview() {
        if (workerReview == null) {
            workerReview = new Worker();
        }
        return workerReview;
    }

    public void setWorkerReview(Worker workerReview) {
        this.workerReview = workerReview;
    }

    public Client getClientRecherche() {
        return clientRecherche;
    }

    public void setClientRecherche(Client clientRecherche) {
        this.clientRecherche = clientRecherche;
    }

    public Service getServiceRecherche() {
        return serviceRecherche;
    }

    public void setServiceRecherche(Service serviceRecherche) {
        this.serviceRecherche = serviceRecherche;
    }

    public int getEtatRecherche() {
        return etatRecherche;
    }

    public void setEtatRecherche(int etatRecherche) {
        this.etatRecherche = etatRecherche;
    }

    public Date getDateMin() {
        return dateMin;
    }

    public void setDateMin(Date dateMin) {
        this.dateMin = dateMin;
    }

    public Date getDateMax() {
        return dateMax;
    }

    public void setDateMax(Date dateMax) {
        this.dateMax = dateMax;
    }

    public Worker getWorkerRecherche() {
        return workerRecherche;
    }

    public void setWorkerRecherche(Worker workerRecherche) {
        this.workerRecherche = workerRecherche;
    }

    public List<Worker> getWorkerList() {
        if (workerList == null) {
            workerList = new ArrayList<>();
        }
        return workerList;
    }

    public void setWorkerList(List<Worker> workerList) {
        this.workerList = workerList;
    }

    public Worker getWorkerSelected() {
        if (workerSelected == null) {
            workerSelected = new Worker();
        }
        return workerSelected;
    }

    public void setWorkerSelected(Worker workerSelected) {
        this.workerSelected = workerSelected;
    }

    public Worker getLocationVoitureWorkerSelected() {
        if (locationVoitureWorkerSelected == null) {
            locationVoitureWorkerSelected = new Worker();
        }
        return locationVoitureWorkerSelected;
    }

    public void setLocationVoitureWorkerSelected(Worker locationVoitureWorkerSelected) {
        this.locationVoitureWorkerSelected = locationVoitureWorkerSelected;
    }

    public List<DemandeVoitureItem> getDemandeVoitureItems() {
        if (demandeVoitureItems == null) {
            demandeVoitureItems = demandeVoitureItemFacade.findAll();
        }
        return demandeVoitureItems;
    }

    public void setDemandeVoitureItems(List<DemandeVoitureItem> demandeVoitureItems) {
        this.demandeVoitureItems = demandeVoitureItems;
    }

    public boolean isRenderLocationVoitureItems() {
        return renderLocationVoitureItems;
    }

    public void setRenderLocationVoitureItems(boolean renderLocationVoitureItems) {
        this.renderLocationVoitureItems = renderLocationVoitureItems;
    }

//    public List<DemandeService> getDemandeLocationVoitureServices() {
//        if(demandeLocationVoitureServices == null){
//            demandeLocationVoitureServices = demandeServiceFacade.rechercher(null, null, serviceFacade.find(new Long(21)), -1, null, null);
//        }
//        return demandeLocationVoitureServices;
//    }
//
//    public void setDemandeLocationVoitureServices(List<DemandeService> demandeLocationVoitureServices) {
//        this.demandeLocationVoitureServices = demandeLocationVoitureServices;
//    }
    public List<DemandeVoiture> getDemandeVoitures() {
        if (demandeVoitures == null) {
            demandeVoitures = demandeVoitureFacade.findAll();
        }
        return demandeVoitures;
    }

    public void setDemandeVoitures(List<DemandeVoiture> demandeVoitures) {
        this.demandeVoitures = demandeVoitures;
    }

    public VoitureModele getModeleRecherche() {
        return modeleRecherche;
    }

    public void setModeleRecherche(VoitureModele modeleRecherche) {
        this.modeleRecherche = modeleRecherche;
    }

    public Carburant getCarburantRecherche() {
        return carburantRecherche;
    }

    public void setCarburantRecherche(Carburant carburantRecherche) {
        this.carburantRecherche = carburantRecherche;
    }

    public Worker getEmployeRecherche() {
        return employeRecherche;
    }

    public void setEmployeRecherche(Worker employeRecherche) {
        this.employeRecherche = employeRecherche;
    }

    public Date getDateDebutMin() {
        return dateDebutMin;
    }

    public void setDateDebutMin(Date dateDebutMin) {
        this.dateDebutMin = dateDebutMin;
    }

    public Date getDateFinMin() {
        return dateFinMin;
    }

    public void setDateFinMin(Date dateFinMin) {
        this.dateFinMin = dateFinMin;
    }

    public Date getDateDebutMax() {
        return dateDebutMax;
    }

    public void setDateDebutMax(Date dateDebutMax) {
        this.dateDebutMax = dateDebutMax;
    }

    public Date getDateFinMax() {
        return dateFinMax;
    }

    public void setDateFinMax(Date dateFinMax) {
        this.dateFinMax = dateFinMax;
    }

    public List<Worker> getLocationVoitureWorkerList() {
        if (locationVoitureWorkerList == null) {
            locationVoitureWorkerList = new ArrayList<>();
        }
        return locationVoitureWorkerList;
    }

    public void setLocationVoitureWorkerList(List<Worker> locationVoitureWorkerList) {
        this.locationVoitureWorkerList = locationVoitureWorkerList;
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

    public DemandeVoiture getDemandeVoiture() {
        if (demandeVoiture == null) {
            demandeVoiture = new DemandeVoiture();
        }
        return demandeVoiture;
    }

    public void setDemandeVoiture(DemandeVoiture demandeVoiture) {
        this.demandeVoiture = demandeVoiture;
    }

    public DemandeVoitureItem getDemandeVoitureItemRecherche() {
        if (demandeVoitureItemRecherche == null) {
            demandeVoitureItemRecherche = new DemandeVoitureItem();
        }
        return demandeVoitureItemRecherche;
    }

    public void setDemandeVoitureItemRecherche(DemandeVoitureItem demandeVoitureItemRecherche) {
        this.demandeVoitureItemRecherche = demandeVoitureItemRecherche;
    }
    
    public DemandeCleaning getDetailDemandeCleaning() {
        if (detailDemandeCleaning == null) {
            detailDemandeCleaning = new DemandeCleaning();
        }
        return detailDemandeCleaning;
    }

    public void setDetailDemandeCleaning(DemandeCleaning detailDemandeCleaning) {
        this.detailDemandeCleaning = detailDemandeCleaning;
    }

    public DemandeHandyMan getDetailDemandeHandyMan() {
        if (detailDemandeHandyMan == null) {
            detailDemandeHandyMan = new DemandeHandyMan();
        }
        return detailDemandeHandyMan;
    }

    public void setDetailDemandeHandyMan(DemandeHandyMan detailDemandeHandyMan) {
        this.detailDemandeHandyMan = detailDemandeHandyMan;
    }

    public DemandeFormationPersonnel getDetailDemandeFormationPersonnel() {
        if (detailDemandeFormationPersonnel == null) {
            detailDemandeFormationPersonnel = new DemandeFormationPersonnel();
        }
        return detailDemandeFormationPersonnel;
    }

    public void setDetailDemandeFormationPersonnel(DemandeFormationPersonnel detailDemandeFormationPersonnel) {
        this.detailDemandeFormationPersonnel = detailDemandeFormationPersonnel;
    }

    public Manager prepareCreate() {
        selected = new Manager();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ManagerCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ManagerUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ManagerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Manager> getItems() {
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

    public Manager getManager(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Manager> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Manager> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<String> loadCities() {

        List<String> listCities = new ArrayList<>();
        listCities.add("Marrakech");
        listCities.add("Casablanca");
        listCities.add("Madrid");
        listCities.add("Barcelona");

        return listCities;

    }

    public void loadSeectors(Ville ville) {
        secteurs = secteurFacade.findByVille(ville);
        statSecteurs = secteurFacade.findByVille(ville);

    }

    public void loadServices(Ville ville) {

        services = serviceVilleFacade.findServiceforVille(ville);

    }

    public StatistiqueGenerale getStatistiqueGenerale() {
        if (statistiqueGenerale == null) {
            statistiqueGenerale = new StatistiqueGenerale();
        }
        return statistiqueGenerale;
    }

    public void setStatistiqueGenerale(StatistiqueGenerale statistiqueGenerale) {
        this.statistiqueGenerale = statistiqueGenerale;
    }

    @FacesConverter(forClass = Manager.class)
    public static class ManagerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ManagerController controller = (ManagerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "managerController");
            return controller.getManager(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Manager) {
                Manager o = (Manager) object;
                return getStringKey(o.getLogin());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Manager.class.getName()});
                return null;
            }
        }

    }

}
