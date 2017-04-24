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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import keyto.endlessmine.common.chunk.IChunk;
import keyto.endlessmine.common.coordinate_system.IChunkPoint;

/**
 * 区块管理类
 * 利用享元保存区块
 *
 * @author Keyto
 */
public class ChunkFlyweightFactory {

    //基本的单子例模式
    private static final ChunkFlyweightFactory CHUNK_FACTORY = new ChunkFlyweightFactory();

    private ChunkFlyweightFactory() {
        chunks = new ConcurrentHashMap<>();
    }

    public static ChunkFlyweightFactory getInstance() {
        return CHUNK_FACTORY;
    }

    //享元模式
    private final Map<IChunkPoint, IChunk> chunks;

    public IChunk getChunk(IChunkPoint chunkPoint) {
        return chunks.computeIfAbsent(chunkPoint, (key) -> {
                                  return creatNewChunk(key);
                              });
    }

    public int getFlyweightSize() {
        return chunks.size();
    }

    private IChunk creatNewChunk(IChunkPoint chunkPoint) {
        IChunk chunk = null;
        //TODO:从weakhashmap查询已完成的
        //TODO:从数据库中查询;加载本地文件

        if (null == chunk) {
            //从地图生成器生成
            chunk = ChunkGenerator.build(chunkPoint);
        }
        return chunk;
    }

}
