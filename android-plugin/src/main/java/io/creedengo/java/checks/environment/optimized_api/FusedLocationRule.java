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
package io.creedengo.java.checks.environment.optimized_api;

import io.creedengo.java.checks.helpers.TreeHelper;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.ImportTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>Checks the import of android.location and com.google.android.gms.location.*</li>
 * <li>If the first import is found but not the second, report an issue.</li>
 * </ul>`
 */
@DeprecatedRuleKey(repositoryKey = "creedengo-android-java", ruleKey = "EC517")
@Rule(key = "GCI517")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "EOPT001")
public class FusedLocationRule extends IssuableSubscriptionVisitor {

    protected static final String WRONG_IMPORT = "android.location";
    protected static final String GOOD_IMPORT = "com.google.android.gms.location";
    private boolean hasSeenWrongImport = false;
    private boolean hasSeenGoodImport = false;
    private ArrayList<ImportTree> mImportTreeList = new ArrayList<>();

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.IMPORT);
    }

    @Override
    public void visitNode(Tree tree) {
        ImportTree importTree = (ImportTree) tree;
        String fullQualifiedImportName = TreeHelper.fullQualifiedName(importTree.qualifiedIdentifier());
        if (fullQualifiedImportName.startsWith(WRONG_IMPORT)) {
            hasSeenWrongImport = true;
            mImportTreeList.add(importTree);
        }
        if (fullQualifiedImportName.startsWith(GOOD_IMPORT)) {
            hasSeenGoodImport = true;
        }
    }

    @Override
    public void leaveFile(JavaFileScannerContext context) {
        super.leaveFile(context);
        if (hasSeenWrongImport && !hasSeenGoodImport) {
            for (ImportTree importTree : mImportTreeList) {
                reportIssue(importTree, "Use com.google.android.gms.location instead of android.location to maximize battery life.");
            }
        }

        hasSeenGoodImport = false;
        hasSeenWrongImport = false;
        mImportTreeList.clear();
    }
}
