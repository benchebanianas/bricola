/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeMoving;
import bean.DemandeService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class DemandeMovingFacade extends AbstractFacade<DemandeMoving> {

      @EJB
    private service.DemandeServiceFacade demandeServiceFacade;

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeMovingFacade() {
        super(DemandeMoving.class);
    }

    public void saveDemandeMoving(DemandeMoving demandeMoving, DemandeService demandeService) {
        
        
        demandeMoving.setDemandeService(demandeService);
        demandeMoving.getDemandeService().setPrixHt(demandeMoving.getDemandeService().getServicePricing().getPrix());
        demandeMoving.getDemandeService().setPrixTtc(demandeMoving.getDemandeService().getPrixHt());
        demandeServiceFacade.edit(demandeService);
        demandeMoving.setId(generateId("DemandeMoving", "id"));
        create(demandeMoving);
    }
    
}
