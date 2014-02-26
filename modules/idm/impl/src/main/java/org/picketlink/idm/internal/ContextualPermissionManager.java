package org.picketlink.idm.internal;

import static org.picketlink.idm.IDMMessages.MESSAGES;

import java.io.Serializable;
import java.util.List;

import org.picketlink.idm.IdGenerator;
import org.picketlink.idm.PermissionManager;
import org.picketlink.idm.event.EventBridge;
import org.picketlink.idm.model.Partition;
import org.picketlink.idm.permission.Permission;
import org.picketlink.idm.permission.acl.spi.PermissionHandlerPolicy;
import org.picketlink.idm.spi.StoreSelector;

/**
 * Default implementation of PermissionManager
 *
 * @author Shane Bryzak
 *
 */
public class ContextualPermissionManager extends AbstractIdentityContext implements PermissionManager {
    private final StoreSelector storeSelector;

    public ContextualPermissionManager(Partition partition, EventBridge eventBridge, IdGenerator idGenerator,
                                        PermissionHandlerPolicy permissionHandlerPolicy, StoreSelector storeSelector) {
        super(partition, eventBridge, idGenerator, permissionHandlerPolicy);
        this.storeSelector = storeSelector;
    }

    @Override
    public List<Permission> listPermissions(Object resource) {
        return storeSelector.getStoreForPermissionOperation(this).listPermissions(this, resource);
    }

    @Override
    public List<Permission> listPermissions(Class<?> resourceClass, Serializable identifier) {
        return storeSelector.getStoreForPermissionOperation(this).listPermissions(this, resourceClass, identifier);
    }

    @Override
    public List<Permission> listPermissions(Class<?> resourceClass, Serializable identifier, String operation) {
        return storeSelector.getStoreForPermissionOperation(this).listPermissions(this, resourceClass, identifier, operation);
    }

    @Override
    public List<Permission> listPermissions(Object resource, String operation) {
        return storeSelector.getStoreForPermissionOperation(this).listPermissions(this, resource, operation);
    }

    @Override
    public void grantPermission(Permission permission) {
        try {
            storeSelector.getStoreForPermissionOperation(this).grantPermission(this, permission);
        } catch (Exception e) {
            throw MESSAGES.permissionGrantFailed(permission, e);
        }
    }

    @Override
    public void grantPermissions(List<Permission> permissions) {
        try {
            storeSelector.getStoreForPermissionOperation(this).grantPermissions(this, permissions);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (Permission p : permissions) {
                sb.append(p.toString());
                sb.append(",");
            }
            sb.append("]");
            throw MESSAGES.permissionsGrantFailed(sb.toString(), e);
        }
    }

    @Override
    public void revokePermission(Permission permission) {
        try {
            storeSelector.getStoreForPermissionOperation(this).revokePermission(this, permission);
        } catch (Exception ex) {
            throw MESSAGES.permissionRevokeFailed(permission, ex);
        }
    }

    @Override
    public void revokePermissions(List<Permission> permissions) {
        try {
            storeSelector.getStoreForPermissionOperation(this).revokePermissions(this, permissions);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (Permission p : permissions) {
                sb.append(p.toString());
                sb.append(",");
            }
            sb.append("]");
            throw MESSAGES.permissionsGrantFailed(sb.toString(), e);
        }
    }

    @Override
    public void clearPermissions(Object resource) {
        try {
            storeSelector.getStoreForPermissionOperation(this).revokeAllPermissions(this, resource);
        } catch (Exception ex) {
            throw MESSAGES.permissionRevokeAllFailed(resource, ex);
        }
    }

    @Override
    public List<String> listOperations(Class<?> resourceClass) {

        // TODO Auto-generated method stub
        return null;
    }

}
