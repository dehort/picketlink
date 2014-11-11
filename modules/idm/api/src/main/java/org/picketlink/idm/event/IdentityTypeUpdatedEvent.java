package org.picketlink.idm.event;

import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.model.IdentityType;

/**
 * <p>This event is raised whenever a new IdentityType is created.</p>
 *
 * @author Shane Bryzak
 */
public class IdentityTypeUpdatedEvent extends AbstractBaseEvent {

    private final IdentityType identityType;

    public IdentityTypeUpdatedEvent(IdentityType identityType, PartitionManager partitionManager) {
        super(partitionManager);
        this.identityType = identityType;
    }

    public IdentityType getIdentityType() {
        return this.identityType;
    }

}
