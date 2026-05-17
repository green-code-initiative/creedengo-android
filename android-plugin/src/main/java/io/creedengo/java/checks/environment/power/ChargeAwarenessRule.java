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
package io.creedengo.java.checks.environment.power;

import java.util.List;
import java.util.Optional;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.tree.Arguments;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.NewClassTree;
import org.sonar.plugins.java.api.tree.Tree;

import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * Look for `android.content.IntentFilter` constructor declaration or call to `IntentFilter.addAction()` or `
 * IntentFilter.create()` methods.
 * If the first argument is one of the String `android.intent.action.ACTION_POWER_DISCONNECTED`,
 * android.intent.action.ACTION_POWER_CONNECTED`, `android.intent.action.BATTERY_LOW` or
 * android.intent.action.BATTERY_OKAY` reports a (positive) issue.
 */
@DeprecatedRuleKey(repositoryKey = "creedengo-android-java", ruleKey = "EC519")
@Rule(key = "GCI519")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "EPOW004")
public class ChargeAwarenessRule extends IssuableSubscriptionVisitor {
    private static final Logger LOG = Loggers.get(ChargeAwarenessRule.class);

    private static final String ACTION_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED";
    private static final String ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED";
    private static final String ACTION_POWER_BATTERY_LOW = "android.intent.action.BATTERY_LOW";
    private static final String ACTION_POWER_BATTERY_OKAY = "android.intent.action.BATTERY_OKAY";
    private static final String INTENT_FILTER = "android.content.IntentFilter";
    private static final String INFO_MESSAGE = "Monitoring power changes and customizing behavior depending on battery level is a good practice.";
    private final MethodMatchers addActionOrIntentFilterMatcher = MethodMatchers.or(
            MethodMatchers.create().ofTypes(INTENT_FILTER).names("addAction").withAnyParameters().build(),
            MethodMatchers.create().ofTypes(INTENT_FILTER).names("create").withAnyParameters().build());

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.NEW_CLASS, Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree) {
        super.visitNode(tree);
        try {
            if (tree.is(Tree.Kind.NEW_CLASS)) {
                NewClassTree nct = (NewClassTree) tree;
                if (nct.symbolType().fullyQualifiedName().equals(INTENT_FILTER)) {
                    checkParameter(nct.arguments());
                }
            }
            if (tree.is(Tree.Kind.METHOD_INVOCATION)) {
                MethodInvocationTree mit = (MethodInvocationTree) tree;
                if (addActionOrIntentFilterMatcher.matches(mit)) {
                    checkParameter(mit.arguments());
                }
            }
        } catch (Exception e) {
            LOG.error("Error in visitNode : {}", e.getMessage(), e);
        }
    }

    private void checkParameter(Arguments arguments) {
        if (!arguments.isEmpty()) {
            ExpressionTree firstArgument = arguments.get(0);
            Optional<Object> optionalFirstArgument = firstArgument.asConstant();
            if (firstArgument.symbolType().toString().equals("String")
                    && optionalFirstArgument.isPresent()
                    && ((optionalFirstArgument.get()).equals(ACTION_POWER_CONNECTED)
                    || (optionalFirstArgument.get()).equals(ACTION_POWER_DISCONNECTED)
                    || (optionalFirstArgument.get()).equals(ACTION_POWER_BATTERY_OKAY)
                    || (optionalFirstArgument.get()).equals(ACTION_POWER_BATTERY_LOW))) {
                reportIssue(firstArgument, INFO_MESSAGE);
            }
        }
    }
}
