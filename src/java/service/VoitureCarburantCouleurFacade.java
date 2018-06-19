/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.VoitureMarque;
import bean.VoitureCarburantCouleur;
import bean.VoitureModele;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class VoitureCarburantCouleurFacade extends AbstractFacade<VoitureCarburantCouleur> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    public List<VoitureCarburantCouleur> searchByMarque(VoitureMarque marque) {
        String requette = "SELECT DISTINCT vm FROM VoitureCarburantCouleur vm WHERE vm.marque.id='" + marque.getId() + "'";
        return em.createQuery(requette).getResultList();
    }
    
    public List<VoitureModele> findByMarque(VoitureMarque marque){
        String requette = "SELECT vm FROM VoitureModele vm WHERE vm.marque.id='" + marque.getId() + "'";
        return em.createQuery(requette).getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VoitureCarburantCouleurFacade() {
        super(VoitureCarburantCouleur.class);
    }

}
