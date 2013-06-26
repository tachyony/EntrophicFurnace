package entrophicFurnace.generic;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author tachyony
 *
 */
public class ItemEntrophicOre3 extends Item
{
    protected ItemEntrophicOre3(int id)
    {
        super(id);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setIconIndex(TextureIds.genericOre3);
        this.setItemName("entrophicOre3");
    }

    @Override
    public String getTextureFile()
    {
        return CommonProxy.BLOCK_PNG;
    }
}
