package net.fabricmc.example;

import alexiil.mc.lib.multipart.api.PartDefinition;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {
	public static final PartDefinition BASIC_PART_DEFINITION = new PartDefinition(new Identifier("stuff_stuffs", "basic_multipart"), BasicMultipart::new, BasicMultipart::new);
	public static final Item BASIC_MULTIPART_ITEM = Registry.register(Registry.ITEM, new Identifier("stuff_stuffs", "basic_multipart_item"), new MultipartItem(new Item.Settings()));

	@Override
	public void onInitialize() {
	}

	static {
		BASIC_PART_DEFINITION.register();
	}
}
