package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Delegates extends Model {

	@Id
	public Integer id;
	
	public Integer parent;
	public Integer delegate;
	public String status;
	public String accessLevel;
	public String parentType;
	public String delType;
	
	public static Finder<Integer,Delegates> find = new Finder<>(Integer.class,Delegates.class);

	public static Delegates findByParentDelegate(Integer parent, Integer delegate) {
		return find.where().eq("parent", parent).eq("delegate", delegate).findUnique();
	}

	public static List<Delegates> getAllByParentPatient(Integer parent) {
		return find.where().eq("parent", parent).eq("parentType", "P").findList();
	}
	
	public static List<Delegates> getAllByParentDoctor(Integer parent) {
		return find.where().eq("parent", parent).eq("parentType", "D").findList();
	}

	public static List<Delegates> getAllByDelDoctor(Integer delegate) {
		return find.where().eq("delegate", delegate).eq("delType", "D").findList();
	}

	public static List<Delegates> getAllByDelAssistent(Integer delegate) {
		return find.where().eq("delegate", delegate).eq("delType", "A").findList();
	}

	public static List<Delegates> getAllByDelPatient(Integer delegate) {
		return find.where().eq("delegate", delegate).eq("delType", "P").findList();
	}

	public static Delegates getByParentDelegate(Integer parent, String parType, Integer delegate, String delType) {
		return find.where().eq("parent", parent).eq("parentType", parType).eq("delegate", delegate).eq("delType", delType).findUnique();
	}

}
