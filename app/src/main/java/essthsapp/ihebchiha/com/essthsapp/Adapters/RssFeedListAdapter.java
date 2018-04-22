package essthsapp.ihebchiha.com.essthsapp.Adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import essthsapp.ihebchiha.com.essthsapp.Models.RssFeedModel;
import essthsapp.ihebchiha.com.essthsapp.R;


public class RssFeedListAdapter
        extends RecyclerView.Adapter<RssFeedListAdapter.FeedModelViewHolder> {

    private List<RssFeedModel> mRssFeedModels;

    static class FeedModelViewHolder extends RecyclerView.ViewHolder {
        private View rssFeedView;

        FeedModelViewHolder(View v) {
            super(v);
            rssFeedView = v;
        }
    }

    public RssFeedListAdapter(List<RssFeedModel> rssFeedModels) {
        mRssFeedModels = rssFeedModels;
    }

    @NonNull
    @Override
    public FeedModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell, parent, false);
        return new FeedModelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedModelViewHolder holder, int position) {
        final RssFeedModel rssFeedModel = mRssFeedModels.get(position);
        ((TextView)holder.rssFeedView.findViewById(R.id.titleText)).setText(rssFeedModel.title);
        ((TextView)holder.rssFeedView.findViewById(R.id.descriptionText)).setText(rssFeedModel.description);
        ((TextView)holder.rssFeedView.findViewById(R.id.linkText)).setText(rssFeedModel.link);
    }

    @Override
    public int getItemCount() {
        return mRssFeedModels.size();
    }
}

