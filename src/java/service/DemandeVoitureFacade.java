/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import bean.DemandeService;
import bean.DemandeVoiture;
import bean.DemandeVoitureItem;
import bean.VoiturePricing;
import controller.util.DateUtil;
import controller.util.PdfUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Ashen One
 */
@Stateless
public class DemandeVoitureFacade extends AbstractFacade<DemandeVoiture> {

    @EJB
    private DemandeVoitureItemFacade demandeVoitureItemFacade;
    @EJB
    private DemandeServiceFacade demandeServiceFacade;

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeVoitureFacade() {
        super(DemandeVoiture.class);
    }

    public void saveDemandeVoiture(DemandeVoiture demandeVoiture, DemandeService demandeService, List<DemandeVoitureItem> demandeVoitureItems) {

        if (demandeVoiture == null) {
            demandeVoiture = new DemandeVoiture();
        }
        demandeVoiture.setDemandeService(demandeService);
//        demandeVoiture.getDemandeService().setPrixTtc(voiturePricing.getPrix().multiply(durree));
//        demandeVoiture.getDemandeService().setPrixHt(demandeVoiture.getDemandeService().getPrixTtc());
        demandeServiceFacade.edit(demandeService);
        demandeVoiture.setId(generateId("DemandeVoiture", "id"));
        create(demandeVoiture);
        demandeVoitureItemFacade.saveDemandeVoitureItem(demandeVoiture, demandeVoitureItems);

    }

    public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public DemandeVoiture findByDemandeService(DemandeService demandeService) {

        String requette = "select dv from DemandeVoiture dv where dv.demandeService.id = '" + demandeService.getId() + "'";
        return (DemandeVoiture) em.createQuery(requette).getResultList().get(0);

    }

    public List<DemandeVoiture> rechercher(Client clientRecherche, int etatRecherche, Date dateMin, Date dateMax) {

        String requete = "select dv from DemandeVoiture dv where 1=1";

        if (clientRecherche != null && clientRecherche.getEmail() != null) {
            requete += " and dv.demandeService.client.email = '" + clientRecherche.getEmail() + "'";
        }
        if (etatRecherche == 1) {
            requete += " and dv.demandeService.dateConfirmation != NULL";
        }
        if (etatRecherche == 2) {
            requete += " and dv.demandeService.dateConfirmation = NULL";
        }
        if (dateMin != null) {
            requete += " AND dv.demandeService.datedemande >= '" + DateUtil.getSqlDateTime(dateMin) + "'";
        }

        if (dateMax != null) {
            requete += " AND dv.demandeService.datedemande <= '" + DateUtil.getSqlDateTime(dateMax) + "'";
        }

        System.out.println("hahiya requette : " + requete);
        return em.createQuery(requete).getResultList();
    }

    public void generatePdf(DemandeVoiture demandeVoiture) throws JRException, IOException {

        List<DemandeVoitureItem> demandeVoitureItems = demandeVoitureItemFacade.findByDemandeVoiture(demandeVoiture);
        
        System.out.println("hahiya la liste : "+demandeVoitureItems);

        Map< String, Object> params = new HashMap<>();
        //params.put("typeCompte", compte.getTypeCompte());
        params.put("ville", demandeVoiture.getDemandeService().getSecteur().getVille().getNom());
        params.put("dateCourante", new Date());
        params.put("nomClient", demandeVoiture.getDemandeService().getClient().getNom() + " " + demandeVoiture.getDemandeService().getClient().getPrenom());
        params.put("emailClient", demandeVoiture.getDemandeService().getClient().getEmail());
        params.put("telClient", demandeVoiture.getDemandeService().getClient().getPhone());
        params.put("adresseClient", demandeVoiture.getDemandeService().getClient().getAdresseComplement() + " ," + demandeVoiture.getDemandeService().getClient().getSecteur());
        params.put("dateDemande", demandeVoiture.getDemandeService().getDatedemande());
        params.put("adresse", demandeVoiture.getDemandeService().getClient().getAdresseComplement() + " ," + demandeVoiture.getDemandeService().getClient().getSecteur());

        PdfUtil.generatePdf(demandeVoitureItems, params, "Demande Voiture N_" + demandeVoiture.getId(), "/report/demandeVoiture.jasper");

    }

}
