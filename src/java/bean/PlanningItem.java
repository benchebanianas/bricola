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

/**
 *
 * @author Boss
 */
@Entity
public class PlanningItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Day day;
    private int numeroJour; // soit choisis day + timing + repetition (tout les lundi a 9h) ou bien  date + timing + rep (tout les 24 de chaque mois)
    @ManyToOne
    private Planning planning;
    @ManyToOne
    private Timing timing;
    private int repetition; // 1: par semaine, 2: par mois

    public Timing getTiming() {
        return timing;
    }
    
    public void setTiming(Timing timing) {
        this.timing = timing;
    }

    public int getNumeroJour() {
        return numeroJour;
    }

    public void setNumeroJour(int numeroJour) {
        this.numeroJour = numeroJour;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }
    

    
    public PlanningItem() {
    }

    public PlanningItem(Day day, Planning planning) {
        this.day = day;
        this.planning = planning;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    public Long getId() {
        if(id == null){
            id = new Long(0);
        }
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
        if (!(object instanceof PlanningItem)) {
            return false;
        }
        PlanningItem other = (PlanningItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlanningItem{" + "id=" + id + ", day=" + day + ", numeroJour=" + numeroJour + ", planning=" + planning + ", timing=" + timing + ", repetition=" + repetition + '}';
    }

    
}
