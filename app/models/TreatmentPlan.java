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
public class TreatmentPlan extends Model {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public Integer doctorId;
	public Integer patientId;
	public Integer procedureId;
	public Integer templateId;
	public String appointmentDate;
	public String appointmentTime;
	
	public static Finder<Integer,TreatmentPlan> find = new Finder<>(Integer.class,TreatmentPlan.class);
	
	public static TreatmentPlan getTreatmentPlanById(long id){
		return find.where().eq("id", id).findUnique();
	}
	
	public static TreatmentPlan checkTreatmentPlanById(Integer doctorId,Integer patientId,Integer procedureId,
			Integer templateId,String appointmentDate,String appointmentTime){
		return find.where().eq("doctorId", doctorId).eq("patientId", patientId)
				.eq("procedureId", procedureId).eq("templateId", templateId).eq("appointmentDate", appointmentDate)
				.eq("appointmentTime", appointmentTime).findUnique();
	}
	
	public static List<TreatmentPlan> getAllTreatmentPlanById(Integer doctorId,Integer patientId,
			                                                  String appointmentDate,String appointmentTime){
		return find.where().eq("doctorId", doctorId).eq("patientId", patientId).eq("appointmentDate", appointmentDate)
				           .eq("appointmentTime", appointmentTime).findList();
	}
	
	/*public static ReminderData getAllReminderDataById(long doctor_id, long patientId, String appointmentDate, String appointmentTime){
		return find.where().eq("doctorId", doctor_id).eq("patientId", patientId).eq("appointmentDate", appointmentDate).eq("appointmentTime", appointmentTime).findUnique();
	}
	
	
	public static ReminderData getAllClinicAppointment(int doctor_id, int patientId){
		return find.where().eq("doctorId", doctor_id).eq("patientId", patientId).findUnique();
	}*/
	
		
}
