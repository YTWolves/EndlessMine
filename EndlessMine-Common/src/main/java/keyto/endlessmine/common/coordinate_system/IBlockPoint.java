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
public interface IBlockPoint {

    /**
     * 按照枚举方位获得33坐标
     *
     * @param ed 方位枚举
     * @return 33内坐标
     */
    IBlockPoint getAroundBPoint(EnumDirection ed);

    /**
     * 按照相对坐标获得绝对坐标
     *
     * @param rc 相对坐标
     * @return 相对于此坐标的绝对坐标
     */
    IBlockPoint getBPointByRC(RelativeCoordinate rc);

    /**
     * @return 区块坐标
     */
    IChunkPoint getChunkPoint();

    /**
     *
     * @return The blockIndex in Chunk
     */
    IBlockIndex getBlockIndex();
    
}
