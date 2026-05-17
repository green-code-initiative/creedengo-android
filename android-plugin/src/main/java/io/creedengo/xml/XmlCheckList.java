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
package io.creedengo.xml;

import io.creedengo.xml.checks.batch.ServiceBootTimeXmlRule;
import io.creedengo.xml.checks.power.ChargeAwarenessXmlRule;
import io.creedengo.xml.checks.power.SaveModeAwarenessXmlRule;
import io.creedengo.xml.checks.sobriety.DarkUIBrightColorsXmlRule;
import io.creedengo.xml.checks.idleness.KeepCpuOnXmlRule;
import io.creedengo.xml.checks.idleness.KeepScreenOnXmlRule;
import io.creedengo.xml.checks.sobriety.DarkUIThemeXmlRule;
import io.creedengo.xml.checks.power.CompagnionInBackgroundXmlRule;
import io.creedengo.xml.checks.power.IgnoreBatteryOptimizationsXmlRule;
import io.creedengo.xml.checks.sobriety.HardwareAccelerationXmlRule;

import java.util.Arrays;
import java.util.List;

public class XmlCheckList {

    private XmlCheckList() {
    }

    public static List<Class<?>> getXmlChecks() {
        return Arrays.asList(
                DarkUIThemeXmlRule.class,
                KeepScreenOnXmlRule.class,
                DarkUIBrightColorsXmlRule.class,
                IgnoreBatteryOptimizationsXmlRule.class,
                KeepCpuOnXmlRule.class,
                CompagnionInBackgroundXmlRule.class,
                ChargeAwarenessXmlRule.class,
                ServiceBootTimeXmlRule.class,
                SaveModeAwarenessXmlRule.class,
                HardwareAccelerationXmlRule.class
        );
    }

}
