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
package android.content;

public class IntentFilter {
    private static final String ACTION_BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";
    private static final String PING_ALARM_ACTION = "android.intent.action.PING_ALARM";
    private String mAction = "";

    public void test() {
        IntentFilter filter3 = new IntentFilter(ACTION_BATTERY_CHANGED); // Noncompliant {{Taking into account when the device is entering or exiting the power save mode is a good practice.}}
        IntentFilter filter4 = new IntentFilter(PING_ALARM_ACTION);

        IntentFilter.create(ACTION_BATTERY_CHANGED, "");// Noncompliant {{Taking into account when the device is entering or exiting the power save mode is a good practice.}}
        IntentFilter.create("foo", "application/blatz");

        filter3.addAction(ACTION_BATTERY_CHANGED);// Noncompliant {{Taking into account when the device is entering or exiting the power save mode is a good practice.}}
        filter4.addAction("android.provider.Telephony.SMS_RECEIVED");

    }

    public IntentFilter(String str) {
        mAction = str;
    }

    public final void addAction(String action) {
        if (!mActions.contains(action)) {
            mActions.add(action.intern());
        }
    }

    public static IntentFilter create(String action, String dataType) {
        try {
            return new IntentFilter(action);
        } catch (MalformedMimeTypeException e) {
            throw new RuntimeException("Bad MIME type", e);
        }
    }
}