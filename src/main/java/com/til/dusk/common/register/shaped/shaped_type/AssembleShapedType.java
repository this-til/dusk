package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class AssembleShapedType extends ShapedType {
    public AssembleShapedType() {
        super("assemble", () -> ManaLevelBlock.assemble);
    }

    @Override
    public void registerSubsidiaryBlack() {
        List<ManaLevelBlock> needUp = new ArrayList<>();
        for (ManaLevelBlock manaLevelBlock : ManaLevelBlock.LEVEL_BLOCK.get()) {
            if (manaLevelBlock.hasTag(ManaLevelBlock.NEED_FRAME_UP)) {
                needUp.add(manaLevelBlock);
            }
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            if (manaLevel.next == null) {
                continue;
            }
            for (ManaLevelBlock manaLevelBlock : needUp) {
                new ShapedOre(
                        this,
                        ShapedDrive.get(0),
                        manaLevel,
                        Map.of(manaLevel.blockMap.get(manaLevelBlock).blockItemTag(), 1, manaLevel.next.get().blockMap.get(ManaLevelBlock.frameBasic).blockItemTag(), 1),
                        null,
                        manaLevel.level * 1024L,
                        manaLevel.level * 32L,
                        0,
                        Map.of(new ItemStack(manaLevel.next.get().blockMap.get(manaLevelBlock).blockItem()), 1d),
                        null
                );
            }

        }
    }
}
