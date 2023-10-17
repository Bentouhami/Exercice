package com.mvntest.DAL.Status;

public class Status {
    private int id;
    private String status;
<<<<<<< HEAD

    public Status(String status) {
        this.status = status;
    }

=======
    
    public Status(String status) {
        this.status = status;
    }
>>>>>>> 606efc33e42394375883277cca04f6f28bba333f
    public Status() {
    }

    public Status(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status [id=" + id + ", status=" + status + "]";
    }

}
