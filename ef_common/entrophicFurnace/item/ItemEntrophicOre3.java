package entrophicFurnace.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author tachyony
 *
 */
public class ItemEntrophicOre3 extends Item
{
    /**
     * 
     * @param id
     */
    public ItemEntrophicOre3(int id)
    {
        super(id);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("entrophicOre3");
    }
}
