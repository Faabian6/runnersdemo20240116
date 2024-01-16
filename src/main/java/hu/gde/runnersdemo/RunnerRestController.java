package hu.gde.runnersdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/runner")
public class RunnerRestController {

    @Autowired
    private LapTimeRepository lapTimeRepository;
    private RunnerRepository runnerRepository;
    private ShoeNameRepository shoeNameRepository;

    @Autowired
    public RunnerRestController(RunnerRepository runnerRepository, LapTimeRepository lapTimeRepository, ShoeNameRepository shoeNameRepository) {
        this.runnerRepository = runnerRepository;
        this.lapTimeRepository = lapTimeRepository;
        this.shoeNameRepository = shoeNameRepository;
    }

    @GetMapping("/{id}")
    public RunnerEntity getRunner(@PathVariable Long id) {
        return runnerRepository.findById(id).orElse(null);
    }

    @GetMapping("/{id}/averagelaptime")
    public double getAverageLaptime(@PathVariable Long id) {
        RunnerEntity runner = runnerRepository.findById(id).orElse(null);
        if (runner != null) {
            List<LapTimeEntity> laptimes = runner.getLaptimes();
            int totalTime = 0;
            for (LapTimeEntity laptime : laptimes) {
                totalTime += laptime.getTimeSeconds();
            }
            double averageLaptime = (double) totalTime / laptimes.size();
            return averageLaptime;
        } else {
            return -1.0;
        }
    }
    @GetMapping("/biggestshoesize")
    public String getBiggestShoeSize() {
        List<RunnerEntity> runnerList = runnerRepository.findAll();
        if (!runnerList.isEmpty()) {
            RunnerEntity biggestShoeSize = runnerList.get(0);
            for(int i = 1; i < runnerList.size(); i++){
                if (biggestShoeSize.getShoeSize() < runnerList.get(i).getShoeSize())  {
                    biggestShoeSize = runnerList.get(i);
                }
            }
            return biggestShoeSize.getRunnerName();
        } else {
            return "Hiba";
        }
    }
    @GetMapping("")
    public List<RunnerEntity> getAllRunners() {
        return runnerRepository.findAll();
    }

    @PostMapping("/{id}/addlaptime")
    public ResponseEntity addLaptime(@PathVariable Long id, @RequestBody LapTimeRequest lapTimeRequest) {
        RunnerEntity runner = runnerRepository.findById(id).orElse(null);
        if (runner != null) {
            LapTimeEntity lapTime = new LapTimeEntity();
            lapTime.setTimeSeconds(lapTimeRequest.getLapTimeSeconds());
            lapTime.setLapNumber(runner.getLaptimes().size() + 1);
            lapTime.setRunner(runner);
            lapTimeRepository.save(lapTime);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Runner with ID " + id + " not found");
        }
    }
    @GetMapping("/averagepace")
    public double getAveragePace() {
        List<RunnerEntity> runnerList = runnerRepository.findAll();
        if (!runnerList.isEmpty()) {
            int totalPace = 0;
            int counter = 0;
            for(int i = 0; i < runnerList.size(); i++){
                totalPace += runnerList.get(i).getPace();
                counter++;
            }
            return (double) totalPace / counter;
        } else {
            return -1.00;
        }
    }
    @PostMapping("/{id}/setshoe")
    public ResponseEntity setShoe(@PathVariable Long id, @RequestBody ShoeRequest shoeRequest) {
        RunnerEntity runner = runnerRepository.findById(id).orElse(null);
        ShoeNameEntity shoe = shoeNameRepository.findById(shoeRequest.getShoeId()).orElse(null);
        if(runner != null && shoe != null) {
            runner.setShoeName(shoe);
            runnerRepository.save(runner);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Runner with ID " + id + " not found");
        }
    }
    public static class LapTimeRequest {
        private int lapTimeSeconds;

        public int getLapTimeSeconds() {
            return lapTimeSeconds;
        }

        public void setLapTimeSeconds(int lapTimeSeconds) {
            this.lapTimeSeconds = lapTimeSeconds;
        }
    }
    public static class ShoeRequest {
        private long shoeId;

        public long getShoeId() {
            return shoeId;
        }

        public void setShoeId(long shoeId) {
            this.shoeId = shoeId;
        }
    }
}
