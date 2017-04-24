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
public interface IChunkPoint {

    /**
     * 以当前坐标为中心，通过方位枚举获得33坐标值
     *
     * @param ed 方位的枚举
     * @return 绝对坐标
     */
    IChunkPoint getAroundCPoint(EnumDirection ed);

    /**
     * 通过相对坐标获得绝对坐标
     *
     * @param rc 相对坐标
     * @return 绝对坐标
     */
    IChunkPoint getCPointByRC(RelativeCoordinate rc);

    /**
     * @return the x 坐标
     */
    int getX();

    /**
     * @return the y 坐标
     */
    int getY();

}
