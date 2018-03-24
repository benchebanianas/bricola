/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Service;
import bean.Worker;
import controller.util.EmailUtil;
import controller.util.SessionUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class WorkerFacade extends AbstractFacade<Worker> {

    @EJB
    ReviewFacade reviewFacade;
    @EJB
    WorkerJobFacade workerJobFacade;
    @EJB
    DemandeServiceFacade demandeServiceFacade;

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public double showRating(Worker worker) {
        return reviewFacade.calculRating(worker);
    }
    public int numberReviews(Worker worker){
        return reviewFacade.numberReviews(worker);
    }
    public int numberServices(Worker worker){
        return workerJobFacade.findNumberofServicesByWorker(worker);
    }
    public int numberDemandes(Worker worker){
        return demandeServiceFacade.findNumberOfDemandesByWorker(worker);
    }
    public List<Service> findServiceByWorker(Worker worker){
        return workerJobFacade.findServiceByWorker(worker);
    }
    public int login(Worker worker) {
        boolean valid = EmailUtil.emailValidate(worker.getEmail());
        if (!valid) {
            //email ecrit incorrectement 
            return -1;
        } else {
            Worker w = find(worker.getEmail());
            if (w == null) {
                //email n'existe pas dans la bd
                return -2;
            } else if (w.isBlocked()) {
                //compte bloqué
                return -3;
            } else if (!worker.getPassword().equals(w.getPassword())) {
                return -4;
            } else {
                SessionUtil.setAttribute("connectedWorker", w);
                return 1;
            }
        }
    }

    public WorkerFacade() {
        super(Worker.class);
    }

}
