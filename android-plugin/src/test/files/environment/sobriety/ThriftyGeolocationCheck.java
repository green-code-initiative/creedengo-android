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

public final class LocationManager {

    private static final int MIN_DISTANCE = 0;
    private static final int MIN_TIME = 0;

    public void test() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                "providerString",
                0,// Noncompliant {{Location updates should be done with a time greater than 0.}}
                0,// Noncompliant {{Location updates should be done with a distance interval greater than 0.}}
                null);
        locationManager.requestLocationUpdates("",
                0,// Noncompliant {{Location updates should be done with a time greater than 0.}}
                0,// Noncompliant {{Location updates should be done with a distance interval greater than 0.}}
                null);
        locationManager.requestLocationUpdates(null,
                0,// Noncompliant {{Location updates should be done with a time greater than 0.}}
                0,// Noncompliant {{Location updates should be done with a distance interval greater than 0.}}
                null);
        locationManager.requestLocationUpdates("",
                0.f, // Noncompliant {{Location updates should be done with a time greater than 0.}}
                0.f,// Noncompliant {{Location updates should be done with a distance interval greater than 0.}}
                null);
        locationManager.requestLocationUpdates("",
                0L, // Noncompliant {{Location updates should be done with a time greater than 0.}}
                0L,// Noncompliant {{Location updates should be done with a distance interval greater than 0.}}
                null);
        locationManager.requestLocationUpdates(0,// Noncompliant {{Location updates should be done with a time greater than 0.}}
                0,// Noncompliant {{Location updates should be done with a distance interval greater than 0.}}
                null,
                null);
        locationManager.requestLocationUpdates(MIN_DISTANCE,// Noncompliant {{Location updates should be done with a time greater than 0.}}
                MIN_TIME,// Noncompliant {{Location updates should be done with a distance interval greater than 0.}}
                null,
                null);

        locationManager.requestLocationUpdates(12147483647, 12.15487265142556, null, null);
        locationManager.requestLocationUpdates(1L, 12.f, null, null);
        locationManager.requestLocationUpdates(1, 12, null, null);
        locationManager.requestLocationUpdates(null, null, null);
    }

    public Sensor getDefaultSensor(int type) {
        return null;
    }

    public void requestLocationUpdates(String provider, long minTime, float minDistance, LocationListener listener) {
    }

    public void requestLocationUpdates(@NonNull String provider, long minTime, float minDistance,
                                       @NonNull LocationListener listener, @Nullable Looper looper) {
    }

    public void requestLocationUpdates(
            @NonNull String provider,
            long minTime,
            float minDistance,
            @NonNull @CallbackExecutor Executor executor,
            @NonNull LocationListener listener) {
    }

    public void requestLocationUpdates(long minTime, float minDistance,
                                       @NonNull Criteria criteria, @NonNull LocationListener listener,
                                       @Nullable Looper looper) {
    }

    public void requestLocationUpdates(
            long minTime,
            float minDistance,
            @NonNull Criteria criteria,
            @NonNull @CallbackExecutor Executor executor,
            @NonNull LocationListener listener) {
    }

    public void requestLocationUpdates(@NonNull String provider, long minTime, float minDistance,
                                       @NonNull PendingIntent pendingIntent) {
    }

    public void requestLocationUpdates(long minTime, float minDistance,
                                       @NonNull Criteria criteria, @NonNull PendingIntent pendingIntent) {
    }

    public void requestLocationUpdates(
            @Nullable LocationRequest locationRequest,
            @NonNull LocationListener listener,
            @Nullable Looper looper) {
    }

    public void requestLocationUpdates(
            @Nullable LocationRequest locationRequest,
            @NonNull @CallbackExecutor Executor executor,
            @NonNull LocationListener listener) {
    }

    public void requestLocationUpdates(
            @Nullable LocationRequest locationRequest,
            @NonNull PendingIntent pendingIntent) {
    }
}
