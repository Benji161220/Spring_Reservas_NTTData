package org.nttdata.spring.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private final JwtUtil jwtUtil = new JwtUtil(
            "NTTDataReservasSecretKey2024MustBe256BitsLongForHS256Algorithm", 86400000L);

    @Test
    void generateAndValidateToken() {
        String token = jwtUtil.generateToken("test@test.com", "usuario");

        assertNotNull(token);
        assertTrue(jwtUtil.validateToken(token));
        assertEquals("test@test.com", jwtUtil.getEmailFromToken(token));
    }

    @Test
    void validateToken_invalid() {
        assertFalse(jwtUtil.validateToken("invalid-token"));
    }

    @Test
    void validateToken_null() {
        assertFalse(jwtUtil.validateToken(null));
    }
}
