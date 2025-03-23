package io.github.arthurhoch.purevalue.software;

import io.github.arthurhoch.purevalue.software.git.GitCommitSHA;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GitCommitSHA.
 */
class GitCommitSHATest {

    @Test
    void shouldAcceptValidSHA1() {
        // Example valid SHA-1 commit: 40 hex characters.
        GitCommitSHA sha = GitCommitSHA.of("73c0aafe12b609326ea292f393854c3bd4ef5620");
        assertEquals("73c0aafe12b609326ea292f393854c3bd4ef5620", sha.value());
    }

    @Test
    void shouldAcceptValidSHA256() {
        // Example valid SHA-256 commit: 64 hex characters.
        GitCommitSHA sha = GitCommitSHA.of("0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef");
        assertEquals("0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef", sha.value());
    }

    @Test
    void shouldRejectInvalidGitCommitSHA() {
        assertThrows(IllegalArgumentException.class, () -> GitCommitSHA.of("12345"));
    }

    @Test
    void shouldFormatCorrectly() {
        assertEquals("673acd1419f6e27ebe6534093d1adead3afba33b",
                GitCommitSHA.of("673acd1419f6e27ebe6534093d1adead3afba33b").format());
    }
}
