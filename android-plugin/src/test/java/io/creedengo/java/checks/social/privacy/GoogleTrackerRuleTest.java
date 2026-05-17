/*
 * Creedengo Android Java plugin - Provides rules to reduce the environmental footprint of your Android applications
 * Copyright © 2020 Green Code Initiative (contact@green-code-initiative.org)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.creedengo.java.checks.social.privacy;

import io.creedengo.java.checks.social.privacy.GoogleTrackerRule;
import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class GoogleTrackerRuleTest {

    @Test
    public void verify() {

        CheckVerifier.newVerifier().onFile("src/test/files/social/privacy/GoogleTrackerGoogleCheck.java")
                .withCheck(new GoogleTrackerRule())
                .verifyIssues();
        CheckVerifier.newVerifier().onFile("src/test/files/social/privacy/GoogleTrackerFirebaseCheck.java")
                .withCheck(new GoogleTrackerRule())
                .verifyIssues();
        CheckVerifier.newVerifier().onFile("src/test/files/social/privacy/GoogleTrackerGoogleAndFirebaseCheck.java")
                .withCheck(new GoogleTrackerRule())
                .verifyIssues();

    }
}
