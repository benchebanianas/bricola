/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashen One
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }

    public List<Client> checkEmail(String email) {
    
        String requette = "Select c from Client c where c.email = '"+email+"'";
         return em.createQuery(requette).getResultList();
     }

    public void checkClientInfo(Client client) {
    
        List<Client> clients = checkEmail(client.getEmail());
        if(clients.isEmpty()){
            client.setPassword(client.getEmail());
        }
        edit(client);
    }
    
}
