package yamahari.ilikewood.util;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenWoodenSignEditor {
    public BlockPos pos;

    public OpenWoodenSignEditor(BlockPos pos)
    {
        this.pos = pos;
    }

    public OpenWoodenSignEditor(PacketBuffer buf)
    {
        pos = buf.readBlockPos();
    }

    public void encode(PacketBuffer buf)
    {
        buf.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        ClientUtil.openWoodenSignGUI(pos);
    }
}

