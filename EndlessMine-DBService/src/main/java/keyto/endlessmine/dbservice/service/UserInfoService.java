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
package keyto.endlessmine.dbservice.service;

import keyto.endlessmine.dbservice.entity.UserInfo;
import keyto.endlessmine.dbservice.repository.UserInfoRepository;

/**
 *
 * @author Keyto
 */
public class UserInfoService {

    private UserInfoRepository userInfoRepository;

    /**
     * @return the userInfoRepository
     */
    public UserInfoRepository getUserInfoRepository() {
        return userInfoRepository;
    }

    /**
     * @param userInfoRepository the userInfoRepository to set
     */
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo save(String userName, String email, String password) {

        UserInfo userInfo = userInfoRepository.save(new UserInfo(null, userName, email, password));
        System.out.println("res:" + userInfo);
        return userInfo;
    }
}
