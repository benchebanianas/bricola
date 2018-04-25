/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Filiere;
import bean.Matiere;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class MatiereFacade extends AbstractFacade<Matiere> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    public List<Matiere> findByFiliere(Filiere filiere) {
        return em.createQuery("select m from Matiere m where m.filiere.id='" + filiere.getId() + "'").getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatiereFacade() {
        super(Matiere.class);
    }

}
