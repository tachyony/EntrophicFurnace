/*
 * Copyright (c) SpaceToad, 2011-2012
 * http://www.mod-buildcraft.com
 * 
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package entrophicFurnace.block;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;

import entrophicFurnace.generic.EntityDropParticleFX;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public class BlockDarkWater extends BlockFluidClassic {

	protected float particleRed;
	
	protected float particleGreen;
	
	protected float particleBlue;

	/**
	 * 
	 * @param id
	 * @param fluid
	 * @param material
	 */
	public BlockDarkWater(int id, Fluid fluid, Material material) {
		super(id, fluid, material);
	}
	
	@SideOnly(Side.CLIENT)
	protected Icon[] theIcon;
	
	protected boolean flammable;
	
	protected int flammability = 0;

	/**
	 * 
	 */
	@Override
	public Icon getIcon(int side, int meta) {
		return side != 0 && side != 1 ? this.theIcon[1] : this.theIcon[0];
	}

	/**
	 * 
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.theIcon = new Icon[]{iconRegister.registerIcon("buildcraft:" + this.fluidName + "_still"), iconRegister.registerIcon("buildcraft:" + this.fluidName + "_flow")};
	}

	/**
	 * 
	 * @param flammable
	 * @return Fluid
	 */
	public BlockDarkWater setFlammable(boolean flammable) {
		this.flammable = flammable;
		return this;
	}

	/**
	 * 
	 * @param flammability
	 * @return Fluid
	 */
	public BlockDarkWater setFlammability(int flammability) {
		this.flammability = flammability;
		return this;
	}

	/**
	 * 
	 */
	@Override
	public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face) {
		return this.flammable ? 300 : 0;
	}

	/**
	 * 
	 */
	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face) {
		return this.flammability;
	}

	/**
	 * 
	 */
	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face) {
		return this.flammable;
	}

	/**
	 * 
	 */
	@Override
	public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side) {
		return this.flammable && this.flammability == 0;
	}

	/**
	 * 
	 * @param particleRed
	 * @param particleGreen
	 * @param particleBlue
	 * @return Fluid
	 */
	public BlockDarkWater setParticleColor(float particleRed, float particleGreen, float particleBlue){
		this.particleRed = particleRed;
		this.particleGreen = particleGreen;
		this.particleBlue = particleBlue;
		return this;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("cast")
    @Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		super.randomDisplayTick(world, x, y, z, rand);
		if (rand.nextInt(10) == 0 && world.doesBlockHaveSolidTopSurface(x, y - 1, z) && !world.getBlockMaterial(x, y - 2, z).blocksMovement()) {
			double px = (float)x + rand.nextFloat();
			double py = y - 1.05D;
			double pz = (float)z + rand.nextFloat();	
			EntityFX fx = new EntityDropParticleFX(world, px, py, pz, this.particleRed, this.particleGreen, this.particleBlue);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
		}
	}
	
	/**
	 * 
	 */
	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if (world.getBlockMaterial(x,  y,  z).isLiquid()) return false;
		return super.canDisplace(world, x, y, z);
	}
	
	/**
	 * 
	 */
	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		if (world.getBlockMaterial(x,  y,  z).isLiquid()) return false;
		return super.displaceIfPossible(world, x, y, z);
	}
}
