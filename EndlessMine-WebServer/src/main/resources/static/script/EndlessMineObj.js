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

/*
 //示例JSON
 var block = {
 "blockPoint": {"chunkPoint": {"x": 1, "y": 2},
 "blockIndex": {"x": 3, "y": 0}},
 "blockInfo": {"searched": true, "bomb": true, "bombCount": 2, "click": "SECONDARY", "userID": 1}
 };
 */
//MouseButton枚举，本项目只有PRIMARY，SECONDARY有效值
if (typeof MouseButton === "undefined") {
    var MouseButton = {
        NONE: "NONE",
        PRIMARY: "PRIMARY",
        MIDDLE: "MIDDLE",
        SECONDARY: "SECONDARY"
    };
}
function ChunkPoint(chunkPoint) {
    this.x = chunkPoint.x;
    this.y = chunkPoint.y;
}
function BlockIndex(blockIndex) {
    this.x = blockIndex.x;
    this.y = blockIndex.y;
}
function BlockPoint(blockPoint) {
    this.chunkPoint = new ChunkPoint(blockPoint.chunkPoint);
    this.blockIndex = new BlockIndex(blockPoint.blockIndex);
}
function BlockInfo(blockInfo) {
    this.searched = blockInfo.searched;
    this.bomb = blockInfo.bomb;
    this.bombCount = blockInfo.bombCount;
    this.click = blockInfo.click;
    this.userID = blockInfo.userID;
}
function Block(block) {
    this.blockPoint = new BlockPoint(block.blockPoint);
    this.blockInfo = new BlockInfo(block.blockInfo);
}

//测试Git
BlockInfo.prototype = {
    constructor: BlockInfo,
    isSearched: function () {
        return this.searched;
    },
    isBomb: function () {
        return this.bomb;
    },
    getClick: function () {
        return this.click;
    },
    getBombCount: function () {
        return this.bombCount;
    },
    getPic: function () {
        if (!this.isSearched()) {
            return "/image/BMP_BLANK.bmp";
        } else if (this.isBomb()) {
            if (this.getClick() === MouseButton.SECONDARY) {
                //TODO:flag换成个人头像
                return "/image/BMP_FLAG.bmp";
            } else {
                return "/image/BMP_BLOOD.bmp";
            }
        } else if (this.getClick() !== MouseButton.SECONDARY) {
            return this.getPicByCount(this.getBombCount());
        } else {
            //TODO:选择错误；扣分处理；server端处理
            return this.getPicByCount(this.getBombCount());
        }
    },
    getPicByCount: function (count) {
        switch (count) {
            case 0:
                return "/image/BMP_0.bmp";
            case 1:
                return "/image/BMP_1.bmp";
            case 2:
                return "/image/BMP_2.bmp";
            case 3:
                return "/image/BMP_3.bmp";
            case 4:
                return "/image/BMP_4.bmp";
            case 5:
                return "/image/BMP_5.bmp";
            case 6:
                return "/image/BMP_6.bmp";
            case 7:
                return "/image/BMP_7.bmp";
            case 8:
                return "/image/BMP_8.bmp";
            default:
                return "/image/BMP_ASK.bmp";
        }
    }
};

