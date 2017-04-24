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

import java.util.Objects;
import keyto.endlessmine.common.coordinate_system.EnumDirection;
import keyto.endlessmine.common.coordinate_system.IBlockIndex;
import keyto.endlessmine.common.coordinate_system.IBlockPoint;
import keyto.endlessmine.common.coordinate_system.IChunkPoint;
import keyto.endlessmine.common.coordinate_system.RelativeCoordinate;
import keyto.endlessmine.common.coordinate_system.Specifications;

/**
 * 格子坐标
 *
 * @author Keyto
 */
public class BlockPoint implements IBlockPoint {

    private final IChunkPoint chunkPoint;    //区块坐标
    private final IBlockIndex blockIndex;    //区块内格子序号

    /**
     *
     * @param chunkPoint 区块坐标
     * @param blockIndex 格子序号
     */
    public BlockPoint(IChunkPoint chunkPoint, IBlockIndex blockIndex) {
        this.chunkPoint = chunkPoint;
        this.blockIndex = blockIndex;
    }

    /**
     *
     * @param chunkPoint 区块坐标
     * @param index      格子序号
     */
    public BlockPoint(IChunkPoint chunkPoint, int index) {
        this.chunkPoint = chunkPoint;
        this.blockIndex = BlockIndex.valueof(index);
    }

    /**
     *
     * @param chunkPoint 区块坐标
     * @param x          格子x坐标
     * @param y          格子y坐标
     */
    public BlockPoint(IChunkPoint chunkPoint, int x, int y) {
        this.chunkPoint = chunkPoint;
        this.blockIndex = BlockIndex.valueof(x, y);
    }

    /**
     * 按照枚举方位获得33坐标
     *
     * @param ed 方位枚举
     * @return 33内坐标
     */
    @Override
    public BlockPoint getAroundBPoint(EnumDirection ed) {
        return getBPointByRC(ed.getRc());
    }

    /**
     * 按照相对坐标获得绝对坐标
     *
     * @param rc 相对坐标
     * @return 相对于此坐标的绝对坐标
     */
    @Override
    public BlockPoint getBPointByRC(RelativeCoordinate rc) {
        int rcx = rc.getX() + this.getBlockIndex().getX();
        int rcy = rc.getY() + this.getBlockIndex().getY();
        final int SIDE_EXPONENT = Specifications.SIDE_EXPONENT;
        RelativeCoordinate rcPoint = new RelativeCoordinate(rcx >> SIDE_EXPONENT, rcy >> SIDE_EXPONENT);
        return new BlockPoint(chunkPoint.getCPointByRC(rcPoint), rcx, rcy);
    }

    /**
     * @return 区块坐标
     */
    @Override
    public IChunkPoint getChunkPoint() {
        return this.chunkPoint;
    }

    @Override
    public IBlockIndex getBlockIndex() {
        return this.blockIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final BlockPoint other = (BlockPoint) obj;
        if (!Objects.equals(this.chunkPoint, other.chunkPoint)) return false;
        if (!Objects.equals(this.blockIndex, other.blockIndex)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.chunkPoint);
        hash = 53 * hash + Objects.hashCode(this.blockIndex);
        return hash;
    }

    @Override
    public String toString() {
        return "BlockPoint[" + this.chunkPoint.toString()
                + "," + this.blockIndex.toString() + "]";
    }

}
