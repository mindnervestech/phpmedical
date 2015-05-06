package controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import models.Clinic;
import models.DoctorClinicSchedule;
import models.PatientRegister;
import models.Person;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;
import viewmodel.DoctorClinicDetails;
import viewmodel.TimeTable;

public class Doctor extends Controller {
	
	public static Result index() {
		return ok(views.html.index.render("Your new application is ready."));
	}
	
	public static Result getDoctorsClinicsDetails() {
		
		
		String decryptedValue = null;
		String type = null;
		List<Clinic> clinicList = null;
		
		try {
			decryptedValue = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
			type = request().getQueryString("type");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("decryptedValue:" + decryptedValue);
		System.out.println("type:" + type);
		
		Person person = Person.getPersonByDoctorID(decryptedValue);
		
		String doctorEmail = person.emailID;
		
		List<DoctorClinicDetails> ClinicDetailsList = new ArrayList<DoctorClinicDetails>();
		
		if(type.equals("1")){
			clinicList = Clinic.findAllByDoctorId(Integer.parseInt(decryptedValue));
			System.out.println("Size = "+clinicList.size());
			
			for(Clinic clinic : clinicList){
				/*String daysOfWeekSlot1 = null;
				String daysOfWeekSlot2 = null;
				String daysOfWeekSlot3 = null;*/
				TimeTable timeTable1 = new TimeTable();
				TimeTable timeTable2 = new TimeTable();
				TimeTable timeTable3 = new TimeTable();
				
				List<DoctorClinicSchedule> doctorClinicScheduleList = DoctorClinicSchedule.findAllClinicSchedule(doctorEmail, clinic.idClinic.toString());
				
				for(DoctorClinicSchedule doctorClinicSchedule : doctorClinicScheduleList){
					
					if(doctorClinicSchedule.shift.equals("shift1")){
						
						timeTable1.startTimes = doctorClinicSchedule.form;
						timeTable1.endTimes = doctorClinicSchedule.totime;
						
						if(timeTable1.days == null){
							timeTable1.days = doctorClinicSchedule.day;
						}else{
							if(!timeTable1.days.contains(doctorClinicSchedule.day)){
								timeTable1.days = timeTable1.days + " - "+ doctorClinicSchedule.day;
							}
						}
					}
					if(doctorClinicSchedule.shift.equals("shift2")){
						
						timeTable2.startTimes = doctorClinicSchedule.form;
						timeTable2.endTimes = doctorClinicSchedule.totime;
						
						if(timeTable2.days == null){
							timeTable2.days = doctorClinicSchedule.day;
						}else{
							if(!timeTable2.days.contains(doctorClinicSchedule.day)){
								timeTable2.days = timeTable2.days + " - "+ doctorClinicSchedule.day;
							}
						}
					}
					if(doctorClinicSchedule.shift.equals("shift3")){
						
						timeTable3.startTimes = doctorClinicSchedule.form;
						timeTable3.endTimes = doctorClinicSchedule.totime;
						
						if(timeTable3.days == null){
							timeTable3.days = doctorClinicSchedule.day;
						}else{
							if(!timeTable3.days.contains(doctorClinicSchedule.day)){
								timeTable3.days = timeTable3.days + " - "+ doctorClinicSchedule.day;
							}
						}
					}
					
					
				}
				
				System.out.println(" timeTable1 = "+timeTable1.days);
				ClinicDetailsList.add(new DoctorClinicDetails(decryptedValue, clinic.idClinic.toString(), clinic.clinicName, 
															clinic.location, clinic.landLineNumber.toString(),timeTable1,timeTable2,timeTable3));
			}
			
			System.out.println("ClinicDetailsList = "+ClinicDetailsList.size());
		
			
		}else{
			
		}
		return ok(Json.toJson(ClinicDetailsList));
	}
}
	
