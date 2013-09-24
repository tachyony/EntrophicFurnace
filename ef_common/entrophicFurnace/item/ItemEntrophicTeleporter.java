package entrophicFurnace.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import entrophicFurnace.generic.EntrophicTeleporter;

/**
 * 
 * @author tachyony
 *
 */
public class ItemEntrophicTeleporter extends Item {
    /**
     * 
     * @param id
     */
    public ItemEntrophicTeleporter(int id)
    {
        super(id);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName("entrophicTeleporter");
    }
    
    /**
     * 
     */
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        if ((player.ridingEntity == null) && (player.riddenByEntity == null) && ((player instanceof EntityPlayerMP)))
        {
            EntityPlayerMP thePlayer = (EntityPlayerMP)player;
            if (thePlayer.timeUntilPortal > 0)
            {
                thePlayer.timeUntilPortal = 10;
            }
            else if (thePlayer.dimension == 143)
            {
                thePlayer.timeUntilPortal = 10;
                thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new EntrophicTeleporter(thePlayer.mcServer.worldServerForDimension(0)));
            }
            else
            {
                if (thePlayer.dimension == 0)
                {
                    NBTTagCompound tag = player.getEntityData();
                    tag.setDouble("oldx", player.posX);
                    tag.setDouble("oldy", player.posY);
                    tag.setDouble("oldz", player.posZ);
                    thePlayer.timeUntilPortal = 10;
                }
                
                thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 143, new EntrophicTeleporter(thePlayer.mcServer.worldServerForDimension(143)));
            }
        }
        
        return itemStack;
    }
}
