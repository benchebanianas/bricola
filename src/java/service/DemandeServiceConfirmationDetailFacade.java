/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeService;
import bean.DemandeServiceConfirmationDetail;
import bean.Manager;
import bean.TypeAction;
import controller.util.DateUtil;
import controller.util.SearchUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class DemandeServiceConfirmationDetailFacade extends AbstractFacade<DemandeServiceConfirmationDetail> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    public void save(Manager manager, TypeAction action, DemandeService demandeService) {
        DemandeServiceConfirmationDetail dscd = new DemandeServiceConfirmationDetail(manager, action, demandeService);
        dscd.setId(generateId("DemandeServiceConfirmationDetail", "id"));
        create(dscd);
    }

    public Manager findByManagerSuppression(DemandeService demandeService) {
        return (Manager) em.createQuery("SELECT d.manager FROM DemandeServiceConfirmationDetail d "
                + "WHERE d.demandeService.id='" + demandeService.getId() + "' and d.typeAction.name='suppression'").getSingleResult();
    }

    public List<DemandeServiceConfirmationDetail> findByCriteria(String manager,Date dateAction, Long secteur, String workerNom, Long service, Date dateDemande, Long typeAction) {

        String requette = "select d from DemandeServiceConfirmationDetail d where d.manager.id='" + manager + "'";
        requette += SearchUtil.addConstraint("d", "demandeService.secteur.id", "=", secteur);
        requette += SearchUtil.addConstraint("d", "demandeService.worker.nom", "=", workerNom);
        requette += SearchUtil.addConstraint("d", "demandeService.service.id", "=", service);
        requette += SearchUtil.addConstraint("d", "typeAction.id", "=", typeAction);
        requette += SearchUtil.addConstraint("d", "demandeService.datedemande", "=", DateUtil.formateDate("yyyy-MM-dd", dateDemande));
        requette += SearchUtil.addConstraint("d", "dateAction", "=", DateUtil.formateDate("yyyy-MM-dd", dateAction));
        System.out.println("haa requeta : " + requette);
        List<DemandeServiceConfirmationDetail> list = getEntityManager().createQuery(requette).getResultList();
        System.out.println("ha list :::"+list);
        return list;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeServiceConfirmationDetailFacade() {
        super(DemandeServiceConfirmationDetail.class);
    }

}
