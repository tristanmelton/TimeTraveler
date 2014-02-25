package timeTraveler.core;

import static org.objectweb.asm.Opcodes.FDIV;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.tree.AbstractInsnNode.METHOD_INSN;

import java.util.Iterator;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class TTClassTransformer implements IClassTransformer 
{

	@Override
	public byte[] transform(String arg0, String arg1, byte[] arg2) 
	{
		if (arg0.equals("aqz"))
		{
			System.out.println("********* INSIDE OBFUSCATED BLOCK TRANSFORMER ABOUT TO PATCH: " + arg0);
			return patchClassASM(arg0, arg2, true);
        }
		if (arg0.equals("net.minecraft.block.Block"))
		{
			System.out.println("********* INSIDE BLOCK TRANSFORMER ABOUT TO PATCH: " + arg0);
			return patchClassASM(arg0, arg2, false);
        }
        return arg2;
	}
	 public byte[] patchClassASM(String name, byte[] bytes, boolean obfuscated)
	 {
		 	String targetMethodName = "";
	        if(obfuscated == true)
	        {
	        	targetMethodName ="a";
	        	System.out.println("******** Using obfuscated method");
	        }
	        else
	        {
	        	targetMethodName ="onBlockPlaced";
	        	System.out.println("******** Using unobfuscated method");
	        }
	        //set up ASM class manipulation stuff. Consult the ASM docs for details
		    ClassNode classNode = new ClassNode();
	        ClassReader classReader = new ClassReader(bytes);
	        classReader.accept(classNode, 0);
	        //Now we loop over all of the methods declared inside the Block class until we get to the targetMethodName "onBlockPlaced" 
	        @SuppressWarnings("unchecked")
	        Iterator<MethodNode> methods = classNode.methods.iterator();
	        while(methods.hasNext())
	        {
	            MethodNode m = methods.next();
	            System.out.println("********* Method Name: "+m.name + " Desc:" + m.desc);
	            int fdiv_index = -1;
	            //Check if this is onBlockPlaced and it's method signature is (Lnet/minecraft/world/World;IIIIFFFI)I
	            if ((m.name.equals(targetMethodName) && m.desc.equals("(Lnet/minecraft/world/World;IIIIFFFI)I")))
	            {
	                System.out.println("********* Inside target method!");
	                
	                AbstractInsnNode currentNode = null;
	                AbstractInsnNode targetNode = null;

	                @SuppressWarnings("unchecked")
	                Iterator<AbstractInsnNode> iter = m.instructions.iterator();
	                int index = -1;

	                //Loop over the instruction set and find the instruction 21 which does the line before the ILOAD
	                while (iter.hasNext())
	                {
	                    index++;
	                    currentNode = iter.next();
	                    System.out.println("********* index : " + index + " currentNode.getOpcode() = " + currentNode.getOpcode());
	                    //Found it! save the index location of instruction ILOAD and the node for this instruction
	                    if (currentNode.getOpcode() == 21)
	                    {
	                    	targetNode = currentNode;
	                        fdiv_index = index;
	                    }
	                }
	                System.out.println("********* fdiv_index should be 2 -> " + fdiv_index);
	                if (targetNode == null)
	                {
	                    System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
	                    return bytes;
	                }
	                if (fdiv_index == -1)
	                {
	                    System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
	                    return bytes;
	                }
	                
	                InsnList toInject = new InsnList();
	                //LINENUMBER 87 L0 (possibly unneeded)
    				toInject.add(new TypeInsnNode(Opcodes.NEW, "timeTraveler/mechanics/BlockPlaceEvent"));//NEW timeTraveler/mechanics/BlockPlaceEvent
    				toInject.add(new InsnNode(Opcodes.DUP));//DUP
					toInject.add(new VarInsnNode(Opcodes.ALOAD, 0));//ALOAD 0
					toInject.add(new VarInsnNode(Opcodes.ILOAD, 2));//ILOAD 2
					toInject.add(new VarInsnNode(Opcodes.ILOAD, 3));//ILOAD 3
					toInject.add(new VarInsnNode(Opcodes.ILOAD, 4));//ILOAD 4
					toInject.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "timeTraveler/mechanics/BlockPlaceEvent", "<init>", "(Lnet/minecraft/block/Block;III)V"));//INVOKESPECIAL timeTraveler/mechanics/BlockPlaceEvent.<init> (Lnet/minecraft/block/Block;III)V
					toInject.add(new VarInsnNode(Opcodes.ASTORE, 5));//ASTORE 5
					//L1 (possibly unneeded)
					//LINENUMBER 88 L1 (possibly unneeded)
					toInject.add(new FieldInsnNode(Opcodes.GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lnet/minecraftforge/event/EventBus;"));//GETSTATIC net/minecraftforge/common/MinecraftForge.EVENT_BUS : Lnet/minecraftforge/event/EventBus;
					toInject.add(new VarInsnNode(Opcodes.ALOAD, 5));//ALOAD 5
					toInject.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraftforge/event/EventBus", "post", "(Lnet/minecraftforge/event/Event;)Z"));//INVOKEVIRTUAL net/minecraftforge/event/EventBus.post (Lnet/minecraftforge/event/Event;)Z
					m.instructions.insert(targetNode, toInject);
					//POP (possibly unneeded)
					//L2 (possibly unneeded)
					
	                /*
	                //To add new instructions, such as calling a static method can be done like so:
	                
	                // make new instruction list
	                InsnList toInject = new InsnList();
	               
					toInject.add(new VarInsnNode(ALOAD, 0));
                    toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/culegooner/MyStaticClass", "myStaticMethod", "()V"));
                
	                // inject new instruction list into method instruction list
	                m.instructions.insert(targetNode, toInject);
	                */
	                System.out.println("Patching Complete!");
	                break;
	            }
	        }
	        //ASM specific for cleaning up and returning the final bytes for JVM processing.
	        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
	        classNode.accept(writer);
	        return writer.toByteArray();
      }
}
