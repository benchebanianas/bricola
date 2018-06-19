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
public class DemandeCleaning implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private DemandeService demandeService;
    private BigDecimal nbrHeures;
    private Boolean bringEquipement = false;
    private BigDecimal nbrCleaner;

    public BigDecimal getNbrCleaner() {
        if(nbrCleaner == null){
            nbrCleaner = new BigDecimal(0);
        }
        return nbrCleaner;
    }

    public void setNbrCleaner(BigDecimal nbrCleaner) {
        this.nbrCleaner = nbrCleaner;
    }

    public DemandeCleaning() {
    }

    public DemandeCleaning(DemandeService demandeService, BigDecimal nbrHeures, Boolean bringEquipement, BigDecimal nbrCleaners) {
        this.demandeService = demandeService;
        this.nbrHeures = nbrHeures;
        this.bringEquipement = bringEquipement;
        this.nbrCleaner = nbrCleaners;
    }

    public DemandeService getDemandeService() {
        if(demandeService == null){
            demandeService = new DemandeService();
        }
        return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
        this.demandeService = demandeService;
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

   

    public Boolean getBringEquipement() {
        return bringEquipement;
    }

    public void setBringEquipement(Boolean bringEquipement) {
        this.bringEquipement = bringEquipement;
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
        if (!(object instanceof DemandeCleaning)) {
            return false;
        }
        DemandeCleaning other = (DemandeCleaning) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.DemandeCleaning[ id=" + id + " ]";
    }

}
