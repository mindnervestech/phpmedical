package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class SummaryHistory extends Model {
	@Id
	public Integer idSumaaryHistory;
	public String doctorId;
	public String patientId;
	public String symptoms;
	public String diagonosis;
	public String medicalPrescribed;
	public String testPrescribed;
	public String curDate;
	public String appointmentDate;
	public String appointmentTime;
    public String type;
	
	public static Finder<Integer,SummaryHistory> find = new Finder<>(Integer.class,SummaryHistory.class);
	public static List<SummaryHistory> getAllSummaryHistory(String appointmentDate,String appointmentTime,String doctorId,String patientId)
	{
		return find.where().eq("appointmentDate", appointmentDate).eq("appointmentTime", appointmentTime).eq("doctorId", doctorId).eq("patientId", patientId).findList();
	}
	

}
