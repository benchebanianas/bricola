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
import bean.Ville;
import bean.Worker;
import controller.util.EmailUtil;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import java.io.IOException;
import service.ClientFacade;

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
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;
import service.ReviewFacade;

@Named("clientController")
@SessionScoped
public class ClientController implements Serializable {

    @EJB
    private service.ClientFacade ejbFacade;
    @EJB
    private service.DemandeVoitureItemFacade demandeVoitureItemFacadeFacade;
    @EJB
    private service.DemandeVoitureFacade demandeVoitureFacade;
    @EJB
    private service.DemandeCleaningFacade demandeCleaningFacade;
    @EJB
    private service.DemandeHandyManFacade demandeHandyManFacade;
    @EJB
    private service.DemandeFormationPersonnelFacade demandeFormationPersonnelFacade;
    @EJB
    private service.SecteurFacade secteurFacade;
    @EJB
    private service.DemandeServiceFacade demandeServiceFacade;
    private List<Client> items = null;
    private Client selected;
    @EJB
    private service.PasswordEmail passwordEmailFacade;
    @EJB
    private ReviewFacade reviewFacade;
    private DemandeService demandeService;
    private Review review;
    private Ville ville;
    private List<Secteur> secteurs;
    private Service serviceRecherche;
    private Worker worker;
    private int etatRecherche;
    private Date dateMin;
    private Date dateMax;
    private List<DemandeService> demandeServices;
    private List<DemandeVoitureItem> detailDemandeVoitureItems;
    private DemandeService selectedDemandeService;
    
    private DemandeCleaning detailDemandeCleaning;
    private DemandeHandyMan detailDemandeHandyMan;
    private DemandeVoiture detailDemandeVoiture;
    private DemandeFormationPersonnel detailDemandeFormationPersonnel;


    public ClientController() {
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

    public void initAvis(DemandeService demandeService) {

        selectedDemandeService = demandeService;
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

    public void initWorkerInfo(DemandeService demandeService) {
        worker = demandeService.getWorker();
    }

    public void rechercheDemande() {
        demandeServices = demandeServiceFacade.rechercher(null, selected, serviceRecherche, etatRecherche, dateMin, dateMax);
    }

    public void miseAJourClient() {
        ejbFacade.edit(selected);
    }

    public void findSecteursByVille() {
        secteurs = secteurFacade.findByVille(ville);
    }

//    public List<DemandeService> initDemande() {
//
//        List<DemandeService> demandes = (List<DemandeService>) demandeServiceFacade.findByClient(selected);
//        System.out.println(selected);
//        return demandes;
//    }
    public String seDeConnnecter() {

        ejbFacade.seDeConnnecter();
        System.out.println("hani hnaaaa");
        return "/index?faces-redirect=true";
    }

    public void commenter() {
        review.setId(ejbFacade.generateId("Review", "id"));
        review.setClient(selected);
        review.setDateReview(new Date());
        review.setDemandeService(selectedDemandeService);
        review.setWorker(selectedDemandeService.getWorker());
        reviewFacade.create(review);
    }

    public String redirectToLogin() {
        return "/client/ClientLogin.xhtml?faces-redirect=true";
    }

    public void showLoginForm() {

        System.out.println("inside function ...");

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('ClientDialog').show()");

    }

    public void showMessage(String msg) {
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", " " + msg + ""));
    }

    public Client init() {
        Client c = (Client) SessionUtil.getAttribute("nselected");
        return c;
    }

    public String returnInsc() {
        selected = null;
        return "/client/Inscription?faces-redirect=true";

    }

    public String connect() {
        if (EmailUtil.emailValidate(selected.getEmail())) {
            int res = ejbFacade.seConnecter(selected);
            if (res > 0) {
                selected = ejbFacade.findByEmail(selected.getEmail());
                System.out.println("done");
                SessionUtil.setAttribute("nselected", selected);
                showMessage("connection reussite");
                return "/client/Profil?faces-redirect=true";
            } else if (res == -3) {
                showMessage("email invalid");
                return null;
            } else if (res == -1) {
                showMessage("ce compte est blocké .");
                return null;
            } else if (res == -2) {
                showMessage("mot de passe erroné .");
                return null;
            } else {
                return null;
            }
        }

        return null;
    }

    public String deconnecte() {
        SessionUtil.remove("nselected");
        return "/client/Login";

    }

    public String resetPassword() {
        int res = passwordEmailFacade.recByEmail(selected, selected.getEmail(), "heizenhamza@gmail.com");

        if (res > 0) {
            showMessage("cheek your email");
            return "/client/Login";
        } else if (res == -2) {
            showMessage("ce compte est bloqué");
        } else {
            showMessage("compte introuvable");
        }
        return null;
    }

    public String createCompte() {
        showMessage("un email est envoyé a " + selected.getEmail());
        ejbFacade.register(selected);
        SessionUtil.setAttribute("client", selected);
        passwordEmailFacade.recByEmailRegistre(selected, selected.getEmail(), "servicemarketmaroc@gmail.com");

        return "/client/ClientLogin";

    }

    public String createCompte2() {
        int res = ejbFacade.creerCompte(selected);
        if (res > 0) {
            showMessage("Creation avec succes");
            return "/client/Login";
        } else {
            showMessage("erreur l'ors de la creation");
            return null;
        }
    }

    public void acc() {
        ejbFacade.creatAccount(selected);
        showMessage("hellow");
    }

    public Client getSelected() {
        if (selected == null) {
            selected = new Client();
        }
        return selected;
    }

    public void setSelected(Client selected) {
        this.selected = selected;
    }

    public DemandeService getDemandeService() {
        return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    public Review getReview() {
        if (review == null) {
            review = new Review();
        }
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
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

    public List<Secteur> getSecteurs() {
        if (secteurs == null) {
            secteurs = new ArrayList<>();
        }
        return secteurs;
    }

    public void setSecteurs(List<Secteur> secteurs) {
        this.secteurs = secteurs;
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

    public List<DemandeService> getDemandeServices() {
        if (demandeServices == null) {
            demandeServices = demandeServiceFacade.rechercher(null, selected, null, -1, null, null);
        }
        return demandeServices;
    }

    public void setDemandeServices(List<DemandeService> demandeServices) {
        this.demandeServices = demandeServices;
    }

    public Worker getWorker() {
        if (worker == null) {
            worker = new Worker();
        }
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
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

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ClientFacade getFacade() {
        return ejbFacade;
    }

    public Client prepareCreate() {
        selected = new Client();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ClientCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ClientUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ClientDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Client> getItems() {
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

    public Client getClient(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Client> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Client> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Client.class)
    public static class ClientControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ClientController controller = (ClientController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "clientController");
            return controller.getClient(getKey(value));
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
            if (object instanceof Client) {
                Client o = (Client) object;
                return getStringKey(o.getEmail());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Client.class.getName()});
                return null;
            }
        }

    }

}
