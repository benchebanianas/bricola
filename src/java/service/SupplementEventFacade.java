/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.SupplementEvent;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class SupplementEventFacade extends AbstractFacade<SupplementEvent> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    public SupplementEvent findByNom(String nom) {
        return getUniqueResult("select s from SupplementEvent s where s.nom='" + nom + "'");
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SupplementEventFacade() {
        super(SupplementEvent.class);
    }

}
