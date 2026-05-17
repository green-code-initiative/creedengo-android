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
package io.creedengo.xml.checks;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheck;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;

/**
 * Get the nodes resulting from a xPathExpression and apply @visitNode on these nodes.
 * Override @visitNode to indicate what to do on these nodes. In most of the cases, just report an issue.
 */
public abstract class XPathCheck extends SonarXmlCheck {

    private static final Logger LOG = Loggers.get(XPathCheck.class);

    /**
     * @return the message to display when the issue is raised.
     */
    protected abstract String getMessage();

    /**
     * @return the Xpath expression to look for in the XMLs
     */
    protected abstract String getXPathExpressionString();

    /**
     * Action to perform on the nodes found by the xPathExpression.
     * @param node a node returned by the xPathExpression
     * @param message the message to display if an issue must be reported
     */
    protected abstract void visitNode(Node node, String message);

    @Override
    public void scanFile(XmlFile xmlFile) {
        XPathExpression expression = getXPathExpression();
        Document document = xmlFile.getNamespaceUnawareDocument();
        NodeList nodes = null;
        try {
            nodes = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            LOG.debug(String.format("[%s] Unable to evaluate XPath expression '%s' on file %s", ruleKey(), expression, inputFile().toString()));
            LOG.error("Xpath exception:", e);
        }
        if (nodes != null) {
            for (int i = 0; i < nodes.getLength(); i++) {
                visitNode(nodes.item(i), getMessage() != null ? getMessage() : getDefaultMessage());
            }
        }
    }

    private XPathExpression getXPathExpression() {
        XPathExpression xPathExpression;
        XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            xPathExpression = xpath.compile(getXPathExpressionString());
        } catch (XPathExpressionException e) {
            throw new IllegalStateException("Failed to compile XPath expression based on user-provided parameter [" + getXPathExpressionString() + "]", e);
        }
        return xPathExpression;
    }

    private String getDefaultMessage() {
        return "Change this XML node to not match: " + getXPathExpressionString();
    }
}
