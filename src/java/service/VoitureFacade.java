/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Carburant;
import bean.Couleur;
import bean.Voiture;
import bean.VoitureModele;
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
public class VoitureFacade extends AbstractFacade<Voiture> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VoitureFacade() {
        super(Voiture.class);
    }

    public List<Voiture> findByModel(VoitureModele modele) {

        String requette = "select v from Voiture v where v.modele.id = '" + modele.getId() + "'";
        System.out.println("requette : " + requette);

        return em.createQuery(requette).getResultList();

    }

    public List<Voiture> findByCriteria(VoitureModele modele, Couleur couleur, Carburant carburant) {
        
        
     
        String requette = "select distinct vco.voiture from VoitureCarburant vca, VoitureCouleur vco where vca.voiture.modele.id = '"+modele.getId()+"' and vco.voiture.id = vca.voiture.id";
        if(couleur!= null && couleur.getId() != null){
            requette += " and vco.couleur.id = '"+couleur.getId()+"'";
        }
        if(carburant!= null && carburant.getId() != null){
            requette += " and vca.carburant.id = '"+carburant.getId()+"'";
        }
        
        System.out.println("hahiya requette : "+requette);
        List<Voiture> voitures = em.createQuery(requette).getResultList();
        
        if(!voitures.isEmpty()){
            System.out.println("list dial voitures : "+voitures);
            return voitures;
        }else{
            return new ArrayList<>();
        }
    
    
    }

}
