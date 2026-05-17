package io.creedengo.java.checks.social.gdpr;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class GoogleAndroidAdsConsentRuleTest {
    @Test
    public void verify() {

        CheckVerifier.newVerifier().onFile("src/test/files/social/privacy/GoogleTrackerGoogleCheck.java")
                .withCheck(new GoogleAndroidAdsConsentRule())
                .verifyNoIssues();
        CheckVerifier.newVerifier().onFile("src/test/files/social/gdpr/GoogleAndroidAdsConsentCheck.java")
                .withCheck(new GoogleAndroidAdsConsentRule())
                .verifyIssueOnProject("Good Smell : User Consent");


    }
}
