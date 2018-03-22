/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Worker;
import controller.util.EmailUtil;
import controller.util.SessionUtil;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class WorkerFacade extends AbstractFacade<Worker> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
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
