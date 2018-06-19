/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Service;
import bean.ServicePricing;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class ServicePricingFacade extends AbstractFacade<ServicePricing> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicePricingFacade() {
        super(ServicePricing.class);
    }

    public ServicePricing findByService(Service service) {
    
        String requette = "select sp from ServicePricing sp where sp.service.id = '"+service.getId()+"'";
        List<ServicePricing> servicePricings = em.createQuery(requette).getResultList();
        if(servicePricings.isEmpty()){
            return new ServicePricing();
        }else{
            return servicePricings.get(0);
        }
    
    }
    
}
