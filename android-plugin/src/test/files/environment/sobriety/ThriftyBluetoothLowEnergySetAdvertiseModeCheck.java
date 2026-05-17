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
package android.bluetooth.le;

public final class AdvertiseSettings {

    public static final int ADVERTISE_MODE_LOW_POWER = 0;
    public static final int ADVERTISE_MODE_BALANCED = 1;
    public static final int ADVERTISE_MODE_LOW_LATENCY = 2;
    public static final long ADVERTISE_MODE_LOW_LATENCY_LONG = 2L;

    public void test() {
        Builder builder = new Builder();
        builder.setAdvertiseMode(ADVERTISE_MODE_LOW_LATENCY); // Noncompliant {{You should call AdvertiseSettings.Builder.setAdvertiseMode(ADVERTISE_MODE_LOW_POWER) to optimize battery usage.}}
        builder.setAdvertiseMode(ADVERTISE_MODE_BALANCED); // Noncompliant {{You should call AdvertiseSettings.Builder.setAdvertiseMode(ADVERTISE_MODE_LOW_POWER) to optimize battery usage.}}
        builder.setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_BALANCED); // Noncompliant {{You should call AdvertiseSettings.Builder.setAdvertiseMode(ADVERTISE_MODE_LOW_POWER) to optimize battery usage.}}
        builder.setAdvertiseMode((int) ADVERTISE_MODE_LOW_LATENCY_LONG); // Noncompliant {{You should call AdvertiseSettings.Builder.setAdvertiseMode(ADVERTISE_MODE_LOW_POWER) to optimize battery usage.}}
        builder.setAdvertiseMode(1); // Noncompliant {{You should call AdvertiseSettings.Builder.setAdvertiseMode(ADVERTISE_MODE_LOW_POWER) to optimize battery usage.}}
        builder.setAdvertiseMode(ADVERTISE_MODE_LOW_POWER);
        builder.setAdvertiseMode(0);
        builder.setAdvertiseMode((int) 0L);
    }

    public static final class Builder {
        public Builder() {
        }

        public Builder setAdvertiseMode(int advertiseMode) {
            return new Builder();
        }
    }
}
