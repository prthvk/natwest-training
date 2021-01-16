import { Component, OnInit } from '@angular/core';
import { DashdataService } from '../services/dashdata.service';
import { AuthserviceService } from '../services/authservice.service';
import { Router } from '@angular/router';
import { Chart } from 'node_modules/chart.js';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  minDateFrom: Date;
  maxDateFrom: Date;

  minDateTo: Date;
  maxDateTo: Date;

  public topdata: any;
  public selectedUiType = 'Choose Format';
  public selectedFromDate = new FormControl(new Date());
  public selectedToDate = new FormControl(new Date());
  public resultMessage = "Showing all results";
  public loadDataFormat = 'Tabular';
  public chart = new Chart('myChart', {});

  constructor(private dashData: DashdataService, private authservice: AuthserviceService, private router: Router) {
  }

  ngOnInit() {
    return this.dashData.getPosts().subscribe(
      data => {
        //console.log(data);
        this.topdata = data["restaurants"];
        // console.log(data["restaurants"])
        // console.log(this.topdata)
      },
      error => {
        console.log(error.message);
      })
  }

  selectUiType(uiType) {
    this.selectedUiType = uiType;
    console.log(this.selectedUiType);
  }

  renderData() {
    // check if both dates and display format has been selected or not. If not then don't allow fetching by showing alert with respective error messages.
    if (this.selectedUiType === "Choose Format") {
      alert("Please choose a representation format");
    } else {
      this.loadDataFormat = this.selectedUiType;

      // Fetch Data and do further activities below upon success of fetching data. Change resultMessage to "No results" if they dont exist in given window.
      // Handle other cases as well if any.

      console.log("Render Data");
      // get the picker1 date in some variable |  console.log(this.selectedFromDate);
      // get the picker2 date in some variable | console.log(this.selectedToDate);

      // Checking if a representation UI type is selected or not
      if (false/*no results found*/) {
        this.resultMessage = "No results found!";
      } else if (this.loadDataFormat === "Choose Format") {
        this.resultMessage = "Showing All Results";
      } else {
        this.resultMessage = "Showing results as per your selection";
      }


      if (this.loadDataFormat === "Pie Chart") {
        this.chart = this.getPieChart(true);
      } else if (this.loadDataFormat === "Bar Graph") {
        this.chart = this.getBarGraph(true);
      } else if (this.loadDataFormat === "Polar Area Chart") {
        this.chart = this.getPolarChart(true);
      }
    }
  }

  // retrieve values from transactin dataArray to return an array with sum of values of expenses in food, hotel, travel anf other decided tags.
  getDataArray(data) {
    // var retVal = [0,0,0,0];
    // data.array.forEach(element => {
    //   if (element.tag=="Food"){
    //     retVal[0]+=element.amount;
    //   } else if (element.tag=="Hotel"){
    //     retVal[1]+=element.amount;
    //   } else if(element.tag=="Travel"){
    //     retVal[2]+=element.amount;
    //   } else {
    //     retVal[3]+=element.amount;
    //   }
    // });

    // at present return for 4 tags
    return [10, 20, 30, 25];
  }

  getPieChart(data) {
    this.chart.destroy();
    var pieChartData = {
      datasets: [{
        // label: '# of Votes',
        data: this.getDataArray(data),
        backgroundColor: [
          'rgba(255, 99, 132, 0.5)',
          'rgba(54, 162, 235, 0.5)',
          'rgba(255, 206, 86, 0.5)',
          'rgba(221, 160, 221, 0.5)'
        ],
        borderColor: [
          'rgba(255, 99, 132, 1)',
          'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(221, 160, 221, 1)'
        ],
        borderWidth: 1
      }],
      // These labels appear in the legend and in the tooltips when hovering different arcs
      labels: [
        'Food',
        'Hotel',
        'Travel',
        'Miscellaneous'
      ]
    };

    return new Chart('myChart', {
      type: 'pie',
      data: pieChartData,
      options: Chart.defaults.pie
    });
  }

  getBarGraph(data) {
    this.chart.destroy();

    var barGraphData = {
      type: 'bar',
      data: {
        labels: ['Food', 'Hotel', 'Travel', 'Miscellaneous'],
        datasets: [{
          data: this.getDataArray(data),
          backgroundColor: [
            'rgba(255, 99, 132, 0.5)',
            'rgba(54, 162, 235, 0.5)',
            'rgba(255, 206, 86, 0.5)',
            'rgba(221, 160, 221, 0.5)'
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(221, 160, 221, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        legend: { display: false },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    };

    return new Chart('myChart', barGraphData);
  }

  getPolarChart(data) {
    this.chart.destroy();

    var polarChartData = {
      type: 'polarArea',
      data: {
        labels: ['Food', 'Hotel', 'Travel', 'Miscellaneous'],
        datasets: [{
          data: this.getDataArray(data),
          backgroundColor: [
            'rgba(255, 99, 132, 0.5)',
            'rgba(54, 162, 235, 0.5)',
            'rgba(255, 206, 86, 0.5)',
            'rgba(221, 160, 221, 0.5)'
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(221, 160, 221, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        legend: { display: false },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    };

    return new Chart('myChart', polarChartData);
  }
}
