
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;
@Entity
public class UploadFiles extends Model {
	@Id
	public Long id; 

	public String type;

	public Integer doctorId;
	public Integer patientId;
	public Integer assistentId;
	
	public String appointmentDate;
	public String appointmentTime;
	public String name;
	public String category;
	public String documentType;
	public String Url;

	public String fileName;
	
	public String clinicName;
	public String clinicId;

	public static Finder<Integer, UploadFiles> find = new Finder<>(Integer.class, UploadFiles.class);
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPersonType() {
		return type;
	}
	public void setPersonType(String personType) {
		this.type = personType;
	}
	
	public static List<UploadFiles> getUploadedFiles(Integer doctorId, Integer patientId, String appointmentDate, String appointmentTime){
		return find.where().eq("doctorId", doctorId).eq("patientId", patientId).eq("appointmentDate", appointmentDate).eq("appointmentTime", appointmentTime).findList();
	}
	
}
