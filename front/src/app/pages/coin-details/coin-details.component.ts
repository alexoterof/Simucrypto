import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { Chart } from 'chart.js';
import { CoinDetails } from 'src/app/model/coin-detail';
import { CoinService } from 'src/app/services/coin/coin.service';

@Component({
  selector: 'app-coin-details',
  templateUrl: './coin-details.component.html',
  styleUrls: ['./coin-details.component.scss']
})
export class CoinDetailsComponent implements OnInit {
  monthChart: Chart;
  dayChart: Chart;
  dayData: number[];
  monthData: number[];
  monthAndNotDay: boolean = true;
  coin: CoinDetails;

  data: any;

  constructor(private activatedRouter: ActivatedRoute,
              private router: Router,
              private cryptoService: CoinService) { }

  ngOnInit(): void {
    this.coin = new CoinDetails();
    const id = String(this.activatedRouter.snapshot.paramMap.get('id'))
    this.cryptoService.findDetail(id).subscribe(
      (response) => {
        this.coin = response;
      },
      (error) => {
        console.log(error);
      }
    );
    this.cryptoService.findHistoric(id).subscribe(
      (response) => {
        this.dayData = response.day;
        this.monthData = response.month
        this.setUpHistoric();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  setUpHistoric(){
    this.monthChart = new Chart("month", {
      type: "line",
      data: {
        labels: new Array(30), 
        datasets: [{
            data: this.monthData,
            backgroundColor: "rgba(29, 71, 111, 0.2)",
            borderColor: "rgba(29, 71, 111, 1)",
            borderWidth: 3,
            radius: 0,
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        legend: { display: false },
        scales: {
          xAxes: [{ display: false }],
        }
      },
    });
  }

  toggleHistoric(){
    this.monthAndNotDay = !this.monthAndNotDay;
    if(this.monthAndNotDay){
      this.monthChart.data.datasets.forEach(dataset => {
        dataset.data = this.monthData;
        dataset.backgroundColor = "rgba(29, 71, 111, 0.2)"; 
        dataset.borderColor = "rgba(29, 71, 111, 1)";
      });
      this.monthChart.data.labels = new Array(30);
    }

    else{
      this.monthChart.data.labels = new Array(24);
      this.monthChart.data.datasets.forEach(dataset => {
        dataset.data = this.dayData;
        dataset.backgroundColor = "rgba(255, 99, 132, 0.2)"; 
        dataset.borderColor = "rgba(255, 99, 132, 1)";
      });
    }
    this.monthChart.update();
    console.log(this.monthChart.data);
  }

/*
  getData(){
    const DATA_COUNT = 13;
    const labels = [];
    for (let i = 0; i < DATA_COUNT; ++i) {
      labels.push(i.toString());
    }
    const datapoints = [0, 20, 20, 60, 60, 120, 130, 180, 120, 125, 105, 110, 170];
    this.data = {
      labels: labels,
      datasets: [
        {
          label: 'Cubic interpolation (monotone)',
          data: datapoints,
          borderColor: '#FF0000',
          fill: false,
          cubicInterpolationMode: 'monotone',
          tension: 0.4
        }
      ]
    };
  }
*/
  initializaCanvas(){
    
  }

  buy(){
    let dataObject: NavigationExtras = {
      queryParams: {
          "data": JSON.stringify(this.coin)
      }
    };
    this.router.navigate(["buy"],  dataObject);
    return `/buy?state=${btoa(JSON.stringify(this.coin))}`;
  }

}
