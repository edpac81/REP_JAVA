//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.03.08 a las 08:20:55 AM CST 
//


package com.muscle.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * <p>Clase Java para ProjectModel complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ProjectModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="release" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="analist" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="systemUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="scrumTeam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="build" type="{}Build" minOccurs="0"/>
 *         &lt;element name="test" type="{}Test" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
public class ProjectModel {

    protected String release;
    protected String analist;
    protected String systemUser;
    protected String scrumTeam;
    protected Build build;
    protected Test test;

    /**
     * Obtiene el valor de la propiedad release.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @XmlElement(required = true)
    public String getRelease() {
        return release;
    }

    /**
     * Define el valor de la propiedad release.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelease(String value) {
        this.release = value;
    }

    /**
     * Obtiene el valor de la propiedad analist.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @XmlElement(required = true)
    public String getAnalist() {
        return analist;
    }

    /**
     * Define el valor de la propiedad analist.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnalist(String value) {
        this.analist = value;
    }

    /**
     * Obtiene el valor de la propiedad systemUser.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @XmlElement(required = true)
    public String getSystemUser() {
        return systemUser;
    }

    /**
     * Define el valor de la propiedad systemUser.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemUser(String value) {
        this.systemUser = value;
    }

    /**
     * Obtiene el valor de la propiedad scrumTeam.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScrumTeam() {
        return scrumTeam;
    }

    /**
     * Define el valor de la propiedad scrumTeam.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScrumTeam(String value) {
        this.scrumTeam = value;
    }

    /**
     * Obtiene el valor de la propiedad build.
     * 
     * @return
     *     possible object is
     *     {@link Build }
     *     
     */
    public Build getBuild() {
        return build;
    }

    /**
     * Define el valor de la propiedad build.
     * 
     * @param value
     *     allowed object is
     *     {@link Build }
     *     
     */
    public void setBuild(Build value) {
        this.build = value;
    }

    /**
     * Obtiene el valor de la propiedad test.
     * 
     * @return
     *     possible object is
     *     {@link Test }
     *     
     */
    public Test getTest() {
        return test;
    }

    /**
     * Define el valor de la propiedad test.
     * 
     * @param value
     *     allowed object is
     *     {@link Test }
     *     
     */
    public void setTest(Test value) {
        this.test = value;
    }

}
