/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Filiere;
import bean.NiveauScolaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class FiliereFacade extends AbstractFacade<Filiere> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;
    
    public List<Filiere> findByNiveauScolaire(NiveauScolaire niveauScolaire) {
        String requette = "select f from Filiere f where f.niveauScolaire.id='" + niveauScolaire.getId() + "'";
        return em.createQuery(requette).getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiliereFacade() {
        super(Filiere.class);
    }
    
}
