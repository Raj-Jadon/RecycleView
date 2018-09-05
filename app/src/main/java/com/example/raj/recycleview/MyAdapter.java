package com.example.raj.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private List<Listitem> listitems;
    private Context context;

    MyAdapter(List <Listitem> listitems, Context context)
    {
        this.listitems = listitems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View v = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.listview_row,parent,false);
       return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position)
    {
        final Listitem listitem = listitems.get(position);
        holder.textViewHead.setClickable(true);
        holder.textViewHead.setText(listitem.getSname());
       /* holder.textViewHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Here we pass scholarid, name  and class to some activity on which user click.
                ThirdActivity.name = listitem.getSname();
                ThirdActivity.scholarid=listitem.getScholarid();
                ThirdActivity.class_id   = listitem.getClassid();

                FifthActivity.scholarid = listitem.getScholarid();
                FifthActivity.class_id   = listitem.getClassid();

                SixthActivity.scholarid = listitem.getScholarid();
                SixthActivity.class_id   = listitem.getClassid();

                SeventhActivity.class_no = listitem.getClass_no();
                EighthActivity.class_no = listitem.getClass_no();
                NinthActivity.class_id = listitem.getClassid();
                SecondActivity.onClick(v);
            }
        });*/
    }

    @Override
    public int getItemCount()
    {
        return listitems.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewHead;
        ViewHolder(View itemView)
        {
            super(itemView);
            textViewHead = itemView.findViewById(R.id.textViewHead);

        }
    }
}
//MyViewholder  Exam_Adapterclass