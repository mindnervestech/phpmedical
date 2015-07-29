// @SOURCE:C:/Documents and Settings/User/kaustubh/phpmedical/conf/routes
// @HASH:badf33b30a9b9153a66f21536c1fac1aa56bd078
// @DATE:Wed Jul 29 10:25:50 IST 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:96
// @LINE:93
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:87
// @LINE:86
// @LINE:85
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:57
// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
package controllers {

// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:62
// @LINE:61
class ReverseDoctor {
    

// @LINE:67
def getDoctorProcedures(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getDoctorProcedures")
}
                                                

// @LINE:74
def getAllInvoices(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllInvoices")
}
                                                

// @LINE:62
def getAllPatientInformation(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllPatientInformation")
}
                                                

// @LINE:76
def getAllClinicsWeekAppointment(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllClinicsWeekAppointment")
}
                                                

// @LINE:65
def getDoctorNotes(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getDoctorNotes")
}
                                                

// @LINE:69
def removeSelectedFields(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "removeSelectedFields")
}
                                                

// @LINE:80
def saveShareWithPatientTotalInvoice(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "saveShareWithPatientTotalInvoice")
}
                                                

// @LINE:68
def addTemplateAllField(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addTemplateAllField")
}
                                                

// @LINE:75
def getAllClinicsAppointment(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllClinicsAppointment")
}
                                                

// @LINE:81
def updateTreatementTemplate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "updateTreatementTemplate")
}
                                                

// @LINE:63
def getAllDoctorPatientClinics(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllDoctorPatientClinics")
}
                                                

// @LINE:79
def saveTreatementTemplate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "saveTreatementTemplate")
}
                                                

// @LINE:64
def saveDoctorNotes(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "saveDoctorNotes")
}
                                                

// @LINE:77
def saveTotalInvoice(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "saveTotalInvoice")
}
                                                

// @LINE:72
def getAllDoctorClinics(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllDoctorClinics")
}
                                                

// @LINE:61
def getDoctorsClinicsDetails(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getDoctorsClinicsDetails")
}
                                                

// @LINE:66
def saveDoctorProcedures(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "saveDoctorProcedures")
}
                                                

// @LINE:73
def saveInvoices(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "saveInvoices")
}
                                                

// @LINE:71
def getAllTreatmentPlan(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllTreatmentPlan")
}
                                                

// @LINE:78
def getTotalInvoice(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getTotalInvoice")
}
                                                

// @LINE:70
def saveTreatmentPlan(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "saveTreatmentPlan")
}
                                                
    
}
                          

// @LINE:93
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:87
// @LINE:86
// @LINE:85
class ReversePatient {
    

// @LINE:93
def getAlldoctorsOfClinic(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAlldoctorsOfClinic")
}
                                                

// @LINE:90
def savePatientReminder(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "savePatientReminder")
}
                                                

// @LINE:85
def saveClinicsAppointmentDetails(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "saveClinicsAppointmentDetails")
}
                                                

// @LINE:86
def getClinicsAppointmentDetails(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getClinicsAppointmentDetails")
}
                                                

// @LINE:88
def getAllPatientAppointment(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllPatientAppointment")
}
                                                

// @LINE:91
def getPatientReminder(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getPatientReminder")
}
                                                

// @LINE:92
def getAllPatientClinics(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllPatientClinics")
}
                                                

// @LINE:87
def cancelClinicsAppointment(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "cancelClinicsAppointment")
}
                                                

// @LINE:89
def saveVisitedPatientAppointment(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "saveVisitedPatientAppointment")
}
                                                
    
}
                          

// @LINE:57
// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:43
def addPatientDependent(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addPatientDependent")
}
                                                

// @LINE:39
def addDoctorsAssistants(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addDoctorsAssistants")
}
                                                

// @LINE:49
def addDeleagatesForParent(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addDeleagatesForParent")
}
                                                

// @LINE:17
def removePatientsDoctor(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "removePatientsDoctor")
}
                                                

// @LINE:24
def removeFields(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "removeFields")
}
                                                

// @LINE:13
def registerAssistent(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "registerAssistent")
}
                                                

// @LINE:31
def saveDoctorClinicScheduleTime(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "saveDoctorClinicScheduleTime")
}
                                                

// @LINE:38
def removeDoctorAssistance(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "removeDoctorAssistance")
}
                                                

// @LINE:16
def doctorsPatient(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "doctorsPatient")
}
                                                

// @LINE:30
def getAllFields(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllFields")
}
                                                

// @LINE:23
def updateField(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "updateField")
}
                                                

// @LINE:33
def getClinicScheduleshiftDetails(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getClinicScheduleshiftDetails")
}
                                                

// @LINE:7
def login(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "login")
}
                                                

// @LINE:50
def getAllDelegatesForParent(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllDelegatesForParent")
}
                                                

