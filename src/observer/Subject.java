package observer;

/**
 * Interface Subject.
 * */

public interface Subject {
  void addObserver(Observer o);

  void removeObserver(Observer o);

  void notifierObservateurs();
}
