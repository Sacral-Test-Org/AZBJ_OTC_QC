export class PanDetailsModel {
  panNumber: string;
  panStatus: string;
  nameMatch: boolean;
  dobMatch: boolean;
  partnerType: string;
  firstName: string;
  middleName: string;
  surname: string;
  dateOfBirth: Date;

  constructor(
    panNumber: string,
    panStatus: string,
    nameMatch: boolean,
    dobMatch: boolean,
    partnerType: string,
    firstName: string,
    middleName: string,
    surname: string,
    dateOfBirth: Date
  ) {
    this.panNumber = panNumber;
    this.panStatus = panStatus;
    this.nameMatch = nameMatch;
    this.dobMatch = dobMatch;
    this.partnerType = partnerType;
    this.firstName = firstName;
    this.middleName = middleName;
    this.surname = surname;
    this.dateOfBirth = dateOfBirth;
  }
}