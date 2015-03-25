package com.cumulus.repo.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Property.
 */
@Entity
@Table(name = "T_PROPERTY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Property implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "rules")
	private String rules;

	@OneToMany(mappedBy = "property")
	@JsonIgnore
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<Template> template_rs = new HashSet<>();

	@OneToMany(mappedBy = "property")
	// @JsonIgnore
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<PropertyAttribute> attribute_rs = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	@JsonIgnore
	public Set<Template> getTemplate_rs() {
		return template_rs;
	}

	@JsonIgnore
	public void setTemplate_rs(Set<Template> templates) {
		this.template_rs = templates;
	}

	@JsonIgnore
	public Set<PropertyAttribute> getAttributes() {
		return attribute_rs;
	}

	@JsonIgnore
	public void setAttributes(Set<PropertyAttribute> propertyattributes) {
		this.attribute_rs = propertyattributes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Property property = (Property) o;

		if (id != null ? !id.equals(property.id) : property.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "Property{" + "id=" + id + ", rules='" + rules + "'" + '}';
	}
}
