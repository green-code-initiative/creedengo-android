/*
 * Creedengo Android plugin - Provides rules to reduce the environmental footprint of your Android applications
 * Copyright © 2020 Green Code Initiative (contact@creedengo.io)
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
package io.creedengo.java.checks.environment.idleness;

import io.creedengo.java.checks.helpers.constant.FlagOnMethodCheck;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

/**
 * Check the call of the method "setRenderMode" of "opengl.GLSurfaceView".
 * If argument value is "1" report issue.
 */
@Rule(key = "EC510")
@DeprecatedRuleKey(repositoryKey = "creedengo-java", ruleKey = "EIDL008")
public class ContinuousRenderingRule extends FlagOnMethodCheck {

    public ContinuousRenderingRule() {
        super("setRenderMode", "android.opengl.GLSurfaceView", 1, 0);
    }

    public String getMessage() {
        return "Using RENDERMODE_WHEN_DIRTY instead of RENDERMODE_CONTINUOUSLY can improve battery life.";
    }
}
