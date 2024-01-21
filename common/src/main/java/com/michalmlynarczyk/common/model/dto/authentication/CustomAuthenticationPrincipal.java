package com.michalmlynarczyk.common.model.dto.authentication;

import java.util.UUID;

public record CustomAuthenticationPrincipal(UUID userId, String email) {
}
