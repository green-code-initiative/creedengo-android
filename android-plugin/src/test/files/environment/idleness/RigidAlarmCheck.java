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

public class AlarmManager {

    public void test() {
        AlarmManager alarm = new AlarmManager();
        alarm.setExact(1, 1, null); // Noncompliant {{Using exact alarms unnecessarily reduces the OS's ability to minimize battery use (i.e. Doze Mode).}}
        alarm.setExact(1, 1, "tag", null, null); // Noncompliant {{Using exact alarms unnecessarily reduces the OS's ability to minimize battery use (i.e. Doze Mode).}}
        alarm.setExactAndAllowWhileIdle(1, 1, null); // Noncompliant {{Using exact alarms unnecessarily reduces the OS's ability to minimize battery use (i.e. Doze Mode).}}
        alarm.setRepeating(1, 1, 1, ""); // Noncompliant {{Using exact alarms unnecessarily reduces the OS's ability to minimize battery use (i.e. Doze Mode).}}
    }

    public AlarmManager() {
    }

    public void setExact(@AlarmType int type, long triggerAtMillis, PendingIntent operation) {
    }

    public void setExact(@AlarmType int type, long triggerAtMillis, String tag, OnAlarmListener listener, Handler targetHandler) {
    }

    public void setExactAndAllowWhileIdle(@AlarmType int type, long triggerAtMillis, PendingIntent operation) {
    }

    public void setRepeating(int alarmType, long triggerAtMillis, long intervalMillis, Object operation) {
    }

}
