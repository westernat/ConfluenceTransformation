package org.mesdag.transformation;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import org.jetbrains.annotations.NotNull;
import org.mesdag.transformation.visitors.ContainerInterfaceVisitor;
import org.objectweb.asm.ClassVisitor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @author Westernat
 */
public class ConfluenceTransformationService implements ITransformationService {
    private final Map<String, Function<ClassVisitor, ClassVisitor>> interfaceVisitors = new ConcurrentHashMap<>();

    @Override
    public @NotNull String name() {
        return "mesdag";
    }

    @Override
    public void initialize(IEnvironment environment) {
        interfaceVisitors.put(ContainerInterfaceVisitor.TARGET, ContainerInterfaceVisitor::new);
    }

    @Override
    public void onLoad(IEnvironment env, Set<String> otherServices) {}

    @Override
    public @NotNull List<? extends ITransformer<?>> transformers() {
        return List.of(new InterfaceTransformer(interfaceVisitors));
    }
}
