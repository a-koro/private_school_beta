/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author korov
 */
public class Trainer {
    private int trainerId;
    private String trainerFirstName;
    private String trainerLastName;
    private String subject;

    public Trainer(int trainerId, String trainerFirstName, String trainerLastName, String subject) {
        this.trainerId = trainerId;
        this.trainerFirstName = trainerFirstName;
        this.trainerLastName = trainerLastName;
        this.subject = subject;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerFirstName() {
        return trainerFirstName;
    }

    public void setTrainerFirstName(String trainerFirstName) {
        this.trainerFirstName = trainerFirstName;
    }

    public String getTrainerLastName() {
        return trainerLastName;
    }

    public void setTrainerLastName(String trainerLastName) {
        this.trainerLastName = trainerLastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
}
