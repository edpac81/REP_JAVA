package com.tester.xml;

import com.tester.model.RequestKeys;
import com.tester.model.RequestParameters;

import java.io.StringWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ServiceRequestBuilder
{
  private Document xmlDocument;
  private RequestKeys serviceKeys;
  private RequestParameters serviceParameters;
  
  public ServiceRequestBuilder(Document xmlDoc, RequestKeys srvKeys, RequestParameters srvTestCase)
  {
    this.xmlDocument = xmlDoc;
    this.serviceKeys = srvKeys;
    this.serviceParameters = srvTestCase;
  }
  
  public void builXmlRequest() {
    for (int i = 0; i < this.serviceKeys.size(); i++) {
      String tagName = (String)this.serviceKeys.get(i);
      String tagValue = (String)this.serviceParameters.get(tagName);
      Node wNode = this.xmlDocument.getElementsByTagName(tagName).item(0);
      Element wElem = (Element)wNode;
      wElem.setTextContent(tagValue.trim());
    }
  }
  
  public String getXmlRequest() throws Exception
  {
    String xmlString = null;
    
    Source source = new DOMSource(this.xmlDocument);
    StringWriter stringWriter = new StringWriter();
    Result result = new StreamResult(stringWriter);
    TransformerFactory factory = TransformerFactory.newInstance();
    
    try
    {
      Transformer transformer = factory.newTransformer();
      transformer.transform(source, result);
    } catch (TransformerException e) {
      throw e; }
    xmlString = stringWriter.getBuffer().toString();
    return xmlString;
  }
}