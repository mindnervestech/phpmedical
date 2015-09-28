package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	public DoctorProcedure doctorProcedure;
	
	public static Finder<Integer,TemplateClass> find = new Finder<>(Integer.class,TemplateClass.class);
	
	public static List<TemplateClass> getTemplatesDoctor(Integer doctorId, String procedureName ) {
		return find.where().eq("doctorId", doctorId).eq("procedureName", procedureName).findList();
	}
	
	public static List<TemplateClass> getTemplate(String name){
		return find.where().like("name", "%" + name +"%").findList();
	}
	
	public static TemplateClass findTemplateById(Integer id){
		return find.byId(id);
	}
	
	public static TemplateClass getTemplateClassByName(Integer doctorId,String templateName, String procedureName) {
		return find.where().eq("doctorId", doctorId).eq("templateName", templateName).eq("procedureName", procedureName).findUnique();
	}
	
	public static int getTemplateCount(Integer doctorId,String procedureName) {
		return find.where().eq("doctorId", doctorId).eq("procedureName", procedureName).findRowCount();
	}
	
	public static TemplateClass getTemplateClassByProcedureId(Integer procedureId)
	{
		return find.where().eq("doctor_procedure_id",procedureId).findUnique();
	}
	
	
}
