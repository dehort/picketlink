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
package org.picketlink.http.test.authorization;

import org.junit.Test;
import org.picketlink.annotations.PicketLink;
import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;
import org.picketlink.idm.model.basic.Realm;
import org.picketlink.http.test.AbstractSecurityFilterTestCase;
import org.picketlink.test.weld.Deployment;
import org.picketlink.http.test.SecurityInitializer;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Pedro Igor
 */
@Deployment(
    beans = {
        RealmBasedAuthorizationTestCase.SecurityConfiguration.class, SecurityInitializer.class
    },
    excludeBeansFromPackage = "org.picketlink.http.test"
)
public class RealmBasedAuthorizationTestCase extends AbstractSecurityFilterTestCase {

    @Inject
    @PicketLink
    private Instance<HttpServletRequest> picketLinkRequest;

    @Override
    public void onBefore() throws Exception {
        super.onBefore();

        this.credentials.setUserId("picketlink");
        this.credentials.setPassword("picketlink");

        this.identity.login();

        this.credentials.setCredential(null);
    }

    @Test
    public void testOnlyDefaultRealm() throws Exception {
        when(this.request.getRequestURI()).thenReturn("/onlyDefaultRealmName");

        this.securityFilter.doFilter(this.request, this.response, this.filterChain);

        verify(this.filterChain, times(1)).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

        assertEquals("/onlyDefaultRealmName", picketLinkRequest.get().getRequestURI());
    }

    @Test
    public void testOnlyDefaultRealmByType() throws Exception {
        when(this.request.getRequestURI()).thenReturn("/onlyDefaultRealmType");

        this.securityFilter.doFilter(this.request, this.response, this.filterChain);

        verify(this.filterChain, times(1)).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));
        assertEquals("/onlyDefaultRealmType", picketLinkRequest.get().getRequestURI());
    }

    public static class SecurityConfiguration {
        public void configureHttpSecurity(@Observes SecurityConfigurationEvent event) {
            SecurityConfigurationBuilder builder = event.getBuilder();
            builder
                .http()
                .path("/onlyDefaultRealmName")
                .inbound()
                .authz()
                .allowedRealms(Realm.DEFAULT_REALM)
                .path("/onlyDefaultRealmType")
                .inbound()
                .authz()
                .allowedRealms(Realm.class.getName())
                .path("/onlyAcmeRealmName")
                .inbound()
                .authz()
                .allowedRealms("Acme");
        }
    }
}
