/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author DELL
 */
@Entity
public class Voiture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private VoitureModele modele;
    @ManyToOne
    private Worker worker;
    @ManyToOne
    private Carburant carburant;
    private int quantite;

    @OneToMany(mappedBy = "voiture")
    private List<VoitureImage> imageVoitures;

    public Voiture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public VoitureModele getModele() {
        if (modele == null) {
            modele = new VoitureModele();
        }
        return modele;
    }

    public void setModele(VoitureModele modele) {
        this.modele = modele;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public List<VoitureImage> getImageVoitures() {
        return imageVoitures;
    }

    public void setImageVoitures(List<VoitureImage> imageVoitures) {
        this.imageVoitures = imageVoitures;
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
        if (!(object instanceof Voiture)) {
            return false;
        }
        Voiture other = (Voiture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Voiture[ id=" + id + " ]";
    }

}
