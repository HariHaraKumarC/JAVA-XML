package hari.education.java.xml.jaxb;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import hari.education.java.pojo.Book;
import hari.education.java.pojo.Books;
import hari.education.java.xml.constants.XmlConstants;

public class JaxbXmlCreator {
	public static String fileName=XmlConstants.XML_FILES_DIRECTORY+"books.xml";
	public static String DATE_FORMAT="dd-MM-yyyy";
	public static void main(String[] args) throws 
		ParserConfigurationException, 
		SAXException, 
		IOException, 
		ParseException, 
		JAXBException {
		List<Book> bookList=readXmlData();
		/*for (Book book : bookList) {
			System.out.println(book);
		}*/
		Books books=new Books();
		books.setBook(bookList);
		JAXBContext context=JAXBContext.newInstance(Books.class);
		Marshaller marshaller=context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter writer= new StringWriter();
		marshaller.marshal(books, writer);
		System.out.println(writer.toString());
	}
	
	//Read XML Data using DOM
	private static List<Book> readXmlData() throws 
		ParserConfigurationException, 
		SAXException, 
		IOException, 
		ParseException {
		List<Book> books=new ArrayList<Book>();
		Book book;
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document doc=builder.parse(new File(fileName)); 
		NodeList bookList=doc.getElementsByTagName("book");
		DateFormat df=new SimpleDateFormat(DATE_FORMAT);
		for (int i = 0; i < bookList.getLength(); i++) {
			book=new Book();
			Element bookElement=(Element) bookList.item(i);
			String idAsString=bookElement.getAttribute(Book.ID);
			book.setId(Integer.parseInt(idAsString));
			book.setName(getElementValue(bookElement,Book.NAME));
			book.setAuthor(getElementValue(bookElement,Book.AUTHOR));
			book.setLanguage(getElementValue(bookElement,Book.LANGUAGE));
			book.setPublisher(getElementValue(bookElement,Book.PUBLISHER));
			book.setPublicationDate(df.parse(getElementValue(bookElement,Book.DOP)));
			book.setVersion(Integer.parseInt(getElementValue(bookElement,Book.VOP)));
			book.setComments(getElementValue(bookElement,Book.ABOUT));
			books.add(book);
		}
		return books;
	}

	private static String getElementValue(Element parent, String childElementName) {
		Element child=(Element) parent.getElementsByTagName(childElementName).item(0);
		String content=child.getTextContent();
		return content;
	}

}
