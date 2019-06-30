package yamahari.ilikewood.gui.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.fonts.TextInputUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.CUpdateSignPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import yamahari.ilikewood.blocks.sign.WoodenHangingSignBlock;
import yamahari.ilikewood.blocks.sign.WoodenStandingSignBlock;
import yamahari.ilikewood.blocks.sign.WoodenWallSignBlock;
import yamahari.ilikewood.tileentities.WoodenSignTileEntity;

public class WoodenEditSignScreen extends Screen {
    private final WoodenSignTileEntity tileSign;
    private int updateCounter;
    private int editLine;
    private TextInputUtil textInputUtil;

    public WoodenEditSignScreen(WoodenSignTileEntity p_i1097_1_) {
        super(new TranslationTextComponent("sign.edit"));
        this.tileSign = p_i1097_1_;
    }

    protected void init() {
        this.minecraft.keyboardListener.enableRepeatEvents(true);
        this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 120, 200, 20, I18n.format("gui.done"), (p_214266_1_) -> {
            this.close();
        }));
        this.tileSign.setEditable(false);
        this.textInputUtil = new TextInputUtil(this.minecraft, this.tileSign.getText(this.editLine)::getString, (p_214265_1_) -> {
            this.tileSign.setText(this.editLine, new StringTextComponent(p_214265_1_));
        }, 90);
    }

    public void removed() {
        this.minecraft.keyboardListener.enableRepeatEvents(false);
        ClientPlayNetHandler lvt_1_1_ = this.minecraft.getConnection();
        if (lvt_1_1_ != null) {
            lvt_1_1_.sendPacket(new CUpdateSignPacket(this.tileSign.getPos(), this.tileSign.getText(0), this.tileSign.getText(1), this.tileSign.getText(2), this.tileSign.getText(3)));
        }

        this.tileSign.setEditable(true);
    }

    public void tick() {
        ++this.updateCounter;
        if (!this.tileSign.getType().isValidBlock(this.tileSign.getBlockState().getBlock())) {
            this.close();
        }

    }

    private void close() {
        this.tileSign.markDirty();
        this.minecraft.displayGuiScreen((Screen)null);
    }

    public boolean charTyped(char p_charTyped_1_, int p_charTyped_2_) {
        this.textInputUtil.func_216894_a(p_charTyped_1_);
        return true;
    }

    public void onClose() {
        this.close();
    }

    public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
        if (p_keyPressed_1_ == 265) {
            this.editLine = this.editLine - 1 & 3;
            this.textInputUtil.func_216899_b();
            return true;
        } else if (p_keyPressed_1_ != 264 && p_keyPressed_1_ != 257 && p_keyPressed_1_ != 335) {
            return this.textInputUtil.func_216897_a(p_keyPressed_1_) || super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
        } else {
            this.editLine = this.editLine + 1 & 3;
            this.textInputUtil.func_216899_b();
            return true;
        }
    }

    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        this.renderBackground();
        this.drawCenteredString(this.font, this.title.getFormattedText(), this.width / 2, 40, 16777215);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translatef((float)(this.width / 2), 0.0F, 50.0F);
        GlStateManager.scalef(-93.75F, -93.75F, -93.75F);
        GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
        BlockState blockState = this.tileSign.getBlockState();
        float rotation;
        if (blockState.getBlock() instanceof WoodenStandingSignBlock || blockState.getBlock() instanceof WoodenHangingSignBlock) {
            rotation = (float)(blockState.get(BlockStateProperties.ROTATION_0_15) * 360) / 16.0F;
        } else {
            rotation = (blockState.get(WoodenWallSignBlock.FACING)).getHorizontalAngle();
        }

        GlStateManager.rotatef(rotation, 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(0.0F, -1.0625F, 0.0F);
        this.tileSign.func_214062_a(this.editLine, this.textInputUtil.func_216896_c(), this.textInputUtil.func_216898_d(), this.updateCounter / 6 % 2 == 0);
        this.tileSign.func_214063_g();
        GlStateManager.popMatrix();
        super.render(p_render_1_, p_render_2_, p_render_3_);
    }
}
