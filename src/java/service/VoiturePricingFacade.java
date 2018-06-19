/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Voiture;
import bean.VoiturePricing;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class VoiturePricingFacade extends AbstractFacade<VoiturePricing> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void save(VoiturePricing voiturePricing){
        voiturePricing.setId(generateId("VoiturePricing", "id"));
        create(voiturePricing);
    }
    public VoiturePricingFacade() {
        super(VoiturePricing.class);
    }

    public VoiturePricing findByVoiture(Voiture voiture) {
    
       String requette = "select vp from VoiturePricing vp where vp.voiture.id = '"+voiture.getId()+"'";
       return (VoiturePricing) em.createQuery(requette).getResultList().get(0);
    
    
    }
    
}