// @LINE:51
def getAllParentsForDelegates(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllParentsForDelegates")
}
                                                

// @LINE:32
def getAllDoctors(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllDoctors")
}
                                                

// @LINE:54
def uploadFiles(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "uploadFiles")
}
                                                

// @LINE:42
def getAssistantDetails(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "searchAssistants")
}
                                                

// @LINE:11
def registerPatient(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "registerPatient")
}
                                                

// @LINE:37
def removeDoctorsClinic(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "removeDoctorsClinic")
}
                                                

// @LINE:14
def addDoctor(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addDoctor")
}
                                                

// @LINE:47
def confirmOrDenyParent(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "confirmOrDenyParent")
}
                                                

// @LINE:28
def getDoctorsClinic(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getDoctorsClinic")
}
                                                

// @LINE:48
def getAllDoctorsAndAssistants(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllDoctorsAndAssistants")
}
                                                

// @LINE:26
def addTemplate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addTemplate")
}
                                                

// @LINE:12
def registerDoctor(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "registerDoctor")
}
                                                

// @LINE:35
def getAllAssistants(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllAssistants")
}
                                                

// @LINE:41
def addPatient(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addPatient")
}
                                                

// @LINE:29
def getAllTemplates(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllTemplates")
}
                                                

// @LINE:36
def addDoctorsClinic(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addDoctorsClinic")
}
                                                

// @LINE:46
def getAllParents(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllParents")
}
                                                

// @LINE:57
def getAllClinic(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllClinics")
}
                                                

// @LINE:18
def removeDoctorsPatient(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "removeDoctorsPatient")
}
                                                

// @LINE:34
def getDoctorAssistant(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getDoctorAssistant")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:40
def addAssistant(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addAssistant")
}
                                                

// @LINE:22
def addClinic(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addClinic")
}
                                                

// @LINE:52
def confirmOrDenyParentForDelegates(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "confirmOrDenyParentForDelegates")
}
                                                

// @LINE:44
def getAllPatientDependents(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getAllPatientDependents")
}
                                                

// @LINE:9
def getDoctorDetails(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "searchDoctors")
}
                                                

// @LINE:20
def getDoctorsPatients(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getDoctorsPatients")
}
                                                

// @LINE:21
def getAllMembersForChat(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getChatMember")
}
                                                

// @LINE:19
def getPatientsDoctors(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getPatientsDoctors")
}
                                                

// @LINE:56
def getUploadedFilesForPatient(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getUploadedFilesForPatient")
}
                                                

// @LINE:45
def removePatientDependent(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "removePatientDependent")
}
                                                

// @LINE:10
def getPatientDetails(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "searchPatients")
}
                                                

// @LINE:27
def getClinicDetails(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "searchClinic")
}
                                                

// @LINE:55
def getUploadedFiles(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getUploadedFiles")
}
                                                

// @LINE:53
def removeDelegatesForParent(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "removeDelegatesForParent")
}
                                                

// @LINE:25
def addField(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addField")
}
                                                

// @LINE:15
def patientDoctor(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "patientDoctor")
}
                                                
    
}
                          

// @LINE:96
class ReverseAssets {
    

// @LINE:96
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          
}
                  


// @LINE:96
// @LINE:93
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:87
// @LINE:86
// @LINE:85
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:57
// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:62
// @LINE:61
class ReverseDoctor {
    

// @LINE:67
def getDoctorProcedures : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.getDoctorProcedures",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getDoctorProcedures"})
      }
   """
)
                        

// @LINE:74
def getAllInvoices : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.getAllInvoices",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllInvoices"})
      }
   """
)
                        

// @LINE:62
def getAllPatientInformation : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.getAllPatientInformation",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllPatientInformation"})
      }
   """
)
                        

// @LINE:76
def getAllClinicsWeekAppointment : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.getAllClinicsWeekAppointment",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllClinicsWeekAppointment"})
      }
   """
)
                        

// @LINE:65
def getDoctorNotes : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.getDoctorNotes",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getDoctorNotes"})
      }
   """
)
                        

// @LINE:69
def removeSelectedFields : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.removeSelectedFields",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "removeSelectedFields"})
      }
   """
)
                        

// @LINE:80
def saveShareWithPatientTotalInvoice : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.saveShareWithPatientTotalInvoice",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "saveShareWithPatientTotalInvoice"})
      }
   """
)
                        

// @LINE:68
def addTemplateAllField : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.addTemplateAllField",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addTemplateAllField"})
      }
   """
)
                        

// @LINE:75
def getAllClinicsAppointment : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.getAllClinicsAppointment",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllClinicsAppointment"})
      }
   """
)
                        

// @LINE:81
def updateTreatementTemplate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.updateTreatementTemplate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "updateTreatementTemplate"})
      }
   """
)
                        

