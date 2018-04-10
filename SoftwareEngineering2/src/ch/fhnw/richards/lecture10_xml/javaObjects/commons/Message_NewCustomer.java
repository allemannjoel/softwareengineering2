package ch.fhnw.richards.lecture10_xml.javaObjects.commons;

import java.io.Serializable;

public class Message_NewCustomer extends Message implements Serializable {
	private static final long serialVersionUID = 1; // This is version 1
    private String name;
    private Integer age;
    
    public Message_NewCustomer() {
    	super();
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", name='" + this.name + "', age=" + age;
	}
}
