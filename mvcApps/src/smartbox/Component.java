package smartbox;

import java.util.*;
import java.io.Serializable;
import java.lang.reflect.*;



public class Component implements Serializable {

	private Set<Class<?>> requiredInterfaces;
	private Set<Class<?>> providedInterfaces;
	private transient Map<Class<?>, Field> fields; // transient because Field not serializable
	protected Container container;
	protected String name;

	public Component() {
		 fields = new HashMap<Class<?>, Field>();
		 providedInterfaces = new HashSet<Class<?>>();
		 requiredInterfaces = new HashSet<Class<?>>();
		 computeRequiredInterfaces();
		 computeProvidedInterfaces();
		 container = null;
		 name = this.getClass().getSimpleName();
	}

	// add needed getters & setters
	public Set<Class<?>> getRequiredInterfaces() {
		return requiredInterfaces;
	}

	public Set<Class<?>> getProvidedInterfaces() {
		return providedInterfaces;
	}

	public Map<Class<?>, Field> getFields() {
		return fields;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public String toString() { return name; }

    // initializes fields and requiredInterfaces
	public void computeRequiredInterfaces() {
		Field[] fieldArray = this.getClass().getDeclaredFields();
		for (Field field : fieldArray) {
			Class<?> type = field.getType();
			if (type.isInterface()) {
				fields.put(type, field);
				requiredInterfaces.add(type);
				
			}
		}
			//if the type of field[i] is an interface, then add it to fields and requiredInterfaces ???
	}

	// initializes provided interfaces
	public void computeProvidedInterfaces() {
		Class<?>[] interfaces = this.getClass().getInterfaces();
		for (Class<?> i : interfaces) {
			if (i.isInterface()) {
				providedInterfaces.add(i);
			}
		}
		// get interfaces implemented by the class of this component and add them to providedInterfaces
	}

	// set the field of this object to the provider
	public void setProvider(Class<?> intf, Component provider) throws Exception {
		Field field = fields.get(intf);
		field.set(this, provider); // field probably needs to be public for this.
	}

	// needed by file/open
	public void initComponent() {
            fields = new HashMap<Class<?>, Field>();
	    computeProvidedInterfaces();
	    computeRequiredInterfaces();
    }
}
