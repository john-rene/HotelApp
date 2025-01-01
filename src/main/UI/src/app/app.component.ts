import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {HttpClient, HttpResponse,HttpHeaders} from "@angular/common/http";
import { Observable } from 'rxjs';
import {map} from "rxjs/operators";





@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(private httpClient:HttpClient){}

  private baseURL:string='http://localhost:8080';

  private getUrl:string = this.baseURL + '/room/reservation/v1/';
  private postUrl:string = this.baseURL + '/room/reservation/v1';
  public submitted!:boolean;
  roomsearch! : FormGroup;
  rooms! : Room[];
  request!:ReserveRoomRequest;
  currentCheckInVal!:string;
  currentCheckOutVal!:string;

  // New variable to hold welcome messages
  public welcomeMessages: string[] = [];
  // Variable to hold the converted times
  public convertedTimes: string[] = [];


  ngOnInit(){
      this.roomsearch= new FormGroup({
        checkin: new FormControl(' '),
        checkout: new FormControl(' ')
      });

    // Fetch the converted times
    this.getConvertedTimes();

 //     this.rooms=ROOMS;


    const roomsearchValueChanges$ = this.roomsearch.valueChanges;

    // subscribe to the stream
    roomsearchValueChanges$.subscribe(x => {
      this.currentCheckInVal = x.checkin;
      this.currentCheckOutVal = x.checkout;
    });

    // Fetch welcome messages on component initialization
    this.getWelcomeMessages();

    // Fetch welcome messages on component initialization
    this.getWelcomeMessages();

    // Fetch the converted times
    this.getConvertedTimes();
  }


  onSubmit({ value, valid }: { value: Roomsearch; valid: boolean }){
    this.getAll().subscribe(

      rooms => {console.log(Object.values(rooms)[0]);this.rooms = <Room[]>Object.values(rooms)[0];

        // Make conversion rates and assign to new variables
        this.rooms.forEach(room => {
          room.priceCAD = (parseFloat(room.price) * 1.3).toFixed(2);
          room.priceEUR = (parseFloat(room.price) * 0.85).toFixed(2);
        });
      }
    );
  }


  reserveRoom(value:string){
      this.request = new ReserveRoomRequest(value, this.currentCheckInVal, this.currentCheckOutVal);

      this.createReservation(this.request);
    }
    createReservation(body:ReserveRoomRequest) {
      let bodyString = JSON.stringify(body); // Stringify payload
      let headers = new Headers({'Content-Type': 'application/json'}); // ... Set content type to JSON
     // let options = new RequestOptions({headers: headers}); // Create a request option

     const options = {
      headers: new HttpHeaders().append('key', 'value'),

    }

      this.httpClient.post(this.postUrl, body, options)
        .subscribe(res => console.log(res));
    }

  /*mapRoom(response:HttpResponse<any>): Room[]{
    return response.body;
  }*/

    getAll(): Observable<any> {


       return this.httpClient.get(this.baseURL + '/room/reservation/v1?checkin='+ this.currentCheckInVal + '&checkout='+this.currentCheckOutVal, {responseType: 'json'});
    }

  getWelcomeMessages(): void {
    this.httpClient.get<string[]>(this.baseURL + '/api/welcome', { responseType: 'json' })
      .subscribe(
        (response: any) => {
          this.welcomeMessages = [response.english, response.french];
          console.log('Welcome Messages:', this.welcomeMessages);  // this line is to check if messages are fetched
        },
        (error) => {
          console.error('Error fetching welcome messages', error);
        }
      );
  }

  getConvertedTimes() {
    // Example time in ET
    const time = "2024-08-30T16:30:00-04:00";
    this.httpClient.get(`${this.baseURL}/api/convert-time?time=${encodeURIComponent(time)}`, { responseType: 'text' })
      .subscribe(response => {
        // Split the string into an array
        this.convertedTimes = response.split(', ');
      }, error => {
        console.error('Error fetching converted times', error);
      });
  }


}



export interface Roomsearch{
    checkin:string;
    checkout:string;
  }




export interface Room{
  id:string;
  roomNumber:string;
  price:string;
  links:string;
  priceCAD?: string; // Added for Canadian Dollar price
  priceEUR?: string; // Added for Euro price
}
export class ReserveRoomRequest {
  roomId:string;
  checkin:string;
  checkout:string;

  constructor(roomId:string,
              checkin:string,
              checkout:string) {

    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }
}

/*
var ROOMS: Room[]=[
  {
  "id": "13932123",
  "roomNumber" : "409",
  "price" :"20",
  "links" : ""
},
{
  "id": "139324444",
  "roomNumber" : "509",
  "price" :"30",
  "links" : ""
},
{
  "id": "139324888",
  "roomNumber" : "609",
  "price" :"40",
  "links" : ""
}
] */

