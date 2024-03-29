package com.michalmlynarczyk.authenticationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum WorkshopPosition {

    OWNER("ROLE_OWNER_CANDIDATE", "ROLE_OWNER"),
    MECHANIC("ROLE_MECHANIC_CANDIDATE", "ROLE_MECHANIC");

    private final String candidateRole;

    private final String fullyQualifiedRole;

    private static final Map<String, WorkshopPosition> CANDIDATE_ROLE_MAP = new HashMap<>();

    private static final Map<String, WorkshopPosition> ROLE_MAP = new HashMap<>();

    static {
        for (WorkshopPosition position : values()) {
            CANDIDATE_ROLE_MAP.put(position.getCandidateRole(), position);
            ROLE_MAP.put(position.getFullyQualifiedRole(), position);
            ROLE_MAP.put(position.getCandidateRole(), position);
        }
    }

    public static Optional<WorkshopPosition> getByCandidateRole(final String candidateRole) {
        return Optional.ofNullable(CANDIDATE_ROLE_MAP.get(candidateRole));
    }


    public static Optional<WorkshopPosition> getByRoleName(final String roleName) {
        return Optional.ofNullable(ROLE_MAP.get(roleName));
    }
}
