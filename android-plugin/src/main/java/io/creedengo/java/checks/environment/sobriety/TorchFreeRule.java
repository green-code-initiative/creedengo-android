/*
 * Creedengo Android plugin - Provides rules to reduce the environmental footprint of your Android applications
 * Copyright © 2020 Green Code Initiative (contact@creedengo.io)
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
package io.creedengo.java.checks.environment.sobriety;

import io.creedengo.java.checks.helpers.constant.ArgumentValueOnMethodCheck;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.Optional;

/**
 * Check the call of the method "setTorchMode" from "android.hardware.camera2.CameraManager"
 * with the param 1 set to "true".
 */
@Rule(key = "EC530")
@DeprecatedRuleKey(repositoryKey = "creedengo-java", ruleKey = "ESOB013")
public class TorchFreeRule extends ArgumentValueOnMethodCheck {

    public TorchFreeRule() {
        super("setTorchMode", "android.hardware.camera2.CameraManager", true, 1);
    }

    @Override
    public String getMessage() {
        return "Flashlight is one of the most energy-intensive component. Don't programmatically turn it on.";
    }

    @Override
    protected void checkConstantValue(Optional<Object> optionalConstantValue, Tree reportTree, Object constantValueToCheck) {
        if (optionalConstantValue.isPresent() && (optionalConstantValue.get().equals(constantValueToCheck) || ((Boolean) optionalConstantValue.get()))) {
            reportIssue(reportTree, getMessage());
        }
    }

}
