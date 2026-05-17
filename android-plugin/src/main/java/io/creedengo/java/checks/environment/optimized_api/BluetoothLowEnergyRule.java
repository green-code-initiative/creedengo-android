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
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Rule to test if the class contains the imports 'android.bluetooth' or 'android.bluetooth.le'
 * <ul>
 * <li> if only 'android.bluetooth' is imported, an issue is raised : "You are using Bluetooth. Did you take a look
 * at the Bluetooth Low Energy API?"
 * </li>
 * <li> if only 'android.bluetooth.le' is imported, an issue is raised : "Using android.bluetooth.le.* is a good practice"
 * </li>
 * <li>if 'android.bluetooth' and 'android.bluetooth.le' are imported, we assume the two imports are needed and raise a
 * good practice issue : "Using android.bluetooth.le.* is a good practice"
 * </li>
 * </ul>
 */
@DeprecatedRuleKey(repositoryKey = "creedengo-android-java", ruleKey = "EC518")
@Rule(key = "GCI518")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "EOPT002")
public class BluetoothLowEnergyRule extends BaseTreeVisitor implements JavaFileScanner {

    private static final String ERROR_MESSAGE = "You are using Bluetooth. Did you take a look at the Bluetooth Low Energy API?";
    private static final String GOOD_PRACTICE_MESSAGE = "Using android.bluetooth.le.* is a good practice.";

    @Override
    public void scanFile(JavaFileScannerContext context) {
        CompilationUnitTree cut = context.getTree();

        if (cut.moduleDeclaration() != null) {
            // skip module declarations as long as semantic is not resolved correctly.
            // imports can be used for types used in module directive or annotations
            return;
        }

        BluetoothImports bluetoothImports = new BluetoothImports();

        for (ImportClauseTree importClauseTree : cut.imports()) {
            ImportTree importTree = null;

            if (importClauseTree.is(Tree.Kind.IMPORT)) {
                importTree = (ImportTree) importClauseTree;
            }

            if (importTree == null) {
                // discard empty statements, which can be part of imports
                continue;
            }

            bluetoothImports.collectBluetoothImport(importTree);
        }
        handleResult(context, bluetoothImports);
        scan(cut);
    }

    private void handleResult(JavaFileScannerContext context, BluetoothImports bluetoothImports) {
        if (bluetoothImports.hasBluetoothImports()) {
            if (bluetoothImports.hasBothBluetoothTypeImports()) {
                for (ImportTree importTree : bluetoothImports.getBluetoothLEImports()) {
                    context.reportIssue(this, importTree, GOOD_PRACTICE_MESSAGE);
                }
            } else if (bluetoothImports.hasBluetoothLEImports()) {
                for (ImportTree importTree : bluetoothImports.getBluetoothLEImports()) {
                    context.reportIssue(this, importTree, GOOD_PRACTICE_MESSAGE);
                }
            } else {
                for (ImportTree importTree : bluetoothImports.getBluetoothClassicImports()) {
                    context.reportIssue(this, importTree, ERROR_MESSAGE);
                }
            }
        }
    }

    private class BluetoothImports {

        private static final String IMPORT_STR_BC = "android.bluetooth";
        private static final String IMPORT_STR_BLE = "android.bluetooth.le";

        private final List<ImportTree> bleListTree = new ArrayList<>();
        private final List<ImportTree> bcListTree = new ArrayList<>();

        public List<ImportTree> getBluetoothClassicImports() {
            return bcListTree;
        }

        public List<ImportTree> getBluetoothLEImports() {
            return bleListTree;
        }

        public boolean hasBluetoothClassicImports() {
            return !bcListTree.isEmpty();
        }

        public boolean hasBluetoothLEImports() {
            return !bleListTree.isEmpty();
        }

        public boolean hasBluetoothImports() {
            return hasBluetoothClassicImports() || hasBluetoothLEImports();
        }

        public boolean hasBothBluetoothTypeImports() {
            return hasBluetoothClassicImports() && hasBluetoothLEImports();
        }

        public void collectBluetoothImport(ImportTree importTree) {
            collectBluetoothClassicImport(importTree);
            collectBluetoothLEImport(importTree);
        }

        private void collectBluetoothClassicImport(ImportTree importTree) {
            String importName = TreeHelper.fullQualifiedName(importTree.qualifiedIdentifier());
            if (importName.startsWith(IMPORT_STR_BC) && !importName.startsWith(IMPORT_STR_BLE)) {
                bcListTree.add(importTree);
            }
        }

        private void collectBluetoothLEImport(ImportTree importTree) {
            String importName = TreeHelper.fullQualifiedName(importTree.qualifiedIdentifier());
            if (importName.startsWith(IMPORT_STR_BLE)) {
                bleListTree.add(importTree);
            }
        }
    }
}
