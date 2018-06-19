/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeFormationPersonnel;
import bean.DemandeHandyMan;
import bean.DemandeService;
import bean.PlanningItem;
import controller.util.PdfUtil;
import java.io.IOException;
import java.math.BigDecimal;
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
 * @author Boss
 */
@Stateless
public class DemandeFormationPersonnelFacade extends AbstractFacade<DemandeFormationPersonnel> {
    
    @EJB
    private DemandeServiceFacade demandeServiceFacade;

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeFormationPersonnelFacade() {
        super(DemandeFormationPersonnel.class);
    }

    public void saveDemandeFormationPersonnel(DemandeFormationPersonnel demandeFormationPersonnel, DemandeService demandeService) {
    
    demandeFormationPersonnel.setDemandeService(demandeService);
        demandeFormationPersonnel.getDemandeService().setPrixTtc(demandeFormationPersonnel.getDemandeService().getServicePricing().getPrix().multiply(new BigDecimal(demandeFormationPersonnel.getNbrpersonne())));
        demandeFormationPersonnel.getDemandeService().setPrixHt(demandeFormationPersonnel.getDemandeService().getPrixTtc());
        demandeServiceFacade.edit(demandeService);
        demandeFormationPersonnel.setId(generateId("DemandeFormationPersonnel", "id"));
        create(demandeFormationPersonnel);
    
    }

    public DemandeFormationPersonnel findByDemandeService(DemandeService demandeService) {
    
    String requette = "select fp from DemandeFormationPersonnel fp where fp.demandeService.id = '"+demandeService.getId()+"'";
        return (DemandeFormationPersonnel) em.createQuery(requette).getResultList().get(0);
    }

    public void generatePdf(DemandeService demandeService) throws JRException, IOException {
     
        List<PlanningItem> planningItems = demandeService.getPlanning().getPlanningItems();
        if(planningItems.isEmpty()){
            planningItems = new ArrayList<>();
        }
        
        DemandeFormationPersonnel demandeFormationPersonnel = findByDemandeService(demandeService);
        
        
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
        
        params.put("niveauScolaire", demandeFormationPersonnel.getMatiere().getFiliere().getNiveauScolaire().getNom());
        params.put("matiere", demandeFormationPersonnel.getMatiere().getNom());
        params.put("filiere", demandeFormationPersonnel.getMatiere().getFiliere().getNom());
        
        
        
        PdfUtil.generatePdf(planningItems, params, "demande N " +demandeService.getId(),"/report/demandeFormationPersonnel.jasper");

    
    }
    
}
