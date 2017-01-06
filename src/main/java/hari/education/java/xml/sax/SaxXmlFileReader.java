package hari.education.java.xml.sax;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import hari.education.java.pojo.Student;
import hari.education.java.xml.constants.XmlConstants;

public class SaxXmlFileReader {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		String fileName=XmlConstants.XML_FILES_DIRECTORY+"ns_students.xml";
		SaxStudentHandler saxHandler=new SaxStudentHandler();
		List<Student> studentList=saxHandler.readXmlData(fileName);
		System.out.println("Number of Students is:"+studentList.size());
		for (Student student : studentList) {
			System.out.println(student);
		}
	}

}
