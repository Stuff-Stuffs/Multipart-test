package net.fabricmc.example;

import alexiil.mc.lib.multipart.api.MultipartContainer;
import alexiil.mc.lib.multipart.api.MultipartUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;

public class MultipartItem extends Item {
    public MultipartItem(final Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult useOnBlock(final ItemUsageContext context) {
        if (!context.getWorld().isClient) {
            final Direction direction = context.getSide();
            final MultipartContainer.MultipartCreator creator = holder -> new BasicMultipart(ExampleMod.BASIC_PART_DEFINITION, holder, direction);
            MultipartContainer.PartOffer offer = MultipartUtil.offerNewPart(context.getWorld(), context.getBlockPos(), creator);
            if (offer == null) {
                offer = MultipartUtil.offerNewPart(context.getWorld(), context.getBlockPos().offset(direction), creator);
            }
            if (offer != null) {
                offer.apply();
                offer.getHolder().getPart().onPlacedBy(context.getPlayer(), context.getHand());
                return ActionResult.SUCCESS;
            }
        }
        return super.useOnBlock(context);
    }
}
