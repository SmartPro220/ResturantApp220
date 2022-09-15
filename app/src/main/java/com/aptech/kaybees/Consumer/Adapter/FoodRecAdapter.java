package com.aptech.kaybees.Consumer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.TextViewKt;
import androidx.recyclerview.widget.RecyclerView;

import com.aptech.kaybees.Admin.Model.TasteModel;
import com.aptech.kaybees.Consumer.Model.AddToCartData;
import com.aptech.kaybees.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FoodRecAdapter extends RecyclerView.Adapter<FoodRecAdapter.ViewHolder> {

    Context context;
    ArrayList<TasteModel> tasteModelArrayList=new ArrayList<>();

    ArrayList<String> keys=new ArrayList<>();
    int noCount =1;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public FoodRecAdapter(Context context, ArrayList<TasteModel> tasteModelArrayList, ArrayList<String> keys) {
        this.context = context;
        this.tasteModelArrayList = tasteModelArrayList;
        this.keys = keys;

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("cart");

    }

    @NonNull
    @Override
    public FoodRecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_cart,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodRecAdapter.ViewHolder holder, int position) {


        String name=tasteModelArrayList.get(position).getName();
        String disPrice=tasteModelArrayList.get(position).getDisPrice();
        String desc=tasteModelArrayList.get(position).getDescription();
        String quantity=tasteModelArrayList.get(position).getQuantity();
        String price=tasteModelArrayList.get(position).getPrice();


        String id=keys.get(position);

        holder.ctitle.setText(name);
        holder.cdprice.setText("Rs "+disPrice);
        holder.cquant.setText("Quantity "+quantity);
        holder.cprice.setText("Rs "+price);


        holder.openCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBottom(name,disPrice,id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasteModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView openCart;
        TextView ctitle, cdprice,cquant,cprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            openCart=itemView.findViewById(R.id.openCart);

            ctitle=itemView.findViewById(R.id.ctitle);
            cprice=itemView.findViewById(R.id.cprice);
            cquant=itemView.findViewById(R.id.cquantity);
            cdprice=itemView.findViewById(R.id.cdprice);


        }
    }

    public void openBottom(String title,String price, String id)
    {


        noCount=1;



        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(context);

        bottomSheetDialog.setContentView(R.layout.add_bottom_sheet);


        TextView btitle=bottomSheetDialog.findViewById(R.id.btitle);
        TextView bdis=bottomSheetDialog.findViewById(R.id.bdis);


        ImageView add=bottomSheetDialog.findViewById(R.id.add);
        ImageView minus=bottomSheetDialog.findViewById(R.id.minus);
        TextView count=bottomSheetDialog.findViewById(R.id.count);
        MaterialButton mybtn=bottomSheetDialog.findViewById(R.id.myBtn);


        count.setText(noCount+"");

        int getPRice=Integer.parseInt(price);

        int total=getPRice*noCount;
        mybtn.setText("Rs "+total+" Add to cart");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noCount++;

                count.setText(noCount+"");

                int getPRice=Integer.parseInt(price);

                int total=getPRice*noCount;
                mybtn.setText("Rs "+total+" Add to cart");

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if(noCount>1)
                {

                    noCount--;
                    count.setText(noCount+"");


                    int getPRice=Integer.parseInt(price);

                    int total=getPRice*noCount;
                    mybtn.setText("Rs "+total+" Add to cart");

                }
            }
        });


        String userID= FirebaseAuth.getInstance().getUid();



        mybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mtotal=getPRice*noCount;
                AddToCartData addToCartData=new AddToCartData(title,price,String.valueOf(noCount),String.valueOf(mtotal),id,userID);

                databaseReference.push().setValue(addToCartData);

                bottomSheetDialog.dismiss();
            }
        });

        btitle.setText(title);

        bdis.setText("Rs "+price);
        bottomSheetDialog.show();

    }
}
