package controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

import controllers.Patient.Error;
import controllers.Patient.ErrorResponse;

import models.Clinic;
import models.DoctorClinicSchedule;
import models.DoctorNotes;
import models.DoctorProcedure;
import models.DoctorRegister;
import models.Invoices;
import models.PatientClientBookAppointment;
import models.PatientRegister;
import models.Person;
import models.SummaryHistory;
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
import sun.misc.BASE64Decoder;
import viewmodel.AlarmReminderVM;
import viewmodel.AllClinicAppointment;
import viewmodel.AllPatientsData;
import viewmodel.AllProcedureVm;
import viewmodel.AllTemplateVm;
import viewmodel.AllTreatmentPlanVm;
import viewmodel.CategoryVm;
import viewmodel.ClinicDoctorVM;
import viewmodel.DoctorClinicDetails;
import viewmodel.DoctorNotesVM;
import viewmodel.DoctorProcedureVm;
import viewmodel.DoctorReportVm;
import viewmodel.DoctorsPatient;
import viewmodel.FieldVm;
import viewmodel.HomeCountDoctorVM;
import viewmodel.ModeVM;
import viewmodel.PDAEditVm;
import viewmodel.PatientClinicsAppointmentVM;
import viewmodel.PersonVM;
import viewmodel.ReminderVM;
import viewmodel.ShiftAppointment;
import viewmodel.ShiftDetails;
import viewmodel.ShowFieldVm;
import viewmodel.SummaryHistoryVM;
import viewmodel.TimeTable;
import viewmodel.TreatementFieldVm;
import viewmodel.TreatmentPlanVm;
import viewmodel.UpdateVM;
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
	     public static Result getAllClinicsWeekAppointment() throws ParseException {
			
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
			SimpleDateFormat standardFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = (Date)formatter1.parse(dateStr);
				date = removeTime(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				date = (Date)standardFormat.parse(dateStr);
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
	    
	   
	     
	    public static Result getAllClinicsAppointment() throws ParseException{
				
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
			SimpleDateFormat standardFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = null;
			try {
				date = (Date)formatter1.parse(dateStr);
				date = removeTime(date);
			} catch (ParseException e) {
				date = (Date)standardFormat.parse(dateStr);
				date = removeTime(date);
				// TODO Auto-generated catch block
			}
			/*List<DoctorClinicSchedule> doctorClinicSchedules = DoctorClinicSchedule.getDoctorClinicScheduleById(""+clinicId,doctorId);
			System.out.println("Clinic Schedule size::::::::::::::"+doctorClinicSchedules.size());
			for(DoctorClinicSchedule doctorClinic : doctorClinicSchedules)
			{
				System.out.println("available"+doctorClinic.availability);
				System.out.println("date"+doctorClinic.date);
				try {
					Date dateDb = (Date)formatter1.parse(dateStr);
					dateDb = removeTime(dateDb);
					System.out.println("DB date:::::::::::"+dateDb.toString());
					System.out.println("date:::::::::::"+date.toString());
					System.out.println("Compare Date::::::::::::"+date.compareTo(dateDb));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}*/
			System.out.println("Appointmnet List= "+appointmentList.size());
			for(PatientClientBookAppointment appointment : appointmentList){
				
					try {
				 		Date dbDate = formatter1.parse(appointment.appointmentDate.toString());
				 		System.out.println("database date= "+dbDate.getTime());
				 		System.out.println("User date= "+date.getTime());
				 	    if(date.getTime() == dbDate.getTime())
				 	    {
							if(ClinicList.size() != 0){
								
								for(AllClinicAppointment clinicAppointment : ClinicList){
									
									//List<ShiftAppointment> shiftDetailsList = new ArrayList<ShiftAppointment>();
									ShiftAppointment shiftDetails = new ShiftAppointment();
									shiftDetails.appointmentType = appointment.visitType;
									shiftDetails.bookTime = appointment.bookTime;
									shiftDetails.timeSlot = appointment.timeSlot;
									shiftDetails.status = appointment.status;
									//shiftDetailsList.add(shiftDetails);
									 if(appointment.patientId != 0)
									 {
										 Person person = Person.getPatientsById(appointment.patientId);
										 PersonVM personVM = new PersonVM();
										 personVM.id = person.patient.toString();
										 personVM.name = person.name;
									
										 shiftDetails.patientInfo = personVM;
									 }
									
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
								allClinicAppointment.status = appointment.status;
								Clinic clinic = Clinic.findClinicById(appointment.clinicId);
								
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
	    
	    public static Result getAllDoctorFinance() throws IOException
	    {
	    	String doctorId = null;
	  		
	  		try {
	  			doctorId = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
	  			
	  		} catch (UnsupportedEncodingException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
	  		System.out.println("doctorId= "+doctorId);
	  		int doctor_id = Person.getDoctorByMail(doctorId);
	  		List<Invoices> list = Invoices.getAllInvoicesByDoctorId(doctor_id);
	  		List<AllTreatmentPlanVm> treatmentList  = new ArrayList<AllTreatmentPlanVm>();
	  		List<AllProcedureVm> allProcedureList = new ArrayList<AllProcedureVm>();
	  		System.out.println("list size:::::::::"+list.size());
	  		for(Invoices invoice : list)
	  		{
	  			System.out.println("AppointmentTime:::::::::"+invoice.appointmentTime);
	  			DoctorProcedure doctorProcedure = DoctorProcedure.doctorProcedureById(invoice.procedureId);
	  			TemplateClass templateClass = TemplateClass.getTemplateClassByProcedureId(invoice.procedureId);
	  			System.out.println("Template:::::::::"+templateClass.templateName);
	  			List<TemplateAttribute> attributes = TemplateAttribute.getAllAttributes(templateClass);
	  			System.out.println("Attribute LIst:::::::::::"+attributes.size());
	  			Boolean procedureFlag = false;
	  			for(AllTreatmentPlanVm planVm : treatmentList){
	  				if(planVm.patientAppointmentDate.equals(invoice.appointmentDate)){
	  					procedureFlag = true;
	  					DoctorProcedure procedureDoctor = DoctorProcedure.doctorProcedureById(invoice.procedureId);
	  					AllProcedureVm allProcedureVm = new AllProcedureVm();
					    allProcedureVm.procedureName = doctorProcedure.procedureName;
					    allProcedureVm.category = doctorProcedure.category;
						allProcedureVm.id = String.valueOf(doctorProcedure.id);
						allProcedureVm.doctorId = String.valueOf(doctorProcedure.doctorId);
						TemplateClass tempClass = TemplateClass.getTemplateClassByProcedureId(invoice.procedureId);
						ArrayList<AllTemplateVm> templates = new ArrayList<AllTemplateVm>();
						AllTemplateVm allTemplateVm = new AllTemplateVm();
						allTemplateVm.templateName = templateClass.templateName;
						allTemplateVm.procedureName = templateClass.procedureName;
						allTemplateVm.doctorId = String.valueOf(doctorProcedure.doctorId);
						List<TemplateAttribute> attributeList = TemplateAttribute.getAllAttributes(templateClass);
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
						templates.add(allTemplateVm);
						allProcedureVm.allTemplate = templates;
						planVm.procedure.add(allProcedureVm);
						
	  				}
	  			}
	  			if(!procedureFlag){
		  		    AllTreatmentPlanVm allTreatmentPlanVm = new AllTreatmentPlanVm();
	  				allTreatmentPlanVm.doctorId = invoice.doctorId;
	  				allTreatmentPlanVm.patientId = invoice.patientId;
	  				allTreatmentPlanVm.patientAppointmentDate = invoice.appointmentDate;
	  				allTreatmentPlanVm.patientAppointmentTime = invoice.appointmentTime;
	  			    TotalInvoice totalInvoice = TotalInvoice.grandTotalAndTotalDue(doctor_id, invoice.patientId, invoice.appointmentDate,invoice.appointmentTime);
	  				//System.out.println("treatmentPlan.procedureId = "+treatmentPlan.procedureId);
	  			    if(totalInvoice == null)
	  			    {
	  			    	System.out.println("Grand Total::::::::"+totalInvoice);
		  				allTreatmentPlanVm.grandTotal = "0.0";
		  				allTreatmentPlanVm.totalDue = "0.0";
		  				allTreatmentPlanVm.discount = "0.0";
		  				allTreatmentPlanVm.tax = "0.0";
		  				allTreatmentPlanVm.advance = "0.0";
		  				allTreatmentPlanVm.total = "0.0";
	  			    }
	  			    else
	  			    {
	  			    	System.out.println("Grand Total::::::::"+totalInvoice);
		  				allTreatmentPlanVm.grandTotal = totalInvoice.grandTotal;
		  				allTreatmentPlanVm.totalDue = totalInvoice.totalDue;
		  				allTreatmentPlanVm.discount = totalInvoice.discount;
		  				allTreatmentPlanVm.tax = totalInvoice.taxValue;
		  				allTreatmentPlanVm.advance = totalInvoice.advance;
		  				allTreatmentPlanVm.total = totalInvoice.total;
	  			    }
	  			   
	  				List <AllProcedureVm>  doctorProcedures = new ArrayList<AllProcedureVm>();
	  				AllProcedureVm allProcedureVm = new AllProcedureVm();
				    allProcedureVm.procedureName = doctorProcedure.procedureName;
				    allProcedureVm.category = doctorProcedure.category;
					allProcedureVm.id = String.valueOf(doctorProcedure.id);
					allProcedureVm.doctorId = String.valueOf(doctorProcedure.doctorId);
					ArrayList<AllTemplateVm> templates = new ArrayList<AllTemplateVm>();
					AllTemplateVm allTemplateVm = new AllTemplateVm();
					allTemplateVm.templateName = templateClass.templateName;
					allTemplateVm.procedureName = templateClass.procedureName;
					allTemplateVm.doctorId = String.valueOf(doctorProcedure.doctorId);
						
						//System.out.println("attributeList = "+attributeList.size());
						
						for(TemplateAttribute attribute : attributes){
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
					templates.add(allTemplateVm);
					allProcedureVm.allTemplate = templates;
					doctorProcedures.add(allProcedureVm);
					allTreatmentPlanVm.procedure = doctorProcedures;
	  				treatmentList.add(allTreatmentPlanVm);
	  			}
	  			
	  		}
	  	
	  	 return ok(Json.toJson(treatmentList));
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
	  					allProcedureVm.id = String.valueOf(invoices.id);
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
	  						List<TemplateAttribute> attributes = TemplateAttribute.getAllAttributes(templateClass);
	  						for(TemplateAttribute att : attributes)
	  						{
	  							System.out.println("Attribute name::::"+att.fieldName);
	  							System.out.println("Attribute Cost::::"+att.fieldDefaultValue);
	  						}
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
	
		public static Result homeCountDoctor() throws Exception{
			
			int patientCount = 0;
			int financeCount = 0;
			int appointmentCount = 0;
			
			String emailId = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
			Person person = Person.getDoctorIdByEmail(emailId);
			int doctorId = person.doctor;
			DoctorRegister doctorRegister = DoctorRegister.getDoctorById(doctorId);
			List<PatientRegister> patients = doctorRegister.patient;
			patientCount = patients.size();
			List<PatientClientBookAppointment> appointments = PatientClientBookAppointment
					                                          .getAllPatientOfDoctor(doctorId);
			appointmentCount = appointments.size();
			List<Invoices> invoices = Invoices.getAllInvoicesByDoctorId(doctorId);
			financeCount = invoices.size();
			HomeCountDoctorVM vm = new HomeCountDoctorVM();
			vm.patientCount = patientCount;
			vm.appointments = appointmentCount;
			vm.financeCount = financeCount;
			return ok(Json.toJson(vm));
			
		}
		public static Result getAllDoctorClinics() throws Exception{
			
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
				DateFormat standardFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = (Date)formatter1.parse(dateStr);
					date = removeTime(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					date = (Date)standardFormat.parse(dateStr);
					e.printStackTrace();
				}
				
				Integer doctor_Id = Person.getDoctorByMail(doctorId);
				System.out.println("doctorId = "+doctorId);
				List <PatientClientBookAppointment> appointmentList = PatientClientBookAppointment.getNextDoctorClinicAppointment(doctor_Id,"Occupied");
				System.out.println("appointmentList = "+appointmentList.size());
				List<Clinic> clinics = Clinic.findAllByDoctorId(doctor_Id);
				DoctorRegister doctor = DoctorRegister.getDoctorById(doctor_Id);
				List<Clinic> clinicList = doctor.clinic;
				for(Clinic cl : clinics){
					if(cl.doctorId == doctor.doctorId){
						clinicList.add(cl);
					}
				}
				System.out.println("Clinic List = "+clinicList.size());
				for(Clinic c: clinicList)
				{
					System.out.println("Clinic Name:::::"+c.clinicName);
					ClinicDoctorVM vm = new ClinicDoctorVM();
					vm.idClinic = c.idClinic;
					vm.clinicName = c.clinicName;
					vm.landLineNumber = c.landLineNumber;
					vm.mobileNumber = c.mobileNumber;
					vm.address = c.address;
					vm.location = c.location;
					vm.email = c.email;
					vm.speciality = c.speciality;
					vm.doctorId = doctor_Id;
					
					
						List<DoctorClinicSchedule> schedules = DoctorClinicSchedule.findAllClinicSchedule(doctorId, ""+c.idClinic);
						for(DoctorClinicSchedule clinicSchedule : schedules)
						{
							if(clinicSchedule.shift.equalsIgnoreCase("shift1"))
							{
								List<DoctorClinicSchedule> schedulesShift1 = DoctorClinicSchedule.findAllClinicScheduleByShift(doctorId, ""+c.idClinic, "shift1");
								ShiftDetails shift1Details = new ShiftDetails();
								shift1Details.shiftTime = clinicSchedule.form+" to "+clinicSchedule.totime;
								shift1Details = getDays(schedulesShift1, shift1Details);
								List<PatientClientBookAppointment> appointments = PatientClientBookAppointment
										.getAllClinicAppointment(doctor_Id, c.idClinic, "Shift1", date);
								shift1Details.appointmentCount = appointments.size();
								if((clinicSchedule.availability == null) || (clinicSchedule.availability.equalsIgnoreCase("Available")))
								{
									shift1Details.shiftAvailability = "Available";
								}
								else
								{
									shift1Details.shiftAvailability = clinicSchedule.availability;
								}
								vm.shift1 = shift1Details;
							}
							if(clinicSchedule.shift.equalsIgnoreCase("shift2"))
							{
								List<DoctorClinicSchedule> schedulesShift2 = DoctorClinicSchedule.findAllClinicScheduleByShift(doctorId, ""+c.idClinic, "shift2");
								ShiftDetails shift2Details = new ShiftDetails();
								shift2Details.shiftTime = clinicSchedule.form+" to "+clinicSchedule.totime;
								shift2Details = getDays(schedulesShift2, shift2Details);
								List<PatientClientBookAppointment> appointments = PatientClientBookAppointment
										.getAllClinicAppointment(doctor_Id, c.idClinic, "Shift2", date);
								shift2Details.appointmentCount = appointments.size();
								if((clinicSchedule.availability == null) || (clinicSchedule.availability.equalsIgnoreCase("Available")))
								{
									shift2Details.shiftAvailability = "Available";
								}
								else
								{
									shift2Details.shiftAvailability = clinicSchedule.availability;
								}
								vm.shift2 = shift2Details;
							}
							if(clinicSchedule.shift.equalsIgnoreCase("shift3"))
							{
								List<DoctorClinicSchedule> schedulesShift3 = DoctorClinicSchedule.findAllClinicScheduleByShift(doctorId, ""+c.idClinic, "shift3");
								ShiftDetails shift3Details = new ShiftDetails();
								shift3Details.shiftTime = clinicSchedule.form+" to "+clinicSchedule.totime;
								shift3Details = getDays(schedulesShift3, shift3Details);
								List<PatientClientBookAppointment> appointments = PatientClientBookAppointment
										.getAllClinicAppointment(doctor_Id, c.idClinic, "Shift3", date);
								shift3Details.appointmentCount = appointments.size();
								if((clinicSchedule.availability == null) || (clinicSchedule.availability.equalsIgnoreCase("Available")))
								{
									shift3Details.shiftAvailability = "Available";
								}
								else
								{
									shift3Details.shiftAvailability = clinicSchedule.availability;
								}
								vm.shift3 = shift3Details;
							}
							
						
					}
					for(PatientClientBookAppointment app : appointmentList)
					{
						System.out.println("Appointment::::::"+app.appointmentDate);
					}
					clinicDoctorVM.add(vm);
					
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
  					allProcedureVm.id = String.valueOf(treatmentPlan.id);
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
	public static Result deleteDoctor()throws IOException 
	{
		String id = URLDecoder.decode(request().getQueryString("id"), "UTF-8");
		int personId = Integer.parseInt(id);
		Person person = Person.getPersonById(personId);
		int doctorId = person.doctor;
		DoctorRegister registerDoctor = DoctorRegister.getDoctorById(doctorId);
		registerDoctor.delete();
		person.delete();
		return ok(Json.toJson("Success"));
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
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person
				.getDoctorByMail(doctorId)));
		List<AllPatientsData> patientDoctor = new ArrayList<AllPatientsData>();
		List<DoctorsPatient> doctorsPatient = new ArrayList<>();
		System.out.println("doctors patient= "+doctor.patient.size());
		for(PatientRegister patient : doctor.patient){
			Person p = Person.getPatientsById(patient.patientId);

			String bookTime = "";
			String bookDate = "";
			String lastVisited="";
			String lastVisitedTime = "";
			int totalAppointment = 0;
			String nextDate = "";
			String nextBookTime = "";
			String nextShift = "";
			Integer clinicId = null;
			Integer patient_id = null;
			Integer lastVisitedClinicId = 0;
			Integer appointmentClinicId= 0;
			AllPatientsData patientData = new AllPatientsData();
			patientData.patientId = ""+p.patient;
			patientData.doctorId = ""+doctor_id;
			patientData.name = p.name;
			patientData.speciality = "";
			patientData.emailID = p.emailID;
			patientData.mobileNumber = p.mobileNumber;
			patientData.location = p.location;
			patientData.dateOfBirth = formatter.format(p.dateOfBirth);
			patientData.gender = p.gender.toString();
			patientData.blood_group = p.bloodGroup;
			patientData.allergic_to = p.allergicTo;
			patientData.type = 1;
			
			List <PatientClientBookAppointment> appointmentList = PatientClientBookAppointment.
					                                              getAllAppointment(doctor_id, p.patient);
			System.out.println("New Size = "+appointmentList.size());
			List<PatientClientBookAppointment> visitedList = new ArrayList<PatientClientBookAppointment>();
			totalAppointment = appointmentList.size();
			for(PatientClientBookAppointment appointment : appointmentList){
				
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
						if(appointment.star == null){
							bookDate = formatter.format(appointment.appointmentDate);
							bookTime = appointment.bookTime;
							appointmentClinicId = appointment.clinicId;
						}
						
					}else{
						nextDate = formatter.format(appointment.appointmentDate);
						nextBookTime = appointment.bookTime;
						nextShift = appointment.shift;
						clinicId = appointment.clinicId;
						patient_id = appointment.patientId;
						break;
					}
					if(appointment.isVisited != null){
						visitedList.add(appointment);
					}
				}
				
				patientData.bookDate = nextDate;
				patientData.bookTime = nextBookTime;
				patientData.shift = nextShift;
				patientData.clinicId = clinicId;
				patientData.appointmentDate = bookDate;
				patientData.appointmentTime = bookTime;
				for(PatientClientBookAppointment visit : visitedList){
					lastVisited = formatter.format(visit.appointmentDate);
					lastVisitedTime = visit.bookTime;
					lastVisitedClinicId = visit.clinicId;
				}
				patientData.lastVisited = lastVisited;
				patientData.lastVisitedTime = lastVisitedTime;
				patientData.totalAppointment = ""+totalAppointment;
				patientData.lastVisitedClinicId = ""+lastVisitedClinicId;
				patientData.appointmentClinicId = ""+appointmentClinicId;
				patientDoctor.add(patientData);
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
				DoctorClinicDetails doctorClinicDetails = new DoctorClinicDetails(decryptedValue, person.name, clinic.idClinic.toString(), clinic.clinicName, 
						clinic.location, clinic.landLineNumber.toString(),timeTable1,timeTable2,timeTable3,clinic.onlineAppointment);
				doctorClinicDetails.clinicMobile = ""+clinic.mobileNumber;
				doctorClinicDetails.clinicEmail = clinic.email;
				doctorClinicDetails.clinicSpeciality = clinic.speciality;
				doctorClinicDetails.doctorEmail = person.emailID;
				ClinicDetailsList.add(doctorClinicDetails);
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
				DoctorClinicDetails clinicDetail = new DoctorClinicDetails();
				clinicDetail.doctorId = person.doctor.toString();
				clinicDetail.doctorName = person.name;
				clinicDetail.clinicId = clinic.idClinic.toString();
				clinicDetail.clinicName = clinic.clinicName;
				clinicDetail.clinicLocation = clinic.location;
				clinicDetail.clinicMobile = clinic.mobileNumber.toString();
				clinicDetail.contactNumber = clinic.landLineNumber.toString();
				clinicDetail.slot1 = timeTable1;
				clinicDetail.slot2 = timeTable2;
				clinicDetail.slot3 = timeTable3;
				clinicDetail.clinicEmail = clinic.email;
				clinicDetail.clinicSpeciality = clinic.speciality;
				clinicDetail.onlineAppointment = clinic.onlineAppointment;
				ClinicDetailsList.add(clinicDetail);
				
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
			invoices.total = totalInvoices.total;
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
			invoices.total = totalInvoices.total;
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
	
	public static Result deleteTreatmentTemplate() throws IOException{
		System.out.println("Delete Treatment Template");
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		String message = "";
		AllProcedureVm procedure = mapper.readValue(request().body().asJson(),AllProcedureVm.class);
		if(procedure != null){
			if(procedure.id != null){
				Long id = Long.parseLong(procedure.id);
				TreatmentPlan treatment = TreatmentPlan.getTreatmentPlanById(id);
				treatment.delete();
				message = "Success";
			}else{
				message = "Failure";
			}
		}else{
			message = "Failure";
		}
		return ok(Json.toJson(message));
		
	}
	public static Result deleteInvoiceTemplate() throws IOException{
		System.out.println("Delete Treatment Template");
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		String message = "";
		AllProcedureVm procedure = mapper.readValue(request().body().asJson(),AllProcedureVm.class);
		if(procedure != null){
			if(procedure.id != null){
				Long id = Long.parseLong(procedure.id);
				Invoices treatment = Invoices.getTreatmentPlanById(id);
				treatment.delete();
				message = "Success";
			}else{
				message = "Failure";
			}
		}else{
			message = "Failure";
		}
		return ok(Json.toJson(message));
		
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
		String value = "";
		if(picture != null){
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
			  value = "Success";
			}else{
				value = "Failure";
			}
		}else{
			value = "Failure";
		}
		
		return ok(Json.toJson(value));
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
	
	public static Result setStatusSlot() throws IOException
	{
		List<DoctorClinicSchedule> doctorClinicSchedules;
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		ModeVM vm = mapper.readValue(request().body().asJson(),ModeVM.class);	
		if(vm.markAs.equalsIgnoreCase("All Slots"))
		{
			doctorClinicSchedules = DoctorClinicSchedule.getClinicScheduleshiftDetails(vm.doctorId, ""+vm.clinicId);
		}
		else if(vm.markAs.equalsIgnoreCase("Selected Slot"))
		{
			doctorClinicSchedules = DoctorClinicSchedule.findAllClinicScheduleByShift(vm.doctorId, ""+vm.clinicId,vm.shift);
		}
		else
		{
			doctorClinicSchedules = DoctorClinicSchedule.findAllClinicScheduleByShift(vm.doctorId, ""+vm.clinicId,vm.shift);
		}
		
		for(DoctorClinicSchedule schedule : doctorClinicSchedules)
		{
				schedule.availability = vm.availability;
				schedule.markAs = vm.markAs;
				schedule.date = vm.date;
				schedule.save();
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
	public static Result saveDoctorAppointmentDetails() throws IOException {

		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();

		PatientClinicsAppointmentVM bookAppointment = mapper.readValue(
				json.traverse(), PatientClinicsAppointmentVM.class);

		System.out.println("bookAppointment.patientId = "
				+ bookAppointment.patientId);

		Integer patient_id = Integer.parseInt(bookAppointment.patientId);

		PatientClientBookAppointment appointment = PatientClientBookAppointment
				.getClinicAppointment(bookAppointment.doctorId, patient_id,
						bookAppointment.clinicId, bookAppointment.shift);

		appointment.clinicId = bookAppointment.clinicId;
		// appointment.patientId = bookAppointment.patientId;
		appointment.patientId = patient_id;// Person.getPatientByMail(bookAppointment.patientId);
		appointment.doctorId = bookAppointment.doctorId;
		appointment.shift = bookAppointment.shift;
		appointment.bookTime = bookAppointment.bookTime;
		appointment.timeSlot = bookAppointment.timeSlot;
		appointment.visitType = bookAppointment.visitType;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// System.out.println("Date  in java = "+format.parse(bookAppointment.appointmentDate));
			appointment.appointmentDate = format
					.parse(bookAppointment.appointmentDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// appointment.appointmentDate = bookAppointment.appointmentDate;
		appointment.status = bookAppointment.status;
		appointment.save();

		return ok(Json.toJson(new ErrorResponse(Error.E304.getCode(),
				Error.E304.getMessage())));

	}

	
}
	
