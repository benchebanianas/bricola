/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeHandyMan;
import bean.DemandeService;
import bean.PlanningItem;
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
public class DemandeHandyManFacade extends AbstractFacade<DemandeHandyMan> {
    
    @EJB
    private service.DemandeServiceFacade demandeServiceFacade;

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeHandyManFacade() {
        super(DemandeHandyMan.class);
    }

    public void saveDemandeHandyMan(DemandeHandyMan demandeHandyMan, DemandeService demandeService) {
    
        demandeHandyMan.setDemandeService(demandeService);
        demandeHandyMan.getDemandeService().setPrixTtc(demandeHandyMan.getDemandeService().getServicePricing().getPrix().multiply(demandeHandyMan.getNbrHeures()).multiply(demandeHandyMan.getNbrHandyMan()));
        demandeHandyMan.getDemandeService().setPrixHt(demandeHandyMan.getDemandeService().getPrixTtc());
        demandeHandyMan.setService(demandeService.getService());
        demandeServiceFacade.edit(demandeService);
        demandeHandyMan.setId(generateId("DemandeHandyMan", "id"));
        create(demandeHandyMan);
    
    }
    
    public DemandeHandyMan findByDemandeService(DemandeService demandeService) {
    
        String requette = "select hm from DemandeHandyMan hm where hm.demandeService.id = '"+demandeService.getId()+"'";
        return (DemandeHandyMan) em.createQuery(requette).getResultList().get(0);
    
    }

       public void generatePdf(DemandeService demandeService) throws JRException, IOException {
     
        List<PlanningItem> planningItems = demandeService.getPlanning().getPlanningItems();
        if(planningItems.isEmpty()){
            planningItems = new ArrayList<>();
        }
        
        DemandeHandyMan demandeHandyMan = findByDemandeService(demandeService);
        
        
        Map< String, Object> params = new HashMap<>();
        //params.put("typeCompte", compte.getTypeCompte());
        params.put("ville", demandeService.getSecteur().getVille().getNom());
        params.put("service", demandeService.getService().getNom());
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
        
        params.put("nbrHandyMan", demandeHandyMan.getNbrHandyMan());
        params.put("nbrHeures", demandeHandyMan.getNbrHeures());
        
        
        
        PdfUtil.generatePdf(planningItems, params, "demande N " +demandeService.getId(),"/report/demandeHandyMan.jasper");

    
    }
}
    

