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
package io.creedengo.java.checks.helpers.constant;

/**
 * Class used to create method matchers easily.
 */
public class MethodSpecs {
    private final String methodOwner;
    private final String methodName;
    private final Object constantValueToCheck;
    private final int[] parametersPositions;

    /**
     * Class used to create method matchers easily.
     *
     * @param methodName           name of the method to check
     * @param methodOwner          name of the type that own the method
     * @param constantValueToCheck the current value to check
     * @param parametersPositions  the position(s) of the argument on the method to check
     */
    public MethodSpecs(String methodName, String methodOwner, Object constantValueToCheck, int... parametersPositions) {
        this.methodName = methodName;
        this.methodOwner = methodOwner;
        this.constantValueToCheck = constantValueToCheck;
        this.parametersPositions = parametersPositions;
    }

    public String getMethodOwner() {
        return methodOwner;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object getConstantValueToCheck() {
        return constantValueToCheck;
    }

    public int[] getParamsPositions() {
        return parametersPositions;
    }
}
