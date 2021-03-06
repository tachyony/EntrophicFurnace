package entrophicFurnace.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import universalelectricity.prefab.block.BlockRotatable;
import universalelectricity.prefab.tile.TileEntityAdvanced;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import entrophicFurnace.EntrophicFurnace;
import entrophicFurnace.tileentity.TileEntrophicFurnace;

/**
 * @author tachyony
 *
 */
public class BlockEntrophicFurnace2 extends BlockRotatable
{
    /**
     * @param id Block id
     * @param material The material
     */
    public BlockEntrophicFurnace2(int id, Material material)
    {
        super(id, material);
        this.setUnlocalizedName("entrophicFurnace2");
        this.setStepSound(soundMetalFootstep);
    }

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
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

    /**
     * 
     * @param par1World
     * @param x
     * @param y
     * @param z
     * @param par5EntityPlayer
     * @param side
     * @param hitX
     * @param hitY
     * @param hitZ
     * @return Used wrench
     */
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

    /**
     * 
     */
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * 
     */
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * 
     */
    @Override
    public boolean canProvidePower()
    {
        return true;
    }

    /**
     * 
     * @param world
     * @return Tile entity
     */
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntrophicFurnace(0, this.blockID);
    }

    /**
     * 
     * @param world
     * @return Tile entity
     */
    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntrophicFurnace(0, this.blockID, metadata);
    }

    /**
     * 
     */
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

    /**
     * 
     */
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
     */
    @Override
    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        return super.isProvidingStrongPower(par1IBlockAccess, x, y, z, side);
    }

    /**
     * Is this block indirectly powering the block on the specified side
     */
    @Override
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        return super.isProvidingStrongPower(par1IBlockAccess, x, y, z, side);
    }
    
    /**
     * 
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        this.blockIcon = iconRegister.registerIcon(this.getUnlocalizedName());
    }
}
