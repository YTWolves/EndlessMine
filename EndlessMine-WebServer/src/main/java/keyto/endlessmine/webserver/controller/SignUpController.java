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

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> origin/master
import javax.servlet.http.HttpServletRequest;
import keyto.endlessmine.dbservice.entity.Player;
import keyto.endlessmine.dbservice.entity.SysRole;
import keyto.endlessmine.dbservice.service.PlayerService;
import keyto.endlessmine.webserver.massage.MsgDoLogin;
import keyto.endlessmine.webserver.massage.MsgSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Keyto
 */
@Controller
@RequestMapping("/signUp")
public class SignUpController {

<<<<<<< HEAD
=======
    private static final List<SysRole> DEFAULT_ROLES;

    static {
        DEFAULT_ROLES = new ArrayList<>();
        DEFAULT_ROLES.add(new SysRole("ROLE_USER"));
        DEFAULT_ROLES.add(new SysRole("ROLE_PLAYER"));
    }

>>>>>>> origin/master
    @Autowired
    PlayerService playerService;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @RequestMapping("")
    String signUp() {
        return "signUp";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    ModelAndView signUp(MsgSignUp msgSignUp, HttpServletRequest request) {
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
            Player player = new Player(null, msgSignUp.getName(), msgSignUp.getEmail(), msgSignUp.getPassword());
<<<<<<< HEAD
=======
            player.setRoles(DEFAULT_ROLES);
>>>>>>> origin/master
            Player save = playerService.save(player);
            MsgDoLogin msgDoLogin = new MsgDoLogin();
            msgDoLogin.setUsername(msgSignUp.getName());
            msgDoLogin.setPassword(msgSignUp.getPassword());
            return loginAfterSignUp(msgDoLogin, request);
        } catch (final Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private ModelAndView loginAfterSignUp(MsgDoLogin msgDoLogin,
                                          HttpServletRequest request) {
        System.out.println("loginAfterSignUp");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                msgDoLogin.getUsername(), msgDoLogin.getPassword());

        //request.getSession();
        try {
            token.setDetails(new WebAuthenticationDetails(request));
            Authentication authenticatedUser = authenticationManager
                    .authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return new ModelAndView(new RedirectView("login?error"));
        }

        return new ModelAndView(new RedirectView(""));
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
