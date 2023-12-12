import {Component, OnInit} from '@angular/core';
import {LeagueService} from "./service/league.service";
import {Statistik} from "./model/statistik";
import {interval, Subject, takeUntil} from "rxjs";
import {QuizQuestions} from "./model/quizQuestions";
import {Questions} from "./model/questions";
import {League} from "./model/league";

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss']
})
export class QuizComponent implements OnInit{
  attributes = ["HP", "Attack", "Defence"];
  top3Players: Statistik[] = [];
  timer = new Subject<string>();
  destroy$ = new Subject<void>();
  startTime!: Date;
  quizCount: number = 0;
  isFinished: boolean = false;
  isSelected: boolean = false;
  endTime!: Date;
  quiz: QuizQuestions = {
    answers: [],
    question: ''
  };
  userName: string = '';
  questions: Questions[] = []
  currentStep: number = 0; // Start at step 1
  steps: number[] = [1, 2, 3]; // Adjust number of steps as needed
  selectedAttribute: string = "";
  playerPoints: number = 0;
  constructor(private leagueService: LeagueService) {}

  ngOnInit() {
    this.leagueService.getTop3Statistik().subscribe(response => {
      console.log('Statistik gespeichert', response);
      this.top3Players = response

    });

    this.leagueService.createQuizByAttribute("name").subscribe(response => {
      if(response.length === 0) {
        this.leagueService.createNewQuiz().subscribe(data => {

        })
      }
    })
  }

  onLoadData(quizAttribut: string) {
    this.selectedAttribute = quizAttribut;
    if (this.selectedAttribute) {
      this.leagueService.createQuizByAttribute(this.selectedAttribute).subscribe(data => {
        this.questions = data;

        this.startTime = new Date();
        this.loadNewQuiz(data[this.quizCount]);

        interval(1000).pipe(takeUntil(this.destroy$)).subscribe(() => {
          const currentTime = new Date();
          const diff = currentTime.getTime() - this.startTime.getTime();
          const timeString = new Date(diff).toISOString().substr(11, 8);
          this.timer.next(timeString);
        });

        this.isSelected = true;
      });
    } else {
      console.log("Bitte wählen Sie ein Attribut aus.");
    }
    this.nextStep();
  }
  loadNewQuiz(data: Questions) {

    const testAnswers = this.leagueService.createTestAnswers(this.selectedAttribute);
    const randomIndex = Math.floor(Math.random() * (testAnswers.length + 1));
    testAnswers.splice(randomIndex, 0, data.answers);

    this.quiz.answers = testAnswers;
    this.quiz.question = data.question;

  }


  nextStep() {
    if (this.currentStep < 4) {
      this.currentStep++;
    }
  }

  nextQuiz(answer: string) {
    this.quizCount += 1;
    if (this.questions[this.quizCount - 1].answers == answer) {
      this.playerPoints += 1;
    }
    console.log(this.quizCount < this.questions.length)
    if (this.quizCount < this.questions.length) {
      let league: Questions = this.questions[this.quizCount];

      this.loadNewQuiz(league);
    } else {
      this.endTime = new Date();
      const timeTaken = new Date(this.endTime.getTime() - this.startTime.getTime()).toISOString().substr(11, 8);
      this.saveStatistik(timeTaken);
      this.isSelected = false;
      console.log("Quiz Finished");
    }
  }
  saveStatistik(time: string) {
    const statistik: Statistik = {
      points: this.playerPoints,
      time: time,
      userName: this.userName,
    };
    this.leagueService.createStatistik(statistik).subscribe(response => {
      console.log('Statistik gespeichert', response);
    });
    this.isFinished = true;
  }
  restartQuiz() {
    this.currentStep = 0;
    this.isFinished = false;
  }
}
