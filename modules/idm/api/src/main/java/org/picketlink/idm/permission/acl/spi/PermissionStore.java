package org.picketlink.idm.permission.acl.spi;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.picketlink.idm.permission.Permission;
import org.picketlink.idm.spi.IdentityContext;

/**
 * Permission Store interface
 *
 * @author Shane Bryzak
 */
public interface PermissionStore {
    /**
     * Returns a List value containing all permissions for the specified resource.
     *
     * @param resource
     * @return
     */
    List<Permission> listPermissions(IdentityContext context, Object resource);

    /**
     * Returns a List value containing all permissions for the specified resource, having the specified operation
     *
     * @param resource
     * @param permission
     * @return
     */
    List<Permission> listPermissions(IdentityContext context, Object resource, String operation);

    /**
     * Returns a List value containing all permissions for all of the specified resource,
     * having the specified operation
     *
     * @param resources
     * @param operation
     * @return
     */
    List<Permission> listPermissions(IdentityContext context, Set<Object> resources, String operation);

    /**
     * Returns a List containing all the permissions for a resource that has not yet been loaded,
     * using the specified resource class and resource identifier value.
     *
     * @param resourceClass
     * @param identifier
     * @return
     */
    List<Permission> listPermissions(IdentityContext context, Class<?> resourceClass, Serializable identifier);

    /**
     * Returns a List containing all the permissions for a resource that has not yet been loaded,
     * using the specified resource class and resource identifier value, with the specified operation.
     *
     * @param resourceClass
     * @param identifier
     * @return
     */
    List<Permission> listPermissions(IdentityContext context, Class<?> resourceClass, Serializable identifier, String operation);

    /**
     * Grants the specified permission
     *
     * @param permission
     * @return
     */
    boolean grantPermission(IdentityContext context, Permission permission);

    /**
     * Grants all of the permissions contained in the specified List
     *
     * @param permissions
     * @return
     */
    boolean grantPermissions(IdentityContext context, List<Permission> permissions);

    /**
     * Revokes the specified permission
     *
     * @param permission
     * @return
     */
    boolean revokePermission(IdentityContext context, Permission permission);

    /**
     * Revokes all of the permissions contained in the specified List
     *
     * @param permissions
     * @return
     */
    boolean revokePermissions(IdentityContext context, List<Permission> permissions);

    /**
     * Revokes all permissions for the specified resource
     *
     * @param resource
     */
    void revokeAllPermissions(IdentityContext context, Object resource);
}
