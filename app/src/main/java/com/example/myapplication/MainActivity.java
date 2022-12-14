package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.yalantis.library.Koloda;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private SwipeAdapter adapter;
    private List<Integer> list;
    Koloda koloda; //זה Class של הItem שאותו אנחנו מחליקים
    ArrayAdapter arrayAdapter;

    private static final String TAG = "Swipe Position";
    private float x1, x2, y1, y2;
    private static int MIN_DISTANCE = 150;
    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        koloda = findViewById(R.id.koloda);
        list = new ArrayList<>();           //מקשר את ההחלקה של ההצעות עבודה לMainActivity
        adapter = new SwipeAdapter(this, list);
        koloda.setAdapter(adapter);

        this.gestureDetector = new GestureDetector(MainActivity.this, this);

        SwipeFlingAdapterView swipeFlingAdapterView=(SwipeFlingAdapterView) findViewById(R.id.koloda);
        arrayAdapter=new ArrayAdapter<Integer>(this,R.layout.item_swipe, R.id.image,list);
        swipeFlingAdapterView.setAdapter(arrayAdapter);
        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o)
            {

                Toast.makeText(MainActivity.this, "Right is swiped", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Right Swipe");

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float v) {

            }
        });

    }

    @Override
    public boolean onTouchEvent( MotionEvent koloda1)
    {
        gestureDetector.onTouchEvent(koloda1);
        switch (koloda1.getAction())
        {
            //starting to swipe time gesture
            case MotionEvent.ACTION_DOWN:
                x1 = koloda1.getX();
                y1 = koloda1.getY();
                break;

            //ending time swipe gesture
            case MotionEvent.ACTION_UP:
                x2 = koloda1.getX();
                y2 = koloda1.getY();

                //getting value for horizontal swipe
                float valueX = x2 - x1;

                //getting value for vertical swipe
                float valueY = y2 - y1;

                if (Math.abs(valueX) > MIN_DISTANCE)
                {
                    //detect left to right swipe
                    if (x2 > x1)
                    {
                        Toast.makeText(this, "Right is swiped", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Right Swipe");
//                        try {
//                            String stringSenderEmail = "gil2005.ar@gmail.com";
//                            String stringReceiverEmail = "amitco654321@gmail.com";
//                            String stringPasswordSenderEmail = "pomodoro28";
//                            String stringHost = "smtp.gmail.com";
//                            Properties properties = System.getProperties();
//                            properties.put("mail.smtp.host", stringHost);
//                            properties.put("mail.smtp.port", "465");
//                            properties.put("mail.smtp.ssl.enable", "true");
//                            properties.put("mail.smtp.auth", "true");
//
//                            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
//                                @Override
//                                protected PasswordAuthentication getPasswordAuthentication() {
//                                    return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
//                                }
//                            });
//                            MimeMessage mimeMessage = new MimeMessage(session);
//
//                            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));
//
//                            mimeMessage.setSubject("Android App Email");
//                            mimeMessage.setText("Hello, I want to work for you");
//
//                            Thread thread = new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
//                                        Transport.send(mimeMessage);
//                                    } catch (MessagingException e) {           //שולח את ההודעה
//                                        e.printStackTrace();
//                                    }
//                                }
//                            });
//                            thread.start();
//                        } catch (AddressException e) {
//                            e.printStackTrace();
//                        } catch (MessagingException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
        }

        return super.onTouchEvent(koloda1);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

}