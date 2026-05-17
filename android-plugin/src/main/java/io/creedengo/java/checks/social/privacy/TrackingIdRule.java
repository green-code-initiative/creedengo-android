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
package io.creedengo.java.checks.social.privacy;

import io.creedengo.java.checks.helpers.SpecificMethodCheck;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * For some use cases, it might be necessary to get a unique device identifier by a call to TelephonyManager#getDeviceId()
 * (returns IMEI on GSM, MEID for CDMA).
 * However, this raises privacy concerns and it is not recommended.
 * Alternatively, you may use android.provider.Settings.Secure.ANDROID_ID.
 */

@DeprecatedRuleKey(repositoryKey = "creedengo-android-java", ruleKey = "EC534")
@Rule(key = "GCI534")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "SPRI004")
public class TrackingIdRule extends SpecificMethodCheck {

    private static final String ERROR_MESSAGE = "Avoid using TelephonyManager#getDeviceId() due to privacy concerns.";
    private static final String METHOD_NAME = "getDeviceId";
    private static final String METHOD_OWNER_TYPE = "android.telephony.TelephonyManager";


    public TrackingIdRule() {
        super(METHOD_OWNER_TYPE, METHOD_NAME);
    }

    @Override
    public String getMessage() {
        return ERROR_MESSAGE;
    }
}
