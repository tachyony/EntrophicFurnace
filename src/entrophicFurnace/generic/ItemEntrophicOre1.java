package entrophicFurnace.generic;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author tachyony
 *
 */
public class ItemEntrophicOre1 extends Item
{
    protected ItemEntrophicOre1(int id)
    {
        super(id);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setIconIndex(TextureIds.genericOre);
        this.setItemName("entrophicOre");
    }

    @Override
    public String getTextureFile()
    {
        return CommonProxy.BLOCK_PNG;
    }
}
