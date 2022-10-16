package com.til.dusk.common.register.bind_type.bind_types;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.shaped_drive.IShapedDrive;
import com.til.dusk.common.register.bind_type.BindTypeBindCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.DuskColor;

/**
 * @author til
 */
public class PosTrackBindType extends BindTypeBindCapability<IPosTrack> {

    public PosTrackBindType() {
        super("pos_track", () -> CapabilityRegister.iPosTrack);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "坐标锚点");
        lang.add(LangType.EN_CH, "Pos Track");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(69, 187, 178, 255);
    }
}
