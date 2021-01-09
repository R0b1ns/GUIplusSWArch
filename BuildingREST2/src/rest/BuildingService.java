package rest;

import de.hsalbsig.inf.dea.model.*;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Collection;

@Path("app")
@Consumes({ "application/json" })
@Produces({ "application/json" })
// @Stateless // geht auch ...
@RequestScoped
public class BuildingService implements java.io.Serializable {
	private static final long serialVersionUID = 1545633471206295064L;

	private static final String EJBNAME = "java:global/BuildingEJB2/Infrastructure!de.hsalbsig.inf.dea.model.InfrastructureRemote";

	@EJB(mappedName = EJBNAME)
	private InfrastructureRemote infrastructureRemote;

	//
	// Bemerkung:
	// Falls es zu Problemen mit der Injektion geben sollte hülfe ggfs.
	// nachfolgende explizite Vereinbarung:
	//
//	private InitialContext ctx;
//
//	// statt @EJB-Injektion
//	{
//		try {
//			ctx = new InitialContext();
//			infrastructureRemote = (InfrastructureRemote) ctx.lookup(EJBNAME);
//		} catch (NamingException e) {
//			System.out.println("Naming Exception nach lookup");
//			e.printStackTrace();
//		}
//	}

	// funktioniert
	@GET
	@Path("/buildings")
	public Collection<Building> getAllBuildings() {
		System.out.println("\n\n---------------------------------");
		System.out.println("BuildingRestProject :: GET getAllBuildings()");
		System.out.println("\n\n---------------------------------");

		return infrastructureRemote.getAllBuildings();
	}

	// funktioniert
	@GET
	@Path("/buildings/{id}")
	public Building getBuilding(@PathParam("id") int id) throws NoSuchRowException {
		System.out.println("\n\n---------------------------------");
		System.out.println("BuildingRestProject :: GET getBuilding id = " + id);
		System.out.println("\n\n---------------------------------");
		return infrastructureRemote.getBuilding(id);
	}

	@GET
	@Path("/rooms")
	public Collection<Room> getAllRooms() {
		System.out.println("\n\n---------------------------------");
		System.out.println("BuildingRestProject :: GET getAllRooms()");
		System.out.println("\n\n---------------------------------");
		return infrastructureRemote.getAllRooms();
	}

	@GET
	@Path("/count")
	public long getCountRooms() {
		return infrastructureRemote.getCountRooms();
	}

	@GET
	@Path("/rooms/{id}")
	public Room getRoom(@PathParam("id") int id) throws NoSuchRowException {
		System.out.println("\n\n---------------------------------");
		System.out.println("BuildingRestProject :: getRoom id = " + id);
		System.out.println("\n\n---------------------------------");
		return infrastructureRemote.getRoom(id);
	}
	
	
	//eigener Code ######################################################
	
	
	//############################### GET  ##############################
	
	//getAll[in Table]
	@GET
	@Path("/patients")
	public Collection<Patient> getAllPatients(){
	  System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getAllPatients()");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getAllPatients();
	}
	
	
	@GET
    @Path("/schedule")
    public Collection<Schedule> getSchedule(){
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getSchedule()");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getSchedule();
    }
	
	@GET
    @Path("/positions")
    public Collection<Position> getAllPositions(){
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getAllPositions()");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getAllPositions();
    }
    
    @GET
    @Path("/persons")
    public Collection<Person> getAllPersons(){
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getAllPersons()");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getAllPersons();
    }
    
    @GET
    @Path("/treatments")
    public Collection<Treatment> getAllTreatments(){
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getAllTreatments()");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getAllTreatments();
    }
    
    @GET
    @Path("/healthinsurances")
    public Collection<HealthInsurance> getAllHealthInsurances(){
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getAllHealthInsurances()");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getAllHealthInsurances();
    }
    
    @GET
    @Path("/adresses")
    public Collection<Adress> getAllAdresses(){
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getAllAdresses()");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getAllAdresses();
    }
    
    @GET
    @Path("/employees")
    public Collection<Employee> getAllEmployees(){
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getAllEmployees()");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getAllEmployees();
    }
    
    @GET
    @Path("/prescriptions")
    public Collection<Prescription> getAllPrescriptions(){
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getAllPrescriptions()");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getAllPrescriptions();
    }
    
