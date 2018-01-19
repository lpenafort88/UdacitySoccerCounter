package com.penafort.soccercounter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int homeGoals=0, homeFouls=0, awayGoals=0,awayFouls=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addEvent(final View view){
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(this);
        View mView=getLayoutInflater().inflate(R.layout.add_event_dialog,null);
        final EditText et=mView.findViewById(R.id.event_description_edit);
        final Spinner sp=mView.findViewById(R.id.events_spinner);

        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String eventType=sp.getSelectedItem().toString();
                int targetContainerId,targetGoalCounterId,targetFoulCounter;
                if(view.getId()==R.id.home_team_add_event_btn){
                    targetContainerId=R.id.home_team_events_container;
                    targetGoalCounterId=R.id.home_goal_counter;
                    targetFoulCounter=R.id.home_foul_counter;
                }else{
                    targetContainerId=R.id.away_team_events_container;
                    targetGoalCounterId=R.id.away_goal_counter;
                    targetFoulCounter=R.id.away_foul_counter;
                }
                LinearLayout parentLinearLayout=findViewById(targetContainerId);
                View mEventView=getLayoutInflater().inflate(R.layout.event,null);
                ImageView im=mEventView.findViewById(R.id.event_type_icon);
                TextView tv=mEventView.findViewById(R.id.event_description);
                TextView tv2=mEventView.findViewById(R.id.event_type);
                TextView counter=null;
                int targetDrawableId=0;
                String type="";

                switch (eventType.toUpperCase()){
                    case "GOAL":
                        counter=findViewById(targetGoalCounterId);
                        targetDrawableId=R.drawable.soccerball;
                        type="GOL";
                        break;
                    case "YELLOW":
                        counter=findViewById(targetFoulCounter);
                        targetDrawableId=R.drawable.yellowcard;
                        type="YLW";
                        break;
                    case "RED":
                        counter=findViewById(targetFoulCounter);
                        targetDrawableId=R.drawable.redcard;
                        type="RED";
                        break;
                }
                int currentCount=Integer.parseInt(counter.getText().toString());
                currentCount+=1;
                counter.setText(String.valueOf(currentCount));
                im.setImageResource(targetDrawableId);
                tv.setText(et.getText());
                tv2.setText(type);
                parentLinearLayout.addView(mEventView);
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog=mBuilder.create();
        dialog.show();

    }
}
