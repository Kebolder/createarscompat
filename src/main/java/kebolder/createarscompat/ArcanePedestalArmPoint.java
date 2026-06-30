package kebolder.createarscompat;

import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPoint;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPointType;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Lets Create's Mechanical Arm target the Ars Nouveau Arcane Pedestal.
 *
 * Ars already exposes Capabilities.ItemHandler.BLOCK for the pedestal tile
 * (InvWrapper over the single-slot Container), and Create's base ArmInteractionPoint
 * inserts/extracts purely through that cap. So no custom point is needed - we only
 * declare "this block is a valid target".
 */
public class ArcanePedestalArmPoint extends ArmInteractionPointType {
    private static final ResourceLocation PEDESTAL =
            ResourceLocation.fromNamespaceAndPath("ars_nouveau", "arcane_pedestal");

    // Detect by block id, so this compiles/loads even when Ars is absent (block just never matches).
    @Override
    public boolean canCreatePoint(Level level, BlockPos pos, BlockState state) {
        return BuiltInRegistries.BLOCK.getKey(state.getBlock()).equals(PEDESTAL);
    }

    @Override
    public ArmInteractionPoint createPoint(Level level, BlockPos pos, BlockState state) {
        return new ArmInteractionPoint(this, level, pos, state);
    }
}
