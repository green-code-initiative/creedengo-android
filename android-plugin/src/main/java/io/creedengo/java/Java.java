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
package io.creedengo.java;

public interface Java { //NOSONAR - we use the interface for constant without inheriting it
    String REPOSITORY_NAME = "Creedengo (Android Java)";
    String KEY = "java";
    String REPOSITORY_KEY = "creedengo-android-java";
    String RULES_SPECIFICATIONS_JAVA_PATH = "/io/creedengo/rules/java"; //NOSONAR - this URI is the same everywhere
    String PROFILE_NAME = "Creedengo (Android Java)";
    String PROFILE_PATH = "io/creedengo/android/java/creedengo_java_profile.json";
}
