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

/**
 *
 * @author Boss
 */
@Entity
public class ChildDemandeBabySitting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int age;
    @ManyToOne
    private DemandeBabySitting demandeBabySitting;

    public DemandeBabySitting getDemandeBabySitting() {
        return demandeBabySitting;
    }

    public void setDemandeBabySitting(DemandeBabySitting demandeBabySitting) {
        this.demandeBabySitting = demandeBabySitting;
    }

    
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        if (!(object instanceof ChildDemandeBabySitting)) {
            return false;
        }
        ChildDemandeBabySitting other = (ChildDemandeBabySitting) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.ChildDemandeBabySitting[ id=" + id + " ]";
    }
    
}
