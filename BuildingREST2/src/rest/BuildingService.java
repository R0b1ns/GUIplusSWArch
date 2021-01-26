package rest;

import de.hsalbsig.inf.dea.model.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;

@Path("app")
@Consumes({"application/json"})
@Produces({"application/json"})
// @Stateless // geht auch ...
@RequestScoped
public class BuildingService implements Serializable {
    private static final long serialVersionUID = 1545633471206295064L;

    private static final String EJBNAME = "java:global/BuildingEJB2/Infrastructure!de.hsalbsig.inf.dea.model.InfrastructureRemote";

    @EJB(mappedName = EJBNAME)
    private InfrastructureRemote infrastructureRemote;

    /*
     * Bemerkung:
     * Falls es zu Problemen mit der Injektion geben sollte hülfe ggfs.
     * nachfolgende explizite Vereinbarung:
     */
    /*
	private InitialContext ctx;
	// statt @EJB-Injektion
	{
		try {
			ctx = new InitialContext();
			infrastructureRemote = (InfrastructureRemote) ctx.lookup(EJBNAME);
		} catch (NamingException e) {
			System.out.println("Naming Exception nach lookup");
			e.printStackTrace();
		}
	}
     */
    
    /*
     * Only use on NOT overloaded Methods!
     */
    private String getRouteInfo(String methodName) {
    	String path = "undefined";
    	String mode = "undefined";
    	String servicename = this.getClass().getAnnotation(Path.class).value();
    	
		try {
			
			Method methods[] = this.getClass().getMethods();
			Method m = null;

			//Can be wrong if you have overloaded Methods
			for(Method method : methods) {
				if(method.getName().equals(methodName)) {
					m = method;
					break;
				}
			}
			
			path = m.getAnnotation(Path.class).value();

			if(null != m.getAnnotation(GET.class)) {
				mode = "GET";
			}
			if(null != m.getAnnotation(POST.class)) {
				mode = "POST";
			}
			if(null != m.getAnnotation(DELETE.class)) {
				mode = "DELETE";
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		return mode + " /" + servicename + path;
    }

    private void printRequestInfo(String paramString) {
    	StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
    	StackTraceElement eTrace = stacktrace[2];
    	String methodName = eTrace.getMethodName();

        System.out.println("---------------------------------");
        System.out.println("Request :: " + getRouteInfo(methodName) + " > "+methodName+"("+paramString+")");
        System.out.println("---------------------------------");
    }
    
    private void printRequestResult(boolean result) {
    	StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
    	StackTraceElement eTrace = stacktrace[2];
    	String methodName = eTrace.getMethodName();
    	
    	String resultText = result ? "SUCCESS" : "FAILED";
    	
        System.out.println("------------RESULT---------------");
        System.out.println(resultText + " - Request :: " + getRouteInfo(methodName) + " > "+methodName);
        System.out.println("---------------------------------");
    }

    //############################### GET  ##############################

    //getAll[in Table]
    @GET
    @Path("/patients")
    public Collection<Patient> getAllPatients() {
        printRequestInfo("");
        return infrastructureRemote.getAllPatients();
    }

    @GET
    @Path("/schedule")
    public Collection<Schedule> getSchedule() {
        printRequestInfo("");
        return infrastructureRemote.getSchedule();
    }

    @GET
    @Path("/positions")
    public Collection<Position> getAllPositions() {
        printRequestInfo("");
        return infrastructureRemote.getAllPositions();
    }

    @GET
    @Path("/persons")
    public Collection<Person> getAllPersons() {
        printRequestInfo("");
        return infrastructureRemote.getAllPersons();
    }

    @GET
    @Path("/treatments")
    public Collection<Treatment> getAllTreatments() {
        printRequestInfo("");
        return infrastructureRemote.getAllTreatments();
    }

    @GET
    @Path("/healthinsurances")
    public Collection<HealthInsurance> getAllHealthInsurances() {
        printRequestInfo("");
        return infrastructureRemote.getAllHealthInsurances();
    }

    @GET
    @Path("/adresses")
    public Collection<Adress> getAllAdresses() {
        printRequestInfo("");
        return infrastructureRemote.getAllAdresses();
    }

    @GET
    @Path("/employees")
    public Collection<Employee> getAllEmployees() {
        printRequestInfo("");
        return infrastructureRemote.getAllEmployees();
    }

    @GET
    @Path("/prescriptions")
    public Collection<Prescription> getAllPrescriptions() {
        printRequestInfo("");
        return infrastructureRemote.getAllPrescriptions();
    }

    @GET
    @Path("/diseases")
    public Collection<Disease> getAllDiseases() {
        printRequestInfo("");
        return infrastructureRemote.getAllDiseases();
    }

    @GET
    @Path("/schedule/requests")
    public Collection<ScheduleRequest> getAllScheduleRequests() {
        printRequestInfo("");
        return infrastructureRemote.getAllScheduleRequests();
    }

    @GET
    @Path("/drugs")
    public Collection<Drug> getAllDrugs() {
        printRequestInfo("");
        return infrastructureRemote.getAllDrugs();
    }

    //get[specific id in Table]
    @GET
    @Path("/schedule/patient/{id}")
    public Collection<Schedule> getScheduleOfPatient(@PathParam("id") String id) {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getScheduleOfPatient(id);
    }

    @GET
    @Path("/schedule/employee/{id}")
    public Collection<Schedule> getScheduleOfEmployee(@PathParam("id") String id) {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getScheduleOfEmployee(id);
    }

    @GET
    @Path("/employees/name")
    public long getEmployeeOfName(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName) throws NoSuchRowException {
        printRequestInfo("firstName = " + firstName + ", lastName = " + lastName);
        return infrastructureRemote.getEmployeeOfName(firstName, lastName);
    }

    @GET
    @Path("/patients/name")
    public long getPatientOfName(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName) throws NoSuchRowException {
        printRequestInfo("firstName = " + firstName + ", lastName = " + lastName);
        return infrastructureRemote.getPatientOfName(firstName, lastName);
    }

    @GET
    @Path("/patients/{id}")
    public Patient getPatientInfo(@PathParam("id") long id) throws NoSuchRowException {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getPatientInfo(id);
    }

    @GET
    @Path("/employees/{id}")
    public Employee getEmployeeInfo(@PathParam("id") long id) throws NoSuchRowException {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getEmployeeInfo(id);
    }

    @GET
    @Path("/login/ID")
    //called like /login/ID?type=employee/Patient&firstname=...&lastname=...&number=...
    public long getLoginId(@QueryParam("type") String type,
                           @QueryParam("firstname") String firstName,
                           @QueryParam("lastname") String lastName,
                           @QueryParam("number") int number) {
        printRequestInfo("type = " + type + ", firstName = " + firstName + ", lastName = " + lastName + ", number = " + number);

        return infrastructureRemote.getLoginId(type, firstName, lastName, number);
    }

    @GET
    @Path("/login") //called like /login?type=employee/Patient&firstname=...&lastname=...&number=...
    public String Login(@QueryParam("type") String type,
                        @QueryParam("firstname") String firstName,
                        @QueryParam("lastname") String lastName,
                        @QueryParam("number") int number) {
        printRequestInfo("type = " + type + ", firstName = " + firstName + ", lastName = " + lastName + ", number = " + number);

        return infrastructureRemote.login(type, firstName, lastName, number);
    }

    @GET
    @Path("/prescriptions/{id}")
    public Collection<Prescription> getPrescriptionOf(@PathParam("id") String id) {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getPrescriptionOf(id);
    }

    @GET
    @Path("/prescriptions/id/{id}")
    public Prescription getPrescription(@PathParam("id") long id) throws NoSuchRowException {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getPrescription(id);
    }

    @GET
    @Path("/prescriptions/employee/{id}")
    public Collection<Prescription> getPrescriptionFrom(@PathParam("id") String id) {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getPrescriptionFrom(id);
    }

    @GET
    @Path("/schedule/requests/")
    public Collection<ScheduleRequest> getScheduleRequests() {
        printRequestInfo("");
        return infrastructureRemote.getScheduleRequests();
    }

    //defaults
    @GET
    @Path("/positions/{id}")
    public Position getPosition(@PathParam("id") long id) throws NoSuchRowException {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getPosition(id);
    }

    @GET
    @Path("/persons/{id}")
    public Person getPerson(@PathParam("id") long id) throws NoSuchRowException {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getPerson(id);
    }

    @GET
    @Path("/treatments/{id}")
    public Treatment getTreatment(@PathParam("id") long id) throws NoSuchRowException {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getTreatment(id);
    }

    @GET
    @Path("/drugs/{id}")
    public Drug getDrug(@PathParam("id") long id) throws NoSuchRowException {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getDrug(id);
    }

    @GET
    @Path("/healthinsurances/{id}")
    public HealthInsurance getHealthInsurance(@PathParam("id") long id) throws NoSuchRowException {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getHealthInsurance(id);
    }

    @GET
    @Path("/adresses/{id}")
    public Adress getAdress(@PathParam("id") long id) throws NoSuchRowException {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getAdress(id);
    }

    @GET
    @Path("/schedule/{id}")
    public Schedule getSchedule(@PathParam("id") long id) throws NoSuchRowException {
        printRequestInfo("id = " + id);
        return infrastructureRemote.getSchedule(id);
    }

    @GET
    @Path("/diseases/{id}")
    public Disease getDisease(@PathParam("id") long id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);
        return infrastructureRemote.getDisease(id);
    }

    @GET
    @Path("/schedule/requests/{id}")
    public ScheduleRequest getScheduleRequest(@PathParam("id") long id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);
        return infrastructureRemote.getScheduleRequest(id);
    }
    
