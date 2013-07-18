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
package org.picketlink.test.idm.model.complex.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.picketlink.idm.jpa.annotations.AttributeValue;
import org.picketlink.idm.jpa.annotations.OwnerReference;
import org.picketlink.idm.jpa.annotations.entity.IdentityManaged;
import org.picketlink.idm.model.sample.Group;

/**
 * Contains group-specific attribute values
 *
 * @author Shane Bryzak
 */
@Entity
@IdentityManaged(Group.class)
public class GroupAttribute implements Serializable {
    private static final long serialVersionUID = 5125034753443642890L;

    @Id
    @OneToOne
    @OwnerReference
    private IdentityObject identity;

    @AttributeValue(name = "name")
    private String groupName;

    @AttributeValue
    private String path;

    @ManyToOne
    @AttributeValue (name = "parentGroup")
    private IdentityObject parent;

    public IdentityObject getIdentity() {
        return identity;
    }

    public void setIdentity(IdentityObject identity) {
        this.identity = identity;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public IdentityObject getParent() {
        return parent;
    }

    public void setParent(IdentityObject parent) {
        this.parent = parent;
    }
}
