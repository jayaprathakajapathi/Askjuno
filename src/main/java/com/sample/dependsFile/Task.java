package com.sample.dependsFile;

import java.time.LocalDateTime;

public class Task {
private String name;
private String assignedTo;
private LocalDateTime date;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAssignedTo() {
	return assignedTo;
}
public void setAssignedTo(String assignedTo) {
	this.assignedTo = assignedTo;
}
public LocalDateTime getDate() {
	return date;
}
public void setDate(LocalDateTime date) {
	this.date = date;
}

}
