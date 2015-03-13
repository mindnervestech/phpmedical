package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
@Entity
public class AssistentRegister extends Model{
	@Id
	public Integer assitentId;
	
	//@JsonIgnore
	//@ManyToOne
	//public DoctorRegister doctorRegister;
	
	public static Finder<Integer,AssistentRegister> find = new Finder<>(Integer.class,AssistentRegister.class);
	public static AssistentRegister getAssistantById(Integer id) {
		return find.byId(id);
	}
	
}
