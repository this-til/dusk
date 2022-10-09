package com.til.dusk.common.config.util;

import com.til.dusk.common.config.AcceptTypeJson;
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
@AcceptTypeJson
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

        public OreShapedCreate(ResourceLocation name) {
            this.name = name;
        }

        public OreShapedCreate(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive) {
            this.name = name;
            this.shapedType = shapedType;
            this.shapedDrive = shapedDrive;
        }

        public OreShapedCreate(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive, long surplusTime, long consumeMana, long outMana) {
            this.name = name;
            this.shapedType = shapedType;
            this.shapedDrive = shapedDrive;
            this.surplusTime = surplusTime;
            this.consumeMana = consumeMana;
            this.outMana = outMana;
        }

        public OreShapedCreate(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive, long surplusTime, long consumeMana, long outMana, boolean isShow, @Nullable List<IShapedOreConfig<Ore>> shapedOreConfig) {
            this.name = name;
            this.shapedType = shapedType;
            this.shapedDrive = shapedDrive;
            this.surplusTime = surplusTime;
            this.consumeMana = consumeMana;
            this.outMana = outMana;
            this.isShow = isShow;
            this.shapedOreConfig = shapedOreConfig;
        }

        @Override
        public Shaped create(Ore data) {
            if (shapedOreConfig == null) {
                return null;
            }if (shapedType == null) {
                shapedType = ShapedType.empty;
            }if (shapedDrive == null) {
                 shapedDrive = ShapedDrive.get(0);
            }
            ShapedOre shapedOre = (ShapedOre) new ShapedOre(ResourceLocationUtil.fuseName("/", shapedType.name, shapedDrive.name, name), shapedType, shapedDrive, data.manaLevel)
                    .addMultipleSurplusTime((long) (surplusTime * data.strength))
                    .addMultipleConsumeMana((long) (consumeMana * data.consume * data.consume))
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

        public OreShapedCreate setShapedType(ShapedType shapedType) {
            this.shapedType = shapedType;
            return this;
        }

        public OreShapedCreate setShapedDrive(ShapedDrive shapedDrive) {
            this.shapedDrive = shapedDrive;
            return this;
        }

        public OreShapedCreate setShow(boolean show) {
            isShow = show;
            return this;
        }
    }


}
