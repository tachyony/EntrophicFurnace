package entrophicFurnace.generic;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 *
 * @author Tachyony
 *
 */
public class ItemEntrophicOre5 extends Item
{
    /**
     *
     * @param id
     */
    protected ItemEntrophicOre5(int id)
    {
        super(id);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setIconIndex(TextureIds.genericOre5);
        this.setItemName("entrophicOre5");
    }

    /**
	 *
	 */
    @Override
    public String getTextureFile()
    {
        return CommonProxy.BLOCK_PNG;
    }
}
