/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Faq;
import bean.Service;
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
public class FaqFacade extends AbstractFacade<Faq> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FaqFacade() {
        super(Faq.class);
    }

    public List<Faq> findByService(Service currentService) {
    
        String requette = "Select f from Faq f where f.service.id = '"+currentService.getId()+"'";
        
        List<Faq> faqs = em.createQuery(requette).getResultList();
        if(faqs.isEmpty()){
            return new ArrayList<>();
        }else{
            return faqs;
        }
    
    
    }
    
}
