package com.nadolinskyi.serhii.gcmbackend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;
import com.nadolinskyi.serhii.gcmbackend.messageEndpoint.MessageEndpoint;
import com.nadolinskyi.serhii.gcmbackend.messageEndpoint.model.CollectionResponseMessageData;
import com.nadolinskyi.serhii.gcmbackend.messageEndpoint.model.MessageData;

import java.io.IOException;
import java.util.Date;

public class MainActivity extends Activity {


    enum State {
        REGISTERED, REGISTERING, UNREGISTERED, UNREGISTERING
    }

    private State curState = State.UNREGISTERED;
    private MessageEndpoint messageEndpoint = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerInCloud();
        initViews();
        initEndPoint();



/*        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);*/
    //some changes
        //some changes2


    }

    private void initEndPoint() {

        MessageEndpoint.Builder endpointBuilder = new MessageEndpoint.Builder(
                AndroidHttp.newCompatibleTransport(),
                new JacksonFactory(),
                new HttpRequestInitializer() {
                    public void initialize(HttpRequest httpRequest) { }
                });

        messageEndpoint = CloudEndpointUtils.updateBuilder(endpointBuilder).build();

    }

    EditText etNickname, etMessage;
    Button btnSend;
    TextView txtChat;

    private void initViews() {

        txtChat     = (TextView) findViewById(R.id.txtvChat);
        btnSend     = (Button) findViewById(R.id.btnSend);
        etNickname  = (EditText) findViewById(R.id.etNickname);
        etMessage   = (EditText) findViewById(R.id.etNickname);

        txtChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendMessage(etNickname.getText().toString(), etMessage.getText().toString());
            }
        });


    }

    private void sendMessage(String nickname, String message) {
        //TODO send message
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    /*
     * If we are dealing with an intent generated by the GCMIntentService
     * class, then display the provided message.
     */
        if (intent.getBooleanExtra("gcmIntentServiceMessage", false)) {

//      showDialog(intent.getStringExtra("message"));

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
                new QueryMessagesTask(this, messageEndpoint).execute();
            }
        }
    }

    private void registerInCloud() {

        if (GCMIntentService.PROJECT_NUMBER == null
                || GCMIntentService.PROJECT_NUMBER.length() == 0) {
            showDialog("Unable to register for Google Cloud Messaging. "
                    + "Your application's PROJECT_NUMBER field is unset! You can change "
                    + "it in GCMIntentService.java");
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
        curState = newState;
    }





    /*
   * Need to run this in background so we don't hold up the UI thread,
   * this task will ask the App Engine backend for the last 5 messages
   * sent to it
   */
    private class QueryMessagesTask
            extends AsyncTask<Void, Void, CollectionResponseMessageData> {
        Exception exceptionThrown = null;
        MessageEndpoint messageEndpoint;

        public QueryMessagesTask(Activity activity, MessageEndpoint messageEndpoint) {
            this.messageEndpoint = messageEndpoint;
        }

        @Override
        protected CollectionResponseMessageData doInBackground(Void... params) {
            try {
                CollectionResponseMessageData messages =
                        messageEndpoint.listMessages().setLimit(50).execute();
                return messages;
            } catch (IOException e) {
                exceptionThrown = e;
                return null;
                //Handle exception in PostExecute
            }
        }

        protected void onPostExecute(CollectionResponseMessageData messages) {
            // Check if exception was thrown
            if (exceptionThrown != null) {
                Log.e(RegisterActivity.class.getName(),
                        "Exception when listing Messages", exceptionThrown);
                showDialog("Failed to retrieve the last 5 messages from " +
                        "the endpoint at " + messageEndpoint.getBaseUrl() +
                        ", check log for details");
            }
            else {

                txtChat.setText("Last 5 Messages read from " +
                        messageEndpoint.getBaseUrl() + ":\n");
                for(MessageData message : messages.getItems()) {

                    String nickname = null;
//                    String nickname = message.getNickname();

                    if(TextUtils.isEmpty(nickname)){
                        nickname  = "anonymous";
                    }

                    Date date = new Date(message.getTimestamp());
                    txtChat.append(date.getHours()+":"+date.getMinutes()+"<"+message.getMessage()+">" + "\n");
                }
            }
        }
    }
}
