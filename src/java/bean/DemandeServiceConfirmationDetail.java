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
import javax.persistence.Temporal;

/**
 *
 * @author Boss
 */
@Entity
public class DemandeServiceConfirmationDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Manager manager;
    @ManyToOne
    private TypeAction typeAction;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAction;
    @ManyToOne
    private DemandeService demandeService;

    public DemandeServiceConfirmationDetail() {
    }
    public DemandeServiceConfirmationDetail(Manager manager, TypeAction typeAction, DemandeService demandeService) {
        this.manager = manager;
        this.typeAction = typeAction;
        this.demandeService = demandeService;
        this.dateAction = new Date();
    }
    
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public TypeAction getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(TypeAction typeAction) {
        this.typeAction = typeAction;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DemandeServiceConfirmationDetail)) {
            return false;
        }
        DemandeServiceConfirmationDetail other = (DemandeServiceConfirmationDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DemandeServiceConfirmationDetail{" + "id=" + id + ", manager=" + manager.getLogin() + ", typeAction=" + typeAction + ", dateAction=" + dateAction + '}';
    }


}
