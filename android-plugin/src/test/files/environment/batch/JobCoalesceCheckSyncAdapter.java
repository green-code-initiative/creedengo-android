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

public final class SyncAdapter extends AbstractThreadedSyncAdapter {

    public void test() {
        SyncAdapter syncAdapter = new SyncAdapter();
        Account account = null;
        Bundle extras = null;
        String authority = "administrator";
        ContentProviderClient contentProviderClient = null;
        SyncResult syncResult = null;

        syncAdapter.getSyncAdapterBinder(); // Noncompliant {{Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.}}
        syncAdapter.onPerformSync(account, extras, authority, contentProviderClient, syncResult); // Noncompliant {{Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.}}
    }

    @Override
    private void onPerformSync(Account account,
                               Bundle extras,
                               String authority,
                               ContentProviderClient contentProviderClient,
                               SyncResult syncResult) {
    }
}

public abstract class AbstractThreadedSyncAdapter {

    private void getSyncAdapterBinder() {
        return null;
    }

    private abstract void onPerformSync(Account account,
                                        Bundle extras,
                                        String authority,
                                        ContentProviderClient contentProviderClient,
                                        SyncResult syncResult) {
    }
}