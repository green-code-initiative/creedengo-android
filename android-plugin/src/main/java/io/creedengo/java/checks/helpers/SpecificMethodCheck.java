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
package io.creedengo.java.checks.helpers;

import com.google.common.collect.ImmutableList;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.List;

public abstract class SpecificMethodCheck extends IssuableSubscriptionVisitor {

    private final MethodMatchers methodMatcher;

    /**
     * Constructor to configure the rule on a given class and method.
     *
     * @param methodOwnerType name of the type that own the method
     * @param methodNames     name of the methods to check
     */
    protected SpecificMethodCheck(String methodOwnerType, String... methodNames) {
        super();
        this.methodMatcher = MethodMatchers.create().ofTypes(methodOwnerType).names(methodNames).withAnyParameters().build();
    }

    /**
     * Message to report on the issue.
     *
     * @return Message to report on the issue.
     */
    public abstract String getMessage();

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree) {
        if (tree.is(Tree.Kind.METHOD_INVOCATION)) {
            MethodInvocationTree mit = (MethodInvocationTree) tree;
            if (methodMatcher.matches(mit)) {
                reportIssue(mit, getMessage());
            }
        }
    }
}