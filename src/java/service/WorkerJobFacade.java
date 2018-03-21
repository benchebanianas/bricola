/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Worker;
import bean.WorkerJob;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class WorkerJobFacade extends AbstractFacade<WorkerJob> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WorkerJobFacade() {
        super(WorkerJob.class);
    }

    public List<Worker> findWorkerByServiceAndType(String service, int type) {

        String requette = "select w.worker from WorkerJob w where w.service.nom = '"+service+"' and w.worker.type = '"+type+"'";
        System.out.println("hahiya requette : "+requette);
        List<Worker> workers = em.createQuery(requette).getResultList();
        System.out.println("hahiya list workers : " + workers);
        return workers;
    }

}
