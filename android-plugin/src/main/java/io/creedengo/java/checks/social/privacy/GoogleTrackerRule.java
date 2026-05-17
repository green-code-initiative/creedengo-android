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
package io.creedengo.java.checks.social.privacy;

import io.creedengo.java.checks.helpers.TreeHelper;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Check the presence of the import com.google.android.gms.analytics.Tracker
 * Importing the com.google.android.gms.analytics.Tracker class means that
 * the app sends hits to Google Analytics. It is not necessarily sensitive
 * information, but it is a first step towards Google Ads and hence this
 * practice should be discouraged at early stage.
 */

@DeprecatedRuleKey(repositoryKey = "creedengo-android-java", ruleKey = "EC532")
@Rule(key = "GCI532")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "SPRI002")
public class GoogleTrackerRule extends BaseTreeVisitor implements JavaFileScanner {

    private static final String ERROR_MESSAGE_GGL_TRACKER = "Using com.google.android.gms.analytics.* is a potential threat for privacy.";
    private static final String ERROR_MESSAGE_FIREBASE_TRACKER = "Using com.google.firebase.analytics.* is a potential threat for privacy.";

    @Override
    public void scanFile(JavaFileScannerContext context) {
        CompilationUnitTree cut = context.getTree();

        GoogleTrackerImports googleTrackerImport = new GoogleTrackerImports();

        for (ImportClauseTree importClauseTree : cut.imports()) {
            ImportTree importTree = null;

            if (importClauseTree.is(Tree.Kind.IMPORT)) {
                importTree = (ImportTree) importClauseTree;
            }

            if (importTree == null) {
                // discard empty statements, which can be part of imports
                continue;
            }

            googleTrackerImport.collectTrackerImport(importTree);
        }

        handleResult(context, googleTrackerImport);
        scan(cut);
    }

    private void handleResult(JavaFileScannerContext context, GoogleTrackerImports googleTrackerImport) {
        if (googleTrackerImport.hasTrackerImports()) {
            List<AnalyticsIssue> issues = new ArrayList<>();
            if (googleTrackerImport.hasGoogleTrackerImports()) {
                for (ImportTree importTree : googleTrackerImport.getGoogleTrackerImports()) {
                    issues.add(new AnalyticsIssue(importTree, ERROR_MESSAGE_GGL_TRACKER));
                }
            }

            if (googleTrackerImport.hasFirebaseTrackerImports()) {
                for (ImportTree importTree : googleTrackerImport.getFirebaseTrackerImports()) {
                    issues.add(new AnalyticsIssue(importTree, ERROR_MESSAGE_FIREBASE_TRACKER));
                }
            }
            for (AnalyticsIssue issue : issues) {
                context.reportIssue(this, issue.tree, issue.desc);
            }
        }
    }

    private static class AnalyticsIssue {
        private final ImportTree tree;
        private final String desc;

        AnalyticsIssue(ImportTree tree, String desc) {
            this.tree = tree;
            this.desc = desc;
        }
    }

    private static class GoogleTrackerImports {
        private static final String STR_GOOGLE_ANALYTICS_IMPORT = "com.google.android.gms.analytics";
        private static final String STR_FIREBASE_ANALYTICS_IMPORT = "com.google.firebase.analytics";

        private final List<ImportTree> googleTrackerListTree = new ArrayList<>();
        private final List<ImportTree> firebaseTrackerListTree = new ArrayList<>();

        public List<ImportTree> getGoogleTrackerImports() {
            return googleTrackerListTree;
        }

        public List<ImportTree> getFirebaseTrackerImports() {
            return firebaseTrackerListTree;
        }

        public boolean hasTrackerImports() {
            return hasGoogleTrackerImports() || hasFirebaseTrackerImports();
        }

        public boolean hasGoogleTrackerImports() {
            return !googleTrackerListTree.isEmpty();
        }

        public boolean hasFirebaseTrackerImports() {
            return !firebaseTrackerListTree.isEmpty();
        }

        public void collectTrackerImport(ImportTree importTree) {
            String importName = TreeHelper.fullQualifiedName(importTree.qualifiedIdentifier());
            if (importName.startsWith(STR_GOOGLE_ANALYTICS_IMPORT)) {
                googleTrackerListTree.add(importTree);
            } else if (importName.startsWith(STR_FIREBASE_ANALYTICS_IMPORT)) {
                firebaseTrackerListTree.add(importTree);
            }
        }
    }
}
