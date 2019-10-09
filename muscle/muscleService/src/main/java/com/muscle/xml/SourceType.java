//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.03.08 a las 08:20:55 AM CST 
//


package com.muscle.xml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SourceType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="SourceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SQLRPGLE"/>
 *     &lt;enumeration value="RPGLE"/>
 *     &lt;enumeration value="CLLE"/>
 *     &lt;enumeration value="SQL"/>
 *     &lt;enumeration value="BND"/>
 *     &lt;enumeration value="LF"/>
 *     &lt;enumeration value="TXT"/>
 *     &lt;enumeration value="DSPF"/>
 *     &lt;enumeration value="DTAARA"/>
 *     &lt;enumeration value="PRTF"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SourceType")
@XmlEnum
public enum SourceType {

    SQLRPGLE,
    RPGLE,
    CLLE,
    SQL,
    BND,
    LF,
    TXT,
    DSPF,
    DTAARA,
    PRTF;

    public String value() {
        return name();
    }

    public static SourceType fromValue(String v) {
        return valueOf(v);
    }

}
