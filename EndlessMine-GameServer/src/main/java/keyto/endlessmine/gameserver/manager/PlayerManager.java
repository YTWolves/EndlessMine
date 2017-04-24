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

/**
 *
 * @author Keyto
 */
public class PlayerManager {
//
//    public UserInfo loginWithName(String name, String passwd) {
//        //检查参数有效
//        if (!checkStringValid(name)) {
//            return null;
//        }
//        if (!checkStringValid(passwd)) {
//            return null;
//        }
//        //密码转换为MD5
//        passwd = MD5Util.MD5(passwd);
//        //判断是否存在
//        UserInfo info = null;
//        if (UserInfoService.isExistsByNameAndPwd(name, passwd)) {
//            info = UserInfoService.getUserInfoByNameAndPwd(name, passwd);
//        }
//
//        return info;
//    }
//
//    public UserInfo loginWithEmail(String email, String passwd) {
//        //检查参数有效
//        if (!checkStringValid(email)) {
//            return null;
//        }
//        if (!checkStringValid(passwd)) {
//            return null;
//        }
//        //密码转换为MD5
//        passwd = MD5Util.MD5(passwd);
//        //判断是否存在
//        UserInfo info = null;
//        if (UserInfoService.isExistsByEmailAndPwd(email, passwd)) {
//            info = UserInfoService.getUserInfoByEmailAndPwd(email, passwd);
//        }
//
//        return info;
//    }
//
//    public static boolean checkStringValid(String str) {
//        return !(null == str || str.isEmpty());
//    }
//
//    public static void main(String[] args) {
//        PlayerManager manager = new PlayerManager();
//        System.out.println(manager.loginWithName("我是6", "12346"));
//        System.out.println(manager.loginWithName("我是7", "12347"));
//        System.out.println(manager.loginWithEmail("email@5", "12345"));
//        System.out.println(manager.loginWithEmail("email@4", "12344"));
//    }
}
