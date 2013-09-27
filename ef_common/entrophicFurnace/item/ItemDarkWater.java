package entrophicFurnace.item;

import net.minecraft.item.Item;

/**
 *
 * @author Tachyony
 *
 */
public class ItemDarkWater extends Item
{
    /**
     *
     * @param id
     */
    public ItemDarkWater(int id)
    {
        super(id);
        this.setMaxStackSize(64);
        //this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("darkWater");
    }
}
