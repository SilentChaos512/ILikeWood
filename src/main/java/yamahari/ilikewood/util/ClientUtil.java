package yamahari.ilikewood.util;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import yamahari.ilikewood.gui.screen.WoodenEditSignScreen;
import yamahari.ilikewood.tileentities.WoodenSignTileEntity;

public class ClientUtil {
    public static void openWoodenSignGUI(BlockPos pos)
    {
        Minecraft mc = Minecraft.getInstance();
        mc.execute(() -> {
            World world = mc.world;
            TileEntity te = world.getTileEntity(pos);
            if (te instanceof WoodenSignTileEntity)
            {
                mc.displayGuiScreen(new WoodenEditSignScreen((WoodenSignTileEntity) te));
            }
        });
    }
}
