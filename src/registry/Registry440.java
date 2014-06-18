package registry;

import ror.RemoteObjectRef;

public interface Registry440 {
	
	/**
	 * Given the name of the service, looks up on the RMI registry
	 * and get the remote object reference of that service if registered
	 * on registry
	 * 
	 * @param serviceName the name for the remote reference to look up
	 * @return RemoteObjectRef a reference to a remote object, if found
	 */
	public RemoteObjectRef<?> lookup(String serviceName) throws Exception;
	
	/**
	 * Returns an array of the names bound in this registry
	 * 
	 * @return an array of the names bound in this registry
	 */
	public void rebind(String serviceName, RemoteObjectRef<?> ror);
	
	/**
	 * Replaces the binding for the specified serviceName in this registry with 
	 * the supplied remote reference. If there is an existing binding for the 
	 * specified serviceName, it is discarded.
	 * 
	 * @param serviceName the name to associate with the remote reference
	 * 		  on RMI registry
	 * @param ror a reference to a remote object (usually a stub)
	 * @return The string array of all services registered on RMIregistry
	 */
	public String[] list();
	
	/**
	 * Removes the binding for the specified name in this registry.
	 * 
	 * @param serviceName the name of the binding to remove
	 */
	public void unbind(String serviceName); 
	
}
