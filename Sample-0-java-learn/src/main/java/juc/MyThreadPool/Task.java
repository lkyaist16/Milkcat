package juc.MyThreadPool;

public class Task {
    private int id;

    private Runnable job;

    public Task(int id, Runnable job) {
        this.id = id;
        this.job = job;
    }

    public Task(Runnable job) {
        this.job = job;
    }

    public void job(){
        job.run();
    }

    public int getId() {
        return id;
    }
}
