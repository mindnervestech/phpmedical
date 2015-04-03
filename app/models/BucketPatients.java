package models;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import play.db.ebean.Model.Finder;
import play.db.ebean.Model;

@Entity
public class BucketPatients extends Model {

	@Id
	public Integer patientId;
	public String name;
	public String email;
	public Long mobileNumber;
	public String location;
	public String userMailId;
	
	public Integer doctor;
	
	public static Finder<Integer,BucketPatients> find = new Finder<>(Integer.class,BucketPatients.class);
	public static List<BucketPatients> getPersonByDoctor(Integer doctor) {
		return find.where().eq("doctor", doctor).findList();
	}
	
	public static List<BucketPatients> getPatient(String name){
		return find.where().like("name", "%" + name +"%").findList();
	}
	
	public static BucketPatients getPatientById(Integer id) {
		return find.byId(id);
	}
}
