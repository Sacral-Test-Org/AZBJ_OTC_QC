export class UnderwriterComment {
  userId: string;
  commentDate: Date;
  commentText: string;

  constructor(userId: string, commentDate: Date, commentText: string) {
    this.userId = userId;
    this.commentDate = commentDate;
    this.commentText = commentText;
  }
}