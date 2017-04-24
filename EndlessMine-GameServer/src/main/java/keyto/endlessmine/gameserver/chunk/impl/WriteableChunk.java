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

import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;
import keyto.endlessmine.common.block.IBlock;
import keyto.endlessmine.common.block.IBlockInfo;
import keyto.endlessmine.common.block.impl.Block;
import keyto.endlessmine.common.block.impl.BlockInfo;
import keyto.endlessmine.common.chunk.IChunk;
import keyto.endlessmine.common.coordinate_system.IBlockPoint;
import keyto.endlessmine.common.coordinate_system.IChunkPoint;
import keyto.endlessmine.common.coordinate_system.Specifications;

/**
 * 可读写区块类
 *
 * @author Keyto
 */
public class WriteableChunk implements IChunk {

    private final IChunkPoint cPoint;
    private final AtomicLongArray blockSerialize;
    private final BitSet battlefield;
    private final AtomicInteger finishCount = new AtomicInteger();

    public WriteableChunk(IChunkPoint cPoint, BitSet battlefield) {
        this(cPoint, battlefield, 0);
    }

    public WriteableChunk(IChunkPoint cPoint, BitSet battlefield, int finishCount) {
        this.cPoint = cPoint;
        this.blockSerialize = new AtomicLongArray(Specifications.CHUNK_CAPACITY);
        this.battlefield = battlefield;
        this.finishCount.set(finishCount);
    }

    /**
     * @return the chunkPoint
     */
    @Override
    public IChunkPoint getCPoint() {
        return cPoint;
    }

    @Override
    public Block getBlock(IBlockPoint blockPoint) {
        long blockId = blockSerialize.get(blockPoint.getBlockIndex().getIndex());
        return new Block(blockPoint, blockId);
    }

    @Override
    public BlockInfo getBlockInfo(int index) {
        long blockId = blockSerialize.get(index);
        return new BlockInfo(blockId);
    }

    @Override
    public boolean getIsBomb(int index) {
        return battlefield.get(index);
    }

    @Override
    public IBlockInfo[] getBlockInfos() {
        IBlockInfo[] result = new IBlockInfo[Specifications.CHUNK_CAPACITY];
        for (int i = 0; i < Specifications.CHUNK_CAPACITY; i++) {
            long tmp=blockSerialize.get(i);
            result[i] = new BlockInfo(tmp);
        }
        return result;
    }

    /**
     * 把格子信息写入区块
     *
     * @param block 格子
     * @return true 写入成功
     *         false 写入失败
     */
    @Override
    public boolean setBlockID(IBlock block) {
        int index = block.getBlockPoint().getBlockIndex().getIndex();
        long blockID = block.getBlockInfo().toBlockID();
        boolean success = this.blockSerialize.compareAndSet(index, 0L, blockID);
        if (success) {
            finishCount.getAndIncrement();
        }
        return success;
    }

    /**
     * 区块已经结束
     *
     * @return true 区块已经被扫干净
     *         false 区块未被扫干净
     */
    @Override
    public boolean isFinished() {
        return finishCount.get() == 1024;
    }

}
