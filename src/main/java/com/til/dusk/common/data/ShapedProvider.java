package com.til.dusk.common.data;

/*
  @author til
 */
/*public class ShapedProvider implements DataProvider {
    @Override
    public void run(@NotNull CachedOutput cachedOutput) throws IOException {
        for (RegisterBasics<?> allRegisterBasic : RegisterManage.ALL_REGISTER_BASICS) {
            allRegisterBasic.registerShaped();
        }
        DelayTrigger.run(DelayTrigger.SHAPED, Extension.Func::func);
        for (Map.Entry<ShapedType, Map<ShapedDrive, List<Shaped>>> e1 : Shaped.MAP.entrySet()) {
            for (Map.Entry<ShapedDrive, List<Shaped>> e2 : e1.getValue().entrySet()) {
                AllShaped.Judge judge = new AllShaped.Judge();
                for (Shaped shaped : e2.getValue()) {
                    if (!(shaped instanceof ShapedOre shapedOre)) {
                        continue;
                    }
                    judge.containsAndDeBug(new AllShaped.JudgeCell(shapedOre));
                    generate(shaped, cachedOutput);
                }
            }
        }

    }

    public void generate(Shaped shaped, CachedOutput cachedOutput) throws IOException {
        JsonObject jsonObject = new JsonObject();
        AllNBTPack.TYPE.set(jsonObject, shaped.getClass());
        shaped.writ(jsonObject);
        Path mainOutput = DuskData.dataGenerator.getOutputFolder();
        String pathSuffix = String.format("data/%s/shaped/%s/%s/%s.json",
                shaped.shapedType.name.getNamespace(),
                shaped.shapedType.name.getPath(),
                shaped.shapedDrive.name.getPath(),
                shaped.name);
        Path outputPath = mainOutput.resolve(pathSuffix);
        DataProvider.saveStable(cachedOutput, jsonObject, outputPath);
    }

    @Override
    public @NotNull String getName() {
        return "shaped";
    }


}*/
