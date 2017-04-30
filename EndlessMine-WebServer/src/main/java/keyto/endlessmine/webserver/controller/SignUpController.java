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
import keyto.endlessmine.webserver.massage.MsgSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Keyto
 */
@Controller
@RequestMapping("/signUp")
public class SignUpController {
    
    @Autowired
    PlayerService playerService;
    
    @RequestMapping("")
    String signUp() {
        return "signUp";
    }
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    ModelAndView signUp(MsgSignUp msgSignUp, HttpSession session) {
        System.out.println("signUp:" + msgSignUp);
        if (msgSignUp.getName().equals("")
                || msgSignUp.getName().matches("[@]")
                || msgSignUp.getPassword().equals("")
                || existsByName(msgSignUp.getName())
                || existsByEmail(msgSignUp.getEmail())) {
            ModelAndView mv = new ModelAndView("signUp");
            mv.addObject("name", msgSignUp.getName());
            mv.addObject("email", msgSignUp.getEmail());
            return mv;
        }
        try {
            Player save = playerService.save(msgSignUp.getName(), msgSignUp.getEmail(), msgSignUp.getPassword(), 0L);
            ModelAndView mv = new ModelAndView("forward:/login");
            mv.addObject("name", save.getName());
            mv.addObject("password", save.getPassword());
            return mv;
        } catch (final Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    @RequestMapping(value = "/existsByName")
    @ResponseBody
    Boolean existsByName(String name) {
        return playerService.existsByName(name);
    }
    
    @RequestMapping(value = "/existsByEmail")
    @ResponseBody
    Boolean existsByEmail(String email) {
        return playerService.existsByEmail(email);
    }
}
