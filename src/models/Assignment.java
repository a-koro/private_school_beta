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
public class Assignment {
    private int assignmentId;
    private String assignmentTitle;
    private String description;
    private int maxOralMark;
    private int maxTotalMark;

    public Assignment(int assignmentId, String assignmentTitle, String description, int maxOralMark, int maxTotalMark) {
        this.assignmentId = assignmentId;
        this.assignmentTitle = assignmentTitle;
        this.description = description;
        this.maxOralMark = maxOralMark;
        this.maxTotalMark = maxTotalMark;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxOralMark() {
        return maxOralMark;
    }

    public void setMaxOralMark(int maxOralMark) {
        this.maxOralMark = maxOralMark;
    }

    public int getMaxTotalMark() {
        return maxTotalMark;
    }

    public void setMaxTotalMark(int maxTotalMark) {
        this.maxTotalMark = maxTotalMark;
    }
    
}
