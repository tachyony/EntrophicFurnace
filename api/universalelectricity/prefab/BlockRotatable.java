package universalelectricity.prefab;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.prefab.implement.IRotatable;

/**
 * A block that can rotate based on placed position and wrenching.
 * 
 * @author Calclavia
 * 
 */
public abstract class BlockRotatable extends BlockAdvanced implements IRotatable
{
	public BlockRotatable(int id, Material material)
	{
		super(id, material);
	}

	public BlockRotatable(int id, int textureIndex, Material material)
	{
		super(id, textureIndex, material);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving par5EntityLiving)
	{
		int angle = MathHelper.floor_double((par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int change = 3;

		switch (angle)
		{
			case 0:
				change = 2;
				break;

			case 1:
				change = 5;
				break;

			case 2:
				change = 3;
				break;

			case 3:
				change = 4;
				break;
		}

		this.setDirection(world, x, y, z, ForgeDirection.getOrientation(change));
	}

	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ)
	{
		this.setDirection(world, x, y, z, ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[0][this.getDirection(world, x, y, z).ordinal()]));
		return true;
	}

	@Override
	public ForgeDirection getDirection(World world, int x, int y, int z)
	{
		return ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
	}

	@Override
	public void setDirection(World world, int x, int y, int z, ForgeDirection facingDirection)
	{
		world.setBlockMetadata(x, y, z, facingDirection.ordinal());
	}
}