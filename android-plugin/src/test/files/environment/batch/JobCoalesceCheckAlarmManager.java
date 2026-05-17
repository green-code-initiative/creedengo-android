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

public final class AlarmManager {

    public void test() {
        Context context = new Context();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManager.class);
        long currentTime = System.currentMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        String alarmTag = "Alarm's tag";
        OnAlarmListener onAlarmListener = listener();
        Handler handler = new Handler();
        AlarmClockInfo alarmClockInfo = new AlarmClockInfo(System.currentTimeMillis(), pendingIntent);

        alarmManager.set(AlarmManager.RTC_WAKEUP, currentTime, pendingIntent); // Noncompliant {{Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.}}
        alarmManager.set(AlarmManager.RTC_WAKEUP, currentTime, alarmTag, onAlarmListener, handler); // Noncompliant {{Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.}}
        alarmManager.setAlarmClock(alarmClockInfo, pendingIntent); // Noncompliant {{Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.}}
        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, currentTime, pendingIntent); // Noncompliant {{Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.}}
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, currentTime, pendingIntent); // Noncompliant {{Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.}}
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, currentTime, alarmTag, onAlarmListener, handler); // Noncompliant {{Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.}}
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, currentTime, pendingIntent); // Noncompliant {{Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.}}
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, currentTime, 1000, pendingIntent); // Noncompliant {{Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.}}
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, currentTime, 1000, pendingIntent); // Noncompliant {{Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.}}
        alarmManager.setWindow(AlarmManager.RTC_WAKEUP, 0, 1, pendingIntent); // Noncompliant {{Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.}}
        alarmManager.setWindow(AlarmManager.RTC_WAKEUP, 0, 1, alarmTag, onAlarmListener, handler); // Noncompliant {{Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.}}
    }

    private AlarmManager set(int type, long triggerAtMillis, PendingIntent operation) {
        return null;
    }

    private AlarmManager set(int type, long triggerAtMillis, String tag, OnAlarmListener listener, Handler targetHandler) {
        return null;
    }

    private AlarmManager setAlarmClock(AlarmClockInfo info, PendingIntent operation) {
        return null;
    }

    private AlarmManager setAndAllowWhileIdle(int type, long triggerAtMillis, PendingIntent operation) {
        return null;
    }

    private AlarmManager setExact(int type, long triggerAtMillis, PendingIntent operation) {
        return null;
    }

    private AlarmManager setExact(int type, long triggerAtMillis, String tag, OnAlarmListener listener, Handler targetHandler) {
        return null;
    }

    private AlarmManager setExactAndAllowWhileIdle(int type, long triggerAtMillis, PendingIntent operation) {
        return null;
    }

    private AlarmManager setInexactRepeating(int type, long triggerAtMillis, long intervalMillis, PendingIntent operation) {
        return null;
    }

    private AlarmManager setRepeating(int type, long triggerAtMillis, long intervalMillis, PendingIntent operation) {
        return null;
    }

    private AlarmManager setWindow(int type, long triggerAtMillis, long intervalMillis, PendingIntent operation) {
        return null;
    }

    private AlarmManager setWindow(int type, long triggerAtMillis, long intervalMillis, String tag, OnAlarmListener listener, Handler targetHandler) {
        return null;
    }

    private void listener() {
        return null;
    }
}