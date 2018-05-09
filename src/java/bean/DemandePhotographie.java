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
 * @author Boss
 */
@Entity
public class DemandePhotographie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private PhotographieType typePhotographie;
    private Boolean videographie;
    @ManyToOne
    private DemandeService demandeService;

    public DemandeService getDemandeService() {
        return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    public DemandePhotographie() {
    }

    public PhotographieType getTypePhotographie() {
        if (typePhotographie == null) {
            typePhotographie = new PhotographieType();
        }
        return typePhotographie;
    }

    public void setTypePhotographie(PhotographieType typePhotographie) {
        this.typePhotographie = typePhotographie;
    }

    public Boolean getVideographie() {
        return videographie;
    }

    public void setVideographie(Boolean videographie) {
        this.videographie = videographie;
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
        if (!(object instanceof DemandePhotographie)) {
            return false;
        }
        DemandePhotographie other = (DemandePhotographie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.DemandePhotographie[ id=" + id + " ]";
    }

}
