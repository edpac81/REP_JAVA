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
 * <p>
 * Clase Java para Item complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="memberName" type="{}AS400Name"/>
 *         &lt;element name="sourceFileName" type="{}AS400Name"/>
 *         &lt;element name="sourceType" type="{}SourceType"/>
 *         &lt;element name="objectType" type="{}ObjectType"/>
 *         &lt;element name="memberDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buildCommand" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "Item", propOrder = {

})
public class Item implements Comparable<Item> {

	@XmlElement(required = true)
	protected String memberName;
	@XmlElement(required = true)
	protected String sourceFileName;
	@XmlElement(required = true)
	@XmlSchemaType(name = "string")
	protected SourceType sourceType;
	@XmlElement(required = true)
	@XmlSchemaType(name = "string")
	protected ObjectType objectType;
	protected String memberDescription;
	protected String buildCommand;
	@XmlSchemaType(name = "positiveInteger")
	protected int buildOrder;
	protected String sourceFileLibrary;

	/**
	 * Obtiene el valor de la propiedad memberName.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * Define el valor de la propiedad memberName.
	 * 
	 * @param value
	 *          allowed object is {@link String }
	 * 
	 */
	public void setMemberName(String value) {
		this.memberName = value;
	}

	/**
	 * Obtiene el valor de la propiedad sourceFileName.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSourceFileName() {
		return sourceFileName;
	}

	/**
	 * Define el valor de la propiedad sourceFileName.
	 * 
	 * @param value
	 *          allowed object is {@link String }
	 * 
	 */
	public void setSourceFileName(String value) {
		this.sourceFileName = value;
	}

	/**
	 * Obtiene el valor de la propiedad sourceType.
	 * 
	 * @return possible object is {@link SourceType }
	 * 
	 */
	public SourceType getSourceType() {
		return sourceType;
	}

	/**
	 * Define el valor de la propiedad sourceType.
	 * 
	 * @param value
	 *          allowed object is {@link SourceType }
	 * 
	 */
	public void setSourceType(SourceType value) {
		this.sourceType = value;
	}

	/**
	 * Obtiene el valor de la propiedad objectType.
	 * 
	 * @return possible object is {@link ObjectType }
	 * 
	 */
	public ObjectType getObjectType() {
		return objectType;
	}

	/**
	 * Define el valor de la propiedad objectType.
	 * 
	 * @param value
	 *          allowed object is {@link ObjectType }
	 * 
	 */
	public void setObjectType(ObjectType value) {
		this.objectType = value;
	}

	/**
	 * Obtiene el valor de la propiedad memberDescription.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMemberDescription() {
		return memberDescription;
	}

	/**
	 * Define el valor de la propiedad memberDescription.
	 * 
	 * @param value
	 *          allowed object is {@link String }
	 * 
	 */
	public void setMemberDescription(String value) {
		this.memberDescription = value;
	}

	/**
	 * Obtiene el valor de la propiedad buildCommand.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBuildCommand() {
		return buildCommand;
	}

	/**
	 * Define el valor de la propiedad buildCommand.
	 * 
	 * @param value
	 *          allowed object is {@link String }
	 * 
	 */
	public void setBuildCommand(String value) {
		this.buildCommand = value;
	}

	/**
	 * Obtiene el valor de la propiedad buildOrder.
	 * 
	 * @return possible object is {@link int }
	 * 
	 */
	public int getBuildOrder() {
		return buildOrder;
	}

	/**
	 * Define el valor de la propiedad buildOrder.
	 * 
	 * @param value
	 *          allowed object is {@link int }
	 * 
	 */
	public void setBuildOrder(int value) {
		this.buildOrder = value;
	}

	@Override
	public int compareTo(Item o) {
		if (this.buildOrder < o.getBuildOrder()) {
			return -1;
		}
		if (this.buildOrder > o.getBuildOrder()) {
			return 1;
		}
		return 0;
	}

	public String getSourceFileLibrary() {
		return sourceFileLibrary;
	}

	public void setSourceFileLibrary(String sourceFileLibrary) {
		this.sourceFileLibrary = sourceFileLibrary;
	}

	@Override
	public String toString() {
		return "Item [" + (memberName != null ? "memberName=" + memberName + ", " : "")
				+ (sourceFileName != null ? "sourceFileName=" + sourceFileName + ", " : "")
				+ (sourceType != null ? "sourceType=" + sourceType + ", " : "")
				+ (objectType != null ? "objectType=" + objectType + ", " : "")
				+ (memberDescription != null ? "memberDescription=" + memberDescription + ", " : "")
				+ (buildCommand != null ? "buildCommand=" + buildCommand + ", " : "") + "buildOrder=" + buildOrder + ", "
				+ (sourceFileLibrary != null ? "sourceFileLibrary=" + sourceFileLibrary : "") + "]";
	}

}
