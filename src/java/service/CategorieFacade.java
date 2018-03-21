/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Categorie;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class CategorieFacade extends AbstractFacade<Categorie> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void save(Categorie categorie){
        categorie.setId(generateId("Categorie", "id"));
        create(categorie);
    }
    public CategorieFacade() {
        super(Categorie.class);
    }
    
}
