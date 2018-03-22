/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Boss
 */
@Entity
public class Planning implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebut;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFin;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOnce;
    @OneToMany(mappedBy = "planning")
    private List<PlanningItem> planningItems;
    @ManyToOne
    private Timing timing;

    public Timing getTiming() {
        return timing;
    }

    public void setTiming(Timing timing) {
        this.timing = timing;
    }
    

    public Planning() {
    }

    public Planning(Date dateDebut, Date dateFin, Date dateOnce) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dateOnce = dateOnce;
    }

    public List<PlanningItem> getPlanningItems() {
        if(planningItems == null){
            planningItems = new ArrayList<>();
        }
        return planningItems;
    }

    public void setPlanningItems(List<PlanningItem> planningItems) {
        this.planningItems = planningItems;
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

    public Date getDateOnce() {
        return dateOnce;
    }

    public void setDateOnce(Date dateOnce) {
        this.dateOnce = dateOnce;
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
        if (!(object instanceof Planning)) {
            return false;
        }
        Planning other = (Planning) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Planning[ id=" + id + " ]";
    }

}
