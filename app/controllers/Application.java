package controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import models.AssistentRegister;
import models.BucketDoctors;
import models.BucketPatients;
import models.Clinic;
import models.Delegates;
import models.DoctorClinicSchedule;
import models.DoctorDependency;
import models.DoctorProcedure;
import models.DoctorRegister;
import models.Notification;
import models.PatientClientBookAppointment;
import models.PatientDependency;
import models.PatientRegister;
import models.Person;
import models.SummaryHistory;
import models.TemplateAttribute;
import models.TemplateClass;
import models.TemplateField;
import models.UploadFiles;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import play.Play;
import play.api.mvc.MultipartFormData;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import sun.misc.BASE64Decoder;
import viewmodel.ChatVm;
import viewmodel.ClinicVM;
import viewmodel.DoctorsPatient;
import viewmodel.FieldVm;
import viewmodel.NotificationVM;
import viewmodel.PDAEditVm;
import viewmodel.PatientSearch;
import viewmodel.PatientsDoctor;
import viewmodel.PersonVM;
import viewmodel.RegisterDoctor;
import viewmodel.RegisterPatient;
import viewmodel.RegisterVM;
import viewmodel.Reminder;
import viewmodel.ShowFieldVm;
import viewmodel.ShowTemplatesVm;
import viewmodel.SummaryHistoryVM;
import viewmodel.TemplateVm;
import viewmodel.UpdateVM;
import viewmodel.totalInvoiceVM;
import javax.activation.*;

public class Application extends Controller {

	public static Result index() {
		System.out.println("String path:::::"+Play.application().configuration().getString("folder_create_url_doctor"));
		File folderParent = new File(Play.application().configuration().getString("folder_parent"));
		File folderProfilePic = new File (Play.application().configuration().getString("folder_profile_pic"));
		if(!folderParent.exists())
		{
			folderParent.mkdir();
		}
		if(!folderProfilePic.exists())
		{
			folderProfilePic.mkdir();
		}
		File folderCreateDoctor = new File(Play.application().configuration().getString("folder_create_url_doctor"));
		File folderCreatePatient = new File(Play.application().configuration().getString("folder_create_url_patient"));
		File folderCreateAssistant = new File(Play.application().configuration().getString("folder_create_url_assistant"));
		File folderProfileDoctor = new File(Play.application().configuration().getString("profile_pic_url_doctors"));
		File folderProfilePatient = new File(Play.application().configuration().getString("profile_pic_url_patients"));
		File folderProfileAssistant = new File(Play.application().configuration().getString("profile_pic_url_assistant"));
		if(!folderCreateDoctor.exists())
		{
			System.out.println("Doctor Condition:::::");
			folderCreateDoctor.mkdir();
		}
		if(!folderCreatePatient.exists())
		{
			folderCreatePatient.mkdir();
		}
		if(!folderCreateAssistant.exists())
		{
			folderCreateAssistant.mkdir();
		}
		if(!folderProfileDoctor.exists())
		{
			folderProfileDoctor.mkdir();
		}
		if(!folderProfilePatient.exists())
		{
			folderProfilePatient.mkdir();
		}
		if(!folderProfileAssistant.exists())
		{
			folderProfileAssistant.mkdir();
		}
		
		
		return ok(views.html.index.render("Your new application is ready."));
	}

	public static Result availableEmail() throws IOException
	{
		String email = request().getQueryString("email");
		Person person = Person.getPersonByMail(email);
		if(person == null)
		{
			return ok("Available");
		}
		else
		{
			return ok(Json.toJson("Not Available"));
		}
		
	}
	
	public static Result verifyCompleted() throws IOException 
	{
		String email = request().getQueryString("email");
		Person person = Person.getPersonByMail(email);
		person.verficationCode = "000000";
		person.update();
		return ok(Json.toJson("Completed"));
	}
	
	
	public static Result verification() throws IOException
	{
		System.out.println("called...............");
		//Form<RegisterVM> form = DynamicForm.form(RegisterVM.class).bindFromRequest();
		//RegisterVM register = form.get();
		String email = request().getQueryString("email");
		String verificationCode = request().getQueryString("verifyCode");
		Person person = Person.getPersonByMail(email);
		if((person.verficationCode).equalsIgnoreCase(verificationCode))
		{
			person.verficationCode = "000000";
			person.update();
			return ok(Json.toJson("Verified"));
		}
		else
		{
			return ok(Json.toJson("Not Verified"));
		}
		
	}
	
