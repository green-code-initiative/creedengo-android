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
package io.creedengo.java.checks.environment.batch;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.List;

/**
 * Checks the call of "set", "setAlarmClock", "setExact", "setInexactRepeating", "setRepeating" & "setWindow" from "android.app.AlarmManager".
 * Checks also the call of "onPerformSync" & "getSyncAdapterBinder" from "android.content.AbstractThreadedSyncAdapter".
 */
@DeprecatedRuleKey(repositoryKey = "creedengo-android-java", ruleKey = "EC501")
@Rule(key = "GCI501")
@DeprecatedRuleKey(repositoryKey = "ecocode-android-java", ruleKey = "EBAT003")
public class JobCoalesceRule extends IssuableSubscriptionVisitor {

	private static final String ALARM_MANAGER_CLASS = "android.app.AlarmManager";

    private final MethodMatchers alarmSchedulerMethodMatcher = MethodMatchers.or(
            MethodMatchers.create().ofTypes(ALARM_MANAGER_CLASS).names("set").withAnyParameters().build(),
            MethodMatchers.create().ofTypes(ALARM_MANAGER_CLASS).names("setAlarmClock").withAnyParameters().build(),
            MethodMatchers.create().ofTypes(ALARM_MANAGER_CLASS).names("setAndAllowWhileIdle").withAnyParameters().build(),
            MethodMatchers.create().ofTypes(ALARM_MANAGER_CLASS).names("setExact").withAnyParameters().build(),
            MethodMatchers.create().ofTypes(ALARM_MANAGER_CLASS).names("setExactAndAllowWhileIdle").withAnyParameters().build(),
            MethodMatchers.create().ofTypes(ALARM_MANAGER_CLASS).names("setInexactRepeating").withAnyParameters().build(),
            MethodMatchers.create().ofTypes(ALARM_MANAGER_CLASS).names("setRepeating").withAnyParameters().build(),
            MethodMatchers.create().ofTypes(ALARM_MANAGER_CLASS).names("setWindow").withAnyParameters().build(),
            //ofAnyType is used because the method is forced to be overridden in a new class expending the abstract class AbstractThreadedSyncAdapter
            MethodMatchers.create().ofAnyType().names("onPerformSync").withAnyParameters().build(),
            //ofSubTypes is used because the method is from an abstract class
            MethodMatchers.create().ofSubTypes("android.content.AbstractThreadedSyncAdapter").names("getSyncAdapterBinder").withAnyParameters().build()
    );

    public JobCoalesceRule() {
        super();
    }

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return List.of(Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree) {
        if (tree.is(Tree.Kind.METHOD_INVOCATION)) {
            MethodInvocationTree mit = (MethodInvocationTree) tree;
            if (alarmSchedulerMethodMatcher.matches(mit)) {
                reportIssue(mit, "Avoid using AlarmManager or a SyncAdapter for an alarm. Instead use the JobScheduler because the alarm triggers are mutualized.");
            }
            /* TODO: 21/02/2022 to upgrade this part, another method matcher could be used to search the fact the AbstractThreadedSyncAdapter class is implemented
            to avoid using an ofAnyType() methodMatcher (line 49) reducing the risk of a reported issue where there should not be one
             */
        }
    }
}
