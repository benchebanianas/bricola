package controller;

import bean.Client;
import bean.DemandeFormationPersonnel;
import bean.Filiere;
import bean.Matiere;
import bean.NiveauScolaire;
import bean.Secteur;
import bean.Ville;
import bean.Worker;
import bean.WorkerType;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.DemandeFormationPersonnelFacade;

import java.io.Serializable;
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
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.context.RequestContext;
import service.ClientFacade;
import service.FiliereFacade;
import service.MatiereFacade;
import service.ProfJobFacade;
import service.SecteurFacade;
import service.VilleFacade;
import service.WorkerTypeFacade;

@Named("demandeFormationPersonnelController")
@SessionScoped
public class DemandeFormationPersonnelController implements Serializable {

    @EJB
    private service.DemandeFormationPersonnelFacade ejbFacade;
    private List<DemandeFormationPersonnel> items = null;
    private DemandeFormationPersonnel selected;
    private NiveauScolaire niveauScolaire;
    @EJB
    private FiliereFacade filiereFacade;
    @EJB
    private MatiereFacade matiereFacade;
    @EJB
    private service.WorkerTypeFacade workerTypeFacade;
    @EJB
    private service.VilleFacade villeFacade;
    @EJB
    private service.SecteurFacade secteurFacade;
    @EJB
    private service.ClientFacade clientFacade;
    @EJB
    private service.ProfJobFacade profJobFacade;
    @EJB
    private service.WorkerJobFacade workerJobFacade;

    private List<Secteur> secteurs;
    private List<Worker> companies;
    private List<Worker> individuals;
    private List<Filiere> filieres;
    private List<Matiere> matieres;

    private Filiere filiere;
    private Matiere matiere;
    private Worker company;
    private Worker individual;
    private WorkerType workerType;
    private Client loadedClient;
    private Ville ville;
    private Secteur secteur;

//    public List<Filiere> filiereItems() {
//        return filiereFacade.findByNiveauScolaire(niveauScolaire);
//    }
//
//    public List<Matiere> matiereItems() {
//        return matiereFacade.findByFiliere(filiere);
//    }
    public List<WorkerType> loadWorkerType() {
        return workerTypeFacade.findAll();
    }

    public List<Ville> loadVilles() {
        return villeFacade.findAll();
    }

    public void loadSectors() {
        secteurs = secteurFacade.findByVille(ville);
    }

    public void doAction() {
        loadSectors();
    }

    public void doActionNiveau(AjaxBehaviorEvent event) {
        filieres = filiereFacade.findByNiveauScolaire(niveauScolaire);
    }

    public void doActionFiliere(AjaxBehaviorEvent event) {
        matieres = matiereFacade.findByFiliere(filiere);
    }

    public void confirmIndividual() {
        System.out.println("hahowa individual selected : " + individual);
    }

    public void confirmCompany() {
        System.out.println("hahiya company selected : " + company);
    }

    public void checkPassword() {
        if (selected.getDemandeService().getClient().getPassword().equals(loadedClient.getPassword())) {
            selected.getDemandeService().setClient(loadedClient);
            ville = loadedClient.getSecteur().getVille();
            doAction();
            selected.getDemandeService().getClient().setSecteur(loadedClient.getSecteur());

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('ConnexionDialog').hide()");

        } else {

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('ConnexionDialog').jq.effect('shake', {times:5}, 100)");

        }
    }

      public void checkChoice() {
        System.out.println("hahowa id li khtar : " + selected.getDemandeService().getWorkerType().getId());
        System.out.println("\nhahowa nom li khtar : " + selected.getDemandeService().getWorkerType().getName());

        RequestContext context = RequestContext.getCurrentInstance();
        if (selected.getDemandeService().getWorkerType().getId() == 2) {
            companies = workerJobFacade.findWorkerByServiceAndType("Nettoyage Maison", selected.getDemandeService().getWorkerType().getId());
            context.execute("PF('SocieteDialog').show()");
        } else if (selected.getDemandeService().getWorkerType().getId() == 1) {
            individuals = workerJobFacade.findWorkerByServiceAndType("Nettoyage Maison", selected.getDemandeService().getWorkerType().getId());

            context.execute("PF('IndividualDialog').show()");
        }
    }

