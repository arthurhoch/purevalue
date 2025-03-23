package io.github.arthurhoch.purevalue.software;

import io.github.arthurhoch.purevalue.software.semver.SemanticVersion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SemanticVersion.
 */
class SemanticVersionTest {

    @Test
    void shouldAcceptValidSemanticVersion() {
        SemanticVersion semVer = SemanticVersion.of("1.0.0");
        assertEquals("1.0.0", semVer.value());

        semVer = SemanticVersion.of("2.1.3-alpha.1+exp.sha.5114f85");
        assertEquals("2.1.3-alpha.1+exp.sha.5114f85", semVer.value());
    }

    @Test
    void shouldRejectInvalidSemanticVersion() {
        assertThrows(IllegalArgumentException.class, () -> SemanticVersion.of("1.0"));
        assertThrows(IllegalArgumentException.class, () -> SemanticVersion.of("1.0.0.0"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("1.0.0", SemanticVersion.of(" 1.0.0 ").format());
    }
}
