package me.henryfbp.library;

import android.text.Editable;
import android.text.TextWatcher;


/**
 * https://stackoverflow.com/questions/9385081/how-can-i-change-the-edittext-text-without-triggering-the-text-watcher/42928051#42928051
 */
public abstract class EditableTextWatcher implements TextWatcher {

    private boolean editing;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (this.editing)
            return;

        this.editing = true;
        try {
            this.beforeTextChange(s, start, count, after);
        } finally {
            this.editing = false;
        }
    }

    public abstract void beforeTextChange(CharSequence s, int start, int count, int after);

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (this.editing)
            return;

        this.editing = true;
        try {
            this.onTextChange(s, start, before, count);
        } finally {
            this.editing = false;
        }
    }

    protected abstract void onTextChange(CharSequence s, int start, int before, int count);

    @Override
    public void afterTextChanged(Editable s) {
        if (this.editing)
            return;

        this.editing = true;
        try {
            this.afterTextChange(s);
        } finally {
            this.editing = false;
        }
    }

    public boolean isEditing() {
        return this.editing;
    }

    protected abstract void afterTextChange(Editable s);
}
