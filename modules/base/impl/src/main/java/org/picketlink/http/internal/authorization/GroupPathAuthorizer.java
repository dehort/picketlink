/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.picketlink.http.internal.authorization;

import org.picketlink.Identity;
import org.picketlink.authorization.util.AuthorizationUtil;
import org.picketlink.config.http.AuthorizationConfiguration;
import org.picketlink.config.http.PathConfiguration;
import org.picketlink.idm.PartitionManager;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>A default implementation of {@link org.picketlink.http.authorization.PathAuthorizer}.</p>
 *
 * @author Pedro Igor
 */
public class GroupPathAuthorizer extends AbstractPathAuthorizer {

    @Inject
    private PartitionManager partitionManager;

    @Override
    protected boolean doAuthorize(PathConfiguration pathConfiguration, HttpServletRequest request, HttpServletResponse response) {
        AuthorizationConfiguration authorizationConfiguration = pathConfiguration.getAuthorizationConfiguration();
        String[] allowedGroups = authorizationConfiguration.getAllowedGroups();

        if (allowedGroups != null) {
            Identity identity = getIdentity();

            for (String groupName : allowedGroups) {
                if (!AuthorizationUtil.isMember(identity, this.partitionManager, groupName)) {
                    return false;
                }
            }
        }

        return true;
    }

}
