package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.db.ebean.Model;

@Entity
public class ReminderData extends Model {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	public Integer doctorId;
	public Integer patientId;
	public String appointmentDate;
	public String appointmentTime;
	public String medicinName;
	public String startDate;
	public String endDate;
	public Integer duration;
	public Integer numberOfDoses;
	public String schedule;
	public String doctorInstruction;
	public String visitDate;
	public String visitType;
	public String referredBy;
	public String symptoms;
	public String diagnosis;
	public String medicinePrescribed;
	public String testsPrescribed;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "reminderData")@JsonIgnore
	public List<ReminderTimeTable> reminderTimeTables = new ArrayList<ReminderTimeTable>();
	
	public static Finder<Integer,ReminderData> find = new Finder<>(Integer.class,ReminderData.class);
	
	public static ReminderData getReminderDataById(long id){
		return find.where().eq("id", id).findUnique();
	}
	
	public static ReminderData getAllReminderDataById(long doctor_id, long patientId, String appointmentDate, String appointmentTime){
		return find.where().eq("doctorId", doctor_id).eq("patientId", patientId).eq("appointmentDate", appointmentDate).eq("appointmentTime", appointmentTime).findUnique();
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getMedicinName() {
		return medicinName;
	}

	public void setMedicinName(String medicinName) {
		this.medicinName = medicinName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getNumberOfDoses() {
		return numberOfDoses;
	}

	public void setNumberOfDoses(Integer numberOfDoses) {
		this.numberOfDoses = numberOfDoses;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getDoctorInstruction() {
		return doctorInstruction;
	}

	public void setDoctorInstruction(String doctorInstruction) {
		this.doctorInstruction = doctorInstruction;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getMedicinePrescribed() {
		return medicinePrescribed;
	}

	public void setMedicinePrescribed(String medicinePrescribed) {
		this.medicinePrescribed = medicinePrescribed;
	}

	public String getTestsPrescribed() {
		return testsPrescribed;
	}

	public void setTestsPrescribed(String testsPrescribed) {
		this.testsPrescribed = testsPrescribed;
	}

	public List<ReminderTimeTable> getReminderTimeTables() {
		return reminderTimeTables;
	}

	public void setReminderTimeTables(List<ReminderTimeTable> reminderTimeTables) {
		this.reminderTimeTables = reminderTimeTables;
	}

	public static ReminderData getAllClinicAppointment(int doctor_id, int patientId){
		return find.where().eq("doctorId", doctor_id).eq("patientId", patientId).findUnique();
	}

	
	
		
}
