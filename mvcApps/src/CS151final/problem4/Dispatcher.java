package CS151final.problem4;

import java.lang.reflect.*;

public class Dispatcher {
    // methName = name of method to be invoked
    // args = an array of inputs for method
    // uses reflection to call this.methName(args)
    
	public Object dispatch(String methName, Object... args) {
		Object result = null;
		try {
            // Get the class of the current instance
            Class<?> currentClass = this.getClass();

            // get the type for each argument that will be taken by the method
            Class<?>[] argTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                argTypes[i] = args[i].getClass();
            }

            // Get the method in the class matching the given name and argument types 
            Method method = currentClass.getMethod(methName, argTypes);
        
            // Call the method using invoke()
            result = method.invoke(this, args);

        } catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

}
