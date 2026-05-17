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
package io.creedengo.java.checks.environment.sobriety;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class ThriftyGeolocationCriteriaTest {

    @Test
    public void verifyOnlyRequest() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/sobriety/ThriftyGeolocationCriteriaCheckOnlyRequest.java")
                .withCheck(new ThriftyGeolocationCriteriaRule())
                .verifyIssues();
    }

    @Test
    public void verifyNoCriteria() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/sobriety/ThriftyGeolocationCriteriaCheckNoCriteria.java")
                .withCheck(new ThriftyGeolocationCriteriaRule())
                .verifyIssues();
    }

    @Test
    public void verifyWrongCriteria() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/sobriety/ThriftyGeolocationCriteriaCheckWrongCriteria.java")
                .withCheck(new ThriftyGeolocationCriteriaRule())
                .verifyIssues();
    }

    @Test
    public void verifyNoIssue() {
        CheckVerifier.newVerifier().onFile("src/test/files/environment/sobriety/ThriftyGeolocationCriteriaCheckNoIssue.java")
                .withCheck(new ThriftyGeolocationCriteriaRule())
                .verifyNoIssues();
    }
}
