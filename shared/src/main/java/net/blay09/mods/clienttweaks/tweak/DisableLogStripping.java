package net.blay09.mods.clienttweaks.tweak;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.event.client.UseItemInputEvent;
import net.blay09.mods.clienttweaks.ClientTweaksConfig;
import net.blay09.mods.clienttweaks.ClientTweaksConfigData;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;

public class DisableLogStripping extends AbstractClientTweak {

    public DisableLogStripping() {
        super("disableLogStripping");

        Balm.getEvents().onEvent(UseItemInputEvent.class, this::onRightClick);
    }

    public void onRightClick(UseItemInputEvent event) {
        if (isEnabled()) {
            Minecraft mc = Minecraft.getInstance();
            ItemStack heldItem = mc.player != null ? mc.player.getItemInHand(event.getHand()) : ItemStack.EMPTY;
            if (!heldItem.isEmpty() && heldItem.getItem() instanceof AxeItem)
                event.setCanceled(true);
        }
    }

    @Override
    public boolean isEnabled() {
        return ClientTweaksConfig.getActive().tweaks.disableLogStripping;
    }

    @Override
    public void setEnabled(boolean enabled) {
        Balm.getConfig().updateConfig(ClientTweaksConfigData.class, it -> it.tweaks.disableLogStripping = enabled);
    }

    @Override
    public boolean hasKeyBinding() {
        return true;
    }
}
