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

class Surface {

    private static final int FRAME_RATE_COMPATIBILITY_DEFAULT = 0;
    private static final int FRAME_RATE_COMPATIBILITY_FIXED_SOURCE = 1;
    private static final int CHANGE_FRAME_RATE_ALWAYS = 1;
    private static final int CHANGE_FRAME_RATE_ONLY_IF_SEAMLESS = 0;
    private static final float FRAME_RATE_0 = 0.0f;
    private static final float FRAME_RATE_60 = 60.0f;
    private static final float FRAME_RATE_90 = 90.0f;
    private static final float FRAME_RATE_120 = 120.0f;

    public void test() {
        Surface surface = new Surface();
        surface.setFrameRate(FRAME_RATE_0, FRAME_RATE_COMPATIBILITY_DEFAULT);
        surface.setFrameRate(FRAME_RATE_60, FRAME_RATE_COMPATIBILITY_DEFAULT);
        surface.setFrameRate(FRAME_RATE_90, FRAME_RATE_COMPATIBILITY_DEFAULT); // Noncompliant {{To optimize content refresh and save energy, frame rate should be set at maximum 60Hz.}}
        surface.setFrameRate(FRAME_RATE_120, FRAME_RATE_COMPATIBILITY_DEFAULT); // Noncompliant {{To optimize content refresh and save energy, frame rate should be set at maximum 60Hz.}}

        // Test with compatibility fixed source
        surface.setFrameRate(0.0f, FRAME_RATE_COMPATIBILITY_FIXED_SOURCE);
        surface.setFrameRate(60.0f, FRAME_RATE_COMPATIBILITY_FIXED_SOURCE);
        surface.setFrameRate(90.0f, FRAME_RATE_COMPATIBILITY_FIXED_SOURCE); // Noncompliant {{To optimize content refresh and save energy, frame rate should be set at maximum 60Hz.}}
        surface.setFrameRate(120.0f, FRAME_RATE_COMPATIBILITY_FIXED_SOURCE); // Noncompliant {{To optimize content refresh and save energy, frame rate should be set at maximum 60Hz.}}
        // Test with 3 args
        surface.setFrameRate(0.0f, FRAME_RATE_COMPATIBILITY_DEFAULT, CHANGE_FRAME_RATE_ONLY_IF_SEAMLESS);
        surface.setFrameRate(60.0f, FRAME_RATE_COMPATIBILITY_DEFAULT, CHANGE_FRAME_RATE_ONLY_IF_SEAMLESS);
        surface.setFrameRate(90.0f, FRAME_RATE_COMPATIBILITY_DEFAULT, CHANGE_FRAME_RATE_ONLY_IF_SEAMLESS); // Noncompliant {{To optimize content refresh and save energy, frame rate should be set at maximum 60Hz.}}
        surface.setFrameRate(120.0f, FRAME_RATE_COMPATIBILITY_DEFAULT, CHANGE_FRAME_RATE_ONLY_IF_SEAMLESS); // Noncompliant {{To optimize content refresh and save energy, frame rate should be set at maximum 60Hz.}}

        surface.setFrameRate(0.0f, FRAME_RATE_COMPATIBILITY_DEFAULT, CHANGE_FRAME_RATE_ALWAYS);
        surface.setFrameRate(60.0f, FRAME_RATE_COMPATIBILITY_DEFAULT, CHANGE_FRAME_RATE_ALWAYS);
        surface.setFrameRate(90.0f, FRAME_RATE_COMPATIBILITY_DEFAULT, CHANGE_FRAME_RATE_ALWAYS); // Noncompliant {{To optimize content refresh and save energy, frame rate should be set at maximum 60Hz.}}
        surface.setFrameRate(120.0f, FRAME_RATE_COMPATIBILITY_DEFAULT, CHANGE_FRAME_RATE_ALWAYS); // Noncompliant {{To optimize content refresh and save energy, frame rate should be set at maximum 60Hz.}}

        surface.setFrameRate(0.0f, FRAME_RATE_COMPATIBILITY_FIXED_SOURCE, CHANGE_FRAME_RATE_ALWAYS);
        surface.setFrameRate(60.0f, FRAME_RATE_COMPATIBILITY_FIXED_SOURCE, CHANGE_FRAME_RATE_ALWAYS);
        surface.setFrameRate(90.0f, FRAME_RATE_COMPATIBILITY_FIXED_SOURCE, CHANGE_FRAME_RATE_ALWAYS); // Noncompliant {{To optimize content refresh and save energy, frame rate should be set at maximum 60Hz.}}
        surface.setFrameRate(120.0f, FRAME_RATE_COMPATIBILITY_FIXED_SOURCE, CHANGE_FRAME_RATE_ALWAYS); // Noncompliant {{To optimize content refresh and save energy, frame rate should be set at maximum 60Hz.}}

        surface.setFrameRate(0.0f, FRAME_RATE_COMPATIBILITY_FIXED_SOURCE, CHANGE_FRAME_RATE_ONLY_IF_SEAMLESS);
        surface.setFrameRate(60.0f, FRAME_RATE_COMPATIBILITY_FIXED_SOURCE, CHANGE_FRAME_RATE_ONLY_IF_SEAMLESS);
        surface.setFrameRate(90.0f, FRAME_RATE_COMPATIBILITY_FIXED_SOURCE, CHANGE_FRAME_RATE_ONLY_IF_SEAMLESS); // Noncompliant {{To optimize content refresh and save energy, frame rate should be set at maximum 60Hz.}}
        surface.setFrameRate(120.0f, FRAME_RATE_COMPATIBILITY_FIXED_SOURCE, CHANGE_FRAME_RATE_ONLY_IF_SEAMLESS); // Noncompliant {{To optimize content refresh and save energy, frame rate should be set at maximum 60Hz.}}
    }

    public void	setFrameRate(float frameRate, int compatibility) {
        return;
    }

    public void setFrameRate(float frameRate, int compatibility, int changeFrameRateStrategy) {
        return;
    }

}