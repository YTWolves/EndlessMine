/*
 * Copyright (C) 2017 Keyto
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * E-mail: keyto1995@outlook.com
 */
package keyto.endlessmine.common.mouse;

/**
 *
 * @author Keyto
 */
public enum MouseButton {

    /**
     * Represents no button.
     */
    NONE(0),

    /**
     * Represents primary (button 1, usually the left) mouse button.
     */
    PRIMARY(1),

    /**
     * Represents middle (button 2) mouse button.
     */
    MIDDLE(2),

    /**
     * Represents seconday (button 3, usually the right) mouse button.
     */
    SECONDARY(3);
    
    private final int mouseKeyValue;

    private MouseButton(int mouseKeyValue) {
        this.mouseKeyValue = mouseKeyValue;
    }

    public int getMouseKeyValue() {
        return this.mouseKeyValue;
    }

    public static MouseButton getMouseButton(int mouseID) {
        switch (mouseID) {
            case 0:
                return NONE;
            case 1:
                return PRIMARY;
            case 2:
                return MIDDLE;
            case 3:
                return SECONDARY;
            default:
                throw new RuntimeException("未知鼠标按键ID");
        }
    }
}
