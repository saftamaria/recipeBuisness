package com.example.converters;

import com.example.commands.NotesCommand;
import com.example.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        if (source == null) {
            return null;
        }
        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setNotesId(source.getNotesId());
        notesCommand.setRecipeNotes(source.getRecipeNotes());
        if (source.getRecipe() != null) {
            notesCommand.setRecipeId(source.getRecipe().getRecipeId());
        }
        return notesCommand;
    }
}
