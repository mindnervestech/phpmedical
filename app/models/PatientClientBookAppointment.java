package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import play.db.ebean.Model;

@Entity
public class PatientClientBookAppointment extends Model {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public Integer doctorId;
	public Integer patientId;
	public Integer clinicId;
	public String shift;
	public String bookTime;
	public String timeSlot;
	public Date appointmentDate;
	public String status;
	
	public static Finder<Integer,PatientClientBookAppointment> find = new Finder<>(Integer.class,PatientClientBookAppointment.class);
	
	
	
	public static List<PatientClientBookAppointment> getAllClinicAppointment(int doctor_id, int clinic_id, String shift, Date date){
		return find.where().eq("doctorId", doctor_id).eq("clinicId", clinic_id).eq("shift", shift).eq("appointmentDate", date).findList();
	}
	
}
