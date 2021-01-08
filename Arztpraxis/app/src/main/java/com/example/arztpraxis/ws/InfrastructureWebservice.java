package com.example.arztpraxis.ws;

import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.HealthInsurance;
import com.example.arztpraxis.model.Patient;
import com.example.arztpraxis.model.Person;
import com.example.arztpraxis.model.Schedule;
import com.example.arztpraxis.model.Treatment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;

import com.example.arztpraxis.model.Building;
import com.example.arztpraxis.model.Drug;
import com.example.arztpraxis.model.Room;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InfrastructureWebservice {
    private static final String URL
            //unten ist die richtige adresse, muss aber nach jedem reconnect geändert werden!
            //141.87.68.X mit X= aktuelle IP, andere Teile bleiben gleich!
            = "http://141.87.68.17:8080/BuildingREST2/rest/app";


    private GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("EEE,yyyy MM dd");//führt evtl später zu Problemen
    private Gson gson = gsonBuilder.create();

    private URL url;
    private URLConnection connection;
    private HttpURLConnection httpConnection;

    private OkHttpClient client = new OkHttpClient();

    private String urlString;

    public int getCountRooms() {
        //urlString = URL + "/count";
        urlString = URL + "/patients";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        System.out.println( "reauest = " + request);
        try {
            Response response = client.newCall(request).execute();
            System.out.println( "response = " + response);
            return Integer.parseInt(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Collection<Room> getRooms() {
        urlString = URL + "/rooms";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Room[] rooms = null;
            if ((output = response.body().string()) != null)
                rooms = gson.fromJson(output, Room[].class);
            Collection<Room> allRooms = new ArrayList<Room>();
            for (int i = 0; i < rooms.length; i++)
                allRooms.add(rooms[i]);
            return allRooms;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Building> getBuildings() {
        urlString = URL + "/buildings";
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Building[] buildings = null;
            if ((output = response.body().string()) != null)
                buildings = gson.fromJson(output, Building[].class);
            Collection<Building> allBuildings = new ArrayList<Building>();
            for (int i = 0; i < buildings.length; i++)
                allBuildings.add(buildings[i]);
            return allBuildings;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Room getRoom(long id) throws NoSuchRowException {
        urlString = URL + "/rooms/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Room room = null;
            if ((output = response.body().string()) != null) {
                room = gson.fromJson(output, Room.class);
                // zugegebene Vergewaltigung der JsonSyntaxException hinsichtlich
                // NoSuchRowException ...
            }
            return room;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        }
        return null;
    }

    public Building getBuilding(long id) throws NoSuchRowException {
        urlString = URL + "/buildings/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            Building building = null;
            if ((output = response.body().string()) != null) {
                building = gson.fromJson(output, Building.class);
                // zugegebene Vergewaltigung der JsonException hinsichtlich
                // NoSuchRowException ...
            }
            return building;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        }
        return null;
    }

    public void removeBuilding(long id) throws NoSuchRowException {
        urlString = URL + "/buildings/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .delete()
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            if (responseString.compareTo("{\"status\":\"success\"}") != 0)
                throw new NoSuchRowException();
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        }
    }

    public void createBuilding(Building building) throws IllegalCreateException {
        urlString = URL + "/buildings/";
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                gson.toJson(building));
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
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

    //##########################eigener Code#####################################
    //########################## GET ID #########################################
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
}

