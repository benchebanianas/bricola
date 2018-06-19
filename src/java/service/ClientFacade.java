/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import bean.Worker;
import controller.util.HashageUtil;
import controller.util.SessionUtil;
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

    public int creerCompte(Client client) {
        Client c = find(client.getEmail());
        if (c == null) {
            Client c1 = new Client();
            c1.setAdresseComplement(client.getAdresseComplement());
            c1.setBlocked(false);
            c1.setEmail(client.getEmail());
            c1.setNom(client.getNom());
            c1.setPassword(client.getPassword());
            c1.setPhone(client.getPassword());
            c1.setPhone(client.getPhone());
            c1.setSecteur(client.getSecteur());
            create(c1);
            return 1;
        } else {
            return -1;
        }
    }

    public void creatAccount(Client client) {
        Client c = new Client();
        c.setEmail(client.getEmail());
        c.setNom(client.getNom());
        create(c);

    }

    public void register(Client client) {
        Client c = find(client.getEmail());
        String hash = HashageUtil.sha256(client.getPassword());
        if (c == null) {
            client.setPassword(hash);
            create(client);
        }
    }

    public int seConnecter(Client client) {
        Client test = findByEmail(client.getEmail());
        if (test != null) {
            if (!test.getPassword().equals(client.getPassword())) {
                return -2;
            } else if (test.isBlocked()) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return -3;
        }
    }

    public Client findByEmail(String em) {
        List<Client> clients = getEntityManager().createQuery("SELECT c FROM Client c WHERE c.email='" + em + "'").getResultList();
        if (clients.isEmpty()) {
            return null;
        } else {
            return clients.get(0);
        }
    }

    public List<Client> checkEmail(String email) {

        String requette = "Select c from Client c where c.email = '" + email + "'";
        return em.createQuery(requette).getResultList();
    }

    public void checkClientInfo(Client client) {

        List<Client> clients = checkEmail(client.getEmail());
        if (clients.isEmpty()) {
            client.setPassword(client.getEmail());
        }
        edit(client);
    }

    public List<Client> findByWorker(Worker selected) {
    
        String requette = "select distinct(ds.client) from DemandeService ds where ds.worker.email = '"+selected.getEmail()+"'";
        return em.createQuery(requette).getResultList();
    
    }

    public void seDeConnnecter() {
        SessionUtil.getSession().invalidate();

    }

}
