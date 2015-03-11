package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
@Entity
public class PatientRegister extends Model{
	@Id
	public Integer patientId;
	
/*	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<DoctorRegister> doctor;
*/	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<DignosticLab> lab;
	
	@ManyToMany
	public List<DoctorRegister> doctors;
	
	public static Finder<Integer,PatientRegister> find = new Finder<>(Integer.class,PatientRegister.class);
	public static PatientRegister getPatientById(Integer id) {
		return find.byId(id);
	}
/*	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<BucketDoctors> bucketDoctors;*/
	
	
}
