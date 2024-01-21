package com.michalmlynarczyk.common.model.dto.auth;

import java.util.UUID;

public record CustomAuthenticationPrincipal(UUID userId, String email) {
}
