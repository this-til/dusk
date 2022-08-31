package com.til.dusk.common.capability.mana_handle;

import com.til.dusk.common.event.EventIO;
import com.til.dusk.util.Pos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;

import java.util.Map;

/**
 * 对灵气处理的一些帮助函数
 *
 * @author til
 */
public class ManaHandleHelp {

    /***
     * 添加灵气
     * @param mana 要添加的灵气
     * @param map 添加进入对象
     * @param start 起始点
     * @return 加入了多少
     */
    public static long addMana(long mana, Map<BlockEntity, IManaHandle> map, BlockEntity start) {
        if (mana <= 0) {
            return 0;
        }
        if (map.isEmpty()) {
            return 0;
        }
        long inMana = 0;
        for (Map.Entry<BlockEntity, IManaHandle> entry : map.entrySet()) {
            long in = entry.getValue().addMana(mana);
            inMana += in;
            mana -= in;
            MinecraftForge.EVENT_BUS.post(new EventIO.Mana(start.getLevel(), in, new Pos(start.getBlockPos()), new Pos(entry.getKey().getBlockPos())));
            if (mana <= 0) {
                break;
            }
        }
        return inMana;
    }

    /***
     * 抽取灵气
     * @param mana 要抽取的灵气
     * @param map 抽取对象
     * @param end 结束点
     * @return 抽取了多少
     */
    public static long extractMana(long mana, Map<BlockEntity, IManaHandle> map, BlockEntity end) {
        if (mana <= 0) {
            return 0;
        }
        if (map.isEmpty()) {
            return 0;
        }
        long outMana = 0;
        for (Map.Entry<BlockEntity, IManaHandle> entry : map.entrySet()) {
            long out = entry.getValue().extractMana(mana);
            outMana += out;
            mana -= out;
            MinecraftForge.EVENT_BUS.post(new EventIO.Mana(end.getLevel(), out, new Pos(entry.getKey().getBlockPos()), new Pos(end.getBlockPos())));
            if (mana < 0) {
                break;
            }
        }
        return outMana;
    }


}
