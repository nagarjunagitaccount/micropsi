package stepdefination;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    @Before
    public void setup()
    {
        disconnectsteps stesp=new disconnectsteps();
        stesp.diconnect();
        Connectsteps step=new Connectsteps();
        step.connect();
    }
    @After
    public void teardown()
    {
        disconnectsteps stesp=new disconnectsteps();
        stesp.diconnect();
    }
}
