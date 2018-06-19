/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.VoitureMarque;
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
public class VoitureModeleFacade extends AbstractFacade<VoitureModele> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    public List<VoitureModele> searchByMarque(VoitureMarque marque) {
        String requette = "SELECT DISTINCT vm FROM VoitureModele vm WHERE vm.marque.id='" + marque.getId() + "'";
        return em.createQuery(requette).getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VoitureModeleFacade() {
        super(VoitureModele.class);
    }

}