// @LINE:63
def getAllDoctorPatientClinics : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.getAllDoctorPatientClinics",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllDoctorPatientClinics"})
      }
   """
)
                        

// @LINE:79
def saveTreatementTemplate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.saveTreatementTemplate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "saveTreatementTemplate"})
      }
   """
)
                        

// @LINE:64
def saveDoctorNotes : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.saveDoctorNotes",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "saveDoctorNotes"})
      }
   """
)
                        

// @LINE:77
def saveTotalInvoice : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.saveTotalInvoice",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "saveTotalInvoice"})
      }
   """
)
                        

// @LINE:72
def getAllDoctorClinics : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.getAllDoctorClinics",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllDoctorClinics"})
      }
   """
)
                        

// @LINE:61
def getDoctorsClinicsDetails : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.getDoctorsClinicsDetails",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getDoctorsClinicsDetails"})
      }
   """
)
                        

// @LINE:66
def saveDoctorProcedures : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.saveDoctorProcedures",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "saveDoctorProcedures"})
      }
   """
)
                        

// @LINE:73
def saveInvoices : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.saveInvoices",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "saveInvoices"})
      }
   """
)
                        

// @LINE:71
def getAllTreatmentPlan : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.getAllTreatmentPlan",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllTreatmentPlan"})
      }
   """
)
                        

// @LINE:78
def getTotalInvoice : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.getTotalInvoice",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getTotalInvoice"})
      }
   """
)
                        

// @LINE:70
def saveTreatmentPlan : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Doctor.saveTreatmentPlan",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "saveTreatmentPlan"})
      }
   """
)
                        
    
}
              

// @LINE:93
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:87
// @LINE:86
// @LINE:85
class ReversePatient {
    

// @LINE:93
def getAlldoctorsOfClinic : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Patient.getAlldoctorsOfClinic",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAlldoctorsOfClinic"})
      }
   """
)
                        

// @LINE:90
def savePatientReminder : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Patient.savePatientReminder",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "savePatientReminder"})
      }
   """
)
                        

// @LINE:85
def saveClinicsAppointmentDetails : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Patient.saveClinicsAppointmentDetails",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "saveClinicsAppointmentDetails"})
      }
   """
)
                        

// @LINE:86
def getClinicsAppointmentDetails : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Patient.getClinicsAppointmentDetails",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getClinicsAppointmentDetails"})
      }
   """
)
                        

// @LINE:88
def getAllPatientAppointment : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Patient.getAllPatientAppointment",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllPatientAppointment"})
      }
   """
)
                        

// @LINE:91
def getPatientReminder : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Patient.getPatientReminder",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getPatientReminder"})
      }
   """
)
                        

// @LINE:92
def getAllPatientClinics : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Patient.getAllPatientClinics",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllPatientClinics"})
      }
   """
)
                        

// @LINE:87
def cancelClinicsAppointment : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Patient.cancelClinicsAppointment",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "cancelClinicsAppointment"})
      }
   """
)
                        

// @LINE:89
def saveVisitedPatientAppointment : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Patient.saveVisitedPatientAppointment",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "saveVisitedPatientAppointment"})
      }
   """
)
                        
    
}
              

// @LINE:57
// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:43
def addPatientDependent : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addPatientDependent",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addPatientDependent"})
      }
   """
)
                        

// @LINE:39
def addDoctorsAssistants : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addDoctorsAssistants",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addDoctorsAssistants"})
      }
   """
)
                        

// @LINE:49
def addDeleagatesForParent : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addDeleagatesForParent",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addDeleagatesForParent"})
      }
   """
)
                        

// @LINE:17
def removePatientsDoctor : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.removePatientsDoctor",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "removePatientsDoctor"})
      }
   """
)
                        

// @LINE:24
def removeFields : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.removeFields",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "removeFields"})
      }
   """
)
                        

// @LINE:13
def registerAssistent : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.registerAssistent",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "registerAssistent"})
      }
   """
)
                        

// @LINE:31
def saveDoctorClinicScheduleTime : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.saveDoctorClinicScheduleTime",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "saveDoctorClinicScheduleTime"})
      }
   """
)
                        

// @LINE:38
def removeDoctorAssistance : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.removeDoctorAssistance",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "removeDoctorAssistance"})
      }
   """
)
                        

// @LINE:16
def doctorsPatient : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.doctorsPatient",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "doctorsPatient"})
      }
   """
)
                        

// @LINE:30
def getAllFields : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllFields",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllFields"})
      }
   """
)
                        

// @LINE:23
def updateField : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.updateField",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "updateField"})
      }
   """
)
                        

// @LINE:33
def getClinicScheduleshiftDetails : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getClinicScheduleshiftDetails",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getClinicScheduleshiftDetails"})
      }
   """
)
                        

// @LINE:7
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.login",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        

