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
package keyto.endlessmine.common.block.impl;

import java.util.Objects;
import keyto.endlessmine.common.block.IBlockInfo;
import keyto.endlessmine.common.mouse.MouseButton;

/**
 *
 * @author Keyto
 */
public class BlockInfo implements IBlockInfo {

    private boolean searched = false;
    private boolean bomb = false;
    private int bombCount = 0;
    private MouseButton click = MouseButton.NONE;
    private long userID = 0;

    public BlockInfo(long blockID) {
        this.userID = blockID & 0xFF_FFFF_FFFF_FFFFL;
        this.click = MouseButton.getMouseButton((int) ((blockID >> 56) & 0b11));
        this.bombCount = (int) ((blockID >> 58) & 0b1111);
        this.bomb = ((blockID >> 62) & 0b1) != 0;
        this.searched = ((blockID >> 63) & 0b1) != 0;
    }

    /**
     * @return the searched
     */
    @Override
    public boolean isSearched() {
        return searched;
    }

    /**
     * @param searched the searched to set
     */
    @Override
    public void setSearched(boolean searched) {
        this.searched = searched;
    }

    /**
     * @return the bomb
     */
    @Override
    public boolean isBomb() {
        return bomb;
    }

    /**
     * @param bomb the bomb to set
     */
    @Override
    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    /**
     * @return the bombCount
     */
    @Override
    public int getBombCount() {
        return bombCount;
    }

    /**
     * @param bombCount the bombCount to set
     */
    @Override
    public void setBombCount(int bombCount) {
        this.bombCount = bombCount & 0b1111;
    }

    /**
     * @return the click
     */
    @Override
    public MouseButton getClick() {
        return click;
    }

    /**
     * @param click the click to set
     */
    @Override
    public void setClick(MouseButton click) {
        this.click = click;
    }

    /**
     * @return the userID
     */
    @Override
    public long getUserID() {
        return userID;
    }

    /**
     * @param usrID the userID to set
     */
    @Override
    public void setUserID(long userID) {
        this.userID = userID;
    }

    /**
     * 获得空BlockInfo
     *
     * @return
     */
    public static BlockInfo EMPTY() {
        return new BlockInfo(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final BlockInfo other = (BlockInfo) obj;
        if (this.searched != other.searched) return false;
        if (this.bomb != other.bomb) return false;
        if (this.bombCount != other.bombCount) return false;
        if (this.userID != other.userID) return false;
        if (this.click != other.click) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.searched ? 1 : 0);
        hash = 29 * hash + (this.bomb ? 1 : 0);
        hash = 29 * hash + this.bombCount;
        hash = 29 * hash + Objects.hashCode(this.click);
        hash = 29 * hash + (int) (this.userID ^ (this.userID >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + this.searched + ","
                + this.bomb + ","
                + this.bombCount + ","
                + this.click + ","
                + this.userID + " ]";
    }

    @Override
    public long toBlockID() {
        long blockID = 0L;
        if (this.searched) {
            blockID |= 0b1L;
        }
        blockID <<= 1;
        if (this.bomb) {
            blockID |= 0b1L;
        }
        blockID <<= 4;
        blockID |= this.bombCount;
        blockID <<= 2;
        blockID |= this.click.getMouseKeyValue();
        blockID <<= 56;
        blockID |= ((long) this.userID) & 0xFF_FFFF_FFFF_FFFFL;
        return blockID;
    }
}
