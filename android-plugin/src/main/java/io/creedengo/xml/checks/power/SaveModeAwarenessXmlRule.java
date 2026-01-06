/*
 * Creedengo Android plugin - Provides rules to reduce the environmental footprint of your Android applications
 * Copyright © 2020 Green Code Initiative (contact@creedengo.io)
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

import io.creedengo.xml.checks.XPathSimpleCheck;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * Checks manifest uses-permissions statement:
 * if the permission "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS" is found, report an issue.
 */
@Rule(key = "EC546")
@DeprecatedRuleKey(repositoryKey = "creedengo-xml", ruleKey = "EPOW007")
public class SaveModeAwarenessXmlRule extends XPathSimpleCheck {

    private static final String ACTION_POWER_CHANGED_XPATH = "//manifest/application/receiver/intent-filter/action/@name[.=\"android.intent.action.BATTERY_CHANGED\"]";
    private static final String ADVICE_MESSAGE = "Taking into account when the device is entering or exiting the power save mode is a good practice.";

    @Override
    protected String getMessage() {
        return ADVICE_MESSAGE;
    }

    @Override
    protected String getXPathExpressionString() {
        return ACTION_POWER_CHANGED_XPATH;
    }
}
