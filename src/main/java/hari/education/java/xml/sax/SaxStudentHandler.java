package hari.education.java.xml.sax;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import hari.education.java.pojo.Student;

public class SaxStudentHandler extends DefaultHandler{
	
	public List<Student> data;
	public String currentElement="";
	public Student student;
	public StringBuilder currentText;
	public String dateFormat="dd-MM-yyyy";
	
	public List<Student> readXmlData(String fileName) throws SAXException, IOException, ParserConfigurationException{
		SAXParserFactory factory=SAXParserFactory.newInstance(); 
		factory.setNamespaceAware(true);
		SAXParser parser=factory.newSAXParser();
		parser.parse(new File(fileName), this);
		return data;
	}
	
	@Override
	public void startDocument() throws SAXException {
		//System.out.println("Start Document");
		data=new ArrayList<>();
	}
	
	@Override
	public void endDocument() throws SAXException {
		//System.out.println("End Document");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//System.out.println("Start Element: "+qName);
		currentElement=localName;
		switch (currentElement) {
		case "students":
			break;
			
		case "student":
			student=new Student();
			String idAsString=attributes.getValue(Student.ID);
			student.setId(Integer.parseInt(idAsString));
			data.add(student);
			break;
			
		default:
			currentText=new StringBuilder();
			break;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//System.out.println("End Element: "+qName);
		if(currentElement.equals("students") || currentElement.equals("student") ){
			return;
		}
		String content=currentText.toString();
		switch (currentElement) {
		case Student.NAME:
			student.setName(content);
			break;
			
		case Student.AGE:
			student.setAge(Integer.parseInt(content));
			break;
			
		case Student.DOB:
			DateFormat df=new SimpleDateFormat(dateFormat);
			try {
				student.setDob(df.parse(content));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
			
		case Student.ABOUT:
			student.setComments(content);
			break;

		default:
			break;
		}
		currentElement="";
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		//System.out.println("Character");
		if(currentText != null){
			currentText.append(ch, start, length);
			//System.out.println("CurrentText:" +currentText.toString());
		}
		
	}
	
	@Override
	public void warning(SAXParseException e) throws SAXException {
		System.out.println("Warning");
	}
	
	@Override
	public void error(SAXParseException e) throws SAXException {
		System.out.println("Error");
	}
	
	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println("Fatal Error");
	}

}
