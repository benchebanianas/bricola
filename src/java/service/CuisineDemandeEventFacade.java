/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.CuisineDemandeEvent;
import bean.CuisineType;
import bean.DemandeEvent;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class CuisineDemandeEventFacade extends AbstractFacade<CuisineDemandeEvent> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuisineDemandeEventFacade() {
        super(CuisineDemandeEvent.class);
    }

    public void saveCuisinesForEvent(List<CuisineType> cuisineTypes, DemandeEvent demandeEvent) {
        for (CuisineType cuisineType : cuisineTypes) {
            System.out.println("zb");
            CuisineDemandeEvent cuisineDemandeEvent = new CuisineDemandeEvent();
            cuisineDemandeEvent.setCuisine(cuisineType);
            cuisineDemandeEvent.setDemandeEvent(demandeEvent);
            cuisineDemandeEvent.setId(generateId("CuisineDemandeEvent", "id"));
            create(cuisineDemandeEvent);
        }
    }

}
