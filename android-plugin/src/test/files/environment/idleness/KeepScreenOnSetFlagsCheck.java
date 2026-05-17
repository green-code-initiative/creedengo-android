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

public class Window {

    private static final int FLAG_TO_CHECK = 0x00000080;
    private static final int OTHER_FLAG = 0x00000002;

    public void setFlags(int flag, int secondFlag) {
    }

    public Window getWindow() {
        return new Window();
    }

    public void myMethod() {
        Window w = new Window();
        w.getWindow().setFlags(128, 8); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.getWindow().setFlags(8, 128); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.getWindow().setFlags(8, 4);
        w.getWindow().setFlags(FLAG_TO_CHECK, 8); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.getWindow().setFlags(8, FLAG_TO_CHECK); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.getWindow().setFlags(OTHER_FLAG, OTHER_FLAG);
        // TODO must pass:
        //w.getWindow().addFlags(FLAG_TO_CHECK | OTHER_FLAG, OTHER_FLAG); // TODO: non compliant
        //w.getWindow().addFlags(FLAG_TO_CHECK & OTHER_FLAG, OTHER_FLAG); // TODO: non compliant

        w.setFlags(128, 4); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.setFlags(4, 128); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.setFlags(8, 4);
        w.setFlags(FLAG_TO_CHECK, OTHER_FLAG); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.setFlags(OTHER_FLAG, FLAG_TO_CHECK); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.setFlags(OTHER_FLAG);
        w.setFlags(0x00000080, 0x00000020); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.setFlags(0x00000020, 0x00000080); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.setFlags(0x00000020, 0x00000020);

        w.getWindow().getWindow().getWindow().setFlags(128, 4); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.getWindow().getWindow().getWindow().setFlags(8, 4);
    }

    class InternalClass {

        public InnerClass() {
            Window w = new Window();
            w.getWindow().setFlags(128, 4); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
            w.getWindow().setFlags(4, 128); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
            w.getWindow().setFlags(8, 8);

            w.getWindow().setFlags(0x00000080, 0x00000020); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
            w.getWindow().setFlags(0x00000020, 0x00000080); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
            w.getWindow().setFlags(0x00000020, 0x00000020);
        }

        public String internalMethod(String parameter) {
            Window w = new Window();
            w.getWindow().setFlags(InnerFinalClass.FLAG_TO_CHECK, InnerFinalClass.OTHER_FLAG); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
            w.getWindow().setFlags(4, 4);
            return parameter;
        }
    }

    public final class InnerFinalClass {
        public static final int FLAG_TO_CHECK = 0x00000080;
        private static final int OTHER_FLAG = 0x00000002;
    }
}