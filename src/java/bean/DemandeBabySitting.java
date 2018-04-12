/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Boss
 */
@Entity
public class DemandeBabySitting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private DemandeService demandeService;
    private BigDecimal nbrHeures;
    @OneToMany(mappedBy = "demandeBabySitting")
    private List<ChildDemandeBabySitting> childs;
    private String detail;
    private Boolean fulltime;

    public Boolean getFulltime() {
        return fulltime;
    }

    public void setFulltime(Boolean fulltime) {
        this.fulltime = fulltime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public DemandeBabySitting() {
    }

    public DemandeBabySitting(BigDecimal nbrHeures) {
        this.nbrHeures = nbrHeures;
    }

    public DemandeBabySitting(DemandeService demandeService, BigDecimal nbrHeures, String detail) {
        this.demandeService = demandeService;
        this.nbrHeures = nbrHeures;
        this.detail = detail;
    }

    public DemandeService getDemandeService() {
        return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    public DemandeBabySitting(DemandeService demandeService, BigDecimal nbrHeures) {
        this.demandeService = demandeService;
        this.nbrHeures = nbrHeures;
    }

    public BigDecimal getNbrHeures() {
        return nbrHeures;
    }

    public void setNbrHeures(BigDecimal nbrHeures) {
        this.nbrHeures = nbrHeures;
    }

    public Long getId() {
        return id;
    }

    public List<ChildDemandeBabySitting> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildDemandeBabySitting> childs) {
        this.childs = childs;
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
        if (!(object instanceof DemandeBabySitting)) {
            return false;
        }
        DemandeBabySitting other = (DemandeBabySitting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DemandeBabySitting{" + "id=" + id + ", nbrHeures=" + nbrHeures + ", detail=" + detail + ", fulltime=" + fulltime + '}';
    }

   }
