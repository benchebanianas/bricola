/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Boss
 */
@Entity
public class DemandeHandyMan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal nbrHeures;
    private BigDecimal nbrHandyMan;
    @ManyToOne
    private Service service;
    @ManyToOne
    private DemandeService demandeService;

    public DemandeService getDemandeService() {
        return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getNbrHeures() {
        if(nbrHeures == null){
            nbrHeures = new BigDecimal(0);
        }
        return nbrHeures;
    }

    public void setNbrHeures(BigDecimal nbrHeures) {
        this.nbrHeures = nbrHeures;
    }

    public BigDecimal getNbrHandyMan() {
        if(nbrHandyMan == null){
            nbrHandyMan = new BigDecimal(0);
        }
        return nbrHandyMan;
    }

    public void setNbrHandyMan(BigDecimal nbrHandyMan) {
        this.nbrHandyMan = nbrHandyMan;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
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
        if (!(object instanceof DemandeHandyMan)) {
            return false;
        }
        DemandeHandyMan other = (DemandeHandyMan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.DemandeHandyMan[ id=" + id + " ]";
    }

}
