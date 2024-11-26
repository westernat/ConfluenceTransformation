package org.mesdag.transformation.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ContainerInterfaceVisitor extends ClassVisitor {
    public static final String TARGET = "net/minecraft/world/Container";

    public ContainerInterfaceVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM9, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if ("()I".equals(descriptor) && "getMaxStackSize".equals(name)) {
            return new MethodVisitor(Opcodes.ASM9, mv) {
                @Override
                public void visitIntInsn(int opcode, int operand) {
                    if (operand == 99) {
                        super.visitIntInsn(Opcodes.SIPUSH, 9999);
                    } else {
                        super.visitIntInsn(opcode, operand);
                    }
                }
            };
        }
        return mv;
    }
}
