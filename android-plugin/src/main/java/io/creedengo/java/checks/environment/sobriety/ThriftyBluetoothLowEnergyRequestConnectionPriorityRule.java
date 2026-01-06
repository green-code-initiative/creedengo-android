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
package io.creedengo.java.checks.environment.sobriety;

import io.creedengo.java.checks.helpers.constant.ConstantOnMethodCheck;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * On the method `android.bluetooth.BluetoothGatt#requestConnectionPriority(int)`, reports an issue if
 * it is called with a parameter value different from `CONNECTION_PRIORITY_LOW_POWER (2)`.
 */
@Rule(key = "EC526")
@DeprecatedRuleKey(repositoryKey = "creedengo-java", ruleKey = "ESOB008")
public class ThriftyBluetoothLowEnergyRequestConnectionPriorityRule extends ConstantOnMethodCheck {

    private static final int CONNECTION_PRIORITY_LOW_POWER = 2;

    public ThriftyBluetoothLowEnergyRequestConnectionPriorityRule() {
        super("requestConnectionPriority", "android.bluetooth.BluetoothGatt", CONNECTION_PRIORITY_LOW_POWER, 0);
    }

    @Override
    public String getMessage() {
        return "Invoking BluetoothGatt.requestConnectionPriority(CONNECTION_PRIORITY_LOW_POWER) is recommend to reduce power consumption.";
    }
}
