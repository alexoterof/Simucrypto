import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Router } from '@angular/router';
import { Chart } from 'chart.js';
import { CoinDetails } from 'src/app/model/coin/coin-detail';
import { CoinService } from 'src/app/services/coin/coin.service';

@Component({
  selector: 'app-coin-details',
  templateUrl: './coin-details.component.html',
  styleUrls: ['./coin-details.component.scss']
})
export class CoinDetailsComponent implements OnInit {
  coin: CoinDetails;
  chart: Chart;
  dayData: number[];
  monthData: number[];
  monthAndNotDay: boolean;
  graphTitle: string;

  data: any;

  constructor(private activatedRouter: ActivatedRoute,
              private router: Router,
              private cryptoService: CoinService) {
                this.monthAndNotDay = true;
                this.graphTitle = "Monthly historic";
               }

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
    this.chart = new Chart("chart", {
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
      this.chart.data.datasets.forEach(dataset => {
        dataset.data = this.monthData;
        dataset.backgroundColor = "rgba(73, 68, 201, 0.2)"; 
        dataset.borderColor = "rgba(73, 68, 201, 1)";
      });
      this.chart.data.labels = new Array(30);
      this.graphTitle = "Monthly historic";
    }

    else{
      this.chart.data.labels = new Array(24);
      this.chart.data.datasets.forEach(dataset => {
        dataset.data = this.dayData;
        dataset.backgroundColor = "rgba(201, 143, 6, 0.2)"; 
        dataset.borderColor = "rgba(201, 143, 6, 1)";
      });
      
      this.graphTitle = "Daily historic";
    }
    this.chart.update();
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
