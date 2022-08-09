package org.moon.figura.avatars.model;

import org.moon.figura.math.vector.FiguraVec3;

import java.util.Set;

public enum ParentType {
    None("NONE"),

    Head(VanillaModelProvider.HEAD, "HEAD"),
    Body(VanillaModelProvider.BODY, "BODY"),
    LeftArm(VanillaModelProvider.LEFT_ARM, FiguraVec3.of(5, 2, 0), "LEFT_ARM"),
    RightArm(VanillaModelProvider.RIGHT_ARM, FiguraVec3.of(-5, 2, 0), "RIGHT_ARM"),
    LeftLeg(VanillaModelProvider.LEFT_LEG, FiguraVec3.of(1.9, 12, 0), "LEFT_LEG"),
    RightLeg(VanillaModelProvider.RIGHT_LEG, FiguraVec3.of(-1.9, 12, 0), "RIGHT_LEG"),

    LeftElytra(VanillaModelProvider.LEFT_ELYTRON, FiguraVec3.of(5, 0, 0), "LEFT_ELYTRA", "LeftElytron", "LEFT_ELYTRON"),
    RightElytra(VanillaModelProvider.RIGHT_ELYTRON, FiguraVec3.of(-5, 0, 0), "RIGHT_ELYTRA", "RightElytron", "RIGHT_ELYTRON"),

    Cape(VanillaModelProvider.FAKE_CAPE, "CAPE"),

    World(true, false, "WORLD"),
    Hud(true, false, "HUD", "Gui", "GUI"),
    Camera("CAMERA"),
    Skull(true, false, "SKULL"),

    LeftItemPivot(false, true,"LEFT_ITEM_PIVOT"),
    RightItemPivot(false, true,"RIGHT_ITEM_PIVOT"),
    LeftSpyglassPivot(false, true,"LEFT_SPYGLASS_PIVOT"),
    RightSpyglassPivot(false, true,"RIGHT_SPYGLASS_PIVOT"),
    HelmetItemPivot(false, true,"HELMET_ITEM_PIVOT"),
    LeftParrotPivot(false, true,"LEFT_PARROT_PIVOT"),
    RightParrotPivot(false, true,"RIGHT_PARROT_PIVOT");

    public final VanillaModelProvider provider;
    public final FiguraVec3 offset;
    public final String[] aliases;
    public final boolean isSpecial, isPivot;

    ParentType(String... aliases) {
        this(false, false, null, aliases);
    }

    ParentType(VanillaModelProvider provider, String... aliases) {
        this(false, false, provider, FiguraVec3.of(), aliases);
    }

    ParentType(boolean isSpecial, boolean isPivot, String... aliases) {
        this(isSpecial, isPivot, null, aliases);
    }

    ParentType(boolean isSpecial, boolean isPivot, VanillaModelProvider provider, String... aliases) {
        this(isSpecial, isPivot, provider, FiguraVec3.of(), aliases);
    }

    ParentType(VanillaModelProvider provider, FiguraVec3 offset, String... aliases) {
        this(false, false, provider, offset, aliases);
    }

    ParentType(boolean isSpecial, boolean isPivot, VanillaModelProvider provider, FiguraVec3 offset, String... aliases) {
        this.isSpecial = isSpecial;
        this.isPivot = isPivot;
        this.provider = provider;
        this.offset = offset;
        this.aliases = aliases;
    }

    public static ParentType get(String name) {
        for (ParentType parentType : values()) {
            if (name.startsWith(parentType.name()))
                return parentType;
            for (String alias : parentType.aliases) {
                if (name.startsWith(alias))
                    return parentType;
            }
        }
        return None;
    }
}
