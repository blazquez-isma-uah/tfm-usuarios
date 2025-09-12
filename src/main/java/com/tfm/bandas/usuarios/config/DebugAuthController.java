package com.tfm.bandas.usuarios.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
class DebugAuthController {
  @GetMapping("/__debug/me")
  public Map<String,Object> me(@org.springframework.security.core.annotation.AuthenticationPrincipal org.springframework.security.oauth2.jwt.Jwt jwt){
    return jwt == null ? Map.of("auth","anonymous") : jwt.getClaims();
  }
}
