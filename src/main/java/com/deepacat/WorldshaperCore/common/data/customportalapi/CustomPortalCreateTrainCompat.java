package com.deepacat.WorldshaperCore.common.data.customportalapi;

import com.simibubi.create.api.contraption.train.PortalTrackProvider;
import com.simibubi.create.content.contraptions.glue.SuperGlueEntity;
import net.createmod.catnip.math.BlockFace;
import net.kyrptonaught.customportalapi.util.CustomPortalHelper;
import net.kyrptonaught.customportalapi.util.CustomTeleporter;
import net.kyrptonaught.customportalapi.util.PortalLink;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.ITeleporter;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class CustomPortalCreateTrainCompat {
    public static PortalTrackProvider.Exit findExit(ServerLevel levelIn, BlockFace trackIn, PortalLink portalLink) {
        ResourceKey<Level> trainDepot = ResourceKey.create(Registries.DIMENSION, portalLink.dimID);
        return probeExit(levelIn, trackIn, Level.OVERWORLD, trainDepot,
                (sl) -> CustomPortalCreateTrainCompat.makeTeleporter(levelIn, trackIn, portalLink));
    }

    public static ITeleporter makeTeleporter(ServerLevel levelIn, BlockFace trackIn, PortalLink portalLink) {
        return new ITeleporter() {

            @Override
            public @Nullable PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
                return CustomTeleporter.customTPTarget(
                        destWorld,
                        entity,
                        trackIn.getConnectedPos(),
                        CustomPortalHelper.getPortalBase(levelIn, entity.getOnPos()),
                        portalLink.getFrameTester());
            }
        };
    }

    // TODO: change this to something less convoluted
    public static PortalTrackProvider.Exit probeExit(ServerLevel levelIn, BlockFace trackIn,
                                                     ResourceKey<Level> firstDimension, ResourceKey<Level> secondDimension,
                                                     Function<ServerLevel, ITeleporter> customPortalForcer) {
        ResourceKey<Level> resourcekey = levelIn.dimension() == secondDimension ? firstDimension : secondDimension;
        MinecraftServer minecraftserver = levelIn.getServer();
        ServerLevel otherLevel = minecraftserver.getLevel(resourcekey);

        if (otherLevel == null || !minecraftserver.isNetherEnabled())
            return null;

        BlockPos portalPos = trackIn.getConnectedPos();
        BlockState portalState = levelIn.getBlockState(portalPos);
        ITeleporter teleporter = customPortalForcer.apply(otherLevel);

        SuperGlueEntity probe = new SuperGlueEntity(levelIn, new AABB(portalPos));
        probe.setYRot(trackIn.getFace()
                .toYRot());
        probe.setPortalEntrancePos();

        PortalInfo portalinfo = teleporter.getPortalInfo(probe, otherLevel, probe::findDimensionEntryPoint);
        if (portalinfo == null)
            return null;

        BlockPos otherPortalPos = BlockPos.containing(portalinfo.pos);
        BlockState otherPortalState = otherLevel.getBlockState(otherPortalPos);
        if (otherPortalState.getBlock() != portalState.getBlock())
            return null;

        Direction targetDirection = trackIn.getFace();
        if (targetDirection.getAxis() == otherPortalState.getValue(BlockStateProperties.AXIS))
            targetDirection = targetDirection.getClockWise();
        BlockPos otherPos = otherPortalPos.relative(targetDirection);
        return new PortalTrackProvider.Exit(otherLevel, new BlockFace(otherPos, targetDirection.getOpposite()));
    }
}