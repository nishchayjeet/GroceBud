package com.example.grocebud;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(@NonNull View itemView) {

        super(itemView);
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClicklistener.onlongclick(view,getAdapterPosition());
                return false;
            }
        });
    }

    public void setData(Context context, String name, String asile){
        TextView textView = itemView.findViewById(R.id.textview_row);

        textView.setText("Name: "+name+"\n" + "Asile Number : "+asile);
    }
    private ViewHolder.clicklistner mClicklistener;
    public interface clicklistner{
        void onlongclick(View view,int position);
    }
    public void setonClickListener(ViewHolder.clicklistner clicklistner){
        mClicklistener = clicklistner;
    }
}
