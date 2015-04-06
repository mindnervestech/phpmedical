package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class TemplateClass extends Model {
	
	@Id
	public Integer templateId;
	public String templateName;
	public String procedureName;
	public Integer doctorId;
	
	@OneToMany
	public List<TemplateAttribute> templateAttributes;
	
	public static Finder<Integer,TemplateClass> find = new Finder<>(Integer.class,TemplateClass.class);
	public static List<TemplateClass> getTemplatesDoctor(Integer doctorId) {
		return find.where().eq("doctorId", doctorId).findList();
	}
	
	public static List<TemplateClass> getTemplate(String name){
		return find.where().like("name", "%" + name +"%").findList();
	}
	
	
	
	public static TemplateClass findTemplateById(Integer id)
	{
		return find.byId(id);
	}
	
}
