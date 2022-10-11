package com.til.dusk.util.gson.test;

import com.til.dusk.Dusk;
import com.til.dusk.util.gson.AcceptTypeJson;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.util.gson.ConfigGson;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
//@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Deprecated
public class ConfigGsonTest {
    @SubscribeEvent
    public static void onEvent(FMLCommonSetupEvent event) {
        TestData testData = new TestData();
        testData.data2Delayed = new Delayed<>(() -> {
            TestData_2 testData_2 = new TestData_2();
            testData_2.name = new ResourceLocation("as", "ps");
            return testData_2;
        }){};
        testData.list = List.of(new TestData_3.A(), new TestData_3.B(), new TestData_3.C());
        testData.stringMap = Map.of("a", new ResourceLocation("a", "a"), "b", new ResourceLocation("b", "b"));
        testData.map = Map.of(new ResourceLocation("a", "a"), new ResourceLocation("b", "b"));
        String json = ConfigGson.GSON.toJson(testData);
        Dusk.instance.logger.debug(json);
        TestData testData2 = ConfigGson.GSON.fromJson(json, TestData.class);
    }

    protected static class TestData {
        public Delayed<? extends TestData_2> data2Delayed;
        public List<TestData_3> list;
        public Map<String, ResourceLocation> stringMap;
        public Map<ResourceLocation, ResourceLocation> map;
    }

    protected static class TestData_2 {
        public ResourceLocation name;
    }

    @AcceptTypeJson
    protected interface TestData_3 {
        class A implements TestData_3 {
            public int a = 114514;
        }

        class B implements TestData_3 {
            public String b = "asd";
        }

        class C implements TestData_3 {
            public int[] c = new int[]{1, 1, 4, 5, 1, 4};
        }
    }
}
