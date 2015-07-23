package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class DoctorClinicSchedule  extends Model{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public String  docId;
	public String clinicId;
	public String day;
	public String form;
	public String  totime;
	public String  shift;
	
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getClinicId() {
		return clinicId;
	}

	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}
     
    public static Finder<Long, DoctorClinicSchedule> find = new Finder<Long, DoctorClinicSchedule>(
			Long.class, DoctorClinicSchedule.class);
    
    
    public static List <DoctorClinicSchedule>  getDoctorClinicScheduleById(String clinicId ,String docId){
    	return find.where().eq("clinicId", clinicId).eq("docId", docId).findList();
    }
    
    public static List <DoctorClinicSchedule> getAllDoctorOfClinicById(String clinicId){
    	return find.where().eq("clinicId", clinicId).findList();
    }
    
    public static List<DoctorClinicSchedule> findAllClinicSchedule(String doc_id, String clinicId) {
		return find.where().eq("doc_id", doc_id).eq("clinicId", clinicId).findList();
	}
    
    public static List<DoctorClinicSchedule> findAllDoctorClinicSchedule(String clinicId) {
		return find.where().eq("clinicId", clinicId).findList();
	}
    
    
    public static List <DoctorClinicSchedule> getClinicScheduleshiftDetails(String docId,String clinicId ){
	return find.where().eq("clinicId", clinicId).eq("docId", docId).findList();
	
    }
    
    
    public static List<DoctorClinicSchedule> findAllClinicScheduleByShift(String doc_id, String clinicId, String shift) {
		return find.where().eq("doc_id", doc_id).eq("clinicId", clinicId).eq("shift", shift).findList();
	}

}
