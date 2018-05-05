package essthsapp.ihebchiha.com.essthsapp.Fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import essthsapp.ihebchiha.com.essthsapp.R;
import essthsapp.ihebchiha.com.essthsapp.rss.MainFragment;
import essthsapp.ihebchiha.com.essthsapp.rss.RssAdapter;
import essthsapp.ihebchiha.com.essthsapp.rss.XMLAsyncTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    private static final String ARG_PARAM2 = "param2";
    private XMLAsyncTask task=null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FeedFragment.OnFragmentInteractionListener mListener;

    public FeedFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance(String cat, String param2) {
        Bundle args = new Bundle();
        FeedFragment fragment = new FeedFragment();
        args.putString("type", cat);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRv;
        //Intializing widgets
        mRv= view.findViewById(R.id.list);
        mRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        RssAdapter adapter=new RssAdapter((RssAdapter.UrlLoader)getActivity());
        mRv.setAdapter(adapter);
        XMLAsyncTask task=new XMLAsyncTask(adapter);
        //
        task.execute(Objects.requireNonNull(getArguments()).getString("type"));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_feed, container, false);


        return rootview;
    }


    // TODO: Rename method, update argument and hook method into UI event

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FeedFragment.OnFragmentInteractionListener) {
            mListener = (FeedFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(task!=null)
            task.cancel(true);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
