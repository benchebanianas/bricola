/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeService;
import bean.DemandeVoiture;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class DemandeVoitureFacade extends AbstractFacade<DemandeVoiture> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeVoitureFacade() {
        super(DemandeVoiture.class);
    }

    public void saveDemandeLocation(DemandeVoiture demandeVoiture, DemandeService demandeService) {
        //kifach andiro lprix dyal tonobilate
    }
    
}
