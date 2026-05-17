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
package io.creedengo.java.checks.environment.sobriety;

import io.creedengo.java.checks.helpers.constant.ArgumentValueOnMethodCheck;
import io.creedengo.java.checks.helpers.constant.MethodSpecs;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.Optional;

@DeprecatedRuleKey(repositoryKey = "creedengo-android-java", ruleKey = "EC528")
@Rule(key = "GCI528")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "ESOB011")
public class VibrationFreeRule extends ArgumentValueOnMethodCheck {

    private static final String SYSTEM_SERVICE_METHOD = "getSystemService";

    public VibrationFreeRule() {
        super(new MethodSpecs[]{
                new MethodSpecs(SYSTEM_SERVICE_METHOD, "android.content.Context", "vibrator", 0),
                new MethodSpecs(SYSTEM_SERVICE_METHOD, "android.content.Context", "vibrator_manager", 0),
                new MethodSpecs(SYSTEM_SERVICE_METHOD, "android.app.Activity", "vibrator", 0),
                new MethodSpecs(SYSTEM_SERVICE_METHOD, "android.app.Activity", "vibrator_manager", 0)
        });
    }

    @Override
    public String getMessage() {
        return "Avoid using the device vibrator to use less energy.";
    }

    @Override
    protected void checkConstantValue(Optional<Object> optionalConstantValue, Tree reportTree, Object constantValueToCheck) {
        if (optionalConstantValue.isPresent() && optionalConstantValue.get().equals(constantValueToCheck)) {
            reportIssue(reportTree, getMessage());
        }
    }

}
