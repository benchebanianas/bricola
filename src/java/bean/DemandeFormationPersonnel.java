/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author abdellzz
 */
@Entity
public class DemandeFormationPersonnel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int nbrpersonne;
    private Boolean adomicile;
    @OneToOne
    private DemandeService demandeService;
    @ManyToOne
    private Matiere matiere;

    public DemandeFormationPersonnel() {
    }

    public DemandeFormationPersonnel(Long id) {
        this.id = id;
    }

    public DemandeFormationPersonnel(Long id, int nbrpersonne, int nbrepersonne, Boolean adomicile, DemandeService demandeService, Matiere matiere) {
        this.id = id;
        this.nbrpersonne = nbrpersonne;
        this.adomicile = adomicile;
        this.demandeService = demandeService;
        this.matiere = matiere;
    }

    public int getNbrpersonne() {
        return nbrpersonne;
    }

    public void setNbrpersonne(int nbrpersonne) {
        this.nbrpersonne = nbrpersonne;
    }

    public Boolean getAdomicile() {
        return adomicile;
    }

    public void setAdomicile(Boolean adomicile) {
        this.adomicile = adomicile;
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

    public Matiere getMatiere() {
        if (matiere == null) {
            matiere = new Matiere();
        }
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
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
        if (!(object instanceof DemandeFormationPersonnel)) {
            return false;
        }
        DemandeFormationPersonnel other = (DemandeFormationPersonnel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DemandeFormationPersonnel{" + "id=" + id + ", nbrpersonne=" + nbrpersonne + ", adomicile=" + adomicile + ", demandeService=" + demandeService + ", matiere=" + matiere + '}';
    }

}
