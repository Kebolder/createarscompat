package kebolder.createarscompat;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.simibubi.create.api.registry.CreateRegistries;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(CreateArsCompat.MODID)
public class CreateArsCompat {
    public static final String MODID = "createarscompat";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateArsCompat(IEventBus modEventBus) {
        modEventBus.addListener(this::registerArmPoints);
    }

    // Create's arm-point registry lives in BuiltInRegistries.REGISTRY and is already frozen by the
    // time mod constructors run, so we add to it during NeoForge's RegisterEvent instead.
    private void registerArmPoints(RegisterEvent event) {
        event.register(CreateRegistries.ARM_INTERACTION_POINT_TYPE, helper ->
                helper.register(ResourceLocation.fromNamespaceAndPath(MODID, "arcane_pedestal"),
                        new ArcanePedestalArmPoint()));
    }
}
