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

import org.junit.Test;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.debt.DebtRemediationFunction.Type;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinition.Param;
import org.sonar.api.server.rule.RulesDefinition.Repository;
import org.sonar.api.server.rule.RulesDefinition.Rule;

import static org.fest.assertions.Assertions.assertThat;

public class JavaRulesDefinitionTest {

    @Test
    public void test() {
        JavaRulesDefinition rulesDefinition = new JavaRulesDefinition();
        RulesDefinition.Context context = new RulesDefinition.Context();
        rulesDefinition.define(context);
        RulesDefinition.Repository repository = context.repository(Java.REPOSITORY_KEY);

        assertThat(repository.name()).isEqualTo(Java.REPOSITORY_NAME);
        assertThat(repository.language()).isEqualTo(Java.KEY);
        assertThat(repository.rules()).hasSize(JavaCheckList.getChecks().size());

        assertEnergyRuleProperties(repository);
        assertSocialRuleProperties(repository);
        assertAllRuleParametersHaveDescription(repository);
    }

    private void assertSocialRuleProperties(Repository repository) {

        Rule googleTrackerRule = repository.rule("GCI532");
        assertThat(googleTrackerRule).isNotNull();
        assertThat(googleTrackerRule.name()).isEqualTo("Privacy: Google Tracker");
        assertThat(googleTrackerRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(googleTrackerRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule trackIdRule = repository.rule("GCI534");
        assertThat(trackIdRule).isNotNull();
        assertThat(trackIdRule.name()).isEqualTo("Privacy: Tracking Id");
        assertThat(trackIdRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(trackIdRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule googleConsentRule = repository.rule("GCI533");
        assertThat(googleConsentRule).isNotNull();
        assertThat(googleConsentRule.name()).isEqualTo("GDPR: Google Consent");
        assertThat(googleConsentRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(googleConsentRule.type()).isEqualTo(RuleType.CODE_SMELL);
    }

    private void assertEnergyRuleProperties(Repository repository) {

        Rule sensorCoalesceRule = repository.rule("GCI500");
        assertThat(sensorCoalesceRule).isNotNull();
        assertThat(sensorCoalesceRule.name()).isEqualTo("Batch: Sensor Coalesce");
        assertThat(sensorCoalesceRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(sensorCoalesceRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule jobCoalesceRule = repository.rule("GCI501");
        assertThat(jobCoalesceRule).isNotNull();
        assertThat(jobCoalesceRule.name()).isEqualTo("Batch: Job Coalesce");
        assertThat(jobCoalesceRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(jobCoalesceRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule internetInTheLoopRule = repository.rule("GCI502");
        assertThat(internetInTheLoopRule).isNotNull();
        assertThat(internetInTheLoopRule.name()).isEqualTo("Bottleneck: Internet In The Loop");
        assertThat(internetInTheLoopRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(internetInTheLoopRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule wifiMulticastLockRule = repository.rule("GCI503");
        assertThat(wifiMulticastLockRule).isNotNull();
        assertThat(wifiMulticastLockRule.name()).isEqualTo("Bottleneck: Wifi Multicast Lock");
        assertThat(wifiMulticastLockRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(wifiMulticastLockRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule uncompressedDataTransmissionRule = repository.rule("GCI504");
        assertThat(uncompressedDataTransmissionRule).isNotNull();
        assertThat(uncompressedDataTransmissionRule.name()).isEqualTo("Bottleneck: Uncompressed Data Transmission");
        assertThat(uncompressedDataTransmissionRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(uncompressedDataTransmissionRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule keepScreenOnAddFlagsRule = repository.rule("GCI505");
        assertThat(keepScreenOnAddFlagsRule).isNotNull();
        assertThat(keepScreenOnAddFlagsRule.name()).isEqualTo("Idleness: Keep Screen On (addFlags)");
        assertThat(keepScreenOnAddFlagsRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(keepScreenOnAddFlagsRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule keepScreenOnSetFlagsRule = repository.rule("GCI506");
        assertThat(keepScreenOnSetFlagsRule).isNotNull();
        assertThat(keepScreenOnSetFlagsRule.name()).isEqualTo("Idleness: Keep Screen On (setFlags)");
        assertThat(keepScreenOnSetFlagsRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(keepScreenOnSetFlagsRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule keepCpuOnRule = repository.rule("GCI507");
        assertThat(keepCpuOnRule).isNotNull();
        assertThat(keepCpuOnRule.name()).isEqualTo("Idleness: Keep Cpu On");
        assertThat(keepCpuOnRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(keepCpuOnRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule durableWakeLockRule = repository.rule("GCI508");
        assertThat(durableWakeLockRule).isNotNull();
        assertThat(durableWakeLockRule.name()).isEqualTo("Idleness: Durable Wake Lock");
        assertThat(durableWakeLockRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(durableWakeLockRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule rigidAlarmRule = repository.rule("GCI509");
        assertThat(rigidAlarmRule).isNotNull();
        assertThat(rigidAlarmRule.name()).isEqualTo("Idleness: Rigid Alarm");
        assertThat(rigidAlarmRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(rigidAlarmRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule continuousRenderingRule = repository.rule("GCI510");
        assertThat(continuousRenderingRule).isNotNull();
        assertThat(continuousRenderingRule.name()).isEqualTo("Idleness: Continuous Rendering");
        assertThat(continuousRenderingRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(continuousRenderingRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule keepVoiceAwakeRule = repository.rule("GCI511");
        assertThat(keepVoiceAwakeRule).isNotNull();
        assertThat(keepVoiceAwakeRule.name()).isEqualTo("Idleness: Keep Voice Awake");
        assertThat(keepVoiceAwakeRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(keepVoiceAwakeRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule cameraLeakRule = repository.rule("GCI512");
        assertThat(cameraLeakRule).isNotNull();
        assertThat(cameraLeakRule.name()).isEqualTo("Leakage: Camera Leak");
        assertThat(cameraLeakRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(cameraLeakRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule locationLeakRule = repository.rule("GCI513");
        assertThat(locationLeakRule).isNotNull();
        assertThat(locationLeakRule.name()).isEqualTo("Leakage: Location Leak");
        assertThat(locationLeakRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(locationLeakRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule sensorManagerLeakRule = repository.rule("GCI514");
        assertThat(sensorManagerLeakRule).isNotNull();
        assertThat(sensorManagerLeakRule.name()).isEqualTo("Leakage: SensorManager Leak");
        assertThat(sensorManagerLeakRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(sensorManagerLeakRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule mediaLeakMediaRecorderLeakRule = repository.rule("GCI515");
        assertThat(mediaLeakMediaRecorderLeakRule).isNotNull();
        assertThat(mediaLeakMediaRecorderLeakRule.name()).isEqualTo("Leakage: Media Leak (MediaRecorder)");
        assertThat(mediaLeakMediaRecorderLeakRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(mediaLeakMediaRecorderLeakRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule mediaLeakMediaPlayerLeakRule = repository.rule("GCI516");
        assertThat(mediaLeakMediaPlayerLeakRule).isNotNull();
        assertThat(mediaLeakMediaPlayerLeakRule.name()).isEqualTo("Leakage: Media Leak (MediaPlayer)");
        assertThat(mediaLeakMediaPlayerLeakRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(mediaLeakMediaPlayerLeakRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule fusedLocationRule = repository.rule("GCI517");
        assertThat(fusedLocationRule).isNotNull();
        assertThat(fusedLocationRule.name()).isEqualTo("Optimized API: Fused Location");
        assertThat(fusedLocationRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(fusedLocationRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule bluetoothLowEnergyRule = repository.rule("GCI518");
        assertThat(bluetoothLowEnergyRule).isNotNull();
        assertThat(bluetoothLowEnergyRule.name()).isEqualTo("Optimized API: Bluetooth Low-Energy");
        assertThat(bluetoothLowEnergyRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(bluetoothLowEnergyRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule chargeAwarenessRule = repository.rule("GCI519");
        assertThat(chargeAwarenessRule).isNotNull();
        assertThat(chargeAwarenessRule.name()).isEqualTo("Power: Charge Awareness");
        assertThat(chargeAwarenessRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(chargeAwarenessRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule saveModeAwareness = repository.rule("GCI520");
        assertThat(saveModeAwareness).isNotNull();
        assertThat(saveModeAwareness.name()).isEqualTo("Power: Save Mode Awareness");
        assertThat(saveModeAwareness.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(saveModeAwareness.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule thriftyMotionSensorRule = repository.rule("GCI521");
        assertThat(thriftyMotionSensorRule).isNotNull();
        assertThat(thriftyMotionSensorRule.name()).isEqualTo("Sobriety: Thrifty Motion Sensor");
        assertThat(thriftyMotionSensorRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(thriftyMotionSensorRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule brightnessOverrideRule = repository.rule("GCI522");
        assertThat(brightnessOverrideRule).isNotNull();
        assertThat(brightnessOverrideRule.name()).isEqualTo("Sobriety: Brightness Override");
        assertThat(brightnessOverrideRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(brightnessOverrideRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule thriftyGeolocationMinTimeRule = repository.rule("GCI523");
        assertThat(thriftyGeolocationMinTimeRule).isNotNull();
        assertThat(thriftyGeolocationMinTimeRule.name()).isEqualTo("Sobriety: Thrifty Geolocation (minTime)");
        assertThat(thriftyGeolocationMinTimeRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(thriftyGeolocationMinTimeRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule thriftyGeolocationCriteriaRule = repository.rule("GCI524");
        assertThat(thriftyGeolocationCriteriaRule).isNotNull();
        assertThat(thriftyGeolocationCriteriaRule.name()).isEqualTo("Sobriety: Thrifty Geolocation Criteria");
        assertThat(thriftyGeolocationCriteriaRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(thriftyGeolocationCriteriaRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule thriftyBluetoothLowEnergySetAdvertiseMode = repository.rule("GCI525");
        assertThat(thriftyBluetoothLowEnergySetAdvertiseMode).isNotNull();
        assertThat(thriftyBluetoothLowEnergySetAdvertiseMode.name()).isEqualTo("Sobriety: Thrifty Bluetooth Low Energy (setAdvertiseMode)");
        assertThat(thriftyBluetoothLowEnergySetAdvertiseMode.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(thriftyBluetoothLowEnergySetAdvertiseMode.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule thriftyBluetoothLowEnergyRequestConnectionPriority = repository.rule("GCI526");
        assertThat(thriftyBluetoothLowEnergyRequestConnectionPriority).isNotNull();
        assertThat(thriftyBluetoothLowEnergyRequestConnectionPriority.name()).isEqualTo("Sobriety: Thrifty Bluetooth Low Energy (requestConnectionPriority)");
        assertThat(thriftyBluetoothLowEnergyRequestConnectionPriority.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(thriftyBluetoothLowEnergyRequestConnectionPriority.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule thriftyGeolocationMinDistanceRule = repository.rule("GCI527");
        assertThat(thriftyGeolocationMinDistanceRule).isNotNull();
        assertThat(thriftyGeolocationMinDistanceRule.name()).isEqualTo("Sobriety: Thrifty Geolocation (minDistance)");
        assertThat(thriftyGeolocationMinDistanceRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(thriftyGeolocationMinDistanceRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule vibrationFreeRule = repository.rule("GCI528");
        assertThat(vibrationFreeRule).isNotNull();
        assertThat(vibrationFreeRule.name()).isEqualTo("Sobriety: Vibration Free");
        assertThat(vibrationFreeRule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(vibrationFreeRule.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule thriftyNotification = repository.rule("GCI529");
        assertThat(thriftyNotification).isNotNull();
        assertThat(thriftyNotification.name()).isEqualTo("Sobriety: Thrifty Notification");
        assertThat(thriftyNotification.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(thriftyNotification.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule torchFree = repository.rule("GCI530");
        assertThat(torchFree).isNotNull();
        assertThat(torchFree.name()).isEqualTo("Sobriety: Torch Free");
        assertThat(torchFree.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(torchFree.type()).isEqualTo(RuleType.CODE_SMELL);

        Rule highFrameRate = repository.rule("GCI531");
        assertThat(highFrameRate).isNotNull();
        assertThat(highFrameRate.name()).isEqualTo("Sobriety: High Frame Rate");
        assertThat(highFrameRate.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(highFrameRate.type()).isEqualTo(RuleType.CODE_SMELL);
    }

    private void assertAllRuleParametersHaveDescription(Repository repository) {
        for (Rule rule : repository.rules()) {
            for (Param param : rule.params()) {
                assertThat(param.description()).as("description for " + param.key()).isNotEmpty();
            }
        }
    }
}
