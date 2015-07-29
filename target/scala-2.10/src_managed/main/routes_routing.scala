// @SOURCE:C:/Documents and Settings/User/kaustubh/phpmedical/conf/routes
// @HASH:badf33b30a9b9153a66f21536c1fac1aa56bd078
// @DATE:Wed Jul 29 10:25:50 IST 2015


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix  
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" } 


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:7
private[this] lazy val controllers_Application_login1 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:9
private[this] lazy val controllers_Application_getDoctorDetails2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("searchDoctors"))))
        

// @LINE:10
private[this] lazy val controllers_Application_getPatientDetails3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("searchPatients"))))
        

// @LINE:11
private[this] lazy val controllers_Application_registerPatient4 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("registerPatient"))))
        

// @LINE:12
private[this] lazy val controllers_Application_registerDoctor5 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("registerDoctor"))))
        

// @LINE:13
private[this] lazy val controllers_Application_registerAssistent6 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("registerAssistent"))))
        

// @LINE:14
private[this] lazy val controllers_Application_addDoctor7 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addDoctor"))))
        

// @LINE:15
private[this] lazy val controllers_Application_patientDoctor8 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("patientDoctor"))))
        

// @LINE:16
private[this] lazy val controllers_Application_doctorsPatient9 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("doctorsPatient"))))
        

// @LINE:17
private[this] lazy val controllers_Application_removePatientsDoctor10 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("removePatientsDoctor"))))
        

// @LINE:18
private[this] lazy val controllers_Application_removeDoctorsPatient11 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("removeDoctorsPatient"))))
        

// @LINE:19
private[this] lazy val controllers_Application_getPatientsDoctors12 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getPatientsDoctors"))))
        

// @LINE:20
private[this] lazy val controllers_Application_getDoctorsPatients13 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getDoctorsPatients"))))
        

// @LINE:21
private[this] lazy val controllers_Application_getAllMembersForChat14 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getChatMember"))))
        

// @LINE:22
private[this] lazy val controllers_Application_addClinic15 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addClinic"))))
        

// @LINE:23
private[this] lazy val controllers_Application_updateField16 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("updateField"))))
        

// @LINE:24
private[this] lazy val controllers_Application_removeFields17 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("removeFields"))))
        

// @LINE:25
private[this] lazy val controllers_Application_addField18 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addField"))))
        

// @LINE:26
private[this] lazy val controllers_Application_addTemplate19 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addTemplate"))))
        

// @LINE:27
private[this] lazy val controllers_Application_getClinicDetails20 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("searchClinic"))))
        

// @LINE:28
private[this] lazy val controllers_Application_getDoctorsClinic21 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getDoctorsClinic"))))
        

// @LINE:29
private[this] lazy val controllers_Application_getAllTemplates22 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllTemplates"))))
        

// @LINE:30
private[this] lazy val controllers_Application_getAllFields23 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllFields"))))
        

// @LINE:31
private[this] lazy val controllers_Application_saveDoctorClinicScheduleTime24 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("saveDoctorClinicScheduleTime"))))
        

// @LINE:32
private[this] lazy val controllers_Application_getAllDoctors25 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllDoctors"))))
        

// @LINE:33
private[this] lazy val controllers_Application_getClinicScheduleshiftDetails26 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getClinicScheduleshiftDetails"))))
        

// @LINE:34
private[this] lazy val controllers_Application_getDoctorAssistant27 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getDoctorAssistant"))))
        

// @LINE:35
private[this] lazy val controllers_Application_getAllAssistants28 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllAssistants"))))
        

// @LINE:36
private[this] lazy val controllers_Application_addDoctorsClinic29 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addDoctorsClinic"))))
        

// @LINE:37
private[this] lazy val controllers_Application_removeDoctorsClinic30 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("removeDoctorsClinic"))))
        

// @LINE:38
private[this] lazy val controllers_Application_removeDoctorAssistance31 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("removeDoctorAssistance"))))
        

// @LINE:39
private[this] lazy val controllers_Application_addDoctorsAssistants32 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addDoctorsAssistants"))))
        

// @LINE:40
private[this] lazy val controllers_Application_addAssistant33 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addAssistant"))))
        

// @LINE:41
private[this] lazy val controllers_Application_addPatient34 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addPatient"))))
        

// @LINE:42
private[this] lazy val controllers_Application_getAssistantDetails35 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("searchAssistants"))))
        

// @LINE:43
private[this] lazy val controllers_Application_addPatientDependent36 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addPatientDependent"))))
        

// @LINE:44
private[this] lazy val controllers_Application_getAllPatientDependents37 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllPatientDependents"))))
        

// @LINE:45
private[this] lazy val controllers_Application_removePatientDependent38 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("removePatientDependent"))))
        

// @LINE:46
private[this] lazy val controllers_Application_getAllParents39 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllParents"))))
        

// @LINE:47
private[this] lazy val controllers_Application_confirmOrDenyParent40 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("confirmOrDenyParent"))))
        

// @LINE:48
private[this] lazy val controllers_Application_getAllDoctorsAndAssistants41 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllDoctorsAndAssistants"))))
        

// @LINE:49
private[this] lazy val controllers_Application_addDeleagatesForParent42 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addDeleagatesForParent"))))
        

// @LINE:50
private[this] lazy val controllers_Application_getAllDelegatesForParent43 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllDelegatesForParent"))))
        

