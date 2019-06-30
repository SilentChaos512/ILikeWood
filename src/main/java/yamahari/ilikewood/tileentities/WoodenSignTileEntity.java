package yamahari.ilikewood.tileentities;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;
import yamahari.ilikewood.objectholders.ModTileEntityType;

public class WoodenSignTileEntity extends SignTileEntity {
    @Override
    public TileEntityType<?> getType() {
        return ModTileEntityType.wooden_sign;
    }
}
