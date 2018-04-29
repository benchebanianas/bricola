/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.CuisineType;
import bean.DemandeEvent;
import bean.DemandeService;
import bean.SupplementEvent;
import java.util.ArrayList;
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
public class DemandeEventFacade extends AbstractFacade<DemandeEvent> {

    @EJB
    private service.DemandeServiceFacade demandeServiceFacade;
    @EJB
    private service.CuisineDemandeEventFacade cuisineDemandeEventFacade;
    @EJB
    private service.SupplementDemandeEventFacade supplementDemandeEventFacade;
    @EJB
    private CuisineTypeFacade cuisineTypeFacade;
    @EJB
    private SupplementEventFacade supplementEventFacade;

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeEventFacade() {
        super(DemandeEvent.class);
    }

    public void saveDemandeEvent(DemandeEvent demandeEvent, List<String> eventCuisines, List<String> eventSupplements, DemandeService demandeService) {
        demandeEvent.setDemandeService(demandeService);
        demandeEvent.getDemandeService().setPrixHt(demandeEvent.getDemandeService().getServicePricing().getPrix());
        demandeEvent.getDemandeService().setPrixTtc(demandeEvent.getDemandeService().getPrixHt());
        demandeServiceFacade.edit(demandeService);
        demandeEvent.setId(generateId("DemandeEvent", "id"));
        create(demandeEvent);

        System.out.println(eventCuisines);
        List<CuisineType> cuisines = new ArrayList();
        for (int i = 0; i < eventCuisines.size(); i++) {
            String cuisine = eventCuisines.get(i);
            cuisines.add(i, cuisineTypeFacade.findByNom(cuisine));
        }
        System.out.println(cuisines);

        System.out.println(eventSupplements);
        
        List<SupplementEvent> supplements = new ArrayList();
        for (int i = 0; i < eventSupplements.size(); i++) {
            String supplement = eventSupplements.get(i);
            supplements.add(i, supplementEventFacade.findByNom(supplement));
        }
        System.out.println(supplements);
        
        cuisineDemandeEventFacade.saveCuisinesForEvent(cuisines, demandeEvent);

        supplementDemandeEventFacade.saveSupplementsForEvent(supplements, demandeEvent);

    }

}
