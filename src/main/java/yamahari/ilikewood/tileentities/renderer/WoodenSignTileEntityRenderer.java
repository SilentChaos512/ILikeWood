package yamahari.ilikewood.tileentities.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yamahari.ilikewood.blocks.sign.WoodenHangingSignBlock;
import yamahari.ilikewood.blocks.sign.WoodenStandingSignBlock;
import yamahari.ilikewood.blocks.sign.WoodenWallSignBlock;
import yamahari.ilikewood.tileentities.WoodenSignTileEntity;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class WoodenSignTileEntityRenderer extends TileEntityRenderer<WoodenSignTileEntity> {
    @Override
    public void func_199341_a(WoodenSignTileEntity tileEntityIn, double x, double y, double z, float p_199341_8_, int p_199341_9_) {
        BlockState blockState = tileEntityIn.getBlockState();
        GlStateManager.pushMatrix();
        if (blockState.getBlock() instanceof WoodenStandingSignBlock) {
            GlStateManager.translatef((float)x + 0.525F, (float)y + 0.5F, (float)z + 0.5F);
            GlStateManager.rotatef(-((float)(blockState.get(BlockStateProperties.ROTATION_0_15) * 360) / 16.0F), 0.0F, 1.0F, 0.0F);
        } else if(blockState.getBlock() instanceof WoodenHangingSignBlock){
            GlStateManager.translatef((float)x + 0.525F, (float)y + 0.5F, (float)z + 0.5F);
            GlStateManager.rotatef(-((float)(blockState.get(BlockStateProperties.ROTATION_0_15) * 360) / 16.0F), 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(0.0F, -0.5125F, 0.0F);
        } else {
            GlStateManager.translatef((float)x + 0.525F, (float)y + 0.5F, (float)z + 0.5F);
            GlStateManager.rotatef(-blockState.get(WoodenWallSignBlock.FACING).getHorizontalAngle(), 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(0.0F, -0.3125F, -0.4375F);
        }

        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();
        GlStateManager.scalef(0.6666667F, -0.6666667F, -0.6666667F);


        GlStateManager.popMatrix();
        FontRenderer lvt_12_1_ = this.getFontRenderer();
        GlStateManager.translatef(0.0F, 0.33333334F, 0.046666667F);
        GlStateManager.scalef(0.010416667F, -0.010416667F, 0.010416667F);
        GlStateManager.normal3f(0.0F, 0.0F, -0.010416667F);
        GlStateManager.depthMask(false);
        int lvt_14_1_ = tileEntityIn.func_214066_f().func_218388_g();
        if (p_199341_9_ < 0) {
            for(int lvt_15_1_ = 0; lvt_15_1_ < 4; ++lvt_15_1_) {
                String lvt_16_1_ = tileEntityIn.getRenderText(lvt_15_1_, (p_212491_1_) -> {
                    List<ITextComponent> lvt_2_1_ = RenderComponentsUtil.splitText(p_212491_1_, 90, lvt_12_1_, false, true);
                    return lvt_2_1_.isEmpty() ? "" : lvt_2_1_.get(0).getFormattedText();
                });
                if (lvt_16_1_ != null) {
                    lvt_12_1_.drawString(lvt_16_1_, (float)(-lvt_12_1_.getStringWidth(lvt_16_1_) / 2), (float)(lvt_15_1_ * 10 - tileEntityIn.signText.length * 5), lvt_14_1_);
                    if (lvt_15_1_ == tileEntityIn.func_214064_s() && tileEntityIn.func_214065_t() >= 0) {
                        int lvt_17_1_ = lvt_12_1_.getStringWidth(lvt_16_1_.substring(0, Math.max(Math.min(tileEntityIn.func_214065_t(), lvt_16_1_.length()), 0)));
                        int lvt_18_1_ = lvt_12_1_.getBidiFlag() ? -1 : 1;
                        int lvt_19_1_ = (lvt_17_1_ - lvt_12_1_.getStringWidth(lvt_16_1_) / 2) * lvt_18_1_;
                        int lvt_20_1_ = lvt_15_1_ * 10 - tileEntityIn.signText.length * 5;
                        int var10001;
                        if (tileEntityIn.func_214069_r()) {
                            if (tileEntityIn.func_214065_t() < lvt_16_1_.length()) {
                                var10001 = lvt_20_1_ - 1;
                                int var10002 = lvt_19_1_ + 1;
                                AbstractGui.fill(lvt_19_1_, var10001, var10002, lvt_20_1_ + 9, -16777216 | lvt_14_1_);
                            } else {
                                lvt_12_1_.drawString("_", (float)lvt_19_1_, (float)lvt_20_1_, lvt_14_1_);
                            }
                        }

                        if (tileEntityIn.func_214067_u() != tileEntityIn.func_214065_t()) {
                            int lvt_21_1_ = Math.min(tileEntityIn.func_214065_t(), tileEntityIn.func_214067_u());
                            int lvt_22_1_ = Math.max(tileEntityIn.func_214065_t(), tileEntityIn.func_214067_u());
                            int lvt_23_1_ = (lvt_12_1_.getStringWidth(lvt_16_1_.substring(0, lvt_21_1_)) - lvt_12_1_.getStringWidth(lvt_16_1_) / 2) * lvt_18_1_;
                            int lvt_24_1_ = (lvt_12_1_.getStringWidth(lvt_16_1_.substring(0, lvt_22_1_)) - lvt_12_1_.getStringWidth(lvt_16_1_) / 2) * lvt_18_1_;
                            var10001 = Math.min(lvt_23_1_, lvt_24_1_);
                            int var10003 = Math.max(lvt_23_1_, lvt_24_1_);
                            lvt_12_1_.getClass();
                            this.func_217657_a(var10001, lvt_20_1_, var10003, lvt_20_1_ + 9);
                        }
                    }
                }
            }
        }

        GlStateManager.depthMask(true);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }

    private void func_217657_a(int p_217657_1_, int p_217657_2_, int p_217657_3_, int p_217657_4_) {
        Tessellator lvt_5_1_ = Tessellator.getInstance();
        BufferBuilder lvt_6_1_ = lvt_5_1_.getBuffer();
        GlStateManager.color4f(0.0F, 0.0F, 255.0F, 255.0F);
        GlStateManager.disableTexture();
        GlStateManager.enableColorLogicOp();
        GlStateManager.logicOp(GlStateManager.LogicOp.OR_REVERSE);
        lvt_6_1_.begin(7, DefaultVertexFormats.POSITION);
        lvt_6_1_.pos((double)p_217657_1_, (double)p_217657_4_, 0.0D).endVertex();
        lvt_6_1_.pos((double)p_217657_3_, (double)p_217657_4_, 0.0D).endVertex();
        lvt_6_1_.pos((double)p_217657_3_, (double)p_217657_2_, 0.0D).endVertex();
        lvt_6_1_.pos((double)p_217657_1_, (double)p_217657_2_, 0.0D).endVertex();
        lvt_5_1_.draw();
        GlStateManager.disableColorLogicOp();
        GlStateManager.enableTexture();
    }
}
