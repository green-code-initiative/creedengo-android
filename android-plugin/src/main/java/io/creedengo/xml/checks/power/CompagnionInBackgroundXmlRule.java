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
package io.creedengo.xml.checks.power;

import io.creedengo.xml.checks.helpers.CheckPermissionsRule;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * Checks manifest uses-permissions statement:
 * if the permission "android.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND" is found, report an issue.
 */
@DeprecatedRuleKey(repositoryKey = "creedengo-android-xml", ruleKey = "EC543")
@Rule(key = "GCI543")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-xml", ruleKey = "EPOW002")
public class CompagnionInBackgroundXmlRule extends CheckPermissionsRule {

    private static final String PERMISSION_NAME = "android.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND";
    private static final String ERROR_MESSAGE = "Using the permission REQUEST_COMPANION_RUN_IN_BACKGROUND will have a negative effect on the device's battery life.";

    public CompagnionInBackgroundXmlRule() {
        super(PERMISSION_NAME, ERROR_MESSAGE);
    }
}
