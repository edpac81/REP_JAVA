//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.03.08 a las 08:20:55 AM CST 
//


package com.muscle.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.muscle.xml package. 
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

    private final static QName _ProjectModel_QNAME = new QName("", "projectModel");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.muscle.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProjectModel }
     * 
     */
    public ProjectModel createProjectModel() {
        return new ProjectModel();
    }

    /**
     * Create an instance of {@link PdeProject }
     * 
     */
    public PdeProject createPdeProject() {
        return new PdeProject();
    }

    /**
     * Create an instance of {@link PdeProyectList }
     * 
     */
    public PdeProyectList createPdeProyectList() {
        return new PdeProyectList();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link Test }
     * 
     */
    public Test createTest() {
        return new Test();
    }

    /**
     * Create an instance of {@link Build }
     * 
     */
    public Build createBuild() {
        return new Build();
    }

    /**
     * Create an instance of {@link LibraryList }
     * 
     */
    public LibraryList createLibraryList() {
        return new LibraryList();
    }

    /**
     * Create an instance of {@link ItemList }
     * 
     */
    public ItemList createItemList() {
        return new ItemList();
    }

    /**
     * Create an instance of {@link Library }
     * 
     */
    public Library createLibrary() {
        return new Library();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProjectModel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "projectModel")
    public JAXBElement<ProjectModel> createProjectModel(ProjectModel value) {
        return new JAXBElement<ProjectModel>(_ProjectModel_QNAME, ProjectModel.class, null, value);
    }

}
