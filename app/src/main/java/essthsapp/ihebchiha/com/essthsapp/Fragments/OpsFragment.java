package essthsapp.ihebchiha.com.essthsapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import essthsapp.ihebchiha.com.essthsapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpsFragment extends Fragment {


    public OpsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ops, container, false);
    }

}
