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
	public String visitType;
	public Integer isVisited;
	
	public static Finder<Integer,PatientClientBookAppointment> find = new Finder<>(Integer.class,PatientClientBookAppointment.class);
	
	public static List<PatientClientBookAppointment> getAllClinicAppointment(int doctor_id, int clinic_id, String shift, Date date){
		return find.where().eq("doctorId", doctor_id).eq("clinicId", clinic_id).eq("shift", shift).eq("appointmentDate", date).findList();
	}
	
	public static PatientClientBookAppointment getClinicAppointment(Integer doctorId, Integer patient_id, Integer clinicId, String shift) {
		PatientClientBookAppointment appointment = find.where().eq("doctorId", doctorId).eq("patientId", patient_id).eq("clinicId", clinicId).eq("shift", shift).findUnique();
		
		if(appointment == null){
			return new PatientClientBookAppointment();
		}else{
			return appointment;
		}
	}
	
	public static PatientClientBookAppointment saveVisitedClinicAppointment(Integer doctorId, Integer patient_id, Integer clinicId, String shift) {
		
		return find.where().eq("doctorId", doctorId).eq("patientId", patient_id).eq("clinicId", clinicId).eq("shift", shift).findUnique();
	}
	
	public static List<PatientClientBookAppointment> getNextAppointment(Integer doctorId, Integer patient_id, String status) {
		return find.where().eq("doctorId", doctorId).eq("patientId", patient_id).eq("status", status).findList();
	}
	
	public static List<PatientClientBookAppointment> getNextClinicAppointment(Integer doctorId, String status) {
		return find.where().eq("doctorId", doctorId).eq("status", status).findList();
	}
	
	public static List<PatientClientBookAppointment> getNextDoctorClinicAppointment(Integer patient_id, String status) {
		return find.where().eq("doctor_id", patient_id).eq("status", status).findList();
	}
	
	public static String deleteNextAppointment(Integer doctorId, Integer patient_id, Integer clinicId, String shift) {
		PatientClientBookAppointment appointment = find.where().eq("doctorId", doctorId).eq("patientId", patient_id).eq("clinicId", clinicId).eq("shift", shift).findUnique();
		
		if(appointment != null){
			appointment.delete();
			return "Deleted successfully !!!";
		}else{
			return "Appointment Not Found  !!!";
		}
	}
	
	public static List<PatientClientBookAppointment> getAllAppointment(Integer doctorId, Integer patient_id) {
		return find.where().eq("doctorId", doctorId).eq("patientId", patient_id).findList();
	}
	
	public static List<PatientClientBookAppointment> getAllPatientOfDoctor(Integer doctorId) {
		return find.where().eq("doctorId", doctorId).findList();
	}
	
	public static List<PatientClientBookAppointment> getAllClinicAppointment(Integer doctorId, Integer clinicId) {
		return find.where().eq("doctorId", doctorId).eq("clinicId", clinicId).findList();
	}
	
}
