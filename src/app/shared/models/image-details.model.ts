export class ImageDetailsModel {
  imagePath: string;
  proposalNumber: string;
  applicationNumber: string;
  imageName: string;
  imageScanTime: Date;
  imageSize: number;
  numberOfPages: number;

  constructor(
    imagePath: string,
    proposalNumber: string,
    applicationNumber: string,
    imageName: string,
    imageScanTime: Date,
    imageSize: number,
    numberOfPages: number
  ) {
    this.imagePath = imagePath;
    this.proposalNumber = proposalNumber;
    this.applicationNumber = applicationNumber;
    this.imageName = imageName;
    this.imageScanTime = imageScanTime;
    this.imageSize = imageSize;
    this.numberOfPages = numberOfPages;
  }
}