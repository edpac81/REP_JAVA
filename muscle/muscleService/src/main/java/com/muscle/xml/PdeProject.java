//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.03.08 a las 08:20:55 AM CST 
//


package com.muscle.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PdeProject complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PdeProject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="projectNumber" type="{}PositiveInteger"/>
 *         &lt;element name="avpNumber" type="{}PositiveInteger"/>
 *         &lt;element name="sourceNumber">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[0-9]{3}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="items" type="{}ItemList"/>
 *         &lt;element name="projectLibraryList" type="{}LibraryList" minOccurs="0"/>
 *         &lt;element name="buildOrder" type="{}PositiveInteger" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PdeProject", propOrder = {

})
public class PdeProject implements Comparable<PdeProject>{

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected int projectNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected int avpNumber;
    @XmlElement(required = true)
    protected String sourceNumber;
    protected String description;
    @XmlElement(required = true)
    protected ItemList items;
    protected LibraryList projectLibraryList;
    @XmlSchemaType(name = "positiveInteger")
    protected int buildOrder;

    /**
     * Obtiene el valor de la propiedad projectNumber.
     * 
     * @return
     *     possible object is
     *     {@link int }
     *     
     */
    public int getProjectNumber() {
        return projectNumber;
    }

    /**
     * Define el valor de la propiedad projectNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link int }
     *     
     */
    public void setProjectNumber(int value) {
        this.projectNumber = value;
    }

    /**
     * Obtiene el valor de la propiedad avpNumber.
     * 
     * @return
     *     possible object is
     *     {@link int }
     *     
     */
    public int getAvpNumber() {
        return avpNumber;
    }

    /**
     * Define el valor de la propiedad avpNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link int }
     *     
     */
    public void setAvpNumber(int value) {
        this.avpNumber = value;
    }

    /**
     * Obtiene el valor de la propiedad sourceNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceNumber() {
        return sourceNumber;
    }

    /**
     * Define el valor de la propiedad sourceNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceNumber(String value) {
        this.sourceNumber = value;
    }

    /**
     * Obtiene el valor de la propiedad description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define el valor de la propiedad description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obtiene el valor de la propiedad items.
     * 
     * @return
     *     possible object is
     *     {@link ItemList }
     *     
     */
    public ItemList getItems() {
        return items;
    }

    /**
     * Define el valor de la propiedad items.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemList }
     *     
     */
    public void setItems(ItemList value) {
        this.items = value;
    }

    /**
     * Obtiene el valor de la propiedad projectLibraryList.
     * 
     * @return
     *     possible object is
     *     {@link LibraryList }
     *     
     */
    public LibraryList getProjectLibraryList() {
        return projectLibraryList;
    }

    /**
     * Define el valor de la propiedad projectLibraryList.
     * 
     * @param value
     *     allowed object is
     *     {@link LibraryList }
     *     
     */
    public void setProjectLibraryList(LibraryList value) {
        this.projectLibraryList = value;
    }

    /**
     * Obtiene el valor de la propiedad buildOrder.
     * 
     * @return
     *     possible object is
     *     {@link int }
     *     
     */
    public int getBuildOrder() {
        return buildOrder;
    }

    /**
     * Define el valor de la propiedad buildOrder.
     * 
     * @param value
     *     allowed object is
     *     {@link int }
     *     
     */
    public void setBuildOrder(int value) {
        this.buildOrder = value;
    }

		@Override
		public int compareTo(PdeProject o) {
			if(this.buildOrder < o.getBuildOrder()){
				return -1;
			}
			if(this.buildOrder > o.getBuildOrder()){
				return 1;
			}
			return 0;
		}

		@Override
		public String toString() {
			return "PdeProject [projectNumber=" + projectNumber + ", avpNumber=" + avpNumber + ", "
					+ (sourceNumber != null ? "sourceNumber=" + sourceNumber + ", " : "")
					+ (description != null ? "description=" + description + ", " : "") + "buildOrder=" + buildOrder + "]";
		}

}
