package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.item.ManaLevelItem;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.ArmorData;
import com.til.dusk.common.register.ore.item.ArmsData;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author til
 */
public class AssembleShapedType extends ShapedType {
    public AssembleShapedType() {
        super("assemble", () -> ManaLevelBlock.assemble);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.get(OreItem.casing).itemTag(), 6)
                    .addInItem(ore.get(OreItem.stick).itemTag(), 4)
                    .addOutItem(new ItemStack(ore.get(OreBlock.bracket).blockItem(), 1), 1d)
                    .addMultipleSurplusTime(4096L)
                    .addMultipleConsumeMana(12L);
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.get(OreBlock.bracket).blockItemTag(), 1)
                    .addInItem(ore.get(OreItem.casing).itemTag(), 6)
                    .addInItem(ore.get(OreItem.string).itemTag(), 32)
                    .addOutItem(new ItemStack(ore.get(OreBlock.coil).blockItem(), 1), 1d)
                    .addMultipleSurplusTime(8192L)
                    .addMultipleConsumeMana(22L);
        }
        for (ManaLevel manaLevel : ManaLevel.MANA_LEVEL.get()) {
            for (Map.Entry<ManaLevelBlock, BlockPack> entry : manaLevel.blockEntrySet()) {
                if (entry.getKey().hasSet(ManaLevelBlock.MECHANIC_MAKE_DATA)) {
                    ManaLevelBlock.ManaLevelMakeData manaLevelMakeData = entry.getKey().getSet(ManaLevelBlock.MECHANIC_MAKE_DATA);
                    ManaLevel level = switch (manaLevelMakeData.makeLevel) {
                        case UP ->
                                manaLevel.up != null ? manaLevel.up.get() : manaLevelMakeData.isMustRegister ? ManaLevel.t1 : null;
                        case CURRENT -> manaLevel;
                        case NEXT ->
                                manaLevel.next != null ? manaLevel.next.get() : manaLevelMakeData.isMustRegister ? ManaLevel.t8 : null;
                    };
                    if (level == null) {
                        continue;
                    }
                    new ShapedOre(this, ShapedDrive.get(2), level)
                            .addOutItem(new ItemStack(entry.getValue().blockItem(), 1), 1d)
                            .runThis(ManaLevelBlock.MECHANIC_MAKE_DATA, entry.getKey(), manaLevel)
                            .addMultipleSurplusTime(4096L * manaLevel.level)
                            .addMultipleConsumeMana(32L * manaLevel.level);
                }
            }
        }
        for (Ore ore : Ore.screen(Ore.ARMOR_DATA)) {
            ArmorData armorData = ore.getSet(Ore.ARMOR_DATA);
            new ShapedOre(this, ShapedDrive.get(3), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate4).itemTag(), 32 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.operation.getTag(ore.manaLevel), 24 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.forming.getTag(ore.manaLevel), 12 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.gather.getTag(ore.manaLevel), 8 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.instructions.getTag(ore.manaLevel), 8 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.power.getTag(ore.manaLevel), 8 * ore.manaLevel.level)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.get(OreItem.head).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(4), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate4).itemTag(), 64 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.operation.getTag(ore.manaLevel), 24 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.forming.getTag(ore.manaLevel), 24 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.gather.getTag(ore.manaLevel), 24 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.instructions.getTag(ore.manaLevel), 24 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.power.getTag(ore.manaLevel), 24 * ore.manaLevel.level)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.get(OreItem.chest).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(5), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate4).itemTag(), 48 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.operation.getTag(ore.manaLevel), 24 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.forming.getTag(ore.manaLevel), 24 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.gather.getTag(ore.manaLevel), 12 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.instructions.getTag(ore.manaLevel), 12 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.power.getTag(ore.manaLevel), 12 * ore.manaLevel.level)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.get(OreItem.legs).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(6), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate4).itemTag(), 32 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.operation.getTag(ore.manaLevel), 24 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.forming.getTag(ore.manaLevel), 24 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.gather.getTag(ore.manaLevel), 4 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.instructions.getTag(ore.manaLevel), 16 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.power.getTag(ore.manaLevel), 16 * ore.manaLevel.level)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.get(OreItem.feet).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
        }
        for (Ore ore : Ore.screen(Ore.ARMS_DATA)) {
            ArmsData armorData = ore.getSet(Ore.ARMS_DATA);
            new ShapedOre(this, ShapedDrive.get(7), ore.manaLevel)
                    .addInItem(ore.get(OreItem.swordBasics).itemTag(), 1)
                    .addInItem(ore.get(OreItem.plate4).itemTag(), 128 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.operation.getTag(ore.manaLevel), 32 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.destruction.getTag(ore.manaLevel), 64 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.spread.getTag(ore.manaLevel), 48 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.instructions.getTag(ore.manaLevel), 32 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.power.getTag(ore.manaLevel), 48 * ore.manaLevel.level)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.get(OreItem.sword).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(8), ore.manaLevel)
                    .addInItem(ore.get(OreItem.shovelBasics).itemTag(), 1)
                    .addInItem(ore.get(OreItem.plate4).itemTag(), 128 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.operation.getTag(ore.manaLevel), 12 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.destruction.getTag(ore.manaLevel), 12 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.spread.getTag(ore.manaLevel), 4 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.instructions.getTag(ore.manaLevel), 4 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.power.getTag(ore.manaLevel), 8 * ore.manaLevel.level)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.get(OreItem.shovel).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(9), ore.manaLevel)
                    .addInItem(ore.get(OreItem.pickaxeBasics).itemTag(), 1)
                    .addInItem(ore.get(OreItem.plate4).itemTag(), 64 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.operation.getTag(ore.manaLevel), 32 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.destruction.getTag(ore.manaLevel), 48 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.spread.getTag(ore.manaLevel), 12 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.instructions.getTag(ore.manaLevel), 12 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.power.getTag(ore.manaLevel), 16 * ore.manaLevel.level)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.get(OreItem.pickaxe).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(10), ore.manaLevel)
                    .addInItem(ore.get(OreItem.axeBasics).itemTag(), 1)
                    .addInItem(ore.get(OreItem.plate4).itemTag(), 64 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.operation.getTag(ore.manaLevel), 32 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.destruction.getTag(ore.manaLevel), 64 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.spread.getTag(ore.manaLevel), 12 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.instructions.getTag(ore.manaLevel), 12 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.power.getTag(ore.manaLevel), 12 * ore.manaLevel.level)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.get(OreItem.axe).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(11), ore.manaLevel)
                    .addInItem(ore.get(OreItem.hoeBasics).itemTag(), 1)
                    .addInItem(ore.get(OreItem.plate4).itemTag(), 64 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.operation.getTag(ore.manaLevel), 16 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.destruction.getTag(ore.manaLevel), 4 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.spread.getTag(ore.manaLevel), 12 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.instructions.getTag(ore.manaLevel), 8 * ore.manaLevel.level)
                    .addInItem(ManaLevelItem.power.getTag(ore.manaLevel), 8 * ore.manaLevel.level)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.get(OreItem.hoe).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);

        }
        for (Ore ore : Ore.screen(Ore.TOOL_DATA)) {
            new ShapedOre(this, ShapedDrive.get(12), ore.manaLevel)
                    .addInItem(ore.get(OreItem.ingot).itemTag(), 6)
                    .addInItem(Tags.Items.RODS_WOODEN, 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.hammer).item()), 1D)
                    .addMultipleSurplusTime((long) (2048 * ore.strength))
                    .addMultipleConsumeMana((long) (7 * ore.consume));
            new ShapedOre(this, ShapedDrive.get(13), ore.manaLevel)
                    .addInItem(ore.get(OreItem.ingot).itemTag(), 4)
                    .addOutItem(new ItemStack(ore.get(OreItem.wrench).item()), 1D)
                    .addMultipleSurplusTime((long) (2048 * ore.strength))
                    .addMultipleConsumeMana((long) (7 * ore.consume));
            new ShapedOre(this, ShapedDrive.get(14), ore.manaLevel)
                    .addInItem(ore.get(OreItem.ingot).itemTag(), 2)
                    .addInItem(ore.get(OreItem.plate).itemTag(), 3)
                    .addOutItem(new ItemStack(ore.get(OreItem.wireCutter).item()), 1D)
                    .addMultipleSurplusTime((long) (2048 * ore.strength))
                    .addMultipleConsumeMana((long) (7 * ore.consume));
            new ShapedOre(this, ShapedDrive.get(15), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate).itemTag(), 2)
                    .addInItem(ore.get(OreItem.casing).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.file).item()), 1D)
                    .addMultipleSurplusTime((long) (2048 * ore.strength))
                    .addMultipleConsumeMana((long) (7 * ore.consume));
            new ShapedOre(this, ShapedDrive.get(16), ore.manaLevel)
                    .addInItem(ore.get(OreItem.plate3).itemTag(), 2)
                    .addInItem(ore.get(OreItem.plate2).itemTag(), 6)
                    .addInItem(ore.get(OreItem.casing).itemTag(), 16)
                    .addOutItem(new ItemStack(ore.get(OreItem.tank).item()), 1D)
                    .addMultipleSurplusTime((long) (4096 * ore.strength))
                    .addMultipleConsumeMana((long) (24 * ore.consume));
        }
    }
}
