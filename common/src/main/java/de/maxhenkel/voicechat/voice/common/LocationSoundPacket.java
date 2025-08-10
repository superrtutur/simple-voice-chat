package de.maxhenkel.voicechat.voice.common;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;
import java.util.UUID;

public class LocationSoundPacket extends SoundPacket<LocationSoundPacket> {

    protected Vec3d location;
    protected float distance;
    protected String CustomInfo;

    public LocationSoundPacket(UUID channelId, UUID sender, Vec3d location, byte[] data, long sequenceNumber, float distance, @Nullable String category, String info) {
        super(channelId, sender, data, sequenceNumber, category, info);
        this.location = location;
        this.distance = distance;
        this.CustomInfo = info;
    }

    public LocationSoundPacket(UUID channelId, UUID sender, short[] data, Vec3d location, float distance, @Nullable String category, String info) {
        super(channelId, sender, data, category, info);
        this.location = location;
        this.distance = distance;
        this.CustomInfo = info;
    }

    public LocationSoundPacket() {

    }

    public Vec3d getLocation() {
        return location;
    }

    public float getDistance() {
        return distance;
    }

    public String getCustomInfo() {
        return CustomInfo;
    }

    public void setDistance(Float Distance) {
        distance = Distance;
    }

    public void setCustomInfo(String CInfo) {
        CustomInfo = CInfo;
    }

    @Override
    public LocationSoundPacket fromBytes(PacketBuffer buf) {
        LocationSoundPacket soundPacket = new LocationSoundPacket();
        soundPacket.channelId = buf.readUniqueId();
        soundPacket.sender = buf.readUniqueId();
        soundPacket.location = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        soundPacket.data = buf.readByteArray();
        soundPacket.sequenceNumber = buf.readLong();
        soundPacket.distance = buf.readFloat();
        soundPacket.custominfo = buf.readString(255);

        byte data = buf.readByte();
        if (hasFlag(data, HAS_CATEGORY_MASK)) {
            soundPacket.category = buf.readString(16);
        }

        return soundPacket;
    }

    @Override
    public void toBytes(PacketBuffer buf) {
        buf.writeUniqueId(channelId);
        buf.writeUniqueId(sender);
        buf.writeDouble(location.x);
        buf.writeDouble(location.y);
        buf.writeDouble(location.z);
        buf.writeByteArray(data);
        buf.writeLong(sequenceNumber);
        buf.writeFloat(distance);
        buf.writeString(custominfo);

        byte data = 0b0;
        if (category != null) {
            data = setFlag(data, HAS_CATEGORY_MASK);
        }
        buf.writeByte(data);
        if (category != null) {
            buf.writeString(category);
        }
    }
}
