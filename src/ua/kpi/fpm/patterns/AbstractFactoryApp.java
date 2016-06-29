package ua.kpi.fpm.patterns;

/**
 * Created by Anastasia Serhienko on 6/21/16.
 */
public class AbstractFactoryApp {

    public static void main(String[] args) {

        DeviceFactory factory = getFactoryByCountryCode("UA");
        Mouse m = factory.getMouse();
        Keyboard k = factory.getKeyboard();
        Touchpad t = factory.getTouchpad();

        m.click();
        k.print();
        k.println();
        t.track(10, 35);
    }

    private static DeviceFactory getFactoryByCountryCode(String lang) {
        switch (lang) {
            case "UA":
                return new UaDeviceFactory();
            case "EN":
                return new EnDeviceFactory();
            default:
                throw new RuntimeException("Unsupported Country Code: " + lang);
        }
    }
}

interface Mouse {
    void click();
    void dblclick();
    void scroll(int direction);
}

interface Keyboard {
    void print();
    void println();
}

interface Touchpad {
    void track(int deltaX, int deltaY);
}

interface DeviceFactory {
    Mouse getMouse();
    Keyboard getKeyboard();
    Touchpad getTouchpad();
}

class UaMouse implements Mouse {

    @Override
    public void click() {
        System.out.println("Клацання мишкою");
    }

    @Override
    public void dblclick() {
        System.out.println("Подвійне клацання мишкою");
    }

    @Override
    public void scroll(int direction) {

        if (direction > 0) {
            System.out.println("Скролимо вгору");
        }
        else if (direction < 0) {
            System.out.println("Скролимо вниз");
        }
        else {
            System.out.println("Не скролимо");
        }
    }
}

class UaKeyboard implements Keyboard {

    @Override
    public void print() {
        System.out.print("Друкуємо рядок");
    }

    @Override
    public void println() {
        System.out.println("Друкуємо рядок з перенесенням на новий рядок");
    }
}

class UaTouchpad implements Touchpad {

    @Override
    public void track(int deltaX, int deltaY) {
        int s = (int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        System.out.println("Пересунулись на " + s + " пікселів");
    }
}

class EnMouse implements Mouse {

    @Override
    public void click() {
        System.out.println("Mouse click");
    }

    @Override
    public void dblclick() {
        System.out.println("Mouse double click");
    }

    @Override
    public void scroll(int direction) {

        if (direction > 0) {
            System.out.println("Scroll Up");
        }
        else if (direction < 0) {
            System.out.println("Scroll Down");
        }
        else {
            System.out.println("No scrolling");
        }
    }
}

class EnKeyboard implements Keyboard {

    @Override
    public void print() {
        System.out.print("Print");
    }

    @Override
    public void println() {
        System.out.println("Print line");
    }
}

class EnTouchpad implements Touchpad {

    @Override
    public void track(int deltaX, int deltaY) {
        int s = (int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        System.out.println("Moved " + s + " pixels");
    }
}

class EnDeviceFactory implements DeviceFactory {

    @Override
    public Mouse getMouse() {
        return new EnMouse();
    }

    @Override
    public Keyboard getKeyboard() {
        return new EnKeyboard();
    }

    @Override
    public Touchpad getTouchpad() {
        return new EnTouchpad();
    }
}

class UaDeviceFactory implements DeviceFactory {

    @Override
    public Mouse getMouse() {
        return new UaMouse();
    }

    @Override
    public Keyboard getKeyboard() {
        return new UaKeyboard();
    }

    @Override
    public Touchpad getTouchpad() {
        return new UaTouchpad();
    }
}