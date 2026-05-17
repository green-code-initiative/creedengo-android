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
package android.hardware;

public final class SensorManager {

    public void test() {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        sensorManager.getDefaultSensor(11); // Noncompliant {{Prefer using TYPE_GEOMAGNETIC_ROTATION_VECTOR instead of TYPE_ROTATION_VECTOR to use less energy.}}
        sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        sensorManager.getDefaultSensor(((int) Sensor.TYPE_ROTATION_VECTOR)); // Noncompliant {{Prefer using TYPE_GEOMAGNETIC_ROTATION_VECTOR instead of TYPE_ROTATION_VECTOR to use less energy.}}
    }

    public Sensor getDefaultSensor(int type) {
        return null;
    }
}

public final class Sensor {
    public static final int TYPE_ROTATION_VECTOR = 11;
    public static final int TYPE_GEOMAGNETIC_ROTATION_VECTOR = 20;
}
