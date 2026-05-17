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
public abstract class OutputStream {

    public void test() {
        OutputStream stream = new OutputStream() {
            public void test() {
            }
        };

        OutputStream olalala = stream.getOutputStream();

        stream.method(olalala);
        java.net.URLConnection con = new java.net.URLConnection();
        java.net.HttpURLConnection httpCon = new java.net.HttpURLConnection();

        java.io.OutputStream stream = ((java.io.OutputStream) con.getOutputStream());// Noncompliant {{Prefer using GzipOutputStream instead of OutputStream to improve energy efficiency.}}
        java.io.OutputStream stream = (java.io.OutputStream) con.getOutputStream();// Noncompliant {{Prefer using GzipOutputStream instead of OutputStream to improve energy efficiency.}}
        java.io.OutputStream stream = ((java.io.DataOutputStream) con.getOutputStream());// Noncompliant {{Prefer using GzipOutputStream instead of OutputStream to improve energy efficiency.}}
        java.io.OutputStream stream = (java.io.DataOutputStream) con.getOutputStream();// Noncompliant {{Prefer using GzipOutputStream instead of OutputStream to improve energy efficiency.}}
        java.io.OutputStream stream = (java.io.DataOutputStream) httpCon.getOutputStream();// Noncompliant {{Prefer using GzipOutputStream instead of OutputStream to improve energy efficiency.}}
        java.io.OutputStream stream = ((java.io.DataOutputStream) httpCon.getOutputStream());// Noncompliant {{Prefer using GzipOutputStream instead of OutputStream to improve energy efficiency.}}

        java.io.OutputStream stream = new java.io.FileOutputStream("myfile.zip");
        java.io.OutputStream stream = new java.io.BufferedOutputStream(new java.io.FileOutputStream("myfile.zip"));
        java.io.OutputStream stream = new java.io.BufferedOutputStream(con.getOutputStream());// Noncompliant {{Prefer using GzipOutputStream instead of OutputStream to improve energy efficiency.}}

        java.util.zip.GZIPOutputStream stream = new java.io.GZIPOutputStream(con.getOutputStream());
        java.io.OutputStream stream = new java.io.GZIPOutputStream(con.getOutputStream());
    }
}
