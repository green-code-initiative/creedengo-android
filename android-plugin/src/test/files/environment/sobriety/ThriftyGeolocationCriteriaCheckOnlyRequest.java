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
package android.location;

public class LocationManager {

    public void test() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.Location_SERVICE);
        locationManager.requestLocationUpdates("", 1, 1f, null);// Noncompliant {{You should configure a location provider (LocationManager.getBestProvider(...)) to optimize battery usage.}}
    }

    public void requestLocationUpdates(String provider, long minTimeMs, float minDistanceM, LocationListener listener) {
    }

    public void getBestProvider(Criteria criteria, Boolean bool) {
    }
}

public class Criteria {
    public static final int NO_REQUIREMENT = 0;
    public static final int POWER_LOW = 1;
    public static final int POWER_MEDIUM = 2;
    public static final int POWER_HIGH = 3;
    private int mPowerRequirement = NO_REQUIREMENT;

    public void setPowerRequirement(int requirement) {
        mPowerRequirement = requirement;
    }
}
