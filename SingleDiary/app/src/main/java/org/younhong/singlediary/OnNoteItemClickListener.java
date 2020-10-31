package org.younhong.singlediary;

import android.view.View;

public interface OnNoteItemClickListener {
    public void OnItemClick(NoteAdapter.ViewHolder holder, View view, int position);
}
