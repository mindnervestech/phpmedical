package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class TemplateAttribute extends Model {
	
	@Id
	public Integer fieldId;
	public String fieldName;
	public String fieldType;
	
	@ManyToOne
	public TemplateClass templateClass;
	
	public static Finder<Integer,TemplateAttribute> find = new Finder<>(Integer.class,TemplateAttribute.class);
	
	public static TemplateAttribute getAttribute(Integer id)
	{
		return find.byId(id);
	}
	
	public static List<TemplateAttribute> getAllAttributes(TemplateClass id)
	{
		return find.where().eq("templateClass", id).findList();
	}
	
	

}
