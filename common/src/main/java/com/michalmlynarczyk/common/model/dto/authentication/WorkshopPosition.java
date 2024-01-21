package com.michalmlynarczyk.common.model.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WorkshopPosition {

    OWNER("ROLE_OWNER_CANDIDATE", "ROLE_OWNER"),
    MECHANIC("ROLE_MECHANIC_CANDIDATE", "ROLE_MECHANIC");

    private final String candidateRole;

    private final String fullyQualifiedRole;
}
