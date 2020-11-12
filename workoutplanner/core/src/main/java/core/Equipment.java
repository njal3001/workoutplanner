package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Equipment {

    private String name;

    public static Equipment BARBELL = new Equipment("Barbell"), DUMBBELL = new Equipment("Dumbbell"),
            BENCH = new Equipment("Bench"), MACHINE = new Equipment("Machine"),
            KETTLE_BELL = new Equipment("Kettle-bell"), EZ_BAR = new Equipment("EZ-bar"),
            BANDS = new Equipment("Bands"), WEIGHT_PLATE = new Equipment("Weight Plate"),
            BODY_WEIGHT = new Equipment("Body Weight"), OTHER = new Equipment("Other");

    private Equipment(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public static Collection<Equipment> getAllEquipment(){
        return new ArrayList<>(Arrays.asList(BARBELL, DUMBBELL,
                BENCH, MACHINE, KETTLE_BELL, EZ_BAR, BANDS, WEIGHT_PLATE, BODY_WEIGHT, OTHER));
    }

    public static Equipment valueOf(String name) {
        return getAllEquipment().stream().filter(e -> e.name.equals(name)).findFirst().orElse(null);
    }
}
