/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Review;
import bean.Worker;
import controller.util.SearchUtil;
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
public class ReviewFacade extends AbstractFacade<Review> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    public double calculRating(Worker worker) {
        double stars = 0;
//        List stars = em.createQuery("SELECT r.stars FROM Review r WHERE r.worker.email='"+worker.getEmail()+"'").getResultList();
        List<Review> reviews = getMultipleResult("SELECT r FROM Review r WHERE r.worker.email='" + worker.getEmail() + "'");
        if (reviews == null) {
            return stars;
        } else {
            for (Review review : reviews) {
                stars += review.getStars();
            }
        }
        stars = stars / reviews.size();
        return stars;
    }

    public int numberReviews(Worker worker) {
        List<Review> reviews = getMultipleResult("SELECT r FROM Review r WHERE r.worker.email='" + worker.getEmail() + "'");
        if (reviews == null) {
            return 0;
        }
        return reviews.size();
    }
    
    public List<Review> findByCriteria(String societe,Long service){
       String query = "select r from Review r where 1=1 ";
        query+=SearchUtil.addConstraint("r", "worker.email", "=", societe);
        query+=SearchUtil.addConstraint("r", "service.id", "=", service);
        System.out.println("requeta" + query);
        return getEntityManager().createQuery(query).getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReviewFacade() {
        super(Review.class);
    }

    public List<Review> findReviewByWorker(Worker selected) {
    
        String requette = "select r from Review r where r.worker.email = '"+selected.getEmail()+"'";
        List<Review> reviews = em.createQuery(requette).getResultList();
        if(reviews.isEmpty()){
            return new ArrayList<>();
        }else{
            return reviews;
        }
        
    }

}