// @LINE:51
private[this] lazy val controllers_Application_getAllParentsForDelegates44 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllParentsForDelegates"))))
        

// @LINE:52
private[this] lazy val controllers_Application_confirmOrDenyParentForDelegates45 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("confirmOrDenyParentForDelegates"))))
        

// @LINE:53
private[this] lazy val controllers_Application_removeDelegatesForParent46 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("removeDelegatesForParent"))))
        

// @LINE:54
private[this] lazy val controllers_Application_uploadFiles47 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("uploadFiles"))))
        

// @LINE:55
private[this] lazy val controllers_Application_getUploadedFiles48 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getUploadedFiles"))))
        

// @LINE:56
private[this] lazy val controllers_Application_getUploadedFilesForPatient49 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getUploadedFilesForPatient"))))
        

// @LINE:57
private[this] lazy val controllers_Application_getAllClinic50 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllClinics"))))
        

// @LINE:61
private[this] lazy val controllers_Doctor_getDoctorsClinicsDetails51 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getDoctorsClinicsDetails"))))
        

// @LINE:62
private[this] lazy val controllers_Doctor_getAllPatientInformation52 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllPatientInformation"))))
        

// @LINE:63
private[this] lazy val controllers_Doctor_getAllDoctorPatientClinics53 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllDoctorPatientClinics"))))
        

// @LINE:64
private[this] lazy val controllers_Doctor_saveDoctorNotes54 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("saveDoctorNotes"))))
        

// @LINE:65
private[this] lazy val controllers_Doctor_getDoctorNotes55 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getDoctorNotes"))))
        

// @LINE:66
private[this] lazy val controllers_Doctor_saveDoctorProcedures56 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("saveDoctorProcedures"))))
        

// @LINE:67
private[this] lazy val controllers_Doctor_getDoctorProcedures57 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getDoctorProcedures"))))
        

// @LINE:68
private[this] lazy val controllers_Doctor_addTemplateAllField58 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addTemplateAllField"))))
        

// @LINE:69
private[this] lazy val controllers_Doctor_removeSelectedFields59 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("removeSelectedFields"))))
        

// @LINE:70
private[this] lazy val controllers_Doctor_saveTreatmentPlan60 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("saveTreatmentPlan"))))
        

// @LINE:71
private[this] lazy val controllers_Doctor_getAllTreatmentPlan61 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllTreatmentPlan"))))
        

// @LINE:72
private[this] lazy val controllers_Doctor_getAllDoctorClinics62 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllDoctorClinics"))))
        

// @LINE:73
private[this] lazy val controllers_Doctor_saveInvoices63 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("saveInvoices"))))
        

// @LINE:74
private[this] lazy val controllers_Doctor_getAllInvoices64 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllInvoices"))))
        

// @LINE:75
private[this] lazy val controllers_Doctor_getAllClinicsAppointment65 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllClinicsAppointment"))))
        

// @LINE:76
private[this] lazy val controllers_Doctor_getAllClinicsWeekAppointment66 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllClinicsWeekAppointment"))))
        

// @LINE:77
private[this] lazy val controllers_Doctor_saveTotalInvoice67 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("saveTotalInvoice"))))
        

// @LINE:78
private[this] lazy val controllers_Doctor_getTotalInvoice68 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getTotalInvoice"))))
        

// @LINE:79
private[this] lazy val controllers_Doctor_saveTreatementTemplate69 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("saveTreatementTemplate"))))
        

// @LINE:80
private[this] lazy val controllers_Doctor_saveShareWithPatientTotalInvoice70 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("saveShareWithPatientTotalInvoice"))))
        

// @LINE:81
private[this] lazy val controllers_Doctor_updateTreatementTemplate71 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("updateTreatementTemplate"))))
        

// @LINE:85
private[this] lazy val controllers_Patient_saveClinicsAppointmentDetails72 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("saveClinicsAppointmentDetails"))))
        

// @LINE:86
private[this] lazy val controllers_Patient_getClinicsAppointmentDetails73 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getClinicsAppointmentDetails"))))
        

// @LINE:87
private[this] lazy val controllers_Patient_cancelClinicsAppointment74 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cancelClinicsAppointment"))))
        

// @LINE:88
private[this] lazy val controllers_Patient_getAllPatientAppointment75 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllPatientAppointment"))))
        

// @LINE:89
private[this] lazy val controllers_Patient_saveVisitedPatientAppointment76 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("saveVisitedPatientAppointment"))))
        

// @LINE:90
private[this] lazy val controllers_Patient_savePatientReminder77 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("savePatientReminder"))))
        

// @LINE:91
private[this] lazy val controllers_Patient_getPatientReminder78 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getPatientReminder"))))
        

// @LINE:92
private[this] lazy val controllers_Patient_getAllPatientClinics79 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAllPatientClinics"))))
        

// @LINE:93
private[this] lazy val controllers_Patient_getAlldoctorsOfClinic80 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getAlldoctorsOfClinic"))))
        

