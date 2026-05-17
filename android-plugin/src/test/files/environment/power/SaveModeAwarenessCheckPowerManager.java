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
package android.os;

public class PowerManager {
    private static final String POWER_SERVICE = "saveMode";

    public void test() {
        PowerManager powerManager = POWER_SERVICE;
        if (powerManager.isPowerSaveMode()) { // Noncompliant {{Taking into account when the device is entering or exiting the power save mode is a good practice.}}
        }
    }

    public final bool isPowerSaveMode() {
        return true;
    }
}