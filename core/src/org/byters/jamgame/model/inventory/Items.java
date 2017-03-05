package org.byters.jamgame.model.inventory;

import java.util.ArrayList;

public class Items {
    private ArrayList<ItemInfo> items;
    private ArrayList<CraftInfo> craft;
    private ArrayList<InteractInfo> timeInteract;
    private ArrayList<DisassemblyInfo> disassembly;

    public ArrayList<ItemInfo> getItems() {
        return items;
    }

    public ArrayList<CraftInfo> getCraft() {
        return craft;
    }

    public ArrayList<InteractInfo> getTimeInteract() {
        return timeInteract;
    }

    public ArrayList<DisassemblyInfo> getDisassembly() {
        return disassembly;
    }
}
