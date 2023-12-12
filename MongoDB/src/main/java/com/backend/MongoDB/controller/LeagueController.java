package com.backend.MongoDB.controller;

import com.backend.MongoDB.model.League;
import com.backend.MongoDB.model.Questions;
import com.backend.MongoDB.model.Statistik;
import com.backend.MongoDB.repository.LeagueRepository;
import com.backend.MongoDB.repository.StatistikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/league")
public class LeagueController {

    @Autowired
    private final LeagueRepository leagueRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    private final StatistikRepository statistikRepository;

    public LeagueController(LeagueRepository leagueRepository, MongoTemplate mongoTemplate, StatistikRepository statistikRepository) {
        this.leagueRepository = leagueRepository;
        this.mongoTemplate = mongoTemplate;
        this.statistikRepository = statistikRepository;
    }

    @GetMapping("/createQuizByAttribute")
    public ResponseEntity<?> createQuizByAttribute(@RequestParam String attribute) {
        try {
            List<League> characters = leagueRepository.findAll();
            ArrayList<Questions> quizQuestions = new ArrayList<>();
            if (attribute.equals("HP")) {
                for (League character : characters) {
                    Questions questions = new Questions();
                    String question = "Wie viel " + attribute + " hat der Character " + character.getName() + "?";
                    String answer = character.getHp();
                    questions.setAnswers(answer);
                    questions.setQuestion(question);

                    quizQuestions.add(questions);
                }
            } else if (attribute.equals("Attack")) {
                for (League character : characters) {
                    Questions questions = new Questions();
                    String question = "Wie viel " + attribute + " Power hat der Character " + character.getName() + "?";
                    String answer = character.getHp();
                    questions.setAnswers(answer);
                    questions.setQuestion(question);

                    quizQuestions.add(questions);
                }
            } else if (attribute.equals("Defence")) {
                for (League character : characters) {
                    Questions questions = new Questions();
                    String question = "Wie viel " + attribute + " Power hat der Character " + character.getName() + "?";
                    String answer = character.getHp();
                    questions.setAnswers(answer);
                    questions.setQuestion(question);

                    quizQuestions.add(questions);
                }
            }
            return ResponseEntity.ok(quizQuestions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Fehler beim Erstellen des Quiz.");
        }
    }



    @PostMapping("/saveStatistik")
    public ResponseEntity<?> saveStatistik(@RequestBody Statistik statistik) {

        try {
            this.statistikRepository.save(statistik);
            return ResponseEntity.ok("Created");
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }

    @GetMapping("/top3Statistik")
    public ResponseEntity<?> getTop3Statistik() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.sort(Sort.by(Sort.Direction.ASC, "time")),
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "points")),
                Aggregation.limit(3)
        );
        Statistik[] top3 = mongoTemplate.aggregate(aggregation, "statistik", Statistik.class)
                .getMappedResults().toArray(new Statistik[0]);
        return ResponseEntity.ok(top3);
    }

    @GetMapping("/createTestCharacters")
    public ResponseEntity<?> createPerson() {
        try {
             String[][] characterData = {
                    {"Ahri", "Mittel", "Hoch", "Niedrig"},
                    {"Yasuo", "Mittel", "Hoch", "Mittel"},
                    {"Jinx", "Niedrig", "Hoch", "Niedrig"},
                    {"Darius", "Hoch", "Mittel", "Hoch"},
                    {"Leona", "Hoch", "Niedrig", "Mittel"},
                    {"Ezreal", "Niedrig", "Hoch", "Niedrig"},
                    {"Garen", "Hoch", "Niedrig", "Hoch"},
                    {"Lux", "Niedrig", "Mittel", "Niedrig"},
                    {"Zed", "Mittel", "Hoch", "Niedrig"},
                    {"Ashe", "Niedrig", "Mittel", "Niedrig"},
                    {"Riven", "Mittel", "Hoch", "Niedrig"},
                    {"Teemo", "Niedrig", "Mittel", "Niedrig"},
                    {"Rengar", "Hoch", "Hoch", "Niedrig"},
                    {"Sona", "Niedrig", "Niedrig", "Mittel"},
                    {"Orianna", "Mittel", "Mittel", "Niedrig"},
                    {"Malphite", "Hoch", "Niedrig", "Hoch"},
                    {"Katarina", "Niedrig", "Hoch", "Niedrig"},
                    {"Vayne", "Niedrig", "Hoch", "Niedrig"},
                    {"Tryndamere", "Hoch", "Hoch", "Niedrig"},
                    {"Alistar", "Hoch", "Niedrig", "Hoch"}
            };

            for (String[] data : characterData) {
                League character = new League();
                character.setName(data[0]);
                character.setAttack(data[1]);
                character.setHp(data[2]);
                character.setDefence(data[3]);

                this.leagueRepository.save(character);
            }
            return ResponseEntity.ok("Created");
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }

}
