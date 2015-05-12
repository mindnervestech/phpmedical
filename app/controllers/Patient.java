package controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();
		
		PatientClinicsAppointmentVM bookAppointment = mapper.readValue(json.traverse(),PatientClinicsAppointmentVM.class);
		PatientClientBookAppointment appointment = new PatientClientBookAppointment();
		
		//Person person =  Person.getPatientByMail(bookAppointment.patientId);
		
		appointment.clinicId = bookAppointment.clinicId;
		//appointment.patientId = bookAppointment.patientId;
		appointment.patientId = Person.getPatientByMail(bookAppointment.patientId);
		appointment.doctorId = bookAppointment.doctorId;
		appointment.shift = bookAppointment.shift;
		appointment.bookTime = bookAppointment.bookTime;
		appointment.timeSlot = bookAppointment.timeSlot;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println("Date  in java = "+format.parse(bookAppointment.appointmentDate));
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
	
