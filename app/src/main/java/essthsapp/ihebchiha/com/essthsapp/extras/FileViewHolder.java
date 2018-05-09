package essthsapp.ihebchiha.com.essthsapp.extras;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import essthsapp.ihebchiha.com.essthsapp.R;

public class FileViewHolder extends RecyclerView.ViewHolder {

 public TextView textFileName;
public ImageView imageType;
    View itemView;

    public View getItemView() {
        return itemView;
    }

    public void setItemView(View itemView) {
        this.itemView = itemView;
    }

    public FileViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        this.textFileName = (TextView) itemView.findViewById(R.id.txtView);
        this.imageType = (ImageView) itemView.findViewById(R.id.imgView);





    }
}
