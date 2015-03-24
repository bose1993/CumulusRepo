//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.03.19 alle 02:09:45 PM CET 
//


package com.cumulus.repo.xml.template;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cumulus.certificate.model.test package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TestBasedCertifcationModel_QNAME = new QName("http://www.cumulus.org/certificate/model/test", "testBasedCertifcationModel");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cumulus.certificate.model.test
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PropertyType }
     * 
     */
    public PropertyType createPropertyType() {
        return new PropertyType();
    }

    /**
     * Create an instance of {@link PropertyType.PropertyParameterList }
     * 
     */
    public PropertyType.PropertyParameterList createPropertyTypePropertyParameterList() {
        return new PropertyType.PropertyParameterList();
    }

    /**
     * Create an instance of {@link PropertyType.PropertyPerformance }
     * 
     */
    public PropertyType.PropertyPerformance createPropertyTypePropertyPerformance() {
        return new PropertyType.PropertyPerformance();
    }

    /**
     * Create an instance of {@link PropertyType.PropertyPerformance.PropertyPerformanceRow }
     * 
     */
    public PropertyType.PropertyPerformance.PropertyPerformanceRow createPropertyTypePropertyPerformancePropertyPerformanceRow() {
        return new PropertyType.PropertyPerformance.PropertyPerformanceRow();
    }

    /**
     * Create an instance of {@link CapabilityType }
     * 
     */
    public CapabilityType createCapabilityType() {
        return new CapabilityType();
    }

    /**
     * Create an instance of {@link AbstracCollectorType }
     * 
     */
    public AbstracCollectorType createAbstracCollectorType() {
        return new AbstracCollectorType();
    }

    /**
     * Create an instance of {@link TestCertificationModel }
     * 
     */
    public TestCertificationModel createTestCertificationModel() {
        return new TestCertificationModel();
    }

    /**
     * Create an instance of {@link CollectorType }
     * 
     */
    public CollectorType createCollectorType() {
        return new CollectorType();
    }

    /**
     * Create an instance of {@link TestInstanceType }
     * 
     */
    public TestInstanceType createTestInstanceType() {
        return new TestInstanceType();
    }

    /**
     * Create an instance of {@link SecurityPropertyType }
     * 
     */
    public SecurityPropertyType createSecurityPropertyType() {
        return new SecurityPropertyType();
    }

    /**
     * Create an instance of {@link TocType }
     * 
     */
    public TocType createTocType() {
        return new TocType();
    }

    /**
     * Create an instance of {@link ActionabilityType }
     * 
     */
    public ActionabilityType createActionabilityType() {
        return new ActionabilityType();
    }

    /**
     * Create an instance of {@link SignatureType }
     * 
     */
    public SignatureType createSignatureType() {
        return new SignatureType();
    }

    /**
     * Create an instance of {@link TestCaseType }
     * 
     */
    public TestCaseType createTestCaseType() {
        return new TestCaseType();
    }

    /**
     * Create an instance of {@link TestAttributeType }
     * 
     */
    public TestAttributeType createTestAttributeType() {
        return new TestAttributeType();
    }

    /**
     * Create an instance of {@link PropertyType.PropertyParameterList.PropertyParameter }
     * 
     */
    public PropertyType.PropertyParameterList.PropertyParameter createPropertyTypePropertyParameterListPropertyParameter() {
        return new PropertyType.PropertyParameterList.PropertyParameter();
    }

    /**
     * Create an instance of {@link PropertyType.PropertyPerformance.PropertyPerformanceRow.PropertyPerformanceCell }
     * 
     */
    public PropertyType.PropertyPerformance.PropertyPerformanceRow.PropertyPerformanceCell createPropertyTypePropertyPerformancePropertyPerformanceRowPropertyPerformanceCell() {
        return new PropertyType.PropertyPerformance.PropertyPerformanceRow.PropertyPerformanceCell();
    }

    /**
     * Create an instance of {@link CapabilityType.AttackerCapabilities }
     * 
     */
    public CapabilityType.AttackerCapabilities createCapabilityTypeAttackerCapabilities() {
        return new CapabilityType.AttackerCapabilities();
    }

    /**
     * Create an instance of {@link AbstracCollectorType.TestAttributes }
     * 
     */
    public AbstracCollectorType.TestAttributes createAbstracCollectorTypeTestAttributes() {
        return new AbstracCollectorType.TestAttributes();
    }

    /**
     * Create an instance of {@link AbstracCollectorType.TestCases }
     * 
     */
    public AbstracCollectorType.TestCases createAbstracCollectorTypeTestCases() {
        return new AbstracCollectorType.TestCases();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestCertificationModel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.cumulus.org/certificate/model/test", name = "testBasedCertifcationModel")
    public JAXBElement<TestCertificationModel> createTestBasedCertifcationModel(TestCertificationModel value) {
        return new JAXBElement<TestCertificationModel>(_TestBasedCertifcationModel_QNAME, TestCertificationModel.class, null, value);
    }

}
