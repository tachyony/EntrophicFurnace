package entrophicFurnace.item;

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
    public ItemIngotTin(int id)
    {
        super(id);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("ingotTin");
    }
}
