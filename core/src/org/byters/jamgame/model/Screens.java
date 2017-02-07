package org.byters.jamgame.model;

import org.byters.engine.view.IScreen;
import org.byters.jamgame.view.ScreenGame;
import org.byters.jamgame.view.ScreenMenu;
import org.byters.jamgame.view.ScreenText;

public enum Screens {
    MENU(), GAME(), TEXT();

    Screens() {
    }

    public IScreen getScreen() {
        //todo just simple init new object
        if (this.equals(Screens.GAME))
            return new ScreenGame();
        else if (this.equals(Screens.MENU))
            return new ScreenMenu();
        else if (this.equals(Screens.TEXT))
            return new ScreenText();
        return null;
    }
}
