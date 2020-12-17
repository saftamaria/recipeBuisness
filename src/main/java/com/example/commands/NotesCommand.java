package com.example.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotesCommand {
    public Long notesId;
    public String recipeNotes;
    public Long recipeId;
}
