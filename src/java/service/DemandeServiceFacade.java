/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeService;
import bean.Service;
import bean.Worker;
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
    
    public int findNumberOfDemandesByWorker(Worker worker){
        List<DemandeService> demandes= getMultipleResult("SELECT ds FROM DemandeService ds WHERE ds.worker.email='"+worker.getEmail()+"'");
        if(demandes == null){
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
        demandeService.setId(generateId("DemandeService", "id"));
        create(demandeService);

    }

    private void initDemandeService(DemandeService demandeService, Service service) {

        demandeService.setService(service);
        demandeService.setServicePricing(servicePricingFacade.findByService(service));
        demandeService.setDatedemande(new Date());
        demandeService.setSecteur(demandeService.getClient().getSecteur());

    }

    private void setWorker(DemandeService demandeService, Worker company, Worker individual) {

        if (demandeService.getWorkerType().getId() > 0) {
            if (demandeService.getWorkerType().getId() == 1) {
                demandeService.setWorker(individual);
            } else {
                demandeService.setWorker(company);
            }
        } else {
            demandeService.setWorkerType(null);
            demandeService.setWorker(workerFacade.findBestWorkerBySector(demandeService.getClient().getSecteur()));
        }

    }

}
