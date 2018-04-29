/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Packaging;
import bean.PestControlType;
import bean.ServicePricing;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class PackagingFacade extends AbstractFacade<Packaging> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    public List<ServicePricing> findServicePricingFromPackaging(PestControlType pestControl) {
//        return getMultipleResult("select p.servicePricing from Packaging p where p.name='" + pestControl.getNom() + "'");
        List<ServicePricing> listServicePricing;
        listServicePricing = em.createQuery("SELECT p.servicePricing FROM Packaging p WHERE p.name='" + pestControl.getNom() + "'").getResultList();
        if (listServicePricing.isEmpty()) {
            return new ArrayList();
        } else {
            return listServicePricing;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PackagingFacade() {
        super(Packaging.class);
    }

}
