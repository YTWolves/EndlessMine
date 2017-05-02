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
package keyto.endlessmine.gameserver.chunk.impl;

import keyto.endlessmine.common.block.IBlock;
import keyto.endlessmine.common.block.IBlockInfo;
import keyto.endlessmine.common.block.impl.Block;
import keyto.endlessmine.common.block.impl.BlockInfo;
import keyto.endlessmine.common.chunk.IChunk;
import keyto.endlessmine.common.coordinate_system.IBlockPoint;
import keyto.endlessmine.common.coordinate_system.Specifications;
import keyto.endlessmine.common.coordinate_system.impl.ChunkPoint;

/**
 * 已经完成的区块用此装载
 * 不能写入数据
 *
 * @author Keyto
 */
public class SimpleChunk implements IChunk {

    private final ChunkPoint cPoint;
    private final long[] blockIDs = new long[Specifications.CHUNK_CAPACITY];

    public SimpleChunk(ChunkPoint cPoint) {
        this.cPoint = cPoint;
    }

    @Override
    public IBlock getBlock(IBlockPoint blockPoint) {
        long blockId = blockIDs[blockPoint.getBlockIndex().getIndex()];
        return new Block(blockPoint, blockId);
    }

    @Override
    public IBlockInfo getBlockInfo(int index) {
        long blockId = blockIDs[index];
        return new BlockInfo(blockId);
    }

    @Override
    public IBlockInfo[] getBlockInfos() {
        IBlockInfo[] copy_blockInfos = new IBlockInfo[Specifications.CHUNK_CAPACITY];
        for (int i = 0; i < Specifications.CHUNK_CAPACITY; i++) {
            copy_blockInfos[i] = new BlockInfo(blockIDs[i]);
        }
        return copy_blockInfos;
    }

    @Override
    public ChunkPoint getCPoint() {
        return cPoint;
    }

    @Override
    public boolean getIsBomb(int index) {
        return new BlockInfo(blockIDs[index]).isBomb();
    }

    @Override
    public boolean setBlock(IBlock block) {
        return false;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
