package com.diversityhack.placespace.aggieeye.Adapters;

import android.content.Context;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.diversityhack.placespace.aggieeye.DetailsDialogManager;
import com.diversityhack.placespace.aggieeye.Font;
import com.diversityhack.placespace.aggieeye.MainActivity;
import com.diversityhack.placespace.aggieeye.Models.EventDetails;
import com.diversityhack.placespace.aggieeye.R;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

/**
 * Created by Srujan on 6/9/2017.
 */

public class Adapter_picker_events extends
    RecyclerView.Adapter<Adapter_picker_events.MyViewHolder> {

  private final LayoutInflater inflater;
  public static List<EventDetails> data = Collections.emptyList();
  private Context context;

  public Adapter_picker_events(Context context, List<EventDetails> data) {
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.data = data;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Font.setAllTextView(parent, context);
    View view = inflater.inflate(R.layout.customrow_picker_events, parent, false);
    MyViewHolder holder = new MyViewHolder(view);
    return holder;
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, final int position) {

    holder.latitude = data.get(position).getLatitude();
    holder.longitude = data.get(position).getLongitude();

    final EventDetails hospital = data.get(position);
    holder.hospitalName.setText(hospital.getName());
    holder.rootView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ((MainActivity) context).currentEvent = hospital;
        DetailsDialogManager dialogManager = new DetailsDialogManager(context);
        dialogManager.showDetailDialog(MainActivity.mainActivity, context);
      }
    });
    holder.time.setText(hospital.getTime());
    Location target = new Location("");
    target.setLatitude(hospital.getLatitude());
    target.setLongitude(hospital.getLongitude());
    DecimalFormat df = new DecimalFormat("#.#");
    if (((MainActivity) context).userLocation != null) {
      holder.distance.setText(
          "" + df.format(((MainActivity) context).userLocation.distanceTo(target) / 1609.34f)
              + " mile");
    } else {
      Location source = new Location("");
      source.setLatitude(30.618803);
      source.setLongitude(-96.337646);
      holder.distance.setText("" + df.format(source.distanceTo(target) / 1609.34f) + " mile");
    }
    holder.address.setText(hospital.getAddress());
    switch (hospital.getCategory()) {
      case FOOD:
        holder.categoryImg.setImageResource(R.drawable.ic_food);
        break;
      case SPORTS:
        holder.categoryImg.setImageResource(R.drawable.ic_sports_1);
        break;
      case STUDY:
        holder.categoryImg.setImageResource(R.drawable.ic_study);
        break;
      case COMPETITION:
        holder.categoryImg.setImageResource(R.drawable.ic_competition);
        break;
      case INFORMATION:
        holder.categoryImg.setImageResource(R.drawable.ic_sports);
        break;
      case ENTERTAINMENT:
        holder.categoryImg.setImageResource(R.drawable.ic_entertainment);
        break;
    }

    if (!hospital.isAR()) {
      holder.arText.setVisibility(View.INVISIBLE);
      holder.arImg.setVisibility(View.INVISIBLE);
    }
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {

    View      rootView;
    TextView  hospitalName;
    TextView  time;
    TextView  distance;
    double    latitude;
    double    longitude;
    TextView  address;
    ImageView categoryImg;
    ImageView arImg;
    TextView  arText;

    public MyViewHolder(View itemView) {
      super(itemView);
      rootView = itemView.findViewById(R.id.rootView);
      hospitalName = (TextView) itemView.findViewById(R.id.hospital_name);
      time = (TextView) itemView.findViewById(R.id.row_time);
      distance = (TextView) itemView.findViewById(R.id.row_distance);
      address = (TextView) itemView.findViewById(R.id.hospital_address);
      categoryImg = (ImageView) itemView.findViewById(R.id.category_image);
      arImg = (ImageView) itemView.findViewById(R.id.ar_enabled);
      arText = (TextView) itemView.findViewById(R.id.ar_text);
    }
  }
}
