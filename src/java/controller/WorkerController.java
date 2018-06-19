package controller;

import bean.Client;
import bean.DemandeCleaning;
import bean.DemandeFormationPersonnel;
import bean.DemandeHandyMan;
import bean.DemandeService;
import bean.DemandeVoiture;
import bean.DemandeVoitureItem;
import bean.Review;
import bean.Secteur;
import bean.Service;
import bean.StatistiqueGenerale;
import bean.Worker;
import bean.WorkerJob;
import bean.WorkerType;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.MathUtil;
import controller.util.SessionUtil;
import java.io.IOException;
import service.WorkerFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import service.DemandeServiceFacade;
import service.WorkerJobFacade;

@Named("workerController")
@SessionScoped
public class WorkerController implements Serializable {

    @EJB
    private service.WorkerFacade ejbFacade;
    @EJB
    private service.DemandeVoitureItemFacade demandeVoitureItemFacadeFacade;
    @EJB
    private service.DemandeCleaningFacade demandeCleaningFacade;
    @EJB
    private service.DemandeFormationPersonnelFacade demandeFormationPersonnelFacade;
    @EJB
    private service.DemandeVoitureFacade demandeVoitureFacade;
    @EJB
    private service.DemandeHandyManFacade demandeHandyManFacade;
    @EJB
    private service.SecteurFacade secteurFacade;
    @EJB
    private service.ReviewFacade reviewFacade;
    @EJB
    private service.ClientFacade clientFacade;
    @EJB
    private service.ServiceFacade serviceFacade;
    @EJB
    private service.ManagerFacade managerFacade;
    @EJB
    private WorkerJobFacade workerJobFacade;
    @EJB
    private DemandeServiceFacade demandeServiceFacade;
    private List<Worker> items = null;
    private Worker selected;
//    les attrs de recherche
    private String email;
    private String nom;
    private Integer nombreEmployeMin;
    private Integer nombreEmployeMax;
    private String siteWeb;
    private String phone;
    private WorkerType workerType;
    private boolean blocked = false;
    private boolean accepted = true;
    private LineChartModel lineCharModel;
    private LineChartModel lineCharModel2;
    private DemandeService demandeServiceDetail;
    private BigDecimal max;
    private BigDecimal max2;
    private StatistiqueGenerale statistiqueGenerale;
    private DemandeService selectedDemandeService;
    private Client client;
    private Client clientRecherche;
    private Service serviceRecherche;
    private int etatRecherche;
    private List<DemandeService> demandeServices;
    private Date dateMin;
    private Date dateMax;
    private WorkerJob workerJob;
    private List<WorkerJob> workerJobs;
    private List<WorkerJob> workerJobsTable;
    private List<DemandeVoitureItem> detailDemandeVoitureItems;

    private List<Service> selectedServices;
    private List<Service> services;
    private List<Secteur> secteurs;

    private DemandeCleaning detailDemandeCleaning;
    private DemandeHandyMan detailDemandeHandyMan;
    private DemandeVoiture detailDemandeVoiture;
    private DemandeFormationPersonnel detailDemandeFormationPersonnel;

    public void initDemandeInfo(DemandeService demandeService) {

    }

    public void deleteWorkerJob(WorkerJob workerJob) {
        workerJobFacade.remove(workerJob);
        workerJobsTable = workerJobFacade.findByWorker(selected);

    }

    public void updateWorkerJob() {
        workerJobFacade.edit(workerJob);
        workerJobsTable = workerJobFacade.findByWorker(selected);
    }

    public void initUpdateWorkerJob(WorkerJob workerJob) {
        this.workerJob = workerJob;
    }

    public void showLoginForm() {

        System.out.println("inside function ...");

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('WorkerDialog').show()");

    }

    public void addWorkerJob() {

        WorkerJob item = clone(this.workerJob);
        workerJobs.add(item);

    }

    public void ajouterWorkerJob() {
        workerJob.setId(workerJobFacade.generateId("WorkerJob", "id"));
        workerJob.setWorker(selected);
        workerJobFacade.create(workerJob);
        workerJobsTable = workerJobFacade.findByWorker(selected);
        workerJob = new WorkerJob();
    }

    public WorkerJob clone(WorkerJob myWorkerJob) {

        WorkerJob item = new WorkerJob();

        int id = 0;
        try {
            id = workerJobs.get(workerJobs.size() - 1).getId().intValue() + 1;
        } catch (Exception e) {
        }

        item.setId(new Long(id));
        item.setSecteur(myWorkerJob.getSecteur());
        item.setService(myWorkerJob.getService());
        System.out.println("hani item : " + item);
        return item;
    }

