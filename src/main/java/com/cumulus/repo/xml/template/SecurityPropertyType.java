//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.04.07 alle 04:45:53 PM CEST 
//

package com.cumulus.repo.xml.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per securityPropertyType complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * <pre>
 * &lt;complexType name="securityPropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sProperty" type="{http://www.cumulus.org/certificate/model/test}propertyType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SecurityPropertyDefinition" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "securityPropertyType", propOrder = { "sProperty" })
public class SecurityPropertyType {

	@XmlElement(required = true)
	protected PropertyType sProperty;
	@XmlAttribute(name = "SecurityPropertyDefinition", required = true)
	protected String securityPropertyDefinition;

	/**
	 * Recupera il valore della proprietà sProperty.
	 * 
	 * @return possible object is {@link PropertyType }
	 * 
	 */
	public PropertyType getSProperty() {
		return sProperty;
	}

	/**
	 * Imposta il valore della proprietà sProperty.
	 * 
	 * @param value
	 *            allowed object is {@link PropertyType }
	 * 
	 */
	public void setSProperty(PropertyType value) {
		this.sProperty = value;
	}

	/**
	 * Recupera il valore della proprietà securityPropertyDefinition.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSecurityPropertyDefinition() {
		return securityPropertyDefinition;
	}

	/**
	 * Imposta il valore della proprietà securityPropertyDefinition.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSecurityPropertyDefinition(String value) {
		this.securityPropertyDefinition = value;
	}

}
