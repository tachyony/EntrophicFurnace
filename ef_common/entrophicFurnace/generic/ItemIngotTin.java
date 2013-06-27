package entrophicFurnace.generic;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 *
 * @author Tachyony
 *
 */
public class ItemIngotTin extends Item
{
    /**
     *
     * @param id
     */
    protected ItemIngotTin(int id)
    {
        super(id);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setIconIndex(TextureIds.ingotTin);
        this.setItemName("ingotTin");
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
