/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Carburant;
import bean.Categorie;
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
 * @author Boss
 */
@Stateless
public class CouleurFacade extends AbstractFacade<Couleur> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void save(Couleur couleur){
        couleur.setId(generateId("Couleur", "id"));
        create(couleur);
    }
    public CouleurFacade() {
        super(Couleur.class);
    }

    public List<Couleur> findByModele(VoitureModele modele) {
        
        String requette = "select distinct vc.couleur from VoitureCouleur vc where vc.voiture.modele.id = '"+modele.getId()+"'";
        System.out.println("requette : "+requette);
        return em.createQuery(requette).getResultList();
    
    
    }

    public List<Couleur> findColorByCarburant(Carburant carburant, VoitureModele modele) {
    
        String requette = "select distinct vco.couleur from VoitureCarburant vca, VoitureCouleur vco where vca.voiture.modele.id = '"+modele.getId()+"' and vca.carburant.id = '"+carburant.getId()+"' and vco.voiture.id = vca.voiture.id";
        System.out.println("requette :" +requette);
        List<Couleur> couleurs = em.createQuery(requette).getResultList();
        if(!couleurs.isEmpty()){
            System.out.println("hahiya la liste coul : "+couleurs);
            return couleurs;
        }else{
            return new ArrayList<>();
        }
    
    }
    
}
