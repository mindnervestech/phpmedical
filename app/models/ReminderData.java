package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.db.ebean.Model;

@Entity
public class ReminderData extends Model {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public Integer doctorId;
	public Integer patientId;
	public String appointmentDate;
	public String appointmentTime;
	public String medicinName;
	public String startDate;
	public String endDate;
	public Integer duration;
	public Integer numberOfDoses;
	public String schedule;
	public String doctorInstruction;
	
	public String visitDate;
	public String visitType;
	public String referredBy;
	public String symptoms;
	public String diagnosis;
	public String medicinePrescribed;
	public String testsPrescribed;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "reminderData")@JsonIgnore
	public List<ReminderTimeTable> reminderTimeTables = new ArrayList<ReminderTimeTable>();
	
	public static Finder<Integer,ReminderData> find = new Finder<>(Integer.class,ReminderData.class);
	
	public static ReminderData getReminderDataById(long id){
		return find.where().eq("id", id).findUnique();
	}
	
	public static ReminderData getAllReminderDataById(long doctor_id, long patientId, String appointmentDate, String appointmentTime){
		return find.where().eq("doctorId", doctor_id).eq("patientId", patientId).eq("appointmentDate", appointmentDate).eq("appointmentTime", appointmentTime).findUnique();
	}
	
	
	public static ReminderData getAllClinicAppointment(int doctor_id, int patientId){
		return find.where().eq("doctorId", doctor_id).eq("patientId", patientId).findUnique();
	}
	
		
}
