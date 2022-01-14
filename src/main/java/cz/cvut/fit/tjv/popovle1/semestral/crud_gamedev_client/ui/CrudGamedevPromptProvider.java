package cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.ui;

import cz.cvut.fit.tjv.popovle1.semestral.crud_gamedev_client.data.DevClient;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CrudGamedevPromptProvider implements PromptProvider {
    private final DevClient devClient;

    public CrudGamedevPromptProvider(DevClient devClient) {
        this.devClient = devClient;
    }

    @Override
    public AttributedString getPrompt() {
        if(devClient.getCurrentId() == null)
            return new AttributedString("crud_gamedev:>",
                    AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
        return new AttributedString("dev=" + devClient.getCurrentId() + ":>",
                AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }
}
