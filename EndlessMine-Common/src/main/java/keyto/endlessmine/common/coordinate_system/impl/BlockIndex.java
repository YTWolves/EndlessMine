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

import keyto.endlessmine.common.coordinate_system.IBlockIndex;
import keyto.endlessmine.common.coordinate_system.Specifications;

/**
 * 二维坐标与一维表示相转化
 * 二维采用屏幕坐标系
 * 一维采用电子管扫描方式
 *
 * 实质上是不可变类;不用担心线程安全
 *
 * @author Keyto
 */
public final class BlockIndex implements IBlockIndex {

    //缓存所有此类对象
    private static final BlockIndex[] BIS = new BlockIndex[Specifications.CHUNK_CAPACITY];

    //静态方法块：初始化缓存
    static {
        for (int i = 0; i < Specifications.SIDE_LENGTH; i++) {
            for (int j = 0; j < Specifications.SIDE_LENGTH; j++) {
                BIS[i * Specifications.SIDE_LENGTH + j] = new BlockIndex(j, i);
            }
        }
    }

    /**
     * 模仿基本类型封装类
     *
     * @param index 区块中坐标序号
     * @return 坐标序号对应对象
     */
    public static BlockIndex valueof(int index) {
        index = index & (Specifications.CHUNK_CAPACITY - 1);
        return BIS[index];
    }

    /**
     * 模仿基本类型封装类
     *
     * @param x 区块中x坐标
     * @param y 区块中y坐标
     * @return 坐标序号对应对象
     */
    public static BlockIndex valueof(int x, int y) {
        x &= Specifications.SIDE_LENGTH - 1;
        y &= Specifications.SIDE_LENGTH - 1;
        int index = (y << Specifications.SIDE_EXPONENT) + x;
        return BIS[index];
    }

    //BlockIndex属性;
    private int x;
    private int y;

    public BlockIndex(int x, int y) {
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
     * @return 区块中坐标序号
     */
    @Override
    public int getIndex() {
        return (this.y << Specifications.SIDE_EXPONENT) + this.x;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final BlockIndex other = (BlockIndex) obj;
        if (this.x != other.x) return false;
        if (this.y != other.y) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.x;
        hash = 29 * hash + this.y;
        return hash;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + this.x + "," + this.y + "]";
    }

}
