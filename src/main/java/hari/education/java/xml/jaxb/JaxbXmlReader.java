package hari.education.java.xml.jaxb;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import hari.education.java.pojo.Book;
import hari.education.java.pojo.Books;
import hari.education.java.xml.constants.XmlConstants;

public class JaxbXmlReader {
	public static String fileName=XmlConstants.XML_FILES_DIRECTORY+"books.xml";
	public static void main(String[] args) throws JAXBException {
		JAXBContext context= JAXBContext.newInstance(Books.class);
		Unmarshaller unmarshaller=context.createUnmarshaller();
		Books books=(Books) unmarshaller.unmarshal(new File(fileName));
		List<Book> bookList=books.getBook();
		for (Book book : bookList) {
			System.out.println(book);
		}
	}

}
