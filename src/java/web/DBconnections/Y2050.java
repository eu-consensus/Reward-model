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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anu
 */
@Entity
@Table(name = "y2050")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Y2050.findAll", query = "SELECT y FROM Y2050 y"),
    @NamedQuery(name = "Y2050.findById", query = "SELECT y FROM Y2050 y WHERE y.id = :id"),
    @NamedQuery(name = "Y2050.findByPolicy", query = "SELECT y FROM Y2050 y WHERE y.policy = :policy"),
    @NamedQuery(name = "Y2050.findByBiodiv", query = "SELECT y FROM Y2050 y WHERE y.biodiv = :biodiv"),
    @NamedQuery(name = "Y2050.findByCo2", query = "SELECT y FROM Y2050 y WHERE y.co2 = :co2"),
    @NamedQuery(name = "Y2050.findByCostfood", query = "SELECT y FROM Y2050 y WHERE y.costfood = :costfood"),
    @NamedQuery(name = "Y2050.findByForestland", query = "SELECT y FROM Y2050 y WHERE y.forestland = :forestland"),
    @NamedQuery(name = "Y2050.findByDistance", query = "SELECT y FROM Y2050 y WHERE y.distance = :distance"),
    @NamedQuery(name = "Y2050.findByDominatedbycategory", query = "SELECT y FROM Y2050 y WHERE y.dominatedbycategory = :dominatedbycategory"),
    @NamedQuery(name = "Y2050.findByDominatedbypool", query = "SELECT y FROM Y2050 y WHERE y.dominatedbypool = :dominatedbypool"),
    @NamedQuery(name = "Y2050.findByMyorder", query = "SELECT y FROM Y2050 y WHERE y.myorder = :myorder"),
    @NamedQuery(name = "Y2050.findByChosen", query = "SELECT y FROM Y2050 y WHERE y.chosen = :chosen"),
    @NamedQuery(name = "Y2050.findByLiked", query = "SELECT y FROM Y2050 y WHERE y.liked = :liked"),
    @NamedQuery(name = "Y2050.findByScore", query = "SELECT y FROM Y2050 y WHERE y.score = :score")})
public class Y2050 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "policy")
    private String policy;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "biodiv")
    private Double biodiv;
    @Column(name = "co2")
    private Double co2;
    @Column(name = "costfood")
    private Double costfood;
    @Column(name = "forestland")
    private Double forestland;
    @Column(name = "distance")
    private Double distance;
    @Column(name = "dominatedbycategory")
    private Integer dominatedbycategory;
    @Column(name = "dominatedbypool")
    private Integer dominatedbypool;
    @Size(max = 6)
    @Column(name = "myorder")
    private String myorder;
    @Column(name = "chosen")
    private Integer chosen;
    @Column(name = "liked")
    private Integer liked;
    @Column(name = "score")
    private Double score;

    public Y2050() {
    }

    public Y2050(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public Double getBiodiv() {
        return biodiv;
    }

    public void setBiodiv(Double biodiv) {
        this.biodiv = biodiv;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getCostfood() {
        return costfood;
    }

    public void setCostfood(Double costfood) {
        this.costfood = costfood;
    }

    public Double getForestland() {
        return forestland;
    }

    public void setForestland(Double forestland) {
        this.forestland = forestland;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getDominatedbycategory() {
        return dominatedbycategory;
    }

    public void setDominatedbycategory(Integer dominatedbycategory) {
        this.dominatedbycategory = dominatedbycategory;
    }

    public Integer getDominatedbypool() {
        return dominatedbypool;
    }

    public void setDominatedbypool(Integer dominatedbypool) {
        this.dominatedbypool = dominatedbypool;
    }

    public String getMyorder() {
        return myorder;
    }

    public void setMyorder(String myorder) {
        this.myorder = myorder;
    }

    public Integer getChosen() {
        return chosen;
    }

    public void setChosen(Integer chosen) {
        this.chosen = chosen;
    }

    public Integer getLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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
        if (!(object instanceof Y2050)) {
            return false;
        }
        Y2050 other = (Y2050) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.DBconnections.Y2050[ id=" + id + " ]";
    }
    
}