    //############################### POST ##############################

    @POST
    @Path("/schedule/add")
    public Response addSchedule(Schedule schedule) {
    	printRequestInfo("schedule with: " + schedule.toString());

        boolean success = false;
        try {
            infrastructureRemote.addSchedule(schedule);
            success = true;
        } catch (Exception e) {
            success = false;
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @POST
    @Path("/schedule/add/{id}")
    public Response addScheduleWithRemove(Schedule schedule, @PathParam("id") int id) {
    	printRequestInfo("schedule with: " + schedule.toString() + ", id = " + id);
    	
        Response r_add = addSchedule(schedule);
        Response r_delete;
        if (r_add.getStatusInfo() == Response.Status.OK) {
            try {
                r_delete = removeScheduleRequest(id);
            } catch (Exception e) {
                r_delete = Response.serverError().build();
            }
        } else {
            r_delete = r_add;
        }

        return r_delete;
    }

    @POST
    @Path("/prescriptions")
    public Response addPrescription(Prescription prescription) {
    	printRequestInfo("prescription with: " + prescription.toString());

        boolean success = false;
        try {
            infrastructureRemote.addPrescription(prescription);
            success = true;
        } catch (Exception e) {
            success = false;
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    //defaults
    @POST
    @Path("/positions")
    public Response addPosition(Position position) {
    	printRequestInfo("position with: " + position.toString());

        boolean success = false;
        try {
            infrastructureRemote.addPosition(position);
            success = true;
        } catch (Exception e) {
            success = false;
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @POST
    @Path("/persons")
    public Response addPerson(Person person) {
    	printRequestInfo("person with: " + person.toString());

        boolean success = false;
        try {
            infrastructureRemote.addPerson(person);
            success = true;
        } catch (Exception e) {
            success = false;
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @POST
    @Path("/treatments")
    public Response addTreatment(Treatment treatment) {
    	printRequestInfo("treatment with: " + treatment.toString());

        boolean success = false;
        try {
            infrastructureRemote.addTreatment(treatment);
            success = true;
        } catch (Exception e) {
            success = false;
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @POST
    @Path("/drugs")
    public Response addDrug(Drug drug) {
    	printRequestInfo("drug with: " + drug.toString());

        boolean success = false;
        try {
            infrastructureRemote.addDrug(drug);
            success = true;
        } catch (Exception e) {
            success = false;
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @POST
    @Path("/healthinsurances")
    public Response addHealthInsurance(HealthInsurance healthInsurance) {
    	printRequestInfo("healthInsurance with: " + healthInsurance.toString());

        boolean success = false;
        try {
            infrastructureRemote.addHealthInsurance(healthInsurance);
            success = true;
        } catch (Exception e) {
            success = false;
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @POST
    @Path("/adresses")
    public Response addAdress(Adress adress) {
    	printRequestInfo("adress with: " + adress.toString());

        boolean success = false;
        try {
            infrastructureRemote.addAdress(adress);
            success = true;
        } catch (Exception e) {
            success = false;
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @POST
    @Path("/employees")
    public Response addEmployee(Employee employee) {
    	printRequestInfo("employee with: " + employee.toString());

        boolean success = false;
        try {
            infrastructureRemote.addEmployee(employee);
            success = true;
        } catch (Exception e) {
            success = false;
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @POST
    @Path("/patients")
    public Response addPatient(Patient patient) {
    	printRequestInfo("patient with: " + patient.toString());

        boolean success = false;
        try {
            infrastructureRemote.addPatient(patient);
            success = true;
        } catch (Exception e) {
            success = false;
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @POST
    @Path("/diseases")
    public Response addDisease(Disease disease) {
    	printRequestInfo("disease with: " + disease.toString());

        boolean success = false;
        try {
            infrastructureRemote.addDisease(disease);
            success = true;
        } catch (Exception e) {
            success = false;
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @POST
    @Path("/schedule/requests")
    public Response addScheduleRequest(ScheduleRequest scheduleRequest) {
    	printRequestInfo("scheduleRequest with: " + scheduleRequest.toString());

        boolean success = false;
        try {
            infrastructureRemote.addScheduleRequest(scheduleRequest);
            success = true;
        } catch (Exception e) {
            success = false;
        }

        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    //############################### DELETE ############################

    @DELETE
    @Path("/schedule/requests/{id}")
    public Response removeScheduleRequest(@PathParam("id") int id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);

        boolean success = false;
        try {
            success = true;
            infrastructureRemote.removeScheduleRequest(id);
        } catch (NoSuchRowException e) {
            throw new NoSuchRowException();
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    //defaults
    @DELETE
    @Path("/positions/{id}")
    public Response removePosition(@PathParam("id") int id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);
    
        boolean success = false;
        try {
            success = true;
            infrastructureRemote.removePosition(id);
        } catch (NoSuchRowException e) {
            throw new NoSuchRowException();
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/persons/{id}")
    public Response removePerson(@PathParam("id") int id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);
    
        boolean success = false;
        try {
            success = true;
            infrastructureRemote.removePerson(id);
        } catch (NoSuchRowException e) {
            throw new NoSuchRowException();
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/treatments/{id}")
    public Response removeTreatment(@PathParam("id") int id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);

        boolean success = false;
        try {
            success = true;
            infrastructureRemote.removeTreatment(id);
        } catch (NoSuchRowException e) {
            throw new NoSuchRowException();
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/drugs/{id}")
    public Response removeDrug(@PathParam("id") int id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);

        boolean success = false;
        try {
            success = true;
            infrastructureRemote.removeDrug(id);
        } catch (NoSuchRowException e) {
            throw new NoSuchRowException();
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/healthinsurances/{id}")
    public Response removeHealthInsurance(@PathParam("id") int id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);

        boolean success = false;
        try {
            success = true;
            infrastructureRemote.removeHealthInsurance(id);
        } catch (NoSuchRowException e) {
            throw new NoSuchRowException();
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/adresses/{id}")
    public Response removeAdress(@PathParam("id") int id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);

        boolean success = false;
        try {
            success = true;
            infrastructureRemote.removeAdress(id);
        } catch (NoSuchRowException e) {
            throw new NoSuchRowException();
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/employees/{id}")
    public Response removeEmployee(@PathParam("id") int id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);
    	
        boolean success = false;
        try {
            success = true;
            infrastructureRemote.removeEmployee(id);
        } catch (NoSuchRowException e) {
            throw new NoSuchRowException();
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/patients/{id}")
    public Response removePatient(@PathParam("id") int id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);
    	
        boolean success = false;
        try {
            success = true;
            infrastructureRemote.removePatient(id);
        } catch (NoSuchRowException e) {
            throw new NoSuchRowException();
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/schedule/{id}")
    public Response removeSchedule(@PathParam("id") int id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);

        boolean success = false;
        try {
            success = true;
            infrastructureRemote.removeSchedule(id);
        } catch (NoSuchRowException e) {
            throw new NoSuchRowException();
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/prescriptions/{id}")
    public Response removePrescription(@PathParam("id") int id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);

        boolean success = false;
        try {
            success = true;
            infrastructureRemote.removePrescription(id);
        } catch (NoSuchRowException e) {
            throw new NoSuchRowException();
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }

    @DELETE
    @Path("/diseases/{id}")
    public Response removeDisease(@PathParam("id") int id) throws NoSuchRowException {
    	printRequestInfo("id = " + id);

        boolean success = false;
        try {
            success = true;
            infrastructureRemote.removeDisease(id);
        } catch (NoSuchRowException e) {
            throw new NoSuchRowException();
        }
        printRequestResult(success);
        StatusMessage msg = RestApplication.getReturnMessage(success);
        return Response.ok(msg).build();
    }
}
