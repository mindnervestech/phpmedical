package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Invoices extends Model {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public Integer doctorId;
	public Integer patientId;
	public Integer procedureId;
	public Integer templateId;
	public String appointmentDate;
	public String appointmentTime;
	
	public static Finder<Integer,Invoices> find = new Finder<>(Integer.class,Invoices.class);
	
	public static Invoices getTreatmentPlanById(long id){
		return find.where().eq("id", id).findUnique();
	}
	
	public static Invoices checkInvoicesById(Integer doctorId,Integer patientId,Integer procedureId,
			Integer templateId,String appointmentDate,String appointmentTime){
		return find.where().eq("doctorId", doctorId).eq("patientId", patientId)
				.eq("procedureId", procedureId).eq("templateId", templateId).eq("appointmentDate", appointmentDate)
				.eq("appointmentTime", appointmentTime).findUnique();
	}
	
	public static List<Invoices> getAllInvoicesById(Integer doctorId,Integer patientId,
			                                                  String appointmentDate,String appointmentTime){
		return find.where().eq("doctorId", doctorId).eq("patientId", patientId).eq("appointmentDate", appointmentDate)
				           .eq("appointmentTime", appointmentTime).findList();
	}
	
}
