package net.fabricmc.example;

import alexiil.mc.lib.multipart.api.AbstractPart;
import alexiil.mc.lib.multipart.api.MultipartHolder;
import alexiil.mc.lib.multipart.api.PartDefinition;
import alexiil.mc.lib.multipart.api.render.PartModelKey;
import alexiil.mc.lib.net.IMsgReadCtx;
import alexiil.mc.lib.net.IMsgWriteCtx;
import alexiil.mc.lib.net.NetByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class BasicMultipart extends AbstractPart {
    private final Direction direction;
    private final BasicMultipartKey key;

    private static final VoxelShape[] SHAPES;

    public BasicMultipart(PartDefinition definition, MultipartHolder holder, Direction direction) {
        super(definition, holder);
        this.direction = direction;
        key = new BasicMultipartKey(direction);
    }

    public BasicMultipart(PartDefinition definition, MultipartHolder holder, CompoundTag nbt) {
        this(definition, holder, Direction.values()[nbt.getByte("direction")]);
    }

    public BasicMultipart(PartDefinition definition, MultipartHolder holder, NetByteBuf buffer, IMsgReadCtx ctx) {
        this(definition, holder, Direction.values()[buffer.readByte()]);
    }

    @Override
    public void writeCreationData(NetByteBuf buffer, IMsgWriteCtx ctx) {
        ctx.assertServerSide();
        buffer.writeByte(direction.ordinal());
    }

    @Override
    public CompoundTag toTag() {
        CompoundTag tag = super.toTag();
        tag.putByte("direction", (byte) direction.ordinal());
        return tag;
    }

    @Override
    public VoxelShape getShape() {
        return SHAPES[direction.ordinal()];
    }

    @Override
    public PartModelKey getModelKey() {
        return key;
    }

    static {
        SHAPES = new VoxelShape[6];
        SHAPES[0] = VoxelShapes.cuboid(0, 0, 0, 1/16.0, 1/16.0, 1/1.16);
        SHAPES[1] = VoxelShapes.cuboid(1/16.0, 1/16.0, 1/1.16, 2/16.0, 2/16.0, 2/1.16);
        SHAPES[2] = VoxelShapes.cuboid(2/1.16, 2/16.0, 2/16.0, 3/16.0, 3/16.0, 3/1.16);
        SHAPES[3] = VoxelShapes.cuboid(3/16.0, 3/16.0, 3/1.16, 4/16.0, 4/16.0, 4/1.16);
        SHAPES[4] = VoxelShapes.cuboid(4/16.0, 4/16.0, 4/1.16, 5/16.0, 5/16.0, 5/1.16);
        SHAPES[5] = VoxelShapes.cuboid(5/16.0, 5/16.0, 5/1.16, 6/16.0, 6/16.0, 6/1.16);
    }
}
