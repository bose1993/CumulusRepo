//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.03.19 alle 02:09:45 PM CET 
//



package com.cumulus.repo.xml.template;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TestCertificationModel complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TestCertificationModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CertificationModelTemplateID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Collectors" type="{http://www.cumulus.org/certificate/model/test}CollectorType"/>
 *         &lt;element name="Signature" type="{http://www.cumulus.org/certificate/model/test}signatureType"/>
 *         &lt;element name="ToC" type="{http://www.cumulus.org/certificate/model/test}tocType"/>
 *         &lt;element name="SecurityProperty" type="{http://www.cumulus.org/certificate/model/test}securityPropertyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestCertificationModel", propOrder = {
    "certificationModelTemplateID",
    "collectors",
    "signature",
    "toC",
    "securityProperty"
})
@XmlRootElement()
public class TestCertificationModel {

    @XmlElement(name = "CertificationModelTemplateID", required = true)
    protected String certificationModelTemplateID;
    @XmlElement(name = "Collectors", required = true)
    protected CollectorType collectors;
    @XmlElement(name = "Signature", required = true)
    protected SignatureType signature;
    @XmlElement(name = "ToC", required = true)
    protected TocType toC;
    @XmlElement(name = "SecurityProperty", required = true)
    protected SecurityPropertyType securityProperty;

    /**
     * Recupera il valore della proprietà certificationModelTemplateID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificationModelTemplateID() {
        return certificationModelTemplateID;
    }

    /**
     * Imposta il valore della proprietà certificationModelTemplateID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificationModelTemplateID(String value) {
        this.certificationModelTemplateID = value;
    }

    /**
     * Recupera il valore della proprietà collectors.
     * 
     * @return
     *     possible object is
     *     {@link CollectorType }
     *     
     */
    public CollectorType getCollectors() {
        return collectors;
    }

    /**
     * Imposta il valore della proprietà collectors.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectorType }
     *     
     */
    public void setCollectors(CollectorType value) {
        this.collectors = value;
    }

    /**
     * Recupera il valore della proprietà signature.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Imposta il valore della proprietà signature.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

    /**
     * Recupera il valore della proprietà toC.
     * 
     * @return
     *     possible object is
     *     {@link TocType }
     *     
     */
    public TocType getToC() {
        return toC;
    }

    /**
     * Imposta il valore della proprietà toC.
     * 
     * @param value
     *     allowed object is
     *     {@link TocType }
     *     
     */
    public void setToC(TocType value) {
        this.toC = value;
    }

    /**
     * Recupera il valore della proprietà securityProperty.
     * 
     * @return
     *     possible object is
     *     {@link SecurityPropertyType }
     *     
     */
    public SecurityPropertyType getSecurityProperty() {
        return securityProperty;
    }

    /**
     * Imposta il valore della proprietà securityProperty.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityPropertyType }
     *     
     */
    public void setSecurityProperty(SecurityPropertyType value) {
        this.securityProperty = value;
    }

}
