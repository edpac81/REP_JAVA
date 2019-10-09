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
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para Build complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Build">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="currentLibrary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bindingDirectory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="libraryList" type="{}LibraryList" minOccurs="0"/>
 *         &lt;element name="pdeProyectList" type="{}PdeProyectList"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Build", propOrder = {

})
public class Build {

	@XmlElement(required = true)
	protected String currentLibrary;
	@XmlElement(required = true)
	protected String bindingDirectory;
	protected LibraryList libraryList;
	@XmlElement(required = true)
	protected PdeProyectList pdeProyectList;

	/**
	 * Obtiene el valor de la propiedad currentLibrary.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCurrentLibrary() {
		return currentLibrary;
	}

	/**
	 * Define el valor de la propiedad currentLibrary.
	 * 
	 * @param value
	 *          allowed object is {@link String }
	 * 
	 */
	public void setCurrentLibrary(String value) {
		this.currentLibrary = value;
	}

	/**
	 * Obtiene el valor de la propiedad bindingDirectory.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBindingDirectory() {
		return bindingDirectory;
	}

	/**
	 * Define el valor de la propiedad bindingDirectory.
	 * 
	 * @param value
	 *          allowed object is {@link String }
	 * 
	 */
	public void setBindingDirectory(String value) {
		this.bindingDirectory = value;
	}

	/**
	 * Obtiene el valor de la propiedad libraryList.
	 * 
	 * @return possible object is {@link LibraryList }
	 * 
	 */
	public LibraryList getLibraryList() {
		return libraryList;
	}

	/**
	 * Define el valor de la propiedad libraryList.
	 * 
	 * @param value
	 *          allowed object is {@link LibraryList }
	 * 
	 */
	public void setLibraryList(LibraryList value) {
		this.libraryList = value;
	}

	/**
	 * Obtiene el valor de la propiedad pdeProyectList.
	 * 
	 * @return possible object is {@link PdeProyectList }
	 * 
	 */
	public PdeProyectList getPdeProyectList() {
		return pdeProyectList;
	}

	/**
	 * Define el valor de la propiedad pdeProyectList.
	 * 
	 * @param value
	 *          allowed object is {@link PdeProyectList }
	 * 
	 */
	public void setPdeProyectList(PdeProyectList value) {
		this.pdeProyectList = value;
	}

}
