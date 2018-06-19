/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeVoitureItem;
import bean.Secteur;
import bean.Service;
import bean.Worker;
import bean.WorkerJob;
import bean.WorkerType;
import controller.util.EmailUtil;
import controller.util.SearchUtil;
import controller.util.SessionUtil;
import java.util.ArrayList;
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

    public int creerCompte(Worker worker) {
        Worker w = find(worker.getEmail());
        if (w == null) {
            Worker inscrit = new Worker();
            inscrit.setDescription(worker.getDescription());
            inscrit.setEmail(worker.getEmail());
            inscrit.setNom(worker.getNom());
            inscrit.setNombreEmploye(worker.getNombreEmploye());
            inscrit.setPassword(worker.getPassword());
            inscrit.setSiteWeb(worker.getSiteWeb());
            inscrit.setPhone(worker.getPhone());
            inscrit.setWorkerType(worker.getWorkerType());
            create(inscrit);
            return 1;
        }
        return -1;

    }

    public double showRating(Worker worker) {
        return reviewFacade.calculRating(worker);
    }

    public int numberReviews(Worker worker) {
        return reviewFacade.numberReviews(worker);
    }

    public int numberServices(Worker worker) {
        return workerJobFacade.findNumberofServicesByWorker(worker);
    }

    public int numberDemandes(Worker worker) {
        return demandeServiceFacade.findNumberOfDemandesByWorker(worker);
    }
    
    

    public List<Service> findServiceByWorker(Worker worker) {
        return workerJobFacade.findServiceByWorker(worker);
    }

    Worker findBestWorkerBySector(Secteur secteur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            } else if(!w.isAccepted()){
                return -5;
            } 
            else {
                SessionUtil.setAttribute("connectedWorker", w);
                return 1;
            }
        }
    }

    public List<Worker> findWorkers() {

        String requette = "select w from Worker w where w.accepted = 1 and w.blocked =0";
        System.out.println("hahiya requette : " + requette);
        List<Worker> workers = em.createQuery(requette).getResultList();
        System.out.println("hahiya list workers : " + workers);
        if (!workers.isEmpty()) {
            return workers;
        } else {
            return new ArrayList();
        }

    }

    public List<Worker> findNvWorkers() {

        String requette = "select w from Worker w where w.accepted = 0 and w.blocked = 0";
        System.out.println("hahiya requette : " + requette);
        List<Worker> workers = em.createQuery(requette).getResultList();
        System.out.println("hahiya list workers : " + workers);
        if (!workers.isEmpty()) {
            return workers;
        } else {
            return new ArrayList();
        }

    }

    public List<Worker> findByCriteria(String email, String nom, Integer nombreEmployeMin, Integer nombreEmployeMax, String siteWeb,
            String phone, WorkerType workerType, Boolean blocked, Boolean accepted) {

        String requette = "select w from Worker w where 1=1 ";
        requette += SearchUtil.addConstraint("w", "email", "=", email);
        requette += SearchUtil.addConstraint("w", "nom", "=", nom);
        requette += SearchUtil.addConstraintMinMax("w", "nombreEmploye", nombreEmployeMin, nombreEmployeMax);
        requette += SearchUtil.addConstraint("w", "siteWeb", "=", siteWeb);
        if(workerType!=null){
        requette += SearchUtil.addConstraint("w", "workerType.id", "=", workerType.getId());
        }
        requette += SearchUtil.addConstraint("w", "siteWeb", "=", siteWeb);
        requette += SearchUtil.addConstraint("w", "phone", "=", phone);
        int i = 0;
        if (blocked) {
            i = 1;
        }
        requette += " and w.blocked = " + i + " ";
        i = 0;
        if (accepted) {
            i = 1;
        }
        requette += " and w.accepted = " + i + " ";
        System.out.println("haa requeta : " + requette);
        return getEntityManager().createQuery(requette).getResultList();
    }

    public WorkerFacade() {
        super(Worker.class);
    }

    public void seDeConnnecter() {
        SessionUtil.getSession().invalidate();

    }

    public void inscription(Worker selected, List<WorkerJob> workerJobs) {
    
        selected.setAccepted(false);
        create(selected);
        workerJobFacade.createList(selected, workerJobs);
        
    
    }

    public List<Worker> rechercher(DemandeVoitureItem demandeVoitureItem) {
     
        String requette = "select distinct v.worker from Voiture v, WorkerJob wj where v.worker.email = wj.worker.email";
        if(demandeVoitureItem.getModele() != null && demandeVoitureItem.getModele().getId() != null){
            requette += " and v.modele.id = '"+demandeVoitureItem.getModele().getId()+"'";
        }
        if(demandeVoitureItem.getCarburant() != null && demandeVoitureItem.getCarburant().getId() != null){
            requette += " and v.carburant.id = '"+demandeVoitureItem.getCarburant().getId()+"'";
        }
    
        return em.createQuery(requette).getResultList();
    }

}
