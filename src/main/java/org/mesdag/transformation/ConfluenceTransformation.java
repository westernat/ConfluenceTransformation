package org.mesdag.transformation;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ConfluenceTransformation.MODID)
public class ConfluenceTransformation {
    public static final String MODID = "confluence_transformation";
    public static final Logger LOGGER = LoggerFactory.getLogger("Confluence Transformation");

    public ConfluenceTransformation(IEventBus modEventBus, ModContainer modContainer) {

    }
}
