/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeService;
import bean.DemandeServiceConfirmationDetail;
import bean.Manager;
import bean.TypeAction;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class DemandeServiceConfirmationDetailFacade extends AbstractFacade<DemandeServiceConfirmationDetail> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    
    public void save(Manager manager,TypeAction action,DemandeService demandeService){
        DemandeServiceConfirmationDetail dscd =  new DemandeServiceConfirmationDetail(manager, action, demandeService);
        create(dscd);
    }
    
    
    public Manager findByManagerSuppression(DemandeService demandeService){
        return (Manager) em.createQuery("SELECT d.manager FROM DemandeServiceConfirmationDetail d "
                + "WHERE d.demandeService.id='"+ demandeService.getId() +"' and d.typeAction.name='suppression'").getSingleResult();
    }
    
    public List<DemandeServiceConfirmationDetail> findByManager(Manager manager){
        return em.createQuery("SELECT d FROM DemandeServiceConfirmationDetail d WHERE d.manager.id='"+ manager.getId() +"'").getResultList();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeServiceConfirmationDetailFacade() {
        super(DemandeServiceConfirmationDetail.class);
    }
    
}
