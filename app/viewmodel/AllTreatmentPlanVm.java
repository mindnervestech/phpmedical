package viewmodel;

import java.util.List;


public class AllTreatmentPlanVm {
	public String id;
	public Integer doctorId;
	public Integer patientId;
	public String patientAppointmentDate;
	public String patientAppointmentTime;

	public List<AllProcedureVm> procedure;
	
}
