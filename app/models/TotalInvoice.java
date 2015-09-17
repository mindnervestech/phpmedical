package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class TotalInvoice extends Model {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public Integer doctorId;
	public Integer patientId;
	public String appointmentDate;
	public String appointmentTime;
	public String grandTotal;
	public String discount;
	public String taxValue;
	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Integer getShareWithPatient() {
		return shareWithPatient;
	}

	public void setShareWithPatient(Integer shareWithPatient) {
		this.shareWithPatient = shareWithPatient;
	}

	public String percentageDiscount;
	public String percentageTax;
	public String advance;
	public String totalDue;
	public Integer shareWithPatient;
	
	public static Finder<Integer,TotalInvoice> find = new Finder<>(Integer.class,TotalInvoice.class);
	
	public static TotalInvoice getTreatmentPlanById(long id){
		return find.where().eq("id", id).findUnique();
	}
	
	public static TotalInvoice returnTotalInvoice(Integer doctorId, Integer patientId, String appointmentDate, String appointmentTime){
		
		System.out.println("doctorNotes.doctorId = "+doctorId);
		System.out.println("doctorNotes.patientId = "+patientId);
		System.out.println("doctorNotes.appointmentDate = "+appointmentDate);
		System.out.println("doctorNotes.appointmentTime = "+appointmentTime);
		
		return find.where().eq("doctorId", doctorId).eq("patientId", patientId).eq("appointmentDate", appointmentDate).eq("appointmentTime", appointmentTime).findUnique();
	}
	
	public static List<TotalInvoice> getAllInvoicesById(Integer doctorId,Integer patientId,
			                                                  String appointmentDate,String appointmentTime){
		return find.where().eq("doctorId", doctorId).eq("patientId", patientId).eq("appointmentDate", appointmentDate)
				           .eq("appointmentTime", appointmentTime).findList();
	}
	
	public static List<TotalInvoice> getAllInvoices(Integer doctorId,String date)
	{
		return find.where().eq("doctorId", doctorId).eq("appointmentDate", date).findList();
	}
	
}
