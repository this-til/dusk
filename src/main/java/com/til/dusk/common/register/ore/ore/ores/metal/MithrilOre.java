package com.til.dusk.common.register.ore.ore.ores.metal;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.DecorateBlockData;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.item.ArmorData;
import com.til.dusk.common.register.ore.item.ArmsData;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.item.ToolData;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * @author til
 */
public class MithrilOre extends Ore {
    public MithrilOre() {
        super("mithril");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "秘银");
        lang.add(LangType.EN_CH, "Mithril");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(205, 209, 229);
        manaLevel = ManaLevel.t1;
        isMetal = true;
        hasDust = true;
        isLevelAcceptable = List.of(ManaLevel.t2);
        decorateBlockData = new DecorateBlockData();
        fluidData = new FluidData()
                .setCanCopy(true);
        armorData = new ArmorData()
                .setOfOre(this)
                .setDefense(3)
                .setDurability(10)
                .setMane(3200000L, 12800L)
                .putSkill(Skill.life, 1);
        armsData = new ArmsData()
                .setOfOre(this)
                .setMane(3200000L, 12800L);
        toolData = new ToolData()
                .setUses(16 * 64)
                .setTankMax(16 * 4000);
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreItem.dust), ShapedType.blend, ShapedDrive.get(0), manaLevel)
                        .addInItem(spiritSilver.get(OreItem.dust).itemTag(), 1)
                        .addInItem(greenTeal.get(OreItem.dust).itemTag(), 1)
                        .addInItem(mediumspringgreen.get(OreItem.dust).itemTag(), 1)
                        .addOutItem(new ItemStack(this.get(OreItem.dust).item(), 3), 1D)
                        .addMultipleSurplusTime((long) consume * 1024L)
                        .addMultipleConsumeMana((long) strength * 27L)));
    }
}

