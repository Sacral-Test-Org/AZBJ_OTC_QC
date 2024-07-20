import { Component, OnInit } from '@angular/core';
import { UnderwriterCommentService } from 'src/app/core/services/underwriter-comment.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {
  comment: string = '';
  isCheckboxChecked: boolean = false;
  commentCount: number = 0;
  status: string = '';

  constructor(private underwriterCommentService: UnderwriterCommentService) {}

  ngOnInit(): void {
    // Initialize the comments section
  }

  toggleCommentsInput(): void {
    this.isCheckboxChecked = !this.isCheckboxChecked;
  }

  saveComment(comment: string): void {
    // Save the comment to a local variable or service
    this.comment = comment;
  }

  submitComment(): void {
    if (!this.comment) {
      alert('Please enter a comment.');
      return;
    }

    this.underwriterCommentService.submitComment(this.comment).subscribe(
      (response) => {
        this.commentCount++;
        switch (this.status) {
          case 'R':
            this.status = 'REJECT';
            // Navigate to the reject section
            break;
          case 'LS':
            this.status = 'LINK/SAVE';
            // Navigate to the link/save section
            break;
          case 'QC':
            this.status = 'PROPOSAL_INVOKED';
            // Navigate to the QC section
            break;
          default:
            this.handleComments();
            break;
        }
      },
      (error) => {
        console.error('Error submitting comment:', error);
      }
    );
  }

  private handleComments(): void {
    // Procedure to handle comments
    // Retrieve contract ID based on the application number
    // Generate a new event number for the comment
    // Insert the comment into the comments table with relevant details
    // Commit the transaction
  }
}
