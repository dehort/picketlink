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
import org.picketlink.common.reflection.Reflections;
import org.picketlink.config.http.AuthorizationConfiguration;
import org.picketlink.config.http.PathConfiguration;
import org.picketlink.idm.model.Partition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.picketlink.authorization.util.AuthorizationUtil.hasPartition;

/**
 * <p>A default implementation of {@link org.picketlink.http.authorization.PathAuthorizer}.</p>
 *
 * @author Pedro Igor
 */
public class RealmPathAuthorizer extends AbstractPathAuthorizer {

    @Override
    public boolean doAuthorize(PathConfiguration pathConfiguration, HttpServletRequest request, HttpServletResponse response) {
        AuthorizationConfiguration authorizationConfiguration = pathConfiguration.getAuthorizationConfiguration();
        String[] allowedRealms = authorizationConfiguration.getAllowedRealms();

        if (allowedRealms == null) {
            return true;
        }

        Identity identity = getIdentity();

        for (String realmName : allowedRealms) {
            if (hasPartition(identity, Partition.class, realmName)) {
                return true;
            }

            try {
                Class<Object> partitionType = Reflections.classForName(realmName);

                if (hasPartition(identity, partitionType, null)) {
                    return true;
                }
            } catch (Exception ignore) {
            }
        }

        return false;
    }
}
