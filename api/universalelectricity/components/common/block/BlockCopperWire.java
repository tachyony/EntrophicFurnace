package universalelectricity.components.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.components.common.BasicComponents;
import universalelectricity.components.common.tileentity.TileEntityCopperWire;
import universalelectricity.prefab.BlockConductor;
import universalelectricity.prefab.UETab;

public class BlockCopperWire extends BlockConductor
{
	public BlockCopperWire(int id)
	{
		super(id, Material.cloth);
		this.setBlockName("copperWire");
		this.setStepSound(soundClothFootstep);
		this.setResistance(0.2F);
		this.setHardness(0.1f);
		this.setBlockBounds(0.30F, 0.30F, 0.30F, 0.70F, 0.70F, 0.70F);
		this.blockIndexInTexture = 19;
		this.setCreativeTab(UETab.INSTANCE);
		this.setBurnProperties(this.blockID, 30, 60);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the
	 * shared face of two adjacent blocks and also whether the player can attach torches, redstone
	 * wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs,
	 * buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World var1)
	{
		return new TileEntityCopperWire();
	}

	@Override
	public String getTextureFile()
	{
		return BasicComponents.ITEM_TEXTURE_FILE;
	}
}