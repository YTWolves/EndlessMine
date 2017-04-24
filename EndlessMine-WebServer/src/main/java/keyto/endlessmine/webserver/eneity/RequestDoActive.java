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
package keyto.endlessmine.webserver.eneity;

/**
 *
 * @author Keyto
 */
public class RequestDoActive {

    private int chunkPointX;
    private int chunkPointY;
    private int blockIndex;
    private String mouseButton;

    /**
     * @return the chunkPointX
     */
    public int getChunkPointX() {
        return chunkPointX;
    }

    /**
     * @param chunkPointX the chunkPointX to set
     */
    public void setChunkPointX(int chunkPointX) {
        this.chunkPointX = chunkPointX;
    }

    /**
     * @return the chunkPointY
     */
    public int getChunkPointY() {
        return chunkPointY;
    }

    /**
     * @param chunkPointY the chunkPointY to set
     */
    public void setChunkPointY(int chunkPointY) {
        this.chunkPointY = chunkPointY;
    }

    /**
     * @return the blockIndex
     */
    public int getBlockIndex() {
        return blockIndex;
    }

    /**
     * @param blockIndex the blockIndex to set
     */
    public void setBlockIndex(int blockIndex) {
        this.blockIndex = blockIndex;
    }

    /**
     * @return the mouseButton
     */
    public String getMouseButton() {
        return mouseButton;
    }

    /**
     * @param mouseButton the mouseButton to set
     */
    public void setMouseButton(String mouseButton) {
        this.mouseButton = mouseButton;
    }

    @Override
    public String toString() {
        return super.toString()
                + " [ " + chunkPointX + "," + chunkPointY + "," + blockIndex + "," + mouseButton + " ]";
    }

}