// @LINE:50
def getAllDelegatesForParent : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllDelegatesForParent",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllDelegatesForParent"})
      }
   """
)
                        

// @LINE:51
def getAllParentsForDelegates : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllParentsForDelegates",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllParentsForDelegates"})
      }
   """
)
                        

// @LINE:32
def getAllDoctors : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllDoctors",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllDoctors"})
      }
   """
)
                        

// @LINE:54
def uploadFiles : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.uploadFiles",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "uploadFiles"})
      }
   """
)
                        

// @LINE:42
def getAssistantDetails : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAssistantDetails",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "searchAssistants"})
      }
   """
)
                        

// @LINE:11
def registerPatient : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.registerPatient",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "registerPatient"})
      }
   """
)
                        

// @LINE:37
def removeDoctorsClinic : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.removeDoctorsClinic",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "removeDoctorsClinic"})
      }
   """
)
                        

// @LINE:14
def addDoctor : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addDoctor",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addDoctor"})
      }
   """
)
                        

// @LINE:47
def confirmOrDenyParent : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.confirmOrDenyParent",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "confirmOrDenyParent"})
      }
   """
)
                        

// @LINE:28
def getDoctorsClinic : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getDoctorsClinic",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getDoctorsClinic"})
      }
   """
)
                        

// @LINE:48
def getAllDoctorsAndAssistants : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllDoctorsAndAssistants",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllDoctorsAndAssistants"})
      }
   """
)
                        

// @LINE:26
def addTemplate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addTemplate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addTemplate"})
      }
   """
)
                        

// @LINE:12
def registerDoctor : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.registerDoctor",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "registerDoctor"})
      }
   """
)
                        

// @LINE:35
def getAllAssistants : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllAssistants",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllAssistants"})
      }
   """
)
                        

// @LINE:41
def addPatient : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addPatient",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addPatient"})
      }
   """
)
                        

// @LINE:29
def getAllTemplates : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllTemplates",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllTemplates"})
      }
   """
)
                        

// @LINE:36
def addDoctorsClinic : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addDoctorsClinic",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addDoctorsClinic"})
      }
   """
)
                        

// @LINE:46
def getAllParents : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllParents",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllParents"})
      }
   """
)
                        

// @LINE:57
def getAllClinic : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllClinic",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllClinics"})
      }
   """
)
                        

// @LINE:18
def removeDoctorsPatient : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.removeDoctorsPatient",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "removeDoctorsPatient"})
      }
   """
)
                        

// @LINE:34
def getDoctorAssistant : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getDoctorAssistant",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getDoctorAssistant"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:40
def addAssistant : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addAssistant",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addAssistant"})
      }
   """
)
                        

// @LINE:22
def addClinic : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addClinic",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addClinic"})
      }
   """
)
                        

// @LINE:52
def confirmOrDenyParentForDelegates : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.confirmOrDenyParentForDelegates",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "confirmOrDenyParentForDelegates"})
      }
   """
)
                        

// @LINE:44
def getAllPatientDependents : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllPatientDependents",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getAllPatientDependents"})
      }
   """
)
                        

// @LINE:9
def getDoctorDetails : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getDoctorDetails",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "searchDoctors"})
      }
   """
)
                        

// @LINE:20
def getDoctorsPatients : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getDoctorsPatients",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getDoctorsPatients"})
      }
   """
)
                        

// @LINE:21
def getAllMembersForChat : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getAllMembersForChat",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getChatMember"})
      }
   """
)
                        

// @LINE:19
def getPatientsDoctors : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getPatientsDoctors",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getPatientsDoctors"})
      }
   """
)
                        

// @LINE:56
def getUploadedFilesForPatient : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getUploadedFilesForPatient",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getUploadedFilesForPatient"})
      }
   """
)
                        

// @LINE:45
def removePatientDependent : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.removePatientDependent",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "removePatientDependent"})
      }
   """
)
                        

// @LINE:10
def getPatientDetails : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getPatientDetails",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "searchPatients"})
      }
   """
)
                        

// @LINE:27
def getClinicDetails : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getClinicDetails",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "searchClinic"})
      }
   """
)
                        

// @LINE:55
def getUploadedFiles : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getUploadedFiles",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getUploadedFiles"})
      }
   """
)
                        

// @LINE:53
def removeDelegatesForParent : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.removeDelegatesForParent",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "removeDelegatesForParent"})
      }
   """
)
                        

// @LINE:25
def addField : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addField",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addField"})
      }
   """
)
                        

// @LINE:15
def patientDoctor : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.patientDoctor",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "patientDoctor"})
      }
   """
)
                        
    
}
              

// @LINE:96
class ReverseAssets {
    

// @LINE:96
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              
}
        


