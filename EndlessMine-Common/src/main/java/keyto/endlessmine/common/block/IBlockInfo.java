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
package keyto.endlessmine.common.block;

import keyto.endlessmine.common.mouse.MouseButton;

/**
 *
 * @author Keyto
 */
public interface IBlockInfo {

    /**
     * @return the bombCount
     */
    int getBombCount();

    /**
     * @return the click
     */
    MouseButton getClick();

    /**
     * @return the usrID
     */
    long getUsrID();

    /**
     * @return the bomb
     */
    boolean isBomb();

    /**
     * @return the searched
     */
    boolean isSearched();

    /**
     * @param bomb the bomb to set
     */
    void setBomb(boolean bomb);

    /**
     * @param bombCount the bombCount to set
     */
    void setBombCount(int bombCount);

    /**
     * @param click the click to set
     */
    void setClick(MouseButton click);

    /**
     * @param searched the searched to set
     */
    void setSearched(boolean searched);

    /**
     * @param usrID the usrID to set
     */
    void setUsrID(long usrID);

    long toBlockID();

}
