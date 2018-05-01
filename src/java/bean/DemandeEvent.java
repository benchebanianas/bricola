/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
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
 * @author Boss
 */
@Entity
public class DemandeEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private EventType eventType;
    private int nbrInvites;
    @ManyToOne
    private DemandeService demandeService;
    @ManyToOne
    private EventBudget eventBudget;
    @OneToMany(mappedBy = "demandeEvent")
    private List<CuisineDemandeEvent> cuisineDemandeEvents;
    @OneToMany(mappedBy = "demandeEvent")
    private List<SupplementDemandeEvent> supplementDemandeEvents;

    public DemandeService getDemandeService() {
        if (demandeService == null) {
            demandeService = new DemandeService();
        }
        return demandeService;
    }

    public void setDemandeService(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    public List<CuisineDemandeEvent> getCuisineDemandeEvents() {
        if (cuisineDemandeEvents == null) {
            cuisineDemandeEvents = new ArrayList();
        }
        return cuisineDemandeEvents;
    }

    public EventBudget getEventBudget() {
        if (eventBudget == null) {
            eventBudget = new EventBudget();
        }
        return eventBudget;
    }

    public void setEventBudget(EventBudget eventBudget) {
        this.eventBudget = eventBudget;
    }

    public void setCuisineDemandeEvents(List<CuisineDemandeEvent> cuisineDemandeEvents) {
        this.cuisineDemandeEvents = cuisineDemandeEvents;
    }

    public List<SupplementDemandeEvent> getSupplementDemandeEvents() {
        if (supplementDemandeEvents == null) {
            cuisineDemandeEvents = new ArrayList();
        }
        return supplementDemandeEvents;
    }

    public void setSupplementDemandeEvents(List<SupplementDemandeEvent> supplementDemandeEvents) {
        this.supplementDemandeEvents = supplementDemandeEvents;
    }

    public DemandeEvent() {
    }

    public EventType getEventType() {
        if (eventType == null) {
            eventType = new EventType();
        }
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getNbrInvites() {
        return nbrInvites;
    }

    public void setNbrInvites(int nbrInvites) {
        this.nbrInvites = nbrInvites;
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
        if (!(object instanceof DemandeEvent)) {
            return false;
        }
        DemandeEvent other = (DemandeEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.DemandeEvent[ id=" + id + " ]";
    }

}
