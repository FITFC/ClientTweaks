package net.blay09.mods.clienttweaks.tweak;

import net.blay09.mods.clienttweaks.ClientTweaksConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NoOffhandTorchAtAll extends AbstractClientTweak {

    public NoOffhandTorchAtAll() {
        super("noOffhandTorchAtAll", ClientTweaksConfig.CLIENT.noOffhandTorchAtAll);
    }

    @SubscribeEvent
    public void onRightClick(InputEvent.ClickInputEvent event) {
        if (isEnabled() && event.isUseItem() && event.getHand() == Hand.OFF_HAND) {
            Minecraft mc = Minecraft.getInstance();
            ItemStack heldItem = mc.player != null ? mc.player.getHeldItem(event.getHand()) : ItemStack.EMPTY;
            if (!heldItem.isEmpty()) {
                ResourceLocation registryName = heldItem.getItem().getRegistryName();
                if (registryName != null) {
                    if (ClientTweaksConfig.CLIENT.torchItems.get().contains(registryName.toString())) {
                        event.setSwingHand(false);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

}
