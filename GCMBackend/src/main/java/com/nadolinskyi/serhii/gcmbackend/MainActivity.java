package com.nadolinskyi.serhii.gcmbackend;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;
import com.nadolinskyi.serhii.gcmbackend.chatmessageendpoint.Chatmessageendpoint;
import com.nadolinskyi.serhii.gcmbackend.chatmessageendpoint.model.ChatMessage;
import com.nadolinskyi.serhii.gcmbackend.chatmessageendpoint.model.CollectionResponseChatMessage;

import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends Activity {


    private static final String LOG_TAG = "MainActivityGCM";
    private View.OnTouchListener registerListener;
    private View.OnTouchListener unregisterListener;
    private Button btnListMessages;

    enum State {
        REGISTERED, REGISTERING, UNREGISTERED, UNREGISTERING
    }


    private State curState = State.UNREGISTERED;

    private Chatmessageendpoint chatmessageendpoint = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();
//        registerInCloud();
        initEndPoint();




    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }

    private void initEndPoint() {


        Chatmessageendpoint.Builder endpintBuilder = new Chatmessageendpoint.Builder(
                AndroidHttp.newCompatibleTransport(),
                new JacksonFactory(),
                new HttpRequestInitializer() {
                    public void initialize(HttpRequest httpRequest) { }
                });

        chatmessageendpoint = CloudEndpointUtils.updateBuilder(endpintBuilder).build();

    }

    EditText etNickname, etMessage;
    Button btnSend;
    TextView txtChat;

    private void initViews() {

        txtChat     = (TextView) findViewById(R.id.txtvChat);
        btnSend     = (Button) findViewById(R.id.btnSend);
        etNickname  = (EditText) findViewById(R.id.etNickname);
        etMessage   = (EditText) findViewById(R.id.etMessage);
        btnListMessages   = (Button) findViewById(R.id.btnListMessages);

        btnSend.setEnabled(false);
        btnListMessages.setEnabled(false);

        btnListMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getListMessages();
            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("SendMessagesTask", "send message button clicked");

                sendMessage(etNickname.getText().toString(), etMessage.getText().toString());
            }
        });


        Button regButton = (Button) findViewById(R.id.btnRegister);

        registerListener = new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        if (GCMIntentService.PROJECT_NUMBER == null
                                || GCMIntentService.PROJECT_NUMBER.length() == 0) {
                            showDialog("Unable to register for Google Cloud Messaging. "
                                    + "Your application's PROJECT_NUMBER field is unset! You can change "
                                    + "it in GCMIntentService2.java");
                        } else {
                            updateState(State.REGISTERING);
                            try {
                                GCMIntentService.register(getApplicationContext());
                            } catch (Exception e) {
                                Log.e(RegisterActivity.class.getName(),
                                        "Exception received when attempting to register for Google Cloud "
                                                + "Messaging. Perhaps you need to set your virtual device's "
                                                + " target to Google APIs? "
                                                + "See https://developers.google.com/eclipse/docs/cloud_endpoints_android"
                                                + " for more information.", e);
                                showDialog("There was a problem when attempting to register for "
                                        + "Google Cloud Messaging. If you're running in the emulator, "
                                        + "is the target of your virtual device set to 'Google APIs?' "
                                        + "See the Android log for more details.");
                                updateState(State.UNREGISTERED);
                            }
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        return true;
                    default:
                        return false;
                }
            }
        };

        unregisterListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        updateState(State.UNREGISTERING);
                        GCMIntentService.unregister(getApplicationContext());
                        return true;
                    case MotionEvent.ACTION_UP:
                        return true;
                    default:
                        return false;
                }
            }
        };

        regButton.setOnTouchListener(registerListener);

    }

    private void getListMessages() {

        new QueryMessagesTask(this, chatmessageendpoint).execute();
    }

    private void sendMessage(String nickname, String message) {
        //TODO send message

        new SendMessagesTask(this, chatmessageendpoint, nickname, message).execute();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    /*
     * If we are dealing with an intent generated by the GCMIntentService2
     * class, then display the provided message.
     *
     *
     */

        Log.d(LOG_TAG, "Got an new Intent");
//        Log.d(LOG_TAG, intent.getAction().toString());
        if (intent.getBooleanExtra("gcmIntentServiceMessage", false)) {

        showDialog(intent.getStringExtra("message"));

            if (intent.getBooleanExtra("registrationMessage", false)) {

                if (intent.getBooleanExtra("error", false)) {
          /*
           * If we get a registration/unregistration-related error,
           * and we're in the process of registering, then we move
           * back to the unregistered state. If we're in the process
           * of unregistering, then we move back to the registered
           * state.
           */
                    if (curState == State.REGISTERING) {
                        updateState(State.UNREGISTERED);
                    } else {
                        updateState(State.REGISTERED);
                    }
                } else {
          /*
           * If we get a registration/unregistration-related success,
           * and we're in the process of registering, then we move to
           * the registered state. If we're in the process of
           * unregistering, the we move back to the unregistered
           * state.
           */
                    if (curState == State.REGISTERING) {
                        updateState(State.REGISTERED);
                    } else {
                        updateState(State.UNREGISTERED);
                    }
                }
            }
            else {
        /*
         * if we didn't get a registration/unregistration message then
         * go get the last 5 messages from app-engine
         */
                /*new QueryMessagesTask(this, chatmessageendpoint).execute();*/
                getListMessages();
            }
        }
    }

    private void registerInCloud() {

        if (GCMIntentService.PROJECT_NUMBER == null
                || GCMIntentService.PROJECT_NUMBER.length() == 0) {
            showDialog("Unable to register for Google Cloud Messaging. "
                    + "Your application's PROJECT_NUMBER field is unset! You can change "
                    + "it in GCMIntentService2.java");
            Log.e(LOG_TAG,"Unable to register for Google Cloud Messaging. "
                    + "Your application's PROJECT_NUMBER field is unset! You can change "
                    + "it in GCMIntentService.java");
        } else {
            updateState(State.REGISTERING);
            try {
                GCMIntentService.register(getApplicationContext());
            } catch (Exception e) {
                Log.e(LOG_TAG,
                        "Exception received when attempting to register for Google Cloud "
                                + "Messaging. Perhaps you need to set your virtual device's "
                                + " target to Google APIs? "
                                + "See https://developers.google.com/eclipse/docs/cloud_endpoints_android"
                                + " for more information.", e);
                showDialog("There was a problem when attempting to register for "
                        + "Google Cloud Messaging. If you're running in the emulator, "
                        + "is the target of your virtual device set to 'Google APIs?' "
                        + "See the Android log for more details.");
                updateState(State.UNREGISTERED);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        GCMIntentService.unregister(getApplicationContext());
    }


    private void showDialog(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }).show();
    }

    private void updateState(State newState) {

        Button registerButton = (Button) findViewById(R.id.btnRegister);
        switch (newState) {
            case REGISTERED:
                registerButton.setText("Unregister");
                registerButton.setOnTouchListener(unregisterListener);
                registerButton.setEnabled(true);

                btnSend.setEnabled(true);
                btnListMessages.setEnabled(true);
                break;

            case REGISTERING:
                registerButton.setText("Registering...");
                registerButton.setEnabled(false);

                btnSend.setEnabled(false);
                btnListMessages.setEnabled(false);

                break;

            case UNREGISTERED:
                registerButton.setText("Register");
                registerButton.setOnTouchListener(registerListener);
                registerButton.setEnabled(true);

                btnSend.setEnabled(false);
                btnListMessages.setEnabled(false);
                break;

            case UNREGISTERING:
                registerButton.setText("Unregistering...");
                registerButton.setEnabled(false);

                btnSend.setEnabled(false);
                btnListMessages.setEnabled(false);
                break;
        }
        
        curState = newState;
    }





    /*
   * Need to run this in background so we don't hold up the UI thread,
   * this task will ask the App Engine backend for the last 50 messages
   * sent to it
   */
    private class QueryMessagesTask
            extends AsyncTask<Void, Void, CollectionResponseChatMessage> {
        Exception exceptionThrown = null;
        Chatmessageendpoint messageEndpoint;

        public QueryMessagesTask(Activity activity, Chatmessageendpoint messageEndpoint) {
            this.messageEndpoint = messageEndpoint;
        }

        @Override
        protected CollectionResponseChatMessage doInBackground(Void... params) {
            try {
                CollectionResponseChatMessage messages =
                        messageEndpoint.listChatMessages().setLimit(10).execute();
                return messages;
            } catch (IOException e) {
                exceptionThrown = e;
                return null;
                //Handle exception in PostExecute
            }
        }

        protected void onPostExecute(CollectionResponseChatMessage messages) {
            // Check if exception was thrown
            if (exceptionThrown != null) {
                Log.e(MainActivity.class.getName(),
                        "Exception when listing Messages", exceptionThrown);
                showDialog("Failed to retrieve the last 5 messages from " +
                        "the endpoint at " + messageEndpoint.getBaseUrl() +
                        ", check log for details");
            }
            else {

                txtChat.setText("Last 5 Messages read from " +
                        messageEndpoint.getBaseUrl() + ":\n");

                if((messages!=null)&&(messages.getItems()!=null))

                    for(ChatMessage message : messages.getItems()) {


    //                    String nickname = null;
                        String nickname = message.getChatname();

                        if(TextUtils.isEmpty(nickname)){
                            nickname  = "anonymous";
                        }


                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(message.getChattimestamp());


                        txtChat.append(calendar.getTime().toString()+"<"+message.getChatname()+">"+message.getChatmessage() + "\n");

                    }
            }
        }
    }


    private class SendMessagesTask
            extends AsyncTask<Void, Void, Void> {
        Exception exceptionThrown = null;
        Chatmessageendpoint messageEndpoint;
        String message;
        String nickname;

        public SendMessagesTask(Activity activity, Chatmessageendpoint messageEndpoint, String nickname, String message) {
            this.messageEndpoint = messageEndpoint;
            this.message = message;
            this.nickname = nickname;

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                messageEndpoint.sendChatMessage(nickname, message).execute();
                Log.i("SendMessagesTask", "Trying to send message");
            } catch (IOException e) {
                exceptionThrown = e;
                //Handle exception in PostExecute
                Log.e("SendMessagesTask", e.getMessage());
            }
            return null;
        }


         @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Check if exception was thrown
            if (exceptionThrown != null) {
                Log.e("SendMessagesTask",
                        "Exception when send Messages", exceptionThrown);
                showDialog("Failed to send message " +
                        "the endpoint at " + messageEndpoint.getBaseUrl() +
                        ", check log for details");
            }
            else {

                showDialog("Message send");
                }
            }
        }
    }

