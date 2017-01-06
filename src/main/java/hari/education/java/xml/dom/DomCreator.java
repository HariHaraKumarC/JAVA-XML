package hari.education.java.xml.dom;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import hari.education.java.pojo.Student;

public class DomCreator {
	Document doc;
	public String DATE_FORMAT="dd-MM-yyyy";
	public Document createXmlDoc(List<Student> studentList) throws ParserConfigurationException {
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		doc=builder.newDocument();
		Element root=doc.createElement("students");
		doc.appendChild(root);
		for (Student student : studentList) {
			Element studElement = addElement(root,"student","");
			String idAsString=Integer.toString(student.getId());
			String ageAsString=Integer.toString(student.getAge());
			studElement.setAttribute(Student.ID,idAsString);
			addElement(studElement,Student.NAME,student.getName());
			addElement(studElement,Student.AGE,ageAsString);
			DateFormat df=new SimpleDateFormat(DATE_FORMAT);
			addElement(studElement,Student.DOB,df.format(student.getDob()));
			Element aboutElement=addElement(studElement,Student.ABOUT,"");
			CDATASection comments=doc.createCDATASection(student.getComments());
			aboutElement.appendChild(comments);
		}
		return doc;
	}
	
	private Element addElement(Element parent, String elementName, String elementValue) {
		Element childElement=doc.createElement(elementName);
		childElement.setTextContent(elementValue);
		parent.appendChild(childElement);
		return childElement;
	}

}
