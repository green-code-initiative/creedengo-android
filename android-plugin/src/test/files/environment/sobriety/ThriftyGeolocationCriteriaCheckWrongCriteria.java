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
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_HIGH);// Noncompliant {{You should set the power requirement to POWER_LOW to optimize battery usage.}}
        locationManager.getBestProvider(null, true);
        locationManager.requestLocationUpdates("", 1, 1f, null);
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
