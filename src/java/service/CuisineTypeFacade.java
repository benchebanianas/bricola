/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.CuisineType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class CuisineTypeFacade extends AbstractFacade<CuisineType> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    
    public CuisineType findByNom(String nom){
        return getUniqueResult("select c from CuisineType c where c.nom='"+nom+"'");
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuisineTypeFacade() {
        super(CuisineType.class);
    }
    
}
