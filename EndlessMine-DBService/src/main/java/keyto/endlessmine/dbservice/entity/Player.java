/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
