package controller;

import bean.Client;
import bean.DemandeService;
import bean.Review;
import controller.util.EmailUtil;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import service.ClientFacade;

import java.io.Serializable;
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
import service.ReviewFacade;

@Named("clientController")
@SessionScoped
public class ClientController implements Serializable {

    @EJB
    private service.ClientFacade ejbFacade;
    private List<Client> items = null;
    private Client selected;
    @EJB
    private service.PasswordEmail passwordEmailFacade;
    @EJB
    private ReviewFacade reviewFacade;
    private DemandeService demandeService;
    private Review review;

    public ClientController() {
    }

//    public List<DemandeService> initDemande() {
//
//        List<DemandeService> demandes = (List<DemandeService>) demandeServiceFacade.findByClient(selected);
//        System.out.println(selected);
//        return demandes;
//    }
    public void commenter() {
        review.setClient(getSelected());
        review.setDateReview(new Date());
        review.setService(demandeService.getService());
        review.setWorker(demandeService.getWorker());
        reviewFacade.create(review);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("reviewDialog.hide();");
    }
    public String redirectToLogin() {
        return "/client/ClientLogin.xhtml?faces-redirect=true";
    }

    public void showMessage(String msg) {
        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", " " + msg + ""));
    }

    public Client init() {
        Client c = (Client) SessionUtil.getAttribute("nselected");
        return c;
    }

    public String connect() {
        if (EmailUtil.emailValidate(selected.getEmail())) {
            int res = ejbFacade.seConnecter(selected);
            if (res > 0) {
                selected = ejbFacade.findByEmail(selected.getEmail());
                System.out.println("done");
                SessionUtil.setAttribute("nselected", selected);
                showMessage("connection reussite");
                return "/client/Compte";
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
        int res = ejbFacade.creerCompte(selected);
        if (res > 0) {
            showMessage("Creation avec succes");
            return "/client/Login";
        } else {
            showMessage("erreur l'ors de la creation");
            return null;
        }
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
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
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
