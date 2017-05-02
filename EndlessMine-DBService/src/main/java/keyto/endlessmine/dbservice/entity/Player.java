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
package keyto.endlessmine.dbservice.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;

/**
 *
 * @author Keyto
 */
@Entity
public class Player extends UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long score;

    public Player() {
    }

    public Player(Long id, String name, String email, String password) {
        super(id, name, email, password);
        this.score = 0L;
    }

    public Player(Long id, String name, String email, String password, Long score) {
        super(id, name, email, password);
        this.score = score;
    }

    /**
     * @return the score
     */
    public Long getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.score);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Player other = (Player) obj;
        if (!Objects.equals(this.score, other.score)) return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString()
                + " [ score=" + score
                + " ]";
    }

}
