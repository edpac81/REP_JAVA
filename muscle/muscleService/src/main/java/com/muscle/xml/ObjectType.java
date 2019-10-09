//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.03.08 a las 08:20:55 AM CST 
//


package com.muscle.xml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ObjectType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="ObjectType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="*PGM"/>
 *     &lt;enumeration value="*MODULE"/>
 *     &lt;enumeration value="*SRVPGM"/>
 *     &lt;enumeration value="*FILE"/>
 *     &lt;enumeration value="*DTAARA"/>
 *     &lt;enumeration value="*NONE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ObjectType")
@XmlEnum
public enum ObjectType {

    @XmlEnumValue("*PGM")
    PGM("*PGM"),
    @XmlEnumValue("*MODULE")
    MODULE("*MODULE"),
    @XmlEnumValue("*SRVPGM")
    SRVPGM("*SRVPGM"),
    @XmlEnumValue("*FILE")
    FILE("*FILE"),
    @XmlEnumValue("*DTAARA")
    DTAARA("*DTAARA"),
    @XmlEnumValue("*NONE")
    NONE("*NONE");
    private final String value;

    ObjectType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ObjectType fromValue(String v) {
        for (ObjectType c: ObjectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
