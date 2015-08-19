package controllers;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import play.Play;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.validator.constraints.URL;
import org.joda.time.DateTime;
import org.joda.time.Days;

import models.Clinic;
import models.DoctorClinicSchedule;
import models.DoctorNotes;
import models.DoctorProcedure;
import models.DoctorRegister;
import models.Invoices;
import models.PatientClientBookAppointment;
import models.PatientRegister;
import models.Person;
import models.Person.GenderType;
import models.ReminderData;
import models.ReminderTimeTable;
import models.TemplateAttribute;
import models.TemplateClass;
import models.TotalInvoice;
import models.TreatementTemplateAttribute;
import models.TreatmentPlan;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData.FilePart;
import play.data.DynamicForm;
import play.libs.Json;
import viewmodel.AlarmReminderVM;
import viewmodel.AllClinicAppointment;
import viewmodel.AllPatientsData;
import viewmodel.AllProcedureVm;
import viewmodel.AllTemplateVm;
import viewmodel.AllTreatmentPlanVm;
import viewmodel.ClinicDoctorVM;
import viewmodel.DoctorClinicDetails;
import viewmodel.DoctorNotesVM;
import viewmodel.DoctorProcedureVm;
import viewmodel.FieldVm;
import viewmodel.PDAEditVm;
import viewmodel.PersonVM;
import viewmodel.ReminderVM;
import viewmodel.ShiftAppointment;
import viewmodel.ShiftDetails;
import viewmodel.ShowFieldVm;
import viewmodel.TimeTable;
import viewmodel.TreatementFieldVm;
import viewmodel.TreatmentPlanVm;
import viewmodel.totalInvoiceVM;

public class Doctor extends Controller {
	
