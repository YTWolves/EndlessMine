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
package keyto.endlessmine.gameserver.manager;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import keyto.endlessmine.common.block.IBlock;
import keyto.endlessmine.common.block.IBlockInfo;
import keyto.endlessmine.common.block.impl.Block;
import keyto.endlessmine.common.block.impl.BlockInfo;
import keyto.endlessmine.common.chunk.IChunk;
import keyto.endlessmine.common.coordinate_system.EnumDirection;
import keyto.endlessmine.common.coordinate_system.IBlockPoint;
import keyto.endlessmine.common.coordinate_system.IChunkPoint;
import keyto.endlessmine.common.coordinate_system.impl.BlockPoint;
import keyto.endlessmine.common.mouse.MouseButton;
import keyto.endlessmine.gameserver.chunk.ChunkFlyweightFactory;

/**
 *
 * @author Keyto
 */
public class BlockManager {

    private static final ChunkFlyweightFactory CF = ChunkFlyweightFactory.getInstance();

    private IBlock getBlock(IBlockPoint blockPoint) {
        IChunk chunk = CF.getChunk(blockPoint.getChunkPoint());
        return chunk.getBlock(blockPoint);
    }

    private List<IBlock> getBlocks(List<IBlock> blocks) {
        List<IBlock> newList = new LinkedList<>();
        for (IBlock block : blocks) {
            newList.add(getBlock(block.getBlockPoint()));
        }
        return newList;
    }

    private IBlockInfo getBlockInfo(BlockPoint blockPoint) {
        IBlock block = getBlock(blockPoint);
        return block.getBlockInfo();
    }

    private int getBombCount(IBlockPoint blockPoint) {
        int count = 0;
        for (EnumDirection ed : EnumDirection.values()) {
            if (getIsBomb(blockPoint.getAroundBPoint(ed))) {
                ++count;
            }
        }
        return count;
    }

    private boolean getIsBomb(IBlockPoint blockPoint) {
        IChunk chunk = CF.getChunk(blockPoint.getChunkPoint());
        return chunk.getIsBomb(blockPoint.getBlockIndex().getIndex());
    }

    private List<IBlock> writeBlockIDs(List<IBlock> blocks) {
        List<IBlock> result = new LinkedList<>();
        for (IBlock block : blocks) {
            if (writeBlockID(block)) {
                result.add(block);
            }
        }
        return result;
    }

    /**
     * 仅有的写数据方法
     *
     * @param blockPoint
     * @param blockID
     * @return true Write Success
     *         false Write Failed
     */
    private boolean writeBlockID(IBlock block) {
        IChunk chunk = CF.getChunk(block.getBlockPoint().getChunkPoint());
        return chunk.setBlock(block);
    }

    /**
     * 操作数据方法
     *
     * @param blockPoint
     * @param mouseButton
     * @param usrID
     * @return
     */
    public List<IBlock> doAction(IBlockPoint blockPoint, MouseButton mouseButton, Long usrID) {
//        //输出接收数据
//        System.out.println("doAction:" + blockPoint + "," + mouseButton + "," + usrID);
        //获得八通域
        List<IBlockPoint> points = eightConnectedRegion(blockPoint);
        //装配格子
        List<IBlock> results = assembleBlocks(points, mouseButton, usrID);
        //写入格子信息
        results = writeBlockIDs(results);

        return results;

    }

    /**
     * 装配Block
     *
     * @param ccs         BlockPoint列表
     * @param mouseButton 按键枚举,允许值PRIMARY:单击,SECONDARY:右击
     * @param usrID       用户ID
     * @return
     */
    private List<IBlock> assembleBlocks(List<IBlockPoint> ccs, MouseButton mouseButton, Long userID) {
        List<IBlock> results = new LinkedList<>();
        for (IBlockPoint blockPoint : ccs) {
            //TODO:替换为工厂？
            IBlock block = new Block(blockPoint, BlockInfo.EMPTY());
            IBlockInfo blockInfo = block.getBlockInfo();
            blockInfo.setSearched(true);
            blockInfo.setBomb(getIsBomb(blockPoint));
            blockInfo.setBombCount(getBombCount(blockPoint));
            blockInfo.setClick(mouseButton);
            blockInfo.setUserID(userID);

            results.add(block);
        }
        return results;

    }

    /**
     * 八通域算法
     *
     * @param seed
     * @return
     */
    private List<IBlockPoint> eightConnectedRegion(IBlockPoint seed) {

        Set<IBlockPoint> result = new LinkedHashSet<>();
        result.add(seed);
        Stack<IBlockPoint> sk = new Stack<>();
        sk.push(seed);
        while (!sk.empty()) {
            IBlockPoint tmp_seed = sk.pop();
            if (getIsZero(tmp_seed)) {
                for (EnumDirection ed : EnumDirection.values()) {
                    IBlockPoint tmp_BlockPoint = tmp_seed.getAroundBPoint(ed);
                    //如果未检查过，压入堆栈
                    if (result.add(tmp_BlockPoint)) {
                        sk.push(tmp_BlockPoint);
                    }
                }
            }
        }

//        //输出计算结果
//        System.out.println("+++++++++++++++++++++++++++++++++++");
//        System.out.println("result:" + result.toString());
//        System.out.println("sk:" + sk.toString());
//        System.out.println("===================================");
        return new LinkedList<>(result);

    }

    /**
     * 二值化格子
     *
     * @param blockPoint 格子坐标
     * @return true 格子周围33无雷
     *         false 格子周围33有雷
     */
    private boolean getIsZero(IBlockPoint blockPoint) {
        return getBombCount(blockPoint) == 0;
    }

    /**
     * 获得指定区块中所有Block
     *
     * @param chunkPoint 区块坐标
     * @return BlockID数组
     */
    public IBlockInfo[][] getEntireBlockInfosOfChunk(IChunkPoint chunkPoint) {
        IChunk ck = CF.getChunk(chunkPoint);
        return ck.getBlockInfos();
    }
}
