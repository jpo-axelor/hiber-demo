package com.axelor.hiberDemo.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue
	Long id;

	String name;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "phone_id")
	private Phone phone;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Address> addr = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Department dept;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "subject_id")
	private List<Subject> sub = new ArrayList<>();

	public Student() {
	}

	public Student(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(Phone n) {
		phone = n;
	}

	public Phone getPhone() {
		return phone;
	}

	public List<Address> getAddress() {
		return addr;
	}

	public void setAddress(Address address) {
		addr.add(address);
		address.setStudents(this);
	}

	public Department getDepartment() {
		return dept;
	}

	public void setDepartment(Department dept) {
		this.dept = dept;
	}

	public List<Subject> getSubject() {
		return sub;
	}

	public void setSubject(Subject subject) {
		sub.add(subject);
	}

	@Override
	public String toString() {
		return this.name;
	}

}
