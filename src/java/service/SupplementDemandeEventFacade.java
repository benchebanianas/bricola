/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeEvent;
import bean.SupplementDemandeEvent;
import bean.SupplementEvent;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class SupplementDemandeEventFacade extends AbstractFacade<SupplementDemandeEvent> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SupplementDemandeEventFacade() {
        super(SupplementDemandeEvent.class);
    }

    public void saveSupplementsForEvent(List<SupplementEvent> supplementEvents, DemandeEvent demandeEvent) {
        for (SupplementEvent supplementEvent : supplementEvents) {
            SupplementDemandeEvent supplementDemandeEvent = new SupplementDemandeEvent();
            supplementDemandeEvent.setDemandeEvent(demandeEvent);
            supplementDemandeEvent.setSupplementEvent(supplementEvent);
            supplementDemandeEvent.setId(generateId("SupplementDemandeEvent", "id"));
            create(supplementDemandeEvent);
        }
    }

}
