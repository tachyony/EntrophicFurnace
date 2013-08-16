package entrophicFurnace.generic;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import entrophicFurnace.tileentity.TileEntrophicFurnace;


/**
 * @author tachyony
 */
public class GUIContainerEntrophicFurnace extends GuiContainer
{
    private TileEntrophicFurnace tileEntity;

    private int containerWidth;

    private int containerHeight;

    /**
     * @param par1InventoryPlayer
     * @param tileEntity
     */
    public GUIContainerEntrophicFurnace(InventoryPlayer par1InventoryPlayer, TileEntrophicFurnace tileEntity)
    {
        super(new ContainerEntrophicFurnace(par1InventoryPlayer, tileEntity));
        this.tileEntity = tileEntity;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of
     * the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString("Quantum Furnace", 60, 6, 4210752);
        this.fontRenderer.drawString("Smelting:", 10, 28, 4210752);
        this.fontRenderer.drawString("Battery:", 10, 53, 4210752);
        this.fontRenderer.drawString(String.format("Charge: %s",
            Integer.valueOf((int)(this.tileEntity.getInternalCharge() / this.tileEntity.getVoltage()))), 82, 45, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2,
                4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the
     * items)
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/textures/gui/furnace.png");
        this.containerWidth = (this.width - this.xSize) / 2;
        this.containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
        if (this.tileEntity.smeltingTicks > 0)
        {
            int scale = (int) (((double) this.tileEntity.smeltingTicks / (double) TileEntrophicFurnace.SMELTING_TIME_REQUIRED) * 23);
            this.drawTexturedModalRect(this.containerWidth + 77, this.containerHeight + 24, 176, 0, 23 - scale, 20);
        }
    }
}