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
public class DemandePestControl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String detail;
    @ManyToOne
    private DemandeService demandeService;
    @OneToOne
    private PestControlType typeOfPestControl;

    public DemandePestControl() {
    }

    public DemandePestControl(String detail, DemandeService demandeService, PestControlType typeOfPestControl) {
        this.detail = detail;
        this.demandeService = demandeService;
        this.typeOfPestControl = typeOfPestControl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public DemandeService getDemandeService() {
        return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    public PestControlType getTypeOfPestControl() {
        return typeOfPestControl;
    }

    public void setTypeOfPestControl(PestControlType typeOfPestControl) {
        this.typeOfPestControl = typeOfPestControl;
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
        if (!(object instanceof DemandePestControl)) {
            return false;
        }
        DemandePestControl other = (DemandePestControl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.DemandePestControl[ id=" + id + " ]";
    }

}
