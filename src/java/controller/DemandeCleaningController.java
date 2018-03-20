package controller;

import bean.Client;
import bean.DemandeCleaning;
import bean.Secteur;
import bean.Ville;
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
    private service.VilleFacade villeFacade;
    @EJB
    private service.SecteurFacade secteurFacade;
    @EJB
    private service.ClientFacade clientFacade;
    private List<DemandeCleaning> items = null;
    private DemandeCleaning selected;
    private boolean cleaningOnce = true;
    private boolean emailValidation;
    private boolean passwordValidation;
    private boolean cleaningMnayTimes;
    private boolean materialsYes;
    private boolean materialsNo = true;
    private boolean ironing = false;
    private boolean windowsCleaning = false;
    private Client loadedClient;
    private Date date;
    private Date dateOnce;
    private List<Date> dates;
    private Ville ville;
    private Secteur secteur;
    private List<Secteur> secteurs;
    private int choice;

    private DemandeCleaning demandeCleaning;

    public DemandeCleaningController() {
    }

    public List<Ville> loadVilles() {
        return villeFacade.findAll();
    }

    public void displayChoice() {
        System.out.println("hahowa choice : " + choice);
        if (choice == 2) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('SocieteDialog').show()");

        }
    }

    public void doAction() {
        loadSeectors();
    }

    public void checkEmail() {
        System.out.println("hahowa dkhl l check email");
        List<Client> clients = clientFacade.checkEmail(demandeCleaning.getDemandeService().getClient());

        if (clients.isEmpty()) {
            emailValidation = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Adresse email non enregister, veuillez remplir les champs !"));

        } else {
            emailValidation = true;
            loadedClient = clients.get(0);
        }
    }

    public void checkPassword() {
        if (demandeCleaning.getDemandeService().getClient().getPassword().equals(loadedClient.getPassword())) {
            passwordValidation = true;
            demandeCleaning.getDemandeService().setClient(loadedClient);
            ville = loadedClient.getSecteur().getVille();
            doAction();
            demandeCleaning.getDemandeService().getClient().setSecteur(loadedClient.getSecteur());

        } else {
            passwordValidation = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Password incorrect"));

        }
        System.out.println("hahowa pass valid : " + passwordValidation);
    }

    public void loadSeectors() {
        secteurs = secteurFacade.findByVille(ville);
    }

    public void checkOnce() {
        if (cleaningOnce == true) {
            cleaningMnayTimes = false;
        }
        if (cleaningOnce == false) {
            cleaningOnce = true;
        }

        System.out.println("hahowa cleaning once : " + cleaningOnce);
        System.out.println("hahowa cleaning many : " + cleaningMnayTimes);
    }

    public void checkMany() {
        if (cleaningMnayTimes == true) {
            cleaningOnce = false;
        }
        if (cleaningMnayTimes == false) {
            cleaningMnayTimes = true;
        }
        System.out.println("hahowa cleaning once : " + cleaningOnce);
        System.out.println("hahowa cleaning many : " + cleaningMnayTimes);

    }

    public void checkMaterialsYes() {
        if (materialsYes == true) {
            materialsNo = false;
        }
        if (materialsYes == false) {
            materialsYes = true;
        }

    }

    public void checkMaterialsNo() {
        if (materialsNo == true) {
            materialsYes = false;
        }
        if (materialsNo == false) {
            materialsNo = true;
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

    public boolean isIroning() {
        return ironing;
    }

    public boolean isPasswordValidation() {
        return passwordValidation;
    }

    public void setPasswordValidation(boolean passwordValidation) {
        this.passwordValidation = passwordValidation;
    }

    public void setIroning(boolean ironing) {
        this.ironing = ironing;
    }

    public boolean isWindowsCleaning() {
        return windowsCleaning;
    }

    public void setWindowsCleaning(boolean windowsCleaning) {
        this.windowsCleaning = windowsCleaning;
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

    public boolean isEmailValidation() {
        return emailValidation;
    }

    public void setEmailValidation(boolean emailValidation) {
        this.emailValidation = emailValidation;
    }

    public boolean isMaterialsYes() {
        return materialsYes;
    }

    public void setMaterialsYes(boolean materialsYes) {
        this.materialsYes = materialsYes;
    }

    public boolean isMaterialsNo() {
        return materialsNo;
    }

    public void setMaterialsNo(boolean materialsNo) {
        this.materialsNo = materialsNo;
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
