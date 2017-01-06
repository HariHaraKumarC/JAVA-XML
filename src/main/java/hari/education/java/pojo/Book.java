package hari.education.java.pojo;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder={"name","author","language","publisher","publicationDate","version","comments"})
public class Book {
	private int id;
	private String name;
	private String author;
	private String language;
	private String publisher;
	private Date publicationDate;
	private int version;
	private String comments;
	
	//Constants used for Consistency Purpose
	public static final String 
		DATE_FORMAT="dd-MM-yyyy",
		ID="id",
		NAME="name",
		AUTHOR="author",
		LANGUAGE="language",
		PUBLISHER="publisher",
		DOP="dop",
		VOP="vop",
		ABOUT="about";
	
	@XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		//DateFormat df=new SimpleDateFormat(DATE_FORMAT);
		String bookDetails="Id:"+ Integer.toString(this.id) + "; Name:" +this.name;
		bookDetails+="; Author:"+ this.author+ "; Language:" +this.language;
		bookDetails+="; Publisher:"+ this.publisher;//+ "; PublicateDate:" +df.format(this.publicationDate);
		bookDetails+="; Version:"+ Integer.toString(this.version)+ "; Comments:" +this.comments;
		return bookDetails;
	}
}
