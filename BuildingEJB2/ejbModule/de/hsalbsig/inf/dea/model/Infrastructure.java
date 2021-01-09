package de.hsalbsig.inf.dea.model;

import java.sql.Date;
import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class Infrastructure implements InfrastructureRemote {
	@PersistenceContext(unitName = "BuildingEJB")
	private EntityManager em;

	public Infrastructure() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Building> getAllBuildings() {
		return em.createQuery("SELECT b FROM Building b").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Room> getAllRooms() {
		return em.createQuery("SELECT r FROM Room r").getResultList();
	}

	@Override
	public long getCountRooms() {
		return (long) em.createQuery("SELECT count(*) FROM Room r").getSingleResult();
	}

	@Override
	public Room getRoom(long id) throws NoSuchRowException {
		Room room = em.find(Room.class, id);
		if (room != null)
			return room;
		else
			throw new NoSuchRowException();
	}

	@Override
	public Building getBuilding(long id) throws NoSuchRowException {
		Building building = em.find(Building.class, id);
		if (building != null)
			return building;
		else
			throw new NoSuchRowException();
	}

	@Override
	public void save(Building building) {
		Building b = em.find(Building.class, building.getId());

		if (b != null) {
			b.copyData(building); // b := building (deep copy)
			em.merge(b);
		} else
			em.persist(building);
	}

	@Override
	public void removeBuilding(Building building) throws NoSuchRowException {
		removeBuilding(building.getId());
	}

	@Override
	public void removeBuilding(long primaryKey) throws NoSuchRowException {
		Building b = em.find(Building.class, primaryKey);
		if (b != null)
			em.remove(b);
		else
			throw new NoSuchRowException();
	}
	
	
	//###############################eigener Code:#######################
	
	//############################### GET ###############################
	
	//getAll[in Table]
	@SuppressWarnings("unchecked")
    @Override
    public Collection<Patient> getAllPatients() {
        return em.createQuery("SELECT p FROM Patient p").getResultList();
    }
	
	@SuppressWarnings("unchecked")
    @Override
    public Collection<Schedule> getSchedule() {
        return em.createQuery("SELECT s FROM Schedule s").getResultList();
    }
	
    @SuppressWarnings("unchecked")
    @Override
    public Collection<Person> getAllPersons() {
        return em.createQuery("SELECT p FROM Person p").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Collection<Treatment> getAllTreatments() {
        return em.createQuery("SELECT t FROM Treatment t").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Collection<Drug> getAllDrugs() {
        return em.createQuery("SELECT d FROM Drug d").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Collection<HealthInsurance> getAllHealthInsurances() {
        return em.createQuery("SELECT h FROM HealthInsurance h").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Collection<Adress> getAllAdresses() {
        return em.createQuery("SELECT a FROM Adress a").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Collection<Employee> getAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Collection<Prescription> getAllPrescriptions() {
        return em.createQuery("SELECT p FROM Prescription p").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Collection<Disease> getAllDiseases() {
        return em.createQuery("SELECT d FROM Disease d").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Collection<Position> getAllPositions() {
        return em.createQuery("SELECT p FROM Position p").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Collection<ScheduleRequest> getAllScheduleRequests() {
        return em.createQuery("SELECT s FROM ScheduleRequest s ORDER BY s.priority").getResultList();
    }
	
	//get[specific id in Table]
	@SuppressWarnings("unchecked")
    @Override
    public Collection<Schedule> getScheduleOfPatient(String id) {
        return em.createQuery("SELECT s FROM Schedule s where s.patientId="+id).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    @Override
    public Collection<Schedule> getScheduleOfEmployee(String id) {
        return em.createQuery("SELECT s FROM Schedule s where s.employeeId="+id).getResultList();
    }
	
	
	@Override
	public long getEmployeeOfName(String firstName, String lastName) {
	  return (long) em.createQuery("SELECT e.id FROM Employee e WHERE e.personId=(SELECT p.id FROM Person p WHERE p.firstName='"+firstName+"' AND p.lastName='"+lastName+"')").getSingleResult();
	}
	
	@Override
    public long getPatientOfName(String firstName, String lastName) {
      return (long) em.createQuery("SELECT e.id FROM Patient e WHERE e.person=(SELECT p.id FROM Person p WHERE p.firstName='"+firstName+"' AND p.lastName='"+lastName+"')").getSingleResult();
    }

	@Override
    public Patient getPatientInfo(long id) throws NoSuchRowException {
        Patient patient = em.find(Patient.class, id);
        if (patient != null)
            return patient;
        else
            throw new NoSuchRowException();
    }
	
	@Override
    public Employee getEmployeeInfo(long id) throws NoSuchRowException {
        Employee employee = em.find(Employee.class,id);
        if (employee != null)
            return employee;
        else
            throw new NoSuchRowException();
    }
	
	@Override
	public long getLoginId(String type, String firstName, String lastName, int number) {
	  long login_id;
	  switch (type) {
        case "patient":
          try {
            login_id=(long) em.createQuery("select s.id from Patient s where s.SSN="+number+
                " and '"+firstName+"' in (select p.firstName from Person p where p.id=s.person)"+ 
                " and '"+lastName+"' in (select p.lastName from Person p where p.id=s.person)").getSingleResult();
            
          } catch (Exception e) {
            login_id=0;
            System.out.println(e);
          }
          
          break;
        case "employee":
          try {
            login_id= (long) em.createQuery("select s.id from Employee s where s.personnalnumber="+number+
                " and '"+firstName+"' in (select p.firstName from Person p where p.id=s.personId)"+ 
                " and '"+lastName+"' in (select p.lastName from Person p where p.id=s.personId)").getSingleResult();
            
          } catch (Exception e) {
            login_id=0;
          }          
          break;  
        default:
          login_id=0;
          break;
      }
	  return login_id;
	}
	
	@Override
    public String login(String type, String firstName, String lastName, int number){
      long login_id=getLoginId(type, firstName, lastName, number);
      String login_info;
      switch (type) {
        case "patient":
          try {
            login_info=getPatientInfo(login_id).toString();
          } catch (NoSuchRowException e) {
            login_info="";
            e.printStackTrace();
          }
          break;
        case "employee":
          try {
            login_info=getEmployeeInfo(login_id).toString();
          } catch (NoSuchRowException e) {
            login_info="";
            e.printStackTrace();
          }
          break;  
        default:
          login_info="";
          break;
      }
      return login_info;
    }
	
	@SuppressWarnings("unchecked")
    @Override
    public Collection<Prescription> getPrescriptionOf(String id) {
        return em.createQuery("SELECT p FROM Prescription p WHERE patientid="+id).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    @Override
    public Collection<Prescription> getPrescriptionFrom(String id) {
        return em.createQuery("SELECT p FROM Prescription p WHERE p.employeeId="+id).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    @Override
    public Collection<ScheduleRequest> getScheduleRequests() {
        return em.createQuery("SELECT r FROM ScheduleRequest r").getResultList();
    }
	//defaults
	@Override
    public Position getPosition(long id) throws NoSuchRowException {
        Position position = em.find(Position.class, id);
        if (position != null)
            return position;
        else
            throw new NoSuchRowException();
    }
	
	@Override
    public Person getPerson(long id) throws NoSuchRowException {
      Person person = em.find(Person.class, id);
      if (person != null)
          return person;
      else
          throw new NoSuchRowException();
    }
    
    @Override
    public Treatment getTreatment(long id) throws NoSuchRowException {
      Treatment treatment = em.find(Treatment.class, id);
      if (treatment != null)
          return treatment;
      else
          throw new NoSuchRowException();
    }
    
    @Override
    public Drug getDrug(long id) throws NoSuchRowException {
      Drug drug = em.find(Drug.class, id);
      if (drug != null)
          return drug;
      else
          throw new NoSuchRowException();
    }
    
    @Override
    public HealthInsurance getHealthInsurance(long id) throws NoSuchRowException {
      HealthInsurance healthInsurance = em.find(HealthInsurance.class, id);
      if (healthInsurance != null)
          return healthInsurance;
      else
          throw new NoSuchRowException();
    }
    
    @Override
    public Adress getAdress(long id) throws NoSuchRowException {
      Adress adress = em.find(Adress.class, id);
      if (adress != null)
          return adress;
      else
          throw new NoSuchRowException();
    }
    
    @Override
    public Prescription getPrescription(long id) throws NoSuchRowException {
      Prescription prescription = em.find(Prescription.class, id);
      if (prescription != null)
          return prescription;
      else
          throw new NoSuchRowException();
    }
    
    @Override
    public Disease getDisease(long id) throws NoSuchRowException {
      Disease disease = em.find(Disease.class, id);
      if (disease != null)
          return disease;
      else
          throw new NoSuchRowException();
    }
    
    @Override
    public ScheduleRequest getScheduleRequest(long id) throws NoSuchRowException {
      ScheduleRequest scheduleRequest = em.find(ScheduleRequest.class, id);
      if (scheduleRequest != null)
          return scheduleRequest;
      else
          throw new NoSuchRowException();
    }
    
    @Override
    public Schedule getSchedule(long id) throws NoSuchRowException {
      Schedule schedule = em.find(Schedule.class, id);
      if (schedule != null)
          return schedule;
      else
          throw new NoSuchRowException();
    }
    
	
	//############################### POST ##############################
	
	@Override
    public void addSchedule(Schedule schedule) {
        Schedule s = em.find(Schedule.class, schedule.getId());

        if (s != null) {
            s.copyData(schedule); // s := schedule (deep copy)
            em.merge(s);
        } else
            em.persist(schedule);
    }
	
	@Override
    public void addPrescription(Prescription prescription) {
        Prescription p = em.find(Prescription.class, prescription.getId());

        if (p != null) {
            p.copyData(prescription); // p := prescription (deep copy)
            em.merge(p);
        } else
            em.persist(prescription);
    }
	
	//defaults
    @Override
    public void addPosition(Position position) {
        Position p = em.find(Position.class, position.getId());

        if (p != null) {
            p.copyData(position); // p := prescription (deep copy)
            em.merge(p);
        } else
            em.persist(position);
    }
    
    @Override
    public void addPerson(Person person) {
        Person p = em.find(Person.class, person.getId());

        if (p != null) {
            p.copyData(person); // p := prescription (deep copy)
            em.merge(p);
        } else
            em.persist(person);
    }
    
    @Override
    public void addTreatment(Treatment treatment) {
        Treatment t = em.find(Treatment.class, treatment.getId());

        if (t != null) {
            t.copyData(treatment); // p := prescription (deep copy)
            em.merge(t);
        } else
            em.persist(treatment);
    }
    
    @Override
    public void addHealthInsurance(HealthInsurance healthInsurance) {
        HealthInsurance hi = em.find(HealthInsurance.class, healthInsurance.getId());

        if (hi != null) {
            hi.copyData(healthInsurance); // p := prescription (deep copy)
            em.merge(hi);
        } else
            em.persist(healthInsurance);
    }
    
    @Override
    public void addAdress(Adress adress) {
        Adress a = em.find(Adress.class, adress.getId());

        if (a != null) {
            a.copyData(adress); // p := prescription (deep copy)
            em.merge(a);
        } else
            em.persist(adress);
    }
    
    @Override
    public void addEmployee(Employee employee) {
        Employee e = em.find(Employee.class, employee.getId());

        if (e != null) {
            e.copyData(employee); // p := prescription (deep copy)
            em.merge(e);
        } else
            em.persist(employee);
    }
    
    @Override
    public void addPatient(Patient patient) {
        Patient p = em.find(Patient.class, patient.getId());

        if (p != null) {
            p.copyData(patient); // p := prescription (deep copy)
            em.merge(p);
        } else
            em.persist(patient);
    }
    
    @Override
    public void addDisease(Disease disease) {
        Disease d = em.find(Disease.class, disease.getId());

        if (d != null) {
            d.copyData(disease); // p := prescription (deep copy)
            em.merge(d);
        } else
            em.persist(disease);
    }
    
    @Override
    public void addScheduleRequest(ScheduleRequest scheduleRequest) {
        ScheduleRequest sr = em.find(ScheduleRequest.class, scheduleRequest.getId());

        if (sr != null) {
            sr.copyData(scheduleRequest); // p := prescription (deep copy)
            em.merge(sr);
        } else
            em.persist(scheduleRequest);
    }
    
    @Override
    public void addDrug(Drug drug) {
        Drug d = em.find(Drug.class, drug.getId());

        if (d != null) {
            d.copyData(drug); // p := prescription (deep copy)
            em.merge(d);
        } else
            em.persist(drug);
    }
	
	
	//############################### DELETE ############################
	
	@Override
    public void removeScheduleRequest(long primaryKey) throws NoSuchRowException {
        ScheduleRequest sr = em.find(ScheduleRequest.class, primaryKey);
        if (sr != null)
            em.remove(sr);
        else
            throw new NoSuchRowException();
    }
	//defaults
	@Override
    public void removePosition(long primaryKey) throws NoSuchRowException {
        Position p = em.find(Position.class, primaryKey);
        if (p != null)
            em.remove(p);
        else
            throw new NoSuchRowException();
    }
    
    @Override
    public void removePerson(long primaryKey) throws NoSuchRowException {
        Person p = em.find(Person.class, primaryKey);
        if (p != null)
            em.remove(p);
        else
            throw new NoSuchRowException();
    }
    
    @Override
    public void removeTreatment(long primaryKey) throws NoSuchRowException {
        Treatment t = em.find(Treatment.class, primaryKey);
        if (t != null)
            em.remove(t);
        else
            throw new NoSuchRowException();
    }
    
    @Override
    public void removeDrug(long primaryKey) throws NoSuchRowException {
        Drug d = em.find(Drug.class, primaryKey);
        if (d != null)
            em.remove(d);
        else
            throw new NoSuchRowException();
    }
    
    @Override
    public void removeHealthInsurance(long primaryKey) throws NoSuchRowException {
        HealthInsurance hi = em.find(HealthInsurance.class, primaryKey);
        if (hi != null)
            em.remove(hi);
        else
            throw new NoSuchRowException();
    }
    
    @Override
    public void removeAdress(long primaryKey) throws NoSuchRowException {
        Adress a = em.find(Adress.class, primaryKey);
        if (a != null)
            em.remove(a);
        else
            throw new NoSuchRowException();
    }
    
    @Override
    public void removeEmployee(long primaryKey) throws NoSuchRowException {
        Employee e = em.find(Employee.class, primaryKey);
        if (e != null)
            em.remove(e);
        else
            throw new NoSuchRowException();
    }
    
    @Override
    public void removePatient(long primaryKey) throws NoSuchRowException {
        Patient p = em.find(Patient.class, primaryKey);
        if (p != null)
            em.remove(p);
        else
            throw new NoSuchRowException();
    }
    
    @Override
    public void removeSchedule(long primaryKey) throws NoSuchRowException {
        Schedule s = em.find(Schedule.class, primaryKey);
        if (s != null)
            em.remove(s);
        else
            throw new NoSuchRowException();
    }
    
    @Override
    public void removePrescription(long primaryKey) throws NoSuchRowException {
        Prescription p = em.find(Prescription.class, primaryKey);
        if (p != null)
            em.remove(p);
        else
            throw new NoSuchRowException();
    }
    
    @Override
    public void removeDisease(long primaryKey) throws NoSuchRowException {
        Disease d = em.find(Disease.class, primaryKey);
        if (d != null)
            em.remove(d);
        else
            throw new NoSuchRowException();
    }
}
