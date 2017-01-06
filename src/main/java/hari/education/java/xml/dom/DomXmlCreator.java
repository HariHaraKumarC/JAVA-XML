package hari.education.java.xml.dom;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import hari.education.java.pojo.Student;
import hari.education.java.xml.constants.XmlConstants;
import hari.education.java.xml.sax.SaxStudentHandler;

public class DomXmlCreator {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		//XML-POJO using SAX Parser
		String fileName=XmlConstants.XML_FILES_DIRECTORY+"ns_students.xml";
		String domOutputFile=XmlConstants.XML_FILES_DIRECTORY+"students.xml";
		SaxStudentHandler saxHandler=new SaxStudentHandler();
		List<Student> studentList=saxHandler.readXmlData(fileName);
		DomCreator creator=new DomCreator();
		Document doc=creator.createXmlDoc(studentList);
		outputToString(doc);
		outputToFile(doc,domOutputFile);
		//Root Node
		/*Node root=doc.getFirstChild();
		System.out.println("Root Element: " +root.getNodeName());*/
		//Child elements of root
		/*NodeList childNodeList=root.getChildNodes();
		for (int i = 0; i < childNodeList.getLength(); i++) {
			Node child=childNodeList.item(i);
			System.out.println("Child Element of the root: " +child.getNodeName());
		}*/
	}

	private static void outputToString(Document doc) throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		DOMSource source=new DOMSource(doc);
		StringWriter writer=new StringWriter();
		StreamResult result=new StreamResult(writer);
		Transformer transformer = getTransformer();
		transformer.transform(source, result);
		String xml=writer.toString();
		System.out.println(xml);
	}
	
	private static void outputToFile(Document doc, String fileName) throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError{
		DOMSource source=new DOMSource(doc);
		StreamResult result=new StreamResult(new File(fileName));
		getTransformer().transform(source, result);
	}
	private static Transformer getTransformer() throws TransformerFactoryConfigurationError, TransformerConfigurationException {
		TransformerFactory factory=TransformerFactory.newInstance();
		Transformer transformer=factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
		return transformer;
	}

}