    public void checkEmail() {

        List<Client> clients = clientFacade.checkEmail(selected.getDemandeService().getClient().getEmail());

        if (clients.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Adresse email non enregister, veuillez remplir les champs !"));

        } else {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('ConnexionDialog').show()");
            loadedClient = clients.get(0);
        }
    }

    public DemandeFormationPersonnelController() {
    }

    public DemandeFormationPersonnel getSelected() {
        if (selected == null) {
            selected = new DemandeFormationPersonnel();
        }
        return selected;
    }

    public void setSelected(DemandeFormationPersonnel selected) {
        this.selected = selected;
    }

    public DemandeFormationPersonnelFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DemandeFormationPersonnelFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
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

    public FiliereFacade getFiliereFacade() {

        return filiereFacade;
    }

    public void setFiliereFacade(FiliereFacade filiereFacade) {
        this.filiereFacade = filiereFacade;
    }

    public Matiere getMatiere() {
        if(matiere == null){
            matiere = new Matiere();
        }
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Filiere getFiliere() {
        if (filiere == null) {
            filiere = new Filiere();
        }
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public MatiereFacade getMatiereFacade() {
        return matiereFacade;
    }

    public void setMatiereFacade(MatiereFacade matiereFacade) {
        this.matiereFacade = matiereFacade;
    }

    public WorkerTypeFacade getWorkerTypeFacade() {
        return workerTypeFacade;
    }

    public void setWorkerTypeFacade(WorkerTypeFacade workerTypeFacade) {
        this.workerTypeFacade = workerTypeFacade;
    }

    public VilleFacade getVilleFacade() {
        return villeFacade;
    }

    public void setVilleFacade(VilleFacade villeFacade) {
        this.villeFacade = villeFacade;
    }

    public SecteurFacade getSecteurFacade() {
        return secteurFacade;
    }

    public void setSecteurFacade(SecteurFacade secteurFacade) {
        this.secteurFacade = secteurFacade;
    }

    public ClientFacade getClientFacade() {
        return clientFacade;
    }

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    public ProfJobFacade getProfJobFacade() {
        return profJobFacade;
    }

    public void setProfJobFacade(ProfJobFacade profJobFacade) {
        this.profJobFacade = profJobFacade;
    }

    public List<Matiere> getMatieres() {
        return matieres;
    }

    public void setMatieres(List<Matiere> matieres) {
        this.matieres = matieres;
    }

    public List<Secteur> getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(List<Secteur> secteurs) {
        this.secteurs = secteurs;
    }

    public List<Worker> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Worker> companies) {
        this.companies = companies;
    }

    public List<Worker> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Worker> individuals) {
        this.individuals = individuals;
    }

    public Worker getCompany() {
        return company;
    }

    public void setCompany(Worker company) {
        this.company = company;
    }

    public Worker getIndividual() {
        return individual;
    }

    public void setIndividual(Worker individual) {
        this.individual = individual;
    }

    public WorkerType getWorkerType() {
        return workerType;
    }

    public void setWorkerType(WorkerType workerType) {
        this.workerType = workerType;
    }

    public Client getLoadedClient() {
        return loadedClient;
    }

    public void setLoadedClient(Client loadedClient) {
        this.loadedClient = loadedClient;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public List<Filiere> getFilieres() {
        return filieres;
    }

    public void setFilieres(List<Filiere> filieres) {
        this.filieres = filieres;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeFormationPersonnelFacade getFacade() {
        return ejbFacade;
    }

    public DemandeFormationPersonnel prepareCreate() {
        selected = new DemandeFormationPersonnel();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeFormationPersonnelCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeFormationPersonnelUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeFormationPersonnelDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DemandeFormationPersonnel> getItems() {
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

    public DemandeFormationPersonnel getDemandeFormationPersonnel(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DemandeFormationPersonnel> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DemandeFormationPersonnel> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DemandeFormationPersonnel.class)
    public static class DemandeFormationPersonnelControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeFormationPersonnelController controller = (DemandeFormationPersonnelController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeFormationPersonnelController");
            return controller.getDemandeFormationPersonnel(getKey(value));
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
            if (object instanceof DemandeFormationPersonnel) {
                DemandeFormationPersonnel o = (DemandeFormationPersonnel) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DemandeFormationPersonnel.class.getName()});
                return null;
            }
        }

    }

}
