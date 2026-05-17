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
package io.creedengo.java.checks.environment.idleness;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.List;

/**
 * Check the call of the method "acquire" of "android.os.PowerManager$WakeLock".
 * Reports an issue if found without any parameters.
 */
@DeprecatedRuleKey(repositoryKey = "creedengo-android-java", ruleKey = "EC508")
@Rule(key = "GCI508")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "EIDL006")
public class DurableWakeLockRule extends IssuableSubscriptionVisitor {
    private String methodOwnerType = "android.os.PowerManager$WakeLock";
    private String methodName = "acquire";
    private static final String ERROR_MESSAGE = "Prefer setting a timeout when acquiring a wake lock to avoid running down the device's battery excessively.";
    private MethodMatchers matcher = MethodMatchers.create().ofTypes(methodOwnerType).names(methodName).addWithoutParametersMatcher().build();

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree) {
        MethodInvocationTree mit = (MethodInvocationTree) tree;
        if (matcher.matches(mit)) {
            reportIssue(mit, ERROR_MESSAGE);
        }
    }
}
