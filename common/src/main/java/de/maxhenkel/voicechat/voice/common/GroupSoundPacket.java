package de.maxhenkel.voicechat.voice.common;

import net.minecraft.network.PacketBuffer;

import javax.annotation.Nullable;
import java.util.UUID;

public class GroupSoundPacket extends SoundPacket<GroupSoundPacket> {

    protected String custominfo;

    public GroupSoundPacket(UUID channelId, UUID sender, byte[] data, long sequenceNumber, @Nullable String category, String info) {
        super(channelId, sender, data, sequenceNumber, category, info);
        custominfo = info;
    }

    public GroupSoundPacket(UUID channelId, UUID sender, short[] data, @Nullable String category, String info) {
        super(channelId, sender, data, category, info);
        custominfo = info;
    }

    public GroupSoundPacket() {

    }

    public String getCustomInfo() {
        return custominfo;
    }

    public void setCustomInfo(String CustomInfo) {
        custominfo = CustomInfo;
    }

    @Override
    public GroupSoundPacket fromBytes(PacketBuffer buf) {
        GroupSoundPacket soundPacket = new GroupSoundPacket();
        soundPacket.channelId = buf.readUniqueId();
        soundPacket.sender = buf.readUniqueId();
        soundPacket.data = buf.readByteArray();
        soundPacket.sequenceNumber = buf.readLong();
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
        buf.writeByteArray(data);
        buf.writeLong(sequenceNumber);
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
