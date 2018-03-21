/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Boss
 */
@Entity
public class DemandeService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datedemande;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Secteur secteur;
    private String detail;
    
    @ManyToOne
    private Worker worker;
    private BigDecimal prixHt;
    private BigDecimal prixTtc;
    @ManyToOne
    private ServicePricing servicePricing;
    @ManyToOne
    private Service service;
    @ManyToOne
    private Planning planning;
    @ManyToOne
    private Manager managerConfirmation;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateConfirmation;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDernierModif;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSuppression;
    @OneToOne
    private TypeDemande typeDemande;

    public DemandeService() {
    }

    public Date getDatedemande() {
        return datedemande;
    }

    public void setDatedemande(Date datedemande) {
        this.datedemande = datedemande;
    }

    public DemandeService(Client client, String detail, Worker worker, BigDecimal prixHt, BigDecimal prixTtc, ServicePricing servicePricing, Service service, Planning planning, TypeDemande typeDemande) {
        this.client = client;
        this.detail = detail;
        this.worker = worker;
        this.prixHt = prixHt;
        this.prixTtc = prixTtc;
        this.servicePricing = servicePricing;
        this.service = service;
        this.planning = planning;
        this.typeDemande = typeDemande;
    }

    public TypeDemande getTypeDemande() {
        if(typeDemande == null){
            typeDemande = new TypeDemande();
        }
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    public Secteur getSecteur() {
        if(secteur == null){
            secteur = new Secteur();
        }
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }
    
    

    public Client getClient() {
        if(client == null){
            client = new Client();
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

   
    public Worker getWorker() {
        if(worker == null){
            worker = new Worker();
        }
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public BigDecimal getPrixHt() {
        return prixHt;
    }

    public void setPrixHt(BigDecimal prixHt) {
        this.prixHt = prixHt;
    }

    public BigDecimal getPrixTtc() {
        return prixTtc;
    }

    public void setPrixTtc(BigDecimal prixTtc) {
        this.prixTtc = prixTtc;
    }

    public ServicePricing getServicePricing() {
        
        return servicePricing;
    }

    public void setServicePricing(ServicePricing servicePricing) {
        this.servicePricing = servicePricing;
    }

    public Service getService() {
        if(service == null){
            service = new Service();
        }
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Planning getPlanning() {
        if(planning == null){
            planning = new Planning();
        }
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    public Manager getManagerConfirmation() {
        if(managerConfirmation == null){
            managerConfirmation = new Manager();
        }
        return managerConfirmation;
    }

    public void setManagerConfirmation(Manager managerConfirmation) {
        this.managerConfirmation = managerConfirmation;
    }

    public Date getDateConfirmation() {
        return dateConfirmation;
    }

    public void setDateConfirmation(Date dateConfirmation) {
        this.dateConfirmation = dateConfirmation;
    }

    public Date getDateDernierModif() {
        return dateDernierModif;
    }

    public void setDateDernierModif(Date dateDernierModif) {
        this.dateDernierModif = dateDernierModif;
    }

    public Date getDateSuppression() {
        return dateSuppression;
    }

    public void setDateSuppression(Date dateSuppression) {
        this.dateSuppression = dateSuppression;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DemandeService)) {
            return false;
        }
        DemandeService other = (DemandeService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.DemandeService[ id=" + id + " ]";
    }

}
