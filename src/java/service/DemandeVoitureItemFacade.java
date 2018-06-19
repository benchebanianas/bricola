/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Carburant;
import bean.DemandeVoiture;
import bean.DemandeVoitureItem;
import bean.VoitureModele;
import bean.Worker;
import controller.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class DemandeVoitureItemFacade extends AbstractFacade<DemandeVoitureItem> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeVoitureItemFacade() {
        super(DemandeVoitureItem.class);
    }

    public void saveDemandeVoitureItem(DemandeVoiture demandeVoiture, List<DemandeVoitureItem> demandeVoitureItems) {
    
        for (DemandeVoitureItem demandeVoitureItem : demandeVoitureItems) {
            demandeVoitureItem.setId(generateId("DemandeVoitureItem", "id"));
            demandeVoitureItem.setDemandeVoiture(demandeVoiture);
            create(demandeVoitureItem);
        }
    
    }

    public List<DemandeVoitureItem> findByDemandeVoiture(DemandeVoiture demandeVoiture) {
    
        String requette = "select dvi from DemandeVoitureItem dvi where dvi.demandeVoiture.id = '"+demandeVoiture.getId()+"'";
        System.out.println("requette : "+requette);
        List<DemandeVoitureItem> result = em.createQuery(requette).getResultList();
        if(result.isEmpty()){
            return new ArrayList<>();
        }else{
            return result;
        }
    
    }

    public List<DemandeVoitureItem> rechercher(VoitureModele modeleRecherche, Carburant carburantRecherche, Worker employeRecherche, Date dateDebutMin, Date dateDebutMax, Date dateFinMin, Date dateFinMax) {
    
        String requette = "select dvi from DemandeVoitureItem dvi where 1=1";
        
        if(modeleRecherche != null && modeleRecherche.getId() != null){
            requette += " and dvi.modele.id = '"+modeleRecherche.getId()+"'";
        }
        if(carburantRecherche != null && carburantRecherche.getId() != null){
            requette += " and dvi.carburant.id = '"+carburantRecherche.getId()+"'";
        }
        if(employeRecherche != null && employeRecherche.getEmail()!= null){
            requette += " and dvi.worker.email = '"+employeRecherche.getEmail()+"'";
        }
        if(dateDebutMin != null){
            requette += " and dvi.dateDebut >= '"+DateUtil.getSqlDateTime(dateDebutMin)+"'";
        }
        if(dateFinMin != null){
            requette += " and dvi.dateFin >= '"+DateUtil.getSqlDateTime(dateFinMin)+"'";
        }
        if(dateDebutMax != null){
            requette += " and dvi.dateDebut <= '"+DateUtil.getSqlDateTime(dateDebutMax)+"'";
        }
        if(dateFinMax != null){
            requette += " and dvi.dateFin <= '"+DateUtil.getSqlDateTime(dateFinMax)+"'";
        }
        
        return em.createQuery(requette).getResultList();
    
    }

}
