/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.dataimporttool.matchingschema;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.openmrs.module.dataimporttool.matchingschema package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openmrs.module.dataimporttool.matchingschema
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
