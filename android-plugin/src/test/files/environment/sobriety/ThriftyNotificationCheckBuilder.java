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

public final class Notification {

    public void test() {
        Notification.Builder notificationBuilder = new Notification.Builder();
        notificationBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});// Noncompliant {{Avoid using vibration or sound when notifying the users to use less energy.}}
        notificationBuilder.setVibrate(null);
        notificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), Notification.AUDIO_ATTRIBUTES_DEFAULT);// Noncompliant {{Avoid using vibration or sound when notifying the users to use less energy.}}
        notificationBuilder.setSound(null, Notification.AUDIO_ATTRIBUTES_DEFAULT);// Noncompliant {{Avoid using vibration or sound when notifying the users to use less energy.}}
        notificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));// Noncompliant {{Avoid using vibration or sound when notifying the users to use less energy.}}
        notificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), Notification.STREAM_DEFAULT);// Noncompliant {{Avoid using vibration or sound when notifying the users to use less energy.}}
        notificationBuilder.setSound(null, Notification.STREAM_DEFAULT);// Noncompliant {{Avoid using vibration or sound when notifying the users to use less energy.}}
        notificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null);// Noncompliant {{Avoid using vibration or sound when notifying the users to use less energy.}}
        notificationBuilder.setSound(null);
        notificationBuilder.setSound(null, null);
    }

    public static class Builder {

        private Builder setVibrate(long[] longueur) {
            return null;
        }

        private Builder setSound(Uri soundUrl, AudioAttributes audio) {
            return null;
        }

        private Builder setSound(Uri soundUrl) {
            return null;
        }

        private Builder setSound(Uri soundUrl, int streamType) {
            return null;
        }

    }
}