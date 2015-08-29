package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class DoctorDependency extends Model{
	
	@Id
	public Integer id;
	
	public Integer doctor;
	public Integer dependent;
	public String status;
	public String accessLevel;
	
	public static Finder<Integer,DoctorDependency> find = new Finder<>(Integer.class,DoctorDependency.class);
	
	public static List<DoctorDependency> getAllByDoctor(Integer doctorId) {
		return find.where().eq("doctor", doctorId).findList();
	}
	
	public static List<DoctorDependency> getAllByDependent(Integer doctorId) {
		return find.where().eq("dependent", doctorId).findList();
	}
	
	public static DoctorDependency getByPatientDependent(Integer dep, Integer parent) {
		return find.where().eq("doctor", parent).eq("dependent", dep).findUnique();
	}

}
