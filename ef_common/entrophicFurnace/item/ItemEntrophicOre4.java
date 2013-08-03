package entrophicFurnace.item;

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
    public ItemEntrophicOre4(int id)
    {
        super(id);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("entrophicOre4");
    }
}
