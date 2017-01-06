package hari.education.java.xml.dom;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import hari.education.java.pojo.Student;

public class DomReader {
	public List<Student> data;
	public String dateFormat="dd-MM-yyyy";
	public String NAMESPACE_URI="http://www.w3.org/students";
	
	public List<Student> readXmlData(String fileName) throws ParserConfigurationException, SAXException, IOException, ParseException{
		data=new ArrayList<Student>();
		DateFormat df=new SimpleDateFormat(dateFormat);
		Document doc=null;
		File xmlFile=new File(fileName);
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder=factory.newDocumentBuilder();
		doc=builder.parse(xmlFile);
		NodeList studentList=doc.getElementsByTagNameNS(NAMESPACE_URI,"student");
		for (int i = 0; i < studentList.getLength(); i++) {
			Student student=new Student();
			data.add(student);
			Element studElement= (Element) studentList.item(i);
			String idAsString=studElement.getAttribute(Student.ID);
			student.setId(Integer.parseInt(idAsString));
			student.setName(getElementValue(studElement,Student.NAME));
			student.setAge(Integer.parseInt(getElementValue(studElement,Student.AGE)));
			student.setDob(df.parse(getElementValue(studElement,Student.DOB)));
			student.setComments(getElementValue(studElement,Student.ABOUT));
		}
		return data;
	}

	private String getElementValue(Element parent, String childElementName ) {
		Element childElement=(Element) parent.getElementsByTagNameNS(NAMESPACE_URI,childElementName).item(0);
		String content=childElement.getTextContent();
		return content;
	}
}
