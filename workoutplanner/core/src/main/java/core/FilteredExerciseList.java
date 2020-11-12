package core;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilteredExerciseList extends ExerciseList {

    private Collection<Equipment> equipmentFilters = new HashSet<>();
    private Collection<BodyPart> bodyPartFilters = new HashSet<>();
    private String nameFilter;

    public void addEquipmentFilter(Equipment equipment) {
        if(equipmentFilters.add(equipment))
            fireExerciseListChanged();
    }

    public void removeEquipmentFilter(Equipment equipment) {
        if(equipmentFilters.remove(equipment));
            fireExerciseListChanged();
    }

    public void addBodyPartFilter(BodyPart bodyPart) {
        if(bodyPartFilters.add(bodyPart))
            fireExerciseListChanged();
    }

    public void removeBodyPartFilter(BodyPart bodyPart) {
        if(bodyPartFilters.remove(bodyPart))
            fireExerciseListChanged();
    }

    public void setNameFilter(String nameFilter) {
        boolean changed = false;
        if(this.nameFilter != null && !this.nameFilter.equals(nameFilter) || this.nameFilter == null && nameFilter != null)
            changed = true;
        this.nameFilter = nameFilter;
        if(changed)
            fireExerciseListChanged();
    }

    public Collection<Exercise> getFilteredExercises() {
        if (bodyPartFilters.size() == 0 && equipmentFilters.size() == 0 && nameFilter == null)
            return getExercises();
        Collection<Exercise> filteredExercises = getExercises();
        for (Equipment equipment : equipmentFilters)
            filteredExercises = filteredExercises.stream().filter(getEquipmentFilter(equipment)).collect(Collectors.toList());
        for (BodyPart bodyPart : bodyPartFilters)
            filteredExercises = filteredExercises.stream().filter(getBodyPartFilter(bodyPart)).collect(Collectors.toList());
        if (nameFilter != null)
            filteredExercises = filteredExercises.stream().filter(getNameFilter(nameFilter)).collect(Collectors.toList());
        return filteredExercises;
    }

    private static Predicate<Exercise> getNameFilter(String name) {
        String formatName = name.trim().toLowerCase();
        return exercise -> formatName.length() <= exercise.getName().length() &&
                exercise.getName().substring(0, formatName.length()).toLowerCase().equals(formatName);
    }

    private static Predicate<Exercise> getBodyPartFilter(BodyPart bodyPart) {
        return exercise -> exercise.getBodyPart().equals(bodyPart);
    }

    private static Predicate<Exercise> getEquipmentFilter(Equipment equipment) {
        return exercise -> exercise.getEquipment().contains(equipment);
    }
}
