/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeCleaning;
import bean.DemandeService;
import bean.PlanningItem;
import bean.Worker;
import controller.util.PdfUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;

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

    public DemandeCleaning findByDemandeService(DemandeService demandeService) {
    
        String requette = "select dc from DemandeCleaning dc where dc.demandeService.id = '"+demandeService.getId()+"'";
        return (DemandeCleaning) em.createQuery(requette).getResultList().get(0);
    
    }


    public void generatePdf(DemandeService demandeService) throws JRException, IOException {
     
        List<PlanningItem> planningItems = demandeService.getPlanning().getPlanningItems();
        if(planningItems.isEmpty()){
            planningItems = new ArrayList<>();
        }
        
        DemandeCleaning demandeCleaning = findByDemandeService(demandeService);
        
        
        Map< String, Object> params = new HashMap<>();
        //params.put("typeCompte", compte.getTypeCompte());
        params.put("ville", demandeService.getSecteur().getVille().getNom());
        params.put("dateCourante", new Date());
        params.put("nomClient", demandeService.getClient().getNom()+" "+demandeService.getClient().getPrenom());
        params.put("emailClient", demandeService.getClient().getEmail());
        params.put("telClient", demandeService.getClient().getPhone());
        params.put("adresseClient", demandeService.getClient().getAdresseComplement()+" ,"+demandeService.getClient().getSecteur());
        params.put("nomEmploye", demandeService.getWorker().getNom());
        params.put("emailEmploye", demandeService.getWorker().getEmail());
        params.put("telEmploye", demandeService.getWorker().getPhone());
        params.put("dateDemande", demandeService.getDatedemande());
        params.put("adresse", demandeService.getClient().getAdresseComplement()+" ,"+demandeService.getClient().getSecteur());
        params.put("detail", demandeService.getDetail());
        params.put("dateDebut", demandeService.getPlanning().getDateDebut());
        params.put("dateFin", demandeService.getPlanning().getDateFin());
        params.put("dateOnce", demandeService.getPlanning().getDateOnce());
        params.put("timingOnce", demandeService.getPlanning().getTiming());
        
        params.put("nbrCleaners", demandeCleaning.getNbrCleaner());
        params.put("nbrHeures", demandeCleaning.getNbrHeures());
        params.put("equipement", demandeCleaning.getBringEquipement()== true ? "Oui" : "Non");
        
        
        
        PdfUtil.generatePdf(planningItems, params, "demande N " +demandeService.getId(),"/report/demandeNettoyage.jasper");

    
    }
}
