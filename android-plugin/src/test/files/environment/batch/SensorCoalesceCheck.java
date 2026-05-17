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

        final int positiveNumber = 200000;
        final long positiveNumberL = 200000L;
        final int zeroNumber = 0;
        final long zeroNumberL = 0L;
        final int negativeNumber = -1;
        final long negativeNumberL = -1L;

        SensorManager sensorManager = null;
        SensorEventListener sensorEventListener = null;
        Sensor gyroscope = null;
        Handler handler = null;

        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL); // Noncompliant {{Prefer using a reported latency on your SensorManager to reduce the power consumption of the app}}
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL, handler); // Noncompliant {{Prefer using a reported latency on your SensorManager to reduce the power consumption of the app}}

        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL, 0); // Noncompliant {{Prefer using a reported latency on your SensorManager to reduce the power consumption of the app}}
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL, (int) 0L); // Noncompliant {{Prefer using a reported latency on your SensorManager to reduce the power consumption of the app}}
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL, -1); // Noncompliant {{Prefer using a reported latency on your SensorManager to reduce the power consumption of the app}}
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL, (int) -1L); // Noncompliant {{Prefer using a reported latency on your SensorManager to reduce the power consumption of the app}}
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL, 200000);
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL, (int) 200000L);
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL, 0, handler); // Noncompliant {{Prefer using a reported latency on your SensorManager to reduce the power consumption of the app}}
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL, (int) 0L, handler); // Noncompliant {{Prefer using a reported latency on your SensorManager to reduce the power consumption of the app}}
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL, -1, handler); // Noncompliant {{Prefer using a reported latency on your SensorManager to reduce the power consumption of the app}}
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL, (int) -1L, handler); // Noncompliant {{Prefer using a reported latency on your SensorManager to reduce the power consumption of the app}}
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL, 200000, handler);
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL, (int) 200000L, handler);

        // TODO: 22/02/2022 Variables need a symbolic expression search (need to update the sonarjava plugin) 
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, zeroNumber); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, (int)zeroNumberL); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, negativeNumber); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, (int)negativeNumberL); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, positiveNumber);
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, (int)positiveNumberL);
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, zeroNumber, handler); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, (int)zeroNumberL, handler); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, negativeNumber, handler); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, (int)negativeNumberL, handler); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, positiveNumber, handler);
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, (int)positiveNumberL, handler);
//
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, zeroNumberG); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, (int)zeroNumberLG); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, negativeNumberG); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, (int)negativeNumberLG); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, positiveNumberG);
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, (int)positiveNumberLG);
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, zeroNumberG, handler); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, (int)zeroNumberLG, handler); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, negativeNumberG, handler); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, (int)negativeNumberLG, handler); // TODO Noncompliant
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, positiveNumberG, handler);
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL, (int)positiveNumberLG, handler);
    }

    private int SENSOR_DELAY_NORMAL = 210000;

    private SensorManager registerListener(SensorEventListener sensorEventListener, Sensor gyroscope, int delay) {
        return null;
    }

    private SensorManager registerListener(SensorEventListener sensorEventListener, Sensor gyroscope, int delay, Handler handler) {
        return null;
    }

    private SensorManager registerListener(SensorEventListener sensorEventListener, Sensor gyroscope, int delay, int latency) {
        return null;
    }

    private SensorManager registerListener(SensorEventListener sensorEventListener, Sensor gyroscope, int delay, int latency, Handler handler) {
        return null;
    }
}