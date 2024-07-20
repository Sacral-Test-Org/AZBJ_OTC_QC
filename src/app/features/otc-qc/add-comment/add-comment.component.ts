import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UnderwriterCommentService } from 'src/app/core/services/underwriter-comment.service';
import { UnderwriterCommentDTO } from 'src/app/shared/models/underwriter-comment.dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent {
  commentForm: FormGroup;
  contractId: string;
  userId: string;

  constructor(
    private fb: FormBuilder,
    private underwriterCommentService: UnderwriterCommentService,
    private router: Router
  ) {
    this.commentForm = this.fb.group({
      comment: ['', Validators.required]
    });
    // Assume contractId and userId are set globally or passed via route params
    this.contractId = '12345'; // Example contract ID
    this.userId = 'user123'; // Example user ID
  }

  onSubmit(): void {
    if (this.commentForm.valid) {
      const commentData: UnderwriterCommentDTO = {
        eventNo: null, // This will be set in the backend
        contractId: this.contractId,
        policyNo: 'policy123', // Example policy number
        userId: this.userId,
        commentDate: new Date(),
        comments: this.commentForm.get('comment')?.value,
        flag: 'Y'
      };

      this.underwriterCommentService.addComment(commentData).subscribe(
        () => {
          alert('Comment added successfully');
          this.commentForm.reset();
          this.router.navigate(['/comments']); // Navigate to comments list or another appropriate page
        },
        (error) => {
          console.error('Error adding comment:', error);
        }
      );
    }
  }
}
