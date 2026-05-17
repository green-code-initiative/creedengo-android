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
package android.view;

public interface WindowManager extends ViewManager {
    public static class LayoutParams {
        public static final float BRIGHTNESS_OVERRIDE_NONE = -1.0f;
        public static final float BRIGHTNESS_OVERRIDE_OFF = 0.0f;
        public static final float BRIGHTNESS_OVERRIDE_FULL = 1.0f;

        public float screenBrightness = BRIGHTNESS_OVERRIDE_NONE;

        public void InnerStaticClassMethod() {
            LayoutParams params;
            params.screenBrightness = BRIGHTNESS_OVERRIDE_FULL; // Noncompliant {{Forcing brightness to max value may cause useless energy consumption.}}
            params.screenBrightness = 1f; // Noncompliant {{Forcing brightness to max value may cause useless energy consumption.}}
            params.screenBrightness = 1; // Noncompliant {{Forcing brightness to max value may cause useless energy consumption.}}
            params.screenBrightness = 0.5f;
            params.screenBrightness = BRIGHTNESS_OVERRIDE_NONE;

            float blabla = 1f;
            params.screenBrightness = blabla; // TODO Noncompliant for VARIABLES

            params.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL; // Noncompliant {{Forcing brightness to max value may cause useless energy consumption.}}
            params.screenBrightness = LayoutParams.BRIGHTNESS_OVERRIDE_FULL; // Noncompliant {{Forcing brightness to max value may cause useless energy consumption.}}
            params.screenBrightness = android.view.WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL; // Noncompliant {{Forcing brightness to max value may cause useless energy consumption.}}
        }
    }
}
