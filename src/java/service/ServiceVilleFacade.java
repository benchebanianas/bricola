/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Service;
import bean.ServiceVille;
import bean.Ville;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class ServiceVilleFacade extends AbstractFacade<ServiceVille> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceVilleFacade() {
        super(ServiceVille.class);
    }

    public List<Service> findServiceforVille(Ville ville) {

        String requette = "select s.service from ServiceVille s where s.ville.nom = '" + ville.getNom() + "'";
        return em.createQuery(requette).getResultList();

    }

}
