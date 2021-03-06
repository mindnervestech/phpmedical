package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class PatientDependency extends Model {

	@Id
	public Integer id;
	
	public Integer patient;
	public Integer dependent;
	public String status;
	public String accessLevel;
	
	public static Finder<Integer,PatientDependency> find = new Finder<>(Integer.class,PatientDependency.class);

	public static PatientDependency findByPatientDependent(Integer patient, Integer dependent) {
		return find.where().eq("patient", patient).eq("dependent", dependent).findUnique();
	}

	public static List<PatientDependency> getAllByPatient(Integer patientId) {
		return find.where().eq("patient", patientId).findList();
	}

	public static List<PatientDependency> getAllByDependent(Integer patientId) {
		return find.where().eq("dependent", patientId).findList();
	}

	public static PatientDependency getByPatientDependent(Integer dep, Integer parent) {
		return find.where().eq("patient", parent).eq("dependent", dep).findUnique();
	}
	
}
