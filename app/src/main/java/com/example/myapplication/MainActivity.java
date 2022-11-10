package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

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

public class MainActivity extends AppCompatActivity {
    private SwipeAdapter adapter;
    private List<Integer> list;
    Koloda koloda; //זה Class של הItem שאותו אנחנו מחליקים

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        koloda = findViewById(R.id.koloda);
        list = new ArrayList<>();           //מקשר את ההחלקה של ההצעות עבודה לMainActivity
        adapter = new SwipeAdapter(this, list);
        koloda.setAdapter(adapter);

    }


        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
        {                   //פעולה ששולחת אימייל
            try {
            String stringSenderEmail = "gil2005.ar@gmail.com";
            String stringReceiverEmail = "sniram210@gmail.com";
            String stringPasswordSenderEmail = "pomodoro28";
            String stringHost = "smtp.gmail.com";
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                }
            });
            MimeMessage mimeMessage = new MimeMessage(session);

                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));

                mimeMessage.setSubject("Android App Email");
                mimeMessage.setText("Hello, I want to work for you");

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Transport.send(mimeMessage);
                        } catch (MessagingException e) {           //שולח את ההודעה
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            } catch (AddressException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }



}