    @GET
    @Path("/diseases")
    public Collection<Disease> getAllDiseases(){
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getAllDiseases()");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getAllDiseases();
    }
    
    @GET
    @Path("/schedule/requests")
    public Collection<ScheduleRequest> getAllScheduleRequests(){
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getAllScheduleRequests()");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getAllScheduleRequests();
    }
    
    @GET
    @Path("/drugs")
    public Collection<Drug> getAllDrugs(){
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getAllDrugs()");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getAllDrugs();
    }
	
	//get[specific id in Table]
	@GET
	@Path("/schedule/patient/{id}")
	public Collection<Schedule> getScheduleOfPatient(@PathParam("id") String id){
	  System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getScheduleOfPatient("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getScheduleOfPatient(id);
	}
	
	@GET
    @Path("/schedule/employee/{id}")
    public Collection<Schedule> getScheduleOfEmployee(@PathParam("id") String id){
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getScheduleOfEmployee("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getScheduleOfEmployee(id);
    }
	
	@GET
	@Path("/employees/name")
	public long getEmployeeOfName(@QueryParam("firstName") String firstName,@QueryParam("lastName") String lastName) throws NoSuchRowException {
	  System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getEmployeeOfName("+firstName+" "+lastName+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getEmployeeOfName(firstName,lastName);
	}
	
	@GET
	@Path("/patients/{id}")
	public Patient getPatientInfo(@PathParam("id") long id) throws NoSuchRowException {
	  System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getPatientInfo("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getPatientInfo(id);
	}
	
	@GET
    @Path("/employees/{id}")
    public Employee getEmployeeInfo(@PathParam("id") long id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getEmployeeInfo("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getEmployeeInfo(id);
    }
	
	
	@GET
	@Path("/login/ID")//called like /login/ID?type=employee/Patient&firstname=...&lastname=...&number=...
	public long getLoginId(@QueryParam("type")String type,
	    @QueryParam("firstname")String firstName,
	    @QueryParam("lastname")String lastName,
	    @QueryParam("number")int number) {
	  System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getLoginId("+type+","+firstName+","+lastName+","+number+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getLoginId(type,firstName,lastName,number);
	}
	
	@GET
    @Path("/login")//called like /login?type=employee/Patient&firstname=...&lastname=...&number=...
    public String Login(@QueryParam("type")String type,
        @QueryParam("firstname")String firstName,
        @QueryParam("lastname")String lastName,
        @QueryParam("number")int number) {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET login("+type+","+firstName+","+lastName+","+number+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.login(type,firstName,lastName,number);
    }
	
	
	@GET
	@Path("/prescriptions/{id}")
	public Collection<Prescription> getPrescriptionOf(@PathParam("id")String id){
	  System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getPrescriptionOf("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getPrescriptionOf(id);
	}
	
	@GET
	@Path("/schedule/requests/")
	public Collection<ScheduleRequest> getScheduleRequests(){
	  System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getScheduleRequests()");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getScheduleRequests();
	}
	
    //defaults
    @GET
    @Path("/positions/{id}")
    public Position getPosition(@PathParam("id") long id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getPosition("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getPosition(id);
    }
    
    @GET
    @Path("/persons/{id}")
    public Person getPerson(@PathParam("id") long id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getPerson("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getPerson(id);
    }
    
    @GET
    @Path("/treatments/{id}")
    public Treatment getTreatment(@PathParam("id") long id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getTreatment("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getTreatment(id);
    }
    
    @GET
    @Path("/drugs/{id}")
    public Drug getDrug(@PathParam("id") long id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getDrug("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getDrug(id);
    }
    
    @GET
    @Path("/healthinsurances/{id}")
    public HealthInsurance getHealthInsurance(@PathParam("id") long id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getHealthInsurance("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getHealthInsurance(id);
    }
    
    @GET
    @Path("/adresses/{id}")
    public Adress getAdress(@PathParam("id") long id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getAdress("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getAdress(id);
    }
    
    @GET
    @Path("/schedule/{id}")
    public Schedule getSchedule(@PathParam("id") long id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getSchedule("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getSchedule(id);
    }
    
    @GET
    @Path("/diseases/{id}")
    public Disease getDisease(@PathParam("id") long id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getDisease("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getDisease(id);
    }
    
    @GET
    @Path("/schedule/requests/{id}")
    public ScheduleRequest getScheduleRequest(@PathParam("id") long id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: GET getScheduleRequest("+id+")");
      System.out.println("\n\n---------------------------------");
      return infrastructureRemote.getScheduleRequest(id);
    }
    
    
	
	
	//############################### POST ##############################
	
	@POST
	@Path("/schedule/add")
	public Response addSchedule(Schedule schedule) {
	  System.out.println("\n\n---------------------------------");
      System.out.println("App :: POST addSchedule with id = " + schedule.getId());
      System.out.println("App :: POST addSchedule with employee_id = " + schedule.getEmployeeId());
      System.out.println("App :: POST addSchedule with patient_id = " + schedule.getPatientId());
      System.out.println("App :: POST addSchedule with treatment_id = " + schedule.getTreatmentId());
      System.out.println("App :: POST addSchedule with date = " + schedule.getDate());
      System.out.println("\n\n---------------------------------");
      
      boolean success = false;
      try {
          infrastructureRemote.addSchedule(schedule);
          success = true;
      } catch (Exception e) {
          success = false;
      }
      System.out.println("App :: POST addSchedule success = " + success);
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
	  
	}
	
	@POST
	@Path("/schedule/add/{id}")
	public Response addScheduleWithRemove(Schedule schedule, @PathParam("id")int id) {
	  Response r_add=addSchedule(schedule);
	  Response r_delete;
	  if (r_add.getStatusInfo()==Response.Status.OK) {
	    try {
	      r_delete=removeScheduleRequest(id);
        } catch (Exception e) {
          r_delete=Response.serverError().build();
        }
	  }else {
        r_delete=r_add;
      }
	  
	  return r_delete;
	}
	
	
	@POST
	@Path("/prescriptions")
	public Response addPrescription(Prescription prescription) {
	  System.out.println("\n\n---------------------------------");
      System.out.println("App :: POST addPrescription with id = " + prescription.getId());
      System.out.println("App :: POST addPrescription with disease_id = " + prescription.getDiseaseId());
      System.out.println("App :: POST addPrescription with drug_id = " + prescription.getDrugId());
      System.out.println("App :: POST addPrescription with employee_id = " + prescription.getEmployeeId());
      System.out.println("App :: POST addPrescription with patient_id = " +prescription.getPatientId());
      System.out.println("App :: POST addPrescription with date = " +prescription.getPrescriptionDate());
      System.out.println("\n\n---------------------------------");	
      
      boolean success = false;
      try {
          infrastructureRemote.addPrescription(prescription);
          success = true;
      } catch (Exception e) {
          success = false;
      }
      System.out.println("App :: POST addPrescription success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
	}
	
	//defaults
	@POST
    @Path("/positions")
    public Response addPosition(Position position) {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: POST addPosition with id = " + position.getId());
      System.out.println("App :: POST addPosition with discription = " + position.getDescription());
      System.out.println("\n\n---------------------------------");  
      
      boolean success = false;
      try {
          infrastructureRemote.addPosition(position);
          success = true;
      } catch (Exception e) {
          success = false;
      }
      System.out.println("App :: POST addPosition success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
	
	@POST
    @Path("/persons")
    public Response addPerson(Person person) {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: POST addPerson with id = " + person.getId());
      System.out.println("App :: POST addPerson with adress_id = " + person.getAdress());
      System.out.println("App :: POST addPerson with first name = " + person.getFirstName());
      System.out.println("App :: POST addPerson with gender = " + person.getGender());
      System.out.println("App :: POST addPerson with last name = " +person.getLastName());
      System.out.println("App :: POST addPerson with birthday = " +person.getBirthday());
      System.out.println("\n\n---------------------------------");  
      
      boolean success = false;
      try {
          infrastructureRemote.addPerson(person);
          success = true;
      } catch (Exception e) {
          success = false;
      }
      System.out.println("App :: POST addPerson success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @POST
    @Path("/treatments")
    public Response addTreatment(Treatment treatment) {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: POST addTreatment with id = " + treatment.getId());
      System.out.println("App :: POST addTreatment with discription = " + treatment.getDescription());
      System.out.println("\n\n---------------------------------");  
      
      boolean success = false;
      try {
          infrastructureRemote.addTreatment(treatment);
          success = true;
      } catch (Exception e) {
          success = false;
      }
      System.out.println("App :: POST addTreatment success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @POST
    @Path("/drugs")
    public Response addDrug(Drug drug) {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: POST addDrug with id = " + drug.getId());
      System.out.println("App :: POST addDrug with discription = " + drug.getDescription());
      System.out.println("\n\n---------------------------------");  
      
      boolean success = false;
      try {
          infrastructureRemote.addDrug(drug);
          success = true;
      } catch (Exception e) {
          success = false;
      }
      System.out.println("App :: POST addDrug success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @POST
    @Path("/healthinsurances")
    public Response addHealthInsurance(HealthInsurance healthInsurance) {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: POST addHealthInsurance with id = " + healthInsurance.getId());
      System.out.println("App :: POST addHealthInsurance with adress_id = " + healthInsurance.getAdress());
      System.out.println("App :: POST addhealthInsurance with name = " + healthInsurance.getName());
      System.out.println("\n\n---------------------------------");  
      
      boolean success = false;
      try {
          infrastructureRemote.addHealthInsurance(healthInsurance);
          success = true;
      } catch (Exception e) {
          success = false;
      }
      System.out.println("App :: POST addHealthInsurance success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @POST
    @Path("/adresses")
    public Response addAdress(Adress adress) {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: POST addAdress with id = " + adress.getId());
      System.out.println("App :: POST addAdress with city = " + adress.getCity());
      System.out.println("App :: POST addAdress with number = " + adress.getNumber());
      System.out.println("App :: POST addAdress with plz = " + adress.getPLZ());
      System.out.println("App :: POST addAdress with street = " +adress.getStreet());
      System.out.println("\n\n---------------------------------");  
      
      boolean success = false;
      try {
          infrastructureRemote.addAdress(adress);
          success = true;
      } catch (Exception e) {
          success = false;
      }
      System.out.println("App :: POST addAdress success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @POST
    @Path("/employees")
    public Response addEmployee(Employee employee) {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: POST addEmployee with id = " + employee.getId());
      System.out.println("App :: POST addEmployee with person_id = " + employee.getPersonId());
      System.out.println("App :: POST addEmployee with personnal number = " + employee.getPersonnalnumber());
      System.out.println("App :: POST addEmployee with position_id = " + employee.getPosition());
      System.out.println("\n\n---------------------------------");  
      
      boolean success = false;
      try {
          infrastructureRemote.addEmployee(employee);
          success = true;
      } catch (Exception e) {
          success = false;
      }
      System.out.println("App :: POST addEmployee success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @POST
    @Path("/patients")
    public Response addPatient(Patient patient) {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: POST addPatient with id = " + patient.getId());
      System.out.println("App :: POST addPatient with health insurance = " + patient.getHealthInsurance());
      System.out.println("App :: POST addPatient with person_id = " + patient.getPerson());
      System.out.println("App :: POST addPatient with ssn = " + patient.getSSN());
      System.out.println("\n\n---------------------------------");  
      
      boolean success = false;
      try {
          infrastructureRemote.addPatient(patient);
          success = true;
      } catch (Exception e) {
          success = false;
      }
      System.out.println("App :: POST addPatient success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @POST
    @Path("/diseases")
    public Response addDisease(Disease disease) {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: POST addDisease with id = " + disease.getId());
      System.out.println("App :: POST addDisease with description = " + disease.getDescription());
      System.out.println("\n\n---------------------------------");  
      
      boolean success = false;
      try {
          infrastructureRemote.addDisease(disease);
          success = true;
      } catch (Exception e) {
          success = false;
      }
      System.out.println("App :: POST addDisease success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @POST
    @Path("/schedule/requests")
    public Response addScheduleRequest(ScheduleRequest scheduleRequest) {
      System.out.println("\n\n---------------------------------");
      System.out.println("App :: POST addScheduleRequest with id = " + scheduleRequest.getId());
      System.out.println("App :: POST addScheduleRequest with employee_id = " + scheduleRequest.getEmployeeId());
      System.out.println("App :: POST addScheduleRequest with note = " + scheduleRequest.getNote());
      System.out.println("App :: POST addScheduleRequest with patient_id = " + scheduleRequest.getPatientId());
      System.out.println("App :: POST addScheduleRequest with priority = " +scheduleRequest.getPriority());
      System.out.println("\n\n---------------------------------");  
      
      boolean success = false;
      try {
          infrastructureRemote.addScheduleRequest(scheduleRequest);
          success = true;
      } catch (Exception e) {
          success = false;
      }
      System.out.println("\n\n--------------------lel-------------");
      System.out.println("App :: POST addScheduleRequest with id = " + scheduleRequest.getId());
      System.out.println("App :: POST addScheduleRequest with employee_id = " + scheduleRequest.getEmployeeId());
      System.out.println("App :: POST addScheduleRequest with note = " + scheduleRequest.getNote());
      System.out.println("App :: POST addScheduleRequest with patient_id = " + scheduleRequest.getPatientId());
      System.out.println("App :: POST addScheduleRequest with priority = " +scheduleRequest.getPriority());
      System.out.println("\n\n--------------------lel-------------");  
      
      System.out.println("App :: POST addScheduleRequest success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
	
	//############################### DELETE ############################
	
	@DELETE
	@Path("/schedule/requests/{id}")
	public Response removeScheduleRequest(@PathParam("id") int id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App:: DELETE removeScheduleRequest with id = " + id);
      System.out.println("\n\n---------------------------------");
      boolean success = false;
      try {
          success = true;
          infrastructureRemote.removeScheduleRequest(id);
      } catch (NoSuchRowException e) {
          throw new NoSuchRowException();
      }
      System.out.println("App :: DELETE removeScheduleRequest success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
	
	//defaults
	@DELETE
    @Path("/positions/{id}")
    public Response removePosition(@PathParam("id") int id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App:: DELETE removePosition with id = " + id);
      System.out.println("\n\n---------------------------------");
      boolean success = false;
      try {
          success = true;
          infrastructureRemote.removePosition(id);
      } catch (NoSuchRowException e) {
          throw new NoSuchRowException();
      }
      System.out.println("App :: DELETE removePosition success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
	
	@DELETE
    @Path("/persons/{id}")
    public Response removePerson(@PathParam("id") int id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App:: DELETE removePerson with id = " + id);
      System.out.println("\n\n---------------------------------");
      boolean success = false;
      try {
          success = true;
          infrastructureRemote.removePerson(id);
      } catch (NoSuchRowException e) {
          throw new NoSuchRowException();
      }
      System.out.println("App :: DELETE removePerson success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @DELETE
    @Path("/treatments/{id}")
    public Response removeTreatment(@PathParam("id") int id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App:: DELETE removeTreatment with id = " + id);
      System.out.println("\n\n---------------------------------");
      boolean success = false;
      try {
          success = true;
          infrastructureRemote.removeTreatment(id);
      } catch (NoSuchRowException e) {
          throw new NoSuchRowException();
      }
      System.out.println("App :: DELETE removeTreatment success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @DELETE
    @Path("/drugs/{id}")
    public Response removeDrug(@PathParam("id") int id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App:: DELETE removeDrug with id = " + id);
      System.out.println("\n\n---------------------------------");
      boolean success = false;
      try {
          success = true;
          infrastructureRemote.removeDrug(id);
      } catch (NoSuchRowException e) {
          throw new NoSuchRowException();
      }
      System.out.println("App :: DELETE removeDrug success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @DELETE
    @Path("/healthinsurances/{id}")
    public Response removeHealthInsurance(@PathParam("id") int id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App:: DELETE removeHealthInsurance with id = " + id);
      System.out.println("\n\n---------------------------------");
      boolean success = false;
      try {
          success = true;
          infrastructureRemote.removeHealthInsurance(id);
      } catch (NoSuchRowException e) {
          throw new NoSuchRowException();
      }
      System.out.println("App :: DELETE removeHealthInsurance success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @DELETE
    @Path("/adresses/{id}")
    public Response removeAdress(@PathParam("id") int id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App:: DELETE removeAdress with id = " + id);
      System.out.println("\n\n---------------------------------");
      boolean success = false;
      try {
          success = true;
          infrastructureRemote.removeAdress(id);
      } catch (NoSuchRowException e) {
          throw new NoSuchRowException();
      }
      System.out.println("App :: DELETE removeAdress success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @DELETE
    @Path("/employees/{id}")
    public Response removeEmployee(@PathParam("id") int id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App:: DELETE removeEmployee with id = " + id);
      System.out.println("\n\n---------------------------------");
      boolean success = false;
      try {
          success = true;
          infrastructureRemote.removeEmployee(id);
      } catch (NoSuchRowException e) {
          throw new NoSuchRowException();
      }
      System.out.println("App :: DELETE removeEmployee success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @DELETE
    @Path("/patients/{id}")
    public Response removePatient(@PathParam("id") int id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App:: DELETE removePatient with id = " + id);
      System.out.println("\n\n---------------------------------");
      boolean success = false;
      try {
          success = true;
          infrastructureRemote.removePatient(id);
      } catch (NoSuchRowException e) {
          throw new NoSuchRowException();
      }
      System.out.println("App :: DELETE removePatient success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @DELETE
    @Path("/schedule/{id}")
    public Response removeSchedule(@PathParam("id") int id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App:: DELETE removeSchedule with id = " + id);
      System.out.println("\n\n---------------------------------");
      boolean success = false;
      try {
          success = true;
          infrastructureRemote.removeSchedule(id);
      } catch (NoSuchRowException e) {
          throw new NoSuchRowException();
      }
      System.out.println("App :: DELETE removeSchedule success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @DELETE
    @Path("/prescriptions/{id}")
    public Response removePrescription(@PathParam("id") int id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App:: DELETE removePrescription with id = " + id);
      System.out.println("\n\n---------------------------------");
      boolean success = false;
      try {
          success = true;
          infrastructureRemote.removePrescription(id);
      } catch (NoSuchRowException e) {
          throw new NoSuchRowException();
      }
      System.out.println("App :: DELETE removePrescription success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
    
    @DELETE
    @Path("/diseases/{id}")
    public Response removeDisease(@PathParam("id") int id) throws NoSuchRowException {
      System.out.println("\n\n---------------------------------");
      System.out.println("App:: DELETE removeDisease with id = " + id);
      System.out.println("\n\n---------------------------------");
      boolean success = false;
      try {
          success = true;
          infrastructureRemote.removeDisease(id);
      } catch (NoSuchRowException e) {
          throw new NoSuchRowException();
      }
      System.out.println("App :: DELETE removeDisease success = " + success);
      System.out.println("\n\n---------------------------------");
      StatusMessage msg = RestApplication.getReturnMessage(success);
      return Response.ok(msg).build();
    }
	
	//eigener Code Ende #################################################
	
	
//	@GET
//	@Path("/buildings/create")
//	public Response createBuilding() {

//	@POST
//	@Path("/buildings")

//	@GET
//	@Path("/buildings/create")
//	public Response createBuilding(@QueryParam("buildingid") int buildingid, @QueryParam("number") String number,
//			@QueryParam("street") String street) {

	@POST
	@Path("/buildings")
	public Response createBuilding(Building building) {
	
		System.out.println("\n\n---------------------------------");
		System.out.println("---------------------------------");
		System.out.println("BuildingRestProject:: POST createBuilding buildingid = " + building.getId());
		System.out.println("BuildingRestProject:: POST createBuilding number = " + building.getNumber());
		System.out.println("BuildingRestProject:: POST createBuilding street = " + building.getStreet());

		boolean success = false;
		try {
			infrastructureRemote.save(building);
			success = true;
		} catch (Exception e) {
			success = false;
		}
		System.out.println("BuildingRestProject:: POST createBuilding success = " + success);
		StatusMessage msg = RestApplication.getReturnMessage(success);
		return Response.ok(msg).build();
	}

	@DELETE
	@Path("/buildings/{id}")
	public Response remove(@PathParam("id") int id) throws NoSuchRowException {
		System.out.println("\n\n---------------------------------");
		System.out.println("BuildingService:: remove id = " + id);
		System.out.println("\n\n---------------------------------");
		boolean success = false;
		try {
			success = true;
			infrastructureRemote.removeBuilding(id);
		} catch (NoSuchRowException e) {
			throw new NoSuchRowException();
		}
		StatusMessage msg = RestApplication.getReturnMessage(success);
		return Response.ok(msg).build();
	}
}
