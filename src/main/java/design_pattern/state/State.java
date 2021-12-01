package design_pattern.state;


public enum State implements IState {
    /**
     * 新建状态
     */
    NEW_BUILT("new"){
        @Override
        public State start() {
            System.out.println("启动线程!启动后状态变为就绪");
            return READY;
        }
    },
    /**
     * 就绪状态
     */
    READY("ready"){
        @Override
        public State getCpu() {
            System.out.println("就绪状态getCPU,变成运行状态");
            return RUNNING;
        }
    },
    /**
     * 运行状态
     */
    RUNNING("running"){
        @Override
        public State stop() {
            System.out.println("当前状态stop后，变为dead状态");
            return DEAD;
        }

        @Override
        public State suspend() {
            System.out.println("当前状态suspend后，变为阻塞状态");
            return BLOCK;
        }
    },
    /**
     * 阻塞状态
     */
    BLOCK("blocking"){
        @Override
        public State resume() {
            System.out.println("当前状态经过resume后，变成就绪状态readyState");
            return READY;
        }
    },
    /**
     * 死亡状态
     */
    DEAD("dead");

    private String stateName;

    State(String stateName){
        this.stateName = stateName;
    }

    public State start() {
        System.out.println("当前状态，不可以start");
        return null;
    }

    public State getCpu() {
        System.out.println("当前状态，不可以getCpu");
        return null;
    }

    public State stop() {
        System.out.println("当前状态，不可以stop");
        return null;
    }

    public State resume() {
        System.out.println("当前状态，不可以resume");
        return null;
    }

    public State suspend() {
        System.out.println("当前状态，不可以suspend");
        return null;
    }
}
