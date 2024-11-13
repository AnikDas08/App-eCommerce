package com.example.firebaseinsert;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends FirebaseRecyclerAdapter<ModelClass,MyAdapter.myViewHolder> {


    public MyAdapter(@NonNull FirebaseRecyclerOptions<ModelClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder myViewHolder,int i, @NonNull ModelClass dataHolder) {


        myViewHolder.name.setText(dataHolder.getName());
        myViewHolder.contact.setText(dataHolder.getContact());
        myViewHolder.roll.setText(dataHolder.getRoll());
        myViewHolder.course.setText(dataHolder.getCourse());
        Glide.with(myViewHolder.img.getContext())
                .load(dataHolder.getPimage())
                .into(myViewHolder.img);

        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(myViewHolder.name.getContext());
                builder.setTitle("Are Your Sure");
                builder.setMessage("Delete data cann't be Undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        FirebaseDatabase.getInstance().getReference().child("students")
                                .child(getRef(i).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(myViewHolder.name.getContext(), "Cancle", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(myViewHolder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.updapte_work))
                        .setExpanded(true,1200)
                        .create();

                View view1=dialogPlus.getHolderView();
                EditText name=view1.findViewById(R.id.UpdateName);
                EditText contact=view1.findViewById(R.id.UpdateContact);
                EditText roll=view1.findViewById(R.id.UpdateRolls);
                EditText course=view1.findViewById(R.id.UpdateCourse);
                EditText imageurl=view1.findViewById(R.id.UpdateImage);

                Button update=view1.findViewById(R.id.UpdateButtonId);

                name.setText(dataHolder.getName());
                contact.setText(dataHolder.getContact());
                roll.setText(dataHolder.getRoll());
                course.setText(dataHolder.getCourse());
                imageurl.setText(dataHolder.getPimage());

                dialogPlus.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map=new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("course",course.getText().toString());
                        map.put("roll",roll.getText().toString());
                        map.put("contact",contact.getText().toString());
                        map.put("pimage",imageurl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("students")
                                .child(getRef(i).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(myViewHolder.name.getContext(), "Successfully Update", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(myViewHolder.name.getContext(), "Data are not update", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.simple_layout,viewGroup,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name,contact,roll,course;
        ImageView imageView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.ImageViewId);
            name=itemView.findViewById(R.id.NameId);
            roll=itemView.findViewById(R.id.RollsId);
            contact=itemView.findViewById(R.id.ContactId);
            course=itemView.findViewById(R.id.CourseId);

            imageView=itemView.findViewById(R.id.Delete);
        }
    }
}