// @LINE:96
// @LINE:93
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:87
// @LINE:86
// @LINE:85
// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:57
// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
package controllers.ref {

// @LINE:81
// @LINE:80
// @LINE:79
// @LINE:78
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:73
// @LINE:72
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:66
// @LINE:65
// @LINE:64
// @LINE:63
// @LINE:62
// @LINE:61
class ReverseDoctor {
    

// @LINE:67
def getDoctorProcedures(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.getDoctorProcedures(), HandlerDef(this, "controllers.Doctor", "getDoctorProcedures", Seq(), "GET", """""", _prefix + """getDoctorProcedures""")
)
                      

// @LINE:74
def getAllInvoices(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.getAllInvoices(), HandlerDef(this, "controllers.Doctor", "getAllInvoices", Seq(), "GET", """""", _prefix + """getAllInvoices""")
)
                      

// @LINE:62
def getAllPatientInformation(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.getAllPatientInformation(), HandlerDef(this, "controllers.Doctor", "getAllPatientInformation", Seq(), "GET", """""", _prefix + """getAllPatientInformation""")
)
                      

// @LINE:76
def getAllClinicsWeekAppointment(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.getAllClinicsWeekAppointment(), HandlerDef(this, "controllers.Doctor", "getAllClinicsWeekAppointment", Seq(), "GET", """""", _prefix + """getAllClinicsWeekAppointment""")
)
                      

// @LINE:65
def getDoctorNotes(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.getDoctorNotes(), HandlerDef(this, "controllers.Doctor", "getDoctorNotes", Seq(), "GET", """""", _prefix + """getDoctorNotes""")
)
                      

// @LINE:69
def removeSelectedFields(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.removeSelectedFields(), HandlerDef(this, "controllers.Doctor", "removeSelectedFields", Seq(), "POST", """""", _prefix + """removeSelectedFields""")
)
                      

// @LINE:80
def saveShareWithPatientTotalInvoice(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.saveShareWithPatientTotalInvoice(), HandlerDef(this, "controllers.Doctor", "saveShareWithPatientTotalInvoice", Seq(), "POST", """""", _prefix + """saveShareWithPatientTotalInvoice""")
)
                      

// @LINE:68
def addTemplateAllField(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.addTemplateAllField(), HandlerDef(this, "controllers.Doctor", "addTemplateAllField", Seq(), "POST", """""", _prefix + """addTemplateAllField""")
)
                      

// @LINE:75
def getAllClinicsAppointment(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.getAllClinicsAppointment(), HandlerDef(this, "controllers.Doctor", "getAllClinicsAppointment", Seq(), "GET", """""", _prefix + """getAllClinicsAppointment""")
)
                      

// @LINE:81
def updateTreatementTemplate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.updateTreatementTemplate(), HandlerDef(this, "controllers.Doctor", "updateTreatementTemplate", Seq(), "POST", """""", _prefix + """updateTreatementTemplate""")
)
                      

// @LINE:63
def getAllDoctorPatientClinics(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.getAllDoctorPatientClinics(), HandlerDef(this, "controllers.Doctor", "getAllDoctorPatientClinics", Seq(), "GET", """""", _prefix + """getAllDoctorPatientClinics""")
)
                      

// @LINE:79
def saveTreatementTemplate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.saveTreatementTemplate(), HandlerDef(this, "controllers.Doctor", "saveTreatementTemplate", Seq(), "POST", """""", _prefix + """saveTreatementTemplate""")
)
                      

// @LINE:64
def saveDoctorNotes(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.saveDoctorNotes(), HandlerDef(this, "controllers.Doctor", "saveDoctorNotes", Seq(), "POST", """""", _prefix + """saveDoctorNotes""")
)
                      

// @LINE:77
def saveTotalInvoice(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.saveTotalInvoice(), HandlerDef(this, "controllers.Doctor", "saveTotalInvoice", Seq(), "POST", """""", _prefix + """saveTotalInvoice""")
)
                      

// @LINE:72
def getAllDoctorClinics(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.getAllDoctorClinics(), HandlerDef(this, "controllers.Doctor", "getAllDoctorClinics", Seq(), "GET", """""", _prefix + """getAllDoctorClinics""")
)
                      

// @LINE:61
def getDoctorsClinicsDetails(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.getDoctorsClinicsDetails(), HandlerDef(this, "controllers.Doctor", "getDoctorsClinicsDetails", Seq(), "GET", """""", _prefix + """getDoctorsClinicsDetails""")
)
                      

// @LINE:66
def saveDoctorProcedures(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.saveDoctorProcedures(), HandlerDef(this, "controllers.Doctor", "saveDoctorProcedures", Seq(), "POST", """""", _prefix + """saveDoctorProcedures""")
)
                      

// @LINE:73
def saveInvoices(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.saveInvoices(), HandlerDef(this, "controllers.Doctor", "saveInvoices", Seq(), "POST", """""", _prefix + """saveInvoices""")
)
                      

