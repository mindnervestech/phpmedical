package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class TreatementTemplateAttribute extends Model {
	
	@Id
	public Integer fieldId;
	
	public String fieldName;
	public String fieldType;
	public String fieldDisplayName;
	public String fieldDefaultValue;
	
	@ManyToOne
	public TemplateClass templateClass;
	
	public Integer doctorId;
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldDisplayName() {
		return fieldDisplayName;
	}

	public void setFieldDisplayName(String fieldDisplayName) {
		this.fieldDisplayName = fieldDisplayName;
	}

	public String getFieldDefaultValue() {
		return fieldDefaultValue;
	}

	public void setFieldDefaultValue(String fieldDefaultValue) {
		this.fieldDefaultValue = fieldDefaultValue;
	}

	public TemplateClass getTemplateClass() {
		return templateClass;
	}

	public void setTemplateClass(TemplateClass templateClass) {
		this.templateClass = templateClass;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer patientId;
	
	
	
	public static Finder<Integer,TreatementTemplateAttribute> find = new Finder<>(Integer.class,TreatementTemplateAttribute.class);
	
	public static TreatementTemplateAttribute getAttribute(Integer id)
	{
		return find.byId(id);
	}
	
	public static TreatementTemplateAttribute getById(Integer id){
		return find.where().eq("fieldId", id).findUnique();
	}
	
	public static List<TreatementTemplateAttribute> getAllAttributes(TemplateClass id)
	{
		return find.where().eq("templateClass", id).findList();
	}
	
}
