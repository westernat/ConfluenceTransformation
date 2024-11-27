package org.mesdag.transformation;

import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TargetType;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InterfaceTransformer implements ITransformer<ClassNode> {
    private final Map<String, Function<ClassVisitor, ClassVisitor>> visitors;

    public InterfaceTransformer(Map<String, Function<ClassVisitor, ClassVisitor>> visitors) {
        this.visitors = visitors;
    }

    @Override
    public @NotNull ClassNode transform(ClassNode node, ITransformerVotingContext context) {
        Function<ClassVisitor, ClassVisitor> visitor = visitors.get(node.name);
        if (visitor != null) {
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            node.accept(visitor.apply(writer));
            ClassNode dest = new ClassNode();
            new ClassReader(writer.toByteArray()).accept(dest, 0);
            ConfluenceTransformationService.LOGGER.info("Transformed {}", node.name);
            return dest;
        }
        return node;
    }

    @Override
    public @NotNull TransformerVoteResult castVote(ITransformerVotingContext context) {
        return TransformerVoteResult.YES;
    }

    @Override
    public @NotNull Set<Target<ClassNode>> targets() {
        return visitors.keySet().stream().map(Target::targetClass).collect(Collectors.toSet());
    }

    @Override
    public @NotNull TargetType<ClassNode> getTargetType() {
        return TargetType.CLASS;
    }
}
