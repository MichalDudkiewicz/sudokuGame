package view.resources;

import java.util.ListResourceBundle;

public class Resources_pl extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"author.first", "Michał Dudkiewicz"},
                {"author.second", "Marek Kulicki"}
        };
    }
}