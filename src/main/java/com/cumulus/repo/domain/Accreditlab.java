package com.cumulus.repo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Accreditlab.
 */
@Entity
@Table(name = "T_ACCREDITLAB")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Accreditlab implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String Name;

    @Column(name = "url")
    private String Url;

    @OneToMany(mappedBy = "accreditlab")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Cm> cms = new HashSet<>();

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public Set<Cm> getCms() {
        return cms;
    }

    public void setCms(Set<Cm> cms) {
        this.cms = cms;
    }

    public User getuser() {
        return user;
    }

    public void setuser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Accreditlab accreditlab = (Accreditlab) o;

        if (id != null ? !id.equals(accreditlab.id) : accreditlab.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Accreditlab{" +
                "id=" + id +
                ", Name='" + Name + "'" +
                ", Url='" + Url + "'" +
                '}';
    }
}
