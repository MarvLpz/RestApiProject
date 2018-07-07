package com.example.marvin.restapiapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    private List<MyList> myData;
    private List<ItemCount> countData;
    public RecyclerViewAdapter(Context con, List<MyList> _myData, List<ItemCount> _countData) {
        this.context = con;
        this.myData = _myData;
        this.countData = _countData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.frame_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.firstText.setText("JSON OBJECT " + String.valueOf(countData.get(position).getCount()));
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("SEE", "CLICKED");
                Intent intent = new Intent(context,JsonData.class);
                intent.putExtra("Title_intent",myData.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return myData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView firstText;
        FrameLayout frameLayout;
            public MyViewHolder(View itemView) {
        super(itemView);
        frameLayout = (FrameLayout) itemView.findViewById(R.id.frame_clickable_id);
        firstText = (TextView) itemView.findViewById(R.id.fl_tv_id);
    }

}
}
