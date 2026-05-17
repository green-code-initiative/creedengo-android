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
package java.net;

public final class URL {
    String con;

    public void testFor() {
        for (int i = 0; i < 10; i++) {
            con = openConnection(); // Noncompliant {{Internet connection should not be opened in loops to preserve the battery.}}
        }
        //notInLoop
        openConnection();
    }

    public void testTypeCastFor() {
        for (int i = 0; i < 10; i++) {
            HttpURLConnection connect = (HttpURLConnection) openConnection(); // Noncompliant {{Internet connection should not be opened in loops to preserve the battery.}}
            connect = (HttpURLConnection) openConnection();// Noncompliant {{Internet connection should not be opened in loops to preserve the battery.}}
        }
    }

    public void testWhile() {
        int i = 2;
        while (i > 0) {
            //in loop
            String str = openConnection();// Noncompliant {{Internet connection should not be opened in loops to preserve the battery.}}

            openConnection(); // Noncompliant {{Internet connection should not be opened in loops to preserve the battery.}}
            i = i - 1;
        }
        //notInLoop
        openConnection();
    }

    public void testTypeCastWhile() {
        int i = 2;
        while (i > 0) {
            //in loop
            i = i - 1;
            HttpURLConnection conn = (HttpURLConnection) URL.openConnection();// Noncompliant {{Internet connection should not be opened in loops to preserve the battery.}}
        }
    }

    public void testDoWhile() {
        int i = 0;
        do {
            //in loop
            openConnection(); // Noncompliant {{Internet connection should not be opened in loops to preserve the battery.}}
            i += i;
        } while (i < 4);
        //notInLoop
        openConnection();
    }

    public void testTypeCastDoWhile() {
        int f = 1;
        do {
            //in loop
            URL.openConnection();// Noncompliant {{Internet connection should not be opened in loops to preserve the battery.}}
            f += f;
        } while (f < 4);
    }

    public void testForEach() {
        int[] intArray = {10, 20, 30};
        for (int val : intArray) {
            //in loop
            openConnection(); // Noncompliant {{Internet connection should not be opened in loops to preserve the battery.}}
        }
        //notInLoop
        openConnection();
    }

    public void tesTypeCastForeach() {
        int[] intArray = {10, 20, 30};
        for (int i : intArray)
            connection = (HttpURLConnection) URL.openConnection();// Noncompliant {{Internet connection should not be opened in loops to preserve the battery.}}
    }

    public void testTypeCastAttribution() {
        HttpURLConnection connection;
        for (int t = 0; t < 10; t++)
            connection = (HttpURLConnection) new URL("http://www.google.com/").openConnection();// Noncompliant {{Internet connection should not be opened in loops to preserve the battery.}}
    }

    public void testInternalClass() {
        InternalClass internal = new InternalClass();
        for (int t = 0; t < 10; t++) {
            internal.myMethod(); // TODO: non compliant
        }
        internal.myMethod();
    }

    public void testInternalMethod() {
        for (int t = 0; t < 10; t++) {
            internalMethod(); // TODO: non compliant
        }
    }

    public String openConnection() throws java.io.IOException {
        String test = "openConnection";
        return test;
    }

    public class InternalClass {
        public void myMethod() {
            openConnection();
        }
    }

    public void internalMethod() {
        openConnection();
    }
}