    public void deleteWorkerJobItem(WorkerJob item) {

        workerJobs.remove(item);
    }

    public void inscription() {
        System.out.println("list workerJobs : " + workerJobs);
        System.out.println("hahowa worker type :" + selected.getWorkerType());

        try {
            ejbFacade.inscription(selected, workerJobs);
            JsfUtil.addSuccessMessage("Inscription faite avec succes");
        } catch (Exception e) {
        }

    }

    public void showInscriptionForm() {

        System.out.println("inside function ...");

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('InscriptionDialog').show()");

    }

    public void genererPdf(DemandeService demandeService) throws JRException, IOException {

        checkServiceForPDF(demandeService);
        FacesContext.getCurrentInstance().getResponseComplete();

    }

    public void checkServiceForPDF(DemandeService demandeService) throws JRException, IOException {
        if (demandeService.getService().getId() == 1) {
            demandeCleaningFacade.generatePdf(demandeService);
        }
        if (demandeService.getService().getId() == 8 || demandeService.getService().getId() == 10 || demandeService.getService().getId() == 11 || demandeService.getService().getId() == 12) {
            demandeHandyManFacade.generatePdf(demandeService);
        }
        if (demandeService.getService().getId() == 22) {
            demandeFormationPersonnelFacade.generatePdf(demandeService);
        }
    }

    public List<Review> findReviewsByWorker() {
        return reviewFacade.findReviewByWorker(selected);
    }

    public String redirectToOperation() {
        return "/worker/WorkerTable.xhtml?faces-redirect=true";
    }

    public String redirectToProfile() {
        return "/worker/WorkerProfil.xhtml?faces-redirect=true";
    }

    public String redirectToServices() {
        return "/worker/WorkerServices.xhtml?faces-redirect=true";
    }

    public List<Service> findServiceForWorker() {

        return serviceFacade.findByWorker(selected);
    }

    public void rechercheDemande() {
        demandeServices = demandeServiceFacade.rechercher(selected, clientRecherche, serviceRecherche, etatRecherche, dateMin, dateMax);
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
        if (demandeService.getService().getId() == 21) {
            detailDemandeVoiture = demandeVoitureFacade.findByDemandeService(demandeService);
            detailDemandeVoitureItems = demandeVoitureItemFacadeFacade.findByDemandeVoiture(detailDemandeVoiture);
        }
        if (demandeService.getService().getId() == 22) {
            detailDemandeFormationPersonnel = demandeFormationPersonnelFacade.findByDemandeService(demandeService);
        }
    }

    public void initClientInfo(DemandeService demandeService) {
        client = demandeService.getClient();
        System.out.println("info client : " + client);
    }

    public void validerDemande(DemandeService demandeService) {

    }

    public void calculStat() {
        createLineModels2(null, statistiqueGenerale, -1);

    }

    public List<Client> clients() {
        return clientFacade.findByWorker(selected);
    }

    public void recherche() {
        items = ejbFacade.findByCriteria(email, nom, nombreEmployeMin, nombreEmployeMax, siteWeb, phone, workerType, blocked, accepted);
    }

    public void accepter() {
        if (!getSelected().isAccepted()) {
            getSelected().setAccepted(true);
            ejbFacade.edit(selected);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('Dialog').hide();");
        }
    }

    public String seDeConnnecter() {

        ejbFacade.seDeConnnecter();
        System.out.println("hani hnaaaa");
        return "/index?faces-redirect=true";
    }

//    Les Statistiques
    private void createLineModels1(String service, StatistiqueGenerale statistiqueGenerale, int equipement) {

        lineCharModel = initCategoryModel(service, statistiqueGenerale, equipement);

        lineCharModel.setLegendPosition("ne");

        lineCharModel.setShowPointLabels(true);
        lineCharModel.getAxes().put(AxisType.X, new CategoryAxis("Mois"));
        Axis yAxis = lineCharModel.getAxis(AxisType.Y);
        yAxis.setLabel("Montant");
        yAxis.setMin(0);
        System.out.println("hahowa si max : " + max);
        yAxis.setMax(max.multiply(new BigDecimal(1.1)));
        Axis xAxis = lineCharModel.getAxis(AxisType.X);
        xAxis.setMin(0);
    }

