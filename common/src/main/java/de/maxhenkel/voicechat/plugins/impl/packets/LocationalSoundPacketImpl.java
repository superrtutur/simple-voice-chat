package de.maxhenkel.voicechat.plugins.impl.packets;

import de.maxhenkel.voicechat.api.Position;
import de.maxhenkel.voicechat.api.packets.LocationalSoundPacket;
import de.maxhenkel.voicechat.plugins.impl.PositionImpl;
import de.maxhenkel.voicechat.voice.common.GroupSoundPacket;
import de.maxhenkel.voicechat.voice.common.LocationSoundPacket;
import de.maxhenkel.voicechat.voice.common.Utils;

import javax.annotation.Nullable;
import java.util.UUID;

public class LocationalSoundPacketImpl extends SoundPacketImpl implements LocationalSoundPacket {

    private final LocationSoundPacket packet;
    private final PositionImpl position;
    private String CustomInfo;

    public LocationalSoundPacketImpl(LocationSoundPacket packet) {
        super(packet);
        this.packet = packet;
        this.position = new PositionImpl(packet.getLocation());
        this.CustomInfo = packet.getCustomInfo();
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public float getDistance() {
        return packet.getDistance();
    }

    @Override
    public String getCustomInfo() {
        if(CustomInfo == null) {
            return packet.getCustomInfo();
        }
        return CustomInfo;
    }

    @Override
    public void setDistance(float newvalue) {
        packet.setDistance(newvalue);
    }

    @Override
    public void setCustomInfo(String info) {
        this.CustomInfo = info;
        packet.setCustomInfo(info);
    }


    @Override
    public LocationSoundPacket getPacket() {
        return packet;
    }

    public static class BuilderImpl extends SoundPacketImpl.BuilderImpl<BuilderImpl, LocationalSoundPacket> implements LocationalSoundPacket.Builder<BuilderImpl> {

        protected PositionImpl position;
        protected float distance;
        protected String CustomInfo;

        public BuilderImpl(SoundPacketImpl soundPacket) {
            super(soundPacket);
            if (soundPacket instanceof LocationalSoundPacketImpl) {
                LocationalSoundPacketImpl p = (LocationalSoundPacketImpl) soundPacket;
                position = p.position;
                distance = p.getDistance();
                CustomInfo = p.getCustomInfo();
            } else if (soundPacket instanceof EntitySoundPacketImpl) {
                EntitySoundPacketImpl p = (EntitySoundPacketImpl) soundPacket;
                distance = p.getDistance();
                CustomInfo = p.getCustomInfo();
            } else {
                if (soundPacket instanceof StaticSoundPacketImpl) {
                    StaticSoundPacketImpl p = (StaticSoundPacketImpl) soundPacket;
                    CustomInfo = p.getCustomInfo();
                }


                distance = Utils.getDefaultDistanceServer();
            }
        }

        public BuilderImpl(UUID channelId, UUID sender, byte[] opusEncodedData, long sequenceNumber, @Nullable String category) {
            super(channelId, sender, opusEncodedData, sequenceNumber, category);
            distance = Utils.getDefaultDistanceServer();
        }

        @Override
        public BuilderImpl position(Position position) {
            this.position = (PositionImpl) position;
            return this;
        }

        @Override
        public LocationalSoundPacketImpl.BuilderImpl distance(float distance) {
            this.distance = distance;
            return this;
        }

        @Override
        public BuilderImpl custominfo(String info) {
            this.CustomInfo = info;
            return this;
        }

        @Override
        public LocationalSoundPacket build() {
            if (position == null) {
                throw new IllegalStateException("position missing");
            }
            return new LocationalSoundPacketImpl(new LocationSoundPacket(channelId, sender, position.getPosition(), opusEncodedData, sequenceNumber, distance, category, CustomInfo));
        }

    }

}
