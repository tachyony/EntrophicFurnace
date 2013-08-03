package entrophicFurnace.generic;

import entrophicFurnace.tileentity.TileEntrophicFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author tachyony
 *
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit()
    {
        //
    }

    @Override
    public void init()
    {
        //
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity != null)
        {
            switch (ID)
            {
                case 0:
                    return new GUIContainerEntrophicFurnace(player.inventory, ((TileEntrophicFurnace) tileEntity));
                default:
                    break;
            }
        }

        return null;
    }
}