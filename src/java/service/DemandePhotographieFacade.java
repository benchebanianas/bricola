/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandePhotographie;
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
public class DemandePhotographieFacade extends AbstractFacade<DemandePhotographie> {

    @EJB
    private service.DemandeServiceFacade demandeServiceFacade;

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void saveDemandePhotographie(DemandePhotographie demandePhotographie, DemandeService demandeService) {
        demandePhotographie.setDemandeService(demandeService);
        demandePhotographie.getDemandeService().setPrixHt(demandePhotographie.getDemandeService().getServicePricing().getPrix());
        demandePhotographie.getDemandeService().setPrixTtc(demandePhotographie.getDemandeService().getPrixHt());
        demandeServiceFacade.edit(demandeService);
        demandePhotographie.setId(generateId("DemandePhotographie", "id"));
        create(demandePhotographie);
    }

    public DemandePhotographieFacade() {
        super(DemandePhotographie.class);
    }

}
