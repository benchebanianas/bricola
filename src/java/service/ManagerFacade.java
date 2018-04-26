/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeServiceConfirmationDetail;
import bean.Device;
import bean.Manager;
import bean.Secteur;
import bean.Service;
import java.util.List;
import controller.util.*;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.primefaces.component.inputtext.InputText.PropertyKeys.type;

/**
 *
 * @author Ashen One
 */
@Stateless
public class ManagerFacade extends AbstractFacade<Manager> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    
    public int login(Manager manager) {

        Manager m = (Manager) em.createQuery("SELECT m FROM Manager m WHERE m.id='" + manager.getId() + "'").getSingleResult();
        if (m == null) {
            return -1;
        } else if (m.isBlocked()) {
            return -2;
        } else if (!m.getPassword().equals(manager.getPassword())) {
            return -3;
        } else {
            List<Device> device = em.createQuery("SELECT d FROM Device d WHERE d.manager.id='" + manager.getId() + "'").setMaxResults(1).getResultList();
            if (device.isEmpty()) {
                return 0;
            } else {
                return 1;
            }
        }
    }
    
    public int RepDernConx(Manager manager, Date dernierDate) {

        String dernierConex = DateUtil.formateDate("yyyy-MM-dd", dernierDate);
        List<Device> device = em.createQuery("SELECT d FROM Device d WHERE "
                + " d.manager.id='" + manager.getId() + "' ORDER BY d.dateConnection DESC").getResultList();
        if (DateUtil.formateDate("yyyy-MM-dd",device.get(0).getDateConnection()).equals(dernierConex)) {
            return 1;
        }
        return 0;
    }
    
    public int RepDernAction(Manager manager, String action) {

        List<DemandeServiceConfirmationDetail> demandes = em.createQuery("SELECT d FROM DemandeServiceConfirmationDetail d WHERE "
                + " d.manager.id='" + manager.getId() + "' ORDER BY d.dateAction DESC").setMaxResults(1).getResultList();
        if (demandes.isEmpty()) {
            System.out.println("type empty");
        } else if (demandes.get(0).getTypeAction().getName().equals(action)) {
            return 1;
        } 
            System.out.println(demandes.get(0).getTypeAction().getName());
        return 0;
    }

    public int RepDernConfir(Manager manager, Date dernierDate) {

        
        String dernierConfirmation = DateUtil.formateDate("yyyy-MM-dd", dernierDate);
        List<DemandeServiceConfirmationDetail> demandes = em.createQuery("SELECT d FROM DemandeServiceConfirmationDetail d WHERE "
                + " d.manager.id='" + manager.getId() + "' AND d.typeAction.name='confirmation' ORDER BY d.dateAction DESC").setMaxResults(1).getResultList();
        if (demandes.isEmpty()) {
            System.out.println("confir empty");
            return 0;
        } else if (DateUtil.formateDate("yyyy-MM-dd",demandes.get(0).getDateAction()).equals(dernierConfirmation)) {
            return 1;
        }
        return 0;
    }
    
    public BigDecimal[] genererStatistique(int annee, Secteur secteur, Service service) {
        BigDecimal[] statistique = new BigDecimal[12];
        for (int i = 1; i <= 12; i++) {
            statistique[i - 1] = statistiqueParMois(annee, i, secteur, service);
        }
        return statistique;

    }

    private BigDecimal statistiqueParMois(int annee,int mois, Secteur secteur, Service service) {
        String requette = "SELECT SUM(ds.prixTtc) FROM DemandeService ds WHERE FUNCTION('MONTH', ds.datedemande) = '"+mois+"'";
        
        if(annee >0){
            requette += " AND FUNCTION('YEAR', ds.datedemande) = '"+annee+"'";
        }

        if (secteur != null && secteur.getId() != null) {
            requette += " AND ds.secteur.id='" + secteur.getId() + "'";
        }

        if (service != null && service.getId() != null) {
            requette += " AND ds.service.id='" + service.getId() + "'";
        }
        
        System.out.println("hahiya requette : "+requette);
        
        List<BigDecimal> res = em.createQuery(requette).getResultList();
        System.out.println("res f service : " + res);
        if (res != null && !res.isEmpty() && res.get(0) != null) {
            return res.get(0);
        }
        return new BigDecimal(0);
    }
    
    public void changeMdp(Manager manager,String mdp){
        Manager m = find(manager.getId());
        m.setPassword(mdp);
        edit(m);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManagerFacade() {
        super(Manager.class);
    }
    
}
