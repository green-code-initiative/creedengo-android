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

import io.creedengo.java.checks.helpers.CheckArgumentComplexTypeUtils;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.tree.*;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.List;
import java.util.Optional;

@Rule(key = "EC531")
@DeprecatedRuleKey(repositoryKey = "creedengo-java", ruleKey = "ESOB014")
public class HighFrameRateRule extends IssuableSubscriptionVisitor {

    private static final float FRAME_RATE_60 = 60.0f;

    private final MethodMatchers surfaceListenerMethodMatcher = MethodMatchers.create().
            ofTypes("android.view.Surface").names("setFrameRate").withAnyParameters().build();

    public HighFrameRateRule() {
        super();
    }

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree) {
        if (tree.is(Tree.Kind.METHOD_INVOCATION)) {
            MethodInvocationTree methodInvocationTree = (MethodInvocationTree) tree;
            if (surfaceListenerMethodMatcher.matches(methodInvocationTree) &&
                    isRefreshSixtyOrHigher(methodInvocationTree.arguments())) {
                reportIssue(methodInvocationTree, "To optimize content refresh and save energy, frame rate should be set at maximum 60Hz.");
            }
        }
    }

    /**
     * Checking if method arguments are complying to the rule
     * @param arguments Arguments of the method called
     * @return true if argument is a float under or equal 60
     */
    private boolean isRefreshSixtyOrHigher(Arguments arguments) {
        ExpressionTree firstArg = arguments.get(0);
        Optional<Object> argOptional = firstArg.asConstant();
        if (argOptional.isPresent() && firstArg.is(Tree.Kind.IDENTIFIER)) {
            Object argValue = argOptional.get();
            if (argValue instanceof Float) {
                return ((Float) argValue).floatValue() > FRAME_RATE_60;
            }
        } else if (firstArg.is(Tree.Kind.FLOAT_LITERAL)) {
            firstArg = (ExpressionTree) CheckArgumentComplexTypeUtils.getChildExpression(firstArg);
            LiteralTree lit = (LiteralTree) firstArg;
            return Float.valueOf(lit.value()) > FRAME_RATE_60;
        }
        // not compliant case
        return false;
    }
}
