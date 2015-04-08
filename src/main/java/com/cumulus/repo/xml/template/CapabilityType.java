//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.04.07 alle 04:45:53 PM CEST 
//

package com.cumulus.repo.xml.template;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per CapabilityType complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * <pre>
 * &lt;complexType name="CapabilityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Attacker" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AttackName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AttackerCapabilities" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CapabilityId" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="AttackerCapability" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CapabilityType", propOrder = { "attacker", "attackName",
		"attackerCapabilities" })
public class CapabilityType {

	@XmlElement(name = "Attacker", required = true)
	protected String attacker;
	@XmlElement(name = "AttackName", required = true)
	protected String attackName;
	@XmlElement(name = "AttackerCapabilities", required = true)
	protected List<CapabilityType.AttackerCapabilities> attackerCapabilities;

	/**
	 * Recupera il valore della proprietà attacker.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAttacker() {
		return attacker;
	}

	/**
	 * Imposta il valore della proprietà attacker.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAttacker(String value) {
		this.attacker = value;
	}

	/**
	 * Recupera il valore della proprietà attackName.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAttackName() {
		return attackName;
	}

	/**
	 * Imposta il valore della proprietà attackName.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAttackName(String value) {
		this.attackName = value;
	}

	/**
	 * Gets the value of the attackerCapabilities property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the attackerCapabilities property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAttackerCapabilities().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link CapabilityType.AttackerCapabilities }
	 * 
	 * 
	 */
	public List<CapabilityType.AttackerCapabilities> getAttackerCapabilities() {
		if (attackerCapabilities == null) {
			attackerCapabilities = new ArrayList<CapabilityType.AttackerCapabilities>();
		}
		return this.attackerCapabilities;
	}

	/**
	 * <p>
	 * Classe Java per anonymous complex type.
	 * 
	 * <p>
	 * Il seguente frammento di schema specifica il contenuto previsto contenuto
	 * in questa classe.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="CapabilityId" type="{http://www.w3.org/2001/XMLSchema}integer"/>
	 *         &lt;element name="AttackerCapability" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "capabilityId", "attackerCapability" })
	public static class AttackerCapabilities {

		@XmlElement(name = "CapabilityId", required = true)
		protected BigInteger capabilityId;
		@XmlElement(name = "AttackerCapability", required = true)
		protected String attackerCapability;

		/**
		 * Recupera il valore della proprietà capabilityId.
		 * 
		 * @return possible object is {@link BigInteger }
		 * 
		 */
		public BigInteger getCapabilityId() {
			return capabilityId;
		}

		/**
		 * Imposta il valore della proprietà capabilityId.
		 * 
		 * @param value
		 *            allowed object is {@link BigInteger }
		 * 
		 */
		public void setCapabilityId(BigInteger value) {
			this.capabilityId = value;
		}

		/**
		 * Recupera il valore della proprietà attackerCapability.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getAttackerCapability() {
			return attackerCapability;
		}

		/**
		 * Imposta il valore della proprietà attackerCapability.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setAttackerCapability(String value) {
			this.attackerCapability = value;
		}

	}

}
