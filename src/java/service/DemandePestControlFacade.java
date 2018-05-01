/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandePestControl;
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
public class DemandePestControlFacade extends AbstractFacade<DemandePestControl> {

    @EJB
    private service.DemandeServiceFacade demandeServiceFacade;

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    public void saveDemandePestControl(DemandePestControl demandePestControl, DemandeService demandeService) {

        demandePestControl.setDemandeService(demandeService);
        demandePestControl.getDemandeService().setPrixHt(demandePestControl.getDemandeService().getServicePricing().getPrix());
        demandePestControl.getDemandeService().setPrixTtc(demandePestControl.getDemandeService().getPrixHt());
        demandeServiceFacade.edit(demandeService);
        demandePestControl.setId(generateId("DemandePestControl", "id"));
        create(demandePestControl);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandePestControlFacade() {
        super(DemandePestControl.class);
    }

}
