package view.resources;

import java.util.ListResourceBundle;

public class Resources extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"author.first", "Michal Dudkiewicz"},
                {"author.second", "Marek Kulicki"}
        };
    }
}