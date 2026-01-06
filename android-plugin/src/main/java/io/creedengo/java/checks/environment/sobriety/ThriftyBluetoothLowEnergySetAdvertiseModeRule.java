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
 * On the method `android.bluetooth.le.AdvertiseSettings$Builder#setAdvertiseMode(int)`, report an issue if
 * it is called with parameter value different from `ADVERTISE_MODE_LOW_POWER (0)`.
 */
@Rule(key = "EC525")
@DeprecatedRuleKey(repositoryKey = "creedengo-java", ruleKey = "ESOB007")
public class ThriftyBluetoothLowEnergySetAdvertiseModeRule extends ConstantOnMethodCheck {

    private static final int CONSTANT_POWER_LOW = 0;

    public ThriftyBluetoothLowEnergySetAdvertiseModeRule() {
        super("setAdvertiseMode", "android.bluetooth.le.AdvertiseSettings$Builder", CONSTANT_POWER_LOW, 0);
    }

    @Override
    public String getMessage() {
        return "You should call AdvertiseSettings.Builder.setAdvertiseMode(ADVERTISE_MODE_LOW_POWER) to optimize battery usage.";
    }
}
