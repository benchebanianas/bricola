/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeCleaning;
import bean.DemandeService;
import bean.Worker;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class DemandeCleaningFacade extends AbstractFacade<DemandeCleaning> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;
    @EJB
    private PlanningItemFacade planningItemFacade;
    @EJB
    private WorkerFacade workerFacade;
    @EJB
    private service.ServiceFacade serviceFacade;
    @EJB
    private service.ServicePricingFacade servicePricingFacade;
    @EJB
    private service.ClientFacade clientFacade;
    @EJB
    private service.DemandeServiceFacade demandeServiceFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeCleaningFacade() {
        super(DemandeCleaning.class);
    }

    public void saveDemandeCleaning(DemandeCleaning demandeCleaning, Worker company, Worker individual, boolean cleaningOnce, boolean cleaningMnayTimes) {

        demandeCleaning.getDemandeService().setService(serviceFacade.find(new Long(1)));
        demandeCleaning.getDemandeService().setServicePricing(servicePricingFacade.findByService(demandeCleaning.getDemandeService().getService()));
        demandeCleaning.getDemandeService().setPrixTtc(demandeCleaning.getDemandeService().getServicePricing().getPrix().multiply(demandeCleaning.getNbrHeures()).multiply(demandeCleaning.getNbrCleaner()));
        demandeCleaning.getDemandeService().setPrixHt(demandeCleaning.getDemandeService().getPrixTtc());
        demandeCleaning.getDemandeService().setDatedemande(new Date());
        demandeCleaning.getDemandeService().setSecteur(demandeCleaning.getDemandeService().getClient().getSecteur());

        planningItemFacade.saveWithPlanning(demandeCleaning.getDemandeService().getPlanning(), demandeCleaning.getDemandeService(), cleaningOnce, cleaningMnayTimes);

        if (demandeCleaning.getDemandeService().getWorkerType().getId() > 0) {

            if (demandeCleaning.getDemandeService().getWorkerType().getId() == 1) {
                demandeCleaning.getDemandeService().setWorker(individual);
            } else {

                demandeCleaning.getDemandeService().setWorker(company);
            }

        } else {
            demandeCleaning.getDemandeService().setWorkerType(null);
            demandeCleaning.getDemandeService().setWorker(workerFacade.findBestWorkerBySector(demandeCleaning.getDemandeService().getClient().getSecteur()));
        }

        clientFacade.checkClientInfo(demandeCleaning.getDemandeService().getClient());

        Long id = generateId("DemandeService", "id");
        demandeCleaning.getDemandeService().setId(id);
        demandeServiceFacade.create(demandeCleaning.getDemandeService());
        Long id2 = generateId("DemandeCleaning", "id");
        demandeCleaning.setId(id2);
        create(demandeCleaning);

    }

    public void saveDemandeCleaning(DemandeCleaning demandeCleaning, DemandeService demandeService) {
    
        demandeCleaning.setDemandeService(demandeService);
        demandeCleaning.getDemandeService().setPrixTtc(demandeCleaning.getDemandeService().getServicePricing().getPrix().multiply(demandeCleaning.getNbrHeures()).multiply(demandeCleaning.getNbrCleaner()));
        demandeCleaning.getDemandeService().setPrixHt(demandeCleaning.getDemandeService().getPrixTtc());
        demandeServiceFacade.edit(demandeService);
        demandeCleaning.setId(generateId("DemandeCleaning", "id"));
        create(demandeCleaning);
    
    }

    
}
