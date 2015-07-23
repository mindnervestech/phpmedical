package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;
@Entity
public class DoctorNotes extends Model {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	public String doctorId;
	public String patientId;
	public String symptoms;
	public String diagnosis;
	public String doctorNotes;
	public String appointmentDate;
	public String appointmentTime;
	
	public static Finder<Integer,DoctorNotes> find = new Finder<>(Integer.class,DoctorNotes.class);
	
	/*public static DoctorNotes getDoctorById(Integer id) {
		return find.where().eq("doctorId", id).findUnique();
	}*/
	
	public static DoctorNotes checkRegisterDoctorNotes(String doctorId, String patientId, String appointmentDate, String appointmentTime){
		
		System.out.println("doctorNotes.doctorId = "+doctorId);
		System.out.println("doctorNotes.patientId = "+patientId);
		System.out.println("doctorNotes.appointmentDate = "+appointmentDate);
		System.out.println("doctorNotes.appointmentTime = "+appointmentTime);
		
		return find.where().eq("doctorId", doctorId).eq("patientId", patientId).eq("appointmentDate", appointmentDate).eq("appointmentTime", appointmentTime).findUnique();
	}
	
}
