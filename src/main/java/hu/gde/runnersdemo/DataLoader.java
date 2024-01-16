package hu.gde.runnersdemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private final RunnerRepository runnerRepository;
    private final LapTimeRepository lapTimeRepository;
    private final ShoeNameRepository shoeNameRepository;

    @Autowired
    public DataLoader(RunnerRepository runnerRepository, LapTimeRepository lapTimeRepository, ShoeNameRepository shoeNameRepository) {
        this.runnerRepository = runnerRepository;
        this.lapTimeRepository = lapTimeRepository;
        this.shoeNameRepository = shoeNameRepository;
    }

    @Override
    public void run(String... args) {

        ShoeNameEntity shoeNameEntity = new ShoeNameEntity();
        shoeNameEntity.setShoeName("Adidas");
        shoeNameRepository.save(shoeNameEntity);

        RunnerEntity runnerEntity = new RunnerEntity();
        runnerEntity.setRunnerName("Tomi");
        runnerEntity.setPace(110);
        runnerEntity.setShoeSize(45);
        runnerEntity.setShoeName(shoeNameEntity);
        runnerRepository.save(runnerEntity);

        LapTimeEntity laptime1 = new LapTimeEntity();
        laptime1.setLapNumber(1);
        laptime1.setTimeSeconds(120);
        laptime1.setRunner(runnerEntity);
        lapTimeRepository.save(laptime1);

        LapTimeEntity laptime2 = new LapTimeEntity();
        laptime2.setLapNumber(2);
        laptime2.setTimeSeconds(110);
        laptime2.setRunner(runnerEntity);
        lapTimeRepository.save(laptime2);

        runnerEntity.getLaptimes().add(laptime1);
        runnerEntity.getLaptimes().add(laptime2);

        ShoeNameEntity shoeNameEntity2 = new ShoeNameEntity();
        shoeNameEntity2.setShoeName("Puma");
        shoeNameRepository.save(shoeNameEntity2);

        RunnerEntity runnerEntity2 = new RunnerEntity();
        runnerEntity2.setRunnerName("Zsuzsi");
        runnerEntity2.setPace(100);
        runnerEntity2.setShoeSize(37);
        runnerEntity2.setShoeName(shoeNameEntity2);
        runnerRepository.save(runnerEntity2);

        LapTimeEntity laptime3 = new LapTimeEntity();
        laptime3.setLapNumber(1);
        laptime3.setTimeSeconds(95);
        laptime3.setRunner(runnerEntity2);
        lapTimeRepository.save(laptime3);

        LapTimeEntity laptime4 = new LapTimeEntity();
        laptime4.setLapNumber(2);
        laptime4.setTimeSeconds(100);
        laptime4.setRunner(runnerEntity2);
        lapTimeRepository.save(laptime4);

        runnerEntity2.getLaptimes().add(laptime3);
        runnerEntity2.getLaptimes().add(laptime4);

        ShoeNameEntity shoeNameEntity3 = new ShoeNameEntity();
        shoeNameEntity3.setShoeName("Nike");
        shoeNameRepository.save(shoeNameEntity3);

        RunnerEntity runnerEntity3 = new RunnerEntity();
        runnerEntity3.setRunnerName("Dani");
        runnerEntity3.setPace(76);
        runnerEntity3.setShoeSize(42);
        runnerEntity3.setShoeName(shoeNameEntity3);
        runnerRepository.save(runnerEntity3);

        LapTimeEntity laptime5 = new LapTimeEntity();
        laptime5.setLapNumber(1);
        laptime5.setTimeSeconds(83);
        laptime5.setRunner(runnerEntity3);
        lapTimeRepository.save(laptime5);

        LapTimeEntity laptime6 = new LapTimeEntity();
        laptime6.setLapNumber(2);
        laptime6.setTimeSeconds(91);
        laptime6.setRunner(runnerEntity3);
        lapTimeRepository.save(laptime6);

        runnerEntity2.getLaptimes().add(laptime5);
        runnerEntity2.getLaptimes().add(laptime6);
    }
}

