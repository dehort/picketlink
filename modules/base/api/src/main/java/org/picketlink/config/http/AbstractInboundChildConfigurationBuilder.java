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
package org.picketlink.config.http;

/**
 * @author Pedro Igor
 */
public class AbstractInboundChildConfigurationBuilder extends AbstractPathConfigurationChildBuilder implements InboundConfigurationChildBuilder {

    private final InboundConfigurationChildBuilder builder;

    AbstractInboundChildConfigurationBuilder(InboundConfigurationChildBuilder builder) {
        super(builder);
        this.builder = builder;
    }

    @Override
    public AuthenticationConfigurationBuilder authc() {
        return this.builder.authc();
    }

    @Override
    public AuthorizationConfigurationBuilder authz() {
        return this.builder.authz();
    }

    @Override
    public LogoutConfigurationBuilder logout() {
        return this.builder.logout();
    }

    @Override
    public InboundHeaderConfigurationBuilder headers() {
        return this.builder.headers();
    }

    @Override
    public InboundConfigurationBuilder methods(String... methods) {
        return this.builder.methods(methods);
    }
}
