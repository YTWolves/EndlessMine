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
package keyto.endlessmine.gameserver.chunk;

import java.util.BitSet;
import java.util.Random;
import keyto.endlessmine.common.coordinate_system.IChunkPoint;
import keyto.endlessmine.gameserver.chunk.impl.WriteableChunk;

/**
 * 区块生成器
 *
 * @author Keyto
 */
public class ChunkGenerator {

    public static final int DEFAULT_BOMB_COUNT = 190;

    /**
     * 根据区块坐标生成区块
     *
     * @param chunkPoint
     * @return 区块实例
     */
    public static WriteableChunk build(IChunkPoint chunkPoint) {
        long seed = getSeed(chunkPoint);
        Random random = new Random(seed);
        BitSet bitSet = new BitSet(1024);
        for (int i = 0; i < DEFAULT_BOMB_COUNT; i++) {
            bitSet.set(random.nextInt(1024));
        }
        WriteableChunk chunk = new WriteableChunk(chunkPoint, bitSet);
        return chunk;
    }

    /**
     * 按照区块坐标获得随机种子
     *
     * @param chunkPoint 区块坐标
     * @return 随机种子
     */
    private static long getSeed(IChunkPoint chunkPoint) {
        long seed = chunkPoint.getX();
        seed <<= 32;
        seed |= chunkPoint.getY();
        return seed;
    }

}