    private void createLineModels2(String service, StatistiqueGenerale statistiqueGenerale, int equipement) {

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

    private LineChartModel initCategoryModel(String service, StatistiqueGenerale statistiqueGenerale, int equipement) {
        LineChartModel model = new LineChartModel();

        BigDecimal[] resultas = managerFacade.genererStatistique(service, statistiqueGenerale, equipement);
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

    //    Table
    public void findDetail(DemandeService demandeService) {
        setDemandeServiceDetail(demandeService);
    }

    public List<Client> findClientsByWorker() {
        return demandeServiceFacade.findClientByWorker(selected);
    }

    public List<Worker> nvWorkers() {
        return ejbFacade.findNvWorkers();
    }

    public void nvWorkerDialog(Worker w) {
        setSelected(w);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("dform");
        context.execute("PF('nvWorkerDialog').hide();");
        context.execute("PF('Dialog').show();");
    }

    public List<WorkerJob> loadServices() {
        return workerJobFacade.findByWorker(selected);
    }

    public void modifier() {
        ejbFacade.edit(getSelected());
        items.set(items.indexOf(getSelected()), getSelected());

    }

    public void supprimer() {
        ejbFacade.remove(getSelected());
        items.remove(getSelected());
        selected = null;
    }

    public String next() {
        return "/workerJob/WorkerJobCreate";
    }

    //    Profil
    public void miseAJourProfil() {
        ejbFacade.edit(selected);
    }

    public WorkerController() {
    }

    //    Dashboard
    public String showRating() {
        double rating = ejbFacade.showRating(selected);
        DecimalFormat numberFormat = new DecimalFormat("#.0");
        return numberFormat.format(rating);
    }

    public int numberReviews() {
        return ejbFacade.numberReviews(selected);
    }

    public int numberServices() {
        return ejbFacade.numberServices(selected);
    }

    public int numberDemandes() {
        return ejbFacade.numberDemandes(selected);
    }

    public List<Service> serviceByWorker() {
        return ejbFacade.findServiceByWorker(selected);
    }

    public List<DemandeService> demandesByWorker() {
        return demandeServiceFacade.findDemandesByWorker(selected);
    }

    public String login() {
        int connected = ejbFacade.login(selected);
        System.out.println("hahowa connected : " + connected);
        if (connected == -1) {
            showMessage("Compte introuvable", "Verifiez votre email et mot de passe");
            return null;
        } else if (connected == -2) {
            showMessage("Compte introuvable", "Verifiez votre email et mot de passe");
            return null;
        } else if (connected == -3) {
            showMessage("Votre compte est blocké", "Veuillez contacter un manager");
            return null;
        } else if (connected == -4) {
            showMessage("Compte Introuvable", "Verifiez votre email et mot de passe");
            return null;

        } else if (connected == -5) {
            showMessage("Compte pas encore accepté", "Verifiez votre email et mot de passe");
            return null;
        }
        setSelected((Worker) SessionUtil.getAttribute("connectedWorker"));
        StatistiqueGenerale sg = new StatistiqueGenerale();
        sg.setPrix(2);
        sg.setWorker(selected);
        createLineModels1(null, sg, -1);
        return "/worker/WorkerDashboard?faces-redirect=true";
    }

    public void showMessage(String titre, String contenu) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, titre, contenu);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public Worker getSelected() {
        if (selected == null) {
            selected = new Worker();
        }
        return selected;
    }

    public void setSelected(Worker selected) {
        this.selected = selected;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNombreEmployeMin() {
        return nombreEmployeMin;
    }

    public void setNombreEmployeMin(Integer nombreEmployeMin) {
        this.nombreEmployeMin = nombreEmployeMin;
    }

    public Integer getNombreEmployeMax() {
        return nombreEmployeMax;
    }

    public void setNombreEmployeMax(Integer nombreEmployeMax) {
        this.nombreEmployeMax = nombreEmployeMax;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public LineChartModel getLineCharModel() {
        if (lineCharModel == null) {
            lineCharModel = new LineChartModel();
        }
        return lineCharModel;
    }

    public void setLineCharModel(LineChartModel lineCharModel) {
        this.lineCharModel = lineCharModel;
    }

    public DemandeService getDemandeServiceDetail() {
        return demandeServiceDetail;
    }

    public void setDemandeServiceDetail(DemandeService demandeServiceDetail) {
        this.demandeServiceDetail = demandeServiceDetail;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
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

    public DemandeService getSelectedDemandeService() {
        if (selectedDemandeService == null) {
            selectedDemandeService = new DemandeService();
        }
        return selectedDemandeService;
    }

    public int getEtatRecherche() {
        return etatRecherche;
    }

    public void setEtatRecherche(int etatRecherche) {
        this.etatRecherche = etatRecherche;
    }

    public void setSelectedDemandeService(DemandeService selectedDemandeService) {
        this.selectedDemandeService = selectedDemandeService;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private WorkerFacade getFacade() {
        return ejbFacade;
    }

    public BigDecimal getMax() {
        if (max == null) {
            max = new BigDecimal(0);
        }
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMax2() {
        if (max2 == null) {
            max2 = new BigDecimal(0);
        }
        return max2;
    }

    public void setMax2(BigDecimal max2) {
        this.max2 = max2;
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

    public Client getClient() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClientRecherche() {
        if (clientRecherche == null) {
            clientRecherche = new Client();
        }
        return clientRecherche;
    }

    public void setClientRecherche(Client clientRecherche) {
        this.clientRecherche = clientRecherche;
    }

    public List<DemandeService> getDemandeServices() {
        if (demandeServices == null) {
            demandeServices = demandesByWorker();
        }
        return demandeServices;
    }

    public void setDemandeServices(List<DemandeService> demandeServices) {
        this.demandeServices = demandeServices;
    }

    public Service getServiceRecherche() {
        if (serviceRecherche == null) {
            serviceRecherche = new Service();
        }
        return serviceRecherche;
    }

    public void setServiceRecherche(Service serviceRecherche) {
        this.serviceRecherche = serviceRecherche;
    }

    public Date getDateMin() {
        if (dateMin == null) {
            dateMin = new Date();
        }
        return dateMin;
    }

    public void setDateMin(Date dateMin) {
        this.dateMin = dateMin;
    }

    public Date getDateMax() {
        if (dateMax == null) {
            dateMax = new Date();
        }
        return dateMax;
    }

    public void setDateMax(Date dateMax) {
        this.dateMax = dateMax;
    }

    public List<Service> getSelectedServices() {
        if (selectedServices == null) {
            selectedServices = new ArrayList<>();
        }
        return selectedServices;
    }

    public void setSelectedServices(List<Service> selectedServices) {
        this.selectedServices = selectedServices;
    }

    public List<Service> getServices() {
        if (services == null) {
            services = serviceFacade.findAll();
        }
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public WorkerJob getWorkerJob() {
        if (workerJob == null) {
            workerJob = new WorkerJob();
        }
        return workerJob;
    }

    public void setWorkerJob(WorkerJob workerJob) {
        this.workerJob = workerJob;
    }

    public List<Secteur> getSecteurs() {
        if (secteurs == null) {
            secteurs = secteurFacade.findAll();
        }
        return secteurs;
    }

    public void setSecteurs(List<Secteur> secteurs) {
        this.secteurs = secteurs;
    }

    public List<WorkerJob> getWorkerJobs() {
        if (workerJobs == null) {
            workerJobs = new ArrayList<>();
        }
        return workerJobs;
    }

    public void setWorkerJobs(List<WorkerJob> workerJobs) {
        this.workerJobs = workerJobs;
    }

    public List<WorkerJob> getWorkerJobsTable() {
        if (workerJobsTable == null) {
            workerJobsTable = workerJobFacade.findByWorker(selected);
        }
        return workerJobsTable;
    }

    public void setWorkerJobsTable(List<WorkerJob> workerJobsTable) {
        this.workerJobsTable = workerJobsTable;
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

    public DemandeVoiture getDetailDemandeVoiture() {
        if (detailDemandeVoiture == null) {
            detailDemandeVoiture = new DemandeVoiture();
        }
        return detailDemandeVoiture;
    }

    public void setDetailDemandeVoiture(DemandeVoiture detailDemandeVoiture) {
        this.detailDemandeVoiture = detailDemandeVoiture;
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

    public List<DemandeVoitureItem> getDetailDemandeVoitureItems() {
        if (detailDemandeVoitureItems == null) {

            detailDemandeVoitureItems = new ArrayList<>();
        }
        return detailDemandeVoitureItems;
    }

    public void setDetailDemandeVoitureItems(List<DemandeVoitureItem> detailDemandeVoitureItems) {
        this.detailDemandeVoitureItems = detailDemandeVoitureItems;
    }

    public Worker prepareCreate() {
        selected = new Worker();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("WorkerCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("WorkerUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("WorkerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Worker> getItems() {
        if (items == null) {
            items = ejbFacade.findWorkers();
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

    public Worker getWorker(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Worker> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Worker> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Worker.class)
    public static class WorkerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            WorkerController controller = (WorkerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "workerController");
            return controller.getWorker(getKey(value));
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
            if (object instanceof Worker) {
                Worker o = (Worker) object;
                return getStringKey(o.getEmail());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Worker.class.getName()});
                return null;
            }
        }

    }

}
