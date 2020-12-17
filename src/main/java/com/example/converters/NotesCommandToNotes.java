package com.example.converters;

import com.example.commands.NotesCommand;
import com.example.model.Notes;
import com.example.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if (source == null) {
            return null;
        }
        final Notes notes = new Notes();
        notes.setNotesId(source.getNotesId());
        notes.setRecipeNotes(source.getRecipeNotes());
        if (source.getRecipeId() != null){
            Recipe recipe = new Recipe();
            recipe.setRecipeId(source.getRecipeId());
            notes.setRecipe(recipe);
            recipe.setCustomNotes(notes);
        }
        return notes;
    }
}
