package hu.gde.runnersdemo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoeNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long shoeId;
    private String shoeName;
    @JsonIgnore
    @OneToMany
    private List<RunnerEntity> shoeNameRunners = new ArrayList<>();

    public long getShoeId() {
        return shoeId;
    }

    public void setShoeId(long shoeId) {
        this.shoeId = shoeId;
    }

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    public List<RunnerEntity> getShoeNameRunners() {
        return shoeNameRunners;
    }

    public void setShoeNameRunners(List<RunnerEntity> shoeNameRunners) {
        this.shoeNameRunners = shoeNameRunners;
    }
}
