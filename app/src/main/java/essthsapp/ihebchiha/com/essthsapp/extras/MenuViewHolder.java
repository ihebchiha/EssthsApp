package essthsapp.ihebchiha.com.essthsapp.extras;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import essthsapp.ihebchiha.com.essthsapp.R;

public class MenuViewHolder extends RecyclerView.ViewHolder {
   public TextView txtView;
    public ImageView img;
    @SuppressLint("CutPasteId")
    public MenuViewHolder(View itemView) {
        super(itemView);

        txtView=(TextView) itemView.findViewById(R.id.filetxt);
        img=(ImageView) itemView.findViewById(R.id.dwBtn);
    }




}
