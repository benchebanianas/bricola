/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Review;
import bean.Worker;
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReviewFacade() {
        super(Review.class);
    }

}
