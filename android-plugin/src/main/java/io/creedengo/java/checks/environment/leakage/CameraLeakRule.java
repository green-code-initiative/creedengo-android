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
package io.creedengo.java.checks.environment.leakage;

import io.creedengo.java.checks.helpers.OpeningClosingMethodCheck;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * Check that Camera#release() method is closed after Camera#open().
 *
 * @see OpeningClosingMethodCheck
 */
@DeprecatedRuleKey(repositoryKey = "creedengo-android-java", ruleKey = "EC512")
@Rule(key = "GCI512")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "ELEA002")
public class CameraLeakRule extends OpeningClosingMethodCheck {

    public CameraLeakRule() {
        super("open", "release", "android.hardware.Camera");
    }

    @Override
    public String getMessage() {
        return "Failing to call android.hardware.Camera#release() can drain the battery in just a few hours.";
    }
}
