import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Questions} from "../model/questions";
import {League} from "../model/league";
import {Statistik} from "../model/statistik";
import {QuizQuestions} from "../model/quizQuestions";

@Injectable({
    providedIn: 'root'
})
export class LeagueService {

    constructor(private http: HttpClient) {}

    createQuizByAttribute(attribute: string): Observable<Questions[]> {
        return this.http.get<Questions[]>(`http://localhost:8080/league/createQuizByAttribute?attribute=${attribute}`);
    }

    getRandomUniqueValues(arr: string[], count: number): string[] {
        const uniqueValues = new Set<string>();
        while (uniqueValues.size < count) {
            const randomIndex = Math.floor(Math.random() * arr.length);
            uniqueValues.add(arr[randomIndex]);
        }

        return Array.from(uniqueValues);
    }

    createNewQuiz() {
        return this.http.get<League>('http://localhost:8080/league/createTestCharacters');
    }
    createStatistik(statistik: Statistik) {
        return this.http.post<Questions[]>('http://localhost:8080/league/saveStatistik', statistik);
    }

    getTop3Statistik() {
        return this.http.get<Statistik[]>('http://localhost:8080/league/top3Statistik');
    }

    createTestAnswers(attributeToGenerate: string) {

        let randomAnswers: string[] = [];

        switch (attributeToGenerate) {
            case "name":
                const championNames: string[] = [
                    "Ahri",
                    "Yasuo",
                    "Jinx",
                    "Darius",
                    "Leona",
                    "Ezreal",
                    "Garen",
                    "Lux",
                    "Zed",
                    "Ashe",
                    "Riven",
                    "Teemo",
                    "Rengar",
                    "Sona",
                    "Orianna",
                ];
                randomAnswers = this.getRandomUniqueValues(championNames, 3);

                break;
            case "HP":
                const hpValues: string[] = [
                    "Niedrig",
                    "Mittel",
                    "Hoch",
                    "sehr Hoch"
                ];
                randomAnswers = this.getRandomUniqueValues(hpValues, 3);
                break;
            case "Attack":
                const attackValues: string[] = [
                    "Niedrig",
                    "Mittel",
                    "Hoch",
                    "sehr Hoch"
                ];
                randomAnswers = this.getRandomUniqueValues(attackValues, 3);
                break;
            case "Defence":
                const defenceValues: string[] = [
                    "Niedrig",
                    "Mittel",
                    "Hoch",
                    "sehr Hoch"
                ];
                randomAnswers = this.getRandomUniqueValues(defenceValues, 3);
                break;
            default:

                break;
        }

        return randomAnswers;
    }

}
