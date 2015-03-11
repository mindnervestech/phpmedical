package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
@Entity
public class DignosticLab extends Model{
	@Id
	public Integer idDiagnosticLab;
	
	@ManyToOne
	public Country country;
	
	public String labName;
	public String landLineNumber;
	public String mobileNumber;
	public String address;
	public String location;
	public String email;
	@ManyToMany
	public List<PatientRegister> patient;
	
	public static Finder<Integer,DignosticLab> find = new Finder<>(Integer.class,DignosticLab.class);
	public static DignosticLab getDignosticLabById(Integer id) {
		return find.byId(id);
	}
	
}
