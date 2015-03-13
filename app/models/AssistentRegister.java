package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;
@Entity
public class AssistentRegister extends Model{
	@Id
	public Integer assitentId;
	
	@ManyToMany
	public List<DoctorRegister> doctors;
	
	public static Finder<Integer,AssistentRegister> find = new Finder<>(Integer.class,AssistentRegister.class);
	public static AssistentRegister getAssistantById(Integer id) {
		return find.byId(id);
	}
	
}
