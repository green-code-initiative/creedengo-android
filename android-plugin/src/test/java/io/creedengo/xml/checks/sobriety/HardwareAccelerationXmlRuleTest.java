package io.creedengo.xml.checks.sobriety;

import org.junit.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

public class HardwareAccelerationXmlRuleTest {

    @Test
    public void detectExplicitOrImplicitHardwareAcceleration() {
        SonarXmlCheckVerifier.verifyIssues("HardwareAccelerationCheck.xml", new HardwareAccelerationXmlRule());
    }
}