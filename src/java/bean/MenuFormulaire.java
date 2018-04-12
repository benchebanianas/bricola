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
public class MenuFormulaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private TypeDemande typeDemande;
    private boolean infoTab;
    private boolean companyTab;
    private boolean detailsTab;
    private boolean summaryTab;
    private boolean payementTab;
    private String imageName;

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    
    public boolean isInfoTab() {
        return infoTab;
    }

    public void setInfoTab(boolean infoTab) {
        this.infoTab = infoTab;
    }

    public boolean isCompanyTab() {
        return companyTab;
    }

    public void setCompanyTab(boolean companyTab) {
        this.companyTab = companyTab;
    }

    public boolean isDetailsTab() {
        return detailsTab;
    }

    public void setDetailsTab(boolean detailsTab) {
        this.detailsTab = detailsTab;
    }

    public boolean isSummaryTab() {
        return summaryTab;
    }

    public void setSummaryTab(boolean summaryTab) {
        this.summaryTab = summaryTab;
    }

    public boolean isPayementTab() {
        return payementTab;
    }

    public void setPayementTab(boolean payementTab) {
        this.payementTab = payementTab;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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
        if (!(object instanceof MenuFormulaire)) {
            return false;
        }
        MenuFormulaire other = (MenuFormulaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MenuFormulaire{" + "id=" + id + ", typeDemande=" + typeDemande.getId() + ", infoTab=" + infoTab +
                ", companyTab=" + companyTab + ", detailsTab=" + detailsTab + ", summaryTab=" + summaryTab +
                ", payementTab=" + payementTab + ", imageName=" + imageName + '}';
    }

    
    
}
