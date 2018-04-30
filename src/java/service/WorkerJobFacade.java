/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Service;
import bean.Worker;
import bean.WorkerJob;
import controller.util.SearchUtil;
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

    public int findNumberofServicesByWorker(Worker worker) {
        List<WorkerJob> jobs = getMultipleResult("SELECT wj FROM WorkerJob wj WHERE wj.worker.email='" + worker.getEmail() + "'");
        if (jobs == null) {
            return 0;
        }
        return jobs.size();
    }

    public List<Service> findServiceByWorker(Worker worker) {
//        return getMultipleResult("SELECT wj.service FROM WorkerJob wj WHERE wj.worker.email='"+worker.getEmail()+"'");
//        return em.createQuery("SELECT wj.service FROM WorkerJob wj WHERE wj.worker.email='"+worker.getEmail()+"'").getResultList();

////////////////tariqa mdl7a for testing
        List<Service> services = new ArrayList();
        List<WorkerJob> jobs = getMultipleResult("SELECT wj FROM WorkerJob wj WHERE wj.worker.email='" + worker.getEmail() + "'");
        if (jobs != null) {
            for (WorkerJob job : jobs) {
                services.add(job.getService());
            }
        }
        return services;
    }

    public List<Worker> findWorkerByServiceAndType(String service, Long type) {
        
        String requette = "select w.worker from WorkerJob w where 1=1 ";
//                + "w.service.nom = '" + service + "' and w.worker.workerType.id = '" + type + "'";
        requette+=SearchUtil.addConstraint("w", "service.nom", "=", service);
        requette+=SearchUtil.addConstraint("w", "worker.workerType.id", "=", type);
        requette+=SearchUtil.addConstraint("w", "worker.accepted", "=", "1");
        requette+=SearchUtil.addConstraint("w", "worker.blocked", "=", "0");
        System.out.println("hahiya requette : " + requette);
        List<Worker> workers = em.createQuery(requette).getResultList();
        System.out.println("hahiya list workers : " + workers);
        if (!workers.isEmpty()) {
            return workers;
        } else {
            return new ArrayList<>();
        }

    }
    public List<WorkerJob> findByWorker(Worker worker) {

        String requette = "select w from WorkerJob w where w.worker.email='" + worker.getEmail() + "'";
        System.out.println("hahiya requette : " + requette);
        List<WorkerJob> workerJobs = em.createQuery(requette).getResultList();
        System.out.println("hahiya list workers : " + workerJobs);
        if (!workerJobs.isEmpty()) {
            return workerJobs;
        } else {
            return new ArrayList();
        }

    }

}
