package entrophicFurnace.generic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

/**
 * Entrophic crop
 * @author Tachyony
 *
 */
public class BlockEntrophicCrop extends BlockCrops {
    public BlockEntrophicCrop(int blockId) {
        super(blockId);
    }
    
    /**
     * Icon array
     */
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;
    
    /**
     * Generate a seed ItemStack for this crop.
     */
    @Override
    protected int getSeedItem()
    {
        return EntrophicFurnace.entrophicSeed.itemID;
    }

    /**
     * Generate a crop produce ItemStack for this crop.
     */
    @Override
    protected int getCropItem()
    {
        return EntrophicFurnace.entrophicOre.itemID;
    }
    
    @Override
    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 > 7)
        {
            return this.iconArray[7];
        }

        return this.iconArray[par2];
    }
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[8];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon("entrophicCrop_" + i);
        }
    }
}
