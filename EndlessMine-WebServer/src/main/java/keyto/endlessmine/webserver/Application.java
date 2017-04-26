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
package keyto.endlessmine.webserver;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import keyto.endlessmine.common.block.IBlock;
import keyto.endlessmine.common.block.IBlockInfo;
import keyto.endlessmine.common.coordinate_system.impl.BlockPoint;
import keyto.endlessmine.common.coordinate_system.impl.ChunkPoint;
import keyto.endlessmine.common.mouse.MouseButton;
import keyto.endlessmine.dbservice.service.UserInfoService;
import keyto.endlessmine.gameserver.manager.BlockManager;
import keyto.endlessmine.webserver.eneity.RequestA;
import keyto.endlessmine.webserver.eneity.RequestDoActive;
import keyto.endlessmine.webserver.eneity.RequestGetChunk;
import keyto.endlessmine.webserver.eneity.ResponseA;
import keyto.endlessmine.webserver.eneity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Keyto
 */
@Controller
@SpringBootApplication
public class Application {

    @Autowired
    UserInfoService userInfoService;

    BlockManager blockManager = new BlockManager();

    @RequestMapping({"", "/"})
    String home() {
        userInfoService.save("kkkk", "aa@as", "qqq");
        return "index";
    }

    @RequestMapping("loginPage")
    String loginPage() {
        return "loginPage";
    }

    @RequestMapping("do_login")
    String login(User user, HttpSession session) {
        session.setAttribute("user", user);
        System.out.println("do_login");
        System.out.println(user);
        return "success";
    }

    @RequestMapping("showPage")
    String showPage() {
        return "showPage";
    }

    @RequestMapping("demo_test_show")
    @ResponseBody
    List<IBlock> demo_test_show(RequestDoActive requestDoActive) {
        System.out.println("demo_test_show");
        System.out.println(requestDoActive);
//        Object attribute = (User)session.getAttribute("user");
        ChunkPoint chunkPoint = new ChunkPoint(requestDoActive.getChunkPointX(), requestDoActive.getChunkPointY());
        BlockPoint blockPoint = new BlockPoint(chunkPoint, requestDoActive.getBlockIndex());
        MouseButton mouseButton = MouseButton.valueOf(requestDoActive.getMouseButton());
        List<IBlock> doActionResult = blockManager.doAction(blockPoint, mouseButton, 1);
        return doActionResult;
    }

    @RequestMapping("demo_test_show_chunk")
    @ResponseBody
    IBlockInfo[] demo_test_show_chunk(RequestGetChunk requestGetChunk) {
        ChunkPoint chunkPoint = new ChunkPoint(requestGetChunk.getChunkPointX(), requestGetChunk.getChunkPointY());

        return blockManager.getEntireBlockInfosOfChunk(chunkPoint);
    }

    @RequestMapping({"demo_test_post"})
    @ResponseBody
    ResponseA[] demo_test_post(RequestA requestA) {
        System.out.println("demo_test_post");
        System.out.println("request:" + requestA);
        ResponseA[] ra = new ResponseA[]{
            new ResponseA("qw", 1, new Date()),
            new ResponseA("as", 2, new Date()),
            new ResponseA("zx", 3, new Date())
        };
        return ra;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
