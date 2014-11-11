/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.picketlink.idm.jpa.model.sample.simple;

import org.picketlink.idm.jpa.annotations.OwnerReference;
import org.picketlink.idm.jpa.annotations.RelationshipDescriptor;
import org.picketlink.idm.jpa.annotations.RelationshipMember;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author pedroigor
 */
@Entity
public class RelationshipIdentityTypeEntity implements Serializable {

    private static final long serialVersionUID = -3619372498444894118L;

    @Id
    @GeneratedValue
    private Long identifier;

    @RelationshipDescriptor
    private String descriptor;

    @RelationshipMember
    @ManyToOne
    private IdentityTypeEntity identityType;

    @OwnerReference
    @ManyToOne
    private RelationshipTypeEntity owner;

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public IdentityTypeEntity getIdentityType() {
        return identityType;
    }

    public void setIdentityType(IdentityTypeEntity identityType) {
        this.identityType = identityType;
    }

    public RelationshipTypeEntity getOwner() {
        return owner;
    }

    public void setOwner(RelationshipTypeEntity owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!getClass().isInstance(obj)) {
            return false;
        }

        RelationshipIdentityTypeEntity other = (RelationshipIdentityTypeEntity) obj;

        return getIdentifier() != null && other.getIdentifier() != null && getIdentifier().equals(other.getIdentifier());
    }

    @Override
    public int hashCode() {
        int result = getIdentifier() != null ? getIdentifier().hashCode() : 0;
        result = 31 * result + (getIdentifier() != null ? getIdentifier().hashCode() : 0);
        return result;
    }
}
