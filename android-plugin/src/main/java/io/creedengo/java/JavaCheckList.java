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
package io.creedengo.java;

import io.creedengo.java.checks.environment.batch.JobCoalesceRule;
import io.creedengo.java.checks.environment.batch.SensorCoalesceRule;
import io.creedengo.java.checks.environment.bottleneck.InternetInTheLoopRule;
import io.creedengo.java.checks.environment.bottleneck.UncompressedDataTransmissionRule;
import io.creedengo.java.checks.environment.bottleneck.WifiMulticastLockRule;
import io.creedengo.java.checks.environment.idleness.*;
import io.creedengo.java.checks.environment.leakage.*;
import io.creedengo.java.checks.environment.optimized_api.BluetoothLowEnergyRule;
import io.creedengo.java.checks.environment.optimized_api.FusedLocationRule;
import io.creedengo.java.checks.environment.power.ChargeAwarenessRule;
import io.creedengo.java.checks.environment.power.SaveModeAwarenessRule;
import io.creedengo.java.checks.environment.sobriety.*;
import io.creedengo.java.checks.social.gdpr.GoogleAndroidAdsConsentRule;
import io.creedengo.java.checks.social.privacy.GoogleTrackerRule;
import io.creedengo.java.checks.social.privacy.TrackingIdRule;
import org.sonar.plugins.java.api.JavaCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class JavaCheckList {

    private JavaCheckList() {
    }

    public static List<Class<? extends JavaCheck>> getChecks() {
        List<Class<? extends JavaCheck>> checks = new ArrayList<>();
        checks.addAll(getJavaEnergyChecks());
        checks.addAll(getJavaSocialChecks());
        checks.addAll(getJavaTestChecks());
        return Collections.unmodifiableList(checks);
    }

    public static List<Class<? extends JavaCheck>> getJavaSocialChecks() {
        return Collections.unmodifiableList(Arrays.asList(
                GoogleTrackerRule.class,
                TrackingIdRule.class,
                GoogleAndroidAdsConsentRule.class
        ));
    }

    public static List<Class<? extends JavaCheck>> getJavaEnergyChecks() {
        return Collections.unmodifiableList(Arrays.asList(
                FusedLocationRule.class,
                BluetoothLowEnergyRule.class,
                KeepScreenOnAddFlagsRule.class,
                KeepScreenOnSetFlagsRule.class,
                BrightnessOverrideRule.class,
                InternetInTheLoopRule.class,
                KeepCpuOnRule.class,
                ThriftyMotionSensorRule.class,
                WifiMulticastLockRule.class,
                LocationLeakRule.class,
                CameraLeakRule.class,
                SensorManagerLeakRule.class,
                DurableWakeLockRule.class,
                MediaLeakMediaRecorderRule.class,
                MediaLeakMediaPlayerRule.class,
                RigidAlarmRule.class,
                ContinuousRenderingRule.class,
                KeepVoiceAwakeRule.class,
                ThriftyBluetoothLowEnergySetAdvertiseModeRule.class,
                ThriftyBluetoothLowEnergyRequestConnectionPriorityRule.class,
                ThriftyGeolocationMinTimeRule.class,
                ThriftyGeolocationMinDistanceRule.class,
                ChargeAwarenessRule.class,
                VibrationFreeRule.class,
                TorchFreeRule.class,
                ThriftyNotificationRule.class,
                UncompressedDataTransmissionRule.class,
                SensorCoalesceRule.class,
                JobCoalesceRule.class,
                SaveModeAwarenessRule.class,
                ThriftyGeolocationCriteriaRule.class,
                HighFrameRateRule.class
        ));
    }

    public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
        return Collections.unmodifiableList(Arrays.asList(
        ));
    }
}
