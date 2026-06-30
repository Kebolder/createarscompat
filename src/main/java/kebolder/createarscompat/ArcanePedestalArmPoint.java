package kebolder.createarscompat;

import java.util.Set;

import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPoint;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPointType;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Lets Create's Mechanical Arm target the Ars Nouveau Arcane Pedestal and Arcane Platform.
 *
 * Both share the same block entity, for which Ars already exposes Capabilities.ItemHandler.BLOCK
 * (InvWrapper over the single-slot Container). Create's base ArmInteractionPoint inserts/extracts
 * purely through that cap, so no custom point is needed - we only declare "this block is a valid target".
 */
public class ArcanePedestalArmPoint extends ArmInteractionPointType {
    // Detect by block id, so this compiles/loads even when Ars is absent (blocks just never match).
    private static final Set<ResourceLocation> TARGETS = Set.of(
            ResourceLocation.fromNamespaceAndPath("ars_nouveau", "arcane_pedestal"),
            ResourceLocation.fromNamespaceAndPath("ars_nouveau", "arcane_platform"));

    @Override
    public boolean canCreatePoint(Level level, BlockPos pos, BlockState state) {
        return TARGETS.contains(BuiltInRegistries.BLOCK.getKey(state.getBlock()));
    }

    @Override
    public ArmInteractionPoint createPoint(Level level, BlockPos pos, BlockState state) {
        return new ArmInteractionPoint(this, level, pos, state);
    }
}
