package finalproject.viewpeople;

import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
/*
This displays the number of people in the dining halls,
percentage, and the gradiated bar
Created by Crystal 12/8/2018
*/


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int PROGRESS = 0x1;

    private Button mRefreshButton;

    private String[] mDiningHallName = new String[]{"Abbey", "Wilder", "Prospect", "Rocky", "Ham", "Mac" };
    private int[] mCapacity = new int[]{90, 90 ,90 ,90 ,90 ,90};

    private DatabaseReference mDatabase;

    private ProgressBar mProgress0;
    private ProgressBar mProgress1;
    private ProgressBar mProgress2;
    private ProgressBar mProgress3;
    private ProgressBar mProgress4;
    private ProgressBar mProgress5;

    //equal to the data that is taken in
    private int mProgressStatus0;
    private int mProgressStatus1;
    private int mProgressStatus2;
    private int mProgressStatus3;
    private int mProgressStatus4;
    private int mProgressStatus5;

    private TextView t0;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;


    private Handler mHandler = new Handler();

    //Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.circular, null);
    //Compat.getDrawable(null, R.drawable.circular);

    public void setProgressDrawable (Drawable d) {

        //d : the new drawable
        //d =
        //mProgress0.setProgressDrawable(getDrawable(context,R.drawable.circular));
    }

    // Set progress bar custom drawable programmatically
    // Uncomment the below line to activate it

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //res = getResources();
        //Drawable drawable = ContextCompat.getDrawable(context, R.drawable.***)
                //getDrawable(R.drawable.sad);
        //        res.getDrawable(R.drawable.myimage);

       // InputStream resource = getResources().openRawResource(R.drawable.sad);
       // Bitmap bitmap = BitmapFactory.decodeStream(resource);
       // mProgress0.setBackground(new MyRoundCornerDrawable(bitmap));


        mProgress0 = (ProgressBar) findViewById(R.id.progressBar0);
        mProgress1 = (ProgressBar) findViewById(R.id.progressBar1);
        mProgress2 = (ProgressBar) findViewById(R.id.progressBar2);
        mProgress3 = (ProgressBar) findViewById(R.id.progressBar3);
        mProgress4 = (ProgressBar) findViewById(R.id.progressBar4);
        mProgress5 = (ProgressBar) findViewById(R.id.progressBar5);

        mRefreshButton = (Button) findViewById(R.id.refresh);


        t0 = (TextView)findViewById(R.id.Abbey);
        t1 = (TextView)findViewById(R.id.Wilder);
        t2 = (TextView)findViewById(R.id.Prospect);
        t3 = (TextView)findViewById(R.id.Rocky);
        t4 = (TextView)findViewById(R.id.Ham);
        t5 = (TextView)findViewById(R.id.McGregor);

        refresh();

        // set the drawable as progress drawable
        //mProgress0.setProgressDrawable(d);
        //mProgress0.setProgressDrawable(d);
        mProgress0.setProgress(mProgressStatus0);
        t0.setText(String.valueOf(mProgressStatus0) + "%");
        mProgress1.setProgress(mProgressStatus1);
        t1.setText(String.valueOf(mProgressStatus1) + "%");
        mProgress2.setProgress(mProgressStatus2);
        t2.setText(String.valueOf(mProgressStatus2) + "%");
        mProgress3.setProgress(mProgressStatus3);
        t3.setText(String.valueOf(mProgressStatus3) + "%");
        mProgress4.setProgress(mProgressStatus4);
        t4.setText(String.valueOf(mProgressStatus4) + "%");
        mProgress5.setProgress(mProgressStatus5);
        t5.setText(String.valueOf(mProgressStatus5) + "%");


        mRefreshButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                refresh();

                new Thread(new Runnable() {
                    public void run() {
                        while (mProgressStatus0 < 100) {

                            // Update the progress bar
                            mHandler.post(new Runnable() {
                                public void run() {
                                    mProgress0.setProgress(mProgressStatus0);
                                    //t0.setText(String.valueOf(mProgressStatus0));
                                    t0.setText(String.valueOf(getPercentage(mProgressStatus0, 0)) + "%");
                                }
                            });
                        }
                    }
                }).start();

                // Start lengthy operation in a background thread
                new Thread(new Runnable() {
                    public void run() {
                        while (mProgressStatus1 < 100) {

                            // Update the progress bar
                            mHandler.post(new Runnable() {
                                public void run() {
                                    mProgress1.setProgress(mProgressStatus1);
                                    //t1.setText(String.valueOf(mProgressStatus1));
                                    t1.setText(String.valueOf(getPercentage(mProgressStatus1, 1))+ "%");
                                }
                            });
                        }
                    }
                }).start();

                new Thread(new Runnable() {
                    public void run() {
                        while (mProgressStatus2 < 100) {

                            // Update the progress bar
                            mHandler.post(new Runnable() {
                                public void run() {
                                    mProgress2.setProgress(mProgressStatus2);
                                    //t2.setText(String.valueOf(mProgressStatus2));
                                    t2.setText(String.valueOf(getPercentage(mProgressStatus2, 2)) + "%");
                                }
                            });
                        }
                    }
                }).start();

                new Thread(new Runnable() {
                    public void run() {
                        while (mProgressStatus3 < 100) {

                            // Update the progress bar
                            mHandler.post(new Runnable() {
                                public void run() {
                                    mProgress3.setProgress(mProgressStatus3);
                                    //t3.setText(String.valueOf(mProgressStatus3));
                                    t3.setText(String.valueOf(getPercentage(mProgressStatus3, 3)) + "%");
                                }
                            });
                        }
                    }
                }).start();

                new Thread(new Runnable() {
                    public void run() {
                        while (mProgressStatus4 < 100) {

                            // Update the progress bar
                            mHandler.post(new Runnable() {
                                public void run() {
                                    mProgress4.setProgress(mProgressStatus4);
                                    //t4.setText(String.valueOf(mProgressStatus4));
                                    t4.setText(String.valueOf(getPercentage(mProgressStatus4, 4)) + "%");
                                }
                            });
                        }
                    }
                }).start();

                new Thread(new Runnable() {
                    public void run() {
                        while (mProgressStatus5 < 100) {

                            // Update the progress bar
                            mHandler.post(new Runnable() {
                                public void run() {
                                    mProgress5.setProgress(mProgressStatus5);
                                    //t5.setText(String.valueOf(mProgressStatus5));
                                    t5.setText(String.valueOf(getPercentage(mProgressStatus5, 5)) + "%");
                                }
                            });
                        }
                    }
                }).start();
            }
        });

    }

    public void refresh(){

        mDatabase= FirebaseDatabase.getInstance().getReference();

        // Get a reference to our posts
        mDatabase.child(mDiningHallName[0]).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        mProgressStatus0 = ((Long) dataSnapshot.getValue()).intValue();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "load:onCancelled", databaseError.toException());
                        // ...
                    }
                });


        // Get a reference to our posts
        mDatabase.child(mDiningHallName[1]).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        mProgressStatus1 = ((Long) dataSnapshot.getValue()).intValue();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "load:onCancelled", databaseError.toException());
                        // ...
                    }
                });

        // Get a reference to our posts
        mDatabase.child(mDiningHallName[2]).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        mProgressStatus2 = ((Long) dataSnapshot.getValue()).intValue();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "load:onCancelled", databaseError.toException());
                        // ...
                    }
                });

        // Get a reference to our posts
        mDatabase.child(mDiningHallName[3]).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        mProgressStatus3 = ((Long) dataSnapshot.getValue()).intValue();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "load:onCancelled", databaseError.toException());
                        // ...
                    }
                });

        // Get a reference to our posts
        mDatabase.child(mDiningHallName[4]).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        mProgressStatus4 = ((Long) dataSnapshot.getValue()).intValue();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "load:onCancelled", databaseError.toException());
                        // ...
                    }
                });

        // Get a reference to our posts
        mDatabase.child(mDiningHallName[5]).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        mProgressStatus5 = ((Long) dataSnapshot.getValue()).intValue();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "load:onCancelled", databaseError.toException());
                        // ...
                    }
                });
    }


    public int getPercentage(int curCapacity, int diningNum){

        int totalCapacity = mCapacity[diningNum];

        return curCapacity/totalCapacity*100;
    }


}
