/*
package com.example.arztpraxis.ws;

import android.graphics.Color;
import android.os.Build;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collection;

//import de.hsalbsig.inf.dea.model.*;
import com.example.arztpraxis.model.*;
public class OldMainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonCountRooms,
            buttonRoom,
            buttonShowBuilding,
            buttonRemoveBuilding,
            buttonCreateBuilding,
            buttonAllRooms,
            buttonAllBuildings,
            buttonClear;
    TextView text;
    EditText editRoomNumber,
            editBuildingNumber,
            editBuildingNumberForRemove,
            buildingId, buildingNumber, buildingStreet; // fuer Mehrfachfeld-Eingabe zu "createBuilding"

    RelativeLayout relativeLayout;
    LinearLayout linearLayout;  // fuer Mehrfachfeld-Eingabe zu "createBuilding"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ... ein evtl. etwas pragmatischeres Setzen der Hintergrundfarbe anstelle
        // des Hexcodes in activity_main.xml
        relativeLayout = findViewById(R.id.relativeLayout);
        relativeLayout.setBackgroundColor(Color.WHITE);
        linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setBackgroundColor(Color.CYAN);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Alle GUI-Elemente
        buttonCountRooms = (Button) findViewById(R.id.buttonCountRooms);
        buttonRoom = (Button) findViewById(R.id.buttonRoom);
        buttonShowBuilding = (Button) findViewById(R.id.buttonShowBuilding);
        buttonRemoveBuilding = (Button) findViewById(R.id.buttonRemoveBuilding);

        buttonCreateBuilding = (Button) findViewById(R.id.buttonCreateBuilding);

        buttonAllRooms = (Button) findViewById(R.id.buttonAllRooms);
        buttonAllBuildings = (Button) findViewById(R.id.buttonAllBuildings);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        editRoomNumber = (EditText) findViewById(R.id.editRoomNumber);
        editBuildingNumber = (EditText) findViewById(R.id.editBuildingNumber);
        editBuildingNumberForRemove = (EditText) findViewById(R.id.editBuildingNumberForRemove);

        buildingId = (EditText) findViewById(R.id.buildingId);
        buildingNumber = (EditText) findViewById(R.id.buildingNumber);
        buildingStreet = (EditText) findViewById(R.id.buildingStreet);

        text = (TextView) findViewById(R.id.tvInfo);

        // Listener
        buttonCountRooms.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonClear.setBackgroundColor(Color.GREEN);

        buttonRoom.setOnClickListener(this);
        buttonShowBuilding.setOnClickListener(this);
        buttonRemoveBuilding.setOnClickListener(this);

        buttonCreateBuilding.setOnClickListener(this);

        buttonAllRooms.setOnClickListener(this);
        buttonAllBuildings.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        InfrastructureWebservice service = null;
        String s = "";
        long id;
        String result;
        Building building = null;
        Room room = null;

        Drug drug=null;

        switch (view.getId()) {
            case R.id.buttonClear:
                clearText();
                break;
            case R.id.buttonCountRooms:
                */
