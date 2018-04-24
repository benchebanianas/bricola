/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.MenuFormulaire;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Boss
 */
@Stateless
public class MenuFormulaireFacade extends AbstractFacade<MenuFormulaire> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFormulaireFacade() {
        super(MenuFormulaire.class);
    }

    public MenuFormulaire findMenuByService(String nomService) {
        String req = "SELECT m FROM MenuFormulaire m WHERE m.service.nom='" + nomService + "'";
        return (MenuFormulaire) getUniqueResult(req);
    }

}
