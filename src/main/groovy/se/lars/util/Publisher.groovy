package se.lars.util;
public class Publisher {
    List<Subscriber> subscribers = []

    def send (String msg){
        subscribers*.receive(msg)
    }
}
