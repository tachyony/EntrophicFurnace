package entrophicFurnace.generic;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 *
 * @author Tachyony
 *
 */
public class ItemEntrophicOre4 extends Item
{
    /**
     *
     * @param id
     */
    protected ItemEntrophicOre4(int id)
    {
        super(id);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setIconIndex(TextureIds.genericOre4);
        this.setItemName("entrophicOre4");
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
