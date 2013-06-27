package entrophicFurnace.generic;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 *
 * @author Tachyony
 *
 */
public class ItemIngotCopper extends Item
{
    /**
     *
     * @param id
     */
    protected ItemIngotCopper(int id)
    {
        super(id);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setIconIndex(TextureIds.ingotCopper);
        this.setItemName("ingotCopper");
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
