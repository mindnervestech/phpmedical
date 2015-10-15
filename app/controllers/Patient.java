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
import java.util.List;
import java.util.Set;

import models.AssistentRegister;
import models.BucketDoctors;
import models.Clinic;
import models.DoctorClinicSchedule;
import models.DoctorRegister;
import models.PatientClientBookAppointment;
import models.PatientRegister;
import models.Person;
import models.ReminderData;
import models.ReminderTimeTable;
import models.SummaryHistory;
import models.Person.GenderType;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData.FilePart;
import viewmodel.AlarmReminderVM;
import viewmodel.ClinicDoctorVM;
import viewmodel.DoctorClinicDetails;
import viewmodel.FeedbackVM;
import viewmodel.HomeCountPatientVM;
import viewmodel.PDAEditVm;
import viewmodel.PatientAppointmentVM;
import viewmodel.PatientClinicsAppointmentVM;
import viewmodel.PatientsDoctor;
import viewmodel.RegisterVM;
import viewmodel.ReminderVM;
import viewmodel.TimeTable;
import play.data.DynamicForm;
import play.libs.Json;

import org.codehaus.jackson.map.ObjectMapper;

import controllers.Application.Error;
import controllers.Application.ErrorResponse;

public class Patient extends Controller {

	public static Result index() {
		return ok(views.html.index.render("Your new application is ready."));
	}

