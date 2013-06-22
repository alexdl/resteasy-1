package com.staleylabs.resteasy.domain.user;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Entity representing a user with permissions in the application with various objects.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/17/13)
 * @see
 */

public class UserPermission extends User implements Serializable {

    @Id
    private String id;

    private String userId;

    private String objectId;

    private int permissionLevel;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
}
