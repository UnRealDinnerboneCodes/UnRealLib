import com.unrealdinnerbone.unreallib.SimpleColor;
import com.unrealdinnerbone.unreallib.discord.DiscordWebhook;
import com.unrealdinnerbone.unreallib.discord.EmbedObject;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class DiscordJsonTest
{
    @Test
    public void testJson() {
        DiscordWebhook webhook = DiscordWebhook.builder();
        webhook.setAvatarUrl("https://unreal.codes/kevStonk.png");
        webhook.addEmbed(EmbedObject.builder().title("Test")
                        .field("Test", "Test", true)
                        .author("test")
                        .color(SimpleColor.fromRGB(255, 255, 255))
                .description("Test").build());
    }
}
