package com.carleton.a4thyearproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.DataInputStream;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button up, down, left, right;
    private Socket socket;
    //private static final int SERVERPORT = 2000;
    // private static final String SERVER_IP = "192.168.0.21";
    private String click;
    private EditText ip, port;
    private Button connect;
    private ToggleButton cameraOn;
    private String SERVERPORT;
    private String SERVER_IP;
    private WebView web;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip = (EditText) findViewById(R.id.idt);
        port = (EditText) findViewById(R.id.port);

       connect = (Button) findViewById(R.id.connect);
      // connect.setOnClickListener(Connect);

        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                String ipAddress= ip.getText().toString();
                SERVER_IP=ipAddress;

                String  Port= port.getText().toString();
                SERVERPORT=Port;

                new Thread(new ClientThread()).start();

                //DO SOMETHING! {RUN SOME FUNCTION ... DO CHECKS... ETC}
            }
        });






        up = (Button) findViewById(R.id.up);
        up.setOnClickListener(this);

        down = (Button) findViewById(R.id.down);
        down.setOnClickListener(this);

        left = (Button) findViewById(R.id.left);
        left.setOnClickListener(this);

        right = (Button) findViewById(R.id.right);
        right.setOnClickListener(this);





        web = (WebView) findViewById(R.id.web);

        cameraOn = (ToggleButton) findViewById(R.id.webon);


        cameraOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {


                    web.removeAllViews();
                    web.loadUrl("file:///android_asset/cameraoff.html"); // The toggle is disabled

                } else {





//
//
                    web.setWebViewClient(new WebViewClient());
                    web.loadUrl("http://192.168.50.188:8081/dashboard/apps/");// The toggle is enabled



                }
            }
        });









    }






    public void onClick(View v) {



        try {

            new Thread(new ClientThread()).start();

            switch (v.getId()) {


                case R.id.up: {
                    // do something for button 1 click
                    click="up";

                    break;
                }

                case R.id.down: {
                    // do something for button 1 click
                    click="down";

                    break;
                }


                case R.id.left: {
                    // do something for button 1 click
                    click="left";

                    break;
                }

                case R.id.right: {
                    // do something for button 1 click
                    click="right";

                    break;
                }
            }
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(click);
            out.flush();

        }catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }




//
//                case R.id.connect: {
//                    // do something for button 1 click
//
//
//                    new Thread(new ClientThread()).start();
//                    Toast.makeText(this, "Connected to the Server",
//                            Toast.LENGTH_LONG).show();
//                    System.out.println(SERVER_IP);
//                    break;
//                }


            }





//
//                case R.id.up: {
//                    // do something for button 1 click
//                    click="up";
//
//                    break;
//                }
//
//                case R.id.down: {
//                    // do something for button 1 click
//                    click="down";
//
//                    break;
//                }
//
//
//                case R.id.left: {
//                    // do something for button 1 click
//                    click="left";
//
//                    break;
//                }
//
//                case R.id.right: {
//                    // do something for button 1 click
//                    click="right";
//
//                    break;
//                }
//            }
//
//            PrintWriter out = new PrintWriter(new BufferedWriter(
//                    new OutputStreamWriter(socket.getOutputStream())),
//                    true);
//            out.println(click);
//            out.flush();
//
//
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//}










    class ClientThread implements Runnable {

    @Override
    public void run() {
        //SERVER_IP=ip.getText().toString();
        //SERVERPORT=port.getText().toString();




        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

            socket = new Socket(serverAddr, Integer.parseInt(SERVERPORT));









        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

}




}