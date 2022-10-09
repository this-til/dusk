package com.til.dusk.common.config.util;

import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author til
 */
public interface IShapedCreate<DATA> {
    @Nullable
    Shaped create(DATA data);

    class OreShapedCreate implements IShapedCreate<Ore> {

        public ResourceLocation name;
        public ShapedType shapedType;
        public ShapedDrive shapedDrive;
        public long surplusTime;
        public long consumeMana;
        public long outMana;
        public boolean isShow = true;

        @Nullable
        public List<IShapedOreConfig<Ore>> shapedOreConfig;

        public OreShapedCreate() {
        }

        public OreShapedCreate(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive) {
            this.name = name;
            this.shapedType = shapedType;
            this.shapedDrive = shapedDrive;
        }

        @Override
        public Shaped create(Ore data) {
            if (shapedOreConfig == null) {
                return null;
            }
            ShapedOre shapedOre = (ShapedOre) new ShapedOre(ResourceLocationUtil.fuseName("/", shapedType.name, shapedDrive.name, name), shapedType, shapedDrive, data.manaLevel)
                    .addMultipleSurplusTime(surplusTime)
                    .addMultipleConsumeMana(consumeMana)
                    .addMultipleOutMana(outMana);
            for (IShapedOreConfig<Ore> dataShapedOreConfig : shapedOreConfig) {
                dataShapedOreConfig.config(shapedOre, data);
            }
            return shapedOre;
        }

        public OreShapedCreate addConfig(IShapedOreConfig<Ore> shapedOreConfig) {
            if (this.shapedOreConfig == null) {
                this.shapedOreConfig = new ArrayList<>(1);
            }
            this.shapedOreConfig.add(shapedOreConfig);
            return null;
        }

        public OreShapedCreate setSurplusTime(long surplusTime) {
            this.surplusTime = surplusTime;
            return this;
        }

        public OreShapedCreate setConsumeMana(long consumeMana) {
            this.consumeMana = consumeMana;
            return this;
        }

        public OreShapedCreate setOutMana(long outMana) {
            this.outMana = outMana;
            return this;
        }

        public OreShapedCreate setShow(boolean show) {
            isShow = show;
            return this;
        }
    }


}
