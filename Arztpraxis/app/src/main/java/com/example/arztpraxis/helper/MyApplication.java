package com.example.arztpraxis.helper;

import android.app.Application;

import com.example.arztpraxis.model.Employee;
import com.example.arztpraxis.model.Patient;


//just do anywhere:
// boolean loggedin=((MyApplication) this.getApplication()).isLoggedIn();
//or:
// ((MyApplication) this.getApplication()).setLoggedIn(true/false);
public class MyApplication extends Application {
    private boolean loggedIn;

    private Patient patient;
    private Employee employee;

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public long getUserId(){
        if (patient!=null){
            return patient.getId();
        }else if (employee!=null){
            return employee.getId();
        }else {
            return 0;
        }
    }

    public boolean isAdmin(){
        return employee!=null;
    }
}
