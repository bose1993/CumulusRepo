//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.03.19 alle 02:09:45 PM CET 
//

package com.cumulus.repo.xml.template;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per tocType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="tocType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CloudLayer" maxOccurs="unbounded">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="SaaS"/>
 *               &lt;enumeration value="PaaS"/>
 *               &lt;enumeration value="IaaS"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ConcreteToc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TocDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tocType", propOrder = {
    "cloudLayer",
    "concreteToc",
    "tocDescription"
})
public class TocType {

    @XmlElement(name = "CloudLayer", required = true)
    protected List<String> cloudLayer;
    @XmlElement(name = "ConcreteToc", required = true)
    protected String concreteToc;
    @XmlElement(name = "TocDescription", required = true)
    protected String tocDescription;

    /**
     * Gets the value of the cloudLayer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cloudLayer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCloudLayer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCloudLayer() {
        if (cloudLayer == null) {
            cloudLayer = new ArrayList<String>();
        }
        return this.cloudLayer;
    }

    /**
     * Recupera il valore della proprietà concreteToc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConcreteToc() {
        return concreteToc;
    }

    /**
     * Imposta il valore della proprietà concreteToc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConcreteToc(String value) {
        this.concreteToc = value;
    }

    /**
     * Recupera il valore della proprietà tocDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTocDescription() {
        return tocDescription;
    }

    /**
     * Imposta il valore della proprietà tocDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTocDescription(String value) {
        this.tocDescription = value;
    }

}