/* Evtl. zum Debuggen ....
                try (PrintStream printf = System.out.printf("NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted() = %s%n",

                        NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted())) {
                } catch ( Exception e) {
                    System.out.println( "Exception zu isCleartextPermitted");
                    e.printStackTrace();
                }
                 *//*


                service = new InfrastructureWebservice();
                text.setText("" + service.getCountRooms());
                editRoomNumber.setText("");
                editBuildingNumber.setText("");
                editBuildingNumberForRemove.setText("");
                buildingId.setText("");
                buildingStreet.setText("");
                buildingNumber.setText("");
                break;
            case R.id.buttonRoom:
                id = 1;
                result = editRoomNumber.getText().toString();
                if (!result.isEmpty())
                    id = Long.parseLong(result);

                service = new InfrastructureWebservice();
                try {
                    room = service.getRoom(id);
                    if (room != null)
                        text.setText(room.toString());
                } catch (NoSuchRowException e) {
                    text.setText("Kein Raum gefunden!");
                }
                editBuildingNumber.setText("");
                editBuildingNumberForRemove.setText("");
                buildingId.setText("");
                buildingStreet.setText("");
                buildingNumber.setText("");
                break;
            case R.id.buttonShowBuilding:
                id = 1;
                result = editBuildingNumber.getText().toString();
                if (!result.isEmpty())
                    id = Long.parseLong(result);
                service = new InfrastructureWebservice();
                try {
                    drug = service.getDrug(id);
                    if (drug != null)
                        text.setText(drug.toString());
                } catch (NoSuchRowException e) {
                    text.setText("Kein Drug gefunden!");
                }
                editRoomNumber.setText("");
                buildingId.setText("");
                buildingStreet.setText("");
                buildingNumber.setText("");
                break;
//            case R.id.buttonShowBuilding:
//                id = 1;
//                result = editBuildingNumber.getText().toString();
//                if (!result.isEmpty())
//                    id = Long.parseLong(result);
//                service = new InfrastructureWebservice();
//                try {
//                    building = service.getBuilding(id);
//                    if (building != null)
//                        text.setText(building.toString());
//                } catch (NoSuchRowException e) {
//                    text.setText("Kein Gebaeude gefunden!");
//                }
//                editRoomNumber.setText("");
//                buildingId.setText("");
//                buildingStreet.setText("");
//                buildingNumber.setText("");
//                break;
            case R.id.buttonRemoveBuilding:
                id = 1;
                result = editBuildingNumberForRemove.getText().toString();
                if (!result.isEmpty())
                    id = Long.parseLong(result);

                service = new InfrastructureWebservice();
                try {
                    service.removeBuilding(id);
                    text.setText(" Gebaeude gelöscht!");
                } catch (NoSuchRowException e) {
                    text.setText("Kein Gebaeude gefunden!");
                }
                editRoomNumber.setText("");
                editBuildingNumber.setText("");
                buildingId.setText("");
                buildingStreet.setText("");
                buildingNumber.setText("");
                break;
            case R.id.buttonCreateBuilding:
                text.setText("");
                service = new InfrastructureWebservice();

                if (buildingId.getText().toString().compareTo("") == 0
                        || buildingNumber.getText().toString().compareTo("") == 0
                        || buildingStreet.getText().toString().compareTo("") == 0) {
                    text.setText("Eingabefehler!");
                } else {
                    building = new Building(Integer.parseInt(buildingId.getText().toString()),
                            buildingNumber.getText().toString(),
                            buildingStreet.getText().toString());
                    try {
                        service.createBuilding(building);
                        text.setText(" Speichern erfolgreich!");
                    } catch (IllegalCreateException e) {
                        text.setText("Fehler beim Speichern in der Datenbank!");
                    }
                    editRoomNumber.setText("");
                    editBuildingNumber.setText("");
                    editBuildingNumberForRemove.setText("");
                }
                break;
            case R.id.buttonAllRooms:
                text.setText("");
                service = new InfrastructureWebservice();
                Collection<Room> allRooms = service.getRooms();
                s = "";
                for (Room r : allRooms)
                    s += r.toString() + "\n";
                text.setText(s);
                editRoomNumber.setText("");
                editBuildingNumber.setText("");
                editBuildingNumberForRemove.setText("");
                buildingId.setText("");
                buildingStreet.setText("");
                buildingNumber.setText("");
                break;
            case R.id.buttonAllBuildings:
                text.setText("");
                service = new InfrastructureWebservice();
                Collection<Building> allBuildings = service.getBuildings();
                s = "";
                for (Building r : allBuildings)
                    s += r.toString() + "\n";
                text.setText(s);
                editRoomNumber.setText("");
                editBuildingNumber.setText("");
                editBuildingNumberForRemove.setText("");
                buildingId.setText("");
                buildingStreet.setText("");
                buildingNumber.setText("");
                break;
            default:
                Toast.makeText(this, "unknown event", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void clearText() {
        text.setText("");
        editRoomNumber.setText("");
        editBuildingNumber.setText("");
        editBuildingNumberForRemove.setText("");
        buildingId.setText("");
        buildingStreet.setText("");
        buildingNumber.setText("");
    }

}


*/