// @LINE:71
def getAllTreatmentPlan(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.getAllTreatmentPlan(), HandlerDef(this, "controllers.Doctor", "getAllTreatmentPlan", Seq(), "GET", """""", _prefix + """getAllTreatmentPlan""")
)
                      

// @LINE:78
def getTotalInvoice(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.getTotalInvoice(), HandlerDef(this, "controllers.Doctor", "getTotalInvoice", Seq(), "GET", """""", _prefix + """getTotalInvoice""")
)
                      

// @LINE:70
def saveTreatmentPlan(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Doctor.saveTreatmentPlan(), HandlerDef(this, "controllers.Doctor", "saveTreatmentPlan", Seq(), "POST", """""", _prefix + """saveTreatmentPlan""")
)
                      
    
}
                          

// @LINE:93
// @LINE:92
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:87
// @LINE:86
// @LINE:85
class ReversePatient {
    

// @LINE:93
def getAlldoctorsOfClinic(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Patient.getAlldoctorsOfClinic(), HandlerDef(this, "controllers.Patient", "getAlldoctorsOfClinic", Seq(), "GET", """""", _prefix + """getAlldoctorsOfClinic""")
)
                      

// @LINE:90
def savePatientReminder(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Patient.savePatientReminder(), HandlerDef(this, "controllers.Patient", "savePatientReminder", Seq(), "POST", """""", _prefix + """savePatientReminder""")
)
                      

// @LINE:85
def saveClinicsAppointmentDetails(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Patient.saveClinicsAppointmentDetails(), HandlerDef(this, "controllers.Patient", "saveClinicsAppointmentDetails", Seq(), "POST", """""", _prefix + """saveClinicsAppointmentDetails""")
)
                      

// @LINE:86
def getClinicsAppointmentDetails(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Patient.getClinicsAppointmentDetails(), HandlerDef(this, "controllers.Patient", "getClinicsAppointmentDetails", Seq(), "GET", """""", _prefix + """getClinicsAppointmentDetails""")
)
                      

// @LINE:88
def getAllPatientAppointment(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Patient.getAllPatientAppointment(), HandlerDef(this, "controllers.Patient", "getAllPatientAppointment", Seq(), "GET", """""", _prefix + """getAllPatientAppointment""")
)
                      

// @LINE:91
def getPatientReminder(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Patient.getPatientReminder(), HandlerDef(this, "controllers.Patient", "getPatientReminder", Seq(), "GET", """""", _prefix + """getPatientReminder""")
)
                      

// @LINE:92
def getAllPatientClinics(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Patient.getAllPatientClinics(), HandlerDef(this, "controllers.Patient", "getAllPatientClinics", Seq(), "GET", """""", _prefix + """getAllPatientClinics""")
)
                      

// @LINE:87
def cancelClinicsAppointment(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Patient.cancelClinicsAppointment(), HandlerDef(this, "controllers.Patient", "cancelClinicsAppointment", Seq(), "GET", """""", _prefix + """cancelClinicsAppointment""")
)
                      

// @LINE:89
def saveVisitedPatientAppointment(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Patient.saveVisitedPatientAppointment(), HandlerDef(this, "controllers.Patient", "saveVisitedPatientAppointment", Seq(), "GET", """""", _prefix + """saveVisitedPatientAppointment""")
)
                      
    
}
                          

// @LINE:57
// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:47
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:31
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:27
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:43
def addPatientDependent(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addPatientDependent(), HandlerDef(this, "controllers.Application", "addPatientDependent", Seq(), "POST", """""", _prefix + """addPatientDependent""")
)
                      

// @LINE:39
def addDoctorsAssistants(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addDoctorsAssistants(), HandlerDef(this, "controllers.Application", "addDoctorsAssistants", Seq(), "POST", """""", _prefix + """addDoctorsAssistants""")
)
                      

// @LINE:49
def addDeleagatesForParent(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addDeleagatesForParent(), HandlerDef(this, "controllers.Application", "addDeleagatesForParent", Seq(), "POST", """""", _prefix + """addDeleagatesForParent""")
)
                      

// @LINE:17
def removePatientsDoctor(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.removePatientsDoctor(), HandlerDef(this, "controllers.Application", "removePatientsDoctor", Seq(), "POST", """""", _prefix + """removePatientsDoctor""")
)
                      

// @LINE:24
def removeFields(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.removeFields(), HandlerDef(this, "controllers.Application", "removeFields", Seq(), "POST", """""", _prefix + """removeFields""")
)
                      

// @LINE:13
def registerAssistent(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.registerAssistent(), HandlerDef(this, "controllers.Application", "registerAssistent", Seq(), "POST", """""", _prefix + """registerAssistent""")
)
                      

// @LINE:31
def saveDoctorClinicScheduleTime(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.saveDoctorClinicScheduleTime(), HandlerDef(this, "controllers.Application", "saveDoctorClinicScheduleTime", Seq(), "POST", """""", _prefix + """saveDoctorClinicScheduleTime""")
)
                      

