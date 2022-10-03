package com.til.dusk.common.data;

import com.google.gson.JsonObject;
import com.til.dusk.common.event.DelayTrigger;
import com.til.dusk.common.event.RegisterManageEvent;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.Extension;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class ShapedProvider implements DataProvider {
    @Override
    public void run(@NotNull CachedOutput cachedOutput) throws IOException {
        for (RegisterBasics<?> allRegisterBasic : RegisterManage.ALL_REGISTER_BASICS) {
            allRegisterBasic.registerShaped();
        }
        DelayTrigger.run(DelayTrigger.SHAPED, Extension.Func::func);
        for (Map.Entry<String, Shaped> entry : Shaped.ID_MAP.entrySet()) {
            Shaped shaped = entry.getValue();
            JsonObject jsonObject = new JsonObject();
            AllNBTPack.CLASS.set(jsonObject, shaped.getClass());
            entry.getValue().writ(jsonObject);
            Path mainOutput = DuskData.dataGenerator.getOutputFolder();
            assert shaped.shapedType != null;
            String pathSuffix = String.format("data/%s/shaped/%s/%s/%s.json",
                    shaped.shapedType.name.getNamespace(),
                    shaped.shapedType.name.getPath(),
                    shaped.shapedDrive.name.getPath(),
                    shaped.name);
            Path outputPath = mainOutput.resolve(pathSuffix);
            DataProvider.saveStable(cachedOutput, jsonObject, outputPath);
        }

    }

    @Override
    public @NotNull String getName() {
        return "shaped";
    }


}
