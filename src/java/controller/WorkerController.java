package controller;

import bean.Service;
import bean.Worker;
import bean.WorkerJob;
import bean.WorkerType;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.WorkerFacade;

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
import org.primefaces.context.RequestContext;
import service.WorkerJobFacade;

@Named("workerController")
@SessionScoped
public class WorkerController implements Serializable {

    @EJB
    private service.WorkerFacade ejbFacade;
    @EJB
    private WorkerJobFacade workerJobFacade;
    private List<Worker> items = null;
    private Worker selected;
//    les attrs de recherche
   private String email;
    private String nom;
    private int nombreEmployeMin;
    private int nombreEmployeMax;
    private String siteWeb;
    private String phone;
    private WorkerType workerType;
    private boolean blocked = false;
    private boolean accepted = true;
    

    
    public void recherche(){
        items = ejbFacade.findByCriteria(email, nom, new Integer(getNombreEmployeMin()),new Integer(getNombreEmployeMax()), siteWeb, phone, workerType, blocked, accepted);
    }
    
    public List<Worker> nvWorkers(){
        return ejbFacade.findNvWorkers();
    }
    
    public void nvWorkerDialog(Worker w){
        setSelected(w);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("dform");
        context.execute("PF('nvWorkerDialog').hide();");
        context.execute("PF('Dialog').show();");
    }
    
    public List<WorkerJob> loadServices(){
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
    
    public WorkerController() {
    }

    public double showRating() {
        return ejbFacade.showRating(selected);
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

    public String login() {
        int connected = ejbFacade.login(selected);
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
        }
        setSelected((Worker) SessionUtil.getAttribute("connectedWorker"));
        return "/worker/WorkerProfile";
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

    public int getNombreEmployeMin() {
        return nombreEmployeMin;
    }

    public void setNombreEmployeMin(int nombreEmployeMin) {
        this.nombreEmployeMin = nombreEmployeMin;
    }

    public int getNombreEmployeMax() {
        return nombreEmployeMax;
    }

    public void setNombreEmployeMax(int nombreEmployeMax) {
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
        if(workerType==null){
            workerType = new WorkerType();
        }
        return workerType;
    }

    public void setWorkerType(WorkerType workerType) {
        this.workerType = workerType;
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
    
    

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private WorkerFacade getFacade() {
        return ejbFacade;
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

    public Worker getWorker(java.lang.Long id) {
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

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
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
