package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
@Entity
public class DoctorProcedure extends Model {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	public Integer doctorId;
	public String procedureName;
	public String category;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "doctorProcedure")@JsonIgnore
	public TemplateClass procedureTemplateClass = new TemplateClass();
	
	public static Finder<Integer,DoctorProcedure> find = new Finder<>(Integer.class,DoctorProcedure.class);
	
	public static List<DoctorProcedure> getAllProcedureByDoctorId(Integer doctorId) {
		return find.where().eq("doctorId", doctorId).findList();
	}
	
	public static DoctorProcedure searchProcedureByprocedureName(String procedureName) {
		return find.where().eq("procedureName", procedureName).findUnique();
	}
	
	public static DoctorProcedure getAllProcedureByIDdoctorId(Integer id,Integer doctorId) {
		return find.where().eq("id", id).eq("doctorId", doctorId).findUnique();
	}
	
	public static DoctorProcedure searchProcedureByIDName(Integer doctorId,String procedureName) {
		return find.where().eq("procedureName", procedureName).eq("doctorId", doctorId).findUnique();
	}
	
	
	/*public static Clinic getClinicById(Integer id) {
		return find.byId(id);
	}
	public static List<Clinic> getClinic(String queryString) {
		return find.where().like("clinicName", "%" + queryString +"%").findList();
	}
	
	public static List<Clinic> getAllClinic() {
		return find.where().findList();
	}
	
	public static List<Clinic> findAllByDoctorId(Integer doctorId2) {
		return find.where().eq("doctorId", doctorId2).findList();
	}
	
	public static Clinic findClinicById(Integer idClinic) {
		return find.where().eq("idClinic", idClinic).findUnique();
	}*/
	
}