	public static Result registerAssistent() throws IOException {
		System.out.println("called...............");
		//Form<RegisterVM> form = DynamicForm.form(RegisterVM.class).bindFromRequest();
		//RegisterVM register = form.get();
		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();
		RegisterVM register = mapper.readValue(json.traverse(),RegisterVM.class);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		if (Person.getPersonByMail(register.emailID) != null) {
			return ok(Json.toJson(new ErrorResponse(Error.E210.getCode(),
					Error.E210.getMessage())));
		}
		
		Person registration = new Person();
		AssistentRegister assistent = new AssistentRegister();
			
		//Person p = Person.getDoctorByMail()
		//int id= 1;
		//assistent.doctorRegister.doctorId
		//DoctorRegister doc =  DoctorRegister.getDoctorById(register.doctorId);
		//System.out.println("doc="+doc);
		//doc.assistentRegister.add(assistent);
		
		//doc.update();
		//assistent.doctorRegister = doc;
		assistent.save();
		
		registration.role = 3;
		registration.name = register.name;
		registration.emailID = register.emailID;
		registration.password = register.password;
		registration.gender = register.gender;
		registration.mobileNumber = register.mobileNumber;
		registration.verficationCode = "000000";
		registration.status = "Under Verification";
		//registration.doctor = register.doctorId;
		try {
			registration.dateOfBirth = format.parse(register.dateOfBirth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		registration.location = register.location;
		registration.bloodGroup = register.bloodGroup;

		registration.assistent = assistent;
		registration.save();

		System.out.println("Return");
		return ok(Json.toJson(registration.idPerson));
	}
	public static Result deleteAssistant()throws IOException
	{
		String id = URLDecoder.decode(request().getQueryString("id"), "UTF-8");
		int personId = Integer.parseInt(id);
		Person person = Person.getPersonById(personId);
		System.out.println("person name:::::"+person.assistent);
		AssistentRegister registerAssistant = person.assistent;
		System.out.println("id::::::"+registerAssistant.assitentId);
		person.delete();
		registerAssistant.delete();
		return ok(Json.toJson("Success"));
	}
	public static Result registerProfilePictureAssistant() throws IOException
	{
		 play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
		 List<String> specialCharactersInSolr = Arrays.asList(new String[]{
		            "+", "-", "&&", "||", "!", "(", ")", "{", "}", "[", "]", "^",
		            "~", "*", "?", ":","\"","\\"," "});
		DynamicForm form = DynamicForm.form().bindFromRequest();
		String path = null;
		int assistantId = 0;
		
  
		if(!form.get("assistantId").equalsIgnoreCase("null"))
		{
				assistantId = Integer.parseInt(form.get("assistantId"));				
		}
		FilePart picture = body.getFile("picture");
		if (picture != null) 
	    {
			Person p = Person.getPersonById(assistantId);
			String fileName = picture.getFilename();
			String contentType = picture.getContentType(); 
			File file = picture.getFile();
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
			File  newFile = new File(Play.application().configuration().getString("profile_pic_url_assistant")+ "//"+  fileName);
			file.renameTo(newFile);
			path = Play.application().configuration().getString("profile_pic_url_assistant")+"/" + fileName;
			System.out.println("Path:::::::"+path);
			System.out.println("Person name:::::"+p.name);
			p.url = path;
			
			p.update();
	    }
		else
		{
			 path = "none";
	    	 Person p = Person.getPersonById(assistantId);
			 p.url = path;
			 p.update();
		}
		return ok(Json.toJson("Success"));
	}
	public static Result registerPatient() throws IOException {
		System.out.println("called...............");

		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		
		ObjectMapper mapper = new ObjectMapper();
		RegisterVM register = mapper.readValue(json.traverse(),RegisterVM.class);
		 
		//Form<RegisterVM> form = DynamicForm.form(RegisterVM.class).bindFromRequest();

		//RegisterVM register = form.get();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		if (Person.getPersonByMail(register.emailID) != null) {
			return ok(Json.toJson(new ErrorResponse(Error.E210.getCode(),
					Error.E210.getMessage())));
		}

		Person registration = new Person();
		PatientRegister patient = new PatientRegister();
		patient.save();

		registration.role = 1;
		registration.name = register.name;
		registration.emailID = register.emailID;
		registration.password = register.password;
		registration.gender = register.gender;
		registration.mobileNumber = register.mobileNumber;

		try {
			System.out.println("date:" + register.dateOfBirth);
			registration.dateOfBirth = format.parse(register.dateOfBirth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		registration.location = register.location;
		registration.bloodGroup = register.bloodGroup;
		registration.allergicTo = register.allergicTo;
		registration.cloudType = register.cloudType;
		registration.cloudLoginId = register.cloudLoginId;
		registration.cloudLoginPassword = register.cloudLoginPassword;
		registration.patient = patient.patientId;
		registration.verficationCode = "000000";
		registration.status = "Under Verification";
		registration.save();

		System.out.println("Return");
		return ok(Json.toJson(registration.idPerson));
	}
	
	public static Result getVerificationCode()throws IOException 
	{
		int aNumber = 0; 
		String email = URLDecoder.decode(request()
				.getQueryString("email"), "UTF-8");
		Properties props = new Properties();
	 	props.put("mail.smtp.auth", "true");
	 	props.put("mail.smtp.starttls.enable", "true");
	 	props.put("mail.smtp.host", "smtp.gmail.com");
	 	props.put("mail.smtp.port", "587");
	 	final String username="kaustubh.kbh@gmail.com";
	    final String password="krrish007";
	 	Session session = Session.getInstance(props,
	 	 		  new javax.mail.Authenticator() {
	 	 			protected PasswordAuthentication getPasswordAuthentication() {
	 	 				return new PasswordAuthentication(username, password);
	 	 			}
	 	  });
		try
		{
			aNumber = (int)((Math.random() * 900000)+100000); 
 			System.out.println("Random Number:::::::"+aNumber);
  			Message feedback = new MimeMessage(session);
  			feedback.setFrom(new InternetAddress(username));
  			feedback.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
  			feedback.setSubject("MedicalDiary Verification Code");	  			
  			BodyPart messageBodyPart = new MimeBodyPart();	  	       
  	        messageBodyPart.setText(""+aNumber);	  	    
  	        Multipart multipart = new MimeMultipart();	  	    
  	        multipart.addBodyPart(messageBodyPart);	            
  	        feedback.setContent(multipart);
  		    Transport.send(feedback);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ok(Json.toJson("1234"));
	}
	public static Result registerProfilePicturePatient() throws IOException{
		 play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
		 List<String> specialCharactersInSolr = Arrays.asList(new String[]{
		            "+", "-", "&&", "||", "!", "(", ")", "{", "}", "[", "]", "^",
		            "~", "*", "?", ":","\"","\\"," "});
		 DynamicForm form = DynamicForm.form().bindFromRequest();
		
		 
		 String path = null;
		 int patId = 0;
			if(!form.get("patientId").equalsIgnoreCase("null")){
				 patId = Integer.parseInt(form.get("patientId"));				
			}
	     FilePart picture = body.getFile("picture");
	     if (picture != null) 
	     {
	    	 String fileName = picture.getFilename();
			 String contentType = picture.getContentType(); 
			 File file = picture.getFile();
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
			  file.renameTo(newFile);
			  path = Play.application().configuration().getString("profile_pic_url_patients")+"/" + fileName;
			  System.out.println("Path:::::::"+path);
			  Person p = Person.getPersonById(patId);
			  System.out.println("Person name:::::"+p.name);
			 
				
			 
			  p.url = path;
			 
			  p.update();
			 
	     }
	     else
	     {	
	    	  Person p = Person.getPersonById(patId);
	    	 
		    	path = "none";
		    	
		    	
				p.url = path;
				p.update();
	     }
		
		return ok(Json.toJson("Success"));
	}

	public static Result registerDoctor() throws IOException {
		System.out.println("called...............");
		//Form<RegisterVM> form = DynamicForm.form(RegisterVM.class).bindFromRequest();
		//RegisterVM register = form.get();
		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();
		RegisterVM register = mapper.readValue(json.traverse(),RegisterVM.class);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		request();
		if (Person.getPersonByMail(register.emailID) != null) {
			return ok(Json.toJson(new ErrorResponse(Error.E210.getCode(),
					Error.E210.getMessage())));
		}

		Person registration = new Person();
		DoctorRegister doctor = new DoctorRegister();

		doctor.flag = true;
		doctor.speciality = register.speciality;
		doctor.save();
		registration.role = 2;
		registration.name = register.name;
		registration.emailID = register.emailID;
		registration.password = register.password;
		registration.gender = register.gender;
		registration.mobileNumber = register.mobileNumber;
		registration.verficationCode = "000000";
		registration.status = "Under Verification";

		try {
			registration.dateOfBirth = format.parse(register.dateOfBirth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		registration.location = register.location;
		registration.bloodGroup = register.bloodGroup;
		registration.cloudType = register.cloudType;
		registration.cloudLoginId = register.cloudLoginId;
		registration.cloudLoginPassword = register.cloudLoginPassword;

		registration.doctor = doctor.doctorId;
		registration.save();
		// doctor.person = registration;
		System.out.println("Return");
		return ok(Json.toJson(registration.idPerson));
	}
	
	public static Result registerProfilePictureDoctor() throws IOException
	{
		 play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
		 List<String> specialCharactersInSolr = Arrays.asList(new String[]{
		            "+", "-", "&&", "||", "!", "(", ")", "{", "}", "[", "]", "^",
		            "~", "*", "?", ":","\"","\\"," "});
		 DynamicForm form = DynamicForm.form().bindFromRequest();
		
		 String path = null;
		 int docId = 0;
		 if(!form.get("doctorId").equalsIgnoreCase("null")){
				 docId = Integer.parseInt(form.get("doctorId"));				
		 }
		 FilePart picture = body.getFile("picture");
		 if (picture != null) 
	     {
			 Person p = Person.getPersonById(docId);
	    	 String fileName = picture.getFilename();
			 String contentType = picture.getContentType(); 
			 File file = picture.getFile();
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
			  file.renameTo(newFile);
			  path = Play.application().configuration().getString("profile_pic_url_doctors")+"/" + fileName;
			  System.out.println("Path:::::::"+path);
			  
			  System.out.println("Person name:::::"+p.name);
			  p.url = path;
			 
			  p.update();
		   }
		   else
	       {	
	    	  path = "none";
	    	  Person p = Person.getPersonById(docId);
	    	  
			  p.url = path;
			 
			  p.update();
	       }
		   return ok(Json.toJson("Success"));
	}
	
	public static Result login() throws IOException {

		System.out.println("called...............");
		//Form<RegisterVM> form = DynamicForm.form(RegisterVM.class).bindFromRequest();
		//RegisterVM register = form.get();
		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();
		RegisterVM register = mapper.readValue(json.traverse(),RegisterVM.class);
		String url = Play.application().configuration().getString("base_url");
		System.out.println("url::::::::"+url);
		
		String email = null;
		String type = null;
		String status = null;
		try {
			Person user = Person.getUserByUserNameAndPassword(register.emailID,
					register.password);
			email = user.emailID;
			status = user.status;
			if(user.role == 1){
				type = "Patient";
			} else if(user.role == 2){
				type = "Doctor";
			} else if(user.role == 3){
				type = "Assistant";
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * String encryptedValue = null; String plainData=email; try {
		 * 
		 * Key key = generateKey(); Cipher c = Cipher.getInstance("AES");
		 * c.init(Cipher.ENCRYPT_MODE, key); byte[] encVal =
		 * c.doFinal(plainData.getBytes()); encryptedValue = new
		 * BASE64Encoder().encode(encVal);
		 * 
		 * } catch(Exception e) { }
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("type", type);
		map.put("status",status);
		return ok(Json.toJson(map));

		/*
		 * BASE64Encoder base64 = new BASE64Encoder(); byte[] bytesEncoded =
		 * Base64.encodeBase64(str .getBytes());
		 * System.out.println("ecncoded value is " + new String(bytesEncoded ));
		 * 
		 * // Decrypt data on other side, by processing encoded data byte[]
		 * valueDecoded= Base64.decodeBase64(bytesEncoded );
		 * System.out.println("Decoded value is " + new String(valueDecoded));
		 * return ok(Json.toJson(base64.encodeBuffer(email.getBytes())));
		 */
	}

	public static Key generateKey() throws Exception {
		Key key = new SecretKeySpec("TheBestSecretkey".getBytes(), "AES");
		return key;
	}

	public static Result patientDoctor() throws Exception {
		System.out.println("patient doctors");
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		PatientRegister patient = PatientRegister.getPatientById((Person
				.getPatientByMail(decryptedValue)));
		String ids = request().getQueryString("doctors");
		String[] array = null;
		if(ids.contains(",")){
			array = request().getQueryString("doctors").split(",");
		} else {
			array = new String[1];
			array[0] = request().getQueryString("doctors");
		}
		for (Integer i = 0; i < array.length; i++) {
			DoctorRegister doctor = DoctorRegister.getDoctorById(Integer
					.parseInt(array[i]));
			patient.doctors.add(doctor);
			System.out.println("patient doctors close");
		}
		patient.save();
		return ok();
	}
	
	public static Result patientDoctorAdd() throws Exception
	{
		System.out.println("Doctor Add");
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		PatientRegister patient = PatientRegister.getPatientById((Person
				.getPatientByMail(decryptedValue)));
		String id = request().getQueryString("doctor");
		DoctorRegister doctor = DoctorRegister.getDoctorById(Integer
				.parseInt(id));
		patient.doctors.add(doctor);
		System.out.println("patient doctors close");
		patient.save();
		return ok();
	}

	public static Result removePatientsDoctor() throws Exception {
		System.out.println("patient doctors");
		JsonNode json = request().body().asJson();
		String email = json.path("patientId").asText();
		PatientRegister patient = PatientRegister.getPatientById((Person.getPatientByMail(email)));
		ArrayNode docs = (ArrayNode) json.path("doctors");
		for(int i=0;i<docs.size();i++){
			JsonNode doc = docs.get(i);
			Integer id = doc.path("id").asInt();
			Integer type = doc.path("type").asInt();
			if(type == 1){
				DoctorRegister doctor = DoctorRegister.getDoctorById(id);
				patient.doctors.remove(doctor);
			} else {
				BucketDoctors doctor = BucketDoctors.getDoctorById(id);
				doctor.delete();
			}
		}
		System.out.println("patient doctors close");
		patient.save();
		return ok();
	}

	public static Result doctorsPatient() throws Exception {
		System.out.println("patient doctors");
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person
				.getDoctorByMail(decryptedValue)));
		String ids = request().getQueryString("patients");
		String[] array = null;
		if(ids.contains(",")){
			array = request().getQueryString("patients").split(",");
		} else {
			array = new String[1];
			array[0] = request().getQueryString("patients");
		}
		for (Integer i = 0; i < array.length; i++) {
			PatientRegister patient = PatientRegister.getPatientById(Integer
					.parseInt(array[i]));
			doctor.patient.add(patient);
			System.out.println("patient doctors close");
		}
		doctor.save();
		return ok();
	}

	public static Result removeDoctorsPatient() throws Exception {
		JsonNode json = request().body().asJson();
		System.out.println("Json::::"+json.toString());
		String email = json.path("doctorId").asText();
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person.getDoctorByMail(email)));
		ArrayNode docs = (ArrayNode) json.path("patients");
		for(int i=0;i<docs.size();i++){
			JsonNode doc = docs.get(i);
			Integer id = doc.path("id").asInt();
			Integer type = doc.path("type").asInt();
			if(type == 1){
				PatientRegister patients = PatientRegister.getPatientById(id);
				doctor.patient.remove(patients);
			} else {
				BucketPatients patient = BucketPatients.getPatientById(id);
				patient.delete();
			}
		}
		System.out.println("patient doctors close");
		doctor.save();
		return ok();
	}
	
	public static Result addPatient() throws IOException
	{
		System.out.println("start");
		JsonNode json = request().body().asJson();
		System.out.println("Json"+json);
		ObjectMapper mapper = new ObjectMapper();
		RegisterPatient register = mapper.readValue(json.traverse(), RegisterPatient.class);
		BucketPatients patients = new BucketPatients();
		patients.name = register.name;
		patients.email = register.emailID;
		patients.mobileNumber = register.mobileNumber;
		patients.location = register.location;
		System.out.println("1");
		
		try
		{
			String decryptedValue = URLDecoder.decode(
					request().getQueryString("id"), "UTF-8");
			System.out.println("2");
			patients.doctor = DoctorRegister.getDoctorById((Person
					.getDoctorByMail(decryptedValue))).doctorId;
			System.out.println("3");
		}
		catch(Exception e)
		{
			System.out.println("4");
			return null;
		}
		patients.save();
		return ok();
	}

	public static Result addDoctor() throws IOException {
		//Form<RegisterDoctor> form = DynamicForm.form(RegisterDoctor.class).bindFromRequest();
		System.out.println("start");
		
		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();
		RegisterDoctor register = mapper.readValue(json.traverse(),RegisterDoctor.class);
		
		BucketDoctors doctors = new BucketDoctors();
		doctors.name = register.name;
		doctors.speciality = register.speciality;
		doctors.email = register.email;
		doctors.mobileNumber = register.mobileNumber;
		doctors.location = register.location;
		System.out.println("1");
		/*
		 * BASE64Decoder base64 = new BASE64Decoder(); byte[] mail=
		 * base64.decodeBuffer(request().getQueryString("id"));
		 */
		try {
			/*
			 * Key dkey = generateKey(); Cipher c = Cipher.getInstance("AES");
			 * c.init(Cipher.DECRYPT_MODE, dkey); byte[] decordedValue = new
			 * BASE64Decoder().decodeBuffer(request().getQueryString("id"));
			 * byte[] decValue = c.doFinal(decordedValue); String decryptedValue
			 * = new String(decValue);
			 */
			String decryptedValue = URLDecoder.decode(
					request().getQueryString("id"), "UTF-8");
			System.out.println("2");
			doctors.patient = PatientRegister.getPatientById((Person
					.getPatientByMail(decryptedValue))).patientId;
			System.out.println("3");
		} catch (Exception e) {
			System.out.println("4");
			return null;
		}
		System.out.println("5");
		doctors.save();
		return ok();
	}

	public static Result getPatientsDoctors() {
		
		String decryptedValue = null;
		try {
			decryptedValue = URLDecoder.decode(request().getQueryString("id"),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("id:" + decryptedValue);
		PatientRegister patient = PatientRegister.getPatientById((Person
				.getPatientByMail(decryptedValue)));
		List<PatientsDoctor> patientDoctor = new ArrayList<PatientsDoctor>();
		for (DoctorRegister doctor : patient.doctors) {
			
			List <PatientClientBookAppointment> appointmentList = PatientClientBookAppointment.getNextAppointment(doctor.doctorId,patient.patientId,"Occupied");
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			String Nextdate = "";
			String nextBookTime = "";
			String nextShift = "";
			String previousAppointment = "";
			String previousDate = "";
			Integer clinicId = null;
			String star = null;
			String reviews = null;
			String lastVisitedTime = null;
			//System.out.println("appointmentList = "+appointmentList.size());
			if(appointmentList.size() > 0)
			{
				
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
				int appointmentListSize = appointmentList.size();
				System.out.println("AppointmentListsize= "+appointmentListSize);
				for(int i = 0; i < appointmentList.size(); i++)
				{
					PatientClientBookAppointment appointment = appointmentList.get(i);
					System.out.println("AppointmentListsize= "+(appointmentListSize == 2));
					
					for(int j=0;j < appointmentList.size();j++)
					{
						if(j == (appointmentListSize - 1))
						{
							System.out.println("Book Time::::::"+appointment.bookTime);
							previousDate = formatter.format(appointment.appointmentDate);
							previousAppointment = appointment.bookTime;
													
						}
					}
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
						break;
					}
					
				
				}
				
		   }
			
			List<PatientClientBookAppointment> visitedList = new ArrayList<PatientClientBookAppointment>();
			for( PatientClientBookAppointment appointment: appointmentList){
				
				if(appointment.isVisited != null){
					if(appointment.isVisited == 1){
						visitedList.add(appointment);
						
					}
				}
			}
			
			String lastVisted = null;
			
			for(PatientClientBookAppointment appointment: visitedList){
				Calendar calOne = Calendar.getInstance();
		    	calOne.setTime(appointment.appointmentDate);
		    	Calendar calTwo = Calendar.getInstance();
		    	if(calOne.getTimeInMillis() < calTwo.getTimeInMillis()){
		    		lastVisted = formatter.format(calOne.getTime());
		    		lastVisitedTime = appointment.bookTime;
		    		star = appointment.star;
		    		reviews = appointment.reviews;
		    	}
			}
			
			Person p = Person.getDoctorsById(doctor.doctorId);
			PatientsDoctor doctorPatient = new PatientsDoctor();
			doctorPatient.doctorId = doctor.doctorId.toString();
			doctorPatient.name = p.name;
		    doctorPatient.speciality = doctor.speciality;
		    doctorPatient.emailID = p.emailID;
		    doctorPatient.mobileNumber = p.mobileNumber;
		    doctorPatient.location = p.location;
		    doctorPatient.dateOfBirth = p.dateOfBirth.toString();
		    doctorPatient.gender = p.gender.toString();
		    doctorPatient.blood_group = p.bloodGroup;
		    doctorPatient.allergic_to = p.allergicTo;
		    doctorPatient.type = 1;
		    doctorPatient.bookDate = Nextdate;
		    doctorPatient.bookTime = nextBookTime;
		    doctorPatient.shift = nextShift;
		    doctorPatient.clinicId = clinicId;
		    doctorPatient.lastVisited = lastVisted;
		    doctorPatient.previousAppointment = previousAppointment;
		    doctorPatient.previousDate = previousDate;
		    doctorPatient.star = star;
		    doctorPatient.reviews = reviews;
		    doctorPatient.lastVisitedTime = lastVisitedTime;
		    patientDoctor.add(doctorPatient);
			
			
		}

		List<BucketDoctors> bucketDoctors = BucketDoctors
				.getPersonByPatient(patient.patientId);
		if (!bucketDoctors.isEmpty()) {
			for (BucketDoctors doctor : bucketDoctors) {
				//System.out.println("2");
				patientDoctor.add(new PatientsDoctor(
						doctor.doctorId.toString(), doctor.name,
						doctor.speciality, doctor.email, doctor.mobileNumber,
						doctor.location, 2));
			}
		}

		return ok(Json.toJson(patientDoctor));

	}

	public static Result getDoctorsPatients() {
		System.out.println("1");
		String decryptedValue = null;
		try {
			decryptedValue = URLDecoder.decode(request().getQueryString("id"),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person
				.getDoctorByMail(decryptedValue)));
		List<DoctorsPatient> doctorsPatient = new ArrayList<>();
		
		for (PatientRegister patient : doctor.patient) {
			
			Person p = Person.getPatientsById(patient.patientId);
			doctorsPatient.add(new DoctorsPatient(patient.patientId.toString(),
					p.name, p.emailID, p.mobileNumber, p.location,1));
		}
		
		System.out.println("before::::::"+doctorsPatient.size());
		List<BucketPatients> bucketPatients= BucketPatients.getPersonByDoctor(doctor.doctorId);
		if (!bucketPatients.isEmpty()) {
			for (BucketPatients patient : bucketPatients) {
				doctorsPatient.add(new DoctorsPatient(
					    patient.patientId.toString(), patient.name,
					    patient.email, patient.mobileNumber,
						patient.location, 2));
			}
		}

		System.out.println("Size:::::"+doctorsPatient.size());
		return ok(Json.toJson(doctorsPatient));

	}

	
    public static class ClinicDetailsVM{
		
		public ClinicVM  clinicDetails;
		
	}
	
	
	public static Result addClinic() throws JsonParseException, JsonMappingException, IOException {
		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();
		ClinicVM clinic = mapper.readValue(json.traverse(),ClinicVM.class);
		
		Clinic clinics = new Clinic();
		
		clinics.clinicName = clinic.clinicName;
		clinics.landLineNumber = clinic.landLineNumber;
		if(clinic.mobileNumber == null)
		{
			clinics.mobileNumber = null;
		}
		else
		{
			clinics.mobileNumber = clinic.mobileNumber;
		}
		clinics.location = clinic.location;
		clinics.email = clinic.email;
		clinics.speciality = clinic.speciality;
		clinics.onlineAppointment = clinic.onlineAppointment;
		clinics.doctorId = DoctorRegister.getDoctorById((Person.getDoctorByMail(clinic.doctorId))).doctorId;
		clinics.save();
		
		return ok(Json.toJson(clinics.idClinic));
		
	}
	
	public static Result editClinic() throws JsonParseException,JsonMappingException,IOException
	{
		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();
		ClinicVM clinic = mapper.readValue(json.traverse(),ClinicVM.class);
		int id = Integer.parseInt(clinic.idClinic);
		Clinic clinics = Clinic.getClinicById(id);
		clinics.clinicName = clinic.clinicName;
		clinics.email = clinic.email;
		clinics.landLineNumber = clinic.landLineNumber;
		clinics.mobileNumber = clinic.mobileNumber;
		clinics.location = clinic.location;
		clinics.speciality = clinic.speciality;
		clinics.onlineAppointment = clinic.onlineAppointment;
		clinics.update();
		return ok(Json.toJson(clinics.idClinic));
	}
	 public static Result getAllClinic()
     {
    	 List<Clinic> clinics = Clinic.getAllClinic();
    	 for(Clinic c : clinics)
    	 {
    		 System.out.println("Clinic Name:::::"+c.clinicName);
    	 }
    	 return ok(Json.toJson(clinics));
     }
	public static Result addTemplate() throws JsonParseException,JsonMappingException,IOException
	{
		JsonNode json = request().body().asJson();
		System.out.println("Called");
		System.out.println("json "+json);
		ObjectMapper mapper = new ObjectMapper();
		TemplateVm templateVm = mapper.readValue(json.traverse(), TemplateVm.class);
		
		//System.out.println("doctorId = "+templateVm.templateName);
		Integer doctor_id = Person.getDoctorByMail(templateVm.doctorId);
		DoctorProcedure  doctorProcedure = DoctorProcedure.searchProcedureByIDName(doctor_id,templateVm.procedureName);
		
		TemplateClass template = TemplateClass.getTemplateClassByName(doctor_id,templateVm.templateName,doctorProcedure.procedureName);
		
		if(template == null){
			TemplateClass temp = new TemplateClass();
			temp.templateName = templateVm.templateName;
			temp.procedureName = doctorProcedure.procedureName;
			temp.doctorId = doctor_id;
			temp.doctorProcedure = doctorProcedure;
			temp.save();
			template = temp;
		}
		//System.out.println("return");
		return ok(Json.toJson(template));
	}
	
	public static Result addField() throws JsonParseException,JsonMappingException,IOException{
		JsonNode json = request().body().asJson();
		//System.out.println("Callled");
		System.out.println("json "+json);
		ObjectMapper mapper = new ObjectMapper();
		FieldVm fieldVm = mapper.readValue(json.traverse(), FieldVm.class);
		
		TemplateAttribute field = new TemplateAttribute();
		field.fieldName = fieldVm.fieldName;
		field.fieldType = fieldVm.fieldType;
		field.fieldDisplayName = fieldVm.fieldDisplayName;
		field.fieldDefaultValue = fieldVm.fieldDefaultValue;
		
		field.templateClass = TemplateClass.findTemplateById(Integer.parseInt(fieldVm.templateId));
		field.save();
		//System.out.println("return");
		return ok(Json.toJson(field.fieldId));
	}
	
	public static Result updateField() throws JsonParseException,JsonMappingException,IOException
	{
		JsonNode json = request().body().asJson();
		System.out.println("Called.....");
		System.out.println("json::"+json);
		String id = json.path("fieldId").asText();
		ArrayNode docs = (ArrayNode)json.path("fields");
		
		for(int i=0;i<docs.size();i++)
		{
			JsonNode par = docs.get(i);
			String fieldName = par.path("name").asText();
			String fieldType = par.path("type").asText();
			TemplateAttribute attribute = TemplateAttribute.getAttribute(Integer.parseInt(id));
			attribute.fieldName = fieldName;
			attribute.fieldType = fieldType;
			attribute.save();
		}
		System.out.println("Attribute Update");
		return ok();
	}
	
	public static Result removeFields() throws UnsupportedEncodingException{
		System.out.println("In RemoveFields.......");
		String decryptedValue = URLDecoder.decode(request().getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		TemplateClass tempateClass = TemplateClass.findTemplateById(Integer.parseInt(decryptedValue));
		String ids = request().getQueryString("fields");
		String[] array = null;
		if(ids.contains(",")){
			array = request().getQueryString("fields").split(",");
		} else {
			array = new String[1];
			array[0] = request().getQueryString("fields");
		}
		
		for(int i=0;i<array.length;i++)
		{
			TemplateAttribute attribute = TemplateAttribute.getAttribute(Integer.parseInt(array[i]));
			attribute.delete();
		}
		System.out.println("Fields are removed.......");
		return ok();
	}
	
	public static Result getAllTemplates() throws UnsupportedEncodingException
	{
		String decryptedValue = URLDecoder.decode(request().getQueryString("id"), "UTF-8");
		
		String procedureName = request().getQueryString("procedureName");
		
		System.out.println("Email "+decryptedValue);
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person.getDoctorByMail(decryptedValue)));
		List<ShowTemplatesVm> templates = new ArrayList<ShowTemplatesVm>();
		List<TemplateClass> temps = TemplateClass.getTemplatesDoctor(doctor.doctorId,procedureName);
		for(TemplateClass temp : temps){
			ShowTemplatesVm vm = new ShowTemplatesVm();
			vm.templateName = temp.templateName;
			vm.procedureName = temp.procedureName;
			vm.templateId  = temp.templateId;
			templates.add(vm);
		}
		return ok(Json.toJson(templates));
	}
	
	public static Result getAllFields() throws UnsupportedEncodingException{
		String decryptedValue = URLDecoder.decode(request().getQueryString("id"), "UTF-8");
		System.out.println("Email "+decryptedValue);
		TemplateClass template = TemplateClass.findTemplateById(Integer.parseInt(decryptedValue));
		List<TemplateAttribute> attributes = TemplateAttribute.getAllAttributes(template);
		System.out.println("Attributes:::"+attributes.size());
		List<ShowFieldVm> fields = new ArrayList<ShowFieldVm>();
		for(TemplateAttribute attribute:attributes){
			ShowFieldVm vm = new ShowFieldVm();
			vm.fieldId = attribute.fieldId;
			vm.fieldName = attribute.fieldName;
			vm.fieldType = attribute.fieldType;
			vm.templateId = template.templateId;
			vm.fieldDisplayName = attribute.fieldDisplayName;
			vm.fieldDefaultValue = attribute.fieldDefaultValue;
			fields.add(vm);
		}
		return ok(Json.toJson(fields));
	}

	public static Result getPatientDetails() {
		List<PatientSearch> patientDoctor = new ArrayList<>();
		List<Person> person = Person.getPatient(request()
				.getQueryString("name"));
		
		for (Person patient : person) {
			List<PatientClientBookAppointment> appointments = PatientClientBookAppointment
					                                        .getAllPatientAppointments(patient.patient);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			String Nextdate = "";
			String nextBookTime = "";
			String nextShift = "";
			Integer clinicId = null;
			Integer doctorId = null;
			String lastVisted = null;
			String doctorEmail = "";
			String appointmentDate = "";
			String appointmentType = "";
			String lastVisitedTime = "";
			String appointmentTime = "";
			ArrayList<PatientClientBookAppointment> visitedList = new ArrayList<PatientClientBookAppointment>();
			for(PatientClientBookAppointment appointment : appointments){
				appointmentDate = "";
				appointmentType = "";
				appointmentTime = "";
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
					appointmentDate = formatter.format(appointment.appointmentDate);
					appointmentTime = appointment.bookTime;
					appointmentType = appointment.visitType;
					
				}else{
					Nextdate = formatter.format(appointment.appointmentDate);
					nextBookTime = appointment.bookTime;
					nextShift = appointment.shift;
					clinicId = appointment.clinicId;
					doctorId = appointment.doctorId;
					break;
				}
				
				if(appointment.isVisited != null){
					if(appointment.isVisited == 1){
						visitedList.add(appointment);
					}
				}
			}
			for(PatientClientBookAppointment visit : visitedList){
				lastVisted = formatter.format(visit.appointmentDate);
				lastVisitedTime = visit.bookTime;
			}
			PatientSearch patientElement = new PatientSearch();
			patientElement.patientId = patient.patient.toString();
			patientElement.name = patient.name;
			patientElement.mobileNumber = patient.mobileNumber;
			patientElement.location = patient.location;
			patientElement.emailID = patient.emailID;
			patientElement.bookDate = Nextdate;
			patientElement.bookTime = nextBookTime;
			patientElement.clinicId = clinicId;
			patientElement.appointmentDate = appointmentDate;
			patientElement.appointmentTime = appointmentTime;
			patientElement.lastVisited = lastVisted;
			patientElement.lastVisitedTime = lastVisitedTime;
			patientElement.gender = patient.gender.toString();
			patientElement.blood_group = patient.bloodGroup;
			patientElement.allergic_to = patient.allergicTo;
			patientElement.shift = nextShift;
			patientElement.doctorId = ""+doctorId;
			System.out.println(patient.name);
			System.out.println(patient.emailID);
			System.out.println(patient.mobileNumber);
			System.out.println(patient.location);
			patientDoctor.add(patientElement);
			
		}
		return ok(Json.toJson(patientDoctor));
	}
	
	public static Result getAllMembersForChat()
	{		
		System.out.println("1");
		String decryptedValue = null;
		String type = null;
		List<ChatVm> chatList = new ArrayList<ChatVm>();
		try {
			decryptedValue = URLDecoder.decode(request().getQueryString("id"),
					"UTF-8");
			type = URLDecoder.decode(request().getQueryString("type"),
					"UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(type.equals("Doctor"))
		{
		
			DoctorRegister doctor = DoctorRegister.getDoctorById((Person
									.getDoctorByMail(decryptedValue)));
			
			for (PatientRegister patient : doctor.patient) 
			{
			
				ChatVm chat = new ChatVm();
				Person p = Person.getPatientsById(patient.patientId);
				chat.name = p.name;
				chat.type = "P";
				chat.id = patient.patientId;
				chatList.add(chat);
		
			}
			List<Delegates> dels = new ArrayList<>();
			dels = Delegates.getAllByParentDoctor(doctor.doctorId);
			for(Delegates del:dels)
			{
		
				if(del.delType.equals("D"))
				{
				
					if((del.status.equals("C")) || (del.status.equals("WC")))
					{
				
						Person p = Person.getDoctorsById(del.delegate);
						System.out.println("dels:::"+p.idPerson);
						ChatVm chat = new ChatVm();
						chat.name = p.name;
						chat.type = "D";
						chat.id = del.id;
						chatList.add(chat);
					}
				}
			}
			List<AssistentRegister>	 assistantReg = doctor.assistentRegister;
			for(AssistentRegister as:assistantReg)
			{
				Person person = Person.getAssistantByAssistantRegisterId(as);
				ChatVm chat = new ChatVm();
				chat.name = person.name;
				chat.type = "A";
				chat.id = as.assitentId;
				chatList.add(chat);
			
			}
			
		}
		else if(type.equals("Patient"))
		{
			PatientRegister patient = PatientRegister.getPatientById((Person
	                                  .getPatientByMail(decryptedValue)));
			System.out.println("Doctors:::"+patient.doctors.size());
			for (DoctorRegister doctor : patient.doctors)
			{
				System.out.println("3");
				Person p = Person.getDoctorsById(doctor.doctorId);
				ChatVm vm = new ChatVm();
				vm.id = p.doctor;
				vm.name = p.name;
				vm.type = "D";
				chatList.add(vm);
				
			}
			List<PatientDependency> deps = PatientDependency.getAllByPatient(patient.patientId);
			System.out.println("Dependency::::"+deps.size());
			for(PatientDependency pd:deps)
			{
				Person p = Person.getPatientsById(pd.dependent);
				ChatVm vm = new ChatVm();
				vm.name = p.name;
				vm.type = "P";
				vm.id = p.patient;
				chatList.add(vm);
			}
		}
		
		return ok(Json.toJson(chatList));
	}

	public static Result getDoctorDetails() {
		List<PatientsDoctor> patientDoctor = new ArrayList<>();
		List<Person> person = Person
				.getDoctor(request().getQueryString("name"));
		/*List<BucketDoctors> bucketDocs = BucketDoctors
				.getDoctor(request().getQueryString("name"));*/
		for (Person doctor : person) {
			System.out.println("2");
			System.out.println(doctor.doctor);
			System.out.println(doctor.name);
			System.out.println(doctor.emailID);
			System.out.println(doctor.mobileNumber);
			System.out.println(doctor.location);
			System.out.println(doctor.gender);
			DoctorRegister d = DoctorRegister.getDoctorById(doctor.doctor);
			patientDoctor.add(new PatientsDoctor(doctor.doctor.toString(),
					doctor.name, d.speciality, doctor.emailID,
					doctor.mobileNumber, doctor.location, 1,""+doctor.gender));
		}
		/*for(BucketDoctors doc:bucketDocs){
			patientDoctor.add(new PatientsDoctor(doc.doctorId.toString(),
					doc.name, doc.speciality, doc.email,
					doc.mobileNumber, doc.location, 2));
		}*/
		return ok(Json.toJson(patientDoctor));
	}

	public static class Schedule {

		public String day;
		public String shift;
		public String from;
		public String to;

	}

	public static class DoctorClinicScheduleVM {
		public String doctorId;
		public String clinicId;
		public List<Schedule> schedules;

	}
	
	public static Result saveDoctorClinicScheduleTime() throws JsonParseException, JsonMappingException, IOException {

/*		Form<DoctorClinicScheduleVM> form = DynamicForm.form(
				DoctorClinicScheduleVM.class).bindFromRequest();
		System.out.println("form"+form);
		DoctorClinicScheduleVM doctorClinicScheduleVM = form.get();*/
		
		JsonNode json = request().body().asJson();
		System.out.println("saveTreatmentPlan json" + json);
		
		ObjectMapper mapper = new ObjectMapper();
		DoctorClinicScheduleVM doctorClinicScheduleVM = mapper.readValue(json.traverse(),DoctorClinicScheduleVM.class);

		System.out.println("doctorClinicScheduleVM.schedules delete" + doctorClinicScheduleVM.schedules);
		// int size = doctorClinicScheduleVM.schedules.size();
		
		if(doctorClinicScheduleVM.schedules != null){
			System.out.println("doctorClinicScheduleVM.schedules delete" + doctorClinicScheduleVM.schedules);
			for (int i = 0; i < doctorClinicScheduleVM.schedules.size(); i++) {
				List <DoctorClinicSchedule> dClinicSchedule =  DoctorClinicSchedule.getDoctorClinicScheduleById(doctorClinicScheduleVM.clinicId,doctorClinicScheduleVM.doctorId);
				   for(DoctorClinicSchedule dcs:dClinicSchedule){
					   dcs.delete();
				   }
				
			}
		}
		if(doctorClinicScheduleVM.schedules != null){
			for (int j = 0; j < doctorClinicScheduleVM.schedules.size(); j++){
				DoctorClinicSchedule doctorClinicSchedule = new DoctorClinicSchedule();
				doctorClinicSchedule.clinicId = doctorClinicScheduleVM.clinicId;
				doctorClinicSchedule.docId = doctorClinicScheduleVM.doctorId;
				System.out.println("doctorClinicScheduleVM.doctorId"+doctorClinicScheduleVM.doctorId);
				doctorClinicSchedule.day = doctorClinicScheduleVM.schedules.get(j).day;
				doctorClinicSchedule.totime = doctorClinicScheduleVM.schedules.get(j).to;
				doctorClinicSchedule.form = doctorClinicScheduleVM.schedules.get(j).from;
				System.out.println("doctorClinicScheduleVM.schedules.get(i).from"+doctorClinicScheduleVM.schedules.get(j).from);
				doctorClinicSchedule.shift = doctorClinicScheduleVM.schedules.get(j).shift;
				System.out.println("doctorClinicScheduleVM.schedules"+ doctorClinicScheduleVM.schedules);
				doctorClinicSchedule.availability = "Available";
				doctorClinicSchedule.markAs = "";
				doctorClinicSchedule.date = "";
				doctorClinicSchedule.save();
			}
		}

			
		
		return ok(Json.toJson(new ErrorResponse(Error.E212.getCode(),
				Error.E212.getMessage())));
	}
	
	public static Result getAllDoctors(){
		List<PatientsDoctor> patientDoctor = new ArrayList<>();
		List<Person> person = Person
				.getAllDoctorById();
		for (Person doctor : person) {
			System.out.println("2");
			System.out.println(doctor.doctor);
			System.out.println(doctor.name);
			System.out.println(doctor.emailID);
			System.out.println(doctor.mobileNumber);
			System.out.println(doctor.location);
			DoctorRegister d = DoctorRegister.getDoctorById(doctor.doctor);
			patientDoctor.add(new PatientsDoctor(doctor.doctor.toString(),
					doctor.name, d.speciality, doctor.emailID,
					doctor.mobileNumber, doctor.location,1));
		}
		return ok(Json.toJson(patientDoctor));
		//return ok();
		
	}
	
	
	public static class DoctorClinicScheduleShiftVM{
		public String day;
		public String from;
		public String  to;
		public String  shift;
		
	}
	public static Result	getClinicScheduleshiftDetails(){
	 String doctorId = "";
	 String clinicId= "";
	 try {
			 doctorId = URLDecoder.decode(
					request().getQueryString("doctorId"), "UTF-8");
			clinicId = URLDecoder.decode(
					request().getQueryString("clinicId"), "UTF-8");
			System.out.println("decryptedValue"+doctorId);
	 
	 	} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 List<DoctorClinicSchedule> doctorClinicSchedule = DoctorClinicSchedule.getClinicScheduleshiftDetails(doctorId,clinicId);
	 ArrayList<DoctorClinicScheduleShiftVM> doctorClinicScheduleShiftVM = new ArrayList<DoctorClinicScheduleShiftVM>();
	 for(DoctorClinicSchedule doctorCS :doctorClinicSchedule){
		 DoctorClinicScheduleShiftVM doc = new DoctorClinicScheduleShiftVM();
		 doc.day = doctorCS.day;
		 doc.from = doctorCS.form;
		 doc.to =  doctorCS.totime;
		 doc.shift = doctorCS.shift;
		 doctorClinicScheduleShiftVM.add(doc);
	 }
	 return ok(Json.toJson(doctorClinicScheduleShiftVM));
	}

	
	public static class AssistantRegisterVM{
		
		//public Integer role;
		public String name;
		public String emailID;
		//public String password;
		//public Long mobileNumber;
		//public String gender;
		//public Date dateOfBirth;
		public String location;
		//public String bloodGroup;
		public int id;
	}
	
	
	public static Result getDoctorAssistant(){
		
		 String doctorEmailId = "";
		 int id = 0;
		 try {
				 doctorEmailId = URLDecoder.decode(
						request().getQueryString("id"), "UTF-8");
		 
		 	} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 Person p = Person.getDoctorsByEmailId(doctorEmailId);
		 if(p != null){
			 id = p.doctor; 
		 }
		
		 DoctorRegister doc =  DoctorRegister.getDoctorById(id);
		List<AssistentRegister>	 assistantReg = doc.assistentRegister;
		System.out.println("assistantReg"+assistantReg.size());
		ArrayList<AssistantRegisterVM>  assistantRegisterVMDetais = new ArrayList<AssistantRegisterVM>(); 
		//assistantReg.get(0).assitentId;
		
			
		//List	<Person> personAssistant =Person.getAssistentByDoctorId(id);
		//for(Person person: personAssistant){
		for(AssistentRegister ass:assistantReg){
			Person person = Person.getAssistantByAssistantRegisterId(ass);
			AssistantRegisterVM assistantRegisterVM = new AssistantRegisterVM();
			//assistantRegisterVM.bloodGroup = person.bloodGroup;
		//	assistantRegisterVM.dateOfBirth = person.dateOfBirth;
			assistantRegisterVM.emailID = person.emailID;
		//	assistantRegisterVM.gender = person.gender;
			assistantRegisterVM.location = person.location;
			
			//assistantRegisterVM.mobileNumber = person.mobileNumber;
			assistantRegisterVM.name = person.name;
		    assistantRegisterVM.id =  person.assistent.assitentId;
			assistantRegisterVMDetais.add(assistantRegisterVM);
		}
		
		
		return ok(Json.toJson(assistantRegisterVMDetais));
	}  
	
	public static Result getClinicDetails() {
		List<Clinic> clinics = Clinic.getClinic(request().getQueryString(
				"clinicName")); 
		List<ClinicVM> clinicVms = new ArrayList<ClinicVM>();
		for(Clinic clinic : clinics){
			ClinicVM vm = new ClinicVM();
			vm.idClinic = ""+clinic.idClinic;
			vm.address = clinic.address;
			vm.location = clinic.location;
			vm.landLineNumber = clinic.landLineNumber;
			vm.mobileNumber = clinic.mobileNumber;
			vm.clinicName = clinic.clinicName;
			vm.doctorId = ""+clinic.doctorId;
			Person person = Person.getDoctorsById(clinic.doctorId);
			vm.doctorEmail = person.emailID;
			vm.speciality = clinic.speciality;
			clinicVms.add(vm);
		}
		
		return ok(Json.toJson(clinicVms));
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
	
	public static Result addDoctorsClinic() throws Exception {
		System.out.println("clinic doctors");
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person
				.getDoctorByMail(decryptedValue)));
		String ids = request().getQueryString("clinics");
		String[] array = null;
		if(ids.contains(",")){
			array = request().getQueryString("clinics").split(",");
		} else {
			array = new String[1];
			array[0] = request().getQueryString("clinics");
		}
		for (Integer i = 0; i < array.length; i++) {
			Clinic clinic = Clinic.getClinicById(Integer
					.parseInt(array[i]));
			doctor.clinic.add(clinic);
		}
		doctor.save();
		System.out.println("clinic doctors close");
		return ok();
	}
	
	public static Result removeDoctorsClinic() throws UnsupportedEncodingException{
		System.out.println("clinic doctors");
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person
				.getDoctorByMail(decryptedValue)));
		String ids = request().getQueryString("clinics");
		String[] array = null;
		if(ids.contains(",")){
			array = request().getQueryString("clinics").split(",");
		} else {
			array = new String[1];
			array[0] = request().getQueryString("clinics");
		}
		for (Integer i = 0; i < array.length; i++) {
			Clinic clinic = Clinic.getClinicById(Integer
					.parseInt(array[i]));
			doctor.clinic.remove(clinic);
		}
		doctor.save();
		System.out.println("clinic doctors close");
		return ok();
	}

	public static Result removeDoctorAssistance() throws UnsupportedEncodingException{
		System.out.println("assist doctors");
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person
				.getDoctorByMail(decryptedValue)));
		String ids = request().getQueryString("assistants");
		String[] array = null;
		if(ids.contains(",")){
			array = request().getQueryString("assistants").split(",");
		} else {
			array = new String[1];
			array[0] = request().getQueryString("assistants");
		}
		for (Integer i = 0; i < array.length; i++) {
			AssistentRegister assist = AssistentRegister.getAssistantById(Integer
					.parseInt(array[i]));
			doctor.assistentRegister.remove(assist);
		}
		doctor.save();
		System.out.println("assist doctors close");
		return ok();
	}
	
