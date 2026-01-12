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

import io.creedengo.xml.checks.XPathSimpleCheck;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * Checks manifest intent-filter statement: if the action is ACTION_POWER_CONNECTED, ACTION_POWER_DISCONNECTED,
 * BATTERY_OKAY or BATTERY_LOW report a good practice.
 */
@Rule(key = "EC545")
@DeprecatedRuleKey(repositoryKey = "ecocode-xml", ruleKey = "EPOW005")
public class ChargeAwarenessXmlRule extends XPathSimpleCheck {

    private String xPathActionPowerConnected = "//manifest/application/receiver/intent-filter/action/@name[.=\"android.intent.action.ACTION_POWER_CONNECTED\"]";
    private String xPathActionPowerDisconnected = "//manifest/application/receiver/intent-filter/action/@name[.=\"android.intent.action.ACTION_POWER_DISCONNECTED\"]";
    private String xPathActionBatteryOkay = "//manifest/application/receiver/intent-filter/action/@name[.=\"android.intent.action.BATTERY_OKAY\"]";
    private String xPathActionBatteryLow = "//manifest/application/receiver/intent-filter/action/@name[.=\"android.intent.action.BATTERY_LOW\"]";

    @Override
    protected String getMessage() {
        return "Monitoring power changes and customizing behavior depending on battery level is a good practice.";
    }

    @Override
    protected String getXPathExpressionString() {
        return xPathActionPowerConnected + "|" + xPathActionPowerDisconnected + "|" + xPathActionBatteryOkay + "|" + xPathActionBatteryLow;
    }
}
