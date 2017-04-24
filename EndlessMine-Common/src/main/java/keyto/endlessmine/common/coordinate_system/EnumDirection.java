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
 *
 * @author Keyto
 */
public enum EnumDirection {

    /**
     * 中心（自身）坐标:5
     */
    CENTER(new RelativeCoordinate(0, 0)),
    /**
     * 右边→坐标：6
     */
    EAST(new RelativeCoordinate(1, 0)),
    /**
     * 右上↗坐标：9
     */
    NORTH_EAST(new RelativeCoordinate(1, -1)),
    /**
     * 上方↑坐标：8
     */
    NORTH(new RelativeCoordinate(0, -1)),
    /**
     * 左上↖坐标：7
     */
    NORTH_WEST(new RelativeCoordinate(-1, -1)),
    /**
     * 左边←坐标：4
     */
    WEST(new RelativeCoordinate(-1, 0)),
    /**
     * 左下↙坐标：1
     */
    SOUTH_WEST(new RelativeCoordinate(-1, 1)),
    /**
     * 下方↓坐标：2
     */
    SOUTH(new RelativeCoordinate(0, 1)),
    /**
     * 右下↘坐标：3
     */
    SOUTH_EAST(new RelativeCoordinate(1, 1));

    private final RelativeCoordinate rc;

    private EnumDirection(RelativeCoordinate rc) {
        this.rc = rc;
    }

    /**
     * @return the rc
     */
    public RelativeCoordinate getRc() {
        return rc;
    }

}