	public static Result getAllAssistants(){
		List<AssistantRegisterVM> assistants = new ArrayList<>();
		List<Person> asses = Person
				.getAllAssistantById();
		for (Person ass : asses) {
			AssistantRegisterVM vm = new AssistantRegisterVM();
			vm.id = ass.assistent.assitentId;
			vm.emailID = ass.emailID;
			vm.name = ass.name;
			vm.location = ass.location;
			assistants.add(vm);
		}
		return ok(Json.toJson(assistants));
	}
	
	
	public static Result getDoctorsClinic() throws UnsupportedEncodingException{
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person
				.getDoctorByMail(decryptedValue)));
		List<Clinic> clinics = new ArrayList<>();
		clinics = Clinic.findAllByDoctorId(doctor.doctorId);
		return ok(Json.toJson(clinics));
	}
	
	public static Result addDoctorsAssistants() throws UnsupportedEncodingException{
		System.out.println("assist doctors");
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person
				.getDoctorByMail(decryptedValue)));
		String ids = request().getQueryString("assistants");
		String[] array = null;
		if(ids.contains(",")){
			array = request().getQueryString("assistants").split(",");
		} else {
			array = new String[1];
			array[0] = request().getQueryString("assistants");
		}
		for (Integer i = 0; i < array.length; i++) {
			AssistentRegister ass = AssistentRegister.getAssistantById(Integer
					.parseInt(array[i]));
			doctor.assistentRegister.add(ass);
			System.out.println("assist doctors close");
		}
		doctor.save();
		return ok();
	}
	
	public static Result getAssistantDetails() {
		List<AssistantRegisterVM> assistants = new ArrayList<>();
		List<Person> asses = Person
				.getAssistant(request().getQueryString("name"));
		for (Person ass : asses) {
			AssistantRegisterVM vm = new AssistantRegisterVM();
			vm.id = ass.assistent.assitentId;
			vm.emailID = ass.emailID;
			vm.name = ass.name;
			vm.location = ass.location;
			assistants.add(vm);
		}
		return ok(Json.toJson(assistants));
	}
	
	public static Result addAssistant() throws IOException{
		System.out.println("called...............");
		JsonNode json = request().body().asJson();
		System.out.println("json" + json);
		ObjectMapper mapper = new ObjectMapper();
		RegisterVM register = mapper.readValue(json.traverse(),RegisterVM.class);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Person registration = new Person();
		AssistentRegister assistent = new AssistentRegister();
		assistent.save();
		registration.role = 3;
		registration.name = register.name;
		registration.emailID = register.emailID;
		registration.gender = register.gender;
		registration.mobileNumber = register.mobileNumber;
		try {
			registration.dateOfBirth = format.parse(register.dateOfBirth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		registration.location = register.location;
		registration.bloodGroup = register.bloodGroup;

		registration.assistent = assistent;
		registration.save();
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("doctorId"), "UTF-8");
		System.out.println(decryptedValue);
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person
				.getDoctorByMail(decryptedValue)));
		doctor.assistentRegister.add(assistent);
		doctor.save();
		System.out.println("Return");
		return ok();
	}
	
	public static Result addPatientDependent() throws UnsupportedEncodingException{
		System.out.println("patient dependent");
		JsonNode json = request().body().asJson();
		String email = json.path("patientId").asText();
		PatientRegister patient = PatientRegister.getPatientById((Person.getPatientByMail(email)));
		ArrayNode docs = (ArrayNode) json.path("dependents");
		for(int i=0;i<docs.size();i++){
			JsonNode dep = docs.get(i);
			Integer id = dep.path("id").asInt();
			String accessLevel = dep.path("accessLevel").asText();
			PatientRegister p = PatientRegister.getPatientById(id);
			PatientDependency pd = new PatientDependency();
			pd.patient = patient.patientId;
			pd.dependent = p.patientId;
			pd.status = "WC";
			pd.accessLevel = accessLevel;
			pd.save();
		}
		System.out.println("patient dependent");
		return ok();
	}

	public static Result addDoctorDependent() throws UnsupportedEncodingException{
		System.out.println("patient dependent");
		JsonNode json = request().body().asJson();
		String email = json.path("doctorId").asText();
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person.getDoctorByMail(email)));
		ArrayNode docs = (ArrayNode) json.path("dependents");
		for(int i=0;i<docs.size();i++){
			JsonNode dep = docs.get(i);
			Integer id = dep.path("id").asInt();
			String accessLevel = dep.path("accessLevel").asText();
			PatientRegister p = PatientRegister.getPatientById(id);
			DoctorDependency pd = new DoctorDependency();
			pd.doctor = doctor.doctorId;
			pd.dependent = p.patientId;
			pd.status = "WC";
			pd.accessLevel = accessLevel;
			pd.save();
		}
		System.out.println("doctor dependent");
		return ok();
	}
	public static Result addPatientDependentWithoutConfirmaton() throws UnsupportedEncodingException{
		System.out.println("patient dependent");
		JsonNode json = request().body().asJson();
		String email = json.path("patientId").asText();
		String emailId = json.path("email").asText();
		String password = json.path("password").asText();
		PatientRegister patient = PatientRegister.getPatientById((Person.getPatientByMail(email)));
		ArrayNode docs = (ArrayNode) json.path("dependents");
		
		JsonNode dep = docs.get(0);
		Integer id = dep.path("id").asInt();
		String accessLevel = dep.path("accessLevel").asText();
		PatientRegister p = PatientRegister.getPatientById(id);
		PatientDependency pd = new PatientDependency();
		Person person = Person.getPatientsById(id);
		System.out.println("email db :::::::"+person.emailID);
		System.out.println("Condition :::::::"+(person.emailID).equalsIgnoreCase(emailId));
		if(((person.emailID).equalsIgnoreCase(emailId))&&((person.password).equalsIgnoreCase(password)))
		{
			pd.patient = patient.patientId;
			pd.dependent = p.patientId;
			pd.status = "C";
			pd.accessLevel = accessLevel;
			pd.save();
			System.out.println("patient dependent");
			return ok();
		}
		else
		{
			return ok(Json.toJson("Error"));
		}

			
	}
	public static Result addDoctorDependentWithoutConfirmaton() throws UnsupportedEncodingException{
		System.out.println("Doctor dependent");
		JsonNode json = request().body().asJson();
		String email = json.path("doctorId").asText();
		String emailId = json.path("email").asText();
		String password = json.path("password").asText();
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person.getDoctorByMail(email)));
		ArrayNode docs = (ArrayNode) json.path("dependents");
		
		JsonNode dep = docs.get(0);
		Integer id = dep.path("id").asInt();
		String accessLevel = dep.path("accessLevel").asText();
		PatientRegister p = PatientRegister.getPatientById(id);
		DoctorDependency pd = new DoctorDependency();
		Person person = Person.getPatientsById(id);
		System.out.println("email db :::::::"+person.emailID);
		System.out.println("Condition :::::::"+(person.emailID).equalsIgnoreCase(emailId));
		if(((person.emailID).equalsIgnoreCase(emailId))&&((person.password).equalsIgnoreCase(password)))
		{
			pd.doctor = doctor.doctorId;
			pd.dependent = p.patientId;
			pd.status = "C";
			pd.accessLevel = accessLevel;
			pd.save();
			System.out.println("patient dependent");
			return ok();
		}
		else
		{
			return ok(Json.toJson("Error"));
		}
			
	}
	public static Result getAllPatientDependents() throws UnsupportedEncodingException{
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		PatientRegister patient = PatientRegister.getPatientById((Person.getPatientByMail(decryptedValue)));
		List<PatientSearch> dependents = new ArrayList<>();
		List<PatientDependency> deps = PatientDependency.getAllByPatient(patient.patientId);
		for(PatientDependency pd:deps){
			Person p = Person.getPatientsById(pd.dependent);
			dependents.add(new PatientSearch(p.patient.toString(),
					p.name, p.mobileNumber, p.location, p.emailID, pd.status, pd.accessLevel));
		}
		return ok(Json.toJson(dependents));
	}
	
	public static Result getAllDoctorDependents() throws UnsupportedEncodingException{
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person.getDoctorByMail(decryptedValue)));
		List<PatientSearch> dependents = new ArrayList<>();
		List<DoctorDependency> deps = DoctorDependency.getAllByDoctor(doctor.doctorId);
		for(DoctorDependency pd:deps){
			Person p = Person.getPatientsById(pd.dependent);
			dependents.add(new PatientSearch(p.patient.toString(),
					p.name, p.mobileNumber, p.location, p.emailID, pd.status, pd.accessLevel));
		}
		return ok(Json.toJson(dependents));
	}
	
	public static Result removePatientDependent() throws UnsupportedEncodingException{	
		System.out.println("patient dependent");
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		PatientRegister patient = PatientRegister.getPatientById((Person.getPatientByMail(decryptedValue)));
		String ids = request().getQueryString("dependents");
		String[] array = null;
		if(ids.contains(",")){
			array = request().getQueryString("dependents").split(",");
		} else {
			array = new String[1];
			array[0] = request().getQueryString("dependents");
		}
		for(int i=0;i<array.length;i++){
			PatientDependency pd = PatientDependency.findByPatientDependent(patient.patientId,Integer.parseInt(array[i]));
			pd.delete();
		}
		System.out.println("patient dependent close");
		return ok();
	}
	
	public static Result getAllParents() throws UnsupportedEncodingException{
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		PatientRegister patient = PatientRegister.getPatientById((Person.getPatientByMail(decryptedValue)));
		List<PatientSearch> dependents = new ArrayList<>();
		List<PatientDependency> deps = PatientDependency.getAllByDependent(patient.patientId);
		for(PatientDependency pd:deps){
			Person p = Person.getPatientsById(pd.patient);
			dependents.add(new PatientSearch(p.patient.toString(),
					p.name, p.mobileNumber, p.location, p.emailID, pd.status, pd.accessLevel));
		}
		return ok(Json.toJson(dependents));
	}
	
	public static Result getAllParentsDoctor() throws UnsupportedEncodingException{
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person.getDoctorByMail(decryptedValue)));
		System.out.println("Doctor Id:::::"+doctor.doctorId);
		List<PatientSearch> dependents = new ArrayList<>();
		List<DoctorDependency> deps = DoctorDependency.getAllByDependent(doctor.doctorId);
		System.out.println("list size:::::::"+deps.size());
		for(DoctorDependency pd:deps){
			
			Person p = Person.getPatientsById(pd.doctor);
			
			if(!(pd.dependent == doctor.doctorId))
			{
				dependents.add(new PatientSearch(p.patient.toString(),
						p.name, p.mobileNumber, p.location, p.emailID, pd.status, pd.accessLevel));
			}
		}
		return ok(Json.toJson(dependents));
	}
	
	public static Result confirmOrDenyParent(){
		System.out.println("patient dependent");
		JsonNode json = request().body().asJson();
		String email = json.path("patientId").asText();
		PatientRegister patient = PatientRegister.getPatientById((Person.getPatientByMail(email)));
		ArrayNode docs = (ArrayNode) json.path("parents");
		for(int i=0;i<docs.size();i++){
			JsonNode par = docs.get(i);
			Integer id = par.path("id").asInt();
			String status = par.path("status").asText();
			String accessLevel = par.path("accessLevel").asText();
			PatientDependency pd = PatientDependency.getByPatientDependent(patient.patientId, id);
			pd.status = status;
			pd.accessLevel = accessLevel;
			pd.update();
		}
		System.out.println("patient dependent");
		return ok();
	}
	
	public static Result confirmOrDenyParentDoctor(){
		System.out.println("Doctor dependent");
		JsonNode json = request().body().asJson();
		String email = json.path("patientId").asText();
		DoctorRegister doctor = DoctorRegister.getDoctorById((Person.getDoctorByMail(email)));
		ArrayNode docs = (ArrayNode) json.path("parents");
		for(int i=0;i<docs.size();i++){
			JsonNode par = docs.get(i);
			Integer id = par.path("id").asInt();
			String status = par.path("status").asText();
			String accessLevel = par.path("accessLevel").asText();
			DoctorDependency pd = DoctorDependency.getByPatientDependent(doctor.doctorId, id);
			pd.status = status;
			pd.accessLevel = accessLevel;
			pd.update();
		}
		System.out.println("Doctor dependent");
		return ok();
	}
	public static Result getAllDoctorsAndAssistants(){
		List<Person> docs = Person.getAllDoctorById();
		List<Person> asses = Person.getAllAssistantById();
		List<Person> patients = Person.getAllPatientsById();
		List<PersonVM> all = new ArrayList<>();
		for(Person p:docs){
			all.add(new PersonVM(p.doctor.toString(), p.name, p.mobileNumber, p.location, p.emailID, "D"));
		}
		for(Person p:asses){
			all.add(new PersonVM(p.assistent.assitentId.toString(), p.name, p.mobileNumber, p.location, p.emailID, "A"));
		}
		
		for(Person p:patients){
			all.add(new PersonVM(p.patient.toString(), p.name, p.mobileNumber, p.location, p.emailID, "P"));
		}
		
		return ok(Json.toJson(all));
	}
	
	public static Result addDeleagatesForParent() throws UnsupportedEncodingException{
		JsonNode json = request().body().asJson();
		String email = json.path("id").asText();
		String type = json.path("type").asText();
		ArrayNode dels = (ArrayNode) json.path("delegates");
		if(type.equalsIgnoreCase("D")){
			DoctorRegister doctor = DoctorRegister.getDoctorById((Person.getDoctorByMail(email)));
			for(int i=0;i<dels.size();i++){
				JsonNode del = dels.get(i);
				Integer id = del.path("id").asInt();
				String delType = del.path("type").asText();
				String accessLevel = del.path("accessLevel").asText();
				Delegates d = new Delegates();
				d.parent = doctor.doctorId;
				if(delType.equalsIgnoreCase("D")){
					DoctorRegister doc = DoctorRegister.getDoctorById(id);
					d.delegate = doc.doctorId;
					d.delType = "D";
				} else if(delType.equalsIgnoreCase("A")){
					AssistentRegister ass = AssistentRegister.getAssistantById(id);
					d.delegate = ass.assitentId;
					d.delType = "A";
				}
				else
				{
					PatientRegister patient = PatientRegister.getPatientById(id);
					d.delegate = patient.patientId;
					d.delType = "P";
				}
				d.parentType = "D";
				d.status = "WC";
				d.accessLevel = accessLevel;
				d.save();
			}
		}
		if(type.equalsIgnoreCase("P")){
			PatientRegister patient = PatientRegister.getPatientById((Person.getPatientByMail(email)));
			for(int i=0;i<dels.size();i++){
				JsonNode del = dels.get(i);
				Integer id = del.path("id").asInt();
				String delType = del.path("type").asText();
				String accessLevel = del.path("accessLevel").asText();
				PatientRegister p = PatientRegister.getPatientById(id);
				Delegates d = new Delegates();
				d.parent = patient.patientId;
				if(delType.equalsIgnoreCase("D")){
					DoctorRegister doc = DoctorRegister.getDoctorById(id);
					d.delegate = doc.doctorId;
					d.delType = "D";
				} else if(delType.equalsIgnoreCase("A")){
					AssistentRegister ass = AssistentRegister.getAssistantById(id);
					d.delegate = ass.assitentId;
					d.delType = "A";
				}
				else
				{
					PatientRegister patientRequest = PatientRegister.getPatientById(id);
					d.delegate = patient.patientId;
					d.delType = "P";
				}
				d.parentType = "P";
				d.status = "WC";
				d.accessLevel = accessLevel;
				d.save();
				d.save();
			}
		}
		return ok();
	}

	public static Result getAllDelegatesForParent() throws UnsupportedEncodingException{
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		String type = request().getQueryString("type");
		List<PersonVM> delegates = new ArrayList<>();
		List<Delegates> dels = new ArrayList<>();
		if(type.equalsIgnoreCase("D")){
			DoctorRegister doctor = DoctorRegister.getDoctorById((Person.getDoctorByMail(decryptedValue)));
			dels = Delegates.getAllByParentDoctor(doctor.doctorId);
		} else {
			
			PatientRegister patient = PatientRegister.getPatientById((Person.getPatientByMail(decryptedValue)));
			dels = Delegates.getAllByParentPatient(patient.patientId);
		}
		for(Delegates del:dels){
			if(del.delType.equalsIgnoreCase("D")){
				Person p = Person.getDoctorsById(del.delegate);
				delegates.add(new PersonVM(p.doctor.toString(),p.name, p.mobileNumber, p.location, p.emailID, del.status, del.accessLevel, del.delType));
			} else if(del.delType.equalsIgnoreCase("A")){
				Person p = Person.getAssistantByAssistantRegisterId(AssistentRegister.getAssistantById(del.delegate));
				delegates.add(new PersonVM(del.delegate.toString(),p.name, p.mobileNumber, p.location, p.emailID, del.status, del.accessLevel, del.delType));
			} else {
				Person p = Person.getPatientsById(del.delegate);
				delegates.add(new PersonVM(p.patient.toString(),p.name, p.mobileNumber, p.location, p.emailID, del.status, del.accessLevel, del.delType));
			}
		}
		return ok(Json.toJson(delegates));
	}
	
	
	//remaining
	public static Result removeDelegatesForParent() throws UnsupportedEncodingException{	
		JsonNode json = request().body().asJson();
		String email = json.path("id").asText();
		String type = json.path("type").asText();
		ArrayNode dels = (ArrayNode) json.path("delegates");
		if(type.equalsIgnoreCase("D")){
			DoctorRegister doctor = DoctorRegister.getDoctorById((Person.getDoctorByMail(email)));
			for(int i=0;i<dels.size();i++){
				JsonNode del = dels.get(i);
				Integer id = del.path("id").asInt();
				String delType = del.path("type").asText();
				Delegates d = Delegates.getByParentDelegate(doctor.doctorId,type,id,delType);
				d.delete();
			}
		}
		if(type.equalsIgnoreCase("P")){
			PatientRegister patient = PatientRegister.getPatientById((Person.getPatientByMail(email)));
			for(int i=0;i<dels.size();i++){
				JsonNode del = dels.get(i);
				Integer id = del.path("id").asInt();
				String delType = del.path("type").asText();
				Delegates d = Delegates.getByParentDelegate(patient.patientId,type,id,delType);
				d.delete();
			}
		}
		return ok();
	}
	
	public static Result getAllParentsForDelegates() throws UnsupportedEncodingException{
		String decryptedValue = URLDecoder.decode(request()
				.getQueryString("id"), "UTF-8");
		System.out.println(decryptedValue);
		String type = request().getQueryString("type");
		List<PersonVM> parents = new ArrayList<>();
		List<Delegates> dels = new ArrayList<>();
		if(type.equalsIgnoreCase("D")){
			DoctorRegister doctor = DoctorRegister.getDoctorById((Person.getDoctorByMail(decryptedValue)));
			dels = Delegates.getAllByDelDoctor(doctor.doctorId);
		} else if(type.equalsIgnoreCase("A")){
			AssistentRegister ass = AssistentRegister.getAssistantById((Person.getAssistentByMail(decryptedValue)));
			dels = Delegates.getAllByDelAssistent(ass.assitentId);
		} else {
			PatientRegister patient = PatientRegister.getPatientById((Person.getPatientByMail(decryptedValue)));
			dels = Delegates.getAllByDelPatient(patient.patientId);
		}
		for(Delegates del:dels){
			if(del.parentType.equalsIgnoreCase("D")){
				Person p = Person.getDoctorsById(del.parent);
				parents.add(new PersonVM(p.doctor.toString(),p.name, p.mobileNumber, p.location, p.emailID, del.status, del.accessLevel, del.parentType));
			} else if(del.parentType.equalsIgnoreCase("A")){
				Person p = Person.getAssistantByAssistantRegisterId(AssistentRegister.getAssistantById(del.parent));
				parents.add(new PersonVM(del.delegate.toString(),p.name, p.mobileNumber, p.location, p.emailID, del.status, del.accessLevel, del.parentType));
			} else {
				Person p = Person.getPatientsById(del.parent);
				parents.add(new PersonVM(p.patient.toString(),p.name, p.mobileNumber, p.location, p.emailID, del.status, del.accessLevel, del.parentType));
			}
		}
		return ok(Json.toJson(parents));
	}
	
	
	public static Result confirmOrDenyParentForDelegates(){
		JsonNode json = request().body().asJson();
		String email = json.path("id").asText();
		String type = json.path("type").asText();
		ArrayNode docs = (ArrayNode) json.path("parents");
		if(type.equalsIgnoreCase("D")){
			DoctorRegister doctor = DoctorRegister.getDoctorById((Person.getDoctorByMail(email)));
			for(int i=0;i<docs.size();i++){
				JsonNode par = docs.get(i);
				Integer id = par.path("id").asInt();
				String status = par.path("status").asText();
				String accessLevel = par.path("accessLevel").asText();
				String parType = par.path("type").asText();
				Delegates d = Delegates.getByParentDelegate(id,parType,doctor.doctorId,type);
				d.status = status;
				d.accessLevel = accessLevel;
				d.update();
			}
		} else if(type.equalsIgnoreCase("A")){
			AssistentRegister ass = AssistentRegister.getAssistantById((Person.getAssistentByMail(email)));
			for(int i=0;i<docs.size();i++){
				JsonNode par = docs.get(i);
				Integer id = par.path("id").asInt();
				String status = par.path("status").asText();
				String accessLevel = par.path("accessLevel").asText();
				String parType = par.path("type").asText();
				Delegates d = Delegates.getByParentDelegate(id,parType,ass.assitentId,type);
				d.status = status;
				d.accessLevel = accessLevel;
				d.update();
			}
		} else {
			PatientRegister patient = PatientRegister.getPatientById((Person.getPatientByMail(email)));
			for(int i=0;i<docs.size();i++){
				JsonNode par = docs.get(i);
				Integer id = par.path("id").asInt();
				String status = par.path("status").asText();
				String accessLevel = par.path("accessLevel").asText();
				String parType = par.path("type").asText();
				Delegates d = Delegates.getByParentDelegate(id,parType,patient.patientId,type);
				d.status = status;
				d.accessLevel = accessLevel;
				d.update();
			}
		}
		return ok();
	}
	public static Result uploadFiles() 
	{
		  play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
		  List<String> specialCharactersInSolr = Arrays.asList(new String[]{
		            "+", "-", "&&", "||", "!", "(", ")", "{", "}", "[", "]", "^",
		            "~", "*", "?", ":","\"","\\"," "});

		  DynamicForm form = DynamicForm.form().bindFromRequest();

			String type = form.get("type");
			String doctorId = null;
			String patientId = null;
			String assistentId = null;

			if(!form.get("doctorId").equalsIgnoreCase("null")){
				doctorId = form.get("doctorId");				
			}
			
			if(!form.get("patientId").equalsIgnoreCase("null")){
				 patientId = form.get("patientId");				
			}
			
			if(!form.get("assistentId").equalsIgnoreCase("null")){
			    System.out.println("assistentId +++++++++++" + assistentId);
				 assistentId = form.get("assistentId");				
			}

			String appointmentDate = form.get("appointmentDate");
			String appointmentTime = form.get("appointmentTime");
			String category = form.get("category");
			String documentType = form.get("documentType");
			String name = form.get("name");
		
			String clinicName = form.get("clinicName");
			String clinicId = form.get("clinicId");

		    System.out.println("doctorId" + doctorId);
		    System.out.println("patientId" + patientId);
		    System.out.println("assistentId" + assistentId);
		    
			
			String path = null;
	    	FilePart picture = body.getFile("picture");
	    	String remote = request().remoteAddress();
	    	System.out.println("IP Address::::::"+remote);
	    	String url = Play.application().configuration().getString("base_url");
	    		    	
		  
		  if (picture != null) 
		  {
		    String fileName = picture.getFilename();
		    String contentType = picture.getContentType(); 
		    File file = picture.getFile();
		    String fileNameString = fileName;
		    for(String s : specialCharactersInSolr)
		    {
		    	if(fileNameString.contains(s))
		    	{
		    		fileNameString = fileNameString.replace(""+s, "");
		    	}
		    }
		    fileName = fileNameString;
		    System.out.println("fileNameString::::::"+fileNameString);
		    System.out.println("fileName" + fileNameString);
		    System.out.println("contentType" + contentType);
		    System.out.println("file" + file.getAbsolutePath());
		    File newFile = null;
		    UploadFiles uploadFile = new UploadFiles();
		    if(type.equalsIgnoreCase("doctor")){

		    	File folder = new File(Play.application().configuration().getString("folder_create_url_doctor")+ "//" + doctorId);
		    	System.out.println("Condition:::::::"+folder.exists());
			   	if(folder.exists()){
				    newFile = new File(Play.application().configuration().getString("folder_create_url_doctor")+ "//"+ doctorId + "//" +  fileName);
		            file.renameTo(newFile); //here you are moving photo to new directory
		    	}else{
		    		folder.mkdirs();
				    newFile = new File(Play.application().configuration().getString("folder_create_url_doctor") + "//"+ doctorId + "//" + fileName);
		            file.renameTo(newFile); //here you are moving photo to new directory
		    	}
		    	path = Play.application().configuration().getString("folder_create_url_doctor")+"/" + doctorId + "/"+  fileName;		    	
		    }else if(type.equalsIgnoreCase("assistent")){

		    	File folder = new File(Play.application().configuration().getString("folder_create_url_assistant") + "//" + assistentId);
		    	if(folder.exists()){
				    newFile = new File(Play.application().configuration().getString("folder_create_url_assistant") + "//"+ assistentId + "//" +  fileName);
		            file.renameTo(newFile); //here you are moving photo to new directory
		    	}else{
		    		folder.mkdirs();
				    newFile = new File(Play.application().configuration().getString("folder_create_url_assistant")+ "//"+ assistentId + "//" + fileName);
		            file.renameTo(newFile); //here you are moving photo to new directory
		    	}
		    	path = Play.application().configuration().getString("folder_create_url_assistant")+"/" + assistentId + "/"+  fileName;
		    }else if(type.equalsIgnoreCase("patient")){
		    	File folder = new File(Play.application().configuration().getString("folder_create_url_patient") + "//" + patientId);
		    	System.out.println("patient Condition::::"+folder.exists());
		    	if(folder.exists()){
				    newFile = new File(Play.application().configuration().getString("folder_create_url_patient") + "//"+ patientId + "//" + fileName);
		            file.renameTo(newFile); //here you are moving photo to new directory
		    	}else{
		    		folder.mkdirs();
				    newFile = new File(Play.application().configuration().getString("folder_create_url_patient") + "//"+ patientId + "//" + fileName);
		            file.renameTo(newFile); //here you are moving photo to new directory
		    	}
		    	path = Play.application().configuration().getString("folder_create_url_patient")+"/" + patientId + "/"+  fileName;
		    }

		    if(newFile.exists())
		    {
		    	if(!doctorId.equalsIgnoreCase("0"))
		    	{
		    		uploadFile.doctorId = Person.getDoctorByMail(doctorId); 
		    	}
		    	else
		    	{
		    		uploadFile.doctorId = 0;
		    	}
		    	if(!patientId.equalsIgnoreCase("0"))
		    	{
				    System.out.println("patientId" + patientId);
		    		uploadFile.patientId = Person.getPatientByMail(patientId); 
		    	}
		    	else{
		    		uploadFile.patientId = 0;
		    	}
		    	if(!assistentId.equalsIgnoreCase("0")){
				    System.out.println("assistentId" + assistentId);
		    		uploadFile.assistentId = Person.getAssistentByMail(assistentId); 
		    	}else
		    	{
		    		uploadFile.assistentId = 0;
		    	}
		    	uploadFile.appointmentDate = appointmentDate;
		    	uploadFile.appointmentTime = appointmentTime;
		    	uploadFile.category = category;
		    	uploadFile.name = name;
		    	uploadFile.fileName = fileName;
		    	uploadFile.documentType = documentType;
		    	uploadFile.type = type;
		    	uploadFile.Url = path;
		    	uploadFile.clinicId = clinicId;
		    	uploadFile.clinicName = clinicName;
		    	uploadFile.save();
		    }
		    return ok(Json.toJson(uploadFile));
		  } else 
		  {
			    UploadFiles uploadFile = new UploadFiles();
			    if(!doctorId.equalsIgnoreCase("0"))
		    	{
		    		uploadFile.doctorId = Person.getDoctorByMail(doctorId); 
		    	}
		    	else
		    	{
		    		uploadFile.doctorId = 0;
		    	}
		    	if(!patientId.equalsIgnoreCase("0"))
		    	{
				    System.out.println("patientId" + patientId);
		    		uploadFile.patientId = Person.getPatientByMail(patientId); 
		    	}
		    	else{
		    		uploadFile.patientId = 0;
		    	}
		    	if(!assistentId.equalsIgnoreCase("0")){
				    System.out.println("assistentId" + assistentId);
		    		uploadFile.assistentId = Person.getAssistentByMail(assistentId); 
		    	}else
		    	{
		    		uploadFile.assistentId = 0;
		    	}
			  	uploadFile.appointmentDate = appointmentDate;
		    	uploadFile.appointmentTime = appointmentTime;
		    	uploadFile.category = category;
		    	uploadFile.name = name;
		    	uploadFile.fileName = null;
		    	uploadFile.documentType = documentType;
		    	uploadFile.type = type;
		    	uploadFile.Url = null;
		    	uploadFile.clinicId = clinicId;
		    	uploadFile.clinicName = clinicName;
		    	uploadFile.save();
		    	return ok(Json.toJson(uploadFile));    
		  }
		}
	public static Result getUploadedFiles(){
		Integer docID = Person.getDoctorByMail(request().getQueryString("doctorId"));
		Integer pId = Person.getPatientByMail(request().getQueryString("patientId"));
		String aDate = request().getQueryString("appointmentDate");
		String aTime = request().getQueryString("appointmentTime");
		
		List<UploadFiles> uploadedFiles =  UploadFiles.getUploadedFiles(docID, pId,aDate,aTime);
		System.out.println("KBJSOn:::::::"+Json.toJson(uploadedFiles));
		return ok(Json.toJson(uploadedFiles));
		
	}
	
	public static Result getUploadedFilesForPatient(){
		String docString = request().getQueryString("doctorId");
		System.out.println("Doc id::::"+docString);
		Integer docID = Integer.parseInt(docString);
		Integer pId = Person.getPatientByMail(request().getQueryString("patientId"));
		String aDate = request().getQueryString("appointmentDate");
		String aTime = request().getQueryString("appointmentTime");
		List<UploadFiles> uploadedFiles =  UploadFiles.getUploadedFiles(docID, pId,aDate,aTime);
		System.out.println("KBJSOn:::::::"+Json.toJson(uploadedFiles));
		return ok(Json.toJson(uploadedFiles));
		
	}
	
	public static Result getFile(Long id)
	{			
		System.out.println("panmkaj .........:::::::"+id);
		UploadFiles uploadFile = UploadFiles.getUploadFile(id);
		File file = new File(uploadFile.Url);
		return ok(file);
		
	}
	
	public static Result getPicture(Integer id)
	{
		Person person = Person.getPersonById(id);
	    File file = new File(person.url);
	    return ok(file);
	}
	public static Result saveNotification() throws IOException
	{
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		NotificationVM vm = mapper.readValue(request().body().asJson(),NotificationVM.class);
		ArrayList<Reminder> reminders = vm.reminders;
		System.out.println("Reminders= "+reminders.size());
		for(Reminder rem : reminders){
			Notification notification = new Notification();
			notification.doctorId = rem.id;
			notification.date = rem.date;
			notification.time = rem.time;
			notification.title = rem.title;
			notification.save();
		}
		return ok(Json.toJson("Success"));
	}
	public static Result getNotification() throws IOException{
		String doctorId = null;
		doctorId = URLDecoder.decode(request().getQueryString("email"),"UTF-8");
		List<Notification> notificationDoctor = Notification.getAllNotificationDoctor(doctorId);
		System.out.println("NotificationDoctor= "+notificationDoctor.size());
		ArrayList<Reminder> reminders = new ArrayList<Reminder>();
		for(Notification doctor : notificationDoctor){
			Reminder rem = new Reminder();
			rem.id = doctor.doctorId;
			rem.date = doctor.date;
			rem.time = doctor.time;
			rem.title = doctor.title;
			reminders.add(rem);
		}
		NotificationVM vm = new NotificationVM();
		vm.reminders = reminders;
		return ok(Json.toJson(vm));
	}
	public static Result deleteNotification() throws IOException{
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		NotificationVM vm = mapper.readValue(request().body().asJson(),NotificationVM.class);
		List<Notification> notifications = Notification.getAllNotificationDoctor(vm.reminders.get(0).id);
		System.out.println("Notification List= "+notifications.size());
		for(Reminder rem : vm.reminders){
			for(Notification notif : notifications){
				if((notif.date.equals(rem.date))&&(notif.time.equals(rem.time))){
					notif.delete();
					break;
				}
			}
		}
		return ok(Json.toJson("Success"));
	}
	
	public static Result profilePictureUpdateBase64() throws IOException{
		List<String> specialCharactersInSolr = Arrays.asList(new String[]{
	            "+", "-", "&&", "||", "!", "(", ")", "{", "}", "[", "]", "^",
	            "~", "*", "?", ":","\"","\\"," "});
		JsonNode json = request().body().asJson();
		ObjectMapper mapper = new ObjectMapper();
		UpdateVM vm = mapper.readValue(request().body().asJson(),UpdateVM.class);
		String value = "";
		if(vm != null){
			if(vm.file != null){
				String path = "";
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] imageByte = decoder.decodeBuffer(vm.file);
				Person person = Person.getPersonByMail(vm.email);
				File file = new File(person.url);
				System.out.println("url::::::"+person.url);
				System.out.println("file Name= "+file.getName());
				Boolean result = file.delete();
				System.out.println("Result= "+result);
				if(result){
					 String fileName = vm.fileName;
					 String fileNameString = fileName;
					 for(String s : specialCharactersInSolr)
					 {
					     if(fileNameString.contains(s))
					     {
					    	fileNameString = fileNameString.replace(""+s, "");
					     }
					  }
					  fileName = fileNameString;
					  System.out.println("File Name= "+fileName);
					  File fileBase64;
					  if(person.role == 1){
						  fileBase64 = new File(Play.application().configuration().getString("profile_pic_url_patients")+"//"+fileName+vm.fileExtension);
					  }else{
						  fileBase64 = new File(Play.application().configuration().getString("profile_pic_url_doctors")+"//"+fileName+vm.fileExtension);
					  }
					  
					  BufferedOutputStream bos = null;
					  try{
							  FileOutputStream fos = new FileOutputStream(fileBase64);
						      bos = new BufferedOutputStream(fos); 
						      bos.write(imageByte);
						      if(person.role == 1){
						    	  path = Play.application().configuration().getString("profile_pic_url_patients")+"/" + fileName + vm.fileExtension;
						      }else if(person.role == 2){
						    	  path = Play.application().configuration().getString("profile_pic_url_doctors")+"/" + fileName + vm.fileExtension;
						      }
						      System.out.println("Url:::::::"+path);
						      person.url = path;
						      person.update();
						      value = "Success";
						      
					   }catch(Exception e){
							e.printStackTrace();
						}
						
				}else{
					value = "Failure";
				}
				
			}
		}
		
		return ok (Json.toJson(value));
	}
	public static Result getAllHistory() throws IOException
	{
		System.out.println("called...............");
		String appointmentDate = URLDecoder.decode(request().getQueryString("appointmentDate"),"UTF-8");
		String appointmentTime = URLDecoder.decode(request().getQueryString("appointmentTime"),"UTF-8");
		String doctorEmail = URLDecoder.decode(request().getQueryString("doctorId"),"UTF-8");
		String patientEmail = URLDecoder.decode(request().getQueryString("patientId"),"UTF-8");
		List <SummaryHistory> summaryList = SummaryHistory.getAllSummaryHistory(appointmentDate,appointmentTime,doctorEmail,patientEmail);
		return ok(Json.toJson(summaryList));
	}
	

}
	
