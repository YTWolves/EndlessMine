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
//MinePicEnum，枚举格子图片
if (typeof MinePicEnum === "undefined") {
    var MinePicEnum = {
        BMP_0: "/image/0.bmp",
        BMP_1: "/image/1.bmp",
        BMP_2: "/image/2.bmp",
        BMP_3: "/image/3.bmp",
        BMP_4: "/image/4.bmp",
        BMP_5: "/image/5.bmp",
        BMP_6: "/image/6.bmp",
        BMP_7: "/image/7.bmp",
        BMP_8: "/image/8.bmp",
        BMP_ASK: "/image/ask.bmp",
        BMP_BLANK: "/image/blank.bmp",
        BMP_BLOOD: "/image/blood.bmp",
        BMP_ERROR: "/image/error.bmp",
        BMP_FLAG: "/image/flag.bmp",
        BMP_MINE: "/image/mine.bmp"
    };
}
function ChunkPoint(chunkPoint) {
    this.x = chunkPoint.x;
    this.y = chunkPoint.y;
}
function BlockIndex(blockIndex) {
    this.x = blockIndex.x;
    this.y = blockIndex.y;
    this.index = blockIndex.index;
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
            return MinePicEnum.BMP_BLANK;
        } else if (this.isBomb()) {
            if (this.getClick() === MouseButton.SECONDARY) {
                //TODO:flag换成个人头像
                return MinePicEnum.BMP_FLAG;
            } else {
                return MinePicEnum.BMP_BLOOD;
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
                return MinePicEnum.BMP_0;
            case 1:
                return MinePicEnum.BMP_1;
            case 2:
                return MinePicEnum.BMP_2;
            case 3:
                return MinePicEnum.BMP_3;
            case 4:
                return MinePicEnum.BMP_4;
            case 5:
                return MinePicEnum.BMP_5;
            case 6:
                return MinePicEnum.BMP_6;
            case 7:
                return MinePicEnum.BMP_7;
            case 8:
                return MinePicEnum.BMP_8;
            default:
                return MinePicEnum.BMP_ASK;
        }
    }
};

function getPicByBlockInfo(json_blockInfo) {
    var blockInfo = new BlockInfo(json_blockInfo);
    return blockInfo.getPic();
}

function getPicByBlock(json_block) {
    var block = new Block(json_block);
    return block.blockInfo.getPic();
}
