package essthsapp.ihebchiha.com.essthsapp.Fragments;


import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import essthsapp.ihebchiha.com.essthsapp.R;
import essthsapp.ihebchiha.com.essthsapp.extras.Demand;

/**
 * A simple {@link Fragment} subclass.
 */
public class DemandFragment extends Fragment {
    View rootview;
    private Button submit;
    private Spinner spinner;
    private RadioButton ar,fr;
    private TextView result;
    private static Socket socket;
    public DemandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_demand, container, false);
        spinner=rootview.findViewById(R.id.spin);
        ar=rootview.findViewById(R.id.arBtn);
        fr=rootview.findViewById(R.id.frBtn);
        result=rootview.findViewById(R.id.result);
        submit=rootview.findViewById(R.id.submitBtn);

        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<String>();
                list.add("شهادة عمل");
                list.add("شهادة حضور");
                list.add("مخطط عمل");
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()),
                        android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);
            }
        });

       fr.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               List<String> list = new ArrayList<String>();
               list.add("Certificat de Présence");
               list.add("Certificat de Travail");
               list.add("Plan de Travail");
               ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()),
                       android.R.layout.simple_spinner_item, list);
               dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               spinner.setAdapter(dataAdapter);
           }
       });
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String fname=preferences.getString("fName","-1");
        final String lname=preferences.getString("lName","-1");
        final String cin=preferences.getString("cin","0");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Demand demand=new Demand("192.168.1.7",25000,fname+" "+lname+" "+"("+cin+")"+"demande "+ spinner.getSelectedItem().toString());
                    demand.execute();
                }

        });

        return rootview;
    }

}