	public static Result getAlldoctorsOfClinic() {

		String clinicId = null;

		try {
			clinicId = URLDecoder.decode(request().getQueryString("clinicId"),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("clinicId = " + clinicId);
		List<DoctorClinicDetails> ClinicDetailsList = new ArrayList<DoctorClinicDetails>();
		Clinic clinic = Clinic.findClinicById(Integer.parseInt(clinicId));

		List<DoctorClinicSchedule> doctorList = DoctorClinicSchedule
				.findAllDoctorClinicSchedule(clinicId);

		List<String> doctorListTemp = new ArrayList<String>();
		System.out.println("doctorClinicScheduleList = " + doctorList.size());

		for (DoctorClinicSchedule doctorClinicList : doctorList) {
			if (!doctorListTemp.contains(doctorClinicList.docId)) {
				doctorListTemp.add(doctorClinicList.docId);
			}
		}
		System.out.println("doctorListTemp =  " + doctorListTemp.size());

		for (String doctoID : doctorListTemp) {
			List<DoctorClinicSchedule> doctorClinicScheduleList = DoctorClinicSchedule
					.findAllClinicSchedule(doctoID, clinicId);
			System.out.println("doctorClinicScheduleList size  = "
					+ doctorClinicScheduleList.size());

			TimeTable timeTable1 = new TimeTable();
			TimeTable timeTable2 = new TimeTable();
			TimeTable timeTable3 = new TimeTable();

			for (DoctorClinicSchedule doctorClinicSchedule : doctorClinicScheduleList) {

				if (doctorClinicSchedule.shift.equals("shift1")) {

					timeTable1.startTimes = doctorClinicSchedule.form;
					timeTable1.endTimes = doctorClinicSchedule.totime;

					if (timeTable1.days == null) {
						timeTable1.days = doctorClinicSchedule.day;
					} else {
						if (!timeTable1.days.contains(doctorClinicSchedule.day)) {
							timeTable1.days = timeTable1.days + " - "
									+ doctorClinicSchedule.day;
						}
					}
				}
				if (doctorClinicSchedule.shift.equals("shift2")) {

					timeTable2.startTimes = doctorClinicSchedule.form;
					timeTable2.endTimes = doctorClinicSchedule.totime;

					if (timeTable2.days == null) {
						timeTable2.days = doctorClinicSchedule.day;
					} else {
						if (!timeTable2.days.contains(doctorClinicSchedule.day)) {
							timeTable2.days = timeTable2.days + " - "
									+ doctorClinicSchedule.day;
						}
					}
				}
				if (doctorClinicSchedule.shift.equals("shift3")) {

					timeTable3.startTimes = doctorClinicSchedule.form;
					timeTable3.endTimes = doctorClinicSchedule.totime;

					if (timeTable3.days == null) {
						timeTable3.days = doctorClinicSchedule.day;
					} else {
						if (!timeTable3.days.contains(doctorClinicSchedule.day)) {
							timeTable3.days = timeTable3.days + " - "
									+ doctorClinicSchedule.day;
						}
					}
				}

			}

			Person person = Person.getDoctorsByEmailId(doctoID);

			ClinicDetailsList.add(new DoctorClinicDetails(person.doctor
					.toString(), person.name, clinic.idClinic.toString(),
					clinic.clinicName, clinic.location, clinic.landLineNumber
							.toString(), timeTable1, timeTable2, timeTable3,clinic.onlineAppointment,person.emailID));

		}

		return ok(Json.toJson(ClinicDetailsList));
	}
	
	public static Result saveClinicAppointment() throws IOException
	{
		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();

		PatientClinicsAppointmentVM bookAppointment = mapper.readValue(
				json.traverse(), PatientClinicsAppointmentVM.class);

		System.out.println("bookAppointment.patientId = "
				+ bookAppointment.patientId);
		Integer patient_id = 0;
		try
		{
			patient_id = Integer.parseInt(bookAppointment.patientId);
		}
		catch(Exception e)
		{
		    patient_id = Person.getPatientByMail(bookAppointment.patientId);
			
		}
		PatientClientBookAppointment appointment = PatientClientBookAppointment
				.getClinicAppointment(bookAppointment.doctorId, patient_id,
						bookAppointment.clinicId, null);
		appointment.clinicId = bookAppointment.clinicId;
		// appointment.patientId = bookAppointment.patientId;
		appointment.patientId = patient_id;// Person.getPatientByMail(bookAppointment.patientId);
		appointment.doctorId = bookAppointment.doctorId;
		appointment.shift = bookAppointment.shift;
		appointment.bookTime = bookAppointment.bookTime;
		appointment.timeSlot = null;
		appointment.visitType = bookAppointment.visitType;
		appointment.status = bookAppointment.status;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			appointment.appointmentDate = format
					.parse(bookAppointment.appointmentDate);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		appointment.save();
		return ok(Json.toJson(new ErrorResponse(Error.E304.getCode(),
				Error.E304.getMessage())));
	}

	public static Result saveClinicsAppointmentDetails() throws IOException {

		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();

		PatientClinicsAppointmentVM bookAppointment = mapper.readValue(
				json.traverse(), PatientClinicsAppointmentVM.class);

		System.out.println("bookAppointment.patientId = "
				+ bookAppointment.patientId);

		Integer patient_id = Person.getPatientByMail(bookAppointment.patientId);

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

	public static Result savePatientReminder() throws IOException {

		JsonNode json = request().body().asJson();
		System.out.println("json " + json);
		ObjectMapper mapper = new ObjectMapper();
		ReminderVM reminderVM = mapper.readValue(json.traverse(),
				ReminderVM.class);

		ReminderData reminderData = null;

		if (reminderVM.id != null) {
			reminderData = ReminderData.getReminderDataById(reminderVM.id);
			if (reminderData != null) {
				reminderData.id = reminderVM.id;
			} else {
				reminderData = new ReminderData();
			}

		} else {
			reminderData = new ReminderData();
		}

/*		Integer patient_id = Person.getPatientByMail(reminderVM.patientId);
		reminderData.doctorId = reminderVM.doctorId;
		reminderData.appointmentDate = reminderVM.appointmentDate;
		reminderData.appointmentTime = reminderVM.appointmentTime;
		reminderData.doctorId = reminderVM.doctorId;
		reminderData.patientId = patient_id;
		reminderData.medicinName = reminderVM.medicinName;
		reminderData.startDate = reminderVM.startDate;
		reminderData.endDate = reminderVM.endDate;
		reminderData.duration = reminderVM.duration;
		reminderData.numberOfDoses = reminderVM.numberOfDoses;
		reminderData.schedule = reminderVM.schedule;
		reminderData.doctorInstruction = reminderVM.doctorInstruction;
		reminderData.visitDate = reminderVM.visitDate;
		reminderData.visitType = reminderVM.visitType;
		reminderData.referredBy = reminderVM.referredBy;
		reminderData.symptoms = reminderVM.symptoms;
		reminderData.diagnosis = reminderVM.diagnosis;
		reminderData.medicinePrescribed = reminderVM.medicinePrescribed;
		reminderData.testsPrescribed = reminderVM.testsPrescribed;*/
		
		Integer patient_id = Person.getPatientByMail(reminderVM.patientId);
		System.out.println("patientId::::::"+patient_id);
		reminderData.setDoctorId(reminderVM.doctorId);
		reminderData.setAppointmentDate(reminderVM.appointmentDate);
		reminderData.setAppointmentTime(reminderVM.appointmentTime);
		reminderData.setPatientId(patient_id);
		reminderData.setMedicinName(reminderVM.medicinName);
		reminderData.setStartDate(reminderVM.startDate);
		reminderData.setEndDate(reminderVM.endDate);
		reminderData.setDuration(reminderVM.duration);
		reminderData.setNumberOfDoses(reminderVM.numberOfDoses);
		reminderData.setSchedule(reminderVM.schedule);
		reminderData.setDoctorInstruction(reminderVM.doctorInstruction);
		reminderData.setVisitDate(reminderVM.visitDate);
		reminderData.setVisitType(reminderVM.visitType);
		reminderData.setReferredBy(reminderVM.referredBy);
		reminderData.setSymptoms(reminderVM.symptoms);
		reminderData.setDiagnosis(reminderVM.diagnosis);
		reminderData.setMedicinePrescribed(reminderVM.medicinePrescribed);
		reminderData.setTestsPrescribed(reminderVM.testsPrescribed);
		
		SummaryHistory summaryHistory = new SummaryHistory();
		Person person = Person.getPersonByDoctorID(""+reminderVM.doctorId);
		summaryHistory.doctorId = person.emailID;
		summaryHistory.patientId = reminderVM.patientId;
		summaryHistory.symptoms = reminderVM.symptoms;
		summaryHistory.diagonosis = reminderVM.diagnosis;
		summaryHistory.medicalPrescribed = reminderVM.medicinName;
		summaryHistory.testPrescribed = reminderVM.testsPrescribed;
		Date currentDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		summaryHistory.curDate = format.format(currentDate);
		System.out.println("Date:::::::::"+summaryHistory.curDate);
		summaryHistory.appointmentDate = reminderVM.appointmentDate;
		summaryHistory.appointmentTime = reminderVM.appointmentTime;
		summaryHistory.type = reminderVM.ownerType;
		summaryHistory.save();
		/*		if (reminderVM.alarmReminderVMList != null) {
			List<AlarmReminderVM> alarmReminderVMList = reminderVM.alarmReminderVMList;

			for (AlarmReminderVM alarmReminderVM : alarmReminderVMList) {
				System.out.println("alarmReminderVM.id =  "
						+ alarmReminderVM.id);
				if (alarmReminderVM.id != null) {
					ReminderTimeTable reminderTimeTables = ReminderTimeTable
							.getreminderTimeTableById(alarmReminderVM.id);
					reminderTimeTables.alarmDate = alarmReminderVM.alarmDate;
					reminderTimeTables.time1 = alarmReminderVM.time1;
					reminderTimeTables.time2 = alarmReminderVM.time2;
					reminderTimeTables.time3 = alarmReminderVM.time3;
					reminderTimeTables.time4 = alarmReminderVM.time4;
					reminderTimeTables.time5 = alarmReminderVM.time5;
					reminderTimeTables.time6 = alarmReminderVM.time6;
					reminderTimeTables.update();
				} else {
					ReminderTimeTable reminderTimeTables = new ReminderTimeTable();
					reminderTimeTables.alarmDate = alarmReminderVM.alarmDate;
					reminderTimeTables.time1 = alarmReminderVM.time1;
					reminderTimeTables.time2 = alarmReminderVM.time2;
					reminderTimeTables.time3 = alarmReminderVM.time3;
					reminderTimeTables.time4 = alarmReminderVM.time4;
					reminderTimeTables.time5 = alarmReminderVM.time5;
					reminderTimeTables.time6 = alarmReminderVM.time6;
					reminderData.reminderTimeTables.add(reminderTimeTables);
				}
			}
		}*/
		
		if (reminderVM.alarmReminderVMList != null)
		{
			if (reminderVM.id != null) 
			{
				List<ReminderTimeTable> listTime = ReminderTimeTable.getAllReminderTimeTableById(reminderVM.id);
//				System.out.println("listTime " + listTime);
				for (ReminderTimeTable alarmReminder : listTime) 
				{
					alarmReminder.delete();
				}

				List<AlarmReminderVM> alarmReminderVMList = reminderVM.alarmReminderVMList;
				for (AlarmReminderVM alarmReminderVM : alarmReminderVMList) 
				{
					ReminderTimeTable reminderTimeTables = new ReminderTimeTable();
					reminderTimeTables.alarmDate = alarmReminderVM.alarmDate;
					reminderTimeTables.time1 = alarmReminderVM.time1;
					reminderTimeTables.time2 = alarmReminderVM.time2;
					reminderTimeTables.time3 = alarmReminderVM.time3;
					reminderTimeTables.time4 = alarmReminderVM.time4;
					reminderTimeTables.time5 = alarmReminderVM.time5;
					reminderTimeTables.time6 = alarmReminderVM.time6;
					reminderData.reminderTimeTables.add(reminderTimeTables);
				}
			}
		    else
		    {
				List<AlarmReminderVM> alarmReminderVMList = reminderVM.alarmReminderVMList;
				for (AlarmReminderVM alarmReminderVM : alarmReminderVMList) 
				{
					ReminderTimeTable reminderTimeTables = new ReminderTimeTable();
					reminderTimeTables.alarmDate = alarmReminderVM.alarmDate;
					reminderTimeTables.time1 = alarmReminderVM.time1;
					reminderTimeTables.time2 = alarmReminderVM.time2;
					reminderTimeTables.time3 = alarmReminderVM.time3;
					reminderTimeTables.time4 = alarmReminderVM.time4;
					reminderTimeTables.time5 = alarmReminderVM.time5;
					reminderTimeTables.time6 = alarmReminderVM.time6;
					reminderData.reminderTimeTables.add(reminderTimeTables);
				}
		}
		
	    System.out.println("id:::::"+reminderVM.id);
		if (reminderVM.id != null) 
		{
			System.out.println("Update" + reminderVM.id);
			reminderData.update();
		} else 
		{
			reminderData.save();
		}
	  }
		reminderData.save();
		return ok(Json.toJson(reminderVM));
	}

	public static Result getAllPatientClinics() {

		String patientId = null;

		try {
			patientId = URLDecoder.decode(
					request().getQueryString("patientId"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("patientId = " + patientId);
		Integer patient_id = Person.getPatientByMail(patientId);

		List<Clinic> clinicList = Clinic.getAllClinic();

		List<PatientClientBookAppointment> appointmentList = PatientClientBookAppointment
				.getNextClinicAppointment(patient_id, "Occupied");

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String Nextdate = "";
		String nextBookTime = "";
		String nextShift = "";
		Integer clinicId = null;

		System.out.println("appointmentList = "+appointmentList.size());
		if (appointmentList.size() > 0) {

			// Collections.sort(appointmentList, new CustomDateComparator());
			Collections.sort(appointmentList,
					new Comparator<PatientClientBookAppointment>() {
						public int compare(PatientClientBookAppointment chair1,
								PatientClientBookAppointment chair2) {

							String[] timeValue;
							Calendar calOne = Calendar.getInstance();
							calOne.setTime(chair1.appointmentDate);
							timeValue = chair1.bookTime.split(":");

							int hour1 = Integer.parseInt(timeValue[0].trim());
							int min1 = Integer.parseInt(timeValue[1].trim()
									.split("[a-zA-Z ]+")[0]);
							calOne.set(Calendar.HOUR, hour1);
							calOne.set(Calendar.MINUTE, min1);

							String strAM_PM = timeValue[1].replaceAll("[0-9]",
									"");
							if (strAM_PM.equals("AM")) {
								calOne.set(Calendar.AM_PM, 0);
							} else {
								calOne.set(Calendar.AM_PM, 1);
							}
							Calendar calTwo = Calendar.getInstance();
							calTwo.setTime(chair2.appointmentDate);
							timeValue = chair2.bookTime.split(":");

							int hour2 = Integer.parseInt(timeValue[0].trim());
							int min2 = Integer.parseInt(timeValue[1].trim()
									.split("[a-zA-Z ]+")[0]);
							calTwo.set(Calendar.HOUR, hour2);
							String strAM_PM2 = timeValue[1].replaceAll("[0-9]",
									"");

							if (strAM_PM2.equals("AM")) {
								calTwo.set(Calendar.AM_PM, 0);
							} else {
								calTwo.set(Calendar.AM_PM, 1);
							}
							calOne.set(Calendar.AM_PM, 1);
							calTwo.set(Calendar.MINUTE, min2);

							if (calOne.compareTo(calTwo) == 1) {
								return 1;
							} else if (calOne.compareTo(calTwo) == -1) {
								return -1;
							} else {
								return 0;
							}

						}
					});

			
			for (int i = 0; i < appointmentList.size(); i++) 
			{
				PatientClientBookAppointment appointment = appointmentList
						.get(i);
				
				String[] timeValue;
				Calendar calOne = Calendar.getInstance();
				calOne.setTime(appointment.appointmentDate);
				timeValue = appointment.bookTime.split(":");

				int hour1 = Integer.parseInt(timeValue[0].trim());
				int min1 = Integer.parseInt(timeValue[1].trim().split(
						"[a-zA-Z ]+")[0]);
				calOne.set(Calendar.HOUR, hour1);
				calOne.set(Calendar.MINUTE, min1);

				String strAM_PM = timeValue[1].replaceAll("[0-9]", "");
				if (strAM_PM.equals("AM")) {
					calOne.set(Calendar.AM_PM, 0);
				} else {
					calOne.set(Calendar.AM_PM, 1);
				}
				calOne.set(Calendar.AM_PM, 1);

				Calendar calTwo = Calendar.getInstance();
				System.out.println("Condition::::::"+(calOne.getTimeInMillis() < calTwo.getTimeInMillis()));

				if (calOne.getTimeInMillis() < calTwo.getTimeInMillis()) {
					Nextdate = "";
					nextBookTime = "";
					nextShift = "";
					clinicId = null;

				} else {
					Nextdate = formatter.format(appointment.appointmentDate);
					nextBookTime = appointment.bookTime;
					nextShift = appointment.shift;
					clinicId = appointment.clinicId;
					System.out.println("App clinicId= "+appointment.clinicId);
					break;
				}

			}

		}
		
		List<PatientClientBookAppointment> visitedList = new ArrayList<PatientClientBookAppointment>();
		for (PatientClientBookAppointment appointment : appointmentList) {
			
			if (appointment.isVisited != null) {
				if (appointment.isVisited == 1) {
					visitedList.add(appointment);
				}
			}
		}

		String lastVisted = null;

		for (PatientClientBookAppointment appointment : visitedList) {
			Calendar calOne = Calendar.getInstance();
			calOne.setTime(appointment.appointmentDate);
			Calendar calTwo = Calendar.getInstance();
			if (calOne.getTimeInMillis() < calTwo.getTimeInMillis()) {
				lastVisted = formatter.format(calOne.getTime());

			}
		}

		System.out.println("Nextdate = " + Nextdate);
		System.out.println("nextBookTime = " + nextBookTime);
		System.out.println("nextShift = " + nextShift);
		System.out.println("clinicId = " + clinicId);
		System.out.println("lastVisted = " + lastVisted);

		List<ClinicDoctorVM> clinicDoctorVM = new ArrayList<ClinicDoctorVM>();

		/*
		 * public Integer idClinic; public String clinicName; public Long
		 * landLineNumber; public Long mobileNumber; public String address;
		 * public String location; public String email; public Integer doctorId;
		 * public String bookDate; public String bookTime; public String shift;
		 * public String lastVisited;
		 */
		
		for (Clinic clinic : clinicList) {
			int appointmentCount = 0;
			ClinicDoctorVM doctorVM = new ClinicDoctorVM();
			doctorVM.idClinic = clinic.idClinic;
			doctorVM.clinicName = clinic.clinicName;
			doctorVM.landLineNumber = clinic.landLineNumber;
			doctorVM.mobileNumber = clinic.mobileNumber;
			doctorVM.address = clinic.address;
			doctorVM.location = clinic.location;
			doctorVM.email = clinic.email;
			doctorVM.doctorId = clinic.doctorId;
			doctorVM.doctorEmail = Person.getDoctorsById(clinic.doctorId).emailID;
			for(PatientClientBookAppointment appointment : appointmentList)
			{
				if(appointment.clinicId == Integer.valueOf(clinic.idClinic))
				{
					appointmentCount = appointmentCount + 1;
				}
			}
			System.out.println("Appointment Count= "+appointmentCount);
			doctorVM.totalAppointmentCount = ""+appointmentCount;


			System.out.println("clinic.doctorId = " + clinic.doctorId);
			System.out.println("clinicId = " + clinicId);

			if (clinicId != null) {
				if (clinicId.equals(Integer.valueOf(clinic.idClinic))) {
					
					doctorVM.bookDate = Nextdate;
					doctorVM.bookTime = nextBookTime;
					doctorVM.shift = nextShift;
					doctorVM.lastVisited = lastVisted;
					
				}
			}
			clinicDoctorVM.add(doctorVM);
		}

		return ok(Json.toJson(clinicDoctorVM));
	}

	public static Result getPatientReminder() throws Exception {

		String decryptedValue = null;
		String patientId = null;
		String appointmentDate = null;
		String appointmentTime = null;

			decryptedValue = request().getQueryString("doctorId");
			patientId = request().getQueryString("patientId");
			appointmentDate = request().getQueryString("appointmentDate");
			appointmentTime = request().getQueryString("appointmentTime");

			System.out.println("decryptedValue = " + decryptedValue);
			System.out.println("patientId = " + patientId);
			System.out.println("appointmentDate = " + appointmentDate);
			System.out.println("appointmentTime = " + appointmentTime);
			
			try{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = dateFormat.parse(appointmentDate);
				SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-yyyy");
				System.out.println("new Date::::::"+monthFormat.format(date));
				appointmentDate = monthFormat.format(date);
			}
			catch(Exception e)
			{
				
			}
			
			
		long doctor_id = Long.parseLong(decryptedValue);
		// long patient_id = Long.parseLong(patientId);
		Integer patient_id = Person.getPatientByMail(patientId);

		System.out.println("patient_id = " + patient_id);
		ReminderData reminderData = ReminderData.getAllReminderDataById(
				doctor_id, patient_id, appointmentDate, appointmentTime);
		System.out.println("Condition:::::::::::::::"+(reminderData == null));

		if (reminderData == null) {
			return ok(Json.toJson(new ReminderVM()));
		}

		ReminderVM reminderVM = new ReminderVM();
		reminderVM.id = reminderData.id;
		reminderVM.doctorId = reminderData.doctorId;
		reminderVM.patientId = Person.getPersonById(reminderData.patientId).emailID;
		reminderVM.appointmentDate = reminderData.appointmentDate;
		reminderVM.appointmentTime = reminderData.appointmentTime;
		reminderVM.medicinName = reminderData.medicinName;
		reminderVM.startDate = reminderData.startDate;
		reminderVM.endDate = reminderData.endDate;
		reminderVM.duration = reminderData.duration;
		reminderVM.numberOfDoses = reminderData.numberOfDoses;
		reminderVM.schedule = reminderData.schedule;
		reminderVM.doctorInstruction = reminderData.doctorInstruction;
		reminderVM.visitDate = reminderData.visitDate;
		reminderVM.visitType = reminderData.visitType;
		reminderVM.referredBy = reminderData.referredBy;
		reminderVM.symptoms = reminderData.symptoms;
		reminderVM.diagnosis = reminderData.diagnosis;
		reminderVM.medicinePrescribed = reminderData.medicinePrescribed;
		reminderVM.testsPrescribed = reminderData.testsPrescribed;

		List<ReminderTimeTable> reminderTimeTablesList = ReminderTimeTable
				.getAllReminderTimeTableById(reminderVM.id);
		List<AlarmReminderVM> alarmReminderVMs = new ArrayList<AlarmReminderVM>();

		for (ReminderTimeTable reminderTimeTable : reminderTimeTablesList) {
			alarmReminderVMs.add(new AlarmReminderVM(reminderTimeTable.id,
					reminderTimeTable.alarmDate, reminderTimeTable.time1,
					reminderTimeTable.time2, reminderTimeTable.time3,
					reminderTimeTable.time4, reminderTimeTable.time5,
					reminderTimeTable.time6));
		}

		reminderVM.alarmReminderVMList = alarmReminderVMs;

		return ok(Json.toJson(reminderVM));
	}

	public static Result saveVisitedPatientAppointment() throws IOException {

		String decryptedValue = null;
		String clinicId = null;
		String shift = null;
		String patientId = null;
		String visited = null;

		try {
			decryptedValue = URLDecoder.decode(
					request().getQueryString("doctorId"), "UTF-8");
			clinicId = request().getQueryString("clinicId");
			shift = request().getQueryString("shift");
			patientId = request().getQueryString("patientId");
			visited = request().getQueryString("isVisited");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int doctor_id = Integer.parseInt(decryptedValue);
		int clinic_id = Integer.parseInt(clinicId);
		Integer patient_id = Person.getPatientByMail(patientId);
		int isVisited = Integer.parseInt(visited);

		PatientClientBookAppointment appointment = PatientClientBookAppointment
				.saveVisitedClinicAppointment(doctor_id, patient_id, clinic_id,
						shift);

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
			decryptedValue = URLDecoder.decode(
					request().getQueryString("doctorId"), "UTF-8");
			clinicId = request().getQueryString("clinicId");
			shift = request().getQueryString("shift");
			date = request().getQueryString("date");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("decryptedValue = " + decryptedValue);
		System.out.println("clinicId = " + clinicId);
		System.out.println("shift = " + shift);
		System.out.println("date = " + date);

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

		List<PatientClientBookAppointment> clinicList = PatientClientBookAppointment
				.getAllClinicAppointment(doctor_id, clinic_id, shift,
						appintmentDate);

		return ok(Json.toJson(clinicList));
	}


	public static Result getAllPatientAppointment() {

		String decryptedValue = null;
		String patientId = null;

		try {
			decryptedValue = URLDecoder.decode(
					request().getQueryString("doctorId"), "UTF-8");
			patientId = request().getQueryString("patientId");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int doctor_id = Integer.parseInt(decryptedValue);
		Integer patient_id = Person.getPatientByMail(patientId);

		List<PatientClientBookAppointment> clinicList = PatientClientBookAppointment
				.getAllAppointment(doctor_id, patient_id);

		Collections.sort(clinicList,
				new Comparator<PatientClientBookAppointment>() {
					public int compare(PatientClientBookAppointment chair1,
							PatientClientBookAppointment chair2) {

						String[] timeValue;
						Calendar calOne = Calendar.getInstance();
						calOne.setTime(chair1.appointmentDate);
						timeValue = chair1.bookTime.split(":");

						int hour1 = Integer.parseInt(timeValue[0].trim());
						int min1 = Integer.parseInt(timeValue[1].trim().split(
								"[a-zA-Z ]+")[0]);
						calOne.set(Calendar.HOUR, hour1);
						calOne.set(Calendar.MINUTE, min1);

						String strAM_PM = timeValue[1].replaceAll("[0-9]", "");
						if (strAM_PM.equals("AM")) {
							calOne.set(Calendar.AM_PM, 0);
						} else {
							calOne.set(Calendar.AM_PM, 1);
						}
						Calendar calTwo = Calendar.getInstance();
						calTwo.setTime(chair2.appointmentDate);
						timeValue = chair2.bookTime.split(":");

						int hour2 = Integer.parseInt(timeValue[0].trim());
						int min2 = Integer.parseInt(timeValue[1].trim().split(
								"[a-zA-Z ]+")[0]);
						calTwo.set(Calendar.HOUR, hour2);
						String strAM_PM2 = timeValue[1].replaceAll("[0-9]", "");

						if (strAM_PM2.equals("AM")) {
							calTwo.set(Calendar.AM_PM, 0);
						} else {
							calTwo.set(Calendar.AM_PM, 1);
						}
						calOne.set(Calendar.AM_PM, 1);
						calTwo.set(Calendar.MINUTE, min2);

						if (calOne.compareTo(calTwo) == 1) {
							return -1;
						} else if (calOne.compareTo(calTwo) == -1) {
							return 1;
						} else {
							return 0;
						}

					}
				});

		return ok(Json.toJson(clinicList));
	}

	public static Result getProfilePatient() throws IOException
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
		vm.setAllegricTo(person.allergicTo);
		return ok(Json.toJson(vm));
	}
	
	
	
	public static Result cancelClinicsAppointment() {

		String decryptedValue = null;
		String clinicId = null;
		String shift = null;
		String patientId = null;

		try {
			decryptedValue = URLDecoder.decode(
					request().getQueryString("doctorId"), "UTF-8");
			clinicId = request().getQueryString("clinicId");
			shift = request().getQueryString("shift");
			patientId = request().getQueryString("patientId");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("decryptedValue = " + decryptedValue);
		System.out.println("clinicId = " + clinicId);
		System.out.println("shift = " + shift);
		System.out.println("patientId = " + patientId);

		int doctor_id = Integer.parseInt(decryptedValue);
		int clinic_id = Integer.parseInt(clinicId);
		Integer patient_id = Person.getPatientByMail(patientId);

		String status = PatientClientBookAppointment.deleteNextAppointment(
				doctor_id, patient_id, clinic_id, shift);

		return ok(Json.toJson(status));
	}
	public static Result profilePictureUpdatePatient() throws IOException
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
			  File  newFile = new File(Play.application().configuration().getString("profile_pic_url_patients")+ "//"+  fileName);
			  fileSave.renameTo(newFile);
			  path = Play.application().configuration().getString("profile_pic_url_patients")+"/" + fileName;
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
	
	public static Result deletePatient()throws IOException 
	{
		String id = URLDecoder.decode(request().getQueryString("id"), "UTF-8");
		int personId = Integer.parseInt(id);
		Person person = Person.getPersonById(personId);
		int patientId = person.patient;
		PatientRegister registerPatient = PatientRegister.getPatientById(patientId);
		registerPatient.delete();
		person.delete();
		return ok(Json.toJson("Success"));
	}
	
	public static Result homeCountPatient() throws IOException
	{
		String decryptedValue = null;
		int doctorsCount = 0;
		int clinicCount = 0;
		int appointmentCount = 0;
		ArrayList<PatientsDoctor> patientDoctors = new ArrayList<PatientsDoctor>();
		try 
		{
			decryptedValue = URLDecoder.decode(request().getQueryString("id"),"UTF-8");
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		PatientRegister patient = PatientRegister.getPatientById((Person
				.getPatientByMail(decryptedValue)));
		List<DoctorRegister> doctors = patient.doctors; 
		System.out.println("Doctor Register::::"+doctors.size());
		doctorsCount = doctors.size();
		List<BucketDoctors> bucketDoctors = BucketDoctors.getPersonByPatient(patient.patientId);
		System.out.println("bucket Doctors::::"+bucketDoctors.size());
		doctorsCount = doctorsCount + bucketDoctors.size();
		System.out.println("Doctors Count::::::"+doctorsCount);
		int patientId = Person.getPatientByMail(decryptedValue);
		List<PatientClientBookAppointment> appointments = PatientClientBookAppointment.getAllPatientAppointments(patientId);
		System.out.println("Appointments:::::"+appointments.size());
		Set<Integer> clinicIds = new HashSet<Integer>();
		for(PatientClientBookAppointment app : appointments)
		{
			System.out.println("clinic ID"+app.clinicId);
			clinicIds.add(app.clinicId);
		}
		System.out.println("count"+clinicIds.size());
		clinicCount = clinicIds.size();
		appointmentCount = appointments.size();
		System.out.println("Appointments"+appointmentCount);
		HomeCountPatientVM vm = new HomeCountPatientVM();
		vm.doctorsCount = doctorsCount;
		vm.clinicsCount = clinicCount;
		vm.appointmentsCount = appointmentCount;
		System.out.println("Appointment Date::::::"+appointments.get(0).timeSlot);
		PatientAppointmentVM appointmentVm = new PatientAppointmentVM();
		appointmentVm.id = appointments.get(0).id;
		appointmentVm.doctorId = appointments.get(0).doctorId;
		appointmentVm.patientId = appointments.get(0).patientId;
		appointmentVm.clinicId = appointments.get(0).clinicId;
		appointmentVm.shift = appointments.get(0).shift;
		appointmentVm.bookTime = appointments.get(0).bookTime;
		appointmentVm.appointmentDate = appointments.get(0).appointmentDate;
		appointmentVm.timeSlot = appointments.get(0).timeSlot;
		appointmentVm.visitType = appointments.get(0).visitType;
		appointmentVm.isVisited = appointments.get(0).isVisited;
		vm.appointmentVm = appointmentVm;
		for(DoctorRegister doctorRegister : doctors){
			
			Person p = Person.getDoctorsById(doctorRegister.doctorId);
			PatientsDoctor patientDoctor = new PatientsDoctor();
			patientDoctor.doctorId = ""+doctorRegister.doctorId;
			patientDoctor.name = p.name;
			patientDoctor.speciality = doctorRegister.speciality;
			patientDoctor.emailID = p.emailID;
			patientDoctor.mobileNumber = p.mobileNumber;
			patientDoctor.location = p.location;
			patientDoctor.dateOfBirth = p.dateOfBirth.toString();
			patientDoctor.gender = p.gender.toString();
			patientDoctor.blood_group = p.bloodGroup;
			System.out.println("Condition Register:::::"+(appointments.get(0).doctorId == p.doctor));
			if(appointments.get(0).doctorId == p.doctor){
				patientDoctor.clinicId = appointments.get(0).clinicId;
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				patientDoctor.bookDate = formatter.format(appointments.get(0).appointmentDate);
				patientDoctor.shift = appointments.get(0).shift;
				patientDoctor.bookTime = appointments.get(0).bookTime;
			}
			patientDoctor.type = 1;
			patientDoctors.add(patientDoctor);
			
		}
		for(BucketDoctors bucketDoctor : bucketDoctors){
			PatientsDoctor patientDoctor = new PatientsDoctor();
			patientDoctor.doctorId = ""+bucketDoctor.doctorId;
			patientDoctor.name = bucketDoctor.name;
			patientDoctor.speciality = bucketDoctor.speciality;
			patientDoctor.emailID = bucketDoctor.email;
			patientDoctor.mobileNumber = bucketDoctor.mobileNumber;
			patientDoctor.location = bucketDoctor.location;
			if(appointments.get(0).doctorId == bucketDoctor.doctorId){
				patientDoctor.clinicId = appointments.get(0).clinicId;
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				patientDoctor.bookDate = formatter.format(appointments.get(0).appointmentDate);
				patientDoctor.bookTime = appointments.get(0).bookTime;
				patientDoctor.shift = appointments.get(0).shift;
			}
			patientDoctor.type = 2;
			patientDoctors.add(patientDoctor);
			
		}
		vm.doctors = patientDoctors;
		return ok(Json.toJson(vm));
	}
	public static Result updatePatientProfile() throws IOException 
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
		person.allergicTo = personVm.getAllegricTo();
		
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
	
	public static Result saveFeedBackPatient() throws IOException
	{
		String doctorString = "";
		String patientString = "";
		String clinicString = "";
		String dateString = "";
		String timeString = "";
		Date appointmentDate = null;
		PatientClientBookAppointment appointment = null;
		ObjectMapper mapper = new ObjectMapper();
		FeedbackVM vm = mapper.readValue(request().body().asJson(),FeedbackVM.class);
		try{
			doctorString = vm.doctorId;
			patientString = vm.patientId; 
			clinicString = vm.clinicId;
			dateString = vm.appointmentDate; 
			timeString = vm.appointmentTime; 
			int patientId = Person.getPatientByMail(patientString);
			int doctorId = Integer.parseInt(doctorString);
			int clinicId = Integer.parseInt(clinicString);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
			appointmentDate = format.parse(dateString);
			
			List<PatientClientBookAppointment> appointmentList = PatientClientBookAppointment.getAllClinicAppointment(doctorId, clinicId);
			System.out.println("Size = "+appointmentList.size());
			for(PatientClientBookAppointment appoint : appointmentList){
				int dateCompare = appoint.appointmentDate.compareTo(appointmentDate);
				if((dateCompare == 1) && (appoint.bookTime.equals(timeString))){
					System.out.println("Match Found");
					appointment = appoint;
					break;
				}
			}
			System.out.println("Appointment Type= "+appointment.visitType);
			if(vm.visited.equalsIgnoreCase("visited")){
				appointment.star = vm.star;
				appointment.isVisited = 1;
				appointment.reviews = vm.reviews;
			}else{
				appointment.isVisited = null;
				appointment.star = null;
				appointment.reviews = null;
			}
			appointment.save();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return ok(Json.toJson(appointment));
	}
	
	public static Result getFeedbackPatient() throws IOException{
		String doctorString = "";
		String patientString = "";
		String clinicString = "";
		String dateString = "";
		String timeString = "";
		Date appointmentDate = null;
		PatientClientBookAppointment appointment = null;
		ObjectMapper mapper = new ObjectMapper();
		FeedbackVM vm = mapper.readValue(request().body().asJson(),FeedbackVM.class);
		try{
			doctorString = vm.doctorId;
			patientString = vm.patientId; 
			clinicString = vm.clinicId;
			dateString = vm.appointmentDate; 
			timeString = vm.appointmentTime; 
			int patientId = Person.getPatientByMail(patientString);
			int doctorId = Integer.parseInt(doctorString);
			int clinicId = Integer.parseInt(clinicString);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
			appointmentDate = format.parse(dateString);
			List<PatientClientBookAppointment> appointmentList = PatientClientBookAppointment.getAllClinicAppointment(doctorId, clinicId);
			System.out.println("Size = "+appointmentList.size());
			for(PatientClientBookAppointment appoint : appointmentList){
				int dateCompare = appoint.appointmentDate.compareTo(appointmentDate);
				if((dateCompare == 1) && (appoint.bookTime.equals(timeString))){
					System.out.println("Match Found");
					appointment = appoint;
					vm.star = appoint.star;
					vm.reviews = appoint.reviews;
				    if(appoint.isVisited != null){
				    	vm.visited = ""+appoint.isVisited;
				    }
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ok(Json.toJson(vm));
	}
	public static Result getPatientAppointment()throws IOException
	{
		String decryptedValue = null;
		try{
			decryptedValue = URLDecoder.decode(request().getQueryString("patientId"),"UTF-8");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		Person person =  Person.getPersonByMail(decryptedValue);
		int patientId = person.idPerson;
		List<PatientClientBookAppointment> appointmentList = PatientClientBookAppointment
				                                            .getAllPatientAppointments(patientId);	
		List<ClinicDoctorVM> clinicDoctorVM = new ArrayList<ClinicDoctorVM>();
		List<Clinic> clinicList = Clinic.getAllClinic();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String Nextdate = "";
		String nextBookTime = "";
		int idClinic = 0;
		for(PatientClientBookAppointment appoint : appointmentList){
			ClinicDoctorVM doctorVM = new ClinicDoctorVM();
			idClinic = appoint.clinicId;
			for(Clinic clinic : clinicList){
				if(appoint.clinicId == clinic.idClinic)
				{
					doctorVM.idClinic = clinic.idClinic;
					doctorVM.clinicName = clinic.clinicName;
					doctorVM.landLineNumber = clinic.landLineNumber;
					doctorVM.mobileNumber = clinic.mobileNumber;
					doctorVM.address = clinic.address;
					doctorVM.location = clinic.location;
					doctorVM.email = clinic.email;
					doctorVM.doctorId = clinic.doctorId;
					doctorVM.doctorEmail = Person.getDoctorsById(clinic.doctorId).emailID;
					doctorVM.appointmentDate = ""+formatter.format(appoint.appointmentDate);
					doctorVM.appointmentTime = appoint.bookTime;
					doctorVM.appointmentType = appoint.visitType;
					String[] timeValue;
					Calendar calOne = Calendar.getInstance();
					calOne.setTime(appoint.appointmentDate);
					timeValue = appoint.bookTime.split(":");

					int hour1 = Integer.parseInt(timeValue[0].trim());
					int min1 = Integer.parseInt(timeValue[1].trim().split(
								"[a-zA-Z ]+")[0]);
					calOne.set(Calendar.HOUR, hour1);
					calOne.set(Calendar.MINUTE, min1);

					String strAM_PM = timeValue[1].replaceAll("[0-9]", "");
					if (strAM_PM.equals("AM")) {
						calOne.set(Calendar.AM_PM, 0);
					} else {
						calOne.set(Calendar.AM_PM, 1);
					}
					calOne.set(Calendar.AM_PM, 1);

					Calendar calTwo = Calendar.getInstance();
					System.out.println("Condition::::::"+(calOne.getTimeInMillis() < calTwo.getTimeInMillis()));

					if (calOne.getTimeInMillis() < calTwo.getTimeInMillis()) {
							Nextdate = "";
						} else {
							Nextdate = formatter.format(appoint.appointmentDate);
							nextBookTime = appoint.bookTime;
							doctorVM.bookDate = Nextdate;
							doctorVM.bookTime = nextBookTime;
						}

					}
								
			 }
				
			clinicDoctorVM.add(doctorVM);
			
		}
		return ok(Json.toJson(clinicDoctorVM));
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
				"204", "User Registered Successfully!"), E304("204",
				"User Registered Successfully!"), E205("205",
				"Language Param Not Found!"), E206("206",
				"Username is not Valid Number!"), E207("207",
				"Validation Code is Invalid!"), E208("208",
				"Username, Password does'nt matched with our database!"), E209(
				"209", "User Validated Successfully!"), E210("210",
				"User Already Exist!"), E211("211", "Doctor Does Not Exist"), E212(
				"211", "Clinic Doctor time Added ");
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
