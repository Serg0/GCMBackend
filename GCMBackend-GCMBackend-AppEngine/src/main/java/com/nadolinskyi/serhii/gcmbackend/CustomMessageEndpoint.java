package com.nadolinskyi.serhii.gcmbackend;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "custommessageendpoint", namespace = @ApiNamespace(ownerDomain = "nadolinskyi.com", ownerName = "nadolinskyi.com", packagePath = "serhii.gcmbackend"))
public class CustomMessageEndpoint {

    /**
     * This method lists all the entities inserted in datastore.
     * It uses HTTP GET method and paging support.
     *
     * @return A CollectionResponse class containing the list of all entities
     *         persisted and a cursor to the next page.
     */
    @SuppressWarnings({"unchecked", "unused"})
    @ApiMethod(name = "listCustomMessage")
    public CollectionResponse<CustomMessage> listCustomMessage(
            @Nullable @Named("cursor") String cursorString,
            @Nullable @Named("limit") Integer limit) {

        EntityManager mgr = null;
        List<CustomMessage> execute = null;

        try {
            mgr = getEntityManager();
            Query query = mgr.createQuery("select from CustomMessage as CustomMessage");
            Cursor cursor;
            if (cursorString != null && cursorString.trim().length() > 0) {
                cursor = Cursor.fromWebSafeString(cursorString);
                query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
            }

            if (limit != null) {
                query.setFirstResult(0);
                query.setMaxResults(limit);
            }

            execute = (List<CustomMessage>) query.getResultList();
            cursor = JPACursorHelper.getCursor(execute);
            if (cursor != null) cursorString = cursor.toWebSafeString();

            // Tight loop for fetching all entities from datastore and accomodate
            // for lazy fetch.
            for (CustomMessage obj : execute) ;
        } finally {
            if (mgr != null) {
                mgr.close();
            }
        }

        return CollectionResponse.<CustomMessage>builder()
                .setItems(execute)
                .setNextPageToken(cursorString)
                .build();
    }

    /**
     * This method gets the entity having primary key id. It uses HTTP GET method.
     *
     * @param id the primary key of the java bean.
     * @return The entity with primary key id.
     */
    @ApiMethod(name = "getCustomMessage")
    public CustomMessage getCustomMessage(@Named("id") Long id) {
        EntityManager mgr = getEntityManager();
        CustomMessage customMessage = null;
        try {
            customMessage = mgr.find(CustomMessage.class, id);
        } finally {
            mgr.close();
        }
        return customMessage;
    }

    /**
     * This inserts a new entity into App Engine datastore. If the entity already
     * exists in the datastore, an exception is thrown.
     * It uses HTTP POST method.
     *
     * @param customMessage the entity to be inserted.
     * @return The inserted entity.
     */
    @ApiMethod(name = "insertCustomMessage")
    public CustomMessage insertCustomMessage(CustomMessage customMessage) {
        EntityManager mgr = getEntityManager();
        try {
            if (containsCustomMessage(customMessage)) {
                throw new EntityExistsException("Object already exists");
            }
            mgr.persist(customMessage);
        } finally {
            mgr.close();
        }
        return customMessage;
    }

    private static final String API_KEY = "AIzaSyAbtZuqnl3pOCtHxAIPImxAi6x-Zj94Z9M";

    private static final DeviceInfoEndpoint endpoint = new DeviceInfoEndpoint();
    /**
     * This accepts a message and persists it in the AppEngine datastore, it
     * will also broadcast the message to upto 10 registered android devices
     * via Google Cloud Messaging
     *
     * @param message the entity to be inserted.
     * @return
     * @throws java.io.IOException
     */
    @ApiMethod(name = "sendMessage")
    public void sendMessage(@Named("nickname") String nickname, @Named("message") String message)
            throws IOException {
        Sender sender = new Sender(API_KEY);
        // create a MessageData entity with a timestamp of when it was
        // received, and persist it
        CustomMessage messageObj = new CustomMessage();
        messageObj.setMessage(nickname);
        messageObj.setName(message);
        messageObj.setTimestamp(System.currentTimeMillis());
        EntityManager mgr = getEntityManager();
        try {
            mgr.persist(messageObj);
        } finally {
            mgr.close();
        }
        // ping a max of 10 registered devices
        CollectionResponse<DeviceInfo> response = endpoint.listDeviceInfo(null,
                10);
        for (DeviceInfo deviceInfo : response.getItems()) {
            doSendViaGcm(nickname, message, sender, deviceInfo);
        }
    }

    /**
     * Sends the message using the Sender object to the registered device.
     *
     * @param message    the message to be sent in the GCM ping to the device.
     * @param nickname   the nickname to be sent in the GCM ping to the device.
     * @param sender     the Sender object to be used for ping,
     * @param deviceInfo the registration id of the device.
     * @return Result the result of the ping.
     */
    private static Result doSendViaGcm(String nickname, String message, Sender sender,
                                       DeviceInfo deviceInfo) throws IOException {
        // Trim message if needed.
        if (message.length() > 1000) {
            message = message.substring(0, 1000) + "[...]";
        }

        // This message object is a Google Cloud Messaging object, it is NOT
        // related to the MessageData class
        Message msg = new Message.Builder().addData("message", message)
                .addData("nickname",nickname).build();
        Result result = sender.send(msg, deviceInfo.getDeviceRegistrationID(),
                5);
        if (result.getMessageId() != null) {
            String canonicalRegId = result.getCanonicalRegistrationId();
            if (canonicalRegId != null) {
                endpoint.removeDeviceInfo(deviceInfo.getDeviceRegistrationID());
                deviceInfo.setDeviceRegistrationID(canonicalRegId);
                endpoint.insertDeviceInfo(deviceInfo);
            }
        } else {
            String error = result.getErrorCodeName();
            if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
                endpoint.removeDeviceInfo(deviceInfo.getDeviceRegistrationID());
            }
        }

        return result;
    }
    /**
     * This method is used for updating an existing entity. If the entity does not
     * exist in the datastore, an exception is thrown.
     * It uses HTTP PUT method.
     *
     * @param customMessage the entity to be updated.
     * @return The updated entity.
     */
    @ApiMethod(name = "updateCustomMessage")
    public CustomMessage updateCustomMessage(CustomMessage customMessage) {
        EntityManager mgr = getEntityManager();
        try {
            if (!containsCustomMessage(customMessage)) {
                throw new EntityNotFoundException("Object does not exist");
            }
            mgr.persist(customMessage);
        } finally {
            mgr.close();
        }
        return customMessage;
    }

    /**
     * This method removes the entity with primary key id.
     * It uses HTTP DELETE method.
     *
     * @param id the primary key of the entity to be deleted.
     * @return The deleted entity.
     */
    @ApiMethod(name = "removeCustomMessage")
    public CustomMessage removeCustomMessage(@Named("id") Long id) {
        EntityManager mgr = getEntityManager();
        CustomMessage customMessage = null;
        try {
            customMessage = mgr.find(CustomMessage.class, id);
            mgr.remove(customMessage);
        } finally {
            mgr.close();
        }
        return customMessage;
    }

    private boolean containsCustomMessage(CustomMessage customMessage) {
        EntityManager mgr = getEntityManager();
        boolean contains = true;
        try {
            CustomMessage item = mgr.find(CustomMessage.class, customMessage.getId());
            if (item == null) {
                contains = false;
            }
        } finally {
            mgr.close();
        }
        return contains;
    }

    private static EntityManager getEntityManager() {
        return EMF.get().createEntityManager();
    }

}
