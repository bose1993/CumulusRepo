package com.cumulus.repo.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Cm.
 */
@Entity
@Table(name = "T_CM")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cm implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "xml")
	private String Xml;

	@Column(name = "cmid")
	private Long Cmid;

	@Column(name = "xmlid")
	private String Xmlid;

	@Column(name = "status")
	private String status;

	@Column(name = "version", precision = 10, scale = 2)
	private BigDecimal Version;

	@ManyToOne
	private Accreditlab accreditlab;

	@ManyToOne
	private Template template;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getXml() {
		return Xml;
	}

	public void setXml(String Xml) {
		this.Xml = Xml;
	}

	public Long getCmid() {
		return Cmid;
	}

	public void setCmid(Long Cmid) {
		this.Cmid = Cmid;
	}

	public String getXmlid() {
		return Xmlid;
	}

	public void setXmlid(String Xmlid) {
		this.Xmlid = Xmlid;
	}

	public BigDecimal getVersion() {
		return Version;
	}

	public void setVersion(BigDecimal Version) {
		this.Version = Version;
	}

	public Accreditlab getAccreditlab() {
		return accreditlab;
	}

	public void setAccreditlab(Accreditlab accreditlab) {
		this.accreditlab = accreditlab;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template Template) {
		this.template = Template;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Cm cm = (Cm) o;

		if (id != null ? !id.equals(cm.id) : cm.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "Cm{" + "id=" + id + ", Xml='" + Xml + "'" + ", Cmid='" + Cmid
				+ "'" + ", Xmlid='" + Xmlid + "'" + ", Version='" + Version
				+ "'" + '}';
	}
}
