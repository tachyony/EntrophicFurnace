package entrophicFurnace.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author tachyony
 *
 */
public class ItemEntrophicOre extends Item
{
    public ItemEntrophicOre(int id)
    {
        super(id);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("entrophicOre");
    }
}