// @LINE:38
def removeDoctorAssistance(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.removeDoctorAssistance(), HandlerDef(this, "controllers.Application", "removeDoctorAssistance", Seq(), "POST", """""", _prefix + """removeDoctorAssistance""")
)
                      

// @LINE:16
def doctorsPatient(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.doctorsPatient(), HandlerDef(this, "controllers.Application", "doctorsPatient", Seq(), "POST", """""", _prefix + """doctorsPatient""")
)
                      

// @LINE:30
def getAllFields(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllFields(), HandlerDef(this, "controllers.Application", "getAllFields", Seq(), "GET", """""", _prefix + """getAllFields""")
)
                      

// @LINE:23
def updateField(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.updateField(), HandlerDef(this, "controllers.Application", "updateField", Seq(), "POST", """""", _prefix + """updateField""")
)
                      

// @LINE:33
def getClinicScheduleshiftDetails(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getClinicScheduleshiftDetails(), HandlerDef(this, "controllers.Application", "getClinicScheduleshiftDetails", Seq(), "GET", """""", _prefix + """getClinicScheduleshiftDetails""")
)
                      

// @LINE:7
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Seq(), "POST", """""", _prefix + """login""")
)
                      

// @LINE:50
def getAllDelegatesForParent(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllDelegatesForParent(), HandlerDef(this, "controllers.Application", "getAllDelegatesForParent", Seq(), "GET", """""", _prefix + """getAllDelegatesForParent""")
)
                      

// @LINE:51
def getAllParentsForDelegates(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllParentsForDelegates(), HandlerDef(this, "controllers.Application", "getAllParentsForDelegates", Seq(), "GET", """""", _prefix + """getAllParentsForDelegates""")
)
                      

// @LINE:32
def getAllDoctors(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllDoctors(), HandlerDef(this, "controllers.Application", "getAllDoctors", Seq(), "GET", """""", _prefix + """getAllDoctors""")
)
                      

// @LINE:54
def uploadFiles(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.uploadFiles(), HandlerDef(this, "controllers.Application", "uploadFiles", Seq(), "POST", """""", _prefix + """uploadFiles""")
)
                      

// @LINE:42
def getAssistantDetails(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAssistantDetails(), HandlerDef(this, "controllers.Application", "getAssistantDetails", Seq(), "GET", """""", _prefix + """searchAssistants""")
)
                      

// @LINE:11
def registerPatient(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.registerPatient(), HandlerDef(this, "controllers.Application", "registerPatient", Seq(), "POST", """""", _prefix + """registerPatient""")
)
                      

// @LINE:37
def removeDoctorsClinic(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.removeDoctorsClinic(), HandlerDef(this, "controllers.Application", "removeDoctorsClinic", Seq(), "POST", """""", _prefix + """removeDoctorsClinic""")
)
                      

// @LINE:14
def addDoctor(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addDoctor(), HandlerDef(this, "controllers.Application", "addDoctor", Seq(), "POST", """""", _prefix + """addDoctor""")
)
                      

// @LINE:47
def confirmOrDenyParent(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.confirmOrDenyParent(), HandlerDef(this, "controllers.Application", "confirmOrDenyParent", Seq(), "POST", """""", _prefix + """confirmOrDenyParent""")
)
                      

// @LINE:28
def getDoctorsClinic(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getDoctorsClinic(), HandlerDef(this, "controllers.Application", "getDoctorsClinic", Seq(), "GET", """""", _prefix + """getDoctorsClinic""")
)
                      

// @LINE:48
def getAllDoctorsAndAssistants(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllDoctorsAndAssistants(), HandlerDef(this, "controllers.Application", "getAllDoctorsAndAssistants", Seq(), "GET", """""", _prefix + """getAllDoctorsAndAssistants""")
)
                      

// @LINE:26
def addTemplate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addTemplate(), HandlerDef(this, "controllers.Application", "addTemplate", Seq(), "POST", """""", _prefix + """addTemplate""")
)
                      

// @LINE:12
def registerDoctor(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.registerDoctor(), HandlerDef(this, "controllers.Application", "registerDoctor", Seq(), "POST", """""", _prefix + """registerDoctor""")
)
                      

// @LINE:35
def getAllAssistants(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllAssistants(), HandlerDef(this, "controllers.Application", "getAllAssistants", Seq(), "GET", """""", _prefix + """getAllAssistants""")
)
                      

// @LINE:41
def addPatient(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addPatient(), HandlerDef(this, "controllers.Application", "addPatient", Seq(), "POST", """""", _prefix + """addPatient""")
)
                      

// @LINE:29
def getAllTemplates(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllTemplates(), HandlerDef(this, "controllers.Application", "getAllTemplates", Seq(), "GET", """""", _prefix + """getAllTemplates""")
)
                      

