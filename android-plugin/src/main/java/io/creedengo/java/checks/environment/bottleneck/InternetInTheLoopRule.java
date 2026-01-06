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
package io.creedengo.java.checks.environment.bottleneck;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.List;

/**
 * Check if the method openConnection of the Url class is called inside a loop.
 * Not thrown if openConnection() call is deported into an other method.
 */
@Rule(key = "EC502")
@DeprecatedRuleKey(repositoryKey = "creedengo-java", ruleKey = "EBOT001")
public class InternetInTheLoopRule extends IssuableSubscriptionVisitor {

    private static final String ERROR_MESSAGE = "Internet connection should not be opened in loops to preserve the battery.";
    private static final String METHOD_NAME = "openConnection";
    private static final String METHOD_OWNER_TYPE = "java.net.URL";
    private static final MethodMatchers METHOD_MATCHER = MethodMatchers.create().ofTypes(METHOD_OWNER_TYPE).names(METHOD_NAME).withAnyParameters().build();

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree) {
        MethodInvocationTree mit = (MethodInvocationTree) tree;
        if (METHOD_MATCHER.matches(mit)) {
            reportIssueIfInLoop(mit, mit);
        }
    }

    private void reportIssueIfInLoop(Tree treeToCheck, Tree issueTree) {
        if (treeToCheck.is(Tree.Kind.FOR_STATEMENT)
                || treeToCheck.is(Tree.Kind.WHILE_STATEMENT)
                || treeToCheck.is(Tree.Kind.DO_STATEMENT)
                || treeToCheck.is(Tree.Kind.FOR_EACH_STATEMENT)) {
            // Tree is in a loop, report issue
            reportIssue(issueTree, ERROR_MESSAGE);
        } else if (treeToCheck.is(Tree.Kind.COMPILATION_UNIT)) {
            // Top of the tree, exit
        } else {
            // parent is not a loop, continue on next parent
            reportIssueIfInLoop(treeToCheck.parent(), issueTree);
        }
    }
}
