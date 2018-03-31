/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeService;
import bean.Planning;
import bean.PlanningItem;
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
public class PlanningItemFacade extends AbstractFacade<PlanningItem> {
    
    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;
    @EJB
    private PlanningFacade planningFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public PlanningItemFacade() {
        super(PlanningItem.class);
    }
    
    public void saveWithPlanning(Planning planning, DemandeService demandeService, boolean cleaningOnce, boolean cleaningMnayTimes) {
        
        if (cleaningOnce) {
            
            planning.setDateDebut(null);
            planning.setDateFin(null);
        }
        
        for (PlanningItem planningItem : demandeService.getPlanning().getPlanningItems()) {
                planningItem.setId(null);
            }
        
        planning.setId(generateId("Planning", "id"));
        planningFacade.create(planning);
        demandeService.setPlanning(planning);
        
        if (cleaningMnayTimes) {
            
            planning.setDateOnce(null);
            planning.setTiming(null);
            
            planningFacade.edit(planning);
            
            List<PlanningItem> items = planning.getPlanningItems();
            for (PlanningItem planningItem : items) {
                planningItem.setPlanning(planning);
                if (planningItem.getRepetition() == 2) {
                    planningItem.setDay(null);
                } else {
                    planningItem.setNumeroJour(0);
                }
                create(planningItem);
            }
            
        }
        
    }
    
}
