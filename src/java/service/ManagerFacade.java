/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import bean.DemandeServiceConfirmationDetail;
import bean.Device;
import bean.Manager;
import bean.Worker;
import java.util.List;
import controller.util.*;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    public int loginBzaf(Manager manager) {
        Worker w;
        Client c;
        Manager m = (Manager) em.createQuery("SELECT m FROM Manager m WHERE m.id='" + manager.getId() + "'").getResultList().get(0);
        if (m == null) {
            c = (Client) em.createQuery("SELECT m FROM Manager m WHERE m.id='" + manager.getId() + "'").getResultList().get(0);
            if (c == null) {
                w = (Worker) em.createQuery("SELECT m FROM Manager m WHERE m.id='" + manager.getId() + "'").getResultList().get(0);
                if (w == null) {
                    return -1;
                } else if (w.isBlocked()) {
                    return -2;
                } else if (!w.getPassword().equals(manager.getPassword())) {
                    return -3;
                } else {
                    List<Device> device = em.createQuery("SELECT d FROM Device d WHERE d.manager.id='" + manager.getId() + "'").setMaxResults(1).getResultList();
                    if (device.isEmpty()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            } else if (c.isBlocked()) {
                return -2;
            } else if (!c.getPassword().equals(manager.getPassword())) {
                return -3;
            }else{
                return 2;
            }
        } else if (m.isBlocked()) {
            return -2;
        } else if (!m.getPassword().equals(manager.getPassword())) {
            return -3;
        } else {
            List<Device> device = em.createQuery("SELECT d FROM Device d WHERE d.manager.id='" + manager.getId() + "'").setMaxResults(1).getResultList();
            if (device.isEmpty()) {
                return 0;
            } else {
                return 3;
            }
        }
    }

    public int RepDernConx(Manager manager, Date dernierDate) {

        String dernierConex = DateUtil.formateDate("yyyy-MM-dd", dernierDate);
        List<Device> device = em.createQuery("SELECT d FROM Device d WHERE "
                + " d.manager.id='" + manager.getId() + "' ORDER BY d.dateConnection DESC").getResultList();
        if (DateUtil.formateDate("yyyy-MM-dd", device.get(0).getDateConnection()).equals(dernierConex)) {
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
        } else if (DateUtil.formateDate("yyyy-MM-dd", demandes.get(0).getDateAction()).equals(dernierConfirmation)) {
            return 1;
        }
        return 0;
    }

    public void changeMdp(Manager manager, String mdp) {
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
