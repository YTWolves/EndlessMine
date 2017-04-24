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
package keyto.endlessmine.common.coordinate_system;

/**
 * 相对坐标
 *
 * @author Keyto
 */
public class RelativeCoordinate {

    private final int rc_x;
    private final int rc_y;

    /**
     *
     * @param rc_x
     * @param rc_y
     */
    public RelativeCoordinate(int rc_x, int rc_y) {
        this.rc_x = rc_x;
        this.rc_y = rc_y;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return rc_x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return rc_y;
    }

}
