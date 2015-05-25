package viewmodel;


public class AlarmReminderVM {
	public Long id;
	public String alarmDate;
	public String time1;
	public String time2;
	public String time3;
	public String time4;
	public String time5;
	public String time6;
	
	public AlarmReminderVM(){
		
	}
	
	public AlarmReminderVM(long id, String alarmDate, String time1,
			String time2, String time3, String time4, String time5,
			String time6) {
		
		this.id = id;
		this.alarmDate = alarmDate;
		this.time1 = time1;
		this.time2 = time2;
		this.time3 = time3;
		this.time4 = time4;
		this.time5 = time5;
		this.time6 = time6;
		// TODO Auto-generated constructor stub
	}
}
