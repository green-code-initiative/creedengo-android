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
package io.creedengo.xml.checks.helpers;

import io.creedengo.xml.checks.XPathCheck;
import org.w3c.dom.Node;

/**
 * Checks manifest uses-permissions statement.
 * If the manifest contains the permission parameter, report an issue.
 */
public abstract class CheckPermissionsRule extends XPathCheck {

    private final String xpathManifestExpression;
    private final String errorMessage;

    protected CheckPermissionsRule(String permissionName, String errorMessage) {
        this.xpathManifestExpression = "//manifest/uses-permission/@name[. = \"" + permissionName + "\"]";
        this.errorMessage = errorMessage;
    }

    @Override
    protected String getMessage() {
        return errorMessage;
    }

    @Override
    protected String getXPathExpressionString() {
        return xpathManifestExpression;
    }

    @Override
    protected void visitNode(Node node, String message) {
        reportIssue(node, getMessage());
    }
}
