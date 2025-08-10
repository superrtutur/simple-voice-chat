package de.maxhenkel.voicechat.voice.client;

import de.maxhenkel.voicechat.intercompatibility.ClientCompatibilityManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.Objects;

public class PTTKeyHandler {

    private boolean pttKeyDown;
    private boolean whisperKeyDown;

    public PTTKeyHandler() {
        ClientCompatibilityManager.INSTANCE.onKeyboardEvent(this::onKeyboardEvent);
        ClientCompatibilityManager.INSTANCE.onMouseEvent(this::onMouseEvent);
    }

    public void onKeyboardEvent() {
        if (KeyEvents.KEY_PTT.getKeyCode() > 0 && KeyEvents.KEY_PTT.getKeyCode() < 256) {
            pttKeyDown = Keyboard.isKeyDown(KeyEvents.KEY_PTT.getKeyCode());
        }
    }

    public void onMouseEvent() {
        if (KeyEvents.KEY_PTT.getKeyCode() < 0) {
            pttKeyDown = Mouse.isButtonDown(KeyEvents.KEY_PTT.getKeyCode() + 100);
        }
    }

    public boolean isPTTDown() {
        return pttKeyDown;
    }

    public boolean isWhisperDown() {
        return whisperKeyDown;
    }

    public boolean isAnyDown() {
        return pttKeyDown || whisperKeyDown;
    }

}
