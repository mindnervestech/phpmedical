package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
@Entity
public class AssistentRegister extends Model{
	@Id
	public Integer assitentId;
	
	@JsonIgnore
	@ManyToOne
	public DoctorRegister doctorRegister;
	
	public static Finder<Integer,AssistentRegister> find = new Finder<>(Integer.class,AssistentRegister.class);
	public static AssistentRegister getAssistantById(Integer id) {
		return find.byId(id);
	}
	
}
