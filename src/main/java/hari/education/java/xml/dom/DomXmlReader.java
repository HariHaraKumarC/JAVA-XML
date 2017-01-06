package hari.education.java.xml.dom;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import hari.education.java.pojo.Student;
import hari.education.java.xml.constants.XmlConstants;

public class DomXmlReader {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ParseException, XPathExpressionException {
		String fileNameNs=XmlConstants.XML_FILES_DIRECTORY+"ns_students.xml";
		String fileName=XmlConstants.XML_FILES_DIRECTORY+"students.xml";
		DomReader domReader=new DomReader();
		List<Student> studentList=domReader.readXmlData(fileNameNs);
		System.out.println("Number of Total Student is:"+studentList.size());
		for (Student student : studentList) {
			System.out.println(student);
		}
		//With Xpath Expression
		DomReaderWithXPath domXpathReader=new DomReaderWithXPath();
		List<Student> filteredStudList=domXpathReader.readXmlData(fileName,"//student[age>=20]");
		System.out.println("Number of Students whose age >=20 is:"+filteredStudList.size());
		for (Student student : filteredStudList) {
			System.out.println(student);
		}
	}

}
