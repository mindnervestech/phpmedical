package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


import play.db.ebean.Model;
@Entity
public class DoctorRegister extends Model {
	@Id
	public Integer doctorId;
/*	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<AssistentRegister> assistent;
*/	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Clinic> clinic;
	
	@ManyToMany(mappedBy = "doctors")
	public List<PatientRegister> patient;
	
	@OneToOne
	public Person person;
	
	@ManyToMany(mappedBy = "doctors")
	public List<AssistentRegister> assistentRegister;
	
	public boolean flag;
	public String speciality;
	
	public static Finder<Integer,DoctorRegister> find = new Finder<>(Integer.class,DoctorRegister.class);
	public static DoctorRegister getDoctorById(Integer id) {
		return find.where().eq("doctorId", id).findUnique();
	}
	
	public static List<DoctorRegister> getAllDoctorSepeciality()
	{
		return find.findList();
	}
	

	
	
}
