package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
@Entity
public class Clinic extends Model {
	@Id
	public Integer idClinic;
		
	public String clinicName;
	public Long landLineNumber;
	public Long mobileNumber;
	public String address;
	public String location;
	public String email;
	
	public static Finder<Integer,Clinic> find = new Finder<>(Integer.class,Clinic.class);
	public static Clinic getClinicById(Integer id) {
		return find.byId(id);
	}
	public static List<Clinic> getClinic(String queryString) {
		return find.where().like("clinicName", "%" + queryString +"%").findList();
	}
	
}
