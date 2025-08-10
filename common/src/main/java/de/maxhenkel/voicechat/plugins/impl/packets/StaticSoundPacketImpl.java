package de.maxhenkel.voicechat.plugins.impl.packets;

import de.maxhenkel.voicechat.api.packets.StaticSoundPacket;
import de.maxhenkel.voicechat.voice.common.GroupSoundPacket;
import de.maxhenkel.voicechat.voice.common.LocationSoundPacket;
import de.maxhenkel.voicechat.voice.common.Utils;

import javax.annotation.Nullable;
import java.util.UUID;

public class StaticSoundPacketImpl extends SoundPacketImpl implements StaticSoundPacket {

    private final GroupSoundPacket packet;
    private String CustomInfo;

    public StaticSoundPacketImpl(GroupSoundPacket packet) {
        super(packet);
        this.packet = packet;
        this.CustomInfo = packet.getCustomInfo();
    }

    public String getCustomInfo() {
        return packet.getCustomInfo();
    }

    public void setCustomInfo(String info) {
        packet.setCustomInfo(info);
        CustomInfo = info;
    }

    public static class BuilderImpl extends SoundPacketImpl.BuilderImpl<BuilderImpl, StaticSoundPacket> implements StaticSoundPacket.Builder<BuilderImpl> {

        protected String CustomInfo;

        public BuilderImpl(SoundPacketImpl soundPacket) {
            super(soundPacket);
            if (soundPacket instanceof LocationalSoundPacketImpl) {
                LocationalSoundPacketImpl p = (LocationalSoundPacketImpl) soundPacket;
                System.out.println("RECEIVE LOCATIONAL");
                CustomInfo = p.getCustomInfo();
            } else if (soundPacket instanceof EntitySoundPacketImpl) {
                EntitySoundPacketImpl p = (EntitySoundPacketImpl) soundPacket;
                CustomInfo = p.getCustomInfo();
            } else {
                if (soundPacket instanceof StaticSoundPacketImpl) {
                    StaticSoundPacketImpl p = (StaticSoundPacketImpl) soundPacket;
                    System.out.println("RECEIVE STATIC");
                    System.out.println(p.getCustomInfo());
                    CustomInfo = p.getCustomInfo();
                }
            }
        }

        public BuilderImpl(UUID channelId, UUID sender, byte[] opusEncodedData, long sequenceNumber, @Nullable String category) {
            super(channelId, sender, opusEncodedData, sequenceNumber, category);
        }

        @Override
        public StaticSoundPacket build() {
            return new StaticSoundPacketImpl(new GroupSoundPacket(channelId, sender, opusEncodedData, sequenceNumber, category, CustomInfo));
        }

    }

}
