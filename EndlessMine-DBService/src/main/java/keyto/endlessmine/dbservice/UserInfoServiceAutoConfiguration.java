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
package keyto.endlessmine.dbservice;

import keyto.endlessmine.dbservice.repository.UserInfoRepository;
import keyto.endlessmine.dbservice.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Keyto
 */
@Configuration
@ConditionalOnClass(UserInfoService.class)
@EntityScan("keyto.endlessmine.dbservice.entity")
@EnableJpaRepositories(basePackageClasses = UserInfoRepository.class)
public class UserInfoServiceAutoConfiguration {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Bean
    @ConditionalOnMissingBean(UserInfoService.class)
    public UserInfoService userInfoService() {
        UserInfoService userInfoService = new UserInfoService();
        userInfoService.setUserInfoRepository(userInfoRepository);
        return userInfoService;
    }
}
