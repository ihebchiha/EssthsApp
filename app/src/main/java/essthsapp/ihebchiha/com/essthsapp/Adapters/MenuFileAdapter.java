package essthsapp.ihebchiha.com.essthsapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import essthsapp.ihebchiha.com.essthsapp.R;
import essthsapp.ihebchiha.com.essthsapp.extras.FileItem;
import essthsapp.ihebchiha.com.essthsapp.extras.MenuViewHolder;

public class MenuFileAdapter extends RecyclerView.Adapter<MenuViewHolder>{
    private LayoutInflater inflater;
    private List<FileItem> list;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position) throws IOException;
    }
    public MenuFileAdapter(Context context, List<FileItem> list, OnItemClickListener onItemClickListener)
    {
        mOnItemClickListener=onItemClickListener;
        inflater=LayoutInflater.from(context);
        this.list=list;
    }
    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.ftpfilecell,parent,false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        FileItem current=list.get(position);
        holder.txtView.setText(current.getTxt());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mOnItemClickListener.onItemClick(v, position);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
