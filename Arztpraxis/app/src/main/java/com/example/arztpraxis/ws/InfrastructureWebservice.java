package com.example.arztpraxis.ws;

import com.example.arztpraxis.model.Adress;
import com.example.arztpraxis.model.Disease;
import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.HealthInsurance;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.model.Prescription;
import com.example.arztpraxis.model.Schedule;
import com.example.arztpraxis.model.ScheduleRequest;
import com.example.arztpraxis.model.Treatment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;


import com.example.arztpraxis.model.Drug;


import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InfrastructureWebservice {
    private static final String URL
            //unten ist die richtige adresse, muss aber nach jedem reconnect geändert werden!
            //141.87.68.X mit X= aktuelle IP, andere Teile bleiben gleich!
            = "http://141.87.68.55:8080/BuildingREST2/rest/app";


    private GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("EEE,yyyy MM dd");//führt evtl später zu Problemen
    private Gson gson = gsonBuilder.create();

    private GsonBuilder gsonBuilderOtherDate = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z[UTC]'");
    private Gson gsonOtherDate = gsonBuilderOtherDate.create();

    private URL url;
    private URLConnection connection;
    private HttpURLConnection httpConnection;

    private OkHttpClient client = new OkHttpClient();

    private String urlString;

    public Drug getDrug(long id) throws NoSuchRowException {
        urlString = URL + "/drugs/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Drug drug = null;
            if ((output = response.body().string()) != null) {
                drug = gson.fromJson(output, Drug.class);
            }
            return drug;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        }
        return null;
    }

    public Prescription getPrescription(long id) throws NoSuchRowException {
        urlString = URL + "/prescriptions/id/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Prescription prescription = null;
            if ((output = response.body().string()) != null) {
                prescription = gson.fromJson(output, Prescription.class);
            }
            return prescription;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        }
        return null;
    }

    public ScheduleRequest getScheduleRequest(long id) throws NoSuchRowException {
        urlString = URL + "/schedule/requests/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            ScheduleRequest scheduleRequest = null;
            if ((output = response.body().string()) != null) {
                scheduleRequest = gson.fromJson(output, ScheduleRequest.class);
            }
            return scheduleRequest;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        }
        return null;
    }

    public Person getPerson(long id) throws NoSuchRowException {
        //System.out.println("Status: in getPerson("+id+")");
        urlString = URL + "/persons/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Person person = null;
            //System.out.println("Status: in in request");
            //System.out.println("Response-Body:"+response.body().string());
            if ((output = response.body().string()) != null) {
              //System.out.println("Status: in if");
                person = gson.fromJson(output, Person.class);
            }
            //person=gson.fromJson(response.peekBody(2048).string(),Person.class);
            //System.out.println("Status: in request2");
            return person;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Patient getPatient(long id) throws NoSuchRowException {
        //System.out.println("Status: in getPatient("+id+")");
        urlString = URL + "/patients/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Patient patient= null;
            //System.out.println("Status: in in request");
            //System.out.println("Response-Body:"+response.body().string());
            if ((output = response.body().string()) != null) {
                //System.out.println("Status: in if");
                patient = gson.fromJson(output, Patient.class);
            }
            //person=gson.fromJson(response.peekBody(2048).string(),Person.class);
            //System.out.println("Status: in request2");
            return patient;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public HealthInsurance getHealthInsurance(long id) throws NoSuchRowException {
        //System.out.println("Status: in getHealthInsurance("+id+")");
        urlString = URL + "/healthinsurances/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            HealthInsurance healthInsurance= null;
            //System.out.println("Status: in in request");
            //System.out.println("Response-Body:"+response.body().string());
            if ((output = response.body().string()) != null) {
                //System.out.println("Status: in if");
                healthInsurance = gson.fromJson(output, HealthInsurance.class);
            }

            //System.out.println("Status: in request2");
            return healthInsurance;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Schedule> getSchedulePatient(long id) {
        urlString = URL + "/schedule/patient/"+id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Schedule[] schedules = null;
            if ((output = response.body().string()) != null)
                schedules = gson.fromJson(output, Schedule[].class);
            Collection<Schedule> allSchedules = new ArrayList<Schedule>();
            for (int i = 0; i < schedules.length; i++)
                allSchedules.add(schedules[i]);
            return allSchedules;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Treatment getTreatment(long id) throws NoSuchRowException {
        //System.out.println("Status: in getHealthInsurance("+id+")");
        urlString = URL + "/treatments/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Treatment treatment= null;
            //System.out.println("Status: in in request");
            //System.out.println("Response-Body:"+response.body().string());
            if ((output = response.body().string()) != null) {
                //System.out.println("Status: in if");
                treatment = gson.fromJson(output, Treatment.class);
            }

            //System.out.println("Status: in request2");
            return treatment;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Schedule getSchedule(long id) throws NoSuchRowException {
        //System.out.println("Status: in getHealthInsurance("+id+")");
        urlString = URL + "/schedule/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Schedule schedule= null;
            //System.out.println("Status: in in request");
            //System.out.println("Response-Body:"+response.body().string());
            if ((output = response.body().string()) != null) {
                //System.out.println("Status: in if");
                schedule = gson.fromJson(output, Schedule.class);
            }

            //System.out.println("Status: in request2");
            return schedule;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Employee getEmployee(long id) throws NoSuchRowException {
        //System.out.println("Status: in getHealthInsurance("+id+")");
        urlString = URL + "/employees/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Employee employee= null;
            //System.out.println("Status: in in request");
            //System.out.println("Response-Body:"+response.body().string());
            if ((output = response.body().string()) != null) {
                //System.out.println("Status: in if");
                employee = gson.fromJson(output, Employee.class);
            }

            //System.out.println("Status: in request2");
            return employee;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void createScheduleRequest(ScheduleRequest scheduleRequest) throws IllegalCreateException {
        urlString = URL + "/schedule/requests";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                gson.toJson(scheduleRequest));
        //System.out.println(body);
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        System.out.println(gson.toJson(scheduleRequest).toString());
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseString = response.body().string();
            if (responseString.compareTo("{\"status\":\"success\"}") != 0)
                throw new IllegalCreateException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createScheduleFromRequest(Schedule schedule,long id) throws IllegalCreateException {
        urlString = URL + "/schedule/add/"+id;
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                gsonOtherDate.toJson(schedule));
        //System.out.println(body);
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        //System.out.println(gsonOtherDate.toJson(schedule).toString());
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseString = response.body().string();
            if (responseString.compareTo("{\"status\":\"success\"}") != 0)
                throw new IllegalCreateException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<ScheduleRequest> getAllScheduleRequests() {
        urlString = URL + "/schedule/requests";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            ScheduleRequest[] scheduleRequests = null;
            if ((output = response.body().string()) != null)
                scheduleRequests = gson.fromJson(output, ScheduleRequest[].class);
            Collection<ScheduleRequest> allScheduleRequests = new ArrayList<ScheduleRequest>();
            for (int i = 0; i < scheduleRequests.length; i++)
                allScheduleRequests.add(scheduleRequests[i]);
            return allScheduleRequests;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getEmployeeOfName(String firstName, String lastName) throws NoSuchRowException {
        //System.out.println("Status: in getHealthInsurance("+id+")");
        urlString = URL + "/employees/name?firstName=" + firstName + "&lastName=" + lastName;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            long id= 0;
            //System.out.println("Status: in in request");
            //System.out.println("Response-Body:"+response.body().string());
            if ((output = response.body().string()) != null) {
                //System.out.println("Status: in if");
                id = Long.parseLong(output);
            }

            //System.out.println("Status: in request2");
            return id;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public long getPatientOfName(String firstName, String lastName) throws NoSuchRowException {
        //System.out.println("Status: in getHealthInsurance("+id+")");
        urlString = URL + "/patients/name?firstName=" + firstName + "&lastName=" + lastName;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            long id= 0;
            //System.out.println("Status: in in request");
            //System.out.println("Response-Body:"+response.body().string());
            if ((output = response.body().string()) != null) {
                //System.out.println("Status: in if");
                id = Long.parseLong(output);
            }

            //System.out.println("Status: in request2");
            return id;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public Collection<Patient> getAllPatients() {
        urlString = URL + "/patients";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Patient[] patients = null;
            if ((output = response.body().string()) != null)
                patients = gson.fromJson(output, Patient[].class);
            Collection<Patient> allPatients = new ArrayList<Patient>();
            for (int i = 0; i < patients.length; i++)
                allPatients.add(patients[i]);
            return allPatients;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Schedule> getAllSchedule() {
        urlString = URL + "/schedule";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Schedule[] schedules = null;
            if ((output = response.body().string()) != null)
                schedules = gson.fromJson(output, Schedule[].class);
            Collection<Schedule> allSchedules = new ArrayList<Schedule>();
            for (int i = 0; i < schedules.length; i++)
                allSchedules.add(schedules[i]);
            return allSchedules;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Treatment> getAllTreatments() {
        urlString = URL + "/treatments";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Treatment[] treatments = null;
            if ((output = response.body().string()) != null)
                treatments = gson.fromJson(output, Treatment[].class);
            Collection<Treatment> allTreatments = new ArrayList<Treatment>();
            for (int i = 0; i < treatments.length; i++)
                allTreatments.add(treatments[i]);
            return allTreatments;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createPerson(Person person) throws IllegalCreateException {
        urlString = URL + "/persons";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                gsonOtherDate.toJson(person));
        //System.out.println(body);
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        //System.out.println(gsonOtherDate.toJson(person).toString());
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseString = response.body().string();
            if (responseString.compareTo("{\"status\":\"success\"}") != 0)
                throw new IllegalCreateException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<Adress> getAllAdresses() {
        urlString = URL + "/adresses";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Adress[] adresses = null;
            if ((output = response.body().string()) != null)
                adresses = gson.fromJson(output, Adress[].class);
            Collection<Adress> allAdresses = new ArrayList<Adress>();
            for (int i = 0; i < adresses.length; i++)
                allAdresses.add(adresses[i]);
            return allAdresses;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Person> getAllPersons() {
        urlString = URL + "/persons";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Person[] persons = null;
            if ((output = response.body().string()) != null)
                persons = gson.fromJson(output, Person[].class);
            Collection<Person> allPersons = new ArrayList<Person>();
            for (int i = 0; i < persons.length; i++)
                allPersons.add(persons[i]);
            return allPersons;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<HealthInsurance> getAllHealthInsurances() {
        urlString = URL + "/healthinsurances";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            HealthInsurance[] healthInsurances = null;
            if ((output = response.body().string()) != null)
                healthInsurances = gson.fromJson(output, HealthInsurance[].class);
            Collection<HealthInsurance> allHealthInsurances = new ArrayList<HealthInsurance>();
            for (int i = 0; i < healthInsurances.length; i++)
                allHealthInsurances.add(healthInsurances[i]);
            return allHealthInsurances;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createPatient(Patient patient) throws IllegalCreateException {
        urlString = URL + "/patients";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                gson.toJson(patient));
        //System.out.println(body);
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        //System.out.println(gson.toJson(person).toString());
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseString = response.body().string();
            if (responseString.compareTo("{\"status\":\"success\"}") != 0)
                throw new IllegalCreateException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createAdress(Adress adress) throws IllegalCreateException {
        urlString = URL + "/adresses";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                gson.toJson(adress));
        //System.out.println(body);
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        //System.out.println(gson.toJson(adress).toString());
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseString = response.body().string();
            if (responseString.compareTo("{\"status\":\"success\"}") != 0)
                throw new IllegalCreateException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<Prescription> getAllPrescriptions() {
        urlString = URL + "/prescriptions";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Prescription[] prescriptions = null;
            if ((output = response.body().string()) != null)
                prescriptions = gson.fromJson(output, Prescription[].class);
            Collection<Prescription> allPrescriptions = new ArrayList<Prescription>();
            for (int i = 0; i < prescriptions.length; i++)
                allPrescriptions.add(prescriptions[i]);
            return allPrescriptions;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Disease getDisease(long id) throws NoSuchRowException {
        //System.out.println("Status: in getHealthInsurance("+id+")");
        urlString = URL + "/diseases/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Disease disease= null;
            //System.out.println("Status: in in request");
            //System.out.println("Response-Body:"+response.body().string());
            if ((output = response.body().string()) != null) {
                //System.out.println("Status: in if");
                disease = gson.fromJson(output, Disease.class);
            }

            //System.out.println("Status: in request2");
            return disease;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Prescription> getPrescriptionOf(long id) {
        urlString = URL + "/prescriptions/"+id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Prescription[] prescriptions = null;
            if ((output = response.body().string()) != null)
                prescriptions = gson.fromJson(output, Prescription[].class);
            Collection<Prescription> allPrescriptions = new ArrayList<Prescription>();
            for (int i = 0; i < prescriptions.length; i++)
                allPrescriptions.add(prescriptions[i]);
            return allPrescriptions;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Prescription> getPrescriptionFrom(long id) {
        urlString = URL + "/prescriptions/employee/"+id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Prescription[] prescriptions = null;
            if ((output = response.body().string()) != null)
                prescriptions = gson.fromJson(output, Prescription[].class);
            Collection<Prescription> allPrescriptions = new ArrayList<Prescription>();
            for (int i = 0; i < prescriptions.length; i++)
                allPrescriptions.add(prescriptions[i]);
            return allPrescriptions;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Drug> getAllDrugs() {
        urlString = URL + "/drugs";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Drug[] drugs= null;
            if ((output = response.body().string()) != null)
                drugs = gson.fromJson(output, Drug[].class);
            Collection<Drug> allDrugs = new ArrayList<Drug>();
            for (int i = 0; i < drugs.length; i++)
                allDrugs.add(drugs[i]);
            return allDrugs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Disease> getAllDiseases() {
        urlString = URL + "/diseases";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Disease[] diseases= null;
            if ((output = response.body().string()) != null)
                diseases = gson.fromJson(output, Disease[].class);
            Collection<Disease> allDiseases = new ArrayList<Disease>();
            for (int i = 0; i < diseases.length; i++)
                allDiseases.add(diseases[i]);
            return allDiseases;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createPrescription(Prescription prescription) throws IllegalCreateException {
        urlString = URL + "/prescriptions";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                gsonOtherDate.toJson(prescription));
        //System.out.println(body);
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        //System.out.println(gsonOtherDate.toJson(prescription).toString());
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseString = response.body().string();
            if (responseString.compareTo("{\"status\":\"success\"}") != 0)
                throw new IllegalCreateException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogin(String type, String firstName, String lastName, String number) {
        //System.out.println("Status: in getHealthInsurance("+id+")");
        urlString = URL + "/login?type="+type+"&firstname="+firstName+"&lastname="+lastName+"&number="+number;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            //System.out.println("Status: in in request");
            //System.out.println("Response-Body:"+response.body().string());
            if ((output = response.body().string()) != null) {
                //System.out.println("Status: in if");
                return output;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

