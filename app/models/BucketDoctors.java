package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import viewmodel.RegisterDoctor;

@Entity
public class BucketDoctors extends Model {
	@Id
	public Integer doctorId;
	public String name;
	public String speciality;
	public String email;
	public Long mobileNumber;
	public String location;
	public String userMailId;
	
	/*@OneToOne
	public PatientRegister patient;
	
	public static Finder<Integer,BucketDoctors> find = new Finder<>(Integer.class,BucketDoctors.class);
	public static BucketDoctors getPersonByPatient(PatientRegister patient) {
		return (BucketDoctors) find.where().eq("patient", patient).findList();
	}*/
	
	public Integer patient;
	
	public static Finder<Integer,BucketDoctors> find = new Finder<>(Integer.class,BucketDoctors.class);
	public static List<BucketDoctors> getPersonByPatient(Integer patient) {
		return find.where().eq("patient", patient).findList();
	}

}