// @LINE:36
def addDoctorsClinic(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addDoctorsClinic(), HandlerDef(this, "controllers.Application", "addDoctorsClinic", Seq(), "POST", """""", _prefix + """addDoctorsClinic""")
)
                      

// @LINE:46
def getAllParents(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllParents(), HandlerDef(this, "controllers.Application", "getAllParents", Seq(), "GET", """""", _prefix + """getAllParents""")
)
                      

// @LINE:57
def getAllClinic(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllClinic(), HandlerDef(this, "controllers.Application", "getAllClinic", Seq(), "GET", """""", _prefix + """getAllClinics""")
)
                      

// @LINE:18
def removeDoctorsPatient(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.removeDoctorsPatient(), HandlerDef(this, "controllers.Application", "removeDoctorsPatient", Seq(), "POST", """""", _prefix + """removeDoctorsPatient""")
)
                      

// @LINE:34
def getDoctorAssistant(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getDoctorAssistant(), HandlerDef(this, "controllers.Application", "getDoctorAssistant", Seq(), "GET", """""", _prefix + """getDoctorAssistant""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:40
def addAssistant(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addAssistant(), HandlerDef(this, "controllers.Application", "addAssistant", Seq(), "POST", """""", _prefix + """addAssistant""")
)
                      

// @LINE:22
def addClinic(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addClinic(), HandlerDef(this, "controllers.Application", "addClinic", Seq(), "POST", """""", _prefix + """addClinic""")
)
                      

// @LINE:52
def confirmOrDenyParentForDelegates(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.confirmOrDenyParentForDelegates(), HandlerDef(this, "controllers.Application", "confirmOrDenyParentForDelegates", Seq(), "POST", """""", _prefix + """confirmOrDenyParentForDelegates""")
)
                      

// @LINE:44
def getAllPatientDependents(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllPatientDependents(), HandlerDef(this, "controllers.Application", "getAllPatientDependents", Seq(), "GET", """""", _prefix + """getAllPatientDependents""")
)
                      

// @LINE:9
def getDoctorDetails(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getDoctorDetails(), HandlerDef(this, "controllers.Application", "getDoctorDetails", Seq(), "GET", """""", _prefix + """searchDoctors""")
)
                      

// @LINE:20
def getDoctorsPatients(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getDoctorsPatients(), HandlerDef(this, "controllers.Application", "getDoctorsPatients", Seq(), "GET", """""", _prefix + """getDoctorsPatients""")
)
                      

// @LINE:21
def getAllMembersForChat(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getAllMembersForChat(), HandlerDef(this, "controllers.Application", "getAllMembersForChat", Seq(), "GET", """""", _prefix + """getChatMember""")
)
                      

// @LINE:19
def getPatientsDoctors(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getPatientsDoctors(), HandlerDef(this, "controllers.Application", "getPatientsDoctors", Seq(), "GET", """""", _prefix + """getPatientsDoctors""")
)
                      

// @LINE:56
def getUploadedFilesForPatient(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getUploadedFilesForPatient(), HandlerDef(this, "controllers.Application", "getUploadedFilesForPatient", Seq(), "GET", """""", _prefix + """getUploadedFilesForPatient""")
)
                      

// @LINE:45
def removePatientDependent(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.removePatientDependent(), HandlerDef(this, "controllers.Application", "removePatientDependent", Seq(), "POST", """""", _prefix + """removePatientDependent""")
)
                      

// @LINE:10
def getPatientDetails(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getPatientDetails(), HandlerDef(this, "controllers.Application", "getPatientDetails", Seq(), "GET", """""", _prefix + """searchPatients""")
)
                      

// @LINE:27
def getClinicDetails(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getClinicDetails(), HandlerDef(this, "controllers.Application", "getClinicDetails", Seq(), "GET", """""", _prefix + """searchClinic""")
)
                      

// @LINE:55
def getUploadedFiles(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getUploadedFiles(), HandlerDef(this, "controllers.Application", "getUploadedFiles", Seq(), "GET", """""", _prefix + """getUploadedFiles""")
)
                      

// @LINE:53
def removeDelegatesForParent(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.removeDelegatesForParent(), HandlerDef(this, "controllers.Application", "removeDelegatesForParent", Seq(), "POST", """""", _prefix + """removeDelegatesForParent""")
)
                      

// @LINE:25
def addField(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addField(), HandlerDef(this, "controllers.Application", "addField", Seq(), "POST", """""", _prefix + """addField""")
)
                      

// @LINE:15
def patientDoctor(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.patientDoctor(), HandlerDef(this, "controllers.Application", "patientDoctor", Seq(), "POST", """""", _prefix + """patientDoctor""")
)
                      
    
}
                          

// @LINE:96
class ReverseAssets {
    

// @LINE:96
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          
}
                  
      