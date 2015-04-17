package timeTraveler.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import timeTraveler.core.TimeTraveler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTimeFluid extends BlockFluidClassic 
{

        @SideOnly(Side.CLIENT)
        protected IIcon stillIIcon;
        @SideOnly(Side.CLIENT)
        protected IIcon flowingIIcon;
        
        public BlockTimeFluid(Fluid fluid, Material material)
        {
                super(fluid, material);
                setBlockName("timeLiquid");
                setCreativeTab(TimeTraveler.tabTT);
        }
        
        @Override
        public IIcon getIcon(int side, int meta)
        {
                return (side == 0 || side == 1)? stillIIcon : flowingIIcon;
        }
        
        @SideOnly(Side.CLIENT)
        @Override
        public void registerBlockIcons(IIconRegister register)
        {
                stillIIcon = register.registerIcon(TimeTraveler.modid + ":" + "timeFluid_still");
                flowingIIcon = register.registerIcon(TimeTraveler.modid + ":" + "timeFluid_flow");
        }
}