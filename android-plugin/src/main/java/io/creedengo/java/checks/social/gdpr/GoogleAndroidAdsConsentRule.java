package io.creedengo.java.checks.social.gdpr;

import io.creedengo.java.checks.helpers.TreeHelper;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * Check the presence of the import com.google.android.ads.consent
 * or  com.google.android.ump:user-messaging-platform
 * <p>
 * To support publishers in meeting their duties under the EU User Consent Policy, Google offers a Consent SDK.
 * Hence, importing classes from com.google.android.ads.consent or com.google.android.ump:user-messaging-platform is considered as a good practice.
 */
@Rule(key = "EC533", name = "GDPR: Google Consent")
@DeprecatedRuleKey(repositoryKey = "creedengo-java", ruleKey = "SGDP001")
public class GoogleAndroidAdsConsentRule extends BaseTreeVisitor implements JavaFileScanner {

    private static final String INFO_MESSAGE_GOOGLE_ANDROID_ADS_CONSENT_USED = "Good Smell : User Consent";

    @Override
    public void scanFile(JavaFileScannerContext context) {
        CompilationUnitTree cut = context.getTree();

        GoogleAndroidAdsConsentImports googleTrackerImport = new GoogleAndroidAdsConsentImports();

        boolean isGoogleAdsConsentImported = false;
        for (ImportClauseTree importClauseTree : cut.imports()) {
            ImportTree importTree = null;

            if (importClauseTree.is(Tree.Kind.IMPORT)) {
                importTree = (ImportTree) importClauseTree;
            }

            if (importTree == null) {
                continue;
            }

            if (googleTrackerImport.verifyImportGoogleAdsConsent(importTree)) {
                isGoogleAdsConsentImported = true;
                break;
            }
        }

        if (isGoogleAdsConsentImported) {
            context.addIssueOnProject(this, INFO_MESSAGE_GOOGLE_ANDROID_ADS_CONSENT_USED);
        }

        scan(cut);
    }


    /**
     * Deprecated : com.google.android.ads.consent
     * <p>
     * Android 5.0 (API 21) : com.google.android.ump:user-messaging-platform
     */
    private static class GoogleAndroidAdsConsentImports {
        private static final String STR_GOOGLE_ADS_CONSENT_IMPORT = "com.google.android.ads.consent";
        private static final String STR_GOOGLE_ADS_UMP_CONSENT_IMPORT = "com.google.android.ump";

        public boolean verifyImportGoogleAdsConsent(ImportTree importTree) {
            String importName = TreeHelper.fullQualifiedName(importTree.qualifiedIdentifier());
            return importName.startsWith(STR_GOOGLE_ADS_CONSENT_IMPORT) || importName.startsWith(STR_GOOGLE_ADS_UMP_CONSENT_IMPORT);
        }
    }
}
