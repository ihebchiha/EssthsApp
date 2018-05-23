package essthsapp.ihebchiha.com.essthsapp.rss;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import essthsapp.ihebchiha.com.essthsapp.R;


public class RssAdapter extends RecyclerView.Adapter<RssAdapter.ArticleViewHolder>implements XMLAsyncTask.DocumentConsumer {
    private Document _document=null;

    public interface UrlLoader
    {
        void load(String title,String link);
    }
    private final UrlLoader _urlLoader;
    public RssAdapter(UrlLoader urlLoader){ _urlLoader=urlLoader;}
    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cell,parent,false);

        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Element item= (Element) _document.getElementsByTagName("Info").item(position);
        holder.setElement(item);
    }

    @Override
    public int getItemCount() {
        if(_document!=null)
        {return _document.getElementsByTagName("Info").getLength();}
        else return 0;
    }

    @Override
    public void setXMLDocument(Document document) {
        _document=document;
        notifyDataSetChanged();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mDesc;
        private Element _currentElement;
        public ArticleViewHolder(final View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.title);
            mDesc=itemView.findViewById(R.id.description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title=_currentElement.getElementsByTagName("Title").item(0).getTextContent();
                    String desc=_currentElement.getElementsByTagName("Description").item(0).getTextContent();
                    String link=_currentElement.getElementsByTagName("Link").item(0).getTextContent();
                    _urlLoader.load(title,link);
                 /*   final Context context=itemView.getContext();
                    Intent intent = new Intent(context,ArticleActivity.class);
                    intent.putExtra("title",title);
                    intent.putExtra("link",link);
                    context.startActivity(intent);*/
                }
            });
        }

        public void setElement(Element element)
        {
            _currentElement=element;
            mTitle.setText(element.getElementsByTagName("Title").item(0).getTextContent());
            mDesc.setText(element.getElementsByTagName("Description").item(0).getTextContent());
        }

    }

}