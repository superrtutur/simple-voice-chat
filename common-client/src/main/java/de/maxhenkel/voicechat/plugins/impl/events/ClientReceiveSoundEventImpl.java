package de.maxhenkel.voicechat.plugins.impl.events;

import de.maxhenkel.voicechat.api.Position;
import de.maxhenkel.voicechat.api.events.ClientReceiveSoundEvent;

import javax.annotation.Nullable;
import java.util.UUID;

public class ClientReceiveSoundEventImpl extends ClientEventImpl implements ClientReceiveSoundEvent {

    private UUID id;
    private short[] rawAudio;

    public ClientReceiveSoundEventImpl(UUID id, short[] rawAudio) {
        this.id = id;
        this.rawAudio = rawAudio;

    }

    @Override
    public boolean isCancellable() {
        return false;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Nullable
    @Override
    public short[] getRawAudio() {
        return rawAudio;
    }

    @Override
    public void setRawAudio(@Nullable short[] rawAudio) {
        this.rawAudio = rawAudio;
    }

    public static class EntitySoundImpl extends ClientReceiveSoundEventImpl implements EntitySound {
        private UUID entity;
        private boolean whispering;
        private float distance;
        private String CustomInfo;

        public EntitySoundImpl(UUID id, UUID entity, short[] rawAudio, boolean whispering, float distance, String info) {
            super(id, rawAudio);
            this.entity = entity;
            this.whispering = whispering;
            this.distance = distance;
            this.CustomInfo = info;
        }

        @Override
        public UUID getEntityId() {
            return entity;
        }

        @Override
        public boolean isWhispering() {
            return whispering;
        }

        @Override
        public float getDistance() {
            return distance;
        }

        @Override
        public String getCustomInfo() {
            return CustomInfo;
        }

        @Override
        public void setCustomInfo(String Info) {
            CustomInfo = Info;
        }
    }

    public static class LocationalSoundImpl extends ClientReceiveSoundEventImpl implements LocationalSound {
        private Position position;
        private float distance;
        private String CustomInfo;

        public LocationalSoundImpl(UUID id, short[] rawAudio, Position position, float distance, String info) {
            super(id, rawAudio);
            this.position = position;
            this.distance = distance;
            this.CustomInfo = info;
        }

        @Override
        public Position getPosition() {
            return position;
        }

        @Override
        public float getDistance() {
            return distance;
        }

        @Override
        public void setDistance(float Distance) {
            this.distance = Distance;
        }

        @Override
        public String getCustomInfo() {
            return CustomInfo;
        }

        @Override
        public void setCustomInfo(String Info) {
            CustomInfo = Info;
        }
    }

    public static class StaticSoundImpl extends ClientReceiveSoundEventImpl implements StaticSound {
        private String CustomInfo;

        public StaticSoundImpl(UUID id, short[] rawAudio, String Info) {
            super(id, rawAudio);
            this.CustomInfo = Info;
        }

        @Override
        public String getCustomInfo() {
            return CustomInfo;
        }

        @Override
        public void setCustomInfo(String Info) {
            CustomInfo = Info;
        }

    }

}
