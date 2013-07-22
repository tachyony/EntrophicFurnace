package entrophicFurnace.generic;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import universalelectricity.prefab.block.BlockRotatable;
import universalelectricity.prefab.tile.TileEntityAdvanced;

/**
 * @author tachyony
 *
 */
public class BlockEntrophicFurnace2 extends BlockRotatable
{
    /**
     * @param id
     * @param textureIndex
     */
    public BlockEntrophicFurnace2(int id, Material material)
    {
        super(id, material);
        this.setUnlocalizedName("EntrophicFurnace2");
        this.setStepSound(soundMetalFootstep);
    }

    /**
	 *
	 */
    @Override
    public void randomDisplayTick(World par1World, int x, int y, int z, Random par5Random)
    {
        //
    }

    /**
	 *
	 *
    @SideOnly(Side.CLIENT)
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int metadata)
    {
        if (side == 0 || side == 1)
        {
            return this.blockIndexInTexture; // Top and bottom always look
                                             // the same
        }

        switch (metadata)
        {
            case 0: // North Facing Block
                if (side == 2)
                    return this.blockIndexInTexture - 1; // North Side of Block
                if (side == 3)
                    return this.blockIndexInTexture; // South Side of Block
                if (side == 4)
                    return this.blockIndexInTexture; // West Side of Block
                if (side == 5)
                    return this.blockIndexInTexture; // East Side of Block
                break;
            case 1: // East Facing Block
                if (side == 2)
                    return this.blockIndexInTexture; // North Side of Block
                if (side == 3)
                    return this.blockIndexInTexture; // South Side of Block
                if (side == 4)
                    return this.blockIndexInTexture; // West Side of Block
                if (side == 5)
                    return this.blockIndexInTexture - 1; // East Side of Block
                break;
            case 2: // South Facing Block
                if (side == 2)
                    return this.blockIndexInTexture; // North Side of Block
                if (side == 3)
                    return this.blockIndexInTexture - 1; // South Side of Block
                if (side == 4)
                    return this.blockIndexInTexture; // West Side of Block
                if (side == 5)
                    return this.blockIndexInTexture; // East Side of Block
                break;
            case 3: // West Facing Block
                if (side == 2)
                    return this.blockIndexInTexture;// North Side of Block
                if (side == 3)
                    return this.blockIndexInTexture; // South Side of Block
                if (side == 4)
                    return this.blockIndexInTexture - 1; // West Side of Block
                if (side == 5)
                    return this.blockIndexInTexture; // East Side of Block
                break;
            default:
                return this.blockIndexInTexture;
        }

        return this.blockIndexInTexture; // Fall back if meta is out of range
    }*/

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        int angle = MathHelper.floor_double((entityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if (metadata == 0)
        {
            switch (angle)
            {
                case 0:
                    metadata += 0;
                    break; // South Facing Block = meta 2
                case 1:
                    metadata += 1;
                    break; // West Facing Block = meta 3
                case 2:
                    metadata += 2;
                    break; // North Facing Block = meta 0
                case 3:
                    metadata += 3;
                    break; // East Facing Block = meta 1
                default:
                    metadata += 0;
                    break;
            }
        }

        world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
    }

    @Override
    public boolean onUseWrench(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side,
            float hitX, float hitY, float hitZ)
    {
        int change = 0;
        switch (par1World.getBlockMetadata(x, y, z))
        {
            case 0:
                change = 0;
                break;
            case 1:
                change = 1;
                break;
            case 2:
                change = 2;
                break;
            case 3:
                change = 3;
                break;
            default:
                change = 0;
                break;
        }

        par1World.setBlockMetadataWithNotify(x, y, z, change, 2);
        ((TileEntityAdvanced) par1World.getBlockTileEntity(x, y, z)).initiate();
        return true;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean canProvidePower()
    {
        return true;
    }

    /*@Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityEntrophicFurnace(1, this.blockID);
    }*/

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityEntrophicFurnace(1, this.blockID);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        int id = idPicked(world, x, y, z);
        if (id == 0)
        {
            return null;
        }

        return new ItemStack(id, 1, 2);
    }

    @Override
    public int damageDropped(int metadata)
    {
        return 2;
    }

    /**
     * Called when the machine is right clicked by the player
     *
     * @return True if something happens
     */
    @Override
    public boolean onMachineActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int side,
            float hitX, float hitY, float hitZ)
    {
        if (!par1World.isRemote)
        {
            par5EntityPlayer.openGui(EntrophicFurnace.instance, 0, par1World, x, y, z);
            return true;
        }

        return true;
    }

    /**
     * Is this block powering the block on the specified side
     *
    @Override
    public boolean isProvidingStrongPower(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        TileEntity tileEntity = par1IBlockAccess.getBlockTileEntity(x, y, z);
        if (tileEntity instanceof IRedstoneProvider)
        {
            return ((IRedstoneProvider) tileEntity).isPoweringTo(ForgeDirection.getOrientation(side));
        }

        return false;
    }

    /**
     * Is this block indirectly powering the block on the specified side
     *
    @Override
    public boolean isProvidingWeakPower(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        TileEntity tileEntity = par1IBlockAccess.getBlockTileEntity(x, y, z);
        if (tileEntity instanceof IRedstoneProvider)
        {
            return ((IRedstoneProvider) tileEntity).isIndirectlyPoweringTo(ForgeDirection.getOrientation(side));
        }

        return false;
    }*/
}
