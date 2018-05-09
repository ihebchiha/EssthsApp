package essthsapp.ihebchiha.com.essthsapp.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import essthsapp.ihebchiha.com.essthsapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DemandFragment extends Fragment {
    View rootview;
    private Button submit;
    private Spinner spinner;
    private RadioButton ar,fr;
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Item Selected: "+spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return rootview;
    }

}
