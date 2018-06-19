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
import javax.persistence.Temporal;

/**
 *
 * @author Ashen One
 */
@Entity
public class DemandeVoitureItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private DemandeVoiture demandeVoiture;
    @ManyToOne
    private Carburant carburant;
    @ManyToOne
    private Worker worker;
    @ManyToOne
    private VoitureModele modele;
    private BigDecimal prix;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebut;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFin;
    private int quantite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DemandeVoiture getDemandeVoiture() {
        if(demandeVoiture == null){
            demandeVoiture = new DemandeVoiture();
        }
        return demandeVoiture;
    }

    public void setDemandeVoiture(DemandeVoiture demandeVoiture) {
        this.demandeVoiture = demandeVoiture;
    }

    public Carburant getCarburant() {
        if(carburant == null){
            carburant = new Carburant();
        }
        return carburant;
    }

    public void setCarburant(Carburant carburant) {
        this.carburant = carburant;
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

    public VoitureModele getModele() {
        if(modele == null){
            modele = new VoitureModele();
        }
        return modele;
    }

    public void setModele(VoitureModele modele) {
        this.modele = modele;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
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
        if (!(object instanceof DemandeVoitureItem)) {
            return false;
        }
        DemandeVoitureItem other = (DemandeVoitureItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.DemandeVoitureItem[ id=" + id + " ]";
    }
    
}
