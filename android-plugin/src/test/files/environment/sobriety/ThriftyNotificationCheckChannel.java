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

public final class NotificationChannel {

    public void test() {
        NotificationChannel notifManag = new NotificationChannel();
        notifManag.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});// Noncompliant {{Avoid using vibration or sound when notifying the users to use less energy.}}
        notifManag.setVibrationPattern(null);
        notifManag.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), Notification.AUDIO_ATTRIBUTES_DEFAULT);// Noncompliant {{Avoid using vibration or sound when notifying the users to use less energy.}}
        notifManag.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null);// Noncompliant {{Avoid using vibration or sound when notifying the users to use less energy.}}
        notifManag.setSound(null, Notification.AUDIO_ATTRIBUTES_DEFAULT);// Noncompliant {{Avoid using vibration or sound when notifying the users to use less energy.}}
        notifManag.setSound(null, null);
    }

    private NotificationChannel setVibrationPattern(long[] pattern) {
        return null;
    }

    private NotificationChannel setSound(Uri uri, Notification audio) {
        return null;
    }
}
