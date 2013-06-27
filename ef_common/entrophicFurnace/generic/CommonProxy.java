package entrophicFurnace.generic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * @author tachyony
 *
 */
public class CommonProxy implements IGuiHandler
{
    /* public static String ITEMS_PNG = "/entrophicFurnace/generic/items.png"; */
    /**
     *
     */
    public static String BLOCK_PNG = "/entrophicFurnace/generic/block.png";

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity != null)
        {
            switch (ID)
            {
                case 0:
                    return new ContainerEntrophicFurnace(player.inventory, ((TileEntityEntrophicFurnace) tileEntity));
                default:
                    break;
            }
        }

        return null;
    }

    /**
     *
     */
    public void preInit()
    {
        //
    }

    /**
     *
     */
    public void init()
    {
        //
    }
}