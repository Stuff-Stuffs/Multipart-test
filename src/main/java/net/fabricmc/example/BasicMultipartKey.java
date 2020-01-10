package net.fabricmc.example;

import alexiil.mc.lib.multipart.api.render.PartModelKey;
import net.minecraft.util.math.Direction;

public class BasicMultipartKey extends PartModelKey {
    final Direction direction;

    public BasicMultipartKey(Direction direction) {
        this.direction = direction;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicMultipartKey that = (BasicMultipartKey) o;

        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        return direction.hashCode();
    }
}
