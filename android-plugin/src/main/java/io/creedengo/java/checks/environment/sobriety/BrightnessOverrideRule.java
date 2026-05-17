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

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.*;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.List;
import java.util.Optional;

/**
 * Check the assignment of the variable android.view.WindowManager$LayoutParams.screenBrightness with the value
 * "BRIGHTNESS_OVERRIDE_FULL" (1.0f).
 */
@DeprecatedRuleKey(repositoryKey = "creedengo-android-java", ruleKey = "EC522")
@Rule(key = "GCI522")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "ESOB002")
public class BrightnessOverrideRule extends IssuableSubscriptionVisitor {

    private static final String ERROR_MESSAGE = "Forcing brightness to max value may cause useless energy consumption.";
    private static final String EXPRESSION_FULL_QUALIFIED_NAME = "android.view.WindowManager$LayoutParams";
    private static final String MEMBER_NAME = "screenBrightness";
    private static final int BRIGHTNESS_FULL_VALUE = 1;

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.ASSIGNMENT);
    }

    @Override
    public void visitNode(Tree tree) {
        AssignmentExpressionTree assignmentTree = (AssignmentExpressionTree) tree;
        if (isBrightnessAssignment(assignmentTree)) {
            Tree.Kind assignmentTreeKind = assignmentTree.expression().kind();
            switch (assignmentTreeKind) {
                case MEMBER_SELECT:
                    //that case check members attributes, so we need to check tree identifier's value
                    MemberSelectExpressionTree mset = (MemberSelectExpressionTree) assignmentTree.expression();
                    Optional<Object> identifierTreeConstantMset = mset.identifier().asConstant();
                    Tree initialTreeToFlag = mset.identifier();
                    if (identifierTreeConstantMset.isPresent()) {
                        checkBrightnessAssignmentExpressionValue(initialTreeToFlag,
                                (Number)identifierTreeConstantMset.get());
                    }
                    break;
                case FLOAT_LITERAL:
                    LiteralTree floatLiteralTree = (LiteralTree) assignmentTree.expression();
                    checkBrightnessAssignmentExpressionValue(floatLiteralTree,
                            Float.valueOf(floatLiteralTree.value()));
                    break;
                case INT_LITERAL:
                    LiteralTree intLiteralTree = (LiteralTree) assignmentTree.expression();
                    checkBrightnessAssignmentExpressionValue(intLiteralTree,
                            Integer.valueOf(intLiteralTree.value()));
                    break;
                case VARIABLE:
                    VariableTree variableTree = (VariableTree) assignmentTree.expression();
                    Optional<Object> variableTreeConstant = variableTree.initializer().asConstant();
                    if (variableTreeConstant.isPresent()) {
                        checkBrightnessAssignmentExpressionValue(variableTree,
                                (Number) variableTreeConstant.get());
                    }
                    break;
                case IDENTIFIER:
                    IdentifierTree identifierTree = (IdentifierTree) assignmentTree.expression();
                    Optional<Object> identifierTreeConstant = identifierTree.asConstant();
                    if (identifierTreeConstant.isPresent()) {
                        // Constant identifier only, other cases are ignored
                        checkBrightnessAssignmentExpressionValue(identifierTree,
                                (Number) identifierTreeConstant.get());
                    }
                    break;
                default:
                    // For other tree types we cannot do anything, juste ignore them
                    break;
            }
        }
    }

    /**
     * Check that the assignment is an assignment of android.view.WindowManager$LayoutParams screenBrightness element
     *
     * @param assignmentExpressionTree the assignment expression to check
     * @return true if the expression is LayoutParams.screenBrightness assignment
     */
    private boolean isBrightnessAssignment(AssignmentExpressionTree assignmentExpressionTree) {
        if (assignmentExpressionTree.variable().is(Tree.Kind.MEMBER_SELECT)) {
            MemberSelectExpressionTree mset = (MemberSelectExpressionTree) assignmentExpressionTree.variable();
            return mset.expression().symbolType().fullyQualifiedName().equals(EXPRESSION_FULL_QUALIFIED_NAME)
                    && mset.identifier().name().equals(MEMBER_NAME);
        } else {
            return false;
        }
    }

    private void checkBrightnessAssignmentExpressionValue(Tree tree, Number value) {
        int intValue = value.intValue();
        float floatValue = value.floatValue();
        if (floatValue == BRIGHTNESS_FULL_VALUE || intValue == BRIGHTNESS_FULL_VALUE) {
            reportIssue(tree, ERROR_MESSAGE);
        }
    }
}
