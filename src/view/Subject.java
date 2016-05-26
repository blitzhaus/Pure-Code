package view;

public interface Subject {
	void notifyObservers();
	void registerObserver(GuiObserver o);

}
