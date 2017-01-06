package hari.education.java.pojo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements Serializable {
	// If the class implements Serializable interface then it is always good to declare the default serialVersionUid
	private static final long serialVersionUID = 3L;
	private String name;
	private int age;
	private boolean isMale;
	private int id;
	private Date dob;
	private String comments;
	
	//Constants used for consistency
	public static final String 
		DATE_FORMAT="dd-MM-yyyy",
		NAME="name",
		AGE="age",
		ID="id",
		DOB="dob",
		ABOUT="about";
	 
	public Student(){
		
	}
	
	public Student(String name,int age,boolean isMale){
		this.name=name;
		this.age=age;
		this.isMale=isMale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Override
	public String toString() {
		DateFormat df=new SimpleDateFormat(DATE_FORMAT);
		String studentDetails="Id:"+ Integer.toString(this.id) + "; Name:" +this.name;
		studentDetails+="; Age:"+ Integer.toString(this.age);
		studentDetails+="; Dob:"+ df.format(this.dob)+ "; About:" +this.comments;
		return studentDetails;
	}

}
