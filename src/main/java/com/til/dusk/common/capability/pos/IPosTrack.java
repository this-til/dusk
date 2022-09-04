package com.til.dusk.common.capability.pos;

import com.til.dusk.util.Pos;
import net.minecraft.world.level.Level;

/***
 * 一个位置跟踪能力
 * @author til
 */
public interface IPosTrack {
     Pos getPos();

     Level getLevel();
}
