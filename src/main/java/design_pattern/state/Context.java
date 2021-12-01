package design_pattern.state;


public class Context {
    private State state;

    public Context(){
        state = State.NEW_BUILT;
    }

    public void start() {
        State st = state.start();
        if (st!=null){
            this.state = st;
        }
    }

    public void getCpu() {
        State st = state.getCpu();
        if (st!=null){
            this.state = st;
        }
    }

    public void stop() {
        State st = state.stop();
        if (st!=null){
            this.state = st;
        }
    }

    public void resume() {
        State st = state.resume();
        if (st!=null){
            this.state = st;
        }
    }

    public void suspend() {
        State st = state.suspend();
        if (st!=null){
            this.state = st;
        }
    }
}
