package com.nadolinskyi.serhii.gcmbackend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

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
