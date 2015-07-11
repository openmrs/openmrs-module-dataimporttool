package org.openmrs.module.dataimporttool.matchingschema;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.esaude.matchingschema package. 
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
@SuppressWarnings("restriction")
@XmlRegistry
public class ObjectFactory {

    private final static QName _Tuple_QNAME = new QName("http://esaude.org/matchingschema", "tuple");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.esaude.matchingschema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TupleType }
     * 
     */
    public TupleType createTupleType() {
        return new TupleType();
    }

    /**
     * Create an instance of {@link ReferenceType }
     * 
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link ReferenceSideType }
     * 
     */
    public ReferenceSideType createReferenceSideType() {
        return new ReferenceSideType();
    }

    /**
     * Create an instance of {@link MatchType }
     * 
     */
    public MatchType createMatchType() {
        return new MatchType();
    }

    /**
     * Create an instance of {@link MatchSideType }
     * 
     */
    public MatchSideType createMatchSideType() {
        return new MatchSideType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TupleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://esaude.org/matchingschema", name = "tuple")
    public JAXBElement<TupleType> createTuple(TupleType value) {
        return new JAXBElement<TupleType>(_Tuple_QNAME, TupleType.class, null, value);
    }

}
