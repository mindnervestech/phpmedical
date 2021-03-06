package models;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model;
@Entity
public class Person extends Model {
	@Id
	public Integer idPerson;
	
	@OneToMany
	public List<Country> country;
	
	@OneToMany
	public List<DoctorClinic> doctorClinic;
	
	@OneToMany
	public List<Languages> language;

	public Integer role;
	public String name;
	public String emailID;
	public String password;
	public Long mobileNumber;
	
	@Enumerated(EnumType.STRING)
	public GenderType gender;

	public static enum GenderType {
	Male,
	Female
	}
	
//	public Integer gender;
	public Date dateOfBirth;
	public String location;
	public String bloodGroup;
	public String allergicTo;
	public Integer cloudType;
	public String cloudLoginId;
	public String cloudLoginPassword;
	public Integer patient;
	public String url;
	public String verficationCode;
	public String status;
	
/*	@OneToOne
	public DoctorRegister doctor;
*/
	@OneToOne
	public AssistentRegister assistent;


	public Integer doctor;
	
	public static Finder<Integer,Person> find = new Finder<>(Integer.class,Person.class);
	public static Person getUserByUserNameAndPassword(String emailID,String password) throws NoSuchAlgorithmException {
		return find.where().eq("emailID", emailID).eq("password",password).findUnique();
	}
	public static List<Person> getDoctor(String name){
		return find.where().like("name", "%" + name +"%").eq("role", 2).findList();
	}
	
	public static List<Person> getAssistant(String name){
		return find.where().like("name", "%" + name +"%").eq("role", 3).findList();
	}
	
	public static Person getPersonByDoctorID(String doctorId) {
		return find.where().eq("doctor", doctorId).findUnique();
	}
	
	public static Person getPersonById(Integer id) {
		return find.byId(id);
	}
	public static Person getPersonByMail(String emailID) {
		return find.where().eq("emailID", emailID).findUnique();
	}
	
	public static Integer getPatientByMail(String emailID) {
		return find.where().eq("emailID", emailID).eq("role", "1").findUnique().patient;
	}
	
	public static Integer getDoctorByMail(String emailID) {
		return find.where().eq("emailID", emailID).eq("role", "2").findUnique().doctor;
	}
	public static Person getDoctorsById(Integer id){
		return find.where().eq("doctor", id).eq("role", "2").findUnique();
	}
	public static Person getPatientsById(Integer id){
		return find.where().eq("patient", id).eq("role", "1").findUnique();
	}
	
	public static Person getAllPatientsById(Integer id){
		return find.where().eq("patient", id).findUnique();
	}
	
	public static List<Person> getPatient(String name){
		return find.where().like("name", "%" + name +"%").eq("role", 1).findList();
	}
	
	public static List<Person> getAllDoctorById(){
		String role ="2";
		return find.where().eq("role",role).findList();
	}
	
	public static List<Person> getAllPatientsById()
	{
		String role = "1";
		return find.where().eq("role", role).findList();
	}
	
	public static List<Person> getAllAssistantById(){
		String role ="3";
		return find.where().eq("role",role).findList();
	}
	
	public static Person getDoctorsByEmailId(String  email){
		return find.where().eq("emailID", email).findUnique();
				
	}
	
	public static List <Person> getAssistentByDoctorId(int docId ){
		return find.where().eq("doctor", docId).eq("role", 3).findList();
	}  

	public static List <Person> getDoctorAssistants(String docEmailId){
		
		return find.where().eq("emailID", docEmailId).eq("role", 3).findList();
				
	}
	
	public static Person getDoctorIdByEmail(String docEmail ){
		return find.where().eq("emailID", docEmail).findUnique();
	}
	
	public static Person getAssistantByAssistantRegisterId(AssistentRegister id) {
		return find.where().eq("assistent", id).eq("role", 3).findUnique();
	}
	
	public static Integer getAssistentByMail(String emailID) {
		return find.where().eq("emailID", emailID).eq("role", "3").findUnique().assistent.assitentId;
	}

   public static Person getPersonById(int id)
   {
	   return find.where().eq("id_person", id).findUnique();
   }
}
