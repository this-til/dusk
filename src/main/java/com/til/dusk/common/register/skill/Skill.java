package com.til.dusk.common.register.skill;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.shaped.ShapedDrive;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Skill extends RegisterBasics<Skill> {

    public static Supplier<IForgeRegistry<Skill>> SKILL;
    public static Skill empty;


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SKILL = event.create(new RegistryBuilder<Skill>().setName(new ResourceLocation(Dusk.MOD_ID, "skill")));
        empty = new Skill("empty");
    }

    public Skill(ResourceLocation name) {
        super(name, SKILL);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public Skill(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }
}
