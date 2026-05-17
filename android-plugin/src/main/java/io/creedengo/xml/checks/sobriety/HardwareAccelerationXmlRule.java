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
package io.creedengo.xml.checks.sobriety;

import io.creedengo.xml.checks.XPathSimpleCheck;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;
import org.w3c.dom.Node;

@DeprecatedRuleKey(repositoryKey = "creedengo-android-xml", ruleKey = "EC549")
@Rule(key = "GCI549", name = "Hardware acceleration", priority = Priority.MAJOR)
@DeprecatedRuleKey(repositoryKey = "ecocode-android-xml", ruleKey = "ESOB016")
public class HardwareAccelerationXmlRule extends XPathSimpleCheck {

    private static final String ERROR_MESSAGE_ENABLED =
            "Hardware acceleration is enabled. Consider disabling it to reduce RAM usage.";

    private static final String ERROR_MESSAGE_IMPLICIT =
            "Hardware acceleration is implicitly enabled. Consider disabling it explicitly with android:hardwareAccelerated=\"false\".";

    private static final String ANDROID_HARDWARE_ACCELERATED = "android:hardwareAccelerated";
    private static final String XPATH_TARGET_COMPONENTS = "//application | //activity | //window | //view";

    @Override
    protected String getMessage() {
        return ERROR_MESSAGE_IMPLICIT;
    }

    @Override
    protected String getXPathExpressionString() {
        return XPATH_TARGET_COMPONENTS;
    }

    @Override
    protected void visitNode(Node node, String message) {
        Node hardwareAttributeNode = getHardwareAccelerationAttribute(node);

        if ("true".equals(getNodeValue(hardwareAttributeNode))) {
            reportIssue(hardwareAttributeNode, ERROR_MESSAGE_ENABLED);
        }
        else if (!isHardwareAccelerationDisabledInParent(node)) {
            reportIssue(node, ERROR_MESSAGE_IMPLICIT);
        }
    }

    private boolean isHardwareAccelerationDisabledInParent(Node node) {
        Node parent = node;

        while (parent != null && !"application".equals(parent.getNodeName())) {
            parent = parent.getParentNode();
        }

        if (parent == null) {
            return false;
        }

        Node parentHardwareAttr = getHardwareAccelerationAttribute(parent);
        return "false".equals(getNodeValue(parentHardwareAttr));
    }

    private static Node getHardwareAccelerationAttribute(Node node) {
        if (node == null || node.getAttributes() == null) {
            return null;
        }

        return node.getAttributes().getNamedItem(ANDROID_HARDWARE_ACCELERATED);
    }

    private String getNodeValue(Node node) {
        return (node != null) ? node.getNodeValue() : null;
    }

}
