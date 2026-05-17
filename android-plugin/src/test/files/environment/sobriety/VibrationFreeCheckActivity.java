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
package android.app;

public final class Activity {

    public void test() {
        Service service = (Service) getSystemService((String) Sensor.VIBRATOR_SERVICE);// Noncompliant {{Avoid using the device vibrator to use less energy.}}
        Service service = (Service) getSystemService("vibrator");// Noncompliant {{Avoid using the device vibrator to use less energy.}}
        Service service = (Service) getSystemService(((String) Sensor.VIBRATOR_MANAGER_SERVICE));// Noncompliant {{Avoid using the device vibrator to use less energy.}}
        Service service = (Service) getSystemService("vibrator_manager");// Noncompliant {{Avoid using the device vibrator to use less energy.}}
    }

    private Service getSystemService(String string) {
        return null;
    }

    public final class Sensor {
        public static final String VIBRATOR_MANAGER_SERVICE = "vibrator_manager";
        public static final String VIBRATOR_SERVICE = "vibrator";
    }
}