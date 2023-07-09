import com.unrealdinnerbone.unreallib.discord.DiscordWebhook;
import com.unrealdinnerbone.unreallib.discord.EmbedObject;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class DiscordJsonTest
{
    @Test
    public void testJson() {
        DiscordWebhook webhook = DiscordWebhook.builder();
        webhook.addEmbed(EmbedObject.builder().title("Test")
                        .field("Test", "Test", true)
                        .author("test")
                        .color(Color.RED)
                        .image("https://unreal.codes/kevStonk.png")
                .description("Test").build());
    }
}
