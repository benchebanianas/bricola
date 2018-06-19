/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Carburant;
import bean.Couleur;
import bean.VoitureModele;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class CarburantFacade extends AbstractFacade<Carburant> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void save(Carburant carburant){
        carburant.setId(generateId("Carburant", "id"));
        create(carburant);
    }
    public CarburantFacade() {
        super(Carburant.class);
    }

    public List<Carburant> findByModele(VoitureModele modele) {
        
        String requette = "select distinct vc.carburant from VoitureCarburantCouleur vc where vc.voiture.modele.id = '"+modele.getId()+"'";
        System.out.println("requette : "+requette);
        return em.createQuery(requette).getResultList();
    }

    public List<Carburant> findCarburantsByColor(Couleur couleur, VoitureModele modele) {
    
    String requette = "select distinct vca.carburant from VoitureCarburant vca, VoitureCouleur vco where vca.voiture.modele.id = '"+modele.getId()+"' and vco.couleur.id = '"+couleur.getId()+"' and vco.voiture.id = vca.voiture.id";
        System.out.println("requette :" +requette);
        List<Carburant> carburants = em.createQuery(requette).getResultList();
        if(!carburants.isEmpty()){
            System.out.println("hahiya la liste carb : "+carburants);
            return carburants;
        }else{
            return new ArrayList<>();
        }
   
    }
    
}
