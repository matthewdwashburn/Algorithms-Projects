package mvcsquares;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Solution to MVC project
 * This abstract class is observable with some additional methods
 * @author Nathan Gossett
 * @version Spring 2019
 *
 */
public abstract class SquareModel{
	/**
	 * @return An appropriate description of the state of the system
	 */
	public abstract String getFeedback();
	
	/** A helper object to handle observer pattern behavior */
	protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	/**
	 * 
	 * @param size The new size of the square, must be positive
	 */
	public abstract void setSize(int size);
	
	/**
	 * 
	 * @return the number of rows and columns in this square
	 */
	public abstract int getSize();
	
	/**
	 * 
	 * @return A title for this application
	 */
	public abstract String getTitle();
	
	/**
	 * Clear out all values
	 */
	public abstract void clear();
	
	/**
	 * return String representation of specified location
	 */
	public abstract String getValueAt(int row, int col);
	
	/**
	 * Interpret the supplied String and store value at specified location if valid
	 */
	public abstract void setValueAt(String data, int row, int col);
	
	/**
	 * Don't forget to create a way for Observers to subscribe to this
	 * @param listener
	 */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    /**
     * And Observers probably want to be able to unsubscribe as well
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
    
}
