package com.wuest.prefab.Structures.Gui;

import com.wuest.prefab.Events.ClientEventHandler;
import com.wuest.prefab.Gui.GuiLangKeys;
import com.wuest.prefab.Structures.Config.NetherGateConfiguration;
import com.wuest.prefab.Structures.Messages.StructureTagMessage.EnumStructureConfiguration;
import com.wuest.prefab.Structures.Predefined.StructureNetherGate;
import com.wuest.prefab.Structures.Render.StructureRenderHandler;
import javafx.util.Pair;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

/**
 * @author WuestMan
 */
public class GuiNetherGate extends GuiStructure {
	private static final ResourceLocation structureTopDown = new ResourceLocation("prefab", "textures/gui/nether_gate_top_down.png");
	protected NetherGateConfiguration configuration;

	public GuiNetherGate() {
		super("Nether Gate");
		this.structureConfiguration = EnumStructureConfiguration.NetherGate;
	}

	@Override
	protected Pair<Integer, Integer> getAdjustedXYValue() {
		return new Pair<>(this.getCenteredXAxis() - 213, this.getCenteredYAxis() - 83);
	}

	@Override
	protected void preButtonRender(int x, int y) {
		super.preButtonRender(x, y);

		this.minecraft.getTextureManager().bindTexture(structureTopDown);
		GuiStructure.drawModalRectWithCustomSizedTexture(x + 250, y, 1, 164, 108, 164, 108);
	}

	@Override
	protected void postButtonRender(int x, int y) {
		this.minecraft.fontRenderer.drawSplitString(GuiLangKeys.translateString(GuiLangKeys.GUI_BLOCK_CLICKED), x + 147, y + 10, 95, this.textColor);
	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
	 */
	@Override
	public void buttonClicked(AbstractButton button) {
		this.performCancelOrBuildOrHouseFacing(this.configuration, button);

		if (button == this.btnVisualize) {
			StructureNetherGate structure = StructureNetherGate.CreateInstance(StructureNetherGate.ASSETLOCATION, StructureNetherGate.class);
			StructureRenderHandler.setStructure(structure, Direction.NORTH, this.configuration);
			assert this.minecraft != null;
			this.minecraft.displayGuiScreen(null);
		}
	}

	@Override
	protected void Initialize() {
		this.configuration = ClientEventHandler.playerConfig.getClientConfig("Nether Gate", NetherGateConfiguration.class);
		this.configuration.pos = this.pos;

		// Get the upper left hand corner of the GUI box.
		int grayBoxX = this.getCenteredXAxis() - 213;
		int grayBoxY = this.getCenteredYAxis() - 83;

		// Create the buttons.
		this.btnVisualize = this.createAndAddButton(grayBoxX + 10, grayBoxY + 20, 90, 20, GuiLangKeys.translateString(GuiLangKeys.GUI_BUTTON_PREVIEW));

		// Create the done and cancel buttons.
		this.btnBuild = this.createAndAddButton(grayBoxX + 10, grayBoxY + 136, 90, 20, GuiLangKeys.translateString(GuiLangKeys.GUI_BUTTON_BUILD));

		this.btnCancel = this.createAndAddButton(grayBoxX + 147, grayBoxY + 136, 90, 20, GuiLangKeys.translateString(GuiLangKeys.GUI_BUTTON_CANCEL));
	}
}
