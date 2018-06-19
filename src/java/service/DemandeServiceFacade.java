/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import bean.DemandeService;
import bean.Secteur;
import bean.Service;
import bean.ServicePricing;
import bean.Worker;
import bean.WorkerType;
import controller.util.DateUtil;
import controller.util.SearchUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class DemandeServiceFacade extends AbstractFacade<DemandeService> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;
    @EJB
    private ServicePricingFacade servicePricingFacade;
    @EJB
    private PlanningItemFacade planningItemFacade;
    @EJB
    private WorkerFacade workerFacade;
    @EJB
    private ClientFacade clientFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Object findDemande(DemandeService demandeService) {

        List<Object> demandes = em.createQuery("SELECT demande FROM " + demandeService.getTypeDemande().getId() + " demande WHERE "
                + " demande.demandeService.id='" + demandeService.getId() + "'").getResultList();
        if (demandes == null) {
            return null;
        } else {
            return demandes.get(0);
        }

    }

    public int findByMois(int mois) {
        String moisFormate = "";
        if (mois < 10) {
            moisFormate += "0" + mois;
        } else {
            moisFormate = "" + mois;
        }
        SimpleDateFormat maDate = new SimpleDateFormat("yyyy");
        String annee = maDate.format(new Date());
        String requete = "SELECT ds FROM DemandeService ds WHERE ds.datedemande LIKE '" + annee + "-" + moisFormate + "-%'";
        return em.createQuery(requete).getResultList().size();

    }

    public List<DemandeService> findDemandesByWorker(Worker worker) {
        String requete = "SELECT ds FROM DemandeService ds WHERE ds.worker.email='" + worker.getEmail() + "'";
        return (List<DemandeService>) em.createQuery(requete).getResultList();
    }

    public List<Client> findClientByWorker(Worker worker) {
        String requete = "SELECT ds.client FROM DemandeService ds WHERE ds.worker.email='" + worker.getEmail() + "'";
        return em.createQuery(requete).getResultList();
    }

    public int findNumberOfDemandesByWorker(Worker worker) {
        List<DemandeService> demandes = getMultipleResult("SELECT ds FROM DemandeService ds WHERE ds.worker.email='" + worker.getEmail() + "' AND ds.dateConfirmation = NULL");
        if (demandes == null) {
            return 0;
        }
        return demandes.size();
    }

    public DemandeServiceFacade() {
        super(DemandeService.class);
    }

    public void saveDemandeService(DemandeService demandeService, Service currentService, Worker company, Worker individual, boolean oneTime, boolean multipleTimes) {

        initDemandeService(demandeService, currentService);
        planningItemFacade.saveWithPlanning(demandeService.getPlanning(), demandeService, oneTime, multipleTimes);
        setWorker(demandeService, company, individual);
        
        clientFacade.checkClientInfo(demandeService.getClient());
        System.out.println("mora check client");
        create(demandeService);
        System.out.println("mora create");

    }

    @Override
    public void create(DemandeService demandeService) {
        demandeService.setId(generateId("DemandeService", "id"));
        super.create(demandeService);
    }

    private void initDemandeService(DemandeService demandeService, Service service) {

        demandeService.setService(service);
        ServicePricing servicePricing = servicePricingFacade.findByService(service);
        if(servicePricing != null && servicePricing.getId() != null){
            demandeService.setServicePricing(servicePricing);
        }
        
        demandeService.setDatedemande(new Date());
        demandeService.setSecteur(demandeService.getClient().getSecteur());

    }

    private void setWorker(DemandeService demandeService, Worker company, Worker individual) {
        
        System.out.println("hahowa workertype : "+demandeService.getWorkerType());
        
        if (demandeService.getWorkerType().getId() > 0) {
            if (demandeService.getWorkerType().getId() == 1) {
                demandeService.setWorker(individual);
            } else {
                demandeService.setWorker(company);
            }
        }else {
            demandeService.setWorkerType(null);
        }
        
        System.out.println("salit mn worker type");

    }

    public List<DemandeService> findByCriteria(Long secteur, String workerNom, Long service, Date dateDemande, BigDecimal prixMin,
            BigDecimal prixMax, Integer conSupp) {

        String requette = "select d from DemandeService d where 1=1 ";
        requette += SearchUtil.addConstraint("d", "secteur.id", "=", secteur);
        requette += SearchUtil.addConstraint("d", "worker.nom", "=", workerNom);
        requette += SearchUtil.addConstraintMinMax("d", "prixTtc", prixMin, prixMax);
        requette += SearchUtil.addConstraint("d", "service.id", "=", service);
        requette += SearchUtil.addConstraint("d", "datedemande", "=", DateUtil.formateDate("yyyy-MM-dd", dateDemande));
        if (conSupp != null) {
            switch (conSupp) {
                case 0:
                    requette += " and d.dateSuppression = null and d.dateConfirmation = null";
                    break;
                case 1:
                    requette += " and d.dateConfirmation IS NOT null ";
                    break;
                case 2:
                    requette += " and d.dateSuppression IS NOT null ";
                    break;
                default:
                    break;
            }
        } else {

        }
        System.out.println("haa requeta : " + requette);
        return getEntityManager().createQuery(requette).getResultList();
    }

    public List<DemandeService> rechercher(Worker worker, Client clientRecherche, Service serviceRecherche, int etatRecherche, Date dateMin, Date dateMax) {

        String requete = "SELECT ds FROM DemandeService ds WHERE ds.service.id != 21";
        
        if(worker != null && worker.getEmail() != null){
            requete += " and ds.worker.email = '"+worker.getEmail()+"'";
        }
        
        if(serviceRecherche != null && serviceRecherche.getId() != null){
            requete += " and ds.service.id = '"+serviceRecherche.getId()+"'";
        }
        
        if(clientRecherche != null && clientRecherche.getEmail() != null){
            requete += " and ds.client.email = '"+clientRecherche.getEmail()+"'";
        }
        if(etatRecherche == 1){
            requete += " and ds.dateConfirmation != NULL";
        }
        if(etatRecherche == 2){
            requete += " and ds.dateConfirmation = NULL";
        }
        if (dateMin != null) {
            requete += " AND ds.datedemande >= '" + DateUtil.getSqlDateTime(dateMin) + "'";
        }

        if (dateMax != null) {
            requete += " AND ds.datedemande <= '" + DateUtil.getSqlDateTime(dateMax) + "'";
        }
        
        return em.createQuery(requete).getResultList();

    }
    
    public int findNumberOfNewDemandes(){
        
        List<DemandeService> demandes = getMultipleResult("SELECT ds FROM DemandeService ds WHERE ds.dateConfirmation = NULL");
        if (demandes == null) {
            return 0;
        }
        return demandes.size();
        
    }

    public BigDecimal profitAnnuelle() {
        
        int year = new Date().getYear() + 1900;
        
        String requette = "SELECT sum(ds.prixTtc) FROM DemandeService ds WHERE FUNCTION('YEAR', ds.datedemande) = '"+year+"'";
        
        System.out.println("hahiya requette : "+requette);
        return (BigDecimal) em.createQuery(requette).getResultList().get(0);
        
    }

    public List<DemandeService> orderByDate() {
        
        String requette = "select ds from DemandeService ds where ds.dateConfirmation = NULL order by ds.datedemande desc";
        System.out.println("requette : "+requette);
        return em.createQuery(requette).getResultList().subList(0, 5);
    
    
    }

    public List<DemandeService> findAllDemandesSaufLocation() {
    
        String requette = "select ds from DemandeService ds where ds.service.id != 21";
        return em.createQuery(requette).getResultList();
    
    }

}
