package ua.kpi.fpm.patterns;

/**
 * Created by Anastasia Serhienko on 5/27/16.
 */
public class StateApp {
    public static void main(String[] args) {

//        Station dfm = new RadioDFM();
//        Radio radio = new Radio();
//        radio.setStation(dfm);
//
//        for (int i=0; i<10; i++) {
//            radio.play();
//            radio.nextStation();
//        }

        Human human = new Human();
        human.setState(new Work());
        for (int i = 0; i < 10; i++) {
            human.doSomething();
        }
    }
}

// Context
class Human {
    private Activity state;
    public void setState(Activity s) {this.state = s;}

    public void doSomething() {
        state.doSomething(this);
    }
}

// State
interface Activity {
    void doSomething(Human context);
}

class Work implements Activity {
    public void doSomething(Human context) {
        System.out.println("Working!!!");
        context.setState(new WeekEnd());
    }
}

class WeekEnd implements Activity {
    private int count = 0;
    public void doSomething(Human context) {
        if (count < 3) {
            System.out.println("Resting (Zzz)");
            count++;
            //context.setState(this);
        }
        else {
            context.setState(new Work());
        }
    }
}

// State
interface Station {
    void play();
}
class Radio7 implements Station {
    public void play() {System.out.println("Radio 7...");}
}
class RadioDFM implements Station {
    public void play() {System.out.println("Radio DFM...");}
}
class VistiFM implements Station {
    public void play() {System.out.println("Visti FM...");}
}

// Context
class Radio {
    Station station;
    void setStation(Station st) {station = st;}
    void nextStation() {
        if (station instanceof Radio7) {
            setStation(new RadioDFM());
        }
        else if (station instanceof RadioDFM) {
            setStation(new VistiFM());
        }
        else if (station instanceof VistiFM) {
            setStation(new Radio7());
        }
    }
    void play() {
        station.play();
    }
}