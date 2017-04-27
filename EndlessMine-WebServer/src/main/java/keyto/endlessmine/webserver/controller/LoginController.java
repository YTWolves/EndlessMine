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

import javax.servlet.http.HttpSession;
import keyto.endlessmine.dbservice.entity.Player;
import keyto.endlessmine.dbservice.service.PlayerService;
import keyto.endlessmine.webserver.massage.MsgDoLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Keyto
 */
@Controller
public class LoginController {

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "login")
    String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    Player do_login(MsgDoLogin doLogin, HttpSession session) {
        Player player = playerService.findByNameAndPassword(doLogin.getName(), doLogin.getPassword());
        return player;
    }
}
