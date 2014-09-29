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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anu
 */
@Entity
@Table(name = "preference_order100")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreferenceOrder100.findAll", query = "SELECT p FROM PreferenceOrder100 p"),
    @NamedQuery(name = "PreferenceOrder100.findById", query = "SELECT p FROM PreferenceOrder100 p WHERE p.id = :id"),
    @NamedQuery(name = "PreferenceOrder100.findByPref1", query = "SELECT p FROM PreferenceOrder100 p WHERE p.pref1 = :pref1"),
    @NamedQuery(name = "PreferenceOrder100.findByPlaces", query = "SELECT p FROM PreferenceOrder100 p WHERE p.places = :places")})
public class PreferenceOrder100 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "pref1")
    private String pref1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "places")
    private String places;

    public PreferenceOrder100() {
    }

    public PreferenceOrder100(Integer id) {
        this.id = id;
    }

    public PreferenceOrder100(Integer id, String pref1, String places) {
        this.id = id;
        this.pref1 = pref1;
        this.places = places;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPref1() {
        return pref1;
    }

    public void setPref1(String pref1) {
        this.pref1 = pref1;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
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
        if (!(object instanceof PreferenceOrder100)) {
            return false;
        }
        PreferenceOrder100 other = (PreferenceOrder100) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.DBconnections.PreferenceOrder100[ id=" + id + " ]";
    }
    
}
