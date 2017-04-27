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
package keyto.endlessmine.webserver.controller;

import java.util.List;
import keyto.endlessmine.common.block.IBlock;
import keyto.endlessmine.common.block.IBlockInfo;
import keyto.endlessmine.common.coordinate_system.impl.BlockPoint;
import keyto.endlessmine.common.coordinate_system.impl.ChunkPoint;
import keyto.endlessmine.common.mouse.MouseButton;
import keyto.endlessmine.gameserver.manager.BlockManager;
import keyto.endlessmine.webserver.eneity.RequestDoActive;
import keyto.endlessmine.webserver.eneity.RequestGetChunk;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Keyto
 */
@Controller
@RequestMapping("/game")
public class GameController {

    BlockManager blockManager = new BlockManager();

    @RequestMapping("/demo_test_show")
    @ResponseBody
    List<IBlock> demo_test_show(RequestDoActive requestDoActive) {
        System.out.println("/demo_test_show");
        System.out.println(requestDoActive);
//        Object attribute = (MsgDoLogin)session.getAttribute("user");
        ChunkPoint chunkPoint = new ChunkPoint(requestDoActive.getChunkPointX(), requestDoActive.getChunkPointY());
        BlockPoint blockPoint = new BlockPoint(chunkPoint, requestDoActive.getBlockIndex());
        MouseButton mouseButton = MouseButton.valueOf(requestDoActive.getMouseButton());
        List<IBlock> doActionResult = blockManager.doAction(blockPoint, mouseButton, 1);
        return doActionResult;
    }

    @RequestMapping("/demo_test_show_chunk")
    @ResponseBody
    IBlockInfo[] demo_test_show_chunk(RequestGetChunk requestGetChunk) {
        System.out.println("/demo_test_show_chunk");
        ChunkPoint chunkPoint = new ChunkPoint(requestGetChunk.getChunkPointX(), requestGetChunk.getChunkPointY());

        return blockManager.getEntireBlockInfosOfChunk(chunkPoint);
    }
}
