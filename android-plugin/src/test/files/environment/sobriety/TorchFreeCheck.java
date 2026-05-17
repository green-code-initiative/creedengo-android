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
package android.hardware.camera2;

import java.lang.Boolean;

public final class CameraManager {

    public static final boolean IS_ENABLED = true;
    public static final boolean IS_NOT_ENABLED = false;

    public static final Boolean IS_ENABLED_BOOL = new Boolean(true);
    public static final Boolean IS_NOT_ENABLED_BOOL = new Boolean(false);

    public void test() {
        CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraId = null;
        try {
            cameraId = camManager.getCameraIdList()[0];
            camManager.setTorchMode(cameraId, true);  // Noncompliant {{Flashlight is one of the most energy-intensive component. Don't programmatically turn it on.}}
            camManager.setTorchMode(cameraId, false);

            boolean innerVariable = true;
            boolean innerVariableNot = false;

            camManager.setTorchMode(cameraId, IS_ENABLED);// Noncompliant {{Flashlight is one of the most energy-intensive component. Don't programmatically turn it on.}}
            camManager.setTorchMode(cameraId, IS_NOT_ENABLED);

            camManager.setTorchMode(cameraId, innerVariable); // TODO  Noncompliant
            camManager.setTorchMode(cameraId, innerVariableNot);

            camManager.setTorchMode(cameraId, IS_ENABLED_BOOL); // TODO Noncompliant
            camManager.setTorchMode(cameraId, IS_NOT_ENABLED_BOOL);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private Service getSystemService(String string) {
        return null;
    }

    private String[] getCameraIdList() {
        return new String[]{"0", "1", "2"};
    }

    public void setTorchMode(String cameraId, boolean enabled) throws CameraAccessException {
        //torch manipulation
    }
}
