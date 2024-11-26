package org.mesdag.transformation;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MesdagTransformation.MODID)
public class MesdagTransformation {
    public static final String MODID = "mesdag_transformation";
    private static final Logger LOGGER = LoggerFactory.getLogger("Mesdag Transformation");

    public MesdagTransformation(IEventBus modEventBus, ModContainer modContainer) {

    }
}
