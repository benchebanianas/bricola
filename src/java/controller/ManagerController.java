package controller;

import bean.DemandeServiceConfirmationDetail;
import bean.Device;
import bean.Manager;
import bean.Secteur;
import bean.Service;
import bean.Ville;
import controller.util.JsfUtil;
import controller.util.*;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.ManagerFacade;

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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;
import service.DemandeServiceConfirmationDetailFacade;
import service.DeviceFacade;

@Named("managerController")
@SessionScoped
public class ManagerController implements Serializable {

    @EJB
    private service.ManagerFacade ejbFacade;
    @EJB
    private service.VilleFacade villeFacade;
    @EJB
    private service.SecteurFacade secteurFacade;
    @EJB
    private service.ServiceVilleFacade serviceVilleFacade;
    private List<Manager> items = null;
    private Manager selected;
    private Ville ville;
    private Secteur secteur;
    private Service service;
    private List<Service> services;
    private List<Secteur> secteurs;
    @EJB
    private DeviceFacade deviceFacade;
    @EJB
    private DemandeServiceConfirmationDetailFacade confirmationDetailFacade;
    private List<DemandeServiceConfirmationDetail> managerItems;
    private Date dernierConex;
    private Date dernierConfirmation;
    private String action;
    private DemandeServiceConfirmationDetail demandeServiceConfirmationDetail;
    private String ancienPassword;
    private String nvPassword;
    private String nvPassword1;

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

    public String login() {
        System.out.println("bsmllah");
        int conected = ejbFacade.login(selected);
        System.out.println(selected);
        if (conected == 0) {
            SessionUtil.setAttribute("connectedManager", selected);
            Device dev = deviceFacade.getManagerDevice(selected);
            deviceFacade.creerDevice(dev);
        } else if (conected == 1) {
            System.out.println("search device");
            Device device = deviceFacade.verifDevice(selected);
            System.out.println(device);
            if (device == null) {
                return "/manager/question?faces-redirect=true";
            } else {
                device.setDateConnection(new Date());
                deviceFacade.edit(device);
                selected = ejbFacade.find(selected.getId());
                System.out.println(selected);
                SessionUtil.setAttribute("connectedManager", selected);
            }
        }
        return "/manager/Profile?faces-redirect=true";
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
            return confirmationDetailFacade.findByManager(getSelected());
        }
        return managerItems;
    }

    public void setManagerItems(List<DemandeServiceConfirmationDetail> managerItems) {
        this.managerItems = managerItems;
    }

    public ManagerController() {
    }

    public void onCityChange() {

    }

    public String nomVille() {
        if (ville == null) {
            return "Marrakesh";
        } else {
            return ville.getNom();
        }
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
        loadSeectors();
        loadServices();
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

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ManagerFacade getFacade() {
        return ejbFacade;
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

    public void loadSeectors() {
        secteurs = secteurFacade.findByVille(ville);
    }

    public void loadServices() {

        services = serviceVilleFacade.findServiceforVille(ville);
        System.out.println("hahiya list services : " + services);

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
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Manager.class.getName()});
                return null;
            }
        }

    }

}
