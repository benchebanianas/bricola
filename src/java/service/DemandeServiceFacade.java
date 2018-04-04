/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeService;
import bean.Worker;
import java.util.List;
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
    
}
