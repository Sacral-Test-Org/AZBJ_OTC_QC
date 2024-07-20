import { Component, Input, Output, EventEmitter } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-editor-window',
  templateUrl: './editor-window.component.html',
  styleUrls: ['./editor-window.component.css']
})
export class EditorWindowComponent {
  @Input() comments: string;
  @Output() commentsUpdated = new EventEmitter<string>();

  constructor(public dialogRef: MatDialogRef<EditorWindowComponent>) {}

  openEditorWindow(comments: string): void {
    this.comments = comments;
    this.dialogRef.open();
  }

  updateComments(): void {
    this.commentsUpdated.emit(this.comments);
    this.dialogRef.close();
  }
}
