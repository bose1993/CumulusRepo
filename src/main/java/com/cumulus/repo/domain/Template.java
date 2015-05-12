package com.cumulus.repo.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Template.
 */
@Entity
@Table(name = "T_TEMPLATE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Template implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "xml")
	private String XML;

	@Column(name = "version", precision = 10, scale = 2)
	private BigDecimal version;

	@Column(name = "master")
	private Boolean master;

	@Column(name = "xml_id")
	private String xmlid;

	@Column(name = "creation_timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date creation_timestammp;

	@ManyToOne
	private Toc toc;

	@ManyToOne
	private Property property;

	@ManyToOne
	private User user;

	public Long getId() {
		return id;
	}

	@OneToMany(mappedBy = "template")
	// @JsonIgnore
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<Cm> cms = new HashSet<>();

	public void setId(Long id) {
		this.id = id;
	}

	public String getXML() {
		return XML;
	}

	public void setXML(String XML) {
		this.XML = XML;
	}

	public BigDecimal getVersion() {
		return version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public Boolean getMaster() {
		return master;
	}

	public void setMaster(Boolean master) {
		this.master = master;
	}

	public String getXmlId() {
		return xmlid;
	}

	public void setXmlId(String xmlid) {
		this.xmlid = xmlid;
	}

	public Toc getToc() {
		return toc;
	}

	public void setToc(Toc toc) {
		this.toc = toc;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public Date getCreationeTimestamp() {
		return this.creation_timestammp;
	}

	@JsonIgnore
	public void setAttributes(Set<Cm> propertyattributes) {
		this.cms = propertyattributes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Template template = (Template) o;

		if (id != null ? !id.equals(template.id) : template.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "Template{" + "id=" + id + ", XML='" + XML + "'" + ", version='"
				+ version + "'" + ", master='" + master + "'" + ", xmlid='"
				+ xmlid + "'" + '}';
	}
}
