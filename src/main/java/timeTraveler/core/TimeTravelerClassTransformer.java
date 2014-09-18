package timeTraveler.core;

import java.io.IOException;
import java.util.Iterator;

import static org.objectweb.asm.Opcodes.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;

public class TimeTravelerClassTransformer extends AccessTransformer implements IClassTransformer 
{
    public TimeTravelerClassTransformer() throws IOException
    {
		super("TimeTraveler_at.cfg");  
		System.out.println("** TIMETRAVELER - Changing Access Levels!");
		// TODO Auto-generated constructor stub
	}

	@Override
    public byte[] transform(String arg0, String arg1, byte[] arg2)
    {
        if (arg0.equals("aqz"))
        {
            System.out.println("** TIMETRAVELER - Injecting new event trigger into Block : " + arg0);
            return patchClassASM(arg0, arg2);
        }

        if (arg0.equals("net.minecraft.block.Block")) {
			System.out.println("** TIMETRAVELER TRANS : " + arg0);
        return patchClassASM(arg0, arg2);
        }

        return arg2;
    }

    public byte[] patchClassASM(String name, byte[] bytes)
    {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);
        Iterator<MethodNode> methods = classNode.methods.iterator();

        while (methods.hasNext())
        {
            MethodNode m = methods.next();
      //      System.out.println("Method: " + m.name);
    //        System.out.println("Sig: " + m.desc);
           /* if(m.name.equals("breakBlock"))
           // if (m.name.equals("a") && m.desc.equals("(Labw;IIIII)V)
            {
                System.out.println("** TIMETRAVELER - Patching " + m.name);
                AbstractInsnNode currentNode = null;
                Iterator<AbstractInsnNode> iter = m.instructions.iterator();
                int index = -1;

                //Loop over the instruction set and find the instruction FDIV which does the division of 1/explosionSize
                while (iter.hasNext())
                {
                    index++;
                    currentNode = iter.next();
                    System.out.println(currentNode.getOpcode());

                    if (currentNode.getOpcode() == RETURN)
                    {
                        InsnList toInject = new InsnList();
                        toInject.add(new TypeInsnNode(NEW, "timeTraveler/mechanics/BlockBreakEvent"));
                        toInject.add(new InsnNode(DUP));
                        //toInject.add(new VarInsnNode(ALOAD, 5));
                        //toInject.add(new VarInsnNode(ALOAD, 6));
                        toInject.add(new VarInsnNode(ILOAD, 2));
                        toInject.add(new VarInsnNode(ILOAD, 3));
                        toInject.add(new VarInsnNode(ILOAD, 4));
                        toInject.add(new MethodInsnNode(INVOKESPECIAL, "timeTraveler/mechanics/BlockBreakEvent", "<init>", "(III)V"));
                        toInject.add(new VarInsnNode(ASTORE, 7));
                        toInject.add(new FieldInsnNode(GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lnet/minecraftforge/event/EventBus;"));
                        toInject.add(new VarInsnNode(ALOAD, 7));
                        toInject.add(new MethodInsnNode(INVOKEVIRTUAL, "net/minecraftforge/event/EventBus", "post", "(Lnet/minecraftforge/event/Event;)Z"));
                        toInject.add(new InsnNode(POP));
                        m.instructions.insertBefore(currentNode, toInject);
                    }
                }
            }*/
         
            if(m.name.equals("onBlockPlacedBy"))
           // if (m.name.equals("a") && m.desc.equals("(Labw;IIILof;Lye;)V"))
            {
                System.out.println("** TIMETRAVELER - Patching " + m.name);
                AbstractInsnNode currentNode = null;
                Iterator<AbstractInsnNode> iter = m.instructions.iterator();
                int index = -1;

                //Loop over the instruction set and find the instruction FDIV which does the division of 1/explosionSize
                while (iter.hasNext())
                {
                    index++;
                    currentNode = iter.next();
                    System.out.println(currentNode.getOpcode());

                    if (currentNode.getOpcode() == RETURN)
                    {
                        InsnList toInject = new InsnList();
                        toInject.add(new TypeInsnNode(NEW, "timeTraveler/mechanics/LivingPlaceBlockEvent"));
                        toInject.add(new InsnNode(DUP));
                        toInject.add(new VarInsnNode(ALOAD, 5));
                        toInject.add(new VarInsnNode(ALOAD, 6));
                        toInject.add(new VarInsnNode(ILOAD, 2));
                        toInject.add(new VarInsnNode(ILOAD, 3));
                        toInject.add(new VarInsnNode(ILOAD, 4));
                        toInject.add(new MethodInsnNode(INVOKESPECIAL, "timeTraveler/mechanics/LivingPlaceBlockEvent", "<init>", "(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;III)V"));
                        toInject.add(new VarInsnNode(ASTORE, 7));
                        toInject.add(new FieldInsnNode(GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lnet/minecraftforge/event/EventBus;"));
                        toInject.add(new VarInsnNode(ALOAD, 7));
                        toInject.add(new MethodInsnNode(INVOKEVIRTUAL, "net/minecraftforge/event/EventBus", "post", "(Lnet/minecraftforge/event/Event;)Z"));
                        toInject.add(new InsnNode(POP));
                        m.instructions.insertBefore(currentNode, toInject);
                    }
                }
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}
