package controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import models.Clinic;
import models.DoctorClinicSchedule;
import models.PatientClientBookAppointment;
import models.Person;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import play.mvc.Controller;
import play.mvc.Result;
import viewmodel.DoctorClinicDetails;
import viewmodel.PatientClinicsAppointmentVM;
import viewmodel.TimeTable;
import play.libs.Json;

import org.codehaus.jackson.map.ObjectMapper;

public class Patient extends Controller {
	
	public static Result index() {
		return ok(views.html.index.render("Your new application is ready."));
	}
	
	
	public static Result saveClinicsAppointmentDetails() throws IOException {
		
		JsonNode json = request().body().asJson();
		//System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();
		
		
		PatientClinicsAppointmentVM bookAppointment = mapper.readValue(json.traverse(),PatientClinicsAppointmentVM.class);
		
		Integer patient_id = Person.getPatientByMail(bookAppointment.patientId);

		PatientClientBookAppointment appointment = PatientClientBookAppointment.getClinicAppointment(bookAppointment.doctorId, patient_id, 
																bookAppointment.clinicId, bookAppointment.shift) ;
		
		appointment.clinicId = bookAppointment.clinicId;
		//appointment.patientId = bookAppointment.patientId;
		appointment.patientId = patient_id;//Person.getPatientByMail(bookAppointment.patientId);
		appointment.doctorId = bookAppointment.doctorId;
		appointment.shift = bookAppointment.shift;
		appointment.bookTime = bookAppointment.bookTime;
		appointment.timeSlot = bookAppointment.timeSlot;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//System.out.println("Date  in java = "+format.parse(bookAppointment.appointmentDate));
			appointment.appointmentDate = format.parse(bookAppointment.appointmentDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//appointment.appointmentDate = bookAppointment.appointmentDate;
		appointment.status = bookAppointment.status;
		appointment.save();
		
		return ok(Json.toJson(new ErrorResponse(Error.E304.getCode(),
				Error.E304.getMessage())));
		
	}
	
public static Result saveVisitedPatientAppointment() throws IOException {
		
	String decryptedValue = null;
	String clinicId = null;
	String shift = null;
	String patientId = null;
	String visited = null;
	
	try {
		decryptedValue = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
		clinicId = request().getQueryString("clinicId");
		shift = request().getQueryString("shift");
		patientId = request().getQueryString("patientId");
		visited = request().getQueryString("isVisited");
		
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	/*System.out.println("decryptedValue = "+decryptedValue);
	System.out.println("clinicId = "+clinicId);
	System.out.println("shift = "+shift);
	System.out.println("patientId = "+patientId);
	System.out.println("visited = "+visited);
	*/
	int doctor_id = Integer.parseInt(decryptedValue);
	int clinic_id = Integer.parseInt(clinicId);
	Integer patient_id = Person.getPatientByMail(patientId);
	int isVisited = Integer.parseInt(visited);

	PatientClientBookAppointment appointment = PatientClientBookAppointment
			.saveVisitedClinicAppointment(doctor_id, patient_id,clinic_id, shift) ;
		
	appointment.isVisited = isVisited;
	appointment.update();
		
	return ok(Json.toJson("Saved Successfully !!!"));
		
	}
	
	public static Result getClinicsAppointmentDetails() {
			
			String decryptedValue = null;
			String clinicId = null;
			String shift = null;
			String date = null;
			
			try {
				decryptedValue = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
				clinicId = request().getQueryString("clinicId");
				shift = request().getQueryString("shift");
				date = request().getQueryString("date");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("decryptedValue = "+decryptedValue);
			System.out.println("clinicId = "+clinicId);
			System.out.println("shift = "+shift);
			System.out.println("date = "+date);
			
			int doctor_id = Integer.parseInt(decryptedValue);
			int clinic_id = Integer.parseInt(clinicId);
			
			Date appintmentDate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				appintmentDate = format.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<PatientClientBookAppointment> clinicList = PatientClientBookAppointment.getAllClinicAppointment(doctor_id,clinic_id,shift,appintmentDate);
			
			return ok(Json.toJson(clinicList));
		}
	
	public static Result getAllPatientAppointment() {
		
		String decryptedValue = null;
		String patientId = null;
		
		try {
			decryptedValue = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
			patientId = request().getQueryString("patientId");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int doctor_id = Integer.parseInt(decryptedValue);
		Integer patient_id = Person.getPatientByMail(patientId);
		
		List<PatientClientBookAppointment> clinicList = PatientClientBookAppointment.getAllAppointment(doctor_id,patient_id);
		
		Collections.sort(clinicList, new Comparator<PatientClientBookAppointment>() {
		    public int compare(PatientClientBookAppointment chair1, PatientClientBookAppointment chair2) {
				
		    	String[] timeValue;
		    	Calendar calOne = Calendar.getInstance();
		    	calOne.setTime(chair1.appointmentDate);
		    	timeValue = chair1.bookTime.split(":");
		    	
		     	int hour1 = Integer.parseInt(timeValue[0].trim());
		     	int min1 = Integer.parseInt(timeValue[1].trim().split("[a-zA-Z ]+")[0]);
		     	calOne.set(Calendar.HOUR,hour1);
		    	calOne.set(Calendar.MINUTE, min1);
		    	
		    	String strAM_PM = timeValue[1].replaceAll("[0-9]","");
		    	if(strAM_PM.equals("AM")){
		    		calOne.set(Calendar.AM_PM, 0);
		    	}else{
		    		calOne.set(Calendar.AM_PM, 1);
		    	}
		    	Calendar calTwo = Calendar.getInstance();
		    	calTwo.setTime(chair2.appointmentDate);
		    	timeValue = chair2.bookTime.split(":");
		    	
		    	int hour2 = Integer.parseInt(timeValue[0].trim());
		     	int min2 = Integer.parseInt(timeValue[1].trim().split("[a-zA-Z ]+")[0]);
		     	calTwo.set(Calendar.HOUR,hour2);
		     	String strAM_PM2 = timeValue[1].replaceAll("[0-9]","");
		     	
		     	if(strAM_PM2.equals("AM")){
		     		calTwo.set(Calendar.AM_PM, 0);
		    	}else{
		    		calTwo.set(Calendar.AM_PM, 1);
		    	}
		     	calOne.set(Calendar.AM_PM, 1);
		     	calTwo.set(Calendar.MINUTE, min2);
		    	
		    	
		    	if(calOne.compareTo(calTwo) == 1){
		    		return -1;
		    	}else if(calOne.compareTo(calTwo) == -1){
		    		return 1;
		    	}else{
		    		return 0;
		    	}
		    	
		    }
		});
		
		return ok(Json.toJson(clinicList));
	}
	
	public static Result cancelClinicsAppointment() {
		
		String decryptedValue = null;
		String clinicId = null;
		String shift = null;
		String patientId = null;
		
		try {
			decryptedValue = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
			clinicId = request().getQueryString("clinicId");
			shift = request().getQueryString("shift");
			patientId = request().getQueryString("patientId");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("decryptedValue = "+decryptedValue);
		System.out.println("clinicId = "+clinicId);
		System.out.println("shift = "+shift);
		System.out.println("patientId = "+patientId);
		
		int doctor_id = Integer.parseInt(decryptedValue);
		int clinic_id = Integer.parseInt(clinicId);
		Integer patient_id = Person.getPatientByMail(patientId);
		
	    String status =	PatientClientBookAppointment.deleteNextAppointment(doctor_id, patient_id, clinic_id, shift);
		
		return ok(Json.toJson(status));
	}
	
	
	public static class ErrorResponse {
		public String code;
		public String message;

		public ErrorResponse(String code, String message) {
			this.code = code;
			this.message = message;
		}
	}
	public enum Error {
		E201("201", "Login Failed!"), E202("202", "Required Field Missing!"), E200(
				"200", "Login Successful!"), E203("203", "Passwords Mismatch!"), E204(
				"204", "User Registered Successfully!"),E304(
						"204", "User Registered Successfully!"), E205("205",
				"Language Param Not Found!"), E206("206",
				"Username is not Valid Number!"), E207("207",
				"Validation Code is Invalid!"), E208("208",
				"Username, Password does'nt matched with our database!"), E209(
				"209", "User Validated Successfully!"), E210("210",
				"User Already Exist!"), E211("211", "Doctor Does Not Exist"),
		E212("211", "Clinic Doctor time Added ");
		Error(String code, String message) {
			this.code = code;
			this.message = message;
		}

		private String code;
		private String message;

		public String getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

	}
	
}
	
