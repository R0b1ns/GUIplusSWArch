package de.hsalbsig.inf.dea.model;

import java.sql.Date;
import java.util.Collection;

import javax.ejb.Remote;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Remote
public interface InfrastructureRemote {
	public Collection<Building> getAllBuildings();

	public Collection<Room> getAllRooms();

	public long getCountRooms();

	public Room getRoom(long id) throws NoSuchRowException;

	public Building getBuilding(long id)throws NoSuchRowException;

	public void save(Building building);

	public void removeBuilding(Building building) throws NoSuchRowException;

	public void removeBuilding(long primaryKey) throws NoSuchRowException;
	
	//eigener Code:
	
	//GET
	
	//getAll[in Table]
	public Collection<Patient> getAllPatients();
	
	public Collection<Schedule> getSchedule();
	
	public Collection<Person> getAllPersons();
	
	public Collection<Position> getAllPositions();
    
    public Collection<Treatment> getAllTreatments();
    
    public Collection<Drug> getAllDrugs();
    
    public Collection<HealthInsurance> getAllHealthInsurances();
    
    public Collection<Adress> getAllAdresses();
    
    public Collection<Employee> getAllEmployees();
    
    public Collection<Prescription> getAllPrescriptions();
    
    public Collection<Disease> getAllDiseases();
    
    public Collection<ScheduleRequest> getAllScheduleRequests();
	
	//get[specific id in Table]
	public Collection<Schedule> getScheduleOfPatient(String id);   
	
	public Collection<Schedule> getScheduleOfEmployee(String id); 
	
	public Patient getPatientInfo(long id) throws NoSuchRowException;
	
	public Employee getEmployeeInfo(long id) throws NoSuchRowException;
	
	public long getEmployeeOfName(String firstName, String lastName) throws NoSuchRowException;
	
	public long getLoginId(String type, String firstName, String lastName, int number);
	
	public String login(String type, String firstName, String lastName, int number);
	
	public Collection<Prescription> getPrescriptionOf(String id);
	
	public Collection<Prescription> getPrescriptionFrom(String id);

    public Collection<ScheduleRequest> getScheduleRequests();
 
    public Position getPosition(long id) throws NoSuchRowException; 
    
    public Person getPerson(long id) throws NoSuchRowException; 
    
    public Treatment getTreatment(long id) throws NoSuchRowException; 
    
    public Drug getDrug(long id) throws NoSuchRowException; 
    
    public HealthInsurance getHealthInsurance(long id) throws NoSuchRowException; 
    
    public Adress getAdress(long id) throws NoSuchRowException; 
    
    public Schedule getSchedule(long id) throws NoSuchRowException; //not implemented?
    
    public Prescription getPrescription(long id) throws NoSuchRowException; 
    
    public Disease getDisease(long id) throws NoSuchRowException; 
    
    public ScheduleRequest getScheduleRequest(long id) throws NoSuchRowException;
    
    //POST
    public void addSchedule(Schedule schedule);//=appointment
    
    public void addPrescription(Prescription prescription);
    
    public void addPosition(Position position);
    
    public void addPerson(Person person);
    
    public void addTreatment(Treatment treatment);
    
    public void addDrug(Drug drug);
    
    public void addHealthInsurance(HealthInsurance healthInsurance);
    
    public void addAdress(Adress adress);
    
    public void addEmployee(Employee employee);
    
    public void addPatient(Patient patient);
    
    public void addDisease(Disease disease);
    
    public void addScheduleRequest(ScheduleRequest scheduleRequest);
    
    //DELETE
    public void removeScheduleRequest(long primaryKey) throws NoSuchRowException;
    
    public void removePosition(long primaryKey) throws NoSuchRowException;
    
    public void removePerson(long primaryKey) throws NoSuchRowException;
    
    public void removeTreatment(long primaryKey) throws NoSuchRowException;
    
    public void removeDrug(long primaryKey) throws NoSuchRowException;
    
    public void removeHealthInsurance(long primaryKey) throws NoSuchRowException;
    
    public void removeAdress(long primaryKey) throws NoSuchRowException;
    
    public void removeEmployee(long primaryKey) throws NoSuchRowException;
    
    public void removePatient(long primaryKey) throws NoSuchRowException;
    
    public void removeSchedule(long primaryKey) throws NoSuchRowException;
    
    public void removePrescription(long primaryKey) throws NoSuchRowException;
    
    public void removeDisease(long primaryKey) throws NoSuchRowException;
	
}
