/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.DBconnections;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anu
 */
@Entity
@Table(name = "preference")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preference.findAll", query = "SELECT p FROM Preference p"),
    @NamedQuery(name = "Preference.findById", query = "SELECT p FROM Preference p WHERE p.id = :id"),
    @NamedQuery(name = "Preference.findByBiodivStart", query = "SELECT p FROM Preference p WHERE p.biodivStart = :biodivStart"),
    @NamedQuery(name = "Preference.findByBiodivEnd", query = "SELECT p FROM Preference p WHERE p.biodivEnd = :biodivEnd"),
    @NamedQuery(name = "Preference.findByBiodivValue", query = "SELECT p FROM Preference p WHERE p.biodivValue = :biodivValue"),
    @NamedQuery(name = "Preference.findByCo2Start", query = "SELECT p FROM Preference p WHERE p.co2Start = :co2Start"),
    @NamedQuery(name = "Preference.findByCo2End", query = "SELECT p FROM Preference p WHERE p.co2End = :co2End"),
    @NamedQuery(name = "Preference.findByCo2Value", query = "SELECT p FROM Preference p WHERE p.co2Value = :co2Value"),
    @NamedQuery(name = "Preference.findByCostfoodStart", query = "SELECT p FROM Preference p WHERE p.costfoodStart = :costfoodStart"),
    @NamedQuery(name = "Preference.findByCostfoodEnd", query = "SELECT p FROM Preference p WHERE p.costfoodEnd = :costfoodEnd"),
    @NamedQuery(name = "Preference.findByCostfoodValue", query = "SELECT p FROM Preference p WHERE p.costfoodValue = :costfoodValue"),
    @NamedQuery(name = "Preference.findByForestlandStart", query = "SELECT p FROM Preference p WHERE p.forestlandStart = :forestlandStart"),
    @NamedQuery(name = "Preference.findByForestlandEnd", query = "SELECT p FROM Preference p WHERE p.forestlandEnd = :forestlandEnd"),
    @NamedQuery(name = "Preference.findByForestlandValue", query = "SELECT p FROM Preference p WHERE p.forestlandValue = :forestlandValue")})
public class Preference implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "biodiv_start")
    private double biodivStart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "biodiv_end")
    private double biodivEnd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "biodiv_value")
    private double biodivValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "co2_start")
    private double co2Start;
    @Basic(optional = false)
    @NotNull
    @Column(name = "co2_end")
    private double co2End;
    @Basic(optional = false)
    @NotNull
    @Column(name = "co2_value")
    private double co2Value;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costfood_start")
    private double costfoodStart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costfood_end")
    private double costfoodEnd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costfood_value")
    private double costfoodValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "forestland_start")
    private double forestlandStart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "forestland_end")
    private double forestlandEnd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "forestland_value")
    private double forestlandValue;

    public Preference() {
    }

    public Preference(Integer id) {
        this.id = id;
    }

    public Preference(Integer id, double biodivStart, double biodivEnd, double biodivValue, double co2Start, double co2End, double co2Value, double costfoodStart, double costfoodEnd, double costfoodValue, double forestlandStart, double forestlandEnd, double forestlandValue) {
        this.id = id;
        this.biodivStart = biodivStart;
        this.biodivEnd = biodivEnd;
        this.biodivValue = biodivValue;
        this.co2Start = co2Start;
        this.co2End = co2End;
        this.co2Value = co2Value;
        this.costfoodStart = costfoodStart;
        this.costfoodEnd = costfoodEnd;
        this.costfoodValue = costfoodValue;
        this.forestlandStart = forestlandStart;
        this.forestlandEnd = forestlandEnd;
        this.forestlandValue = forestlandValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getBiodivStart() {
        return biodivStart;
    }

    public void setBiodivStart(double biodivStart) {
        this.biodivStart = biodivStart;
    }

    public double getBiodivEnd() {
        return biodivEnd;
    }

    public void setBiodivEnd(double biodivEnd) {
        this.biodivEnd = biodivEnd;
    }

    public double getBiodivValue() {
        return biodivValue;
    }

    public void setBiodivValue(double biodivValue) {
        this.biodivValue = biodivValue;
    }

    public double getCo2Start() {
        return co2Start;
    }

    public void setCo2Start(double co2Start) {
        this.co2Start = co2Start;
    }

    public double getCo2End() {
        return co2End;
    }

    public void setCo2End(double co2End) {
        this.co2End = co2End;
    }

    public double getCo2Value() {
        return co2Value;
    }

    public void setCo2Value(double co2Value) {
        this.co2Value = co2Value;
    }

    public double getCostfoodStart() {
        return costfoodStart;
    }

    public void setCostfoodStart(double costfoodStart) {
        this.costfoodStart = costfoodStart;
    }

    public double getCostfoodEnd() {
        return costfoodEnd;
    }

    public void setCostfoodEnd(double costfoodEnd) {
        this.costfoodEnd = costfoodEnd;
    }

    public double getCostfoodValue() {
        return costfoodValue;
    }

    public void setCostfoodValue(double costfoodValue) {
        this.costfoodValue = costfoodValue;
    }

    public double getForestlandStart() {
        return forestlandStart;
    }

    public void setForestlandStart(double forestlandStart) {
        this.forestlandStart = forestlandStart;
    }

    public double getForestlandEnd() {
        return forestlandEnd;
    }

    public void setForestlandEnd(double forestlandEnd) {
        this.forestlandEnd = forestlandEnd;
    }

    public double getForestlandValue() {
        return forestlandValue;
    }

    public void setForestlandValue(double forestlandValue) {
        this.forestlandValue = forestlandValue;
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
        if (!(object instanceof Preference)) {
            return false;
        }
        Preference other = (Preference) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.DBconnections.Preference[ id=" + id + " ]";
    }
    
}
