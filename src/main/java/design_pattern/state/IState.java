package design_pattern.state;


public interface IState {
    State start();
    State getCpu();
    State stop();
    State resume();
    State suspend();
}