// @LINE:96
private[this] lazy val controllers_Assets_at81 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.login()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """searchDoctors""","""controllers.Application.getDoctorDetails()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """searchPatients""","""controllers.Application.getPatientDetails()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """registerPatient""","""controllers.Application.registerPatient()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """registerDoctor""","""controllers.Application.registerDoctor()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """registerAssistent""","""controllers.Application.registerAssistent()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addDoctor""","""controllers.Application.addDoctor()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """patientDoctor""","""controllers.Application.patientDoctor()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """doctorsPatient""","""controllers.Application.doctorsPatient()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """removePatientsDoctor""","""controllers.Application.removePatientsDoctor()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """removeDoctorsPatient""","""controllers.Application.removeDoctorsPatient()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getPatientsDoctors""","""controllers.Application.getPatientsDoctors()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getDoctorsPatients""","""controllers.Application.getDoctorsPatients()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getChatMember""","""controllers.Application.getAllMembersForChat()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addClinic""","""controllers.Application.addClinic()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """updateField""","""controllers.Application.updateField()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """removeFields""","""controllers.Application.removeFields()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addField""","""controllers.Application.addField()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addTemplate""","""controllers.Application.addTemplate()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """searchClinic""","""controllers.Application.getClinicDetails()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getDoctorsClinic""","""controllers.Application.getDoctorsClinic()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllTemplates""","""controllers.Application.getAllTemplates()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllFields""","""controllers.Application.getAllFields()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """saveDoctorClinicScheduleTime""","""controllers.Application.saveDoctorClinicScheduleTime()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllDoctors""","""controllers.Application.getAllDoctors()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getClinicScheduleshiftDetails""","""controllers.Application.getClinicScheduleshiftDetails()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getDoctorAssistant""","""controllers.Application.getDoctorAssistant()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllAssistants""","""controllers.Application.getAllAssistants()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addDoctorsClinic""","""controllers.Application.addDoctorsClinic()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """removeDoctorsClinic""","""controllers.Application.removeDoctorsClinic()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """removeDoctorAssistance""","""controllers.Application.removeDoctorAssistance()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addDoctorsAssistants""","""controllers.Application.addDoctorsAssistants()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addAssistant""","""controllers.Application.addAssistant()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addPatient""","""controllers.Application.addPatient()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """searchAssistants""","""controllers.Application.getAssistantDetails()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addPatientDependent""","""controllers.Application.addPatientDependent()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllPatientDependents""","""controllers.Application.getAllPatientDependents()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """removePatientDependent""","""controllers.Application.removePatientDependent()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllParents""","""controllers.Application.getAllParents()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """confirmOrDenyParent""","""controllers.Application.confirmOrDenyParent()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllDoctorsAndAssistants""","""controllers.Application.getAllDoctorsAndAssistants()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addDeleagatesForParent""","""controllers.Application.addDeleagatesForParent()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllDelegatesForParent""","""controllers.Application.getAllDelegatesForParent()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllParentsForDelegates""","""controllers.Application.getAllParentsForDelegates()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """confirmOrDenyParentForDelegates""","""controllers.Application.confirmOrDenyParentForDelegates()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """removeDelegatesForParent""","""controllers.Application.removeDelegatesForParent()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """uploadFiles""","""controllers.Application.uploadFiles()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getUploadedFiles""","""controllers.Application.getUploadedFiles()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getUploadedFilesForPatient""","""controllers.Application.getUploadedFilesForPatient()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllClinics""","""controllers.Application.getAllClinic()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getDoctorsClinicsDetails""","""controllers.Doctor.getDoctorsClinicsDetails()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllPatientInformation""","""controllers.Doctor.getAllPatientInformation()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllDoctorPatientClinics""","""controllers.Doctor.getAllDoctorPatientClinics()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """saveDoctorNotes""","""controllers.Doctor.saveDoctorNotes()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getDoctorNotes""","""controllers.Doctor.getDoctorNotes()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """saveDoctorProcedures""","""controllers.Doctor.saveDoctorProcedures()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getDoctorProcedures""","""controllers.Doctor.getDoctorProcedures()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addTemplateAllField""","""controllers.Doctor.addTemplateAllField()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """removeSelectedFields""","""controllers.Doctor.removeSelectedFields()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """saveTreatmentPlan""","""controllers.Doctor.saveTreatmentPlan()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllTreatmentPlan""","""controllers.Doctor.getAllTreatmentPlan()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllDoctorClinics""","""controllers.Doctor.getAllDoctorClinics()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """saveInvoices""","""controllers.Doctor.saveInvoices()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllInvoices""","""controllers.Doctor.getAllInvoices()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllClinicsAppointment""","""controllers.Doctor.getAllClinicsAppointment()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllClinicsWeekAppointment""","""controllers.Doctor.getAllClinicsWeekAppointment()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """saveTotalInvoice""","""controllers.Doctor.saveTotalInvoice()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getTotalInvoice""","""controllers.Doctor.getTotalInvoice()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """saveTreatementTemplate""","""controllers.Doctor.saveTreatementTemplate()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """saveShareWithPatientTotalInvoice""","""controllers.Doctor.saveShareWithPatientTotalInvoice()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """updateTreatementTemplate""","""controllers.Doctor.updateTreatementTemplate()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """saveClinicsAppointmentDetails""","""controllers.Patient.saveClinicsAppointmentDetails()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getClinicsAppointmentDetails""","""controllers.Patient.getClinicsAppointmentDetails()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cancelClinicsAppointment""","""controllers.Patient.cancelClinicsAppointment()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllPatientAppointment""","""controllers.Patient.getAllPatientAppointment()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """saveVisitedPatientAppointment""","""controllers.Patient.saveVisitedPatientAppointment()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """savePatientReminder""","""controllers.Patient.savePatientReminder()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getPatientReminder""","""controllers.Patient.getPatientReminder()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAllPatientClinics""","""controllers.Patient.getAllPatientClinics()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getAlldoctorsOfClinic""","""controllers.Patient.getAlldoctorsOfClinic()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
       
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:7
case controllers_Application_login1(params) => {
   call { 
        invokeHandler(controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Nil,"POST", """""", Routes.prefix + """login"""))
   }
}
        

// @LINE:9
case controllers_Application_getDoctorDetails2(params) => {
   call { 
        invokeHandler(controllers.Application.getDoctorDetails(), HandlerDef(this, "controllers.Application", "getDoctorDetails", Nil,"GET", """""", Routes.prefix + """searchDoctors"""))
   }
}
        

// @LINE:10
case controllers_Application_getPatientDetails3(params) => {
   call { 
        invokeHandler(controllers.Application.getPatientDetails(), HandlerDef(this, "controllers.Application", "getPatientDetails", Nil,"GET", """""", Routes.prefix + """searchPatients"""))
   }
}
        

// @LINE:11
case controllers_Application_registerPatient4(params) => {
   call { 
        invokeHandler(controllers.Application.registerPatient(), HandlerDef(this, "controllers.Application", "registerPatient", Nil,"POST", """""", Routes.prefix + """registerPatient"""))
   }
}
        

// @LINE:12
case controllers_Application_registerDoctor5(params) => {
   call { 
        invokeHandler(controllers.Application.registerDoctor(), HandlerDef(this, "controllers.Application", "registerDoctor", Nil,"POST", """""", Routes.prefix + """registerDoctor"""))
   }
}
        

// @LINE:13
case controllers_Application_registerAssistent6(params) => {
   call { 
        invokeHandler(controllers.Application.registerAssistent(), HandlerDef(this, "controllers.Application", "registerAssistent", Nil,"POST", """""", Routes.prefix + """registerAssistent"""))
   }
}
        

// @LINE:14
case controllers_Application_addDoctor7(params) => {
   call { 
        invokeHandler(controllers.Application.addDoctor(), HandlerDef(this, "controllers.Application", "addDoctor", Nil,"POST", """""", Routes.prefix + """addDoctor"""))
   }
}
        

// @LINE:15
case controllers_Application_patientDoctor8(params) => {
   call { 
        invokeHandler(controllers.Application.patientDoctor(), HandlerDef(this, "controllers.Application", "patientDoctor", Nil,"POST", """""", Routes.prefix + """patientDoctor"""))
   }
}
        

// @LINE:16
case controllers_Application_doctorsPatient9(params) => {
   call { 
        invokeHandler(controllers.Application.doctorsPatient(), HandlerDef(this, "controllers.Application", "doctorsPatient", Nil,"POST", """""", Routes.prefix + """doctorsPatient"""))
   }
}
        

// @LINE:17
case controllers_Application_removePatientsDoctor10(params) => {
   call { 
        invokeHandler(controllers.Application.removePatientsDoctor(), HandlerDef(this, "controllers.Application", "removePatientsDoctor", Nil,"POST", """""", Routes.prefix + """removePatientsDoctor"""))
   }
}
        

// @LINE:18
case controllers_Application_removeDoctorsPatient11(params) => {
   call { 
        invokeHandler(controllers.Application.removeDoctorsPatient(), HandlerDef(this, "controllers.Application", "removeDoctorsPatient", Nil,"POST", """""", Routes.prefix + """removeDoctorsPatient"""))
   }
}
        

// @LINE:19
case controllers_Application_getPatientsDoctors12(params) => {
   call { 
        invokeHandler(controllers.Application.getPatientsDoctors(), HandlerDef(this, "controllers.Application", "getPatientsDoctors", Nil,"GET", """""", Routes.prefix + """getPatientsDoctors"""))
   }
}
        

// @LINE:20
case controllers_Application_getDoctorsPatients13(params) => {
   call { 
        invokeHandler(controllers.Application.getDoctorsPatients(), HandlerDef(this, "controllers.Application", "getDoctorsPatients", Nil,"GET", """""", Routes.prefix + """getDoctorsPatients"""))
   }
}
        

// @LINE:21
case controllers_Application_getAllMembersForChat14(params) => {
   call { 
        invokeHandler(controllers.Application.getAllMembersForChat(), HandlerDef(this, "controllers.Application", "getAllMembersForChat", Nil,"GET", """""", Routes.prefix + """getChatMember"""))
   }
}
        

// @LINE:22
case controllers_Application_addClinic15(params) => {
   call { 
        invokeHandler(controllers.Application.addClinic(), HandlerDef(this, "controllers.Application", "addClinic", Nil,"POST", """""", Routes.prefix + """addClinic"""))
   }
}
        

// @LINE:23
case controllers_Application_updateField16(params) => {
   call { 
        invokeHandler(controllers.Application.updateField(), HandlerDef(this, "controllers.Application", "updateField", Nil,"POST", """""", Routes.prefix + """updateField"""))
   }
}
        

// @LINE:24
case controllers_Application_removeFields17(params) => {
   call { 
        invokeHandler(controllers.Application.removeFields(), HandlerDef(this, "controllers.Application", "removeFields", Nil,"POST", """""", Routes.prefix + """removeFields"""))
   }
}
        

// @LINE:25
case controllers_Application_addField18(params) => {
   call { 
        invokeHandler(controllers.Application.addField(), HandlerDef(this, "controllers.Application", "addField", Nil,"POST", """""", Routes.prefix + """addField"""))
   }
}
        

// @LINE:26
case controllers_Application_addTemplate19(params) => {
   call { 
        invokeHandler(controllers.Application.addTemplate(), HandlerDef(this, "controllers.Application", "addTemplate", Nil,"POST", """""", Routes.prefix + """addTemplate"""))
   }
}
        

// @LINE:27
case controllers_Application_getClinicDetails20(params) => {
   call { 
        invokeHandler(controllers.Application.getClinicDetails(), HandlerDef(this, "controllers.Application", "getClinicDetails", Nil,"GET", """""", Routes.prefix + """searchClinic"""))
   }
}
        

// @LINE:28
case controllers_Application_getDoctorsClinic21(params) => {
   call { 
        invokeHandler(controllers.Application.getDoctorsClinic(), HandlerDef(this, "controllers.Application", "getDoctorsClinic", Nil,"GET", """""", Routes.prefix + """getDoctorsClinic"""))
   }
}
        

// @LINE:29
case controllers_Application_getAllTemplates22(params) => {
   call { 
        invokeHandler(controllers.Application.getAllTemplates(), HandlerDef(this, "controllers.Application", "getAllTemplates", Nil,"GET", """""", Routes.prefix + """getAllTemplates"""))
   }
}
        

// @LINE:30
case controllers_Application_getAllFields23(params) => {
   call { 
        invokeHandler(controllers.Application.getAllFields(), HandlerDef(this, "controllers.Application", "getAllFields", Nil,"GET", """""", Routes.prefix + """getAllFields"""))
   }
}
        

// @LINE:31
case controllers_Application_saveDoctorClinicScheduleTime24(params) => {
   call { 
        invokeHandler(controllers.Application.saveDoctorClinicScheduleTime(), HandlerDef(this, "controllers.Application", "saveDoctorClinicScheduleTime", Nil,"POST", """""", Routes.prefix + """saveDoctorClinicScheduleTime"""))
   }
}
        

// @LINE:32
case controllers_Application_getAllDoctors25(params) => {
   call { 
        invokeHandler(controllers.Application.getAllDoctors(), HandlerDef(this, "controllers.Application", "getAllDoctors", Nil,"GET", """""", Routes.prefix + """getAllDoctors"""))
   }
}
        

// @LINE:33
case controllers_Application_getClinicScheduleshiftDetails26(params) => {
   call { 
        invokeHandler(controllers.Application.getClinicScheduleshiftDetails(), HandlerDef(this, "controllers.Application", "getClinicScheduleshiftDetails", Nil,"GET", """""", Routes.prefix + """getClinicScheduleshiftDetails"""))
   }
}
        

// @LINE:34
case controllers_Application_getDoctorAssistant27(params) => {
   call { 
        invokeHandler(controllers.Application.getDoctorAssistant(), HandlerDef(this, "controllers.Application", "getDoctorAssistant", Nil,"GET", """""", Routes.prefix + """getDoctorAssistant"""))
   }
}
        

// @LINE:35
case controllers_Application_getAllAssistants28(params) => {
   call { 
        invokeHandler(controllers.Application.getAllAssistants(), HandlerDef(this, "controllers.Application", "getAllAssistants", Nil,"GET", """""", Routes.prefix + """getAllAssistants"""))
   }
}
        

// @LINE:36
case controllers_Application_addDoctorsClinic29(params) => {
   call { 
        invokeHandler(controllers.Application.addDoctorsClinic(), HandlerDef(this, "controllers.Application", "addDoctorsClinic", Nil,"POST", """""", Routes.prefix + """addDoctorsClinic"""))
   }
}
        

// @LINE:37
case controllers_Application_removeDoctorsClinic30(params) => {
   call { 
        invokeHandler(controllers.Application.removeDoctorsClinic(), HandlerDef(this, "controllers.Application", "removeDoctorsClinic", Nil,"POST", """""", Routes.prefix + """removeDoctorsClinic"""))
   }
}
        

// @LINE:38
case controllers_Application_removeDoctorAssistance31(params) => {
   call { 
        invokeHandler(controllers.Application.removeDoctorAssistance(), HandlerDef(this, "controllers.Application", "removeDoctorAssistance", Nil,"POST", """""", Routes.prefix + """removeDoctorAssistance"""))
   }
}
        

// @LINE:39
case controllers_Application_addDoctorsAssistants32(params) => {
   call { 
        invokeHandler(controllers.Application.addDoctorsAssistants(), HandlerDef(this, "controllers.Application", "addDoctorsAssistants", Nil,"POST", """""", Routes.prefix + """addDoctorsAssistants"""))
   }
}
        

// @LINE:40
case controllers_Application_addAssistant33(params) => {
   call { 
        invokeHandler(controllers.Application.addAssistant(), HandlerDef(this, "controllers.Application", "addAssistant", Nil,"POST", """""", Routes.prefix + """addAssistant"""))
   }
}
        

// @LINE:41
case controllers_Application_addPatient34(params) => {
   call { 
        invokeHandler(controllers.Application.addPatient(), HandlerDef(this, "controllers.Application", "addPatient", Nil,"POST", """""", Routes.prefix + """addPatient"""))
   }
}
        

// @LINE:42
case controllers_Application_getAssistantDetails35(params) => {
   call { 
        invokeHandler(controllers.Application.getAssistantDetails(), HandlerDef(this, "controllers.Application", "getAssistantDetails", Nil,"GET", """""", Routes.prefix + """searchAssistants"""))
   }
}
        

// @LINE:43
case controllers_Application_addPatientDependent36(params) => {
   call { 
        invokeHandler(controllers.Application.addPatientDependent(), HandlerDef(this, "controllers.Application", "addPatientDependent", Nil,"POST", """""", Routes.prefix + """addPatientDependent"""))
   }
}
        

// @LINE:44
case controllers_Application_getAllPatientDependents37(params) => {
   call { 
        invokeHandler(controllers.Application.getAllPatientDependents(), HandlerDef(this, "controllers.Application", "getAllPatientDependents", Nil,"GET", """""", Routes.prefix + """getAllPatientDependents"""))
   }
}
        

// @LINE:45
case controllers_Application_removePatientDependent38(params) => {
   call { 
        invokeHandler(controllers.Application.removePatientDependent(), HandlerDef(this, "controllers.Application", "removePatientDependent", Nil,"POST", """""", Routes.prefix + """removePatientDependent"""))
   }
}
        

// @LINE:46
case controllers_Application_getAllParents39(params) => {
   call { 
        invokeHandler(controllers.Application.getAllParents(), HandlerDef(this, "controllers.Application", "getAllParents", Nil,"GET", """""", Routes.prefix + """getAllParents"""))
   }
}
        

// @LINE:47
case controllers_Application_confirmOrDenyParent40(params) => {
   call { 
        invokeHandler(controllers.Application.confirmOrDenyParent(), HandlerDef(this, "controllers.Application", "confirmOrDenyParent", Nil,"POST", """""", Routes.prefix + """confirmOrDenyParent"""))
   }
}
        

// @LINE:48
case controllers_Application_getAllDoctorsAndAssistants41(params) => {
   call { 
        invokeHandler(controllers.Application.getAllDoctorsAndAssistants(), HandlerDef(this, "controllers.Application", "getAllDoctorsAndAssistants", Nil,"GET", """""", Routes.prefix + """getAllDoctorsAndAssistants"""))
   }
}
        

// @LINE:49
case controllers_Application_addDeleagatesForParent42(params) => {
   call { 
        invokeHandler(controllers.Application.addDeleagatesForParent(), HandlerDef(this, "controllers.Application", "addDeleagatesForParent", Nil,"POST", """""", Routes.prefix + """addDeleagatesForParent"""))
   }
}
        

// @LINE:50
case controllers_Application_getAllDelegatesForParent43(params) => {
   call { 
        invokeHandler(controllers.Application.getAllDelegatesForParent(), HandlerDef(this, "controllers.Application", "getAllDelegatesForParent", Nil,"GET", """""", Routes.prefix + """getAllDelegatesForParent"""))
   }
}
        

// @LINE:51
case controllers_Application_getAllParentsForDelegates44(params) => {
   call { 
        invokeHandler(controllers.Application.getAllParentsForDelegates(), HandlerDef(this, "controllers.Application", "getAllParentsForDelegates", Nil,"GET", """""", Routes.prefix + """getAllParentsForDelegates"""))
   }
}
        

// @LINE:52
case controllers_Application_confirmOrDenyParentForDelegates45(params) => {
   call { 
        invokeHandler(controllers.Application.confirmOrDenyParentForDelegates(), HandlerDef(this, "controllers.Application", "confirmOrDenyParentForDelegates", Nil,"POST", """""", Routes.prefix + """confirmOrDenyParentForDelegates"""))
   }
}
        

// @LINE:53
case controllers_Application_removeDelegatesForParent46(params) => {
   call { 
        invokeHandler(controllers.Application.removeDelegatesForParent(), HandlerDef(this, "controllers.Application", "removeDelegatesForParent", Nil,"POST", """""", Routes.prefix + """removeDelegatesForParent"""))
   }
}
        

// @LINE:54
case controllers_Application_uploadFiles47(params) => {
   call { 
        invokeHandler(controllers.Application.uploadFiles(), HandlerDef(this, "controllers.Application", "uploadFiles", Nil,"POST", """""", Routes.prefix + """uploadFiles"""))
   }
}
        

// @LINE:55
case controllers_Application_getUploadedFiles48(params) => {
   call { 
        invokeHandler(controllers.Application.getUploadedFiles(), HandlerDef(this, "controllers.Application", "getUploadedFiles", Nil,"GET", """""", Routes.prefix + """getUploadedFiles"""))
   }
}
        

// @LINE:56
case controllers_Application_getUploadedFilesForPatient49(params) => {
   call { 
        invokeHandler(controllers.Application.getUploadedFilesForPatient(), HandlerDef(this, "controllers.Application", "getUploadedFilesForPatient", Nil,"GET", """""", Routes.prefix + """getUploadedFilesForPatient"""))
   }
}
        

// @LINE:57
case controllers_Application_getAllClinic50(params) => {
   call { 
        invokeHandler(controllers.Application.getAllClinic(), HandlerDef(this, "controllers.Application", "getAllClinic", Nil,"GET", """""", Routes.prefix + """getAllClinics"""))
   }
}
        

// @LINE:61
case controllers_Doctor_getDoctorsClinicsDetails51(params) => {
   call { 
        invokeHandler(controllers.Doctor.getDoctorsClinicsDetails(), HandlerDef(this, "controllers.Doctor", "getDoctorsClinicsDetails", Nil,"GET", """""", Routes.prefix + """getDoctorsClinicsDetails"""))
   }
}
        

// @LINE:62
case controllers_Doctor_getAllPatientInformation52(params) => {
   call { 
        invokeHandler(controllers.Doctor.getAllPatientInformation(), HandlerDef(this, "controllers.Doctor", "getAllPatientInformation", Nil,"GET", """""", Routes.prefix + """getAllPatientInformation"""))
   }
}
        

// @LINE:63
case controllers_Doctor_getAllDoctorPatientClinics53(params) => {
   call { 
        invokeHandler(controllers.Doctor.getAllDoctorPatientClinics(), HandlerDef(this, "controllers.Doctor", "getAllDoctorPatientClinics", Nil,"GET", """""", Routes.prefix + """getAllDoctorPatientClinics"""))
   }
}
        

// @LINE:64
case controllers_Doctor_saveDoctorNotes54(params) => {
   call { 
        invokeHandler(controllers.Doctor.saveDoctorNotes(), HandlerDef(this, "controllers.Doctor", "saveDoctorNotes", Nil,"POST", """""", Routes.prefix + """saveDoctorNotes"""))
   }
}
        

// @LINE:65
case controllers_Doctor_getDoctorNotes55(params) => {
   call { 
        invokeHandler(controllers.Doctor.getDoctorNotes(), HandlerDef(this, "controllers.Doctor", "getDoctorNotes", Nil,"GET", """""", Routes.prefix + """getDoctorNotes"""))
   }
}
        

// @LINE:66
case controllers_Doctor_saveDoctorProcedures56(params) => {
   call { 
        invokeHandler(controllers.Doctor.saveDoctorProcedures(), HandlerDef(this, "controllers.Doctor", "saveDoctorProcedures", Nil,"POST", """""", Routes.prefix + """saveDoctorProcedures"""))
   }
}
        

// @LINE:67
case controllers_Doctor_getDoctorProcedures57(params) => {
   call { 
        invokeHandler(controllers.Doctor.getDoctorProcedures(), HandlerDef(this, "controllers.Doctor", "getDoctorProcedures", Nil,"GET", """""", Routes.prefix + """getDoctorProcedures"""))
   }
}
        

// @LINE:68
case controllers_Doctor_addTemplateAllField58(params) => {
   call { 
        invokeHandler(controllers.Doctor.addTemplateAllField(), HandlerDef(this, "controllers.Doctor", "addTemplateAllField", Nil,"POST", """""", Routes.prefix + """addTemplateAllField"""))
   }
}
        

// @LINE:69
case controllers_Doctor_removeSelectedFields59(params) => {
   call { 
        invokeHandler(controllers.Doctor.removeSelectedFields(), HandlerDef(this, "controllers.Doctor", "removeSelectedFields", Nil,"POST", """""", Routes.prefix + """removeSelectedFields"""))
   }
}
        

// @LINE:70
case controllers_Doctor_saveTreatmentPlan60(params) => {
   call { 
        invokeHandler(controllers.Doctor.saveTreatmentPlan(), HandlerDef(this, "controllers.Doctor", "saveTreatmentPlan", Nil,"POST", """""", Routes.prefix + """saveTreatmentPlan"""))
   }
}
        

// @LINE:71
case controllers_Doctor_getAllTreatmentPlan61(params) => {
   call { 
        invokeHandler(controllers.Doctor.getAllTreatmentPlan(), HandlerDef(this, "controllers.Doctor", "getAllTreatmentPlan", Nil,"GET", """""", Routes.prefix + """getAllTreatmentPlan"""))
   }
}
        

// @LINE:72
case controllers_Doctor_getAllDoctorClinics62(params) => {
   call { 
        invokeHandler(controllers.Doctor.getAllDoctorClinics(), HandlerDef(this, "controllers.Doctor", "getAllDoctorClinics", Nil,"GET", """""", Routes.prefix + """getAllDoctorClinics"""))
   }
}
        

// @LINE:73
case controllers_Doctor_saveInvoices63(params) => {
   call { 
        invokeHandler(controllers.Doctor.saveInvoices(), HandlerDef(this, "controllers.Doctor", "saveInvoices", Nil,"POST", """""", Routes.prefix + """saveInvoices"""))
   }
}
        

// @LINE:74
case controllers_Doctor_getAllInvoices64(params) => {
   call { 
        invokeHandler(controllers.Doctor.getAllInvoices(), HandlerDef(this, "controllers.Doctor", "getAllInvoices", Nil,"GET", """""", Routes.prefix + """getAllInvoices"""))
   }
}
        

// @LINE:75
case controllers_Doctor_getAllClinicsAppointment65(params) => {
   call { 
        invokeHandler(controllers.Doctor.getAllClinicsAppointment(), HandlerDef(this, "controllers.Doctor", "getAllClinicsAppointment", Nil,"GET", """""", Routes.prefix + """getAllClinicsAppointment"""))
   }
}
        

// @LINE:76
case controllers_Doctor_getAllClinicsWeekAppointment66(params) => {
   call { 
        invokeHandler(controllers.Doctor.getAllClinicsWeekAppointment(), HandlerDef(this, "controllers.Doctor", "getAllClinicsWeekAppointment", Nil,"GET", """""", Routes.prefix + """getAllClinicsWeekAppointment"""))
   }
}
        

// @LINE:77
case controllers_Doctor_saveTotalInvoice67(params) => {
   call { 
        invokeHandler(controllers.Doctor.saveTotalInvoice(), HandlerDef(this, "controllers.Doctor", "saveTotalInvoice", Nil,"POST", """""", Routes.prefix + """saveTotalInvoice"""))
   }
}
        

// @LINE:78
case controllers_Doctor_getTotalInvoice68(params) => {
   call { 
        invokeHandler(controllers.Doctor.getTotalInvoice(), HandlerDef(this, "controllers.Doctor", "getTotalInvoice", Nil,"GET", """""", Routes.prefix + """getTotalInvoice"""))
   }
}
        

// @LINE:79
case controllers_Doctor_saveTreatementTemplate69(params) => {
   call { 
        invokeHandler(controllers.Doctor.saveTreatementTemplate(), HandlerDef(this, "controllers.Doctor", "saveTreatementTemplate", Nil,"POST", """""", Routes.prefix + """saveTreatementTemplate"""))
   }
}
        

// @LINE:80
case controllers_Doctor_saveShareWithPatientTotalInvoice70(params) => {
   call { 
        invokeHandler(controllers.Doctor.saveShareWithPatientTotalInvoice(), HandlerDef(this, "controllers.Doctor", "saveShareWithPatientTotalInvoice", Nil,"POST", """""", Routes.prefix + """saveShareWithPatientTotalInvoice"""))
   }
}
        

// @LINE:81
case controllers_Doctor_updateTreatementTemplate71(params) => {
   call { 
        invokeHandler(controllers.Doctor.updateTreatementTemplate(), HandlerDef(this, "controllers.Doctor", "updateTreatementTemplate", Nil,"POST", """""", Routes.prefix + """updateTreatementTemplate"""))
   }
}
        

// @LINE:85
case controllers_Patient_saveClinicsAppointmentDetails72(params) => {
   call { 
        invokeHandler(controllers.Patient.saveClinicsAppointmentDetails(), HandlerDef(this, "controllers.Patient", "saveClinicsAppointmentDetails", Nil,"POST", """""", Routes.prefix + """saveClinicsAppointmentDetails"""))
   }
}
        

// @LINE:86
case controllers_Patient_getClinicsAppointmentDetails73(params) => {
   call { 
        invokeHandler(controllers.Patient.getClinicsAppointmentDetails(), HandlerDef(this, "controllers.Patient", "getClinicsAppointmentDetails", Nil,"GET", """""", Routes.prefix + """getClinicsAppointmentDetails"""))
   }
}
        

// @LINE:87
case controllers_Patient_cancelClinicsAppointment74(params) => {
   call { 
        invokeHandler(controllers.Patient.cancelClinicsAppointment(), HandlerDef(this, "controllers.Patient", "cancelClinicsAppointment", Nil,"GET", """""", Routes.prefix + """cancelClinicsAppointment"""))
   }
}
        

// @LINE:88
case controllers_Patient_getAllPatientAppointment75(params) => {
   call { 
        invokeHandler(controllers.Patient.getAllPatientAppointment(), HandlerDef(this, "controllers.Patient", "getAllPatientAppointment", Nil,"GET", """""", Routes.prefix + """getAllPatientAppointment"""))
   }
}
        

// @LINE:89
case controllers_Patient_saveVisitedPatientAppointment76(params) => {
   call { 
        invokeHandler(controllers.Patient.saveVisitedPatientAppointment(), HandlerDef(this, "controllers.Patient", "saveVisitedPatientAppointment", Nil,"GET", """""", Routes.prefix + """saveVisitedPatientAppointment"""))
   }
}
        

// @LINE:90
case controllers_Patient_savePatientReminder77(params) => {
   call { 
        invokeHandler(controllers.Patient.savePatientReminder(), HandlerDef(this, "controllers.Patient", "savePatientReminder", Nil,"POST", """""", Routes.prefix + """savePatientReminder"""))
   }
}
        

// @LINE:91
case controllers_Patient_getPatientReminder78(params) => {
   call { 
        invokeHandler(controllers.Patient.getPatientReminder(), HandlerDef(this, "controllers.Patient", "getPatientReminder", Nil,"GET", """""", Routes.prefix + """getPatientReminder"""))
   }
}
        

// @LINE:92
case controllers_Patient_getAllPatientClinics79(params) => {
   call { 
        invokeHandler(controllers.Patient.getAllPatientClinics(), HandlerDef(this, "controllers.Patient", "getAllPatientClinics", Nil,"GET", """""", Routes.prefix + """getAllPatientClinics"""))
   }
}
        

// @LINE:93
case controllers_Patient_getAlldoctorsOfClinic80(params) => {
   call { 
        invokeHandler(controllers.Patient.getAlldoctorsOfClinic(), HandlerDef(this, "controllers.Patient", "getAlldoctorsOfClinic", Nil,"GET", """""", Routes.prefix + """getAlldoctorsOfClinic"""))
   }
}
        

// @LINE:96
case controllers_Assets_at81(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}
    
}
        