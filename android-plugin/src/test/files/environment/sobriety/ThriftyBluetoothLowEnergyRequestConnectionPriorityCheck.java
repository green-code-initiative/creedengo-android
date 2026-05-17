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
package android.bluetooth;

public final class BluetoothGatt {

    public static final int CONNECTION_PRIORITY_LOW_POWER = 2;
    public static final int CONNECTION_PRIORITY_HIGH = 1;
    public static final int CONNECTION_PRIORITY_BALANCED = 0;
    public static final long CONNECTION_PRIORITY_BALANCED_LONG = 0L;


    public void test() {
        BluetoothGatt gatt = new BluetoothGatt();
        gatt.requestConnectionPriority(CONNECTION_PRIORITY_HIGH); // Noncompliant {{Invoking BluetoothGatt.requestConnectionPriority(CONNECTION_PRIORITY_LOW_POWER) is recommend to reduce power consumption.}}
        gatt.requestConnectionPriority(CONNECTION_PRIORITY_BALANCED); // Noncompliant {{Invoking BluetoothGatt.requestConnectionPriority(CONNECTION_PRIORITY_LOW_POWER) is recommend to reduce power consumption.}}
        gatt.requestConnectionPriority(BluetoothGatt.CONNECTION_PRIORITY_BALANCED); // Noncompliant {{Invoking BluetoothGatt.requestConnectionPriority(CONNECTION_PRIORITY_LOW_POWER) is recommend to reduce power consumption.}}
        gatt.requestConnectionPriority((int) CONNECTION_PRIORITY_BALANCED_LONG); // Noncompliant {{Invoking BluetoothGatt.requestConnectionPriority(CONNECTION_PRIORITY_LOW_POWER) is recommend to reduce power consumption.}}
        gatt.requestConnectionPriority(0); // Noncompliant {{Invoking BluetoothGatt.requestConnectionPriority(CONNECTION_PRIORITY_LOW_POWER) is recommend to reduce power consumption.}}
        gatt.requestConnectionPriority(CONNECTION_PRIORITY_LOW_POWER);
        gatt.requestConnectionPriority(2);
        gatt.requestConnectionPriority(BluetoothGatt.CONNECTION_PRIORITY_LOW_POWER);
        gatt.requestConnectionPriority((int) 2L);
    }

    public boolean requestConnectionPriority(int connectionPriority) {
        return true;
    }
}