		public static Result index() {
			return ok(views.html.index.render("Your new application is ready."));
		}
		
/*
 * import java.util.Date;
 *  import org.joda.time.DateTime; 
 *  import org.joda.time.Days; 
 *  Date past = new Date(110, 5, 20); // June 20th, 2010 
 *  Date today = new Date(110, 6, 24); // July 24th 
 *  int days = Days.daysBetween(new DateTime(past), new DateTime(today)).getDays(); // => 34		
*/		
	     public static Result getAllClinicsWeekAppointment() {
			
			String doctorId = null;
			String appointmentDate = null;
			String clinicId = null;
			
			try {
				doctorId = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
				
				clinicId = request().getQueryString("clinicId");
				appointmentDate = request().getQueryString("appointmentDate");
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("doctorId = "+doctorId);
			System.out.println("appointmentDate = "+appointmentDate);
			System.out.println("clinicId = "+clinicId);
			
			int doctor_id = Person.getDoctorByMail(doctorId);
	  		int clinic_id = Integer.parseInt(clinicId);
			
	  		List<AllClinicAppointment> ClinicList = new ArrayList<AllClinicAppointment>();
	  		
			List <PatientClientBookAppointment> appointmentList = PatientClientBookAppointment.getAllClinicAppointment(doctor_id,clinic_id);
			
			String dateStr = appointmentDate;
			DateFormat formatter1 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			Date date = null;
			try {
				date = (Date)formatter1.parse(dateStr);
				date = removeTime(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(PatientClientBookAppointment appointment : appointmentList){
				
					try {
				 		Date dbDate = formatter1.parse(appointment.appointmentDate.toString());
						int d = Days.daysBetween(new DateTime(date), new DateTime(dbDate)).getDays();
						System.out.println("Date" + dbDate + " -- " + date + " -- " + d);

						if(d < 7 && d >= 0){

							if(ClinicList.size() != 0){
								DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
								String datestring1 = dateFormat1.format(appointment.appointmentDate);
						  		List<AllClinicAppointment> ClinicListTemp; 
								ClinicListTemp =  new ArrayList<AllClinicAppointment>();
								for(AllClinicAppointment clinicAppointment : ClinicList){
									ClinicListTemp.clear();
									//List<ShiftAppointment> shiftDetailsList = new ArrayList<ShiftAppointment>();
									if(clinicAppointment.appointmentDate.equalsIgnoreCase(datestring1)){
										ShiftAppointment shiftDetails = new ShiftAppointment();
										shiftDetails.appointmentType = appointment.visitType;
										shiftDetails.bookTime = appointment.bookTime;
										shiftDetails.timeSlot = appointment.timeSlot;
										//shiftDetailsList.add(shiftDetails);
										
										 Person person = Person.getPatientsById(appointment.patientId);
										 PersonVM personVM = new PersonVM();
										 personVM.id = person.patient.toString();
										 personVM.name = person.name;
										
										 shiftDetails.patientInfo = personVM;
										
										 if(appointment.shift.equals("shift1")){
											 clinicAppointment.shift1.add(shiftDetails);
										 }else if(appointment.shift.equals("shift2")){
											 clinicAppointment.shift2.add(shiftDetails);
										 }else if(appointment.shift.equals("shift3")){
											 clinicAppointment.shift3.add(shiftDetails);
										 }
									}else{
										AllClinicAppointment allClinicAppointment = new AllClinicAppointment();									
										allClinicAppointment.clinicId = appointment.clinicId;
										allClinicAppointment.doctorId = appointment.doctorId;
										allClinicAppointment.patientId = appointment.patientId;
										//allClinicAppointment.timeSlot = appointment.timeSlot;
										
										DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
										String datestring = dateFormat.format(appointment.appointmentDate); 
										
										allClinicAppointment.appointmentDate = datestring;
										
										List<ShiftAppointment> shiftDetailsList = new ArrayList<ShiftAppointment>();
										
										ShiftAppointment shiftDetails = new ShiftAppointment();
										shiftDetails.appointmentType = appointment.visitType;
										shiftDetails.bookTime = appointment.bookTime;
									    shiftDetails.timeSlot = appointment.timeSlot;
										
										 Person person = Person.getPatientsById(appointment.patientId);
										 PersonVM personVM = new PersonVM();
										 personVM.id = person.patient.toString();
										 personVM.name = person.name;
										
										 shiftDetails.patientInfo = personVM;
										 shiftDetailsList.add(shiftDetails);
										 
										 if(appointment.shift.equals("shift1")){
											 allClinicAppointment.shift1 = shiftDetailsList;
										 }else if(appointment.shift.equals("shift2")){
											 allClinicAppointment.shift2 = shiftDetailsList;
										 }else if(appointment.shift.equals("shift3")){
											 allClinicAppointment.shift3 = shiftDetailsList;
										 }
											ClinicListTemp.add(allClinicAppointment);
									}

								}
								int flag = 1;
								for(AllClinicAppointment clinicAppointment : ClinicListTemp){
									
									for(AllClinicAppointment newObj : ClinicList)
									{
										if((newObj.appointmentDate).equalsIgnoreCase(clinicAppointment.appointmentDate))
										{
											flag = 0;
										}
									}
									
									if(flag == 1)
									{
										ClinicList.add(clinicAppointment);
									}
									else
									{
										flag = 0;
									}
								
									
								}
								
							}else{
								AllClinicAppointment allClinicAppointment = new AllClinicAppointment();
								
								allClinicAppointment.clinicId = appointment.clinicId;
								allClinicAppointment.doctorId = appointment.doctorId;
								allClinicAppointment.patientId = appointment.patientId;
								//allClinicAppointment.timeSlot = appointment.timeSlot;
								
								DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
								String datestring = dateFormat.format(appointment.appointmentDate); 
								
								allClinicAppointment.appointmentDate = datestring;
								
								List<ShiftAppointment> shiftDetailsList = new ArrayList<ShiftAppointment>();
								
								ShiftAppointment shiftDetails = new ShiftAppointment();
								shiftDetails.appointmentType = appointment.visitType;
								shiftDetails.bookTime = appointment.bookTime;
							    shiftDetails.timeSlot = appointment.timeSlot;
								
								 Person person = Person.getPatientsById(appointment.patientId);
								 PersonVM personVM = new PersonVM();
								 personVM.id = person.patient.toString();
								 personVM.name = person.name;
								
								 shiftDetails.patientInfo = personVM;
								 shiftDetailsList.add(shiftDetails);
								 
								 if(appointment.shift.equals("shift1")){
									 allClinicAppointment.shift1 = shiftDetailsList;
								 }else if(appointment.shift.equals("shift2")){
									 allClinicAppointment.shift2 = shiftDetailsList;
								 }else if(appointment.shift.equals("shift3")){
									 allClinicAppointment.shift3 = shiftDetailsList;
								 }
								
								ClinicList.add(allClinicAppointment);
							}
							
						}
					}catch (ParseException e) {
						e.printStackTrace();
					}
				
				}
			
			return ok(Json.toJson(ClinicList));
	     
	    }
	        
	     
	     public static Result getAllClinicsAppointment() {
				
			String doctorId = null;
			String appointmentDate = null;
			String clinicId = null;
			
			try {
				doctorId = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
				
				clinicId = request().getQueryString("clinicId");
				appointmentDate = request().getQueryString("appointmentDate");
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("doctorId = "+doctorId);
			System.out.println("appointmentDate = "+appointmentDate);
			System.out.println("clinicId = "+clinicId);
			
			int doctor_id = Person.getDoctorByMail(doctorId);
	  		int clinic_id = Integer.parseInt(clinicId);
			
	  		List<AllClinicAppointment> ClinicList = new ArrayList<AllClinicAppointment>();
	  		
			List <PatientClientBookAppointment> appointmentList = PatientClientBookAppointment.getAllClinicAppointment(doctor_id,clinic_id);
			
			String dateStr = appointmentDate;
			DateFormat formatter1 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			Date date = null;
			try {
				date = (Date)formatter1.parse(dateStr);
				date = removeTime(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(PatientClientBookAppointment appointment : appointmentList){
				
					try {
				 		Date dbDate = formatter1.parse(appointment.appointmentDate.toString());
						
						if(date.compareTo(dbDate) == 0){
							
							if(ClinicList.size() != 0){
								
								for(AllClinicAppointment clinicAppointment : ClinicList){
									
									//List<ShiftAppointment> shiftDetailsList = new ArrayList<ShiftAppointment>();
									ShiftAppointment shiftDetails = new ShiftAppointment();
									shiftDetails.appointmentType = appointment.visitType;
									shiftDetails.bookTime = appointment.bookTime;
									shiftDetails.timeSlot = appointment.timeSlot;
									//shiftDetailsList.add(shiftDetails);
									
									 Person person = Person.getPatientsById(appointment.patientId);
									 PersonVM personVM = new PersonVM();
									 personVM.id = person.patient.toString();
									 personVM.name = person.name;
									
									 shiftDetails.patientInfo = personVM;
									
									 if(appointment.shift.equals("shift1")){
										 clinicAppointment.shift1.add(shiftDetails);
									 }else if(appointment.shift.equals("shift2")){
										 clinicAppointment.shift2.add(shiftDetails);
									 }else if(appointment.shift.equals("shift3")){
										 clinicAppointment.shift3.add(shiftDetails);
									 }
								}
								
							}else{
								AllClinicAppointment allClinicAppointment = new AllClinicAppointment();
								
								allClinicAppointment.clinicId = appointment.clinicId;
								allClinicAppointment.doctorId = appointment.doctorId;
								allClinicAppointment.patientId = appointment.patientId;
								//allClinicAppointment.timeSlot = appointment.timeSlot;
								
								DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
								String datestring = dateFormat.format(appointment.appointmentDate); 
								
								allClinicAppointment.appointmentDate = datestring;
								
								List<ShiftAppointment> shiftDetailsList = new ArrayList<ShiftAppointment>();
								
								ShiftAppointment shiftDetails = new ShiftAppointment();
								shiftDetails.appointmentType = appointment.visitType;
								shiftDetails.bookTime = appointment.bookTime;
							    shiftDetails.timeSlot = appointment.timeSlot;
								
								 Person person = Person.getPatientsById(appointment.patientId);
								 PersonVM personVM = new PersonVM();
								 personVM.id = person.patient.toString();
								 personVM.name = person.name;
								
								 shiftDetails.patientInfo = personVM;
								 shiftDetailsList.add(shiftDetails);
								 
								 if(appointment.shift.equals("shift1")){
									 allClinicAppointment.shift1 = shiftDetailsList;
								 }else if(appointment.shift.equals("shift2")){
									 allClinicAppointment.shift2 = shiftDetailsList;
								 }else if(appointment.shift.equals("shift3")){
									 allClinicAppointment.shift3 = shiftDetailsList;
								 }
								
								ClinicList.add(allClinicAppointment);
							}
							
						}
					}catch (ParseException e) {
						e.printStackTrace();
					}
				
				}
			
			return ok(Json.toJson(ClinicList));
	     
	    }
	     
	 
				
	    public static Result getAllInvoices() {
	  		
	  		String doctorId = null;
	  		String patientId = null;
	  		String appointmentDate = null;
	  		String appointmentTime = null;
	  		
	  		try {
	  			doctorId = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
	  			patientId = request().getQueryString("patientId");
	  			appointmentDate = request().getQueryString("appointmentDate");
	  			appointmentTime = request().getQueryString("appointmentTime");
	  			
	  		} catch (UnsupportedEncodingException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
	  		
	  		System.out.println("doctorId = "+doctorId);
	  		System.out.println("patientId = "+patientId);
	  		System.out.println("appointmentDate = "+appointmentDate);
	  		System.out.println("appointmentTime = "+appointmentTime);
	  		
	  		int doctor_id = Person.getDoctorByMail(doctorId);
	  		int patient_id = Person.getPatientByMail(patientId);
	  		
	  		List<Invoices> list = Invoices.getAllInvoicesById(doctor_id,patient_id,appointmentDate,appointmentTime);
	  		
	  		AllTreatmentPlanVm allTreatmentPlanVm  = new AllTreatmentPlanVm();
	  		List<AllProcedureVm> allProcedureList = new ArrayList<AllProcedureVm>();
	  			
	  			for(Invoices invoices  : list){
	  				allTreatmentPlanVm.doctorId = invoices.doctorId;
	  				allTreatmentPlanVm.patientId = invoices.patientId;
	  				allTreatmentPlanVm.patientAppointmentDate = invoices.appointmentDate;
	  				allTreatmentPlanVm.patientAppointmentTime = invoices.appointmentTime;
	  				
	  				//System.out.println("treatmentPlan.procedureId = "+treatmentPlan.procedureId);
	  				
	  				DoctorProcedure doctorProcedureList = DoctorProcedure.getAllProcedureByIDdoctorId(invoices.procedureId, doctor_id);
	  				
	  				if(doctorProcedureList != null){
	  					//List <DoctorProcedureVm>  doctorProcedure = new ArrayList<DoctorProcedureVm>();
	  					AllProcedureVm allProcedureVm = new AllProcedureVm();
	  					allProcedureVm.procedureName = doctorProcedureList.procedureName;
	  					allProcedureVm.category = doctorProcedureList.category;
	  					allProcedureVm.id = String.valueOf(doctorProcedureList.id);
	  					allProcedureVm.doctorId = String.valueOf(doctorProcedureList.doctorId);

	  					List<ShowFieldVm> fieldVms = new ArrayList<ShowFieldVm>();
	  					
	  					Boolean checkProcedure = false;
	  					for(AllProcedureVm procedureVm : allProcedureList){
	  						
	  						if(Long.parseLong(procedureVm.id) == doctorProcedureList.id){
	  							
	  							AllTemplateVm allTemplateVm = new AllTemplateVm();
	  							TemplateClass templateClass = TemplateClass.findTemplateById(invoices.templateId);
	  							List <TemplateAttribute> attributeList = TemplateAttribute.getAllAttributes(templateClass);
	  							allTemplateVm.templateName = templateClass.templateName;
	  							allTemplateVm.procedureName = templateClass.procedureName;
	  							
	  							allTemplateVm.doctorId = String.valueOf(doctorProcedureList.doctorId);
	  							
	  							//System.out.println("attributeList = "+attributeList.size());
	  							
	  							for(TemplateAttribute attribute : attributeList){
	  								ShowFieldVm fieldVm = new ShowFieldVm();
	  								fieldVm.fieldDefaultValue = attribute.fieldDefaultValue;
	  								fieldVm.fieldDisplayName = attribute.fieldDisplayName;
	  								fieldVm.fieldId = attribute.fieldId;
	  								fieldVm.templateId = attribute.templateClass.templateId;
	  								fieldVm.fieldName = attribute.fieldName;
	  								fieldVm.fieldType = attribute.fieldType;
	  								allTemplateVm.templates.add(fieldVm);
	  							}
	  							
	  							//allTemplateVm.templates.add(e)
	  							
	  							procedureVm.allTemplate.add(allTemplateVm);
	  							checkProcedure = true;
	  						}
	  					}
	  					
	  					if(!checkProcedure){
	  						
	  						List<ShowFieldVm> allTemplateList = new ArrayList<ShowFieldVm>();
	  						
	  						TemplateClass templateClass = TemplateClass.findTemplateById(invoices.templateId);
	  						
	  						List <TemplateAttribute> attributeList = TemplateAttribute.getAllAttributes(templateClass);
	  						
	  						AllTemplateVm allTemplateVm = new AllTemplateVm();
	  						
	  						allTemplateVm.templateName = templateClass.templateName;
	  						allTemplateVm.procedureName = templateClass.procedureName;
	  						allTemplateVm.doctorId = String.valueOf(doctorProcedureList.doctorId);
	  						
	  						System.out.println("attributeList = "+attributeList.size());
	  						
	  						for(TemplateAttribute attribute : attributeList){
	  							ShowFieldVm fieldVm = new ShowFieldVm();
	  							fieldVm.fieldDefaultValue = attribute.fieldDefaultValue;
	  							fieldVm.fieldDisplayName = attribute.fieldDisplayName;
	  							fieldVm.fieldId = attribute.fieldId;
	  							fieldVm.fieldName = attribute.fieldName;
	  							fieldVm.fieldType = attribute.fieldType;
	  							fieldVm.templateId = attribute.templateClass.templateId;
	  							allTemplateVm.templates.add(fieldVm);
	  						}
	  						
	  						allProcedureVm.allTemplate.add(allTemplateVm);
	  						
	  						allProcedureList.add(allProcedureVm);
	  						
	  					}
	  				}
	  				
	  			}
	  			
	  		allTreatmentPlanVm.procedure = allProcedureList;
	  		
	  		return ok(Json.toJson(allTreatmentPlanVm));
	  		
	  	}
		
	    
		
		public static Result saveInvoices() throws IOException {
			
			JsonNode json = request().body().asJson();
			System.out.println("saveTreatmentPlan json" + json);
			
			ObjectMapper mapper = new ObjectMapper();
			TreatmentPlanVm planVm = mapper.readValue(json.traverse(),TreatmentPlanVm.class);
			
			int doctor_id = Person.getDoctorByMail(planVm.doctorId);
			int patient_id = Person.getPatientByMail(planVm.patientId);
			
			Invoices invoices = Invoices.checkInvoicesById(doctor_id,patient_id,Integer.parseInt(planVm.procedureId),
					Integer.parseInt(planVm.templateId),planVm.patientAppointmentDate,planVm.patientAppointmentTime);
			
			if(invoices == null)
			{
				
				invoices = new Invoices();
				invoices.doctorId = doctor_id;//Person.getDoctorByMail(planVm.doctorId);
				invoices.patientId =  patient_id;//Person.getPatientByMail(planVm.patientId);
				invoices.procedureId = Integer.parseInt(planVm.procedureId);
				invoices.templateId = Integer.parseInt(planVm.templateId);
				invoices.appointmentDate = planVm.patientAppointmentDate;
				invoices.appointmentTime = planVm.patientAppointmentTime;
				invoices.save();
				
			}else{
				invoices = new Invoices();
			}
			
			return ok(Json.toJson(invoices));
		}
	
	
		public static Result getAllDoctorClinics() {
			
		       String doctorId = null;
		       String currentDate = null;
		       List<ClinicDoctorVM> clinicDoctorVM = new ArrayList<ClinicDoctorVM>();
		       
				try {
					doctorId = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
					currentDate = request().getQueryString("date");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String dateStr = currentDate;
				DateFormat formatter1 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				Date date = null;
				try {
					date = (Date)formatter1.parse(dateStr);
					date = removeTime(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Integer doctor_Id = Person.getDoctorByMail(doctorId);
				System.out.println("doctorId = "+doctorId);
				List <PatientClientBookAppointment> appointmentList = PatientClientBookAppointment.getNextDoctorClinicAppointment(doctor_Id,"Occupied");
				System.out.println("appointmentList = "+appointmentList.size());
				if(appointmentList.size() > 0){
					
					for(PatientClientBookAppointment appointment : appointmentList){
						
						try {
					 		Date dbDate = formatter1.parse(appointment.appointmentDate.toString());
							
							if(date.compareTo(dbDate) == 0){
				        		//System.out.println("Date1 is equal to Date2");
				        		if(clinicDoctorVM.size() != 0){
				        			boolean checkAdd = false;
				        			for(int i = 0; i<clinicDoctorVM.size(); i++){
				        			    ClinicDoctorVM doctorVM = clinicDoctorVM.get(i);
				        				if(doctorVM.idClinic.equals(appointment.clinicId)){
				        					//System.out.println("In if !!!"+appointment.bookTime);
				        					checkAdd = true;
				        					
				        					ShiftDetails shiftValue = new ShiftDetails();
				        					if(appointment.shift.equals("shift1")){
				        						ShiftDetails shiftValue1 = 	doctorVM.shift1;
				        						
				        						if(shiftValue1 == null){
				        							shiftValue1 = new ShiftDetails();
				        							shiftValue1.appointmentCount = 1;
				        							shiftValue1.shiftTime = appointment.timeSlot;
				        							shiftValue1.shiftTime = appointment.timeSlot;
				        							List<DoctorClinicSchedule> doctorClinicScheduleList = DoctorClinicSchedule.findAllClinicScheduleByShift(doctorId, appointment.clinicId.toString(),"shift1");
				        							doctorVM.shift1 = getDays(doctorClinicScheduleList,shiftValue1);
				        						}else {
				        							shiftValue1.shiftTime = appointment.timeSlot;
				        							shiftValue1.appointmentCount = shiftValue1.appointmentCount + 1;
				        						}
				        						
				        					}else if(appointment.shift.equals("shift2")){
				        						
				        						ShiftDetails shiftValue2 = 	doctorVM.shift2;
				        						
				        						if(shiftValue2 == null){
				        							shiftValue2 = new ShiftDetails();
				        							shiftValue2.appointmentCount = 1;
				        							shiftValue2.shiftTime = appointment.timeSlot;
				        							List<DoctorClinicSchedule> doctorClinicScheduleList = DoctorClinicSchedule.findAllClinicScheduleByShift(doctorId, appointment.clinicId.toString(),"shift2");
				        							doctorVM.shift2 = getDays(doctorClinicScheduleList,shiftValue2);
				        							
				        						}else {
				        							shiftValue2.shiftTime = appointment.timeSlot;
				        							shiftValue2.appointmentCount = shiftValue2.appointmentCount + 1;
				        							doctorVM.shift2 = shiftValue2;
				        						}
				        						
				        					}else if(appointment.shift.equals("shift3")){
				        						shiftValue.shiftTime = appointment.timeSlot;
				        						doctorVM.shift3 = shiftValue;
				        						ShiftDetails shiftValue3 = 	doctorVM.shift3;
				        						
				        						if(shiftValue3 == null){
				        							shiftValue3 = new ShiftDetails();
				        							shiftValue3.appointmentCount = 1;
				        							shiftValue3.shiftTime = appointment.timeSlot;
				        							List<DoctorClinicSchedule> doctorClinicScheduleList = DoctorClinicSchedule.findAllClinicScheduleByShift(doctorId, appointment.clinicId.toString(),"shift3");
				        							doctorVM.shift3 = getDays(doctorClinicScheduleList,shiftValue3);
				        						}else {
				        							shiftValue3.shiftTime = appointment.timeSlot;
				        							shiftValue3.appointmentCount = shiftValue3.appointmentCount + 1;
				        							doctorVM.shift3 = shiftValue3;
				        						}
				        						
				        					}
				        				}
				        			}
				        			if(!checkAdd){
				        				clinicDoctorVM.add(getClinicDetails(appointment));
				        			}
				        			
				        		}else{
				        			
				        			clinicDoctorVM.add(getClinicDetails(appointment));
				        		}
				        		
				        	}
							
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
			    }
				
				return ok(Json.toJson(clinicDoctorVM));
			}
		
		
		public static ClinicDoctorVM getClinicDetails(PatientClientBookAppointment appointment ) {
			Clinic clinic = Clinic.findClinicById(appointment.clinicId);
			
			ClinicDoctorVM clinicVM = new ClinicDoctorVM();
			clinicVM.idClinic = clinic.idClinic;
			clinicVM.clinicName = clinic.clinicName;
			clinicVM.landLineNumber = clinic.landLineNumber;
			clinicVM.mobileNumber = clinic.mobileNumber;
			clinicVM.location = clinic.location;
			clinicVM.email = clinic.email;
			clinicVM.doctorId = clinic.doctorId;
			
			Person doctor = Person.getDoctorsById(appointment.doctorId);
			
			ShiftDetails shiftValue = new ShiftDetails();
			shiftValue.appointmentCount = 1;
			
			if(appointment.shift.equals("shift1")){
				shiftValue.shiftTime = appointment.timeSlot;
				List<DoctorClinicSchedule> doctorClinicScheduleList = DoctorClinicSchedule.findAllClinicScheduleByShift(doctor.emailID, clinic.idClinic.toString(),"shift1");
				clinicVM.shift1 = getDays(doctorClinicScheduleList,shiftValue);
			}else if(appointment.shift.equals("shift2")){
				shiftValue.shiftTime = appointment.timeSlot;
				List<DoctorClinicSchedule> doctorClinicScheduleList = DoctorClinicSchedule.findAllClinicScheduleByShift(doctor.emailID, clinic.idClinic.toString(),"shift2");
				clinicVM.shift2 = getDays(doctorClinicScheduleList,shiftValue);
			}else if(appointment.shift.equals("shift3")){
				shiftValue.shiftTime = appointment.timeSlot;
				List<DoctorClinicSchedule> doctorClinicScheduleList = DoctorClinicSchedule.findAllClinicScheduleByShift(doctor.emailID, clinic.idClinic.toString(),"shift3");
				clinicVM.shift3 = getDays(doctorClinicScheduleList,shiftValue);
			}
	        return clinicVM;
	    }
		
		public static ShiftDetails getDays(List<DoctorClinicSchedule> doctorClinicScheduleList, ShiftDetails shiftValue) {
	        
			for(int i = 0; i<doctorClinicScheduleList.size(); i++ ){
				DoctorClinicSchedule doctorClinicSchedule = doctorClinicScheduleList.get(i);
				if(shiftValue.days == null){
					shiftValue.days = doctorClinicSchedule.day;
				}else{
					if(!shiftValue.days.contains(doctorClinicSchedule.day)){
						shiftValue.days = shiftValue.days + " - "+ doctorClinicSchedule.day;
					}
				}
			}
			
	        return shiftValue;
	    }
		
		public static Date removeTime(Date date) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MILLISECOND, 0);
	        return cal.getTime();
	    }
		
      public static Result getAllTreatmentPlan() {
  		
  		String doctorId = null;
  		String patientId = null;
  		String appointmentDate = null;
  		String appointmentTime = null;
  		
  		try {
  			doctorId = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
  			patientId = request().getQueryString("patientId");
  			appointmentDate = request().getQueryString("appointmentDate");
  			appointmentTime = request().getQueryString("appointmentTime");
  			
  		} catch (UnsupportedEncodingException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		
  		System.out.println("doctorId = "+doctorId);
  		System.out.println("patientId = "+patientId);
  		System.out.println("appointmentDate = "+appointmentDate);
  		System.out.println("appointmentTime = "+appointmentTime);
  		
  		int doctor_id = Person.getDoctorByMail(doctorId);
  		int patient_id = Person.getPatientByMail(patientId);
  		
  		List<TreatmentPlan> list = TreatmentPlan.getAllTreatmentPlanById(doctor_id,patient_id,appointmentDate,appointmentTime);
  		
  		AllTreatmentPlanVm allTreatmentPlanVm  = new AllTreatmentPlanVm();
  		List<AllProcedureVm> allProcedureList = new ArrayList<AllProcedureVm>();
  			
  			for(TreatmentPlan treatmentPlan  : list){
  				allTreatmentPlanVm.doctorId = treatmentPlan.doctorId;
  				allTreatmentPlanVm.patientId = treatmentPlan.patientId;
  				allTreatmentPlanVm.patientAppointmentDate = treatmentPlan.appointmentDate;
  				allTreatmentPlanVm.patientAppointmentTime = treatmentPlan.appointmentTime;
  				
  				//System.out.println("treatmentPlan.procedureId = "+treatmentPlan.procedureId);
  				
  				DoctorProcedure doctorProcedureList = DoctorProcedure.getAllProcedureByIDdoctorId(treatmentPlan.procedureId, doctor_id);
  				
  				if(doctorProcedureList != null){
  					//List <DoctorProcedureVm>  doctorProcedure = new ArrayList<DoctorProcedureVm>();
  					AllProcedureVm allProcedureVm = new AllProcedureVm();
  					allProcedureVm.procedureName = doctorProcedureList.procedureName;
  					allProcedureVm.category = doctorProcedureList.category;
  					allProcedureVm.id = String.valueOf(doctorProcedureList.id);
  					allProcedureVm.doctorId = String.valueOf(doctorProcedureList.doctorId);

  					List<ShowFieldVm> fieldVms = new ArrayList<ShowFieldVm>();
  					
  					Boolean checkProcedure = false;
  					for(AllProcedureVm procedureVm : allProcedureList){
  						
  						if(Long.parseLong(procedureVm.id) == doctorProcedureList.id){
  							
  							AllTemplateVm allTemplateVm = new AllTemplateVm();
  							TemplateClass templateClass = TemplateClass.findTemplateById(treatmentPlan.templateId);
  							List <TreatementTemplateAttribute> attributeList = TreatementTemplateAttribute.getAllAttributes(templateClass);
  							allTemplateVm.templateName = templateClass.templateName;
  							allTemplateVm.procedureName = templateClass.procedureName;
  							
  							allTemplateVm.doctorId = String.valueOf(doctorProcedureList.doctorId);
  							
  							//System.out.println("attributeList = "+attributeList.size());
  							
  							for(TreatementTemplateAttribute attribute : attributeList){
  								ShowFieldVm fieldVm = new ShowFieldVm();
  								fieldVm.fieldDefaultValue = attribute.fieldDefaultValue;
  								fieldVm.fieldDisplayName = attribute.fieldDisplayName;
  								fieldVm.fieldId = attribute.fieldId;
  								fieldVm.templateId = attribute.templateClass.templateId;
  								fieldVm.fieldName = attribute.fieldName;
  								fieldVm.fieldType = attribute.fieldType;
  								allTemplateVm.templates.add(fieldVm);
  							}
  							
  							//allTemplateVm.templates.add(e)
  							
  							procedureVm.allTemplate.add(allTemplateVm);
  							checkProcedure = true;
  						}
  					}
  					
  					if(!checkProcedure){
  						
  						List<ShowFieldVm> allTemplateList = new ArrayList<ShowFieldVm>();
  						
  						TemplateClass templateClass = TemplateClass.findTemplateById(treatmentPlan.templateId);
  						
  						List <TreatementTemplateAttribute> attributeList = TreatementTemplateAttribute.getAllAttributes(templateClass);
  						
  						AllTemplateVm allTemplateVm = new AllTemplateVm();
  						
  						allTemplateVm.templateName = templateClass.templateName;
  						allTemplateVm.procedureName = templateClass.procedureName;
  						allTemplateVm.doctorId = String.valueOf(doctorProcedureList.doctorId);
  						
  						System.out.println("attributeList = "+attributeList.size());
  						
  						for(TreatementTemplateAttribute attribute : attributeList){
  							ShowFieldVm fieldVm = new ShowFieldVm();
  							fieldVm.fieldDefaultValue = attribute.fieldDefaultValue;
  							fieldVm.fieldDisplayName = attribute.fieldDisplayName;
  							fieldVm.fieldId = attribute.fieldId;
  							fieldVm.fieldName = attribute.fieldName;
  							fieldVm.fieldType = attribute.fieldType;
  							fieldVm.templateId = attribute.templateClass.templateId;
  							allTemplateVm.templates.add(fieldVm);
  						}
  						
  						//allTemplateVm.templates.add(e)
  						
  						allProcedureVm.allTemplate.add(allTemplateVm);
  						//allTemplateVm.allTemplate.add(allTemplateList);
  						
  						allProcedureList.add(allProcedureVm);
  						
  					}
  				}
  				
  			}
  			
  		allTreatmentPlanVm.procedure = allProcedureList;
  		
  		return ok(Json.toJson(allTreatmentPlanVm));
  		
  	}
	
	
    public static Result saveTreatmentPlan() throws IOException {
		
		JsonNode json = request().body().asJson();
		System.out.println("saveTreatmentPlan json" + json);
		ObjectMapper mapper = new ObjectMapper();
		TreatmentPlanVm planVm = mapper.readValue(json.traverse(),TreatmentPlanVm.class);
		
		int doctor_id = Person.getDoctorByMail(planVm.doctorId);
		int patient_id = Person.getPatientByMail(planVm.patientId);
		
		TreatmentPlan treatmentPlan = TreatmentPlan.checkTreatmentPlanById(doctor_id,patient_id,Integer.parseInt(planVm.procedureId),
				Integer.parseInt(planVm.templateId),planVm.patientAppointmentDate,planVm.patientAppointmentTime);
		
		if(treatmentPlan == null){
			
			treatmentPlan = new TreatmentPlan();
			treatmentPlan.doctorId = doctor_id;//Person.getDoctorByMail(planVm.doctorId);
			treatmentPlan.patientId =  patient_id;//Person.getPatientByMail(planVm.patientId);
			treatmentPlan.procedureId = Integer.parseInt(planVm.procedureId);
			treatmentPlan.templateId = Integer.parseInt(planVm.templateId);
			treatmentPlan.appointmentDate = planVm.patientAppointmentDate;
			treatmentPlan.appointmentTime = planVm.patientAppointmentTime;
			treatmentPlan.save();
			
		}else{
			treatmentPlan = new TreatmentPlan();
		}
		
		Invoices invoices = Invoices.checkInvoicesById(doctor_id,patient_id,Integer.parseInt(planVm.procedureId),
				Integer.parseInt(planVm.templateId),planVm.patientAppointmentDate,planVm.patientAppointmentTime);
		
		if(invoices == null){
			
			invoices = new Invoices();
			invoices.doctorId = doctor_id;//Person.getDoctorByMail(planVm.doctorId);
			invoices.patientId =  patient_id;//Person.getPatientByMail(planVm.patientId);
			invoices.procedureId = Integer.parseInt(planVm.procedureId);
			invoices.templateId = Integer.parseInt(planVm.templateId);
			invoices.appointmentDate = planVm.patientAppointmentDate;
			invoices.appointmentTime = planVm.patientAppointmentTime;
			invoices.save();
			
		}else{
			invoices = new Invoices();
		}
		
		
		return ok(Json.toJson(treatmentPlan));
	}
	
	
	public static Result removeSelectedFields() throws UnsupportedEncodingException{
		
		JsonNode json = request().body().asJson();
		//System.out.println("Callled");
		System.out.println("removeSelectedFields json "+json);
		ObjectMapper mapper = new ObjectMapper();
		
		List<FieldVm> fieldVm = null;
		try {
			fieldVm = mapper.readValue(request().body().asJson(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, FieldVm.class));
		}catch(Exception e){
			e.printStackTrace();
		}


		for(FieldVm vm : fieldVm){
			TemplateAttribute templateClass = TemplateAttribute.getById(Integer.parseInt(vm.fieldId)); //getAttribute(Integer.parseInt(vm.fieldId));
			if(templateClass != null){
				templateClass.delete();
			}
		}
		
		return ok(Json.toJson("ok"));
	}
	
	
	public static Result addTemplateAllField(){
		JsonNode json = request().body().asJson();
		//System.out.println("Callled");
		System.out.println("json "+json);
		ObjectMapper mapper = new ObjectMapper();
		//List<FieldVm> fieldVm = mapper.readValue(json.traverse(), FieldVm.class);
		TemplateAttribute field = null;
		List<FieldVm> fieldVm = null;
		try {
			fieldVm = mapper.readValue(request().body().asJson(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, FieldVm.class));
			
			//System.out.println(" vm.templateId = "+vm.templateId);
			
			for(FieldVm vm : fieldVm){
				
				if(vm.fieldId.equals("")){
					
					field = new TemplateAttribute();
					field.fieldName = vm.fieldName;
					field.fieldType = vm.fieldType;
					field.fieldDisplayName = vm.fieldDisplayName;
					field.fieldDefaultValue = vm.fieldDefaultValue;
					field.templateClass = TemplateClass.findTemplateById(Integer.parseInt(vm.templateId));
					field.save();
					
					//System.out.println("Saved !!!!");
					
				}else{
					
					field = TemplateAttribute.getAttribute(Integer.parseInt(vm.fieldId));
					field.fieldName = vm.fieldName;
					field.fieldType = vm.fieldType;
					field.fieldDisplayName = vm.fieldDisplayName;
					field.fieldDefaultValue = vm.fieldDefaultValue;
					field.templateClass = TemplateClass.findTemplateById(Integer.parseInt(vm.templateId));
					field.update();
					//System.out.println("update !!!!");
				}
			}
				
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ok(Json.toJson(fieldVm));
		
	}
	
     public static Result getDoctorProcedures() {
		
		String doctorId = null;
		
		try {
			doctorId = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer doctor_id = Person.getDoctorByMail(doctorId);
		
		List <DoctorProcedure> doctorProcedureList = DoctorProcedure.getAllProcedureByDoctorId(doctor_id);
		
		if(doctorProcedureList != null){
			
			List <DoctorProcedureVm>  doctorProcedure = new ArrayList<DoctorProcedureVm>();
			
			for(DoctorProcedure procedure : doctorProcedureList){
				int count = 0;
				count = TemplateClass.getTemplateCount(doctor_id, procedure.procedureName);
				DoctorProcedureVm procedureVm = new DoctorProcedureVm();
				procedureVm.setDoctorId(doctor_id.toString());
				procedureVm.setId(String.valueOf(procedure.id));
				procedureVm.setProcedureName(procedure.procedureName);
				procedureVm.setNumberOfTemplate(Integer.toString(count));
				procedureVm.setCategory(procedure.category);
				doctorProcedure.add(procedureVm);
			}
			
			return ok(Json.toJson(doctorProcedure));
		}else{
			return ok(Json.toJson(0));
		}
	}
	
	public static Result saveDoctorProcedures() throws IOException {
		
		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();
		
		DoctorProcedureVm  doctorNotesVM = mapper.readValue(json.traverse(),DoctorProcedureVm.class);
		DoctorProcedure doctorProcedure = new DoctorProcedure();
		
		Integer doctor_id = Person.getDoctorByMail(doctorNotesVM.getDoctorId());
		
		doctorProcedure.doctorId = doctor_id;
		doctorProcedure.procedureName = doctorNotesVM.getProcedureName();
		doctorProcedure.category = doctorNotesVM.getCategory();
		
		DoctorProcedure  procedure = DoctorProcedure.searchProcedureByprocedureName(doctorNotesVM.getProcedureName());
		
		if(procedure == null){
			doctorProcedure.save();
		}else{
			doctorProcedure = new DoctorProcedure();
		}
		
		return ok(Json.toJson(doctorProcedure));
	}
	
	public static Result saveDoctorNotes() throws IOException {
		
		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();
		
		DoctorNotesVM  doctorNotesVM = mapper.readValue(json.traverse(),DoctorNotesVM.class);
		
		DoctorNotes doctorNotes = DoctorNotes.checkRegisterDoctorNotes(doctorNotesVM.getDoctorId(), doctorNotesVM.getPatientId(), 
									doctorNotesVM.getAppointmentDate(), doctorNotesVM.getAppointmentTime());
		
		if(doctorNotes == null){
			doctorNotes = new DoctorNotes();
			doctorNotes.doctorId = doctorNotesVM.getDoctorId();
			doctorNotes.patientId = doctorNotesVM.getPatientId();
			doctorNotes.symptoms = doctorNotesVM.getSymptoms();
			doctorNotes.doctorNotes = doctorNotesVM.getDoctorNotes();
			doctorNotes.diagnosis = doctorNotesVM.getDiagnosis();
			doctorNotes.appointmentDate = doctorNotesVM.getAppointmentDate();
			doctorNotes.appointmentTime = doctorNotesVM.getAppointmentTime();
			doctorNotes.save();
		
		}else{
			doctorNotes.doctorId = doctorNotesVM.getDoctorId();
			doctorNotes.patientId = doctorNotesVM.getPatientId();
			doctorNotes.symptoms = doctorNotesVM.getSymptoms();
			doctorNotes.doctorNotes = doctorNotesVM.getDoctorNotes();
			doctorNotes.diagnosis = doctorNotesVM.getDiagnosis();
			doctorNotes.appointmentDate = doctorNotesVM.getAppointmentDate();
			doctorNotes.appointmentTime = doctorNotesVM.getAppointmentTime();
			doctorNotes.update();
		}
		
		return ok(Json.toJson(doctorNotesVM));
	}
	
	
	public static Result getDoctorNotes() {
		
		
		String doctorId = null;
		String patientId = null;
		String appointmentDate = null;
		String appointmentTime = null;
		
		try {
			doctorId = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
			patientId = request().getQueryString("patientId");
			appointmentDate = request().getQueryString("appointmentDate");
			appointmentTime = request().getQueryString("appointmentTime");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DoctorNotes doctorNotes = DoctorNotes.checkRegisterDoctorNotes(doctorId, patientId, appointmentDate, appointmentTime);
		
		if(doctorNotes != null){
			return ok(Json.toJson(doctorNotes));
		}else{
			return ok(Json.toJson(new DoctorNotes()));
		}
		
	}
	
	
	
	public static Result getAllPatientInformation(){
		String doctorId = null;
		try {
			doctorId = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer doctor_id = Person.getDoctorByMail(doctorId);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String Nextdate = "";
		String nextBookTime = "";
		String nextShift = "";
		Integer clinicId = null;
		Integer patient_id = null;
		String lastVisted = null;
		
		List<AllPatientsData> patientDoctor = new ArrayList<AllPatientsData>();
		
		List <PatientClientBookAppointment> appointmentList = PatientClientBookAppointment.getAllPatientOfDoctor(doctor_id);
		System.out.println("Size = "+appointmentList.size());
		
		if(appointmentList.size() > 0){
			
			//Collections.sort(appointmentList, new CustomDateComparator());
			Collections.sort(appointmentList, new Comparator<PatientClientBookAppointment>() {
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
			    		return 1;
			    	}else if(calOne.compareTo(calTwo) == -1){
			    		return -1;
			    	}else{
			    		return 0;
			    	}
			    	
			    }
			});
			
			for(int i = 0; i < appointmentList.size(); i++){
				PatientClientBookAppointment appointment = appointmentList.get(i);
				
				String[] timeValue;
		    	Calendar calOne = Calendar.getInstance();
		    	calOne.setTime(appointment.appointmentDate);
		    	timeValue = appointment.bookTime.split(":");
		    	
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
		     	calOne.set(Calendar.AM_PM, 1);
		     	
		     	Calendar calTwo = Calendar.getInstance();
				
				if(calOne.getTimeInMillis() < calTwo.getTimeInMillis()){
					Nextdate = "";
					nextBookTime = "";
					nextShift = "";
					clinicId = null;
					
				}else{
					Nextdate = formatter.format(appointment.appointmentDate);
					nextBookTime = appointment.bookTime;
					nextShift = appointment.shift;
					clinicId = appointment.clinicId;
					patient_id = appointment.patientId;
					break;
				}
			}
	   }
		
		//end Next appointmnet
		
		for(PatientClientBookAppointment appointment : appointmentList){
			
			Person p = Person.getAllPatientsById(appointment.patientId);
			if(p != null){
				boolean check = false;
				for(AllPatientsData patients : patientDoctor){
					if(patients.emailID.equals(p.emailID)){
						check = true;
					}
				}
				if(!check){
					
					if(patient_id != null && p.patient == patient_id){
						Nextdate = formatter.format(appointment.appointmentDate);
						nextBookTime = appointment.bookTime;
						nextShift = appointment.shift;
						clinicId = appointment.clinicId;
					}else{
						Nextdate = null;
						nextBookTime = null;
						nextShift = null;
						clinicId = null;
					}
					
					patientDoctor.add(new AllPatientsData(doctor_id.toString(),p.patient.toString(),
							p.name, "", p.emailID, p.mobileNumber,
							p.location, p.dateOfBirth.toString(), p.gender.toString(),p.bloodGroup,p.allergicTo, 1, Nextdate,nextBookTime,nextShift,clinicId,lastVisted));
				}
			}
		}
		
		return ok(Json.toJson(patientDoctor));
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
		
		System.out.println("decryptedValue = "+decryptedValue);
		System.out.println("type = "+type);
		
		Person person = Person.getPersonByDoctorID(decryptedValue);
		String doctorEmail = person.emailID;
		List<DoctorClinicDetails> ClinicDetailsList = new ArrayList<DoctorClinicDetails>();
		
		if(type.equals("1")){
			
			clinicList = Clinic.findAllByDoctorId(Integer.parseInt(decryptedValue));
			
			for(Clinic clinic : clinicList){
				
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
				
				ClinicDetailsList.add(new DoctorClinicDetails(decryptedValue, person.name, clinic.idClinic.toString(), clinic.clinicName, 
															clinic.location, clinic.landLineNumber.toString(),timeTable1,timeTable2,timeTable3));
			}
				
		}else{
			
		}
		return ok(Json.toJson(ClinicDetailsList));
	}
	
public static Result getAllDoctorPatientClinics() {
		
		
		String doctorId = null;
		String patientId = null;
		List<Clinic> clinicList = null;
		
		try {
			doctorId = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
			patientId = request().getQueryString("patientId");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("doctorId = "+doctorId);
		System.out.println("patientId = "+patientId);
		
		Person person = Person.getDoctorIdByEmail(doctorId);
		String doctorEmail = person.emailID;
		List<DoctorClinicDetails> ClinicDetailsList = new ArrayList<DoctorClinicDetails>();
		
			clinicList = Clinic.findAllByDoctorId(person.doctor);
			
			for(Clinic clinic : clinicList){
				
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
				
				ClinicDetailsList.add(new DoctorClinicDetails(person.doctor.toString(), person.name, clinic.idClinic.toString(), clinic.clinicName, 
										clinic.location, clinic.landLineNumber.toString(),timeTable1,timeTable2,timeTable3));
			}
		
		return ok(Json.toJson(ClinicDetailsList));
	}
	public static Result saveTotalInvoice() throws IOException{
		JsonNode json = request().body().asJson();
		System.out.println("saveTreatmentPlan json" + json);
		
		ObjectMapper mapper = new ObjectMapper();
		totalInvoiceVM totalInvoices = mapper.readValue(json.traverse(),totalInvoiceVM.class);
		
		int doctor_id = Person.getDoctorByMail(totalInvoices.doctorId);
		int patient_id = Person.getPatientByMail(totalInvoices.patientId);
		TotalInvoice invoices = TotalInvoice.returnTotalInvoice(doctor_id, patient_id, 
				totalInvoices.getAppointmentDate(), totalInvoices.getAppointmentTime());
		
		if(invoices == null){
			
			invoices = new TotalInvoice();
			invoices.doctorId = doctor_id;
			invoices.patientId =  patient_id;
			invoices.appointmentDate = totalInvoices.appointmentDate;
			invoices.appointmentTime = totalInvoices.appointmentTime;
			invoices.grandTotal = totalInvoices.grandTotal;
			invoices.discount = totalInvoices.discount;
			invoices.taxValue = totalInvoices.taxValue;
			invoices.percentageDiscount = totalInvoices.percentageDiscount;
			invoices.percentageTax = totalInvoices.percentageTax;
			invoices.advance = totalInvoices.advance;
			invoices.totalDue = totalInvoices.totalDue;
			invoices.shareWithPatient = totalInvoices.shareWithPatient;

			invoices.save();
		}else{

			invoices.doctorId = doctor_id;
			invoices.patientId =  patient_id;
			invoices.appointmentDate = totalInvoices.appointmentDate;
			invoices.appointmentTime = totalInvoices.appointmentTime;
			invoices.grandTotal = totalInvoices.grandTotal;
			invoices.discount = totalInvoices.discount;
			invoices.taxValue = totalInvoices.taxValue;
			invoices.percentageDiscount = totalInvoices.percentageDiscount;
			invoices.percentageTax = totalInvoices.percentageTax;
			invoices.advance = totalInvoices.advance;
			invoices.totalDue = totalInvoices.totalDue;
			invoices.shareWithPatient = totalInvoices.shareWithPatient;
			invoices.update();
		}
		
		return ok(Json.toJson(invoices));
	
	}
	public static Result getTotalInvoice() throws IOException{
		String doctorId = null;
		String patientId = null;
		String appointmentDate = null;
		String appointmentTime = null;
		doctorId = request().getQueryString("doctorId");
		patientId = request().getQueryString("patientId");
		int docId = Person.getDoctorByMail(doctorId);
		int patId = Person.getPatientByMail(patientId);
		appointmentDate = request().getQueryString("appointmentDate");
		appointmentTime = request().getQueryString("appointmentTime");
		
		TotalInvoice totalInvoice = TotalInvoice.returnTotalInvoice(docId, patId, appointmentDate, appointmentTime);
		
		if(totalInvoice != null){
			return ok(Json.toJson(totalInvoice));
		}else{
			return ok(Json.toJson(new TotalInvoice()));
		}
	}
	
	
	public static Result saveTreatementTemplate() throws IOException{
		System.out.println("saveTreatementTemplate ");
		JsonNode json = request().body().asJson();
		//System.out.println("Callled");
		//System.out.println("json "+json);
		ObjectMapper mapper = new ObjectMapper();
		//List<FieldVm> fieldVm = mapper.readValue(json.traverse(), FieldVm.class);
		TreatementTemplateAttribute field = null;
		List<TreatmentPlanVm> fieldVm = null;
		TreatmentPlanVm planVm = mapper.readValue(request().body().asJson(),TreatmentPlanVm.class);
		System.out.println("fieldVm "+planVm);
		System.out.println("planVm.fieldArrayList.size()"+			planVm.fieldArrayList.size());
		
		int doctor_id = Person.getDoctorByMail(planVm.doctorId);
		int patient_id = Person.getPatientByMail(planVm.patientId);
		
		TreatmentPlan treatmentPlan = TreatmentPlan.checkTreatmentPlanById(doctor_id,patient_id,Integer.parseInt(planVm.procedureId),
				Integer.parseInt(planVm.templateId),planVm.patientAppointmentDate,planVm.patientAppointmentTime);
		
		if(treatmentPlan == null){
			
			treatmentPlan = new TreatmentPlan();
			treatmentPlan.doctorId = doctor_id;//Person.getDoctorByMail(planVm.doctorId);
			treatmentPlan.patientId =  patient_id;//Person.getPatientByMail(planVm.patientId);
			treatmentPlan.procedureId = Integer.parseInt(planVm.procedureId);
			treatmentPlan.templateId = Integer.parseInt(planVm.templateId);
			treatmentPlan.appointmentDate = planVm.patientAppointmentDate;
			treatmentPlan.appointmentTime = planVm.patientAppointmentTime;
			treatmentPlan.save();
			
		}else{
			treatmentPlan = new TreatmentPlan();
		}
		
	
			for(TreatementFieldVm vm : planVm.fieldArrayList){

				if(vm.fieldId.equals("")){
					
					field = new TreatementTemplateAttribute();
					field.fieldName = vm.fieldName;
					field.fieldType = vm.fieldType;
					field.fieldDisplayName = vm.fieldDisplayName;
					field.fieldDefaultValue = vm.fieldDefaultValue;
					field.templateClass = TemplateClass.findTemplateById(Integer.parseInt(vm.templateId));
					field.doctorId = doctor_id;
					field.patientId = patient_id;
					field.save();
					
					//System.out.println("Saved !!!!");
					
				}else{
					
					field = TreatementTemplateAttribute.getAttribute(Integer.parseInt(vm.fieldId));
					field.fieldName = vm.fieldName;
					field.fieldType = vm.fieldType;
					field.fieldDisplayName = vm.fieldDisplayName;
					field.fieldDefaultValue = vm.fieldDefaultValue;
					field.templateClass = TemplateClass.findTemplateById(Integer.parseInt(vm.templateId));
					field.doctorId = doctor_id;
					field.patientId = patient_id;
					field.update();
					//System.out.println("update !!!!");
				}
			}
	
		return ok(Json.toJson(planVm));
		
	}
	
	
	public static Result saveShareWithPatientTotalInvoice() throws IOException{
		JsonNode json = request().body().asJson();
		System.out.println("saveShareWithPatientTotalInvoice json" + json);
		
		ObjectMapper mapper = new ObjectMapper();
		totalInvoiceVM totalInvoices = mapper.readValue(json.traverse(),totalInvoiceVM.class);
		int doctor_id = Person.getDoctorByMail(totalInvoices.doctorId);
		int patient_id = Person.getPatientByMail(totalInvoices.patientId);
		TotalInvoice invoices = TotalInvoice.returnTotalInvoice(doctor_id, patient_id, 
				totalInvoices.getAppointmentDate(), totalInvoices.getAppointmentTime());

		if(invoices == null){
			
			invoices = new TotalInvoice();
			invoices.doctorId = doctor_id;
			invoices.patientId =  patient_id;
			invoices.appointmentDate = totalInvoices.appointmentDate;
			invoices.appointmentTime = totalInvoices.appointmentTime;
			invoices.grandTotal = "";
			invoices.discount = "";
			invoices.taxValue = "";
			invoices.percentageDiscount = "";
			invoices.percentageTax = "";
			invoices.advance = "";
			invoices.totalDue = "";
			invoices.shareWithPatient = totalInvoices.shareWithPatient;

			invoices.save();
		}else{
			System.out.println("saveShareWithPatientTotalInvoice json" + invoices.shareWithPatient + totalInvoices.shareWithPatient);
			invoices.doctorId = doctor_id;
			invoices.patientId =  patient_id;
			invoices.appointmentDate = totalInvoices.appointmentDate;
			invoices.appointmentTime = totalInvoices.appointmentTime;
			invoices.grandTotal = invoices.grandTotal;
			invoices.discount = invoices.discount;
			invoices.taxValue = invoices.taxValue;
			invoices.percentageDiscount = invoices.percentageDiscount;
			invoices.percentageTax = invoices.percentageTax;
			invoices.advance = invoices.advance;
			invoices.totalDue = invoices.totalDue;
			
			invoices.setShareWithPatient(totalInvoices.shareWithPatient);
			invoices.update();
		}
		return ok(Json.toJson(invoices));
	}
	
	public static Result updateTreatementTemplate() throws IOException{
		System.out.println("updateTreatementTemplate " + request().body().asJson());
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		TreatementFieldVm treatementField = mapper.readValue(request().body().asJson(),TreatementFieldVm.class);
		
		TreatementTemplateAttribute treatmentAttribue = TreatementTemplateAttribute.getAttribute(Integer.parseInt(treatementField.fieldId));
		
		int doctor_id = Person.getDoctorByMail(treatementField.doctorId);
		int patient_id = Person.getPatientByMail(treatementField.patientId);			
		
		if(treatmentAttribue != null){
			treatmentAttribue.setFieldName(treatementField.fieldName);
			treatmentAttribue.setFieldType(treatementField.fieldType);
			treatmentAttribue.setFieldDisplayName(treatementField.fieldDisplayName);
			treatmentAttribue.setFieldDefaultValue(treatementField.fieldDefaultValue);
			treatmentAttribue.setTemplateClass(TemplateClass.findTemplateById(Integer.parseInt(treatementField.templateId)));
			treatmentAttribue.setDoctorId(doctor_id);
			treatmentAttribue.setPatientId(patient_id);
			treatmentAttribue.update();			
		}

		return ok(Json.toJson(treatementField));
		
	}
	public static Result getProfileDoctor() throws IOException
	{
		String email = request().getQueryString("email");
		Person person = Person.getPersonByMail(email);
		PDAEditVm vm = new PDAEditVm();
		vm.setName(person.name);
		vm.setEmailID(person.emailID);
		vm.setPassword(person.password);
		vm.setMobileNumber(""+person.mobileNumber);
		vm.setGender(""+person.gender);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		vm.setDateOfBirth(""+df.format(person.dateOfBirth));
		vm.setLocation(person.location);
		vm.setBloodGroup(person.bloodGroup);
		vm.setUrl(person.url);
		vm.setId(person.idPerson);
		DoctorRegister doctor = DoctorRegister.getDoctorById(person.doctor);
		vm.setSpeciality(doctor.speciality);
		return ok(Json.toJson(vm));
	}
	public static Result profilePictureUpdate() throws IOException
	{
		List<String> specialCharactersInSolr = Arrays.asList(new String[]{
		            "+", "-", "&&", "||", "!", "(", ")", "{", "}", "[", "]", "^",
		            "~", "*", "?", ":","\"","\\"," "});
		String path = "";
		play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
		DynamicForm form = DynamicForm.form().bindFromRequest();
		FilePart picture = body.getFile("picture");
		String email = form.get("email");
		Person person = Person.getPersonByMail(email);
		File file = new File(person.url);
		Boolean result = file.delete();
		if(result)
		{
			 String fileName = picture.getFilename();
			 String contentType = picture.getContentType(); 
			 File fileSave = picture.getFile();
			 String fileNameString = fileName;
			 for(String s : specialCharactersInSolr)
			 {
			     if(fileNameString.contains(s))
			     {
			    	fileNameString = fileNameString.replace(""+s, "");
			     }
			  }
			  fileName = fileNameString;
			  System.out.println("File name::::::"+fileName);
			  File  newFile = new File(Play.application().configuration().getString("profile_pic_url_doctors")+ "//"+  fileName);
			  fileSave.renameTo(newFile);
			  path = Play.application().configuration().getString("profile_pic_url_doctors")+"/" + fileName;
			  System.out.println("Path:::::::"+path);
			  System.out.println("Person name:::::"+person.name);
			  System.out.println("fileNameString::::::"+fileNameString);
			  System.out.println("fileName" + fileNameString);
			  System.out.println("contentType" + contentType);
			  System.out.println("file" + file.getAbsolutePath());
			  person.url = path;
			  person.update();
		}
		
		return ok(Json.toJson("Success"));
    }
	
	public static Result updateDoctorProfile() throws IOException 
	{
		GenderType genderJSon;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		ObjectMapper mapper = new ObjectMapper();
		PDAEditVm personVm = mapper.readValue(request().body().asJson(),PDAEditVm.class);
		Person person = Person.getPersonByMail(personVm.getEmailID());
		person.name = personVm.getName();
		person.location = personVm.getLocation();
		person.password = personVm.getPassword();
		person.bloodGroup = personVm.getBloodGroup();
		genderJSon = GenderType.valueOf(personVm.getGender());
		person.gender = genderJSon;
		DoctorRegister doctor = DoctorRegister.getDoctorById(person.doctor);
		doctor.speciality = personVm.getSpeciality();
		doctor.update();
		
		try
		{
			person.dateOfBirth = format.parse(personVm.getDateOfBirth());
			person.update();
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		
		return ok(Json.toJson("Success"));
	}
	
	public static Result getAllSpeciality() throws IOException
	{
		Set<String> speciality = new HashSet<String>();
		List<DoctorRegister> doctors = DoctorRegister.getAllDoctorSepeciality();
		for(DoctorRegister doc : doctors)
		{
			speciality.add(doc.speciality);
			System.out.println("Speciality:::::"+doc.speciality);
		}
		
		return ok(Json.toJson(speciality));
	}
	
	
}
	
