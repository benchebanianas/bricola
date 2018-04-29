/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
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
public class DemandeMoving implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne
    private DemandeService demandeService;
    private Long id;
    @ManyToOne
    private Ville villeDepart;
    private String adresseDepart;
    @ManyToOne
    private Ville villeArrive;
    private String adresseArrive;
    private Boolean storage;
    private Boolean handyman;

    public DemandeMoving() {
    }

    public DemandeMoving(Ville villeDepart, String adresseDepart, Ville villeArrive, String adresseArrive, Boolean storage, Boolean handyman) {
        this.villeDepart = villeDepart;
        this.adresseDepart = adresseDepart;
        this.villeArrive = villeArrive;
        this.adresseArrive = adresseArrive;
        this.storage = storage;
        this.handyman = handyman;
    }

    public DemandeService getDemandeService() {
         if (demandeService == null) {
            demandeService = new DemandeService();
        }
        return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    public Ville getVilleDepart() {
         if (villeDepart == null) {
            villeDepart = new Ville();
        }
        return villeDepart;
    }

    public void setVilleDepart(Ville villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getAdresseDepart() {
        return adresseDepart;
    }

    public void setAdresseDepart(String adresseDepart) {
        this.adresseDepart = adresseDepart;
    }

    public Ville getVilleArrive() {
        if (villeArrive == null) {
            villeArrive = new Ville();
        }
        return villeArrive;
    }

    public void setVilleArrive(Ville villeArrive) {
        this.villeArrive = villeArrive;
    }

    public String getAdresseArrive() {
        return adresseArrive;
    }

    public void setAdresseArrive(String adresseArrive) {
        this.adresseArrive = adresseArrive;
    }

    public Boolean getStorage() {
        return storage;
    }

    public void setStorage(Boolean storage) {
        this.storage = storage;
    }

    public Boolean getHandyman() {
        return handyman;
    }

    public void setHandyman(Boolean handyman) {
        this.handyman = handyman;
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
        if (!(object instanceof DemandeMoving)) {
            return false;
        }
        DemandeMoving other = (DemandeMoving) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.DemandeMoving[ id=" + id + " ]";
    }

}
