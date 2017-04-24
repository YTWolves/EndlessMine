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
package keyto.endlessmine.common.coordinate_system.impl;

import keyto.endlessmine.common.coordinate_system.EnumDirection;
import keyto.endlessmine.common.coordinate_system.IChunkPoint;
import keyto.endlessmine.common.coordinate_system.RelativeCoordinate;

/**
 * 区块坐标
 *
 * @author Keyto
 */
public class ChunkPoint implements IChunkPoint {

    private final int x;
    private final int y;

    public ChunkPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * 以当前坐标为中心，通过方位枚举获得33坐标值
     *
     * @param ed 方位的枚举
     * @return 绝对坐标
     */
    @Override
    public ChunkPoint getAroundCPoint(EnumDirection ed) {
        return getCPointByRC(ed.getRc());
    }

    /**
     * 通过相对坐标获得绝对坐标
     *
     * @param rc 相对坐标
     * @return 绝对坐标
     */
    @Override
    public ChunkPoint getCPointByRC(RelativeCoordinate rc) {
        int tmp_x = this.x + rc.getX();
        int tmp_y = this.y + rc.getY();
        return new ChunkPoint(tmp_x, tmp_y);
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ChunkPoint)) return false;
        ChunkPoint otherCP = (ChunkPoint) obj;
        return (this.x == otherCP.x && this.y == otherCP.y);
    }

    /**
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }

    @Override
    public String toString() {
        return "ChunkPoint[" + x + "," + y + "]";
    }

}
