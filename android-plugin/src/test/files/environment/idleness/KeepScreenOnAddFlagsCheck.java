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

    public void addFlags(int flag) {
    }

    public Window getWindow() {
        return new Window();
    }

    public void myMethod() {
        Window w = new Window();
        w.getWindow().addFlags(128); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.getWindow().addFlags(8);
        w.getWindow().addFlags(FLAG_TO_CHECK); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.getWindow().addFlags(OTHER_FLAG);
        // TODO must pass:
        //w.getWindow().addFlags(FLAG_TO_CHECK | OTHER_FLAG); // TODO: non compliant
        //w.getWindow().addFlags(FLAG_TO_CHECK & OTHER_FLAG); // TODO: non compliant

        w.addFlags(128); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.addFlags(8);
        w.addFlags(FLAG_TO_CHECK); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.addFlags(OTHER_FLAG);
        w.addFlags(0x00000080); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.addFlags(0x00000020);

        w.getWindow().getWindow().getWindow().addFlags(128); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
        w.getWindow().getWindow().getWindow().addFlags(8);
    }

    class InternalClass {

        public InnerClass() {
            Window w = new Window();
            w.getWindow().addFlags(128); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
            w.getWindow().addFlags(8);

            w.getWindow().addFlags(0x00000080); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
            w.getWindow().addFlags(0x00000020);
        }

        public String internalMethod(String parameter) {
            Window w = new Window();
            w.getWindow().addFlags(InnerFinalClass.FLAG_TO_CHECK); // Noncompliant {{Keeping the screen on should be avoided to avoid draining the battery.}}
            w.getWindow().addFlags(4);
            return parameter;
        }
    }

    public final class InnerFinalClass {
        public static final int FLAG_TO_CHECK = 0x00000080;
        private static final int OTHER_FLAG = 0x00000002;
    }